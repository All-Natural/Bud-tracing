package com.copenedu.web.impl.Clue;

import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.copenedu.services.impl.Clue.*;
import com.copenedu.web.support.BaseServlet;


@WebServlet("/show_clue.htm")
public class ShowClueServlet extends BaseServlet {

	/**
	 * 根据线索id查看线索详情
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected String execute(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		String toPath="/";
		int path=Integer.parseInt(request.getParameter("path"));
		if(path==1)
		{
			toPath="/show_my_cluede.jsp";
		}
		else
		{
			toPath="/show_clue.jsp";
		}
		
		ManageClueServicesImpl search = new ManageClueServicesImpl(this.parseRequest(request));
		Map<String, String> ins = search.findById();
		
		if(ins!=null)
		{
			request.setAttribute("ins",ins);
		}
		
		List<Map<String, String>> rows = search.imgURL();
		if(rows.size()>0)
		{
			request.setAttribute("rows", rows);
		}
		return toPath;
	}
}
