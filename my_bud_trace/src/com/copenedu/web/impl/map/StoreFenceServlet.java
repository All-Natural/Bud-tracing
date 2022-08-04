package com.copenedu.web.impl.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;
import com.copenedu.services.impl.map.FenceImpl;

import my.util.json.JsonUtil;
import rest.RestError;
import rest.RestOk;
import rest.RestResp;
import rest.RestfulServlet;

//@WebServlet("/map/store_circle_fence")
@WebServlet("/map/add_circle_fence")
public class StoreFenceServlet extends RestfulServlet
{
		@Override
		protected RestResp execute(HttpServletRequest httpReq, HttpServletResponse httpResp, JSONObject jreq,
				HashMap<String, String> queryParams) throws Exception {
			
				//过滤用户
				HttpSession session = httpReq.getSession();
				Map<String, String> userData = (Map<String, String>) session.getAttribute("userinfo");
				if(userData == null ) return new RestError(-10, "用户未登录");
				String uid = userData.get("uid");
				if(uid == null)
				{
					return new RestError(-10, "用户未登录！！");
				}
				
				Map<String, String> data = JsonUtil.jsonToStrMap(jreq);
				data.put("uid", uid);
				
		//		Map<String, String> data = (Map<String, String>) httpReq.getAttribute("data");
				FenceImpl service = new FenceImpl(data);
				boolean stored = false;
				try
				{
						stored = service.storeFence();
				}
				catch(Exception e)
				{
						return new RestError(-30, "插入数据错误！！");
				}
				
				if(stored)
				{
						return  new RestOk();
				}
				else
				{
						return new RestError(-40, "插入数据错误");
				}
		}
	
}
