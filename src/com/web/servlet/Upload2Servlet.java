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
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class Upload2Servlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		/*
		 * 上传三步
		 * 1.得到工厂
		 * 2.通过工厂得到解析器
		 * 3.解析request，得到FileItem集合
		 * 4.遍历FileItem集合，调用api进行文件保存
		 */
		DiskFileItemFactory factory=new DiskFileItemFactory();
		ServletFileUpload sfu=new ServletFileUpload(factory);
		try {
			//得到集合
			List<FileItem> fileItemList=sfu.parseRequest(request);
			//遍历集合
			FileItem fi1=fileItemList.get(0);//得到第一个表单元素,即普通表单项，username
			FileItem fi2=fileItemList.get(1);//得到第二个表单元素,即文件表单项，photo
			
			//得到各个表单的数据
			System.out.println("formal form "+fi1.getFieldName()+"="+fi1.getString("utf-8"));
			System.out.println("file form:");
			System.out.println("Content-type:"+fi2.getContentType());
			System.out.println("size:"+fi2.getSize());
			System.out.println("file name:"+fi2.getName());
			
			//保存文件
			File deskFile=new File("D:/deskFile.jpg");
			try {
				fi2.write(deskFile);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			
		} catch (FileUploadException e) {
			throw new RuntimeException(e);
		}

	}
}
