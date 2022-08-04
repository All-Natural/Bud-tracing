package com.copenedu.services.impl.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.copenedu.services.support.JdbcServicesSupport;

public class DevicesManagementServicesImpl extends JdbcServicesSupport 
{

	public DevicesManagementServicesImpl(Map<String, String> dto) 
	{
		super(dto);
	}
	
	/**
	 * 添加子端设备
	 * @return
	 * @throws Exception
	 */
	public boolean addDevices() throws Exception
	{
		StringBuilder sql1 = new StringBuilder()
				.append("insert into devices(duid,chid,childname)")
				.append("	          values(?,?,?)")
				;
		StringBuilder sql2 = new StringBuilder()
				.append("select uid from user where uphone = ?")
				;
		Map<String, String> uimfor = this.queryForMap(sql2.toString(), this.getString("uphone"));
		Integer uid = Integer.valueOf(uimfor.get("uid"));
		
		Object[] paras = {
				this.getInteger("uid"),
				uid,
				this.getString("childname")
		};
		
		return this.executeUpdate(sql1.toString(), paras) > 0;
	}
	
	
	/**
	 * 删除子端设备
	 * @return
	 * @throws Exception
	 */
	public boolean delDevices()throws Exception
	{
		String sql="delete from devices where deid=?";
		
		return this.executeUpdate(sql, this.getInteger("deid")) > 0;
	}
	
	/**
	 * 查找子端设备
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> showDevices()throws Exception
	{
		Integer uid = getInteger("uid");
		if(uid == null) return null;
		StringBuilder sql=new StringBuilder()
				.append("select d.duid,d.deid,d.childname,d.chid")
				.append("  from devices d")
				.append(" where d.duid=?")
				;
		List<Object> rows=new ArrayList<Object>();
		
		rows.add(uid);
		
		return this.queryForPage(sql.toString(),10,rows.toArray());
	}
}