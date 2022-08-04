package com.copenedu.web.impl.map.deprecate;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;
import com.copenedu.services.impl.map.AddFenceImpl;

import gaude.GaudeServices;
import httpclient.MyHttp;
import httpclient.MyHttpFactory;
import rest.RestError;
import rest.RestResp;
import rest.RestfulServlet;

//@WebServlet("/map/add_circle_fence")
@Deprecated
public class AddFenceServlet extends RestfulServlet
{
	@Override
	protected RestResp execute(HttpServletRequest httpReq, HttpServletResponse httpResp, JSONObject jreq,
			HashMap<String, String> queryParams) throws Exception {
		
		HttpSession session = httpReq.getSession();
		Map<String, String> userData = (Map<String, String>) session.getAttribute("userinfo");
		String uid = userData.get("uid");
		if(uid == null)
		{
			return new RestError(-10, "缺少参数uid");
		}
		
		Map<String, String> data = parseData(jreq);
		data.put("uid", uid);
		AddFenceImpl service = new AddFenceImpl(data);
		MyHttp client = MyHttpFactory.getMyHttpClient(httpReq);
		String url = "https://tsapi.amap.com/v1/track/geofence/add/circle";
		
		try
		{
//			Map<String, String> gaudeImfor = service.getGaudeImfor();			//�?
			Map<String, String> gaudeImfor = service.getSidImfor();	
			String sid = gaudeImfor.get("usid");
			
			data.put("key", GaudeServices.KEY);
			data.put("sid", sid);
			
			JSONObject respJson = client.postByRespJson(url, data);
			
			httpResp.setHeader("Access-Control-Allow-Origin", "*");
			
			Integer errcode = respJson.getInteger("errcode");
			//返回错误的情�?
			if(errcode == null || errcode != 10000)
			{
				RestResp dataResp = (() -> {return respJson.toJSONString();});			//lambda表达�?
				return dataResp;
			}
			else			//创建成功则在数据库中保存�?�?
			{
				JSONObject result = respJson.getJSONObject("data");
				String gfid = String.valueOf(result.getInteger("gfid"));
				data.put("gfid", gfid);
				httpReq.setAttribute("data", data);
				httpReq.getRequestDispatcher("/map/store_circle_fence").forward(httpReq, httpResp);
			}
		}
		catch(SQLException e)
		{
			return new RestError(-30, "数据查询失败！！");
		}
		catch(Exception e)
		{
			return new RestError(-40, "请求服务失败！！");
		}
		return null;
	}
	
	public Map<String, String> parseData(JSONObject jreq)
	{
		Map<String, String> map = new HashMap<>();
		for(Entry<String, Object> entry : jreq.entrySet())
		{
			map.put(entry.getKey(), String.valueOf(entry.getValue()));
		}
		return map;
	}
	
}
