package gaude;

/**
 * 根据速度划分模式
 * @author Not a Literary Gaint
 *
 */
public class SpeedModel 
{
	private final int noMove = 0;		//不动 0 
	private final int walk = 1;			//走路
	private final int bike = 2;			//自行车
	private final int moter = 3;        //电瓶车、摩托车
	private final int car = 4;			//汽车
	//公交车
	
	private final int train = 5;		//火车
	private final int airplane = 6;		//飞机
	
	private final static String invalid = "不合法";		//飞机
	private final static String noMoveStr = "静止";		//不动 0 
	private final static String walkStr = "走路";			//走路
	private final static String bikeStr = "骑自行车";			//自行车
	private final static String moterStr = "骑摩托车";        //电瓶车、摩托车
	private final static String carStr = "乘坐汽车";			//汽车
	//公交车
	
	private final static String trainStr = "乘坐火车";		//火车
	private final static String airplaneStr = "乘坐飞机";		//飞机
	private final static String nofind = "未知速度";		//飞机
	
	public static int jugleMode(double speed)
	{
		if(speed < 0)
		{
			return -1;
		}
		else if(speed == 0)
		{
			return 0;
		}
		else if(0 < speed && speed <= 3)			//2.77777 ~ 10km
		{
			return 1;
		}
		else if(3 < speed && speed <= 8)			//8.33333 ~ 30km
		{
			return 2;
		}
		else if(8 < speed && speed <= 14)				//13.88888 ~ 50km
		{
			return 3;
		}
		else if(14 < speed && speed <= 28)				//27.7777  ~ 100km
		{
			return 4;
		}
		else if(28 < speed && speed <= 167)				//166.666 ~ 600km
		{
			return 5;
		}
//		else if(167 < speed && speed <= )
//		{
//			return 6;
//		}
		else 
		{
			return 6;
		}
	}
	
	
	public static String trafficMode(int mode)
	{
		if(mode < 0)
		{
			return invalid;
		}
		else if(mode == 0)
		{
			return noMoveStr;
		}
		else if(mode == 1)
		{
			return walkStr;
		}
		else if(mode == 2)
		{
			return bikeStr;
		}
		else if(mode == 3)
		{
			return moterStr;
		}
		else if(mode == 4)
		{
			return carStr;
		}
		else if(mode == 5)
		{
			return trainStr;
		}
		else if(mode == 6)
		{
			return airplaneStr;
		}
		else
		{
			return nofind;
		}
	}
	
}
