package com.copenedu.web.impl.upload;

import com.copenedu.services.impl.Clue.ManageClueServicesImpl;
import com.copenedu.services.impl.PostList.PostListServicesImpl;
import com.copenedu.web.support.BaseServlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

@WebServlet("/upload.htm")
public class FileUploadServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	File tmpDir ;
	@Override
	public void init()throws ServletException
	{
//		File webroot = new File(getServletContext().getRealPath("/"));
		File storeRootFile = new File("D:\\Desktop\\store");  //文件存放位置
		tmpDir = new File(storeRootFile,"imgs");
		tmpDir.mkdirs();
	}
	@Override
	protected String execute(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		StringBuilder toPath=new StringBuilder()
				.append("/upload.jsp")
				;
		Map<String, String> dto = this.parseRequest(request);
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart)
		{
			ServletFileUpload upload = new ServletFileUpload();
			request.setCharacterEncoding("UTF-8");
			FileItemIterator iter = upload.getItemIterator(request);
			while(iter.hasNext()) 
			{
				FileItemStream item = iter.next();
				String name = item.getFieldName();
				InputStream stream = item.openStream();
				if (item.isFormField()) 
				{
					System.out.println("Form field " + name + " with value "
							+ Streams.asString(stream) + " detected.");
				}
				else 
				{
					System.out.println("File field " + name + " with file name "
					        + item.getName() + " detected.");
					// 生成唯一的文件名
					String realName = item.getName();
					String suffix = fileSuffix(realName);
					String tmpFileName = createTmpFileName(suffix);
					System.out.println(tmpFileName);
					String msg = "";
					System.out.println(dto.get("type"));
					if (dto.get("type").equals("0"))
					{
						PostListServicesImpl sImpl = new PostListServicesImpl(dto);
						msg = sImpl.upLoadPhoto(tmpFileName)?"上传成功":"图片已超过两张";
						toPath.append("?postid=")
							  .append(dto.get("id"))
							  .append("&type=0");
					}
					else
					{
						ManageClueServicesImpl sImpl =new ManageClueServicesImpl(dto);
						msg = sImpl.upLoadPhoto(tmpFileName)?"上传成功":"图片已超过两张";
						toPath.append("?clid=")
							  .append(dto.get("id"))
							  .append("&type=1");;
					}
					
					request.setAttribute("msg", msg);
					if (msg.equals("图片已超过两张")) 
					{
						break;
					}
					File tmpFile = new File(tmpDir, tmpFileName);
					
					// 从FieldStream读取数据, 保存到目标文件
					tmpFile.getParentFile().mkdirs();		        
			        FileOutputStream fileStream = new FileOutputStream(tmpFile);
			        long fileSize1;
			        try
			        {
			        	// 从请求里读取文件数据，保存到本地文件
			        	fileSize1 = copy(stream, fileStream);
			        }
			        finally
			        {
			        	try
			        	{ 
			        		fileStream.close();
			        	}
			        	catch(Exception e)
			        	{
			        		e.printStackTrace();
			        	}
			        	
			        	try
			        	{ 
			        		stream.close();
			        	}
			        	catch(Exception e)
			        	{
			        		e.printStackTrace();
			        	}
			        }
			        System.out.println("文件上传完成:" + realName + ", 大小: " + fileSize1);
				}
			}
		}
		return toPath.toString();
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
	// 得到文件的后缀名
	public String fileSuffix(String fileName)
	{
		int p = fileName.lastIndexOf('.');
		if(p >= 0)
		{
			return fileName.substring(p+1).toLowerCase();
		}
		return "";
	}
	// 得到一个保证不重复的临时文件名
	private String createTmpFileName(String suffix)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");
		String datestr = sdf.format(new Date());
		String name = datestr + "-" + createUUID() + "." + suffix;
		return name;
	}
	// 生成一个唯一的ID
	private String createUUID ()
	{
		String s = UUID.randomUUID().toString(); 
		String s2 = s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24); 
		return s2.toUpperCase();
	}
}
