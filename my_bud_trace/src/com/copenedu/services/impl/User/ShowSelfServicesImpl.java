package com.copenedu.services.impl.User;

import java.util.Map;

import com.copenedu.services.support.JdbcServicesSupport;

public class ShowSelfServicesImpl extends JdbcServicesSupport
{

	public ShowSelfServicesImpl(Map<String, String> dto)
	{
		super(dto);
	}
	
	public Map<String, String> showSelf()throws Exception
	{
		StringBuilder sql=new StringBuilder()
				.append("select x.uid,x.uname,x.uemail,x.uphone,x.uaddress")
				.append("  from user x")
				.append(" where x.uid=?")
				 ;
		return this.queryForMap(sql.toString(),this.getInteger("uid"));
	}
	/**
	 * 修改用户信息
	 * @return
	 * @throws Exception
	 */
	public boolean modUser()throws Exception
	{
		StringBuilder sql=new StringBuilder()
				.append("update user")
				.append("   set uname=?,usex=?,urealname=?,uemail=?,uphone=?,")
				.append("	    uaddress=?")
				.append(" where uid=?")
				;
		Object statement[]= {
				this.getString("uname"),
				this.getInteger("usex"),
				this.getString("urealname"),
				this.getString("uemail"),
				this.getString("uphone"),
				this.getString("uaddress")
		};
		Object idlist[]= {
				this.getInteger("auid")
		};
		return this.batchUpdate(sql.toString(), statement, idlist);
	}
	
	/**
	 * 查询用户详情
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> findById()throws Exception
	{
		StringBuilder sql=new StringBuilder()
				.append("select x.uid,x.uname,x.usex,x.urealname,x.uemail,")
				.append("       x.uphone,x.uaddress")
				.append("  from user x")
				.append(" where uid=?")
				;
		Integer auid=this.getInteger("auid");
		return this.queryForMap(sql.toString(), auid);
	}
}
