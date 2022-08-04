package com.copenedu.system.tools;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.mywq.util.LabelValueBean;

import com.copenedu.system.db.DBUtils;


/**
 * �Ͳ���,�߹���Ĺ����Է���
 * @author wangxg
 *
 */
public final class Tools 
{
	private Tools() {}
//	
	public static void main(String[] args)
	{
//		try
//		{
//			System.out.println(Tools.MD5Encode("0000"));
			
//			System.out.println(Tools.getMd5Code("0000"));
			
//			int val=10;
//			String strCode=String.format("%06d", val);
//			System.out.println(strCode);
//			
//			LocalDate ld=LocalDate.now();
//			LocalDateTime ldt=LocalDateTime.now();
//			Instant ins=Instant.now();
			
//			DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyyMMdd");
//			//String date=ld.format(dtf);
//			//System.out.println(date);
			
//			System.out.println(ld);
//			System.out.println(ldt);
//			System.out.println(ins);
		

//				int eid=Tools.getSequence("eid");
//				System.out.println(eid);
			
			
//			//��������
//			String pwd="0000";  
//			//�����Ľ��е�һ�μ���
//			String md5pwd1=Tools.MD5Encode(pwd);
//			//���ɻ�������
//			String pwd2=md5pwd1+"�w�ͧ��z����Ⱥ��t,���^˪��������ѩ��,���˨ܨިϨȨ�׷��¤�ե������ȥ,��ع�����,"+md5pwd1;
//			//���ɻ�������
//			String md5pwd2=Tools.MD5Encode(pwd2);
//			
//			
//			System.out.println(md5pwd1);
//			System.out.println(md5pwd1.length());
//			
//			System.out.println(md5pwd2);
//			System.out.println(md5pwd2.length());
			
			try {
				System.out.println(Tools.getMd5("111111"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			
////			System.out.println(Tools.getSysUserType());
//			
//		}
//		catch(Exception ex)
//		{
//			ex.printStackTrace();
//		}
	}
//	
	public static List<LabelValueBean> getOptions(String fname)throws Exception
	{
		return Tools.getOptions(fname, "0");
	}
	
	/**
	 * ��ȡϵͳ�û����
	 * @return
	 * @throws Exception
	 */
	public static List<LabelValueBean> getOptions(String fname,String pfcode)throws Exception
	{
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try
		{
			//1.����SQL���
			StringBuilder sql=new StringBuilder()
			.append("select x.fvalue,x.fcode")
			.append("  from syscode x")
			.append(" where x.isv=?")
			.append("   and x.pfcode=?")
			.append("   and x.fname=?")
			;
		    pstm=DBUtils.prepareStatement(sql.toString());
		    pstm.setObject(1, "1");
		    pstm.setObject(2, pfcode);
		    pstm.setObject(3, fname);
			rs=pstm.executeQuery();
		    
		    List<LabelValueBean> opts=new ArrayList<>();
		    LabelValueBean bean=null;  
		    while(rs.next())
		    {
		    	//��װ��ǰ������
		    	bean=new  LabelValueBean(rs.getString(1), rs.getString(2));
		    	opts.add(bean);
		    }
		    return opts;
		}
		finally
		{
			DBUtils.close(rs);
			DBUtils.close(pstm);
		}
	}

	
	/**
	 * ��ȡϵͳ��ģ���б�
	 * @return
	 * @throws Exception
	 */
	public static List<LabelValueBean> getSysModule()throws Exception
	{
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try
		{
			String sql="select distinct x.module from sysmenu x";
			pstm=DBUtils.prepareStatement(sql);
			rs=pstm.executeQuery();
			List<LabelValueBean> opts=new ArrayList<>();
			
			LabelValueBean bean=null;
			
			while(rs.next())
			{
				bean=new LabelValueBean(rs.getString(1), rs.getString(1));
				opts.add(bean);
			}
			bean=new LabelValueBean("�µ�ģ��","");
			opts.add(bean);
			return opts;
					
		}
		finally
		{
			DBUtils.close(rs);
			DBUtils.close(pstm);
		}
	}

	/**
	 * ��ȡϵͳ�û����
	 * @return
	 * @throws Exception
	 */
	public static List<LabelValueBean> getSysUserType()throws Exception
	{
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try
		{
			//1.����SQL���
			StringBuilder sql=new StringBuilder()
					 .append("select x.fvalue,x.fcode ")
					 .append("  from syscode x")
					 .append(" where x.isv=?")
					 .append("   and x.fname=?")
					;
		    pstm=DBUtils.prepareStatement(sql.toString());
		    pstm.setObject(1, "1");        //�ɼ�����
		    pstm.setObject(2, "systype");  //�û����
		    rs=pstm.executeQuery();
		    
		    List<LabelValueBean> opts=new ArrayList<>();
		    LabelValueBean bean=null;  
		    while(rs.next())
		    {
		    	//��װ��ǰ������
		    	bean=new  LabelValueBean(rs.getString(1), rs.getString(2));
		    	opts.add(bean);
		    }
		    return opts;
		}
		finally
		{
			DBUtils.close(rs);
			DBUtils.close(pstm);
		}
	}
	
	
	//******************Begin  MD5******************	
	public final static String initPwd="d3c70526aff576899584e95babfb4085";
	
	public static String getMd5(String pwd) throws Exception
	{
		//�����Ľ��е�һ�μ���
		String md5pwd1=Tools.MD5Encode(pwd);
		System.out.println("md5pwd1="+md5pwd1);
		//���ɻ�������
		String pwd2=md5pwd1+"�w�ͧ��z����Ⱥ��t,���^˪��������ѩ��,���˨ܨިϨȨ�׷��¤�ե������ȥ,��ع�����,"+md5pwd1;
		//���ɻ�������
		String md5pwd2=Tools.MD5Encode(pwd2);
		return md5pwd2;
		
	}
	
	
	/**
	 * MD5����������
	 * 1.�������:���Դ�����ͨ������,��������,���ǲ����Դ����Ļ�ԭ������--��������
	 * 2.ͳһ���ַ���,���ۼ��ܶ��ٴ�,�õ�������ʼ������ͬ
	 */
	 private final static String[] hexDigits = {
	        "0", "1", "2", "3", "4", "5", "6", "7",
	        "8", "9", "a", "b", "c", "d", "e", "f"
	     };

	  /**
	   * ת���ֽ�����Ϊ16�����ִ�
	   * @param b �ֽ�����
	   * @return 16�����ִ�
	   */
	  private static String byteArrayToHexString(byte[] b)
	  {
	      StringBuffer resultSb = new StringBuffer();
	      for (int i = 0; i < b.length; i++)
	      {
	         resultSb.append(byteToHexString(b[i]));
	      }
	      return resultSb.toString();
	  }
	  /**
	   * ת���ֽ�Ϊ16�����ַ���
	   * @param b byte
	   * @return String
	   */
	  private static String byteToHexString(byte b)
	  {
	      int n = b;
	      if (n < 0)
	         n = 256 + n;
	      int d1 = n / 16;
	      int d2 = n % 16;
	      return hexDigits[d1] + hexDigits[d2];
	  }
	  /**
	   * �õ�MD5����������
	   * @param origin String
	   * @throws Exception
	   * @return String
	   */
	  private static String MD5Encode(Object origin) throws Exception
	  {
	       String resultString = null;
	       try
	       {
	           resultString=new String(origin.toString());
	           MessageDigest md = MessageDigest.getInstance("MD5");
	           resultString=byteArrayToHexString(md.digest(resultString.getBytes()));
	           return resultString;
	       }
	       catch (Exception ex)
	       {
	          throw ex;
	       }
	  }	
	//******************END  MD5******************	  



	private final static int MATCH_SCALE=2;         //��������Ĭ��С��λ��
	/**
	 * �����ĸ�����Ϊ����ת������
	 * @param dol double
	 * @param scale int
	 * @return String
	 */
	public static double ObjToDouble(Object dol, int scale)
	{
		  return Tools.ObjectToBigDecimal(dol, scale).doubleValue();
	}
	public static double ObjToDouble(Object dol)
	{
	   return Tools.ObjToDouble(dol, MATCH_SCALE);	
	}
	
	public static String DoubleToStr(double dol, int scale)
	{
	    return Tools.ObjectToBigDecimal(dol, scale).toString();
	}
	public static String DoubleToStr(double dol)
	{
	    return Tools.DoubleToStr(dol, MATCH_SCALE);
	}

	public static double DoubleToDouble(double dol, int scale)
	{
	    return Tools.ObjectToBigDecimal(dol, scale).doubleValue();
	}
	public static double DoubleToDouble(double dol)
	{
	    return Tools.DoubleToDouble(dol,  MATCH_SCALE);
	}

	public static double StrToDouble(String dol, int scale)
	{
	    return Tools.ObjectToBigDecimal(dol, scale).doubleValue();
	}
	public static double StrToDouble(String dol)
	{
	    return Tools.StrToDouble(dol, MATCH_SCALE);
	}
	public static String StrToStr(String dol, int scale)
	{
	   return Tools.ObjectToBigDecimal(dol, scale).toString();
	}
	public static String StrToStr(String dol)
	{
	   return Tools.StrToStr(dol,MATCH_SCALE);
	}

	@SuppressWarnings("deprecation")
	private static BigDecimal ObjectToBigDecimal(Object dol,int scale)
	{
		if(dol==null || dol.equals(""))
		{
			return new BigDecimal(0);
		}
		
		BigDecimal decimal = new BigDecimal(dol.toString());
		decimal = decimal.setScale(scale, BigDecimal.ROUND_HALF_UP);
		return decimal;
	}

	
	/**
	 * ���ַ��������java.sql.Date����
	 * <
	 *   SQL������͵ľ�׼ƥ��
	 * >
	 * @param obj_date
	 * @return
	 */
	public static java.sql.Date toSqlDate(Object obj_date)
	{
		java.sql.Date sql_date=java.sql.Date.valueOf(obj_date.toString());
		return sql_date;
	}
	
	public static java.sql.Timestamp getSqlDateTime(Object localDateTime)
	{
		String dateTime=localDateTime.toString().replace("T", " ")+":00";
		java.sql.Timestamp jst = java.sql.Timestamp.valueOf(dateTime);
	    return jst;
	}
	
	
	/**
	 * ��ȡ�̸�ʽ������
	 * @return
	 */
	public static String getShortDate()
	{
		return Tools.getFormatDate("yyyyMMdd");
	}
	
	public static String getIOSDate()
	{
		return Tools.getFormatDate("yyyy-MM-dd");
	}
	
	public static String getFormatDate(String pattern)
	{
		LocalDateTime ldt=LocalDateTime.now();
		DateTimeFormatter dtf=DateTimeFormatter.ofPattern(pattern);
		return ldt.format(dtf);
	}
	
	
	public static int getSequence(String fname)throws Exception
	{
		//1.����JDBC�ӿڱ���
		PreparedStatement  pstm1=null;  //ִ�����е�ǰֵ�Ĳ�ѯ
		PreparedStatement  pstm2=null;  //Ҫô���µ�ǰ���е�ֵ,Ҫô���ӵ�ǰ����
		ResultSet rs=null;              //װ�����еĵ�ǰֵ
		try
		{
			//����SQL���1,��ѯ���е�ǰֵ
			String sql1="select x.currval  from sequence x where x.fname=?";
            pstm1=DBUtils.prepareStatement(sql1);
            pstm1.setObject(1, fname);
            rs=pstm1.executeQuery();  //��ѯ���еĵ�ǰֵ
            
            //���������ʾ���еĵ�ǰֵ
            int currentVal=0;
            //����������е�SQL������
            StringBuilder sql2=new StringBuilder();
            //�ж�sequence����,��ǰ�����Ƿ����
            if(rs.next())   //���ڵ�ǰ����
            {
            	//��ȡ���е�ǰֵ
            	currentVal=rs.getInt(1);
            	//���µ�ǰ����
            	sql2.append("update sequence x")
            		.append("   set x.currval=?")
            		.append(" where x.fname=?")
            		;
            }
            else            //��ǰ���в�����
            {
            	//���뵱ǰ����
            	sql2.append("insert into sequence(currval,fname,seqloop)")
            		.append("              values(?,?,current_date)")
            		;
            }
        	pstm2=DBUtils.prepareStatement(sql2.toString());
        	pstm2.setObject(1, ++currentVal);
        	pstm2.setObject(2, fname);
        	pstm2.executeUpdate();
        	
            return currentVal;
		}
		finally
		{
			DBUtils.close(rs);
			DBUtils.close(pstm1);
			DBUtils.close(pstm2);
		}
		
	}
	/**
	 * ��ȡ����ժҪ
	 * @param content
	 * @return
	 * @throws Exception
	 */
	public static String getDigest(String content)throws Exception
	{
		String digest = "";
		if (content.length()>100)
		{
			digest=content.substring(0, 100);
		}
		else
		{
			digest=content;
		}
		return digest;
	}
//	public static String auditPResult(String name, int result)throws Exception
//    {
//		String re="";
//		if(result==1) 
//		{
//			re="�����ͨ��";
//		}
//		else
//		{
//			re="���δͨ���������鿴ԭ�򣬳����ٴ��ύ";
//		}
//    	StringBuilder builder = new StringBuilder()
//    			.append("�𾴵�")
//    			.append(name)
//    			.append("\n����")
//    			.append("\n������Ѱ֪ͨ��˽��Ϊ��")
//    			.append(re)
//    			;
//    	return builder.toString();
//    }
//	public static String auditCResult(String name, int result)throws Exception
//    {
//		String re="";
//		if(result==1) 
//		{
//			re="�����ͨ��";
//		}
//		else
//		{
//			re="���δͨ���������鿴ԭ�򣬳����ٴ��ύ";
//		}
//    	StringBuilder builder = new StringBuilder()
//    			.append("�𾴵�")
//    			.append(name)
//    			.append("\n����")
//    			.append("\n����������˽��Ϊ��")
//    			.append(re)
//    			;
//    	return builder.toString();
//    }
	public static String auditPResult(String name, int result)throws Exception
    {
		String re="";
		if(result==1) 
		{
			re="审核已通过";
		}
		else
		{
			re="审核未通过，请您查看原因，尝试再次提交";
		}
    	StringBuilder builder = new StringBuilder()
    			.append("尊敬的")
    			.append(name)
    			.append("\n您好")
    			.append("\n您的找寻通知审核结果为：")
    			.append(re)
    			;
    	return builder.toString();
    }
	public static String auditCResult(String name, int result)throws Exception
    {
		String re="";
		if(result==1) 
		{
			re="审核已通过";
		}
		else
		{
			re="审核未通过，请您查看原因，尝试再次提交";
		}
    	StringBuilder builder = new StringBuilder()
    			.append("尊敬的")
    			.append(name)
    			.append("\n您好")
    			.append("\n您的线索审核结果为：")
    			.append(re)
    			;
    	return builder.toString();
    }	
}
