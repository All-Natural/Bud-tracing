package task.part;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.copenedu.services.impl.map.QueryFenceImpl;
import com.copenedu.services.impl.map.UserImforImpl;

import gaude.GaudeServices;
import gaude.LocationImfor;
import gaude.SpeedModel;
import httpclient.MyHttp;
import httpclient.MyHttpFactory;
import my.entity.SysPrompt;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Deprecated
public class PointJugdeTask extends Thread
{
	    public static PointJugdeTask task = new PointJugdeTask();         //单例 -- 暂时支持单例
	    private MyHttp client ;
	    /**
	     * point 内jsonobject 参数需求格式
	     * 
	     	key=c7431e0381db2590a3878decc53434cd
	     	&sid=377576
	     	&location=106.608956,29.530541
	     	&gfids=125514
	     * 
	     */
	    
	    private LinkedList<JSONObject> pointUpImofors;
	    
	    private boolean runFlag;
	    private long timeTry;           //没有数据的时候多久尝试上传一次
	    private String url;
	    private QueryFenceImpl fenceImpl;				//与当前线程绑定，不关闭操作
	    private UserImforImpl userImpl;
	    
	    private long frequence = 180000;				//围栏判断冷却时间 3分钟   1000 * 60 * 3
	    private long speedfre = 60000;     			//速度判断冷却1分钟		1000 * 60
	    private HashMap<String, Long> lastJugle = new HashMap<>();			//围栏冷却期
	    private HashMap<String, Long> lastJugleForSpeed = new HashMap<>();	//速度冷却期
	    
	    private HashMap<String, Integer> lastMode = new HashMap<>();		//上一次的模式
	    
	    private String locationTitle = "孩童位置预警";
	    private String speedTitle = "孩童速度预警";
//	    private double maxSpeed = 2;
	    
	//    private 
	    private PointJugdeTask()
	    {
	        pointUpImofors = new LinkedList<>();
	        client = MyHttpFactory.getMyHttpClient();
	        runFlag = true;
	        timeTry = 1000;
	        url = "https://tsapi.amap.com/v1/track/geofence/status/location";
	        fenceImpl = new QueryFenceImpl(null);
	        userImpl = new UserImforImpl(null);
	        
	        System.out.println("创建jugde task..");
	    }
	    
	    
	    @Override
	    public void run()
	    {
	        while(runFlag)
	        {
	            JSONObject pointImfor = pointUpImofors.poll();
	            if(pointImfor != null)
	            {
	            	List<Map<String, String>> fenceImfor = null;
	            	try 
	            	{
						fenceImfor = queryFenceImfor(pointImfor);
					} catch (Exception e) 
	            	{
						e.printStackTrace();
						
						continue;				//异常之后直接不处理这次请求
					}
	            	
	                JSONObject point = pointImfor.getJSONObject("point");
	                String location = locationStr(point);
	                String key = GaudeServices.KEY;
	                
	                goForSpeed(point, String.valueOf(pointImfor.getInteger("uid")), location);			//速度监测
	                
	                goForLocation(key, location, fenceImfor, point.getString("time"));					//区域监测
	            }
	            else
	            {
	                try
	                {
	                    Thread.sleep(timeTry);
	                }
	                catch(Exception e)
	                {
	                    e.printStackTrace();
	                }
	            }
	        }
	    }
	
	    public void addPoint(JSONObject point)
	    {
	        pointUpImofors.add(point);
	    }
	    
	    /**
	     * 
	     * @param jobj	查询参数
	     * @return
	     * @throws Exception 
	     */
	    public List<Map<String, String>> queryFenceImfor(JSONObject jobj) throws Exception
	    {
	    	List<Map<String, String>> fenceImfor = fenceImpl.queryFence(jobj);
	    	return fenceImfor;
	    }
	    
	    public String locationStr(JSONObject point)
	    {
	    	return point.getDouble("longitude") + "," + point.getDouble("latitude");
	    }
	    
	    public void goForSpeed(JSONObject point, String uid, String location)
	    {
	    	if(!shouldJugleForSpeed(uid))
	    	{
	    		return;				//未在监测冷却期			暂时停用！！
	    	}
	    	Double speed = point.getDouble("speed");
	    	if(speed == null) return;		//未检测到速度
	    	
	    	Integer lastModeNum = lastMode.get(uid);
	    	Integer thisModeNum = SpeedModel.jugleMode(speed);
	    	
	    	if(lastModeNum == null && thisModeNum <= 1)
	    	{
	    		lastMode.put(uid, thisModeNum);
	    		return;			//更新并退出
	    	}
	    	else if(lastModeNum != null && lastModeNum >= thisModeNum)   //当前方式小于等于上次的方式
	    	{
	    		lastMode.put(uid, thisModeNum);
	    		return;			//更新并退出
	    	}
	    	
	    	lastMode.put(uid, thisModeNum);				
	    	String nowMethod = SpeedModel.trafficMode(thisModeNum);   	//提醒消息
	    	
	//    	if(speed < maxSpeed) return;		//弃
	    	
	    	Map<String, Object> queryData = new HashMap();
	    	queryData.put("uid", uid);
	    	List<Map<String, String>> userImfors = null;
			try {
				userImfors = userImpl.queryUserImfor2(queryData);
			} catch (Exception e) {
				e.printStackTrace();
				return;		//直接返回
			}
	    	
	    	String[][] namesAndAddress = spiltNameAndAddress2(userImfors, uid);
	    	String time = point.getString("time");
	    	String chname = namesAndAddress[0][0];
	    	
	    	for(int i=1; i<namesAndAddress.length; i++)
	    	{
		    		String name = namesAndAddress[i][0];
		    		String address = namesAndAddress[i][1];
		    		Integer toUid = Integer.valueOf(namesAndAddress[i][2]);
		    		
		    		String geoLocation = LocationImfor.locationString(location);				//请求真实的地理编码位置  特注！！
		    		String content = buildSpeedContent(name, chname, geoLocation, speed, nowMethod, time);
		    		Map<String, String> email = new HashMap();
		    		email.put("title", speedTitle);
		    		email.put("content", content);
		    		email.put("to", address);
		    		
		    		RemindEmailTask.task.addRemindEmail(email);  
		    		
		    		
		    		Map<String, Object> data = new HashMap<>();
		    		data.put("content", content);
		    		data.put("type", 4);				//速度是4
		    		data.put("grade", 1);
		    		SysPromptTask.task.addSysPrompt(new SysPrompt(data, new Integer[] {toUid}));						//添加到系统消息提示线程
	    	}
	    	
	//    	Integer[] uids = joinParentIds(namesAndAddress);         //父端id，构造多个
			
	//		RemindEmailTask.task.addRemindEmail(map);  
	    }
	    
	    /**
	     * 
	     * @return
	     */
	    public Integer[] joinParentIds(String[][] parts)
	    {
	    	Integer[] uids = (parts.length > 1) ? new Integer[parts.length-1] : new Integer[0];
	    	for(int i =1; i<parts.length; i++)
	    	{
	    		uids[i-1] = Integer.valueOf(parts[i][2]);
	    	}
	    	return uids;
	    }
	    
	    
	    public String buildSpeedContent(String name, String chname, String location,  Double speed, String nowMethod, String time)
	    {
		    	StringBuilder builder = new StringBuilder()
		    			.append("尊敬的")
		    			.append(name)
		    			.append("\n您好")
		    			.append("\n您的孩童：")
		    			.append(chname)
		    			.append(" 于 ")
		    			.append(time)
		    			.append(" 速度达到了")
		    			.append(speed)
		    			.append(" m/s 当前可能正在：")
		    			.append(nowMethod)
		    			.append(" 位置：")
		    			.append(location)
		    			;
		    	return builder.toString();
	    }
	    
	    /**
	     * 对每个围栏进行判断  --多个
	     * @param key
	     * @param location
	     * @param fenceImfor
	     */
	    public void goForLocation(String key, String location, List<Map<String, String>> fenceImfor, String time)
	    {
	    	for(Map<String, String> fence : fenceImfor)
	    	{
	    		jugleForFence(key, location, fence, time);
	    		try {
	    			Thread.sleep(500);				//睡眠0.5s，避免高德qps并发失效
	    		}
	    		catch(Exception e)
	    		{
	    			e.printStackTrace();
	    		}
	    	}
	    }
	    
	    /**
	     * 判断围栏关系  --单个
	     * @param imfor
	     */
	    public void jugleForFence(String key, String location, Map<String, String> imfor, String time)
	    {
	    	Map<String, String> params = new HashMap<>();
	    	
	    	/////////////////////////   判断频率		////////////////////////
	    	
	    	String fdgfid = imfor.get("fdgfid");				//高德围栏id，并不是fence流水号
	    	
	    	if(!shouldJugle(fdgfid)) return;			//处于冷却期直接返回，如果应该修改，则自动测试    
	//    	lastJugle.put(fdgfid, System.currentTimeMillis());			//更新上次监测时间		-停   shoudJugle 自动更新
	    	
	    	////////////////////////    继续   /////////////////////
	    	
	    	params.put("key", key);
	    	params.put("location", location);
	    	params.put("sid", imfor.get("usid"));
	    	params.put("gfids", fdgfid);
	    	
	    	String fenceName = imfor.get("fdname");		//围栏名
	    	String chid = imfor.get("fdforuser");		//子端id
	    	String prid = imfor.get("fdcreater");		//父id
	    	
	    	JSONObject resp = client.getByRespJson(url, params);
	    	Integer errcode = resp.getInteger("errcode");
	    	
	    	
	    	if(errcode == null || errcode != 10000) return;			//请求错误直接返回
	    	
	    	JSONObject respdata = resp.getJSONObject("data");
	    	if(respdata.getInteger("count") == 0) return;				//没有数据直接返回
	    	
	    	JSONArray dataList = respdata.getJSONArray("results");	
	    	JSONObject relationImfor = dataList.getJSONObject(0);
	    	
	    	Integer isIn = relationImfor.getInteger("in");			//返回in 0：未在内， 1：在内    数据库表示字段  ： 0：危险区域    1： 安全区域
	    	
	    	
	//    	Integer isIn = 1;								//测试用 -------
	    	
	    	
	    	Integer type = Integer.valueOf(imfor.get("fdtype"));	//数据库对应字段
	    	
	    	String geoLocation = LocationImfor.locationString(location);				//请求真实的地理编码位置  特注！！
	    	
	    	//isin = 1  -> 在内       type = 0 危险区域
	    	if(isIn != type)			//警报
	    	{
	    		System.out.println("孩童预警！！");
	    		
	    		List<Map<String, String>> usersImfor = null;
	    		try {
					usersImfor = userImpl.queryUserImfor(imfor);
				} catch (Exception e) {
					e.printStackTrace();
	//				return;						//查询异常直接退出
				}
	    		
	    		String[] nameAndAddress = spiltNameAndAddress(usersImfor, prid, chid);
	    		String content = buildContent(nameAndAddress, geoLocation, fenceName, (isIn == 1) ? true : false, time);
	    		String emailAddress = nameAndAddress[2];
	    		
	    		Map<String, String> map = new HashMap<>();
	    		map.put("content", content);
	    		map.put("to", emailAddress);
	    		map.put("title", locationTitle);
	    		
	    		RemindEmailTask.task.addRemindEmail(map);  				//发送任务到邮件线程
	    		
	    		///////////////////////////    添加当前位置信息     地理编码    ///////////////////////////
	    		
	    		
	    		///////////////////////////    构造系统提醒消息      //////////////////////////
	    		
	    		Integer[] uids = {Integer.valueOf(prid)};		//父端id，当前只有一个
	    		Map<String, Object> data = new HashMap<>();
	    		data.put("content", content);
	    		data.put("type", 3);			//围栏是3
	    		data.put("grade", 1);
	    		
	    		SysPromptTask.task.addSysPrompt(new SysPrompt(data, uids));						//添加到系统消息提示线程
	    	}
	    }
	    
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    
	    
	    /**
	     * 判断当前是否应该进行围栏判断
	     * @param gfid
	     * @return
	     */
	    public boolean shouldJugle(String gfid)
	    {
		    	synchronized (lastJugle) {
			    		Long lasttime = lastJugle.get(gfid);
			        	long now = System.currentTimeMillis();
			        	if(lasttime == null)			//没有上次的记录说明需要监测
			        	{
				        		lastJugle.put(gfid, now);			//放入这次的结果
				        		return true;
			        	}
			        	if(lasttime + frequence > now)				//还处于冷却区
			        	{
			        			return false;
			        	}
			        	else
			        	{
				//        		lasttime = now;
				        		lastJugle.put(gfid, now);			// 注 ：： 要修改值！@！！@
				        		return true;
			        	}
				}
	    }
	    
	    /**
	     * 判断当前是否应该进行围栏判断
	     * @param gfid
	     * @return
	     */
	    public boolean shouldJugleForSpeed(String uid)
	    {
		    	synchronized (lastJugleForSpeed) 
		    	{
		    		Long lasttime = lastJugleForSpeed.get(uid);
		        	long now = System.currentTimeMillis();
		        	if(lasttime == null)			//没有上次的记录说明需要监测
		        	{
		        		lastJugleForSpeed.put(uid, now);			//放入这次的结果
		        		return true;
		        	}
		        	if(lasttime + speedfre > now)				//还处于冷却区
		        	{
		        		return false;
		        	}
		        	else
		        	{
		//        		lasttime = now;				//不是
		        		lastJugleForSpeed.put(uid, now);			//注意要放进去！！！！！
		        		return true;
		        	}
				}
	    }
	    
	    
	    /**
	     * 判断是否应该围栏判断
	     * 当前处于冷却期			//弃用!!!!!!!
	     * @param params
	     * @return
	     */
	    @Deprecated
	    public boolean shouldJugle2(Map<String, String> params)
	    {
		    	String lastJugleTime = params.get("fjulasttime");
		    	if(lastJugleTime == null) return true;			//没有监测，说明这次需要监测
		    	
		    	String frequency = params.get("ffrequency");
		    	
		    	try {
					Date a1 = format.parse(lastJugleTime);
					long a1Time = a1.getTime();
					long fre = Long.valueOf(frequency);
					long now = System.currentTimeMillis();
					
					if(a1Time + fre > now)
					{
						return false;
					}
					else
					{
						return true;
					}
				}
		    	catch(Exception e)
		    	{
		    		e.printStackTrace();
		    		return false;
		    	}
	    }
	    
	    
	    
	    /**
	     * 构建邮件格式
	     * @param names
	     * @param loactionImfor
	     * @param fenceName
	     * @param isIn
	     * @param time
	     * @return
	     */
	    public String buildContent(String[] names, String loactionImfor, String fenceName, boolean isIn, String time)
	    {
		    	StringBuilder builder = new StringBuilder()
		    			.append("尊敬的")
		    			.append(names[0])
		    			.append("\n您好")
		    			.append("\n您的孩童：")
		    			.append(names[1])
		    			.append(" 于 ")
		    			.append(time)
		    			.append(" ")
		    			.append(isIn ? "进入 " : "离开 ")
		    			.append(fenceName)
		    			.append(" 位置：")
		    			.append(loactionImfor)
		    			;
		    	return builder.toString();
	    }
	    
	    /**
	     * 父名在前，子名在后
	     * @param userImfor
	     * @return
	     */
	    public String[] spiltNameAndAddress(List<Map<String, String>> userImfor, String puid, String cuid)
	    {
		    	String[] nameAndAddress = new String[3];
		    	if(userImfor.size() != 2)
		    	{
		    		return nameAndAddress;
		    	}
		    	
		    	if(userImfor.get(0).get("uid").equals(puid))
		    	{
		    		nameAndAddress[0] = userImfor.get(0).get("uname");
		    		nameAndAddress[2] = userImfor.get(0).get("uemail");
		    	}
		    	if(userImfor.get(0).get("uid").equals(cuid))
		    	{
		    		nameAndAddress[1] = userImfor.get(0).get("uname");
		    	}
		    	if(userImfor.get(1).get("uid").equals(puid))
		    	{
		    		nameAndAddress[0] = userImfor.get(1).get("uname");
		    		nameAndAddress[2] = userImfor.get(1).get("uemail");
		    	}
		    	if(userImfor.get(1).get("uid").equals(cuid))
		    	{
		    		nameAndAddress[1] = userImfor.get(1).get("uname");
		    	}
		    	
		    	return nameAndAddress;
	    }
	    
	    public String[][] spiltNameAndAddress2(List<Map<String, String>> userImfor, String uid)
	    {
		    	String[][] nameAndAddress = new String[userImfor.size()][3];
		    	if(userImfor.size() < 2) return nameAndAddress;
		    	int uIndex = 1;
		    	for(Map<String, String> user : userImfor)
		    	{
		    		if(user.get("uid").equals(uid))
		    		{
		    			nameAndAddress[0][0] = user.get("uname");
		    		}
		    		else
		    		{
		    			nameAndAddress[uIndex][0] = user.get("uname");
		    			nameAndAddress[uIndex][1] = user.get("uemail");
		    			nameAndAddress[uIndex][2] = user.get("uid");
		    			uIndex++;
		    		}
		    	}
		    	
		    	return nameAndAddress;
	    }
	    
	//    public String buildContent(Map)
	    
	    
	    
	    
	    //////////////////////////////////////////////		后继速度预警实现 		////////////////////////////////////////////////////
	    
	    
	    
	    
	   /////////////////////////////////////////////////     后继区域实现策略         ////////////////////////////////////////////////////
	    
	    public Map<Integer, Integer> parseTypes(List<Map<String, String>> fenceImfor)
	    {
		    	Map<Integer, Integer> types = new HashMap<>();
		    	for(Map<String, String> fence : fenceImfor)
		    	{
		    		types.put(Integer.valueOf(fence.get("fdgfid")), Integer.valueOf(fence.get("fdtype")));
		    	}
		    	return types;
	    }
	    
	    
	    /**
	     * 数据格式：
	     map里面包含一个高德服务sid，以不同的sid组合，即每个监护人的不一样
	     	字段：
	     	sid:高德服务id
	     	
	     
	     
	     * @param fenceImfor
	     * @return
	     */
	    public List<Map<String, String>> joinBySid(List<Map<String, String>> fenceImfor)
	    {
		    	List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		    	Map<String, Integer> fenceForSid = null;
		    	for(Map<String, String> fence : fenceImfor)
		    	{
		    		fenceForSid = new HashMap();
		    	}
		    	
				return list;
	    }
	    
	    public void jugleForSid()
	    {
	    	
	    }
    
}
