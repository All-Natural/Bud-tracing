package com.copenedu.web.impl.PostList.TimeLine;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.copenedu.services.impl.PostList.TimeLineServicesImpl;

import my.util.json.JsonUtil;
import rest.RestError;
import rest.RestOk;
import rest.RestResp;
import rest.RestfulServlet;

@WebServlet("/timeline/list")
public class TimeLineListServlet extends RestfulServlet
{

	@Override
	protected RestResp execute(HttpServletRequest httpReq, HttpServletResponse httpResp, JSONObject jreq,
			HashMap<String, String> queryParams) throws Exception {
		
//		String postid = queryParams.get("lipostid");
		TimeLineServicesImpl service = new TimeLineServicesImpl(queryParams);
		try
		{
			List<Map<String, String>> timelines = service.findById();
			JSONArray arr = JsonUtil.listToJsarr(timelines);
			
			return new RestOk(arr);
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
