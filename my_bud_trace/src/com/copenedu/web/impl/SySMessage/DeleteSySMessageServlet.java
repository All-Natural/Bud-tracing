package com.copenedu.web.impl.SySMessage;

import com.copenedu.services.impl.SySMessage.SySMessageServicesImpl;
import com.copenedu.web.support.BaseServlet;

import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/del_sys_msg.htm")
public class DeleteSySMessageServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected String execute(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String toPath = "/my_sys_msg.htm";
		Map<String, String> dto = this.parseRequest(request);
		SySMessageServicesImpl sImpl = new SySMessageServicesImpl(dto);
		String msg = sImpl.delSySMsg()?"删除成功":"删除失败";
		request.setAttribute("msg", msg);
		return toPath;
	}
}
