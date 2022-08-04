package com.copenedu.web.impl.Manager.AClue;

import com.copenedu.services.impl.Manager.AuditServicesImpl;
import com.copenedu.web.support.BaseServlet;

import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/show_audit_clue.htm")
public class ShowAuditClueServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected String execute(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String toPath="/show_audit_clue.jsp";
		Map<String, String> dto=this.parseRequest(request);
		AuditServicesImpl sImpl=new AuditServicesImpl(dto);
		Map<String, String> ins = sImpl.showAuditClue();
		if(ins!=null)
		{
			request.setAttribute("ins", ins);
		}
		return toPath;
	}
}
