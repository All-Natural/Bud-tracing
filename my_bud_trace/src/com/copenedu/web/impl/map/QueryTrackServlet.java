package com.copenedu.web.impl.map;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.copenedu.services.impl.map.GetTraceImpl;

import rest.RestError;
import rest.RestOk;
import rest.RestResp;
import rest.GenalRestResp;
import rest.RestfulServlet;

@WebServlet("/map/get_trace")
public class QueryTrackServlet extends RestfulServlet
{
	@Override
	protected RestResp execute(HttpServletRequest httpReq, HttpServletResponse httpResp, JSONObject jreq,
			HashMap<String, String> queryParams)
	{
		HttpSession session = httpReq.getSession();
		Map<String, String> userData = (Map<String, String>) session.getAttribute("userinfo");
		if(userData == null ) return new RestError(-10, "用户未登录");
		String uid = userData.get("uid");
		if(uid == null)
		{
				return new RestError(-10, "缺少参数uid");
		}
		
		GetTraceImpl service = new GetTraceImpl(queryParams);
//		List<Map<String, String>> dataList = null;
		String data = null;
		JSONArray dataArr = null;
		try
		{
			List<Map<String, String>> dataList = service.getTrace();
//			data = parseData(dataList);
			dataArr = parseDataArray(dataList);
		}
		catch(NumberFormatException e)
		{
			return new RestError(-40, "数据解析错误");
		}
		catch(Exception e)
		{
			return new RestError(-30, "数据查询错误");
		}
		
		httpResp.setHeader("Access-Control-Allow-Origin", "*");				//设置访问域，供给高德使用
		return new RestOk(dataArr);
	}
	
	
	/**
	 * 解析数据 
	 * 数据格式
	 * <
	 * 		[
	 * 		   [116.413968, 39.980746], 
	 * 		   [116.413923, 39.980701], 
	 * 		   [116.413914, 39.980721], 
	 * 		   [116.413918, 39.980685],
	 *         [116.413909, 39.980717]
	 * 		]
	 * >
	 * @param dataList
	 * @return
	 * @throws NumberFormatException
	 */
	/**
	@Deprecated
	public String parseData(List<Map<String, String>> dataList) throws NumberFormatException
	{
		ArrayList<ArrayList<Double>> datas = new ArrayList<ArrayList<Double>>();
//		BigDecimal de = new BigDecimal();
		for(Map<String, String> data : dataList)
		{
			ArrayList<Double> point = new ArrayList<Double>(2);
			point.add(Double.parseDouble(data.get("posilng")));
			point.add(Double.parseDouble(data.get("posilat")));
			datas.add(point);
		}
		return datas.toString();
	}
	
	*/
	
	/**
	 * 解析数据
	 * 数据格式
	 * 
	 * 
	 * {
	"errcode":200,
	"data":[
		{
			"latitude":29.524761,
			"millisec":1627550076000,
			"longitude":106.605279
		},
		{
			"latitude":29.524761,
			"millisec":1627550094000,
			"longitude":106.605279
		},
		{
			"latitude":29.524761,
			"millisec":1627550164000,
			"longitude":106.605282
		},
		{
			"latitude":29.524761,
			"millisec":1627550169000,
			"longitude":106.605282
		},
		{
			"latitude":29.524903,
			"millisec":1627550209000,
			"longitude":106.605366
		}
		]
	}
	 * 
	 * 
	 * 
	 * 
	 * @param dataList
	 * @return
	 */
	public JSONArray parseDataArray(List<Map<String, String>> dataList)
	{
		int length = dataList.size();
		JSONArray dataArr = new JSONArray(length);
		JSONObject  dataItem = null;
		for(int i=0; i<length; i++)
		{
			Map<String, String> item = dataList.get(i);
			dataItem = new JSONObject(true);
			dataItem.put("longitude", Double.parseDouble(item.get("posilng")));
			dataItem.put("latitude", Double.parseDouble(item.get("posilat")));
			dataItem.put("millisec", getmillisec(item.get("positime")));
			
			dataArr.add(dataItem);			//添加
		}
				
		return dataArr;
	}
	
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public Long getmillisec(String date)
	{
			Date real;
			try {
					real = format.parse(date);
					return real.getTime();
			} catch (ParseException e) {
					e.printStackTrace();
					return null;
			}
	}
}
