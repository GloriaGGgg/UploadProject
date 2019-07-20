package com.web.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.itcast.commons.CommonUtils;

public class Upload3Servlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		/*
		 * 三步
		 */
		//创建工厂
		DiskFileItemFactory factory=new DiskFileItemFactory();
		//解析器
		ServletFileUpload sfu=new ServletFileUpload(factory);
		//下面这句限制文件大小必须在解析之前写
		//sfu.setFileSizeMax(100*1024);//限制单个文件大小为100k，1024是1k
		//sfu.setSizeMax(1024*1024);//设置整个表单文件大小是1M
		
		//得到list
		try {
			//这句话是在解析
			List<FileItem> list=sfu.parseRequest(request);
			//得到第一个文件
			FileItem fi=list.get(1);
			
			//在WEB-INF下面创建文件夹，把得到的文件存在里面,下面就是目录打散的核心代码
			
			/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			/*
			 * 1.得到根路径，即文件保存的路径
			 */
			String root=this.getServletContext().getRealPath("/WEB-INF/files/ ");
			
			/*
			 * 2.生成两层目录
			 *   1）得到文件名
			 *   2）得到文件名的hash code
			 *   3）转换成16进制
			 *   4）得到前两位字符生成目录
			 */
			
			/*
			 * 1）得到文件名
			 */
			String fileName=fi.getName();//得到类似于"c:/xxx/xxx.jpg"这样的显示
			
			
			/*
			 * 处理文件名的绝对路径问题，把之前的不需要的都抛弃，只留下xxx.jpg信息
			 */
			int index=fileName.lastIndexOf("//");//得到最后一个/的位置信息，从这个后面开始截取
			if(index != -1){
				//如果存在/，说明后面的substring就是需要截取的一部分
				fileName=fileName.substring(index+1);//开始从/向后截取
			}
			/*
			 * 给文件加上uuid前缀，处理文件同名问题
			 */
			String saveName=CommonUtils.uuid()+"_"+fileName;
			
			
			
			/*
			 *  2）得到hash code
			 *  3）转换成16进制
			 */
			int hCode=fileName.hashCode();//得到hash code
			String hex=Integer.toHexString(hCode);//转化成16进制
			
			/*
			 *  4）得到前两位字符获取目录
			 */
			File dirFile=new File(root+hex.charAt(0)+"/"+hex.charAt(1));//这样和root组合形成最后的存放文件的地址
			
			/*
			 * 5）创建目录链
			 */
			dirFile.mkdirs();
			
			/*
			 * 6）创建目录文件
			 */
			File deskFile=new File(dirFile,saveName);//把创建的文件名放在定好的位置
			
			
			
			////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
			/*
			 * 7）保存文件
			 */
			try {
				
				fi.write(deskFile);
				
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		} catch (FileUploadException e) {
			throw new RuntimeException(e);
		}
	}

}
