package my.services.clue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.copenedu.services.impl.Clue.ClueListImpl;

import my.util.json.JsonUtil;
import rest.RestError;
import rest.RestResp;
import rest.RestfulServlet;

@WebServlet("/clue/list")
public class QueryClueListServlet extends RestfulServlet
{

	@Override
	protected RestResp execute(HttpServletRequest httpReq, HttpServletResponse httpResp, JSONObject jreq,
			HashMap<String, String> queryParams) throws Exception {
		
		List<Map<String, String>> dataList = null;
		ClueListImpl impl = new ClueListImpl(null);
		try
		{
			dataList = impl.getList();
			
			JSONArray dataArr = JsonUtil.listToJsarr(dataList);
			JSONObject jobj = new JSONObject();
			jobj.put("code", 200);
			jobj.put("msg", "Ok");
			jobj.put("count", dataArr.size());
			jobj.put("data", dataArr);
			
			RestResp dataResp = (() -> {return jobj.toJSONString();});			//lambda表达式
			return dataResp;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new RestError();
		}
		
	}
	
	public JSONArray parseData(List<Map<String, String>> dataList)
	{
		JSONArray arr = new JSONArray();
		for(Map<String, String> map : dataList)
		{
			JSONObject jobj = new JSONObject();
			for(Entry<String, String> entry : map.entrySet())
			{
				jobj.put(entry.getKey(), entry.getValue());
			}
			arr.add(map);
		}
		return arr;
	}
	
}
