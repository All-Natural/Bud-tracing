package com.copenedu.web.impl.SySMessage;

import com.copenedu.services.impl.SySMessage.SySMessageServicesImpl;
import com.copenedu.web.support.BaseServlet;

import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/my_sys_msg.htm")
public class NotifySySMessageListServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected String execute(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String toPath = "/IssueSearchNotice.jsp";
		Map<String, String> dto = this.parseRequest(request);
		SySMessageServicesImpl sImpl=new SySMessageServicesImpl(dto);
		List<Map<String,String>> rows  =sImpl.showSySMessage();
		if (rows.size()>0)
		{
			request.setAttribute("rows", rows);
			String pageController = sImpl.getPageController("/my_sys_msg.htm");
			request.setAttribute("pageController", pageController);
		}
		else
		{
			request.setAttribute("msg", "无符合条件的消息！");
		}
		return toPath;
	}
}
