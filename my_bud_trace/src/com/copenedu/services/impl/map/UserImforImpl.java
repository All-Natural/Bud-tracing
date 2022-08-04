package com.copenedu.services.impl.map;

import java.util.List;
import java.util.Map;

import com.copenedu.services.support.JdbcServicesSupport;

public class UserImforImpl extends JdbcServicesSupport
{

	public UserImforImpl(Map<String, String> dto) {
		super(dto);
	}
	
	public List<Map<String, String>> queryUserImfor(Map<String, String> pAndCh) throws Exception
	{
		StringBuilder builder = new StringBuilder()
				.append("select uid, uname, uemail")
				.append("  from user where uid = ?")
				.append("               or uid = ?")
				;
		Object[] params = {
			 Integer.valueOf(pAndCh.get("fdforuser")),
			 Integer.valueOf(pAndCh.get("fdcreater"))
		};
		
		return this.queryForList(builder.toString(), params);
	}
	
	public List<Map<String, String>> queryUserImfor(Integer puid, Integer cuid) throws Exception
	{
		StringBuilder builder = new StringBuilder()
				.append("select uid, uname, uemail")
				.append("  from user where uid = ?")
				.append("               or uid = ?")
				;
		Object[] params = {
			 puid,
			 cuid
		};
		
		return this.queryForList(builder.toString(), params);
	}
	
	public List<Map<String, String>> queryUserImfor2(Map<String, Object> pAndCh) throws Exception
	{
		StringBuilder builder = new StringBuilder()
				.append("select uid, uname, uemail")
				.append("  from user where uid = ?")
				.append("               or uchildid1 = ?")
				.append("               or uchildid2 = ?")
				;
		Object[] params = {
			 pAndCh.get("uid"),
			 pAndCh.get("uid"),
			 pAndCh.get("uid")
		};
		
		return this.queryForList(builder.toString(), params);
	}
	
	
	
}
