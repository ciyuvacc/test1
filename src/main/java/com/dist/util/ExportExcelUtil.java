package com.dist.util;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * description 工具类导出excel
 * @param <T>
 */
@Slf4j
public class ExportExcelUtil<T> {

    /**
     * @param title 表格标题名
     * @param headers 表格属性列名数组
     * @param columns 表格属性列属性数组
     *                对数字处理时column后面接"*"或"/"再接数字，可以进行运算
     * @param dataset 数据集合
     * @param <T>
     * @return
     * @author: yuyang
     * @date: 2017/9/6 13:22
     */
    @SuppressWarnings("unchecked")
    public static<T> SXSSFWorkbook exportExce(String title, String[] headers, String[] columns, Collection<T> dataset){
        // 声明一个工作薄
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        SXSSFWorkbook sxssfWorkbook = new SXSSFWorkbook(xssfWorkbook, 100);
        // 生成一个表格
        SXSSFSheet sheet = sxssfWorkbook.createSheet(title);
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth((short) 15);
        // 生成一个样式
        CellStyle style = sxssfWorkbook.createCellStyle();
        // 设置这些样式
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 生成一个字体
        Font font = sxssfWorkbook.createFont();
        font.setColor(HSSFColor.VIOLET.index);
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        style.setFont(font);
        // 生成并设置另一个样式
        CellStyle style2 = sxssfWorkbook.createCellStyle();
        style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 生成另一个字体
        Font font2 = sxssfWorkbook.createFont();
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        // 把字体应用到当前的样式
        style2.setFont(font2);
        // 声明一个画图的顶级管理器
        Drawing patriarch = sheet.createDrawingPatriarch();

        // 产生表格标题行
        SXSSFRow row = sheet.createRow(0);
        for (short i = 0; i < headers.length; i++)
        {
            SXSSFCell cell = row.createCell(i);
            cell.setCellStyle(style);
            cell.setCellValue(headers[i]);
        }

        // 遍历集合数据，产生数据行
        Iterator<T> it = dataset.iterator();
        int index = 0;
        while (it.hasNext())
        {
            index++;
            row = sheet.createRow(index);
            T t = it.next();
            for (short i = 0; i < columns.length; i++)
            {
                SXSSFCell cell = row.createCell(i);
                cell.setCellStyle(style2);
                String fieldName = columns[i];
                String ope = "";
                Double dob = 0d;
                if(fieldName.indexOf("/") != -1){
                    String[] arr = fieldName.split("/");
                    fieldName = arr[0];
                    ope = "/";
                    dob = Double.parseDouble(arr[1]);
                }else if(fieldName.indexOf("*") != -1){
                    String[] arr = fieldName.split("\\*");
                    fieldName = arr[0];
                    ope = "*";
                    dob = Double.parseDouble(arr[1]);
                }


                try
                {
                    Class tCls = t.getClass();
                    String getMethodName = null;
                    Method getMethod = null;
                    Object value = null;
                    if (t instanceof Map) {	// add Map 支持
                        getMethodName = "get";
                        getMethod = tCls.getMethod(getMethodName, Object.class);
                        value = getMethod.invoke(t, fieldName);
                    } else {				   // pojo
                        getMethodName = "get"
                                + fieldName.substring(0, 1).toUpperCase()
                                + fieldName.substring(1);
                        getMethod = tCls.getMethod(getMethodName,
                                new Class[]{});
                        value = getMethod.invoke(t, new Object[]
                                {});
                    }

                    // 判断值的类型后进行强制类型转换
                    String textValue = null;
                    if (value instanceof Date)
                    {
                        Date date = (Date) value;
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        textValue = sdf.format(date);
                        sheet.setColumnWidth(i, textValue.getBytes().length*256+184);
                    }
                    else if (value instanceof byte[])
                    {
                        // 有图片时，设置行高为60px;
                        row.setHeightInPoints(60);
                        // 设置图片所在列宽度为80px,注意这里单位的一个换算
                        sheet.setColumnWidth(i, (short) (35.7 * 80));
                        // sheet.autoSizeColumn(i);
                        byte[] bsValue = (byte[]) value;
                        HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0,
                                1023, 255, (short) 6, index, (short) 6, index);
                        patriarch.createPicture(anchor, sxssfWorkbook.addPicture(
                                bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));
                        anchor.setAnchorType(2);
                        patriarch.createPicture(anchor, sxssfWorkbook.addPicture(
                                bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));
                    }
                    else if (value instanceof Integer || value instanceof Long || value instanceof Float || value instanceof Double || value instanceof BigDecimal)
                    {
                        // 是数字当作double处理
                        Double dValue = Double.parseDouble(value.toString());
                        if(dob != 0){
                            if(ope.equals("/")){
                                cell.setCellValue(dValue/dob);
                            }else if(ope.equals("*")){
                                cell.setCellValue(dValue*dob);
                            }
                        }else{
                            cell.setCellValue(dValue);
                        }
                        sheet.setColumnWidth(i, 15*256+184);
                    }
                    else
                    {
                        // 其它数据类型都当作字符串简单处理
                        textValue = value==null?"":value.toString();
                        if(StringUtils.isNotEmpty(textValue)){
                            sheet.setColumnWidth(i, textValue.getBytes().length*256+184);
                        }
                    }
                    if (textValue != null)
                    {
                        cell.setCellValue(textValue);
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return sxssfWorkbook;
    }

    /**
     * 生成Excel文件流
     * @param excelTypeEnum 文件类型
     * @param sheetName 表格名称
     * @param rows  表格数据
     * @param clz   生成Excel所需模型 extends @{code BaseRowModel}
     * @param request
     * @param response
     */
    @Deprecated
    public static void geneExcel(ExcelTypeEnum excelTypeEnum, String sheetName,
                                 List<? extends BaseRowModel> rows, Class clz,
                                 HttpServletRequest request,
                                 HttpServletResponse response){

        try {
            String agent = request.getHeader("User-Agent");
            boolean isMSIE = (agent != null && agent.indexOf("MSIE") != -1);
            if( isMSIE ){
                response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode(sheetName,"utf-8"));
            }else {
                response.setHeader("Content-disposition", "attachment; filename=" + new String(sheetName.getBytes("UTF-8"),"ISO-8859-1"));
            }
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            OutputStream outputStream = response.getOutputStream();
            ExcelWriter writer = new ExcelWriter(outputStream, excelTypeEnum, true);
            Sheet sheet = new Sheet(1, 1, clz);
            sheet.setSheetName(sheetName);
            writer.write(rows, sheet);
            writer.finish();
        } catch (Exception e){
            log.error("生成Excel文件异常", e);
        }
    }

    /**
     * 生成单sheet文件
     * @param sheetName
     * @param rows
     * @param clz
     */
    public static void geneExcel(String sheetName, List<?> rows, Class clz,
                                 HttpServletRequest request,
                                 HttpServletResponse response){
        OutputStream outputStream = null;
        try {
            String agent = request.getHeader("User-Agent");
            boolean isMSIE = (agent != null && agent.indexOf("MSIE") != -1);
            if( isMSIE ){
                response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode(sheetName,"utf-8"));
            }else {
                response.setHeader("Content-disposition", "attachment; filename=" + new String(sheetName.getBytes("UTF-8"),"ISO-8859-1"));
            }
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            outputStream = response.getOutputStream();
            ExcelWriter excelWriter = EasyExcel.write(outputStream, clz).build();
            WriteSheet writeSheet = EasyExcel.writerSheet(sheetName).build();
            excelWriter.write(rows, writeSheet);
            excelWriter.finish();
        } catch (Exception e){
            log.error("生成Excel文件异常", e);
        }
    }

}
