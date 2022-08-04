package com.copenedu.web.impl.map;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;
import com.copenedu.services.impl.map.CurrentPositionImpl;

import cache.map.PointBuffer;
import rest.RestError;
import rest.RestOk;
import rest.RestResp;
import rest.RestfulServlet;

@WebServlet("/map/current_position")
public class CurrentPositionServlet extends RestfulServlet
{

	@Override
	protected RestResp execute(HttpServletRequest httpReq, HttpServletResponse httpResp, JSONObject jreq,
			HashMap<String, String> queryParams) throws Exception {
		
			HttpSession session = httpReq.getSession();
			Map<String, String> userData = (Map<String, String>) session.getAttribute("userinfo");
			if(userData == null ) return new RestError(-10, "用户未登录");
			String cuid = queryParams.get("uid");
			String uid = userData.get("uid");
			if(cuid == null || uid == null)
			{
					return new RestError(-10, "用户未登录");
			}
			
			JSONObject positionInBuffer = PointBuffer.getPointFromBuffer(Integer.valueOf(cuid));
			if(positionInBuffer != null)			//缓存中有数据则直接获取
			{
					return new RestOk(positionInBuffer);
			}
			
			CurrentPositionImpl service = new CurrentPositionImpl(queryParams);
			try
			{
					Map<String, String> position = service.getPosition();
					JSONObject dataResp = parse(position);
					PointBuffer.update(Integer.valueOf(uid), dataResp);
					return new RestOk(dataResp);       //返回位置数据
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
	
	//<Context docBase="C:\java_ee\java_ee_workspace\bud_trace\WebRoot" path="bud"/>
	
		public JSONObject parse(Map<String, String> position)
		{
				JSONObject jobj = new JSONObject();
				
				jobj.put("longitude", Double.valueOf(position.get("posilng")));
				jobj.put("latitude", Double.valueOf(position.get("posilat")));
				jobj.put("time", position.get("positime"));
				jobj.put("speed", Double.valueOf(position.get("posispeed")));
				
				return jobj;
		}
	
}
