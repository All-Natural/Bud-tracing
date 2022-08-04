package com.copenedu.services.impl.Manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.copenedu.services.support.JdbcServicesSupport;
import com.copenedu.system.tools.Tools;

public class AdminManagementServicesImpl extends JdbcServicesSupport {

	public AdminManagementServicesImpl(Map<String, String> dto)
	{
		super(dto);
	}
	
	/**
	 * 添加审核管理员
	 * @return
	 * @throws Exception
	 */
	public boolean addAdmin() throws Exception
	{
		StringBuilder sql = new StringBuilder()
				.append("insert into user(")
				.append("	uname,usex,urealname,upassword,uemail,")
				.append("	uphone,uaddress,systype,utimecreate)")
				.append(" values(")
				.append("	?,?,?,?,?,")
				.append("	?,?,1,current_timestamp)")
				;
		String pswMD5 = Tools.getMd5(this.getString("psw"));
		
		Object[] param = {
				this.getString("uname"),
				this.getString("usex"),
				this.getString("urealname"),
				pswMD5,
				this.getString("uemail"),
				//row2
				this.getString("uphone"),
				this.getString("uaddress")
		};
		this.regSqlToTransaction(sql.toString(), param);
		return this.executeTrasaction();
	}
	
	/**
	 * 删除审核管理员
	 * @return
	 * @throws Exception
	 */
	public boolean delAdminBatch()throws Exception
	{
		String sql="delete from user where uid=?";
		Object idlist[]=this.getIntArray("groupid");
		return this.batchUpdate(sql.toString(), idlist);
	}
	
	/**
	 * 修改审核管理员信息
	 * @return
	 * @throws Exception
	 */
	public boolean modAdmin()throws Exception
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
	 * 查询审核管理员
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> showAdminList()throws Exception
	{
		StringBuilder sql=new StringBuilder()
				.append("select x.uid,x.uname,x.usex,x.urealname,x.uemail,")
				.append("       x.uphone,x.uaddress")
				.append("  from user x")
				.append(" where systype=1")
				;
		List<Object> rows=new ArrayList<Object>();
		
		
		return this.queryForPage(sql.toString(),5,rows.toArray());
	}
	
	/**
	 * 查询某个审核管理员详情
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
