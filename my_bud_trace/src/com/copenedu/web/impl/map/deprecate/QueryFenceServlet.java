package com.copenedu.web.impl.map.deprecate;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.copenedu.services.impl.map.GaudeImpl;
import com.copenedu.services.impl.map.QueryFenceImpl;

import cache.map.FenceBuffer;
import gaude.GaudeServices;
import httpclient.MyHttp;
import httpclient.MyHttpFactory;
import my.util.str.StrUtil;
import rest.RestError;
import rest.RestOk;
import rest.RestResp;
import rest.RestfulServlet;

@Deprecated
//@WebServlet("/map/query_circle_fence")
public class QueryFenceServlet extends RestfulServlet
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
		
//		String uid = queryParams.get("puid");
		
//		JSONObject fence = FenceBuffer.getFenceFromBuffer(Integer.valueOf(uid));
//		if(fence != null)
//		{
//			RestResp dataResp = (() -> {return fence.toJSONString();});			//lambda表达�?
//			return dataResp;
//		}
		
		queryParams.put("uid", uid);
		QueryFenceImpl impl = new QueryFenceImpl(queryParams);
		GaudeImpl gaoService = new GaudeImpl(queryParams);
		MyHttp client = MyHttpFactory.getMyHttpClient(httpReq);
		String url = "https://tsapi.amap.com/v1/track/geofence/list";
		
		
		try
		{
			List<Map<String, String>> list = impl.queryFence();
			Map<Integer, Integer> fenceTypes = joinFenceType(list);
			String gfids = joinAtList(list);
			if(gfids == null)
			{
				return buildNofence();		//没有数据
			}
			
//			Map<String, String> map = gaoService.querySid();		//�?
			Map<String, String> map = gaoService.queryGaoSid();
			map.put("sid", map.get("usid"));
			map.put("key", GaudeServices.KEY);
			map.put("outputshape", "1");
			map.put("gfids", gfids);
			JSONObject jsonResp = client.getByRespJson(url, map);
			
			httpResp.setHeader("Access-Control-Allow-Origin", "*");
			Integer errcode = jsonResp.getInteger("errcode");
			
			addType(jsonResp, fenceTypes);
//			FenceBuffer.update(Integer.valueOf(uid), jsonResp);						//添加到缓�?
			
			//返回错误的情况
			RestResp dataResp = (() -> {return jsonResp.toJSONString();});			//lambda表达�?
			return dataResp;
		}
		catch(SQLException e)
		{
			return new RestError(-30, "数据查询失败！！");
		}
		catch(Exception e)
		{
			return new RestError(-40, "请求服务失败！！");
		}
	}
	
	public String joinAtList(List<Map<String, String>> list)
	{
		int size = list.size();
		String[] gfids = new String[size];
		
		for(int i=0; i<size; i++)
		{
			gfids[i] = list.get(i).get("fdgfid");
		}
		
		return StrUtil.joinStr(gfids);
	}
	
	public RestResp buildNofence()
	{
		JSONObject jobj = new JSONObject();
		jobj.put("errcode", 10000);
		jobj.put("errmsg", "ok");
		JSONObject data = new JSONObject();
		data.put("count", 0);
		data.put("results", new JSONArray());
		jobj.put("data", data);
		RestResp dataResp = (() -> {return jobj.toJSONString();});			//lambda表达�?
		return dataResp;
	}
	
	public Map<Integer, Integer> joinFenceType(List<Map<String, String>> list)
	{
		Map<Integer, Integer> fenceForType = new HashMap<>();
		for(Map<String, String> item : list)
		{
			fenceForType.put(Integer.valueOf(item.get("fdgfid")), Integer.valueOf(item.get("fdtype")));
		}
		return fenceForType;
	}
	
	
	/**
	 * 原格式：
		{
	    "data": {
	        "count": 1,
	        "results": [
	            {
	                "createtime": 1627446813979,
	                "desc": "学校�?",
	                "gfid": 127229,
	                "modifytime": 1627446813979,
	                "name": "学校",
	                "shape": {
	                    "center": "106.504962,29.533155",
	                    "radius": 1000
	                }
	            }
	        ]
	    },
	    "errcode": 10000,
	    "errdetail": null,
	    "errmsg": "OK"
		}
	 * 
	  目标格式�?
	  	{
	    "data": {
	        "count": 1,
	        "results": [
	            {
	                "createtime": 1627446813979,
	                "desc": "学校�?",
	                "gfid": 127229,
	                "modifytime": 1627446813979,
	                "name": "学校",
	                "type": 1,						---add
	                "shape": {
	                    "center": "[106.504962,29.533155]",       ---  modify
	                    "radius": 1000
	                }
	            }
	        ]
	    },
	    "errcode": 10000,
	    "errdetail": null,
	    "errmsg": "OK"
		}
	 * 
	 * 
	 * 修改center 格式
	 * @param jobj
	 */
	public void modifyCenter(JSONObject jobj)
	{
		JSONObject data = jobj.getJSONObject("data");
		JSONArray results = data.getJSONArray("results");
		
		int length = results.size();
		for(int i=0; i<length; i++)
		{
			JSONObject obj = results.getJSONObject(i);
			JSONObject shape = obj.getJSONObject("shape");
			String center = shape.getString("center");
			shape.put("center", "[" + center + "]");
		}
		
	}
	
	public void addType(JSONObject jobj, Map<Integer, Integer> fenceTypes)
	{
		JSONObject data = jobj.getJSONObject("data");
		JSONArray results = data.getJSONArray("results");
		
		int length = results.size();
		for(int i=0; i<length; i++)
		{
			JSONObject obj = results.getJSONObject(i);
			obj.put("type", fenceTypes.get(obj.getInteger("gfid")));
		}
		
	}
	
}
