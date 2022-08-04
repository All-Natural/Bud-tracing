package email;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;

public abstract class EmailHost {
	String sender;						//发送者邮件地址
	int port=25;//465				//端口号
	boolean isSsl=false;			//是否为ssl 传输
	String host;							//邮件服务服务器主机地址
	String vrCode;						//验证码
	
//		abstract void sendEmail();
//		
//		abstract Email sendEmail(String subject,String content,String to) throws EmailException;
	
	//只能同包内创建对象
	protected EmailHost()
	{
		
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public boolean isSsl() {
		return isSsl;
	}

	public void setSsl(boolean isSsl) {
		this.isSsl = isSsl;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getVrCode() {
		return vrCode;
	}

	public void setVrCode(String vrCode) {
		this.vrCode = vrCode;
	}

}
