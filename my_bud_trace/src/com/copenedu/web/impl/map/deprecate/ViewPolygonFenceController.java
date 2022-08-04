/**
 * 
 */
package com.copenedu.web.impl.map.deprecate;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.copenedu.services.impl.map.PolygonFenceImpl;

import rest.RestError;
import rest.RestOk;
import rest.RestResp;
import rest.RestfulServlet;

/**
 * @author xianzhikun
 *
 */
@WebServlet("/map/query_polygon_fence")
@Deprecated
public class ViewPolygonFenceController extends RestfulServlet
{
		@Override
		protected RestResp execute(HttpServletRequest httpReq, HttpServletResponse httpResp, JSONObject jreq,
				HashMap<String, String> queryParams) throws Exception
		{
//				过滤用户
//				HttpSession session = httpReq.getSession();
//				Map<String, String> userData = (Map<String, String>) session.getAttribute("userinfo");
//				if(userData == null ) return new RestError(-10, "用户未登录");
//				String uid = userData.get("uid");
//				if(uid == null)
//				{
//						return new RestError(-10, "用户未登录！！");
//				}
				
				try
				{
						Map<String, String> data = new HashMap<String, String>();
						String uid = queryParams.get("puid");
						data.put("uid", uid);		//模拟登陆
						
						data.put("cuid", queryParams.get("cuid"));
						PolygonFenceImpl service = new PolygonFenceImpl(data);
						List<Map<String, String>> dataList = service.query();
						
						JSONObject jsonResp = new JSONObject();
						JSONArray jsonArr = parse(dataList);
						jsonResp.put("count", jsonArr.size());
						jsonResp.put("results", jsonArr);
						
						return new RestOk(jsonResp);			//返回应答
				}
				catch(SQLException e)
				{
						return new RestError(-50, "数据查询错误");
				}
				catch(NumberFormatException e)
				{
						return new RestError(-40, "数据解析错误");
				}
				catch(Exception e)
				{
						return new RestError(-30, "参数数据错误");
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
		                "name": "学校",
		                "type": 1,						---add
		                "points":"[[106.429524,29.536799],[106.494912,29.502655],[106.480773,29.539752],[106.478511,29.494815],[106.454962,29.528155],[106.459331,29.507425],[106.504212,29.532405]]"
		            }
		        ]
		    },
		    "errcode": 200,
		    "errdetail": null,
		    "errmsg": "OK"
			}
		 * @param fences
		 * @return
		 */
		private JSONArray parse(List<Map<String, String>> dataList)
		{
				JSONArray jsonArr = new JSONArray();
				for(Map<String, String> data : dataList)
				{
						JSONObject jobj = new JSONObject();
						jobj.put("timecreate", data.get("potimecreate"));
						jobj.put("type", Integer.valueOf(data.get("potype")));
						jobj.put("gfid", Integer.valueOf(data.get("poid")));
						jobj.put("name", data.get("poname"));
						jobj.put("desc", data.get("podesc"));
						jobj.put("points", data.get("popoints"));
						jsonArr.add(jobj);
				}
				return jsonArr;
		}
}
