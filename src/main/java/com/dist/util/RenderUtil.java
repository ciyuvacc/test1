package com.dist.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

public class RenderUtil {

	private static Logger log = LoggerFactory.getLogger(RenderUtil.class);

	/**
	 * @param request
	 * @param response
	 * @param fileName
	 * @param enCoding
	 *            填写UTF-8
	 * @param hssfWorkbook
	 */
	public static void PoiRender(HttpServletRequest request, HttpServletResponse response, String fileName, String enCoding, HSSFWorkbook hssfWorkbook) throws Exception {
		String agent = request.getHeader("User-Agent");
        boolean isMSIE = (agent != null && agent.indexOf("MSIE") != -1);
        if( isMSIE ){
        	response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode(fileName,"utf-8"));
        }else {
        	response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes("UTF-8"),"ISO-8859-1"));	
        }

		if (StringUtils.isBlank(enCoding)) {
			enCoding = "UTF-8";
		}
		BufferedInputStream bis = null;
	    OutputStream bos = null;
		String contentType = "application/vnd.ms-excel;charset=" + enCoding;
		response.setContentType(contentType);
		
		try {
			bos = response.getOutputStream();
			hssfWorkbook.write(bos);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (bos != null) {
					bos.flush();
					bos.close();
				}
			} catch (IOException e) {
				log.error(e.getMessage(), e);
			}

		}
		
	}
	
	
	/**
	 * @param request
	 * @param response
	 * @param fileName
	 * @param enCoding
	 *            填写UTF-8
	 * @param hssfWorkbook
	 */
	public static void PoiRender(HttpServletRequest request, HttpServletResponse response, String fileName, String enCoding, SXSSFWorkbook hssfWorkbook) throws Exception {
		String agent = request.getHeader("User-Agent");
        boolean isMSIE = (agent != null && agent.indexOf("MSIE") != -1);
        if( isMSIE ){
        	response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode(fileName,"utf-8"));
        }else {
        	response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes("UTF-8"),"ISO-8859-1"));	
        }

		if (StringUtils.isBlank(enCoding)) {
			enCoding = "UTF-8";
		}
		BufferedInputStream bis = null;
	    OutputStream bos = null;
		String contentType = "application/vnd.ms-excel;charset=" + enCoding;
		response.setContentType(contentType);
		
		try {
			bos = response.getOutputStream();
			hssfWorkbook.write(bos);
		} catch (Exception e) {
			log.error("写入io出现异常"+e.getMessage(),e);
			throw new RuntimeException(e);
		} finally {
			try {
				if (bos != null) {
					bos.flush();
					bos.close();
				}
			} catch (IOException e) {
				log.error(e.getMessage(), e);
			}

		}
		
	}

	/**
	 * 
	 * @Title: download
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param request
	 * @param @param response
	 * @param @param storeName
	 * @param @param contentType
	 * @param @param realName
	 * @param @throws Exception 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	public static void ZipRender( HttpServletResponse response, String path) throws Exception {
		 try {  
			           // path是指欲下载的文件的路径。  
		          File file = new File(path);  
			           // 取得文件名。  
			          String filename = file.getName();  
			            // 清空response  
			            response.reset();  
			             // 设置response的Header  
			            response.addHeader("Content-Disposition", "attachment;filename="  
			                  + new String(filename.getBytes()));  
			            response.addHeader("Content-Length", "" + file.length());  
			            response.setContentType("application/vnd.ms-excel; charset=utf-8");  
			            response.setCharacterEncoding("utf-8");
			            BufferedOutputStream toClient = new BufferedOutputStream(  
			            		response.getOutputStream()); 
			            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(path));
			    		byte[] buff = new byte[2048];
			    		int bytesRead;
			    		while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
			    			toClient.write(buff, 0, bytesRead);
			    		}
			    		bis.close();
			    		toClient.close();
			          
			        } catch (IOException ex) {  
			             ex.printStackTrace();  
			        }  
			     }
}
