package com.copenedu.web.impl.map.deprecate;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;
import com.copenedu.services.impl.map.DelFenceImpl;
import com.copenedu.services.impl.map.GaudeImpl;

import cache.map.FenceBuffer;
import gaude.GaudeServices;
import httpclient.MyHttp;
import httpclient.MyHttpFactory;
import my.util.json.JsonUtil;
import rest.RestError;
import rest.RestOk;
import rest.RestResp;
import rest.RestfulServlet;

@Deprecated
//@WebServlet("/map/delete_circle_fence")
public class DelFenceServlet extends RestfulServlet
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
		
		MyHttp client = MyHttpFactory.getMyHttpClient(httpReq);
		Map<String, String> map = JsonUtil.jsonToStrMap(jreq);
		map.put("uid", uid);
		
//		jreq.put("key", GaudeServices.KEY);
		
		DelFenceImpl service = new DelFenceImpl(map);
		GaudeImpl gaoService = new GaudeImpl(map);
		String url = "https://tsapi.amap.com/v1/track/geofence/delete";
		try
		{
//			Map<String, String> data = gaoService.querySid();		//弃用 改用querygaosid
			Map<String, String> data = gaoService.queryGaoSid();
			map.put("sid", data.get("usid"));
			map.put("key", GaudeServices.KEY);
			
			JSONObject jsonResp = client.postByRespJson(url, map);			//转发
			int errcode = jsonResp.getIntValue("errcode");
			if(errcode != 10000)
			{
				RestResp dataResp = (() -> {return jsonResp.toJSONString();});			//lambda表达�?
				return dataResp;
			}
			
			boolean deled = service.delFence();
			if(deled)
			{
//				FenceBuffer.delete(uid);
				return new RestOk();
			}
			else
			{
				return new RestError(-10, "删除失败！！");
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
		
	}
	
}
