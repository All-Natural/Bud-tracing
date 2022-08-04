package com.copenedu.services.impl.SySMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.copenedu.services.support.JdbcServicesSupport;

public class SySMessageServicesImpl extends JdbcServicesSupport
{
	
	public SySMessageServicesImpl(Map<String, String> dto)
	{
		super(dto);
	}
	
	/**
	 * 显示系统消息
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> showSySMessage()throws Exception
	{
		String prograde=this.getString("qprograde");
		String procreate=this.getString("qprocreate");
		String protype=this.getString("qprotype");
		String prouid=this.getString("uid");
		
		StringBuilder sql = new StringBuilder()
				.append("select x.proid,x.prograde,x.prodigest,x.procreate,x.protype")
				.append("  from prompt x")
				.append(" where x.prouid=?")
				;
		
		List<Object> paramList=new ArrayList<>();
		
		paramList.add(prouid);
		
		if(this.isNotNull(prograde))
		{
			sql.append(" and x.prograde=?");
			paramList.add(prograde);
		}
		if(this.isNotNull(procreate))
		{
			sql.append(" and x.procreate=?");
			paramList.add(procreate);
		}
		if(this.isNotNull(protype))
		{
			sql.append(" and x.protype=?");
			paramList.add(protype);
		}
		return this.queryForPage(sql.toString(), 10, paramList.toArray());
	}

	public boolean delSySMsg()throws Exception
	{
		String sql = "delete from prompt where proid=?";
		Object idlist[] = this.getIntArray("proid");
		return this.batchUpdate(sql, idlist);
	}
	
	
	public Map<String, String> findById()throws Exception
	{
		StringBuilder sql = new StringBuilder()
				.append("select x.proid,x.prograde,x.prodigest,x.procontent，")
				.append("       x.procreate,x.prolink,x.prophoto1,x.prophoto2,x.prodoc1,")
				.append("       x.prodoc2,x.protype")
				.append("  from prompt x")
				.append(" where proid=?")
				;
		
		return  this.queryForMap(sql.toString(), this.getInteger("proid"));
	}
}
