/**
 * 
 */
package com.copenedu.web.impl.map;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;
import com.copenedu.services.impl.map.FenceImpl;
import com.copenedu.services.impl.map.PolygonFenceImpl;

import my.util.json.JsonUtil;
import rest.RestError;
import rest.RestOk;
import rest.RestResp;
import rest.RestfulServlet;

/**
 * @author xianzhikun
 *
 */
@WebServlet("/map/delete_polygon_fence")
public class DeletePolygonFenceController extends RestfulServlet
{
		@Override
		protected RestResp execute(HttpServletRequest httpReq, HttpServletResponse httpResp, JSONObject jreq,
				HashMap<String, String> queryParams) throws Exception
		{
				
				Map<String, String> params = new HashMap<>();
//				//过滤用户
//				HttpSession session = httpReq.getSession();
//				Map<String, String> userData = (Map<String, String>) session.getAttribute("userinfo");
//				if(userData == null ) return new RestError(-10, "用户未登录");
//				String uid = userData.get("uid");
//				if(uid == null)
//				{
//						return new RestError(-10, "用户未登录");
//				}
				
				try 
				{
						Map<String, String> map = JsonUtil.jsonToStrMap(jreq);
//						map.put("uid", jreq.getString("puid"));
//						map.put("uid", uid);
						
						PolygonFenceImpl service = new PolygonFenceImpl(map);
						if(service.delete())
						{
								return new RestOk();
						}
						else
						{
								return new RestError(-50, "数据不存在,或者无法删除");
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
