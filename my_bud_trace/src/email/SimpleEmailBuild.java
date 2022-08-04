package email;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;

public interface SimpleEmailBuild
{
	/**
	 * 
	 * @param subject 标题
	 * @param content 内容
	 * @param to 目标邮箱地址
	 * @return
	 * @throws EmailException
	 */
	public Email build(String subject,String content,String to) throws EmailException;
}
