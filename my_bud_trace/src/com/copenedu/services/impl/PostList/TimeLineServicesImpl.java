package com.copenedu.services.impl.PostList;

import java.util.List;
import java.util.Map;

import com.copenedu.services.support.JdbcServicesSupport;

public class TimeLineServicesImpl extends JdbcServicesSupport 
{

	public TimeLineServicesImpl(Map<String, String> dto) 
	{
		super(dto);
	}
	
	/**
	 * 添加时间线
	 * @return
	 * @throws Exception
	 */
	public boolean addTimeline() throws Exception
	{
		String datetime=this.getString("litimehap");
		String date1=datetime.substring(0, 10);
		String date2=datetime.substring(11);
		datetime=date1+date2;
		StringBuilder sql = new StringBuilder()
				.append("insert into timeline(lipostid,litimehap,litimecreate,liimfor)")
				.append("		       values(?,str_to_date(?,'%Y-%m-%d %H:%i:%s'),current_timestamp,?)")
				;
		Object[] para = {
				this.getString("lipostid"),
				datetime,
				this.getString("liimfor")
		};
		
		return this.executeUpdate(sql.toString(), para ) > 0 ;
	}
	/**
	 * 查看时间线信息
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>>findById()throws Exception
	{
		StringBuilder sql=new StringBuilder()
				.append("select x.litimehap,x.litimecreate,x.listatus,x.lipostid,x.liimfor,")
				.append("       x.liid,x.lidelflag")
				.append("  from timeline x")
				.append(" where x.lipostid=?")
				.append(" order by x.litimehap desc")        //时间后序排列
				;
		return this.queryForList(sql.toString(), this.getInteger("postid"));
	}
}
