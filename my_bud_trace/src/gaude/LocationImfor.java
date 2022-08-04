package gaude;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

import httpclient.MyHttp;
import httpclient.MyHttpFactory;

public class LocationImfor 
{
	private static final String KEY = "21e4c0b1313e7ef2bf1647f4fdac7419";					//web key   与
	private static MyHttp client = MyHttpFactory.getMyHttpClient();
	private static final String url = "https://restapi.amap.com/v3/geocode/regeo";
	
	public static String locationString(Double lng, Double lat)
	{
			if(lng == null || lat == null) return "";
			return locationString(lng + "," + lat);
	}
	
	/**
	 * 
	 逆地理编码：应答格式：
	 {
	    "status": "1",
	    "regeocode": {
	        "addressComponent": {
	            "city": [],
	            "province": "北京市",
	            "adcode": "110105",
	            "district": "朝阳区",
	            "towncode": "110105010000",
	            "streetNumber": {
	                "number": "5号",
	                "location": "116.415161,39.981535",
	                "direction": "东北",
	                "distance": "134.275",
	                "street": "安苑路"
	            },
	            "country": "中国",
	            "township": "小关街道",
	            "businessAreas": [
	                {
	                    "location": "116.413765,39.980391",
	                    "name": "小关",
	                    "id": "110105"
	                },
	                {
	                    "location": "116.424027,39.985572",
	                    "name": "惠新",
	                    "id": "110105"
	                },
	                {
	                    "location": "116.406089,39.966710",
	                    "name": "安贞",
	                    "id": "110105"
	                }
	            ],
	            "building": {
	                "name": [],
	                "type": []
	            },
	            "neighborhood": {
	                "name": [],
	                "type": []
	            },
	            "citycode": "010"
	        },
	        "formatted_address": "北京市朝阳区小关街道小关北京民族医院"			---目标
	    },
	    "info": "OK",
	    "infocode": "10000"
	}
	 
		
	 *
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @param location
	 * @return
	 */
	public static String locationString(String location)
	{
		Map<String, String> params = new HashMap();
		params.put("key", KEY);
		params.put("location", location);
		
		JSONObject jobj = client.getByRespJson(url, params);
		JSONObject regeocode = jobj.getJSONObject("regeocode");
		String formatted_address = regeocode.getString("formatted_address");
		return formatted_address;
	}
	
	public static void main(String[] args)
	{
		System.out.println(locationString(106.6052690,29.5247970));
	}
}
