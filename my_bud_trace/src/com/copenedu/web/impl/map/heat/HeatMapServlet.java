package com.copenedu.web.impl.map.heat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.copenedu.services.impl.map.heat.HeatDataImpl;

import rest.RestOk;
import rest.RestResp;
import rest.RestfulServlet;

@WebServlet("/map/heat_data")
public class HeatMapServlet extends RestfulServlet
{

	@Override
	protected RestResp execute(HttpServletRequest httpReq, HttpServletResponse httpResp, JSONObject jreq,
			HashMap<String, String> queryParams) throws Exception {
		
		HeatDataImpl impl = new HeatDataImpl(queryParams);
		
		List<Map<String, String>> dataList = impl.heatData();
		
		JSONArray arr = parse(dataList);
		
		return new RestOk(arr);
	}
	
	private JSONArray parse(List<Map<String, String>> dataList)
	{
		JSONArray arr = new JSONArray();
		for(Map<String, String> data : dataList)
		{
			JSONObject jobj = new JSONObject();
			jobj.put("lng", Double.valueOf(data.get("lng")));
			jobj.put("lat", Double.valueOf(data.get("lat")));
			jobj.put("count", 1);
			arr.add(jobj);
		}
		return arr;
	}
}
