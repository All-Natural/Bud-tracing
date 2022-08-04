/**
 * 
 */
package com.copenedu.services.impl.map;

import java.util.List;
import java.util.Map;

import com.copenedu.services.support.JdbcServicesSupport;

/**
 * @author xianzhikun
 *
 */
public class PolygonFenceImpl extends JdbcServicesSupport
{
		/**
		 * @param dto
		 */
		public PolygonFenceImpl(Map<String, String> dto)
		{
				super(dto);
		}
		
		/**
		 * 增加多边形围栏
		 * @return
		 * @throws Exception
		 */
		public boolean addFence() throws Exception
		{
				StringBuilder builder = new StringBuilder()
						.append("insert into polyfence(poname, podesc, poforuser, pocreater, potimecreate, ")
						.append("                                potype, popoints)")
						.append("    					values(?, ?, ?, ?, current_timestamp, ?, ?)")
						;
				
				Object[] params = {
						getString("name"),
						getString("desc"),
						getInteger("cuid"),
						getInteger("uid"),
						getInteger("type"),
						getString("points")
				};
				return executeUpdate(builder.toString(), params) > 0;
		}
		
		public List<Map<String, String>> query() throws Exception
		{
				StringBuilder builder = new StringBuilder()
						.append("	select poid, poname, podesc, potimecreate, potype, popoints")
						.append("  from polyfence")
						.append(" where poforuser = ? ")
						.append("    and pocreater = ?")
						;
				
				Object[] params = {
						getInteger("cuid"),
						getInteger("uid")
				};
				
				return queryForList(builder.toString(), params);
		}
		
		public List<Map<String, String>> query(Integer cuid, Integer puid) throws Exception
		{
				StringBuilder builder = new StringBuilder()
						.append("	select poid, poname, podesc, potimecreate, potype, popoints")
						.append("  from polyfence")
						.append(" where poforuser = ? ")
						.append("    and pocreater = ?")
						;
				
				Object[] params = {
						cuid,
						puid
				};
				
				return queryForList(builder.toString(), params);
		}
		
		public List<Map<String, String>> query(Integer cuid) throws Exception
		{
				StringBuilder builder = new StringBuilder()
						.append("	select poid, poname, podesc, pocreater, potimecreate, potype, popoints")
						.append("  from polyfence")
						.append(" where poforuser = ? ")
						;
				
				Object[] params = {
						cuid
				};
				
				return queryForList(builder.toString(), params);
		}
		
		public boolean delete() throws Exception
		{
				StringBuilder builder = new StringBuilder()
						.append("	delete from polyfence ")
						.append(" where pocreater = ? ")
						.append("    and poid = ? ")
						;
				
				Object[] params = {
						getInteger("uid"),
						getInteger("gfids")
				};
				
				return executeUpdate(builder.toString(), params) > 0;
		}
}
