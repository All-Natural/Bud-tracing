package com.copenedu.web.impl.img;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ImgServlet
 */
@WebServlet("/show_img")
public class ImgServlet extends HttpServlet {
	
	final File storeImg = new File("D:/Desktop/store/imgs");  //文件存放位置获取
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String imgName = request.getParameter("iname"); 		//放在url的参数里面
//		String url = request.getRequestURI();
		
		
		File fileInLocal = new File(storeImg, imgName);
		
		try {
			OutputStream output = response.getOutputStream();
			FileInputStream input = new FileInputStream(fileInLocal);
			copy(input, output);
			input.close();
			output.close();
		} catch (Exception e) {
			System.out.println("exception happen .. imgs");
		}
//		output.r
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
	
	
	private long copy(InputStream in, OutputStream out) throws Exception
	{
		long count = 0;
		byte[] buf = new byte[8192];
		while (true)
		{
			int n = in.read(buf);
			if (n < 0)
				break;
			if (n == 0)
				continue;
			out.write(buf, 0, n);

			count += n;
		}
		return count;
	}
}
