package com.copenedu.web.impl.PostList.Post;

import com.copenedu.services.impl.PostList.PostListServicesImpl;
import com.copenedu.web.support.BaseServlet;

import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/del_my_post.htm")
public class DelMyPostServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected String execute(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		String toPath="/show_my_postde.jsp";
		String msg=null;
		Map<String, String> dto=this.parseRequest(request);
		PostListServicesImpl sImpl=new PostListServicesImpl(dto);
		msg=sImpl.delMyPostBatch()?"操作成功":"操作失败";
		request.setAttribute("msg", msg);
		return toPath;
	}
}
