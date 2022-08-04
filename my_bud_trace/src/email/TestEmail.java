package email;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;

public class TestEmail
{
	public static void test1()throws Exception
	{
		// SMTP服务器 : smtp.163.com
		// 端口 : 25  SMTP
		//     465  SMTP over SSL/TLS，加密传输的SMTP
		
		// 发送者 shaofa2019@163.com 授权码 a1b2c3 (授权码不同于邮箱登录密码)
		// 接收者 1926583112@qq.com
		
		Email email = new SimpleEmail();
		
		// 邮件服务器地址
		email.setHostName("smtp.163.com");  
		
		// 普通传输方式
		email.setSmtpPort(25);
		email.setSSLOnConnect(false);
		
		// 加密传输方式 SSL / TLS
		email.setSmtpPort(465);
		email.setSSLOnConnect(true);		
		
		// 用户验证 
		email.setAuthenticator(new DefaultAuthenticator("hatlate2020@163.com", "QBHAEYBCTGTAPQWP"));
		
		// 邮件
		email.setFrom("hatlate2020@163.com"); // 发送者
		email.setSubject("测试邮件 TestMail 哈哈哈"); // 邮件标题
		email.setMsg("你好骚年，今天学得开心吗？aaaabbb");   // 邮件内容
		email.addTo("969216419@qq.com");   // 接收者 ( 允许有多个 )
		
		// 发送
		email.send();		
	}
	
	
	public static void main(String[] args) throws Exception
	{
		test1();
		System.out.println("exit");
	}

}
