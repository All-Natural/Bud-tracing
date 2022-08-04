package email;

import java.util.Date;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class HatEmailHost extends EmailHost implements SimpleEmailBuild
{
//		public static HatEmailHost emailHost;
	DefaultAuthenticator auth;				//验证令牌
	
	public void init()
	{
//				emailHost=new HatEmailHost();
		setSender("hatlate2020@163.com");								//发信邮箱地址
		setHost("smtp.163.com");										//邮件服务器地址
//		setVrCode("AHMXCFGQHOPPDHJJ");									//验证码
//		setVrCode("KABQWBVUGXSSXTUM");
		setVrCode("QBHAEYBCTGTAPQWP");
		setSsl(true);													//ssl加密传输   好像仅支持ssl
		setPort(465);													//端口号 22 / 465   ssl -> 465
//		setPort(22);
//		setSsl(false);	
		
		auth =  new DefaultAuthenticator(sender, vrCode);			//验证令牌
	}
	
	
	@Override
	public Email build(String subject, String content, String to) throws EmailException
	{
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
//		DefaultAuthenticator auth = new DefaultAuthenticator(getSender(), getVrCode());
		email.setAuthenticator(auth);
		
		// 邮件
		email.setFrom(getSender()); 			// 发送者
		email.setSubject(subject); 				// 邮件标题
		email.setMsg(content);   				// 邮件内容
//				email.setSentDate(new Date());    //日期
		email.addTo(to);   // 接收者 ( 允许有多个 )
		
		return email;
	}
	
	
}
