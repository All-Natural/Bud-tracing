/**
 * 
 */
package com.copenedu.web.impl.map;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.copenedu.services.impl.map.PolygonFenceImpl;

import rest.RestError;
import rest.RestOk;
import rest.RestResp;
import rest.RestfulServlet;

/**
 * @author xianzhikun
 *	添加多边形围栏
 */
@WebServlet("/map/add_polygon_fence")
public class AddPolygonFenceController extends RestfulServlet
{
		@Override
		protected RestResp execute(HttpServletRequest httpReq, HttpServletResponse httpResp, JSONObject jreq,
				HashMap<String, String> queryParams) throws Exception
		{
				//过滤用户
//				HttpSession session = httpReq.getSession();
//				Map<String, String> userData = (Map<String, String>) session.getAttribute("userinfo");
//				if(userData == null ) return new RestError(-10, "用户未登录");
//				String uid = userData.get("uid");
//				if(uid == null)
//				{
//						return new RestError(-10, "用户未登录！！");
//				}
//				
				try
				{
						Map<String, String> data = parseData(jreq);
						String uid = jreq.getString("puid");
						data.put("uid", uid);
						PolygonFenceImpl service = new PolygonFenceImpl(data);
						boolean stored = service.addFence();
						if(stored) 	return  new RestOk();
						else 	return new RestError(-40, "插入数据错误");
				}
				catch(NumberFormatException e)
				{
						return new RestError(-40, "数据插入错误");
				}
				catch(Exception e)
				{
						return new RestError(-30, "数据错误");
				}
		}
		
		/**
		 * 
		 * 
		 
		 * @param jobj
		 * @return
		 */
		private Map<String, String> parseData(JSONObject jobj)
		{
				Map<String, String> data = new HashMap<String, String>(){
				{
						put("name", jobj.getString("name"));
						put("desc", jobj.getString("desc"));
						put("type", String.valueOf(jobj.getInteger("type")));
						put("cuid", jobj.getString("cuid"));
//						put("points", jobj.getString("points"));
				}};
				
				JSONArray dataArr  = jobj.getJSONArray("points");
				
				data.put("points", dataArr.toString());
				return data;
		}
		
}
