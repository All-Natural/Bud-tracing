package com.copenedu.web.impl.PostList.Post;

import com.copenedu.services.impl.PostList.PostListServicesImpl;
import com.copenedu.web.support.BaseServlet;

import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/mod_my_post.htm")
public class ModMyPostServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected String execute(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String toPath="/show_my_postde.jsp";
		Map<String, String> dto = this.parseRequest(request);
		PostListServicesImpl sImpl = new PostListServicesImpl(dto);
		String msg = sImpl.updateSearchPost()?"修改成功":"修改失败";
		request.setAttribute("msg", msg);
		return toPath;
	}
}
