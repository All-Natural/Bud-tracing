package com.copenedu.web.impl.prompt;

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
import com.copenedu.services.impl.prompt.QueryPromptImpl;

import my.util.json.JsonUtil;
import rest.RestError;
import rest.RestOk;
import rest.RestResp;
import rest.RestfulServlet;

@WebServlet("/prompt/list")
public class PromptListServlet extends RestfulServlet
{

	@Override
	protected RestResp execute(HttpServletRequest httpReq, HttpServletResponse httpResp, JSONObject jreq,
			HashMap<String, String> queryParams) throws Exception {
		
		HttpSession session = httpReq.getSession();
		Map<String, String> userData = (Map<String, String>) session.getAttribute("userinfo");
		String uid = userData.get("uid");
		if(uid == null)
		{
			return new RestError();
		}
		
		
		QueryPromptImpl service = new QueryPromptImpl(null);
		String type = queryParams.get("type");
		String grade = queryParams.get("grade");
		
		try
		{
			List<Map<String, String>> dataList = (type != null && grade != null) ? service.queryByTypeAndGrade(Integer.valueOf(type), Integer.valueOf(grade), uid) : service.querySysPrompt(uid);
			PromptParser.parse(dataList);		//解析类型
			
			JSONArray arr = JsonUtil.listToJsarr(dataList);
			
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
