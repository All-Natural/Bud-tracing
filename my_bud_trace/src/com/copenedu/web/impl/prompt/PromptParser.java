package com.copenedu.web.impl.prompt;

import java.util.List;
import java.util.Map;

public class PromptParser 
{
	private static final String NORMAL = "普通";					//注， final ，不允许修改
	private static final String IMPORTANT = "重要";
	private static final String SIMPLE = "普通消息";
	private static final String EXAM = "审核消息";
	private static final String CLUE = "线索消息";
	private static final String WARN = "围栏预警 ";
	private static final String WARN2 = "速度预警";
	
	/**
	 * 字段 prograde 0普通， 1重要
	 * protype 0,普通消息， 1：审核消息， 2：线索消息? 3：预警消息?
	 * 
	 * @param dataList
	 * @return
	 */
	public static void parse(Map<String, String> data)
	{
		String type = data.get("protype");
		if(type.equals("0"))
		{
			data.put("protype", SIMPLE);
		}
		else if(type.equals("1"))
		{
			data.put("protype", EXAM);
		}
		else if(type.equals("2"))
		{
			data.put("protype", CLUE);
		}
		else if(type.equals("3"))
		{
			data.put("protype", WARN);
		}
		else if(type.equals("4"))
		{
			data.put("protype", WARN2);
		}
		
		String grade = data.get("prograde");
		if(grade.equals("0"))
		{
			data.put("prograde", NORMAL);
		}
		else if(grade.equals("1"))
		{
			data.put("prograde", IMPORTANT);
		}
	}
	
	public static void parse(List<Map<String, String>> dataList)
	{
		for(Map<String, String> data : dataList)
		{
			parse(data);
		}
	}
	
}
