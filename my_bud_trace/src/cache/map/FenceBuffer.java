package cache.map;

import java.util.HashMap;

import com.alibaba.fastjson.JSONObject;

public class FenceBuffer 
{
	private static HashMap<Integer, JSONObject> fences = new HashMap<>();			//
	
	public static JSONObject getFenceFromBuffer(Integer uid)
	{
		return fences.get(uid);
	}
	
	public static void update(Integer uid, JSONObject point)				//点上传时更新
	{
		fences.put(uid, point);
	}
	
	public static void delete(Integer uid)
	{
		fences.remove(uid);
	}
}
