package com.copenedu.web.impl.User.Account;

import com.copenedu.services.impl.User.ShowSelfServicesImpl;
import com.copenedu.web.support.BaseServlet;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/mod_user.htm")
public class ModUserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected String execute(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		String toPath="/show_self.htm";
		String msg=null;
		Map<String, String> dto=this.parseRequest(request);
		ShowSelfServicesImpl sImpl=new ShowSelfServicesImpl(dto);
		msg=sImpl.modUser()?"操作成功":"操作失败";
		request.setAttribute("msg", msg);
		return toPath;
	}
}
