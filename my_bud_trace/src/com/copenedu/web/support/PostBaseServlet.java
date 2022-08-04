package com.copenedu.web.support;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


public abstract class PostBaseServlet extends HttpServlet
{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		JSONObject obj = parseJSON(req);
		Map<String, String> params = parseRequest(req);
		
		JSONObject re = execute(req, resp, obj, params);
		String str = re.toJSONString();
		
		resp.getWriter().append(str);
	}

	protected final JSONObject parseJSON(HttpServletRequest request)
	{
		StringBuffer data = new StringBuffer();
		String line = null;
		BufferedReader reader = null;
		try {
			reader = request.getReader();
			while (null != (line = reader.readLine()))
				data.append(line);
		} catch (IOException e) {
		} finally {
		}
		String jsonStr = data.toString();
		JSONObject jobj = JSON.parseObject(jsonStr);
		
		return jobj;
	}
	
	protected final Map<String,String> parseRequest(HttpServletRequest request)
	{
		Map<String, String[]> tem=request.getParameterMap();
		int size=tem.size();
		int initSize=((int)(size/0.75))+1;
		Map<String,String> dto=new HashMap<>(initSize);
		tem.forEach((k,v)->{ 
			dto.put(k, v.length==1?v[0]:joinArray(v)); 
		});

		dto.put("classpath", request.getContextPath());
		
		return dto;
	}
	
	protected abstract JSONObject execute(HttpServletRequest req, HttpServletResponse resp, JSONObject jobj, Map<String,String> params);
	
	private String joinArray(String...array)
	{
		StringBuilder content=new StringBuilder(array[0]);
		int len=array.length;
		for(int i=1;i<len;i++)
		{
			content.append(",").append(array[i]);
		}
		return content.toString();
	}
	
}
