package com.copenedu.web.impl.map.deprecate;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.copenedu.services.impl.map.GaudeImpl;

import gaude.GaudeServices;
import httpclient.MyHttp;
import httpclient.MyHttpFactory;
import rest.RestError;
import rest.RestResp;
import rest.RestfulServlet;

/**
 * 弃用，转用current_position
 * @author Not a Literary Gaint
 *
 */
@Deprecated
//@WebServlet("/app/map/current_position")
public class PositionServlet extends RestfulServlet
{

	@Override
	protected RestResp execute(HttpServletRequest httpReq, HttpServletResponse httpResp, JSONObject jreq,
			HashMap<String, String> queryParams) throws Exception {
		String puid = queryParams.get("puid");
		String cuid = queryParams.get("cuid");
		MyHttp client = MyHttpFactory.getMyHttpClient(httpReq);
		GaudeImpl sidGetter = new GaudeImpl(forPuid(puid));
		GaudeImpl keywordsGetter = new GaudeImpl(forCuid(cuid));
		
		String url = "https://tsapi.amap.com/v1/track/terminal/search";
		
		try
		{
			Map<String, String> sid = sidGetter.querySid();
			Map<String, String> keywords = keywordsGetter.queryKeyword();
			
			Map<String, String> params = new HashMap();
			params.put("key", GaudeServices.KEY);
			params.put("sid", sid.get("gaosid"));
			params.put("keywords", keywords.get("gaokeyword"));
			
			JSONObject jsonResp = client.postByRespJson(url, params);
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
	
	public Map<String, String> forPuid(String puid)
	{
		Map<String, String> map = new HashMap();
		map.put("uid", puid);
		return  map;
	}
	
	public Map<String, String> forCuid(String cuid)
	{
		Map<String, String> map = new HashMap();
		map.put("uid", cuid);
		return map;
	}
}
