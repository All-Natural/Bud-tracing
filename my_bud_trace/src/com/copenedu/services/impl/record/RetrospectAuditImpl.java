package com.copenedu.services.impl.record;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.copenedu.services.support.JdbcServicesSupport;

public class RetrospectAuditImpl extends JdbcServicesSupport {

	public RetrospectAuditImpl(Map<String, String> dto) 
	{
		super(dto);
	}
	
	/**
	 * 显示审核记录列表
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,String>> showRecord()throws Exception
	{
		StringBuilder sql = new StringBuilder()
				.append("select x.uname,y.retype,y.reid,y.reitemsid")
				.append("  from user x,record y")
				.append(" where x.uid=y.readminid")
				;
		List<Map<String, String>>paramList=new ArrayList<Map<String,String>>();
		return this.queryForPage(sql.toString(),6, paramList.toArray());
	}

}
