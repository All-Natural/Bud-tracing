package com.copenedu.web.impl.prompt;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.copenedu.services.impl.prompt.QueryPromptImpl;

import my.util.json.JsonUtil;
import rest.RestError;
import rest.RestOk;
import rest.RestResp;
import rest.RestfulServlet;

@WebServlet("/prompt/detail")
public class QueryPromptServlet extends RestfulServlet
{

	@Override
	protected RestResp execute(HttpServletRequest httpReq, HttpServletResponse httpResp, JSONObject jreq,
			HashMap<String, String> queryParams) throws Exception {
		
		String proid = queryParams.get("proid");
		if(proid == null)
		{
			return new RestError(-10,"缺少参数");
		}
		QueryPromptImpl service = new QueryPromptImpl(null);
		
		try
		{
			Map<String, String> data = service.queryByProid(proid);
			PromptParser.parse(data);
			
			JSONObject jobj = JsonUtil.mapToJson2(data);
			
			return new RestOk(jobj);
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
