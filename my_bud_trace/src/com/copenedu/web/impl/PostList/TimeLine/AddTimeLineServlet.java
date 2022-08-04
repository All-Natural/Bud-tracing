package com.copenedu.web.impl.PostList.TimeLine;

import com.copenedu.services.impl.PostList.*;
import com.copenedu.web.support.BaseServlet;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/add_timeline.htm")
public class AddTimeLineServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected String execute(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String toPath="/add_timeline.jsp";
		//1.��ȡҳ�����
		Map<String, String> dto=this.parseRequest(request);
		//2.ʵ����IMPL����
		TimeLineServicesImpl servicesImpl=new TimeLineServicesImpl(dto);
		//3.ִ�н��
		String msg=servicesImpl.addTimeline()?"操作成功":"操作失败";
		//4.������session
		request.setAttribute("msg", msg);
		return toPath;
	}
}
