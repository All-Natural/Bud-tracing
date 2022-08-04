package com.copenedu.services.impl.Clue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.copenedu.services.support.JdbcServicesSupport;
import com.copenedu.system.tools.Tools;

public class ManageClueServicesImpl extends JdbcServicesSupport 
{

	public ManageClueServicesImpl(Map<String, String> dto) 
	{
		super(dto);
	}
	
	/**
	 * 根据单一帖子展示与之相关的线索摘要
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,String>> findByPId() throws Exception
	{
		StringBuilder sql = new StringBuilder()
				.append("select c.clid,u.uname,c.cldigest,c.cltimecreate,c.cltimehap")
				.append("  from clue c,user u")
				.append(" where c.clpostid=? and c.clstate=1 and c.cldelflag=1 and c.cluid=u.uid")
				;
		List<Object> paramList=new ArrayList<>();
		paramList.add(this.getInteger("postid"));
		
		return this.queryForPage(sql.toString(), 6, paramList.toArray());
	}

	/**
	 * 根据线索id展示线索全部内容
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> findById()throws Exception
	{
		StringBuilder sql = new StringBuilder()
				.append("select	clid,cluid,u.uname,clpostid,cldigest,clcontent,")
				.append("		cltimecreate,cltimehap,clstate,cldelflag,clmemo,")
				.append("		clphoto1,clphoto2,cldoc1,cldoc2")
				.append("  from clue,user u")
				.append(" where clid = ? and u.uid=cluid")
				;
		return this.queryForMap(sql.toString(), this.getInteger("clid"));
	}
	
	/**
	 * 添加线索
	 * @return
	 * @throws Exception
	 */
	public Integer addClue() throws Exception
	{
		StringBuilder sql = new StringBuilder()
				.append("insert into clue(cluid,clpostid,cldigest,clcontent,cltimecreate,")
				.append("				  cltimehap,clstate,cldelflag,clmemo,clphoto1,")
				.append("				  clphoto2,cldoc1,cldoc2)")
				.append("	       values(?,?,?,?,current_timestamp,")
				.append("				  str_to_date(?,'%Y-%m-%d %H:%i:%s'),0,1,?,2,")
				.append("				  ?,?,?)")
				;
		String datetime=this.getString("cltimehap");
		System.out.println(datetime);
		String date1=datetime.substring(0, 10);
		String date2=datetime.substring(11);
		datetime=date1+date2;
		String content = this.getString("clcontent");
		Integer uid = this.getInteger("uid");
		Integer clpostid = this.getInteger("clpostid");
		Object param[] = {
				uid,
				clpostid,
				Tools.getDigest(content),
				content,
				//row2
				datetime,
				this.getString("clmemo"),
				//row3
				this.getString("clphoto2"),
				this.getString("cldoc1"),
				this.getString("cldoc2")
		};
		
		return this.executeUpdateOfKey(sql.toString(), param);
	}
	/**
	 * 发布找寻通知线索图片
	 * @return
	 * @throws Exception
	 */
	public boolean upLoadPhoto(String fileName)throws Exception
	{
		StringBuilder path = new StringBuilder();
		int mark = this.imgMark();
		if(mark!=0)
		{	
			mark--;
			StringBuilder sql=new StringBuilder()
					.append("insert into imgs(postid,clid,imgurl,imgmark)")
					.append(" 	 	   values(?,?,?,?)")
					;
			Object params[]= {
					null,
					this.getInteger("id"),
					path.append(fileName).toString(),
					mark
			};
			boolean flag = this.executeUpdate(sql.toString(), params)>0;
			if (flag)
			{
				this.markInsert(mark);
				return flag;
			}
		}
		return false;
	}
	/**
	 * 根据用户id展示与之相关的线索摘要
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,String>> findByUId() throws Exception
	{
		StringBuilder sql = new StringBuilder()
				.append("select c.clid,u.uname,c.clpostid,c.cldigest,c.cltimecreate,c.cltimehap")
				.append("  from clue c,user u")
				.append(" where c.cluid=? and c.cldelflag=1 and u.uid=c.cluid")
				;
		List<Object> paramList=new ArrayList<>();
		paramList.add(this.getInteger("uid"));
		
		return this.queryForPage(sql.toString(), 6, paramList.toArray());
	}
	
	/**
	 * 删除线索
	 * @return
	 * @throws Exception
	 */
	public boolean delClue() throws Exception
	{
		StringBuilder sql = new StringBuilder()
				.append("update clue x ")
				.append("	set x.cldelflag = 2")
				.append(" where x.clid = ?")
				;
		this.regSqlToTransaction(sql.toString(), this.getInteger("clid"));
		return this.executeTrasaction();
	}
	/**
	 * 修改线索
	 * @return
	 * @throws Exception
	 */
	public boolean modClue()throws Exception
	{
		String datetime=this.getString("cltimehap");
		String date1=datetime.substring(0, 10);
		String date2=datetime.substring(11);
		datetime=date1+date2;
		String content = this.getString("clcontent");
		StringBuilder sql = new StringBuilder()
				.append("update clue set")
				.append("       cluid=?,clpostid=?,cldigest=?,clcontent=?,cltimecreate=current_timestamp,")
				.append("		cltimehap=str_to_date(?,'%Y-%m-%d %H:%i:%s'),clmemo=?,clphoto1=?,cldoc1=?, clstate = 0")
				.append(" where clid=?")
				;
		Object param[] = {
				this.getInteger("uid"),
				this.getInteger("clpostid"),
				Tools.getDigest(content),
				content,
				//row2
				datetime,
				this.getString("clmemo"),
				this.getString("clphoto1"),
				this.getString("cldoc1"),
				//row3
				this.getInteger("clid")
		};
		this.regSqlToTransaction(sql.toString(), param);
		return this.executeTrasaction();
	}
	//图片相关操作
	private int imgMark()throws Exception
	{
		String sql= "select clphoto1 from clue where clid = ?";
		Object params[]= {
			this.getInteger("id")	
		};
		Map<String, String> clueInfo = new HashMap<String, String>();
		clueInfo = this.queryForMap(sql, params);
		int mark = Integer.parseInt(clueInfo.get("clphoto1"));
		return mark;
	}
	
	private void markInsert(int mark)throws Exception
	{
		String sql = "update clue set clphoto1=? where clid =?";
		Object param[]= {
			mark,
			this.getInteger("id")
		};
		this.executeUpdate(sql, param);
	}
	
	public List<Map<String, String>> imgURL()throws Exception
	{
		String sql = "select i.imgurl from clue c,imgs i where i.clid=? and c.clid = i.clid";
		Object params[]= {
			this.getInteger("clid")
		};
		return queryForList(sql, params);
	}
}
