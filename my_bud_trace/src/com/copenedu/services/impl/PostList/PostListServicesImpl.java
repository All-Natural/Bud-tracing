package com.copenedu.services.impl.PostList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.copenedu.services.support.JdbcServicesSupport;
import com.copenedu.system.tools.Tools;

public class PostListServicesImpl extends JdbcServicesSupport
{
	public PostListServicesImpl(Map<String, String> dto)
	{
		super(dto);
	}
	
	/**
	 * 发布找寻通知，进入待审核
	 * @return
	 * @throws Exception
	 */
	public Integer addSearchPost()throws Exception
	{
		String datetime=this.getString("posttimehap");
//		System.out.println(datetime);
		String date1=datetime.substring(0, 10);
		String date2=datetime.substring(11);
		datetime=date1+date2;
		
		StringBuilder sql=new StringBuilder()
				.append("insert into post(postuid,postdigest,postcontent,postprogress,poststatus,")
				.append("                 posttimecreate,posttimehap,poststate,postdelflag,postrelaphone,")
				.append("                 postrelaname,postmemo,postrecord1)")
				.append("	       values(?,?,?,?,?,")
				.append("				  current_timestamp,str_to_date(?,'%Y-%m-%d %H:%i:%s'),?,?,?,")
				.append("				  ?,?,2)")
				;
		String content = this.getString("postcontent");
		Object param[]= {
				this.getString("uid"),
				Tools.getDigest(content),
				content,
				this.getString("postprogress"),
				this.getInteger("poststatus"),
				//row2
				datetime,
				0,//新建找寻通知的审核状态默认为：待审核 0  1审核通过 2审核未通过
				1,//新建找寻通知的删除标识默认为：未删除 1  2已删除
				this.getString("postrelaphone"),
				//row3
				this.getString("postrelaname"),
				this.getString("postmemo")
		};
		return this.executeUpdateOfKey(sql.toString(), param);
	}

	/**
	 * 发布找寻通知图片
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
					this.getInteger("id"),
					null,
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
	* 修改通过审核的找寻通知
	* @return
	* @throws Exception
	*/
	public boolean updateSearchPost()throws Exception
	{
		String datetime=this.getString("posttimehap");
		String date1=datetime.substring(0, 10);
		String date2=datetime.substring(11);
		datetime=date1+date2;
		StringBuilder sql=new StringBuilder()
				.append("update post set ")
				.append(" postuid=?,postdigest=?,postcontent=?,poststatus=?,posttimecreate=current_timestamp,")
				.append(" posttimehap=str_to_date(?,'%Y-%m-%d %H:%i:%s'),poststate=?,postdelflag=?,postrelaphone=?,")
				.append(" postrelaname=?,postmemo=?")
				.append(" where postid=?")
				;
		String content = this.getString("postcontent");
		Object param[]= {
				this.getString("uid"),
				Tools.getDigest(content),
				content,
				this.getInteger("poststatus"),
				//row2
				datetime,
				0,
				1,
				this.getString("postrelaphone"),
				//row3
				this.getString("postrelaname"),
				this.getString("postmemo"),
				this.getString("postid")
		};
		this.regSqlToTransaction(sql.toString(), param);
		return this.executeTrasaction();
	}
	
	/**
	 * 删除自己发布的找寻通知
	 * @return
	 * @throws Exception
	 */
	public boolean delMyPostBatch()throws Exception
	{
		String sql="update post set postdelflag=? where postid=?";
		Object params[]= {
				this.getInteger("delflag"),
				this.getInteger("postid")
		};
		this.regSqlToTransaction(sql.toString(), params);
		return this.executeTrasaction();
	}
	
	/**
	 * 选择查看找寻通知
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> findById()throws Exception
	{
		StringBuilder sql = new StringBuilder()
				.append("select x.postid,x.postdigest,x.postcontent,x.poststate,x.poststatus,x.posttimehap,")
				.append("		x.postrelaphone,x.postrelaname,x.postmemo,x.postdelflag,x.posttimecreate")
				.append("  from post x")
				.append(" where x.postid=?")
				;
		return this.queryForMap(sql.toString(), this.getInteger("postid"));
	}
	
	/**
	 * 查询所有自身发布的找寻通知
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> showMyPostList()throws Exception
	{	
		String postuid=this.getString("uid");
		StringBuilder sql = new StringBuilder()
				.append("select p.postid,p.postdigest,p.poststatus,p.posttimecreate,p.postrelaname,")
				.append("		p.postrelaphone,i.imgurl")
				.append("  from post p,imgs i")
				.append(" where p.postdelflag=1 and i.imgmark=1 and i.postid = p.postid")
				;
		
		
		List<Object> paramList=new ArrayList<>();
		//3.逐一拼接查询条件
		sql.append(" and postuid=?");
		paramList.add(postuid);
		
		return this.queryForPage(sql.toString(),4, paramList.toArray());
	}
	
	/**
	 * 查询所有社区找寻通知
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> showPostList()throws Exception
	{
		StringBuilder sql = new StringBuilder()
				.append("select p.postid,p.postdigest,p.postprogress,p.poststatus,p.posttimecreate,")
				.append("		p.postrelaphone,p.postrelaname,i.imgurl")
				.append("  from post p,imgs i")
				.append(" where p.postdelflag=1 and p.poststate=1 and i.imgmark=1 and i.postid = p.postid")
				;
		
		List<Object> paramList=new ArrayList<>();
		
		return this.queryForPage(sql.toString(),4, paramList.toArray());
	}
	
	
	/**
	 * 查看是否切换自我路径
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public int whichPath()throws Exception
	{
		int wpath=this.getInteger("path");
		return wpath;
	}
	//图片相关操作
	private int imgMark()throws Exception
	{
		String sql= "select postrecord1 from post where postid = ?";
		Object params[]= {
			this.getInteger("id")	
		};
		Map<String, String> postInfo = new HashMap<String, String>();
		postInfo = this.queryForMap(sql, params);
		int mark = Integer.parseInt(postInfo.get("postrecord1"));
		return mark;
	}
	
	private void markInsert(int mark)throws Exception
	{
		String sql = "update post set postrecord1=? where postid =?";
		Object param[]= {
			mark,
			this.getInteger("id")
		};
		this.executeUpdate(sql, param);
	}
	
	public List<Map<String, String>> imgURL()throws Exception
	{
		String sql = "select i.imgurl from post p,imgs i where i.postid=? and p.postid = i.postid";
		Object params[]= {
			this.getInteger("postid")
		};
		return queryForList(sql, params);
	}
}
