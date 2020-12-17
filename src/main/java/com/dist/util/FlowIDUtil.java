package com.dist.util;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * 生成流水号工具类（前缀+时间戳（yyyyMMddHHmmssSSS）+5位顺序排列+5位随机数）
 * @author HKRT-PC
 *
 */
public class FlowIDUtil {
	
	private static AtomicInteger orderNum = new AtomicInteger(10000);
	
	public static String foundFlowId(String flowType){
		StringBuffer flowId = new StringBuffer();
		int order = orderNum.addAndGet(1); //5位数据
		if(order > 90000){
			orderNum.set(10000);
		}
		Random rand = new Random();
		int randomOrder = rand.nextInt(9999)+10000;
		flowId.append(flowType).append(DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS"))
		.append(order).append(randomOrder);
		return flowId.toString();
	}
	public static String foundWorkOrderTaskId(){
		StringBuffer flowId = new StringBuffer();
		int order = orderNum.addAndGet(1); //5位数据
		if(order > 90000){
			orderNum.set(10000);
		}
		Random rand = new Random();
		int randomOrder = rand.nextInt(999);
		flowId.append(DateFormatUtils.format(new Date(), "yyyyMMddHHmm"))
				.append(order).append(randomOrder);
		return flowId.toString();
	}
	
}
