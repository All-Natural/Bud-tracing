package cache.map;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

/**
 * 缓存模式
 * @author Not a Literary Gaint
 *
 */
public class PointBuffer 
{
	private static HashMap<Integer, JSONObject> points = new HashMap<>();
	
	public static JSONObject getPointFromBuffer(Integer uid)
	{
		return points.get(uid);
	}
	
	public static void update(Integer uid, JSONObject point)				//点上传时更新
	{
		synchronized (points) {
			points.put(uid, point);
		}
	}
	
	public static void init()
	{
		
	}
}
