package email;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.mail.EmailException;

/**
 * Servlet implementation class email
 */
@WebServlet("/email")
public class email extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					MyEmailHost.Send("用户登录操作通知", "您登录了www.hatlate.cn\nwww.hatlate.cn", "969216419@qq.com");
				} catch (EmailException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
