package com.copenedu.services.impl.User;

import java.util.Map;

import com.copenedu.services.support.JdbcServicesSupport;
import com.copenedu.system.tools.Tools;


public class RegisterServicesImpl extends JdbcServicesSupport
{
	
	public RegisterServicesImpl(Map<String, String> dto)
	{
		super(dto);
	}

	/**
	 * 注册新用户
	 * @return
	 */
//	public boolean addUser()throws Exception
//	{
//		StringBuilder sql=new StringBuilder()
//				.append("insert into user(uname,usex,urealname,upassword,uemail,")
//				.append("                 uphone,uaddress,systype,utimecreate)")
//				.append("	       values(?,?,?,?,?,")
//				.append("				  ?,?,?,current_timestamp)")
//				;
//		Object param[]= {
//				this.getString("uname"),
//				this.getInteger("usex"),
//				this.getString("urealname"),
//				Tools.getMd5(this.getString("upassword")),
//				this.getString("uemail"),
//				//row2
//				this.getString("uphone"),
//				this.getString("uaddress"),
//				this.getString("usystype")
//		};
//		
//		
////		https://tsapi.amap.com/v1/track/service/add
//		
//		
//		this.regSqlToTransaction(sql.toString(), param);
//		return this.executeTrasaction();
//	}
	
	public Integer addUser()throws Exception
	{
		try {
			StringBuilder sql = new StringBuilder().append("insert into user(uname,usex,urealname,upassword,uemail,")
					.append("                 uphone,uaddress,systype,utimecreate)")
					.append("	       values(?,?,?,?,?,").append("				  ?,?,?,current_timestamp)");
			Object param[] = { this.getString("uname"), this.getInteger("usex"), this.getString("urealname"),
					Tools.getMd5(this.getString("upassword")), this.getString("uemail"),
					//row2
					this.getString("uphone"), this.getString("uaddress"), this.getString("usystype") };
			
			Integer uid = this.executeUpdateOfKey(sql.toString(), param);
			return uid;
		} catch (Exception e) {
			return null;
		}
	}
	
	public boolean updateSid(Integer uid, Integer sid) throws Exception
	{
		StringBuilder builder = new StringBuilder()
				.append("update user ")
				.append("   set usid = ?")
				.append(" where uid = ?")
				;
		
		Object[] params = {sid, uid};
		
		return this.executeUpdate(builder.toString(), params) > 0;
	}
	
	
	
	
//	public static void main(String[] args) {
//		try {
//			RegisterServicesImpl impl = new RegisterServicesImpl(null);
//
//			StringBuilder sql = new StringBuilder()
//					.append("insert into user(uname,upassword,")
//					.append("                 uphone,systype,utimecreate)").append("	       values(?,?,")
//					.append("				  ?,3,current_timestamp)");
//			Object param[] = { 
//					"tom",
//					Tools.getMd5("123456"),
//					//row2
//					"12345678915" };
//
//			Integer uid = impl.executeUpdateOfKey(sql.toString(), param);
//			System.out.println(">>>>>" + uid);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}
