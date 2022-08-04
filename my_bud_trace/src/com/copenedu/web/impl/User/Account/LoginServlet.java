package com.copenedu.web.impl.User.Account;

import com.copenedu.services.impl.User.LoginServicesImpl;
import com.copenedu.web.support.BaseServlet;

import java.util.List;
import java.util.Map;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class IssueSearchNoticeServlet
 */
@WebServlet("/login.htm")
public class LoginServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected String execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception
	{
		String toPath="/login.jsp";
		//1.获取页面组件
		Map<String, String> dto = this.parseRequest(request);
		//2.实例化IMPL对象
		LoginServicesImpl sImpl = new LoginServicesImpl(dto);
		//3.检查用户名合法性
		try
		{
			Map<String, String> userinfo = sImpl.checkUser();
			
			//正确
			if(userinfo != null)
			{
				toPath="/index.html";
				//session保存用户信息
				request.getSession().setAttribute("userinfo",userinfo);
				//查看用户类别
				String systype=userinfo.get("systype");
				//获取菜单列表(字符串形式)
				List<Map<String, String>> userMenuList=sImpl.getUserMenu(systype);
				//session保存用户菜单信息
				
				request.getSession().setAttribute("userMenuList", userMenuList);
			}
			//错误
			else 
			{
				request.setAttribute("msg", "用户名或密码错误");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return toPath;
	}
}
