package email;

import java.util.Date;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

@Deprecated
public class MyEmailHost extends EmailHost{
//	String sender="hatlate2020@163.com";
//	String host="smtp.163.com";
//	String vrCode="BLBHOOUIHZZTDTLW";
	
	static MyEmailHost emailHost;
	
	static {
		emailHost=new MyEmailHost();
		emailHost.setSender("hatlate2020@163.com");					//发信邮箱地址
		emailHost.setHost("smtp.163.com");										//邮件服务器地址
		emailHost.setVrCode("AHMXCFGQHOPPDHJJ");					//验证码
		emailHost.setSsl(true);															//ssl加密传输   好像仅支持ssl
		emailHost.setPort(465);															//端口号 22 / 465   ssl -> 465
	}
	
	void sendEmail() 
	{
		
		
		
		
	}
	
	Email sendEmail(String subject, String content, String to) throws EmailException {
		Email email = new SimpleEmail();
		
		// 邮件服务器地址
		email.setHostName(getHost());  
		
		// 普通传输方式
		email.setSmtpPort(getPort());
		email.setSSLOnConnect(isSsl());
		
		// 加密传输方式 SSL / TLS
//		email.setSmtpPort(465);
//		email.setSSLOnConnect(true);		
		
		// 用户验证 
		DefaultAuthenticator auth = new DefaultAuthenticator(getSender(), getVrCode());
		email.setAuthenticator(auth);
		
		// 邮件
		email.setFrom(getSender()); 			// 发送者
		email.setSubject(subject); 				// 邮件标题
		email.setMsg(content);   				// 邮件内容
		email.setSentDate(new Date());    //日期
		
		email.addTo(to);   // 接收者 ( 允许有多个 )
		
		// 发送
		email.send();		
		return email;
	}
	
	public static void Send(String subject,String content,String to) throws EmailException
	{
		emailHost.sendEmail(subject, content, to);
	}
}
