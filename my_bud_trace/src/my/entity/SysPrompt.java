package my.entity;

import java.util.Map;

public class SysPrompt 
{
	
	private Integer[] uids;
	private Map<String, Object> data;
 		
	public SysPrompt(Map<String, Object> params, Integer... uids)
	{
		this.uids = uids;
		this.data = params;
	}

	public Integer[] getUids() {
		return uids;
	}

	public void setUids(Integer[] uids) {
		this.uids = uids;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	
}
