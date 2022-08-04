package task.pool;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.copenedu.services.impl.map.FenceImpl;
import com.copenedu.services.impl.map.PolygonFenceImpl;
import com.copenedu.services.impl.map.UserImforImpl;

import gaude.DistanceUtil;
import gaude.GaudeServices;
import gaude.LocationImfor;
import gaude.PnPolyUtil;
import gaude.SpeedModel;
import my.entity.SysPrompt;
import task.part.PointJugdeTask;
import task.part.RemindEmailTask;
import task.part.SysPromptTask;

public class MutiPointTask extends Thread
{
//		private ThreadLocal<LinkedList<JSONObject>> pointBuffer = new ThreadLocal<LinkedList<JSONObject>>();
		private LinkedList<JSONObject> pointBuffer = new LinkedList<JSONObject>();
		private volatile boolean runFlag = true;
		private long timePause = 1000;
		
		private HashMap<Integer, Long> lastJugleForSpeed = new HashMap<>();	//速度冷却期		uid,lastjugletime(timelong)
		private HashMap<Integer, Integer> lastMode = new HashMap<>();		//上一次的模式
		
	    private long speedfre = 60000;     			//速度判断冷却1分钟		1000 * 60
		
	    private HashMap<Integer, Long> lastJugle = new HashMap<>();			//围栏冷却期
	    private HashMap<Integer, Long> lastPoJugle = new HashMap<>();		//多边形围栏冷却期
	    private long frequence = 60000;				//围栏判断冷却时间 1分钟   1000 * 60 
	    
	    private UserImforImpl userImpl = new UserImforImpl(null);			//用于查询用户邮箱等信息
	    private FenceImpl fenceImpl = new FenceImpl(null);						//用于查询圆形围栏等信息
	    private PolygonFenceImpl polygonFenceImpl = new PolygonFenceImpl(null);						//用于查询多变形围栏等信息
	    
	    private String locationTitle = "孩童位置预警";
	    private String speedTitle = "孩童速度预警";
		/**
		 * 
		 JSONObject point = new JSONObject();
		    point.put("latitude", location.getLatitude());
		    point.put("longitude", location.getLongitude());
		    point.put("speed", location.getSpeed());
		    point.put("time",format.format(new Date(listenTime)));
		    point.put("altitude", location.getAltitude());
		
		    JSONObject data = new JSONObject();
		    data.put("cmd", "uploadPoint");
		    data.put("uid", Constants.uid);
		    data.put("point", point);
		
		    PointUploadTask.task.addPoint(data);
		 * 
		 * 
		 */
	    
	    public static MutiPointTask task = new MutiPointTask();         //单例 -- 暂时支持单例
	    
	    public void pushPoint(JSONObject pointInfo)
	    {
	    		synchronized (pointBuffer)
				{
	    				pointBuffer.add(pointInfo);
				}
	    }
		
		@Override
		public void run() 
		{
				while(runFlag)
				{
//						LinkedList<JSONObject> buffer = pointBuffer.get();
						JSONObject pointInfo = pointBuffer.poll();				//弹出任务
						if(pointInfo != null)
						{
								Integer uid = pointInfo.getInteger("uid");
								JSONObject point = pointInfo.getJSONObject("point");
								/////////////////     速度判断       ///////////////
								goForSpeed(point, uid);
								
								/////////////////     围栏判断       ///////////////
								goForLocation(point, uid);
								
								////////////////      多边形围栏 		/////////////
								goForPolygonLocation(point, uid);
								
						}
						else
						{
								pauseAWhile();		//没有数据，暂停一会儿
						}
				}
		}
		
		
		//暂停一会
		private void pauseAWhile()
		{
				try 
				{
						Thread.sleep(timePause);
				} catch (InterruptedException e) {
						e.printStackTrace();
				}
		}
		
		/**
		 * 速度检测
		 * @param point
		 * @param uid
		 */
		private void goForSpeed(final JSONObject point,final Integer uid)
		{
				if(point == null || uid == null) return;
//				Integer uid = pointInfo.getInteger("uid");
		    	
//		    	JSONObject point = pointInfo.getJSONObject("point");
		    	Double speed = point.getDouble("speed");
		    	if(speed == null) return;		//未检测到速度
		    	
		    	Integer lastModeNum = lastMode.get(uid);
		    	Integer thisModeNum = SpeedModel.jugleMode(speed);
		    	
		    	//提醒模式为，步行不提示，高模式提示，低模式则更换模式
		    	if((lastModeNum == null || lastModeNum == 0) && thisModeNum <= 1)			//没有模式，或者上次为步行
		    	{
			    		lastMode.put(uid, thisModeNum);
			    		return;			//更新并退出
		    	}
		    	else if(lastModeNum != null && lastModeNum >= thisModeNum)   //当前方式小于等于上次的方式
		    	{
			    		lastMode.put(uid, thisModeNum);
			    		return;			//更新并退出
		    	}
		    	
		    	//判断是否在冷却期
		    	if(!shouldJugleForSpeed(uid)) return;				//未在监测冷却期			暂时停用！！
		    	
		    	lastMode.put(uid, thisModeNum);				
		    	String nowMethod = SpeedModel.trafficMode(thisModeNum);   	//提醒消息
		    	
		//    	if(speed < maxSpeed) return;		//弃！！！
		    	
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
		    	String time = point.getString("time");				//点上传的时间
		    	String chname = namesAndAddress[0][0];
		    	
		    	for(int i=1; i<namesAndAddress.length; i++)
		    	{
			    		String name = namesAndAddress[i][0];
			    		String address = namesAndAddress[i][1];
			    		Integer toUid = Integer.valueOf(namesAndAddress[i][2]);
			    		
			    		String geoLocation = LocationImfor.locationString(point.getDouble("longitude"), point.getDouble("latitude"));				//请求真实的地理编码位置  特注！！
			    		String content = buildSpeedContent(name, chname, geoLocation, speed, nowMethod, time);						//提示信息的内容
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
		}
		
		 /**
	     * 对每个多边形围栏进行判断  --多个	
	     * @param key
	     * @param location
	     * @param fenceImfor
	     */
	    private void goForPolygonLocation(final JSONObject point, final Integer uid)
	    {
	    		//判断是否应该进行围栏区域判断
	    		if(!shouldPolygenJugle(uid)) return;			//处于冷却期，直接返回
	    		
		    	double lng = point.getDoubleValue("longitude");
		    	double lat = point.getDoubleValue("latitude");
//		    	Integer uid = pointInfo
	    		//从数据库中获取圆形围栏数据
	    		List<Map<String, String>> fences = null;
				try
				{
						fences = polygonFenceImpl.query(uid);
				} catch (Exception e1)
				{
						e1.printStackTrace();
						return;		//查询失败直接返回
				}
	    		
	    		String geoLocation = null;			//地理位置
	    		String time = point.getString("time");			//点上传的时间
		    	
	    		for(Map<String, String> fence : fences)
	    		{
	    				int type = Integer.valueOf(fence.get("potype"));			//圆的类型		1,安全区， 0,危险区
	    				String pointStr = fence.get("popoints");
	    				JSONArray points = JSON.parseArray(pointStr);
	    				
	    				boolean isIn = PnPolyUtil.pnpoly(lng, lat, points);
	    				boolean isDanger = (type == 0); 						//是否危险区
	    				
	    				String fenceName = fence.get("poname");			//围栏名称
	    				
	    				//需要报警的情况
	    				if(isIn == isDanger)
	    				{
		    					System.out.println("孩童预警！！");
		    		    		
		    					Integer creater = Integer.valueOf(fence.get("pocreater"));
//		    					Integer forUser = Integer.valueOf(fence.get("poforuser"));
		    					
		    		    		List<Map<String, String>> usersImfor = null;
		    		    		try {
		    							usersImfor = userImpl.queryUserImfor(creater, uid);
		    					} catch (Exception e) {
		    							e.printStackTrace();
		    							//				return;						//查询异常直接退出
		    							continue;		//直接下一次循环
		    					}
		    		    		
		    		    		///////////////////////////    添加当前位置信息     地理编码    ///////////////////////////
		    		    		
		    		    		String[] nameAndAddress = spiltNameAndAddress(usersImfor, String.valueOf(creater), String.valueOf(uid));		
		    		    		geoLocation = (geoLocation == null) ? LocationImfor
		    		    				.locationString(lng, lat): geoLocation;				//地理位置没有，则从高德查询
		    		    				
		    		    		String content = buildContent(nameAndAddress, geoLocation, fenceName, isIn, time);			//预警消息内容
		    		    		
		    		    		//////////////////////////////      发送任务到邮件线程      //////////////////////////  
		    		    		
		    		    		String emailAddress = nameAndAddress[2];			//获取邮件地址
		    		    		
		    		    		Map<String, String> map = new HashMap<>();
		    		    		map.put("content", content);
		    		    		map.put("to", emailAddress);
		    		    		map.put("title", locationTitle);
		    		    		
		    		    		RemindEmailTask.task.addRemindEmail(map);  
		    		    		
		    		    		///////////////////////////    构造系统提醒消息      //////////////////////////
		    		    		
		    		    		Integer[] uids = {Integer.valueOf(creater)};		//父端id，当前只有一个
		    		    		Map<String, Object> data = new HashMap<>();
		    		    		data.put("content", content);
		    		    		data.put("type", 3);			//围栏是3
		    		    		data.put("grade", 1);
		    		    		
		    		    		SysPromptTask.task.addSysPrompt(new SysPrompt(data, uids));						//添加到系统消息提示线程
	    				}
	    		}
	    }
	    
	    
	    
	    
	    
	    /**
	     * 判断圆形围栏与点的位置
	     * @param point
	     * @param fence
	     * @return
	     */
	    public boolean isIn(final JSONObject point, final Map<String, String> fence)
	    {
	    		
	    		return true;
	    }
	    
	    
	    /**
	     * 对每个围栏进行判断  --多个
	     * @param key
	     * @param location
	     * @param fenceImfor
	     */
	    private void goForLocation(final JSONObject point, final Integer uid)
	    {
	    		//判断是否应该进行围栏区域判断
	    		if(!shouldJugle(uid)) return;			//处于冷却期，直接返回
	    		
		    	double lng = point.getDoubleValue("longitude");
		    	double lat = point.getDoubleValue("latitude");
//		    	Integer uid = pointInfo
	    		//从数据库中获取圆形围栏数据
	    		List<Map<String, String>> fences = null;
				try
				{
						fences = fenceImpl.queryFence(uid);
				} catch (Exception e1)
				{
						e1.printStackTrace();
						return;		//查询失败直接返回
				}
	    		
	    		String geoLocation = null;			//地理位置
	    		String time = point.getString("time");			//点上传的时间
		    	
	    		for(Map<String, String> fence : fences)
	    		{
	    				double lngCircle = Double.valueOf(fence.get("flng"));
	    				double latCircle = Double.valueOf(fence.get("flat"));
	    				int radiusCircle = Integer.valueOf(fence.get("fradius"));				//圆的半径
	    				int type = Integer.valueOf(fence.get("fdtype"));			//圆的类型		1,安全区， 0,危险区
	    				
	    				double distance = DistanceUtil.getDistanceMeter(lng, lat, lngCircle, latCircle);		//与圆心的距离
	    				boolean isIn = (distance <= radiusCircle);			//是否在圆内
	    				boolean isDanger = (type == 0); 						//是否危险区
	    				
	    				String fenceName = fence.get("fdname");			//围栏名称
	    				
	    				//需要报警的情况
	    				if(isIn == isDanger)
	    				{
		    					System.out.println("孩童预警！！");
		    		    		
		    					Integer creater = Integer.valueOf(fence.get("fdcreater"));
		    					Integer forUser = Integer.valueOf(fence.get("fdforuser"));
		    					
		    		    		List<Map<String, String>> usersImfor = null;
		    		    		try {
		    							usersImfor = userImpl.queryUserImfor(creater, forUser);
		    					} catch (Exception e) {
		    							e.printStackTrace();
		    							//				return;						//查询异常直接退出
		    							continue;		//直接下一次循环
		    					}
		    		    		
		    		    		///////////////////////////    添加当前位置信息     地理编码    ///////////////////////////
		    		    		
		    		    		String[] nameAndAddress = spiltNameAndAddress(usersImfor, String.valueOf(creater), String.valueOf(forUser));		
		    		    		geoLocation = (geoLocation == null) ? LocationImfor
		    		    				.locationString(lng, lat): geoLocation;				//地理位置没有，则从高德查询
		    		    				
		    		    		String content = buildContent(nameAndAddress, geoLocation, fenceName, isIn, time);			//预警消息内容
		    		    		
		    		    		//////////////////////////////      发送任务到邮件线程      //////////////////////////  
		    		    		
		    		    		String emailAddress = nameAndAddress[2];			//获取邮件地址
		    		    		
		    		    		Map<String, String> map = new HashMap<>();
		    		    		map.put("content", content);
		    		    		map.put("to", emailAddress);
		    		    		map.put("title", locationTitle);
		    		    		
		    		    		RemindEmailTask.task.addRemindEmail(map);  
		    		    		
		    		    		///////////////////////////    构造系统提醒消息      //////////////////////////
		    		    		
		    		    		Integer[] uids = {Integer.valueOf(creater)};		//父端id，当前只有一个
		    		    		Map<String, Object> data = new HashMap<>();
		    		    		data.put("content", content);
		    		    		data.put("type", 3);			//围栏是3
		    		    		data.put("grade", 1);
		    		    		
		    		    		SysPromptTask.task.addSysPrompt(new SysPrompt(data, uids));						//添加到系统消息提示线程
	    				}
	    		}
	    }
	    
	    
	    
	    /**
	     * 判断围栏关系  --单个
	     * @param imfor
	     
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
		*/
	    
	    /**
	     * 判断当前是否应该进行围栏判断
	     * @param uid 孩童id
	     * @return
	     */
	    public boolean shouldJugle(Integer uid)
	    {
		    	synchronized (lastJugle) {
			    		Long lasttime = lastJugle.get(uid);
			        	long now = System.currentTimeMillis();
			        	if(lasttime == null)			//没有上次的记录说明需要监测
			        	{
				        		lastJugle.put(uid, now);			//放入这次的结果
				        		return true;
			        	}
			        	if(lasttime + frequence > now)				//还处于冷却区
			        	{
			        			return false;
			        	}
			        	else
			        	{
				        		lastJugle.put(uid, now);			// 注 ：： 要修改值！@！！@
				        		return true;
			        	}
				}
	    }
	    
	    /**
	     * 判断当前是否应该进行多边形围栏判断
	     * @param uid 孩童id
	     * @return
	     */
	    public boolean shouldPolygenJugle(Integer uid)
	    {
		    	synchronized (lastPoJugle) {
			    		Long lasttime = lastPoJugle.get(uid);
			        	long now = System.currentTimeMillis();
			        	if(lasttime == null)			//没有上次的记录说明需要监测
			        	{
			        			lastPoJugle.put(uid, now);			//放入这次的结果
				        		return true;
			        	}
			        	if(lasttime + frequence > now)				//还处于冷却区
			        	{
			        			return false;
			        	}
			        	else
			        	{
			        			lastPoJugle.put(uid, now);			// 注 ：： 要修改值！@！！@
				        		return true;
			        	}
				}
	    }
	    
		/**
	     * 判断当前是否应该进行围栏判断
	     * @param gfid
	     * @return
	     */
	    private boolean shouldJugleForSpeed(final Integer uid)
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
	    
	    public String[][] spiltNameAndAddress2(List<Map<String, String>> userImfor, Integer uid)
	    {
		    	String[][] nameAndAddress = new String[userImfor.size()][3];
		    	if(userImfor.size() < 2) return nameAndAddress;
		    	int uIndex = 1;
		    	String uidStr = String.valueOf(uid);
		    	for(Map<String, String> user : userImfor)
		    	{
			    		if(user.get("uid").equals(uidStr))
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
}
