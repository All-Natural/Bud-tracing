package com.copenedu.web.impl.Manager.APost;

import com.copenedu.services.impl.Manager.AuditServicesImpl;
import com.copenedu.web.support.BaseServlet;

import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/audit_post.htm")
public class AuditPostServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected String execute(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String toPath="/show_audit_post.jsp";
		Map<String, String> dto =this.parseRequest(request);
		AuditServicesImpl sImpl=new AuditServicesImpl(dto);
		String msg = sImpl.AuditPost()?"操作成功":"操作失败";
		request.setAttribute("msg", msg);
		return toPath;
	}
}
