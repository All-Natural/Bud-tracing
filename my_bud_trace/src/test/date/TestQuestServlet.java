package test.date;

import java.util.HashMap;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import rest.RestOk;
import rest.RestResp;
import rest.RestfulServlet;

@WebServlet("/test/url")
public class TestQuestServlet extends RestfulServlet
{

	@Override
	protected RestResp execute(HttpServletRequest httpReq, HttpServletResponse httpResp, JSONObject jreq,
			HashMap<String, String> queryParams) throws Exception {
		
		String param = queryParams.get("data");
		String param2 = (String) httpReq.getParameter("data");
		JSONObject jobj = new JSONObject();
		jobj.put("dataFromDto", param);
		jobj.put("dataFromRequest", param2);
		
		return new RestOk(jobj);
	}
	
}
