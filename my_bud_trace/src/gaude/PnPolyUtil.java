/**
 * 
 */
package gaude;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

/**
 * @author xianzhikun
 *
 */
public class PnPolyUtil
{
		public static boolean pnpoly(int nvert, double[] vertx, double[] verty, double testx, double testy)
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
		
		public static boolean pnpoly(String location , JSONArray points)
		{
				String[] strs = location.split(",");
				double lng = Double.valueOf(strs[0]);
				double lat = Double.valueOf(strs[1]);
				return pnpoly(lng, lat, points);
		}
		
		public static boolean pnpoly(double lng, double lat, JSONArray points)
		{	
				boolean c = false;
				int size = points.size();
				int i, j = 0;
				for (i = 0, j = size - 1; i < size; j = i++)
				{
						double vertxI = points.getJSONArray(i).getDouble(0);
						double vertyI = points.getJSONArray(i).getDouble(1);
						double vertxJ = points.getJSONArray(j).getDouble(0);
						double vertyJ = points.getJSONArray(j).getDouble(1);
						if (((vertyI > lat) != (vertyJ > lat))
									&& (lng < (vertxJ - vertxI) * (lat - vertyI) / (vertyJ - vertyI) + vertxI))
								c = !c;
				}
				return c;
		}
		
//		public static boolean pnpoly(String location, JSONArray points)
//		{
//				String[] strs = location.split(",");
//				double lng = Double.valueOf(strs[0]);
//				double lat = Double.valueOf(strs[1]);
//				
//				int size = points.size();
//				double[] vertx = new double[size];
//				double[] verty = new double[size];
//				
//				for(int i=0; i<size; i++)
//				{
//					vertx[i] = points.getJSONArray(i).getDoubleValue(0);	
//					verty[i] = points.getJSONArray(i).getDoubleValue(1);	
//				}
//				
//				return pnpoly(size, vertx, verty, lng, lat);
//		}	
		
		public static void main(String[] args)
		{
			String points = "[[106.6116,29.53429],[106.612536,29.532796],[106.612564,29.531797],[106.612013,29.530835],[106.611233,29.529471],[106.610893,29.527539],[106.607634,29.526763],[106.608327,29.523607],[106.60681,29.523566],[106.605666,29.523629],[106.60518,29.523941],[106.604336,29.524022],[106.604479,29.525816],[106.604751,29.52649],[106.604443,29.527173],[106.604429,29.528178],[106.603317,29.52825],[106.603383,29.529192],[106.60302,29.530115],[106.602894,29.530707],[106.603305,29.531411],[106.603913,29.532875],[106.604724,29.534442],[106.60455,29.53586],[106.606824,29.536027],[106.607617,29.536839],[106.610642,29.536554],[106.611994,29.535672]]";
			String location = "106.603131,29.523119";
			boolean isIn = pnpoly(location, JSON.parseArray(points));
			System.out.println(isIn ? "在內" : "不在內");
		}
}
