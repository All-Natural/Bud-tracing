package gaude;

public class DistanceUtil 
{
		//地球半径，单位（米）
		private static final double GLOABLE_RADIUS = 6378.137 * 1000; 		
		
		/**
		 * 计算
		 * @param distance
		 * @return
		 */
		private static double getRad(double distance) 
		{
				return distance*Math.PI/180.0;
		}
		
		/**
		 * 获得两坐标点之间的距离:单位 (米)
		 * @param lnga
		 * @param lata
		 * @param lngb
		 * @param latb
		 * @return
		 */
		public static double getDistanceMeter(double lnga, double lata, double lngb, double latb) 
		{
				double n, m, result, sa2, sb2;
				lata = getRad(lata);
				latb = getRad(latb);
				n = lata - latb;
				m = getRad(lnga-lngb);
				
				sa2 = Math.sin(n/2.0);
				sb2 = Math.asin(m/2.0);
				result = 2 * GLOABLE_RADIUS
						   * Math.asin(Math.sqrt(sa2 * sa2+Math.cos(lata)
						   * Math.cos(latb) * sb2 * sb2));
				
				return result;   
		}
		
		/**
		 * 获得两坐标点之间的距离:单位 (千米)
		 * @param lnga
		 * @param lata
		 * @param lngb
		 * @param latb
		 * @return
		 */
		public static double getDistanceKMeter(double longa, double lata, double longb, double latb) 
		{
				return getDistanceMeter(longa, lata, longb, latb) / 1000;
		}
		
		public static void main(String[] args) 
		{
				double long1 = 106.605512;
				double lat1 = 29.524902;
				double long2 = 106.60571;
				double lat2 = 29.526746;
				
				System.out.println(getDistanceMeter(long1, lat1, long2, lat2));
		}
}
