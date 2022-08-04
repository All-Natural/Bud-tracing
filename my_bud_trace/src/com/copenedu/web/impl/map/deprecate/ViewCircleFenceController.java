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
import com.copenedu.services.impl.map.FenceImpl;

import rest.RestError;
import rest.RestOk;
import rest.RestResp;
import rest.RestfulServlet;

/**
 * 查看围栏，不需要查看高德数据库
 * @author 
 *
 */
@WebServlet("/map/query_circle_fence")
@Deprecated
public class ViewCircleFenceController extends RestfulServlet
{
		/**
		 * 需求参数：
		 */
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
						return new RestError(-10, "缺少参数uid");
				}
				
				try {
						queryParams.put("uid", uid);
						FenceImpl service = new FenceImpl(queryParams);
						List<Map<String, String>> fences = service.queryFence();
						
						JSONArray jsonArr = parseFenceType(fences);
						JSONObject jsonResp = new JSONObject();
						jsonResp.put("count", jsonArr.size());
						jsonResp.put("results", jsonArr);
						
						return new RestOk(jsonResp);
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
	
		
		/**
		 * 解析格式：
		 * 
		 * 
		 * 
		传入格式：
		fdid, fdname, fddesc, fdtimecreate, fdtype, flng, flat, fradius
		
		目标格式
		  	{
		    "data": {
		        "count": 1,
		        "results": [
		            {
		                "createtime": 1627446813979,
		                "desc": "学校",
		                "gfid": 127229,
		                "modifytime": 1627446813979,
		                "name": "学校",
		                "type": 1,						---add
		                "shape": {
		                    "center": "106.504962,29.533155",       ---  modify
		                    "radius": 1000
		                }
		            }
		        ]
		    },
		    "errcode": 10000,
		    "errdetail": null,
		    "errmsg": "OK"
			}
		 * @param fences
		 * @return
		 */
		private JSONArray parseFenceType(List<Map<String, String>> fences)
		{
				JSONArray jsonArr = new JSONArray();
				for(Map<String, String> fence : fences)
				{
						JSONObject jobj = new JSONObject();
						jobj.put("desc", fence.get("fddesc"));
						jobj.put("gfid", Integer.valueOf(fence.get("fdid")));
						jobj.put("name", fence.get("fdname"));
						jobj.put("type", Integer.valueOf(fence.get("fdtype")));
						
						JSONObject shape = new JSONObject();
						shape.put("center", fence.get("flng") + "," + fence.get("flat") );
						shape.put("radius", Integer.valueOf(fence.get("fradius")));
						
						jobj.put("shape", shape);
						jsonArr.add(jobj);
				}
				
				return jsonArr;
		}
}
