package com.dist.controller;

import com.dist.constants.BaseConstants;
import com.dist.entity.Order;
import com.dist.entity.OrderArea;
import com.dist.mapper.OrderAreaMapper;
import com.dist.param.OrderAreaParam;
import com.dist.param.OrderParam;
import com.dist.param.SaveOrder;
import com.dist.service.OrderService;
import com.dist.util.DateUtil;
import com.dist.util.ExportExcelUtil;
import com.dist.util.RenderUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Slf4j
@RestController
@RequestMapping(value = "/dist/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderAreaMapper orderAreaMapper;


    private Integer exportPageSize = 10000;

    private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    /**
     * 订单状态的修改
     *
     * @param orderAreaParam
     * @return
     */
    @ApiOperation("通过流水号修改订单状态")
    @RequestMapping(value = "/updateStatusByOrderId", method = RequestMethod.POST)
    public Map<String, Object> updateStatusByOrderId(@RequestBody OrderAreaParam orderAreaParam) {

        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("code", BaseConstants.SUCCESS);
        returnMap.put("msg", BaseConstants.SUCCESSMSG);
        try {
            if (StringUtils.isBlank(orderAreaParam.getOrderId()) ||
                    StringUtils.isBlank(orderAreaParam.getOrderStatus())) {
                returnMap.put("code", BaseConstants.FAIL);
                returnMap.put("msg", "参数错误");
                return returnMap;
            }
            Order order = orderService.queryOrderByOrderId(orderAreaParam.getOrderId());
            if (order == null) {
                returnMap.put("code", BaseConstants.FAIL);
                returnMap.put("msg", "订单记录不存在!");
                return returnMap;
            }

            OrderArea orderArea = new OrderArea();
            orderArea.setOrderId(orderAreaParam.getOrderId());
            OrderArea queryOrderArea = orderAreaMapper.selectOne(orderArea);
            if (queryOrderArea == null) {
                returnMap.put("code", BaseConstants.FAIL);
                returnMap.put("msg", "订单制作区域不存在!");
                return returnMap;
            }

            if (StringUtils.equals("SUCCESS", orderAreaParam.getOrderStatus())) {
                if (StringUtils.isBlank(queryOrderArea.getOrderWorkShopStatus())
                        || StringUtils.equals(queryOrderArea.getOrderWorkShopStatus(), "FAIL")) {
                    returnMap.put("code", BaseConstants.FAIL);
                    returnMap.put("msg", "请检查车间二次入库状态!");
                    return returnMap;
                }
            }
            order.setOrderStatus(orderAreaParam.getOrderStatus());
            order.setUpdateTime(new Date());
            orderService.updateStatusByOrderId(order);
        } catch (Exception e) {
            returnMap.put("code", BaseConstants.FAIL);
            returnMap.put("msg", BaseConstants.FAILMSG);
            log.error("updateStatusByOrderId error:{}", e);
        }
        return returnMap;
    }

    /**
     * 订单查询
     *
     * @param orderParam
     */
    @ApiOperation("订单查询")
    @RequestMapping(value = "/queryOrderList", method = RequestMethod.POST)
    public Map<String, Object> queryOrderList(@RequestBody OrderParam orderParam) {
        orderParam.setBeginLine((orderParam.getCurrentPage() - 1) * orderParam.getPageSize());
        log.info("queryOrderList param:{}", gson.toJson(orderParam));
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("code", BaseConstants.SUCCESS);
        returnMap.put("msg", BaseConstants.SUCCESSMSG);
        try {
            List<Map<String, Object>> list = orderService.queryOrderList(orderParam);
            //计算已下单天数 和距离交货天数
            for (Map<String, Object> map : list) {
                String orderDate = (String) map.get("orderDate");//订单日趋
                String finishDate = (String) map.get("finishDate");//交货日期
                Date orderDate1 = DateUtil.getDateByStr(orderDate);
                Date finishDate1 = DateUtil.getDateByStr(finishDate);

                if (StringUtils.isNotBlank(orderDate)) {
                    Long dateDiff = DateUtil.getDateDiff(orderDate1, new Date());
                    map.put("alreaday", dateDiff + "");
                }
                if (StringUtils.isNotBlank(finishDate)) {
                    Long dateDiff = DateUtil.getDateDiff(new Date(), finishDate1);
                    map.put("distanceDay", dateDiff + "");
                }
            }
            int count = orderService.queryOrderListCount(orderParam);
            returnMap.put("list", list);
            returnMap.put("count", count);
        } catch (Exception e) {
            returnMap.put("code", BaseConstants.FAIL);
            returnMap.put("msg", BaseConstants.FAILMSG);
            log.error("queryOrderList error:{}", e);
        }
        return returnMap;
    }

    /**
     * 订单增加
     *
     * @param saveOrder
     */
    @ApiOperation("订单增加")
    @RequestMapping(value = "/saveOrder", method = RequestMethod.POST)
    public Map<String, Object> saveOrder(@RequestBody SaveOrder saveOrder) {
        log.info("saveOrder param:{}", gson.toJson(saveOrder));
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("code", BaseConstants.SUCCESS);
        returnMap.put("msg", BaseConstants.SUCCESSMSG);
        try {
            Order order = orderService.queryOrderByOrderId(saveOrder.getOrderId());
            if (order != null) {
                returnMap.put("code", BaseConstants.FAIL);
                returnMap.put("msg", "订单已存在");
                return returnMap;
            }
            orderService.saveOrder(saveOrder);
        } catch (Exception e) {
            returnMap.put("code", BaseConstants.FAIL);
            returnMap.put("msg", BaseConstants.FAILMSG);
            log.error("saveOrder error:{}", e);
        }
        return returnMap;
    }

    @ApiOperation(value = "订单导出", notes = "前端页面点击下载操作时调用接口")
    @RequestMapping(value = "/exportOrder", method = {RequestMethod.POST, RequestMethod.GET})
    public void exportOrder(HttpServletRequest request, HttpServletResponse response) {
        OrderParam orderParam = new OrderParam();
        orderParam.setOrderStatus("SUCCESS");
        orderParam.setPageSize(exportPageSize);
        log.info("exportOrder param:{}", gson.toJson(orderParam));
        try {
            List<Map<String, Object>> list = null;
            list = orderService.queryOrderList(orderParam);

            String title = "订单信息";
            String[] header = new String[]{"订单编号", "客户名称", "订单地址", "下单日期", "交货日期"};
            String[] columns = new String[]{"orderId", "customerName", "orderAddress", "orderDate", "finishDate"};
            SXSSFWorkbook sxssfWorkbook = ExportExcelUtil.exportExce(title, header, columns, list);
            String fileName = "订单信息" + DateFormatUtils.format(new Date(), "yyyyMMddHHmmss") + ".xlsx";
            RenderUtil.PoiRender(request, response, fileName, "utf-8", sxssfWorkbook);
        } catch (Exception e) {
            log.error("exportOrder error:{}", e);
        }
    }


    /**
     * 订单状态的修改
     *
     * @param orderAreaParam
     * @return
     */
    @ApiOperation("通过流水号修改订单制作区域")
    @RequestMapping(value = "/updateOrderAreaByOrderId", method = RequestMethod.POST)
    public Map<String, Object> updateOrderAreaByOrderId(@RequestBody OrderAreaParam orderAreaParam) {
        log.info("updateOrderAreaByOrderId param:{}", gson.toJson(orderAreaParam));
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("code", BaseConstants.SUCCESS);
        returnMap.put("msg", BaseConstants.SUCCESSMSG);
        try {
            String orderId = orderAreaParam.getOrderId();
            String orderStatus = orderAreaParam.getOrderStatus();
            String flag = orderAreaParam.getFlag();

            if (StringUtils.isBlank(orderAreaParam.getOrderId()) ||
                    StringUtils.isBlank(orderAreaParam.getOrderStatus()) ||
                    StringUtils.isBlank(orderAreaParam.getFlag())) {
                returnMap.put("code", BaseConstants.FAIL);
                returnMap.put("msg", "参数错误");
                return returnMap;
            }
            Order order = orderService.queryOrderByOrderId(orderAreaParam.getOrderId());
            if (order == null) {
                returnMap.put("code", BaseConstants.FAIL);
                returnMap.put("msg", "订单记录不存在!");
                return returnMap;
            }
            OrderArea orderArea = new OrderArea();
            orderArea.setOrderId(orderId);
            OrderArea queryOrderArea = orderAreaMapper.selectOne(orderArea);
            if (queryOrderArea == null) {
                returnMap.put("code", BaseConstants.FAIL);
                returnMap.put("msg", "订单制作区域不存在!");
                return returnMap;
            }
            if (StringUtils.equals("10", flag)) {
                if (StringUtils.equals("FAIL", orderStatus)) {
                    orderService.updateOrderAreaByOrderId(orderId, orderStatus, flag);
                } else {
                    String str = queryOrderArea.getOrderAreaCabinet() + queryOrderArea.getOrderAreaShelf()
                            + queryOrderArea.getOrderAreaBackdoor() + queryOrderArea.getOrderAreaMobile()
                            + queryOrderArea.getOrderAreaAluminum() + queryOrderArea.getOrderAreaHigh()
                            + queryOrderArea.getOrderAreaDoorPanel() + queryOrderArea.getOrderAreaGetOld()
                            + queryOrderArea.getOrderAreaFitting();
                    log.info("订单号进行车间二次确认:{},订单制作区域完成状态:{}", order, str);
                    if (StringUtils.isNotBlank(str) && !str.contains("FAIL")) {
                        orderService.updateOrderAreaByOrderId(orderId, orderStatus, flag);
                    } else {
                        returnMap.put("code", BaseConstants.FAIL);
                        returnMap.put("msg", "订单制作区域存在未完成");
                        return returnMap;
                    }
                }
            } else {
                if (StringUtils.equals("FAIL", orderStatus)) {
                    orderService.updateOrderAreaByOrderId(orderId, "FAIL", "10");
                }
                orderService.updateOrderAreaByOrderId(orderId, orderStatus, flag);
            }
        } catch (Exception e) {
            returnMap.put("code", BaseConstants.FAIL);
            returnMap.put("msg", BaseConstants.FAILMSG);
            log.error("updateOrderAreaByOrderId error:{}", e);
        }
        return returnMap;
    }
}
