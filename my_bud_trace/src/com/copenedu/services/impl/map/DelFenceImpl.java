package com.copenedu.services.impl.map;

import java.util.Map;

import com.copenedu.services.support.JdbcServicesSupport;
import com.copenedu.system.db.DBUtils;

import my.util.str.StrUtil;

public class DelFenceImpl extends JdbcServicesSupport
{

	public DelFenceImpl(Map<String, String> dto) {
		super(dto);
	}
	
	public boolean delFence() throws Exception
	{
		StringBuilder builder = new StringBuilder()
				.append("delete from fence")
				.append(" where fdcreater = ?")
				.append("   and fdgfid = ?")
				;
		
		Object[] params = {
				getInteger("uid"),
				getInteger("gfids")
		};
		
		return executeUpdate(builder.toString(), params) > 0;
	}
	
}
