package com.copenedu.web.impl.User.Account;

import com.copenedu.services.impl.User.LoginServicesImpl;
import com.copenedu.web.support.BaseServlet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/customer.htm")
public class CustomerLoginServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String>dto=new HashMap<String, String>();
		String toPath="/home.jsp";
		String uphone = "15263097111";
		String upassword="0000";
		dto.put("upassword", upassword);
		dto.put("uphone", uphone);
		LoginServicesImpl sImpl = new LoginServicesImpl(dto);
		Map<String, String> userinfo = sImpl.checkUser();
		request.getSession().setAttribute("userinfo",userinfo);
		//查看用户类别
		String systype=userinfo.get("systype");
		//获取菜单列表(字符串形式)
		List<Map<String, String>> userMenuList=sImpl.getUserMenu(systype);
		//session保存用户菜单信息
		
		request.getSession().setAttribute("userMenuList", userMenuList);
		
		return toPath;
	}
	
}
