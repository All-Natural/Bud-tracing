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
import com.copenedu.services.impl.prompt.DeletePromptImpl;

import my.util.json.JsonUtil;
import rest.RestError;
import rest.RestOk;
import rest.RestResp;
import rest.RestfulServlet;

@WebServlet("/prompt/delete")
public class DeletePromptServlet extends RestfulServlet
{

	@Override
	protected RestResp execute(HttpServletRequest httpReq, HttpServletResponse httpResp, JSONObject jreq,
			HashMap<String, String> queryParams) throws Exception {
		
		Integer proid = jreq.getInteger("proid");
		if(proid == null)
		{
			return new RestError(-10, "缺少参数");
		}
		Map<String,String> map = JsonUtil.jsonToStrMap(jreq);
		DeletePromptImpl impl = new DeletePromptImpl(map);
		try
		{
//			List<Map<String, String>> dataList = (type != null && grade != null) ? service.queryByTypeAndGrade(Integer.valueOf(type), Integer.valueOf(grade)) : service.querySysPrompt();
//			parse(dataList);		//解析类型
			
			boolean isDel = impl.delete();
			if(isDel) 
			{
				return new RestOk();
			}
			else
			{
				return new RestError(-40, "删除失败！！");
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
