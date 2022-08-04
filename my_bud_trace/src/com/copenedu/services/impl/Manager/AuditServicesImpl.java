package com.copenedu.services.impl.Manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.copenedu.services.support.JdbcServicesSupport;
import com.copenedu.system.tools.Tools;

public class AuditServicesImpl extends JdbcServicesSupport
{
	public AuditServicesImpl(Map<String, String> dto)
	{
		super(dto);
	}
	
	/**
	 * 查看所有未审核找寻通知
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,String>> showAuditPostList()throws Exception
	{
		String postdigest = this.getString("digest");
		StringBuilder sql = new StringBuilder()
				.append("select postid,postuid,postdigest,posttimecreate,postrelaname,postrelaphone")
				.append("  from post")
				.append(" where postdelflag=1 and poststate=0")
				;
		
		List<Object> paramList=new ArrayList<>();
		if (this.isNotNull(postdigest)) 
		{
			sql.append(" and postdigest like ?");
			paramList.add("%"+postdigest+"%");
		}
		
		return this.queryForPage(sql.toString(),3, paramList.toArray());
	}
	
	/**
	 * 查看所有未审核线索
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> showAuditClueList()throws Exception
	{
		String cldigest=this.getString("cldigest");
		StringBuilder sql = new StringBuilder()
				.append("select c.clid,c.cluid,c.clpostid,c.cldigest,c.cltimecreate,")
				.append("		c.cltimehap,x.uname")
				.append("  from clue c,user x")
				.append(" where c.clstate=0 and c.cldelflag=1 and c.cluid=x.uid ")
				;
		
		List<Object> paramList=new ArrayList<>();
		if(this.isNotNull(cldigest))
		{
			sql.append(" and cldigest like ?");
			paramList.add("%"+cldigest+"%");
		}
		
		return this.queryForPage(sql.toString(),3, paramList.toArray());
	}
	
	/**
	 * 查看待审核线索详情
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> showAuditClue()throws Exception
	{
		StringBuilder sql = new StringBuilder()
				.append("select x.clid,x.cluid,x.clpostid,x.cldigest,x.clcontent,x.clstate,")
				.append("       x.cltimehap,x.cltimecreate,x.clmemo,x.clphoto1,x.clphoto2,")
				.append("       x.cldoc1,x.cldoc2,x.cluid,u.urealname")
				.append("  from clue x,user u")
				.append(" where u.uid=x.cluid and x.clid=?")
				;
		return this.queryForMap(sql.toString(),this.getInteger("clid"));
	}
	
	/**
	 * 查看待审核找寻通知详情
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> showAuditPost()throws Exception
	{
		StringBuilder sql = new StringBuilder()
				.append("select x.postid,x.postuid,x.postdigest,x.postcontent,x.postprogress,x.poststatus,")
				.append("       x.posttimehap,x.postrelaphone,x.postrelaname,x.postmemo,x.poststate")
				.append("  from post x")
				.append(" where x.postid=?")
				;
		return this.queryForMap(sql.toString(),this.getInteger("postid"));
	}
	
	/**
	 * 通知审核
	 * 1 通知审核结果 2线索审核结果 3其他
	 * 1-5 重要等级依次提升
	 * @return  
	 * @throws Exception
	 */
	public boolean AuditPost()throws Exception
	{
		String sql1="update post set poststate=? where postid=?";
		StringBuilder sql2=new StringBuilder()
				.append("insert into record(readminid,retype,retime,recontent,rememo,reitemsid)")
				.append("               values(?,?,current_timestamp,?,?,?)")
				; 
		StringBuilder sql3=new StringBuilder()
				.append("insert into prompt(prouid,prograde,procontent,prodigest,procreate,")
				.append("                   protype, prodelflag)")
				.append("               values(?,?,?,?,current_timestamp,?, 1)")
				;
		String procontent=Tools.auditPResult(this.getString("apostrelaname"), this.getInteger("poststate"));
		Object params1[]= {
			this.getInteger("poststate"),	
			this.getInteger("postid")
		};
		Object param2[]= {
			this.getInteger("uid"),
			1,
			this.getString("recontent"),
			this.getString("rememo"),
			this.getInteger("postid")
		};
		Object param3[]= {
			this.getInteger("postuid"),
			1,
			procontent,
			Tools.getDigest(procontent),
			1	
		};

		this.regSqlToTransaction(sql1, params1);
		this.regSqlToTransaction(sql2.toString(), param2);
		this.regSqlToTransaction(sql3.toString(), param3);
		return this.executeTrasaction();
	}
	
	/**
	 * 线索审核
	 * 2线索审核结果
	 * 0-n 重要等级依次提升
	 * @return  
	 * @throws Exception
	 */
	public boolean AuditClue()throws Exception
	{
		String sql1="update clue set clstate=? where clid=?";
		StringBuilder sql2=new StringBuilder()
				.append("insert into record(readminid,retype,retime,recontent,rememo,reitemsid)")
				.append("               values(?,?,current_timestamp,?,?,?)")
				; 
		StringBuilder sql3=new StringBuilder()
				.append("insert into prompt(prouid,prograde,procontent,prodigest,procreate,")
				.append("                   protype, prodelflag)")
				.append("            values(?,?,?,?,current_timestamp,?, 1)")
				;
		String procontent=Tools.auditPResult(this.getString("urealname"), this.getInteger("clstate"));
		Object params1[]= {
				this.getInteger("clstate"),	
				this.getInteger("clid")
			};
		Object param2[]= {
				this.getInteger("uid"),
				2,
				this.getString("recontent"),
				this.getString("rememo"),
				this.getInteger("clid")
			};
		Object param3[]= {
				this.getInteger("cluid"),
				1,				
				procontent,
				Tools.getDigest(procontent),
				1	
			};
		this.regSqlToTransaction(sql1, params1);
		this.regSqlToTransaction(sql2.toString(), param2);
		this.regSqlToTransaction(sql3.toString(), param3);
		return this.executeTrasaction();
	}
}
