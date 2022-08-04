package com.copenedu.web.impl.Manager.AClue;

import com.copenedu.services.impl.Manager.AuditServicesImpl;
import com.copenedu.web.support.BaseServlet;

import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/show_audit_clue_list.htm")
public class ShowAuditClueListServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected String execute(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String toPath = "/show_audit_clue_list.jsp";
		Map<String, String> dto = this.parseRequest(request);
		AuditServicesImpl sImpl = new AuditServicesImpl(dto);
		List<Map<String, String>> rows = sImpl.showAuditClueList();
		String msg = "暂无";
		if (rows.size()>0)
		{
			request.setAttribute("rows", rows);
			String pageController = sImpl.getPageController("/show_audit_clue_list.htm");
			request.setAttribute("pageController", pageController);
			msg="";
		}
		else
		{
			msg="暂时还没有要审核的线索";
		}
		request.setAttribute("msg", msg);
		return toPath;
	}
}
