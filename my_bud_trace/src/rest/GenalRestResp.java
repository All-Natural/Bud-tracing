package rest;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class GenalRestResp implements RestResp
{
	int errcode;					//0表示成功
	String errmsg;
	String errdetail;
	Object data;
	
	@Override
	public String draw()
	{
		JSONObject result = new JSONObject();
		result.put("errcode", errcode);
		result.put("errmsg", errmsg);
		
		if(errdetail != null) result.put("errdetail", errdetail);
		
		if(data != null)
		{
				if(data instanceof JSONObject)
				{
						result.put("data", data);
				}
				else if (data instanceof JSONArray)
				{
						result.put("data", data);
				}
				else if(data instanceof List)
				{
						JSONArray arr = new JSONArray((List)data);
						JSONObject res = new JSONObject();
						res.put("results", arr);
						result.put("data", res);
				}
				else if(data instanceof Map)
				{
						JSONObject res = new JSONObject((Map<String, Object>) data);
						result.put("data", res);
				}
				else if(data instanceof String)
				{
						result.put("data", data);
				}
				else
				{
//						JSONObject res = new JSONObject();
//						result.put("data", res);
						//其他类型直接put
						result.put("data", data);
				}
		}
		
		return JSON.toJSONString(result, SerializerFeature.PrettyFormat);
	}

	public int getErrcode() 
	{
		return errcode;
	}

	public void setErrcode(int errcode) 
	{
		this.errcode = errcode;
	}

	public String getErrmsg() 
	{
		return errmsg;
	}

	public void setErrmsg(String errmsg) 
	{
		this.errmsg = errmsg;
	}

	public String getErrdetail() 
	{
		return errdetail;
	}

	public void setErrdetail(String errdetail) 
	{
		this.errdetail = errdetail;
	}

	public Object getData() 
	{
		return data;
	}

	public void setData(Object data) 
	{
		this.data = data;
	}
	
}
