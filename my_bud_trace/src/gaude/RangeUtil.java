/**
 * 
 */
package gaude;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

/**
 * @author xianzhikun
 *
 */
public class RangeUtil
{
	/**
	 * 是否有 横断（横断的意思就是 4(n)个点的8(n*2)个坐标
	 * 的8(n*2)个数字(每个点都有横纵坐标)只要有任意两个数字(横和横比较纵和纵比较)相等，就是横断的意思） 参数为四个点的坐标
	 *
	 * @param px1
	 * @param py1
	 * @param px2
	 * @param py2
	 * @param px3
	 * @param py3
	 * @param px4
	 * @param py4
	 * @return
	 */
	public boolean isIntersect(double px1, double py1, double px2, double py2, double px3, double py3, double px4,
			double py4)
	{
		boolean flag = false;
		double d = (px2 - px1) * (py4 - py3) - (py2 - py1) * (px4 - px3);
		if (d != 0)
		{
			double r = ((py1 - py3) * (px4 - px3) - (px1 - px3) * (py4 - py3)) / d;
			double s = ((py1 - py3) * (px2 - px1) - (px1 - px3) * (py2 - py1)) / d;
			if ((r >= 0) && (r <= 1) && (s >= 0) && (s <= 1))
			{
				flag = true;
			}
		}
		return flag;
	}

	/**
	 * 目标点是否在目标区域边上
	 *
	 * @param px0 目标点的经度坐标
	 * @param py0 目标点的纬度坐标
	 * @param px1 目标线的起点(终点)经度坐标
	 * @param py1 目标线的起点(终点)纬度坐标
	 * @param px2 目标线的终点(起点)经度坐标
	 * @param py2 目标线的终点(起点)纬度坐标
	 * @return
	 */
	public boolean isPointOnLine(double px0, double py0, double px1, double py1, double px2, double py2)
	{
		boolean flag = false;
		double ESP = 1e-9;// 无限小的正数
		if ((Math.abs(Multiply(px0, py0, px1, py1, px2, py2)) < ESP) && ((px0 - px1) * (px0 - px2) <= 0)
				&& ((py0 - py1) * (py0 - py2) <= 0))
		{
			flag = true;
		}
		return flag;
	}

	public double Multiply(double px0, double py0, double px1, double py1, double px2, double py2)
	{
		return ((px1 - px0) * (py2 - py0) - (px2 - px0) * (py1 - py0));
	}

	/**
	 * 判断目标点是否在多边形内(由多个点组成)
	 *
	 * @param px        目标点的经度坐标
	 * @param py        目标点的纬度坐标
	 * @param polygonXA 多边形的经度坐标集合
	 * @param polygonYA 多边形的纬度坐标集合
	 * @return
	 */
	public boolean inPointInPolygon(double px, double py, ArrayList<Double> polygonXA, ArrayList<Double> polygonYA)
	{
		boolean isInside = false;
		double ESP = 1e-9;
		int count = 0;
		double linePoint1x;
		double linePoint1y;
		double linePoint2x = 180;
		double linePoint2y;

		linePoint1x = px;
		linePoint1y = py;
		linePoint2y = py;

		for (int i = 0; i < polygonXA.size() - 1; i++)
		{
			double cx1 = polygonXA.get(i);
			double cy1 = polygonYA.get(i);
			double cx2 = polygonXA.get(i + 1);
			double cy2 = polygonYA.get(i + 1);
			// 如果目标点在任何一条线上
			if (isPointOnLine(px, py, cx1, cy1, cx2, cy2))
			{
				return true;
			}
			// 如果线段的长度无限小(趋于零)那么这两点实际是重合的，不足以构成一条线段
			if (Math.abs(cy2 - cy1) < ESP)
			{
				continue;
			}
			// 第一个点是否在以目标点为基础衍生的平行纬度线
			if (isPointOnLine(cx1, cy1, linePoint1x, linePoint1y, linePoint2x, linePoint2y))
			{
				// 第二个点在第一个的下方,靠近赤道纬度为零(最小纬度)
				if (cy1 > cy2)
					count++;
			}
			// 第二个点是否在以目标点为基础衍生的平行纬度线
			else if (isPointOnLine(cx2, cy2, linePoint1x, linePoint1y, linePoint2x, linePoint2y))
			{
				// 第二个点在第一个的上方,靠近极点(南极或北极)纬度为90(最大纬度)
				if (cy2 > cy1)
					count++;
			}
			// 由两点组成的线段是否和以目标点为基础衍生的平行纬度线相交
			else if (isIntersect(cx1, cy1, cx2, cy2, linePoint1x, linePoint1y, linePoint2x, linePoint2y))
			{
				count++;
			}
		}
		if (count % 2 == 1)
		{
			isInside = true;
		}
		return isInside;
	}

	public boolean inPointInPolygon(String location, JSONArray points)
	{
		String[] strs = location.split(",");
		double lng = Double.valueOf(strs[0]);
		double lat = Double.valueOf(strs[1]);
		return inPointInPolygon(lng, lat, points);
	}

	public boolean inPointInPolygon2(String location, JSONArray points)
	{
		String[] strs = location.split(",");
		double lng = Double.valueOf(strs[0]);
		double lat = Double.valueOf(strs[1]);
		return inPointInPolygon(lng, lat, points);
	}

	public boolean inPointInPolygon2(double lng, double lat, JSONArray points)
	{
		ArrayList<Double> polygonXA = new ArrayList<Double>();
		ArrayList<Double> polygonYA = new ArrayList<Double>();

		for (JSONArray point : (JSONArray[]) points.toArray())
		{
			polygonXA.add(point.getDouble(0));
			polygonYA.add(point.getDouble(1));
		}

		return inPointInPolygon(lng, lat, polygonXA, polygonYA);
	}

	public boolean inPointInPolygon(double lng, double lat, JSONArray points)
	{
		boolean isInside = false;
		double ESP = 1e-9;
		int count = 0;
		double linePoint1x;
		double linePoint1y;
		double linePoint2x = 180;
		double linePoint2y;

		linePoint1x = lng;
		linePoint1y = lat;
		linePoint2y = lat;

		for (int i = 0; i < points.size() - 1; i++)
		{
			double cx1 = points.getJSONArray(i).getDoubleValue(0);
			double cy1 = points.getJSONArray(i).getDoubleValue(1);
			double cx2 = points.getJSONArray(i + 1).getDoubleValue(0);
			double cy2 = points.getJSONArray(i + 1).getDoubleValue(1);
			// 如果目标点在任何一条线上
			if (isPointOnLine(lng, lat, cx1, cy1, cx2, cy2))
			{
				return true;
			}
			// 如果线段的长度无限小(趋于零)那么这两点实际是重合的，不足以构成一条线段
			if (Math.abs(cy2 - cy1) < ESP)
			{
				continue;
			}
			// 第一个点是否在以目标点为基础衍生的平行纬度线
			if (isPointOnLine(cx1, cy1, linePoint1x, linePoint1y, linePoint2x, linePoint2y))
			{
				// 第二个点在第一个的下方,靠近赤道纬度为零(最小纬度)
				if (cy1 > cy2)
					count++;
			}
			// 第二个点是否在以目标点为基础衍生的平行纬度线
			else if (isPointOnLine(cx2, cy2, linePoint1x, linePoint1y, linePoint2x, linePoint2y))
			{
				// 第二个点在第一个的上方,靠近极点(南极或北极)纬度为90(最大纬度)
				if (cy2 > cy1)
					count++;
			}
			// 由两点组成的线段是否和以目标点为基础衍生的平行纬度线相交
			else if (isIntersect(cx1, cy1, cx2, cy2, linePoint1x, linePoint1y, linePoint2x, linePoint2y))
			{
				count++;
			}
		}
		if (count % 2 == 1)
		{
			isInside = true;
		}
		return isInside;
	}

	/**
	 * 传入某个坐标判断是否在指定区域（这里使用4个点做区域范围，此算法可以是5个点，6个点，甚至更多！）
	 */
	private static Boolean isPointInPolygon(double px, double py)
	{
		/**
		 * 经度x,纬度y
		 * 通常读法是读作“经纬度”（经度在前，纬度在后；但一般书写是纬度在前，经度在后，也有人带上单位把经度写在前面，总之这里哪个是x，哪个是y，自己注意一下对应好，千万别弄错！自己就中过坑！！）
		 * 【重点：点的顺序必须顺时钟或者逆时钟添加，不可随便排列！！否则当点数多了的时候，同样的点可能出现不同的形状，导致定位出错！】
		 */
		Map<String, Double> map1 = new HashMap<String, Double>();// 左下
		map1.put("px", 113.897051);
		map1.put("py", 22.301719);
		Map<String, Double> map2 = new HashMap<String, Double>();// 左上
		map2.put("px", 113.897051);
		map2.put("py", 22.32022);
		Map<String, Double> map3 = new HashMap<String, Double>();// 右上
		map3.put("px", 113.94599);
		map3.put("py", 22.32022);
		Map<String, Double> map4 = new HashMap<String, Double>();// 右下
		map4.put("px", 113.94599);
		map4.put("py", 22.301719);
		Map<String, Double> map5 = new HashMap<String, Double>();// 可以更多的点……
		map5.put("px", 113.9273643);
		map5.put("py", 22.3129196);

		// 组成多边形!!
		List<Map<String, Double>> areas = new ArrayList<Map<String, Double>>();
		areas.add(map1);
		areas.add(map2);
		areas.add(map3);
		areas.add(map4);
		areas.add(map5);
		// areas.add(map5);

		ArrayList<Double> polygonXA = new ArrayList<Double>();
		ArrayList<Double> polygonYA = new ArrayList<Double>();
		for (int i = 0; i < areas.size(); i++)
		{
			Map<String, Double> map = areas.get(i);
			polygonXA.add(map.get("px"));
			polygonYA.add(map.get("py"));
		}

		RangeUtil otg = new RangeUtil();

		// true在区域内，false不在区域内
		Boolean flag = otg.inPointInPolygon(px, py, polygonXA, polygonYA);

		// ==↓↓↓下面这段只是展示作用，其实到上面的flag标记就可以判断结果了！↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓======================
		StringBuffer buffer = new StringBuffer();
		buffer.append("目标点").append("(").append(px).append(",").append(py).append(")").append("\n");
		buffer.append(flag ? "在" : "不在").append("\t").append("由\n");
		for (int i = 0; i < areas.size(); i++)
		{
			Map<String, Double> map = areas.get(i);
			Double x = map.get("px");
			Double y = map.get("py");

			StringBuffer bufferr = new StringBuffer();
			String string = bufferr.append("(").append(x).append(",").append(y).append(")").toString();

			buffer.append(string).append("; ");
		}
		System.out.println("结果为：" + flag);
		buffer.append("\n" + areas.size()).append("个点组成的").append(areas.size()).append("边行内");
		System.out.println(buffer.toString());
		// ==↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑======================

		return flag;
	}

	public boolean pnpoly(int nvert, double[] vertx, double[] verty, double testx, double testy)
	{
		boolean c = false;
		int i, j = 0;
		for (i = 0, j = nvert - 1; i < nvert; j = i++)
		{
			if (((verty[i] > testy) != (verty[j] > testy))
					&& (testx < (vertx[j] - vertx[i]) * (testy - verty[i]) / (verty[j] - verty[i]) + vertx[i]))
				c = !c;
		}
		return c;
	}
	
	public boolean pnpoly(String location, JSONArray points)
	{
		String[] strs = location.split(",");
		double lng = Double.valueOf(strs[0]);
		double lat = Double.valueOf(strs[1]);
		
		int size = points.size();
		double[] vertx = new double[size];
		double[] verty = new double[size];
		
		for(int i=0; i<size; i++)
		{
			vertx[i] = points.getJSONArray(i).getDoubleValue(0);	
			verty[i] = points.getJSONArray(i).getDoubleValue(1);	
		}
		
		return pnpoly(size, vertx, verty, lng, lat);
	}

	// 测试方法
	public static void main(String[] args)
	{
//				isPointInPolygon(113.9079236, 22.3075597);
//				System.out.printf("%11.9f",1e-9);
		String points = "[[106.6116,29.53429],[106.612536,29.532796],[106.612564,29.531797],[106.612013,29.530835],[106.611233,29.529471],[106.610893,29.527539],[106.607634,29.526763],[106.608327,29.523607],[106.60681,29.523566],[106.605666,29.523629],[106.60518,29.523941],[106.604336,29.524022],[106.604479,29.525816],[106.604751,29.52649],[106.604443,29.527173],[106.604429,29.528178],[106.603317,29.52825],[106.603383,29.529192],[106.60302,29.530115],[106.602894,29.530707],[106.603305,29.531411],[106.603913,29.532875],[106.604724,29.534442],[106.60455,29.53586],[106.606824,29.536027],[106.607617,29.536839],[106.610642,29.536554],[106.611994,29.535672]]";
//				String points = "[\r\n" + 
//						"    [\r\n" + 
//						"        106.534661,\r\n" + 
//						"        29.579218\r\n" + 
//						"    ],\r\n" + 
//						"    [\r\n" + 
//						"        106.535041,\r\n" + 
//						"        29.578538\r\n" + 
//						"    ],\r\n" + 
//						"    [\r\n" + 
//						"        106.534948,\r\n" + 
//						"        29.578271\r\n" + 
//						"    ],\r\n" + 
//						"    [\r\n" + 
//						"        106.534577,\r\n" + 
//						"        29.577993\r\n" + 
//						"    ],\r\n" + 
//						"    [\r\n" + 
//						"        106.533932,\r\n" + 
//						"        29.57726\r\n" + 
//						"    ],\r\n" + 
//						"    [\r\n" + 
//						"        106.533127,\r\n" + 
//						"        29.57661\r\n" + 
//						"    ],\r\n" + 
//						"    [\r\n" + 
//						"        106.532715,\r\n" + 
//						"        29.576915\r\n" + 
//						"    ],\r\n" + 
//						"    [\r\n" + 
//						"        106.532303,\r\n" + 
//						"        29.57734\r\n" + 
//						"    ],\r\n" + 
//						"    [\r\n" + 
//						"        106.532483,\r\n" + 
//						"        29.577713\r\n" + 
//						"    ],\r\n" + 
//						"    [\r\n" + 
//						"        106.533328,\r\n" + 
//						"        29.578478\r\n" + 
//						"    ],\r\n" + 
//						"    [\r\n" + 
//						"        106.534341,\r\n" + 
//						"        29.57929\r\n" + 
//						"    ]\r\n" + 
//						"]";

		// 106.602418,29.535184
		RangeUtil util = new RangeUtil();
		boolean isIn = util.pnpoly("106.603131,29.523119", JSON.parseArray(points));
		System.out.println(isIn ? "在内" : "不在内");
	}
}
