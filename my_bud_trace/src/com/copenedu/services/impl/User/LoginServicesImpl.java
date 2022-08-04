package com.copenedu.services.impl.User;

import java.util.List;
import java.util.Map;

import com.copenedu.services.support.JdbcServicesSupport;
import com.copenedu.system.tools.Tools;

public class LoginServicesImpl extends JdbcServicesSupport
{
	
	public LoginServicesImpl(Map<String, String> dto)
	{
		super(dto);
	}

	/**
	 * 校验用户
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> checkUser()throws Exception
	{
		//1.定义SQL语句
		StringBuilder sql=new StringBuilder()
				.append("select x.uid,x.uname,x.systype")
				.append("  from user x")
				.append(" where x.uphone=?")
				.append("   and x.upassword=?")
				;
		//2.组织参数数组
		Object param[]= {
				this.getString("uphone"),
				//密文混淆
				Tools.getMd5(this.getString("upassword"))
		};
		//3.执行查询
		return this.queryForMap(sql.toString(), param);
	}

	/**
	 * 查询用户权限，展示相关菜单
	 * @param systype
	 * @return
	 * @throws Exception
	 */
	
	
	public String getMenuForString(String systype)throws Exception
	{
		StringBuilder sql=new StringBuilder()
				.append("select x.menuname,x.openurl")
				.append("  from sysmenu x")
				.append(" where x.systype=?")
				;
		
		
		return this.queryForString(
				sql.toString(),
				(rs)->{
					StringBuilder menu=new StringBuilder();
					while(rs.next())
					{
						menu
						.append("<a href=\"")
						.append(this.getString("classpath"))
						.append("/")
						.append(rs.getString(2))
						.append("\" target=\"view\">")
					    .append(rs.getString(1))
					    .append("</a><br>");
					}
					return menu.toString();
				}, 
				systype);
	}
	
	public List<Map<String,String>> getUserMenu(String systype)throws Exception
	{
		
		StringBuilder sql=new StringBuilder()
				 .append("select x.menuname,x.openurl") 
				 .append("  from sysmenu x")
				 .append(" where x.systype=?")	
				;
		return this.queryForList(sql.toString(), systype);
		
	}
}
