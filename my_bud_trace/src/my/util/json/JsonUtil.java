package my.util.json;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JsonUtil 
{
	public final static JSONObject mapToJson(Map<String, Object>  map)
	{
		JSONObject jobj = new JSONObject();
		for(Entry<String, Object> entry : map.entrySet())
		{
			jobj.put(entry.getKey(), entry.getValue());
		}
		return jobj;
	}
	
	public final static JSONObject mapToJson2(Map<String, String>  map)
	{
		JSONObject jobj = new JSONObject();
		for(Entry<String, String> entry : map.entrySet())
		{
			jobj.put(entry.getKey(), entry.getValue());
		}
		return jobj;
	}
	
	public static JSONArray listToJsarr(List<Map<String, String>> dataList)
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
	
	public final static Map<String, String> jsonToStrMap(JSONObject jreq)
	{
		Map<String, String> map = new HashMap<>();
		for(Entry<String, Object> entry : jreq.entrySet())
		{
			map.put(entry.getKey(), String.valueOf(entry.getValue()));
		}
		return map;
	}
	
	public final static Map<String, Object> jsonToMap(JSONObject jreq)
	{
		Map<String, Object> map = new HashMap<>();
		for(Entry<String, Object> entry : jreq.entrySet())
		{
			Object obj = entry.getValue();
			if(obj instanceof JSONObject)
			{
				map.put(entry.getKey(), jsonToMap((JSONObject)obj));
			}
			else
			{
				map.put(entry.getKey(), obj);
			}
		}
		return map;
	}

}
