package com.web.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;


/**
 * 这个servlet是用来处理下载文件命令的
 * @author Gloria
 *
 */
public class Download1Servlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 两个头一个流
		 * 1.content-Type
		 * 2.content-Disposition
		 * 流：下载文件的数据
		 */
		
		//得到两个头
		String fileName="";//把文件名称放在这里
		String contentType=this.getServletContext().getMimeType(fileName);//通过文件名得到它的Mime的类型
		String contentDisposition="attachment;a.mp3";
		
		//得到一个流
		//把一个文件（比如mp）变成一个流文件
		FileInputStream input=new FileInputStream(fileName);
		
		//设置两个头
		response.setHeader("Content-Type", contentType);
		response.setHeader("Content-Disposition", contentDisposition);
		
		//把上面的文件流写入另一个流里面
		//获取绑定了客户端的流，这个流向客户端输出
		ServletOutputStream output=response.getOutputStream();
		
		IOUtils.copy(input, output);//把一个流写入客户端输出流去
		
		input.close();//把输出流关闭
		
		
	}

}
