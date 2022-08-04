package com.copenedu.web.impl.record;

import com.copenedu.services.impl.record.RetrospectAuditImpl;
import com.copenedu.web.support.BaseServlet;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/show_record_list.htm")
public class ShowRecordServlet extends BaseServlet 
{
	private static final long serialVersionUID = 1L;

	@Override
	protected String execute(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		String toPath ="/show_record_list.jsp";
		Map<String, String> dto=this.parseRequest(request);
		RetrospectAuditImpl sImpl=new RetrospectAuditImpl(dto);
		List<Map<String, String>> rows = sImpl.showRecord();
		String msg = "";
		if(rows.size()>0)
		{
			msg="审核记录";
			request.setAttribute("rows", rows);
			String pageController = sImpl.getPageController("/show_record_list.htm");
			request.setAttribute("pageController", pageController);
		}
		else
		{
			msg="暂无审核记录";
		}
		request.setAttribute("msg", msg);
		return toPath;
	}
	
}
