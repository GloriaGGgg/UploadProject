package com.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;


/**
 * 这个servlet是用来处理从上传表单上传上来的数据
 * @author Gloria
 *
 */
public class Upload1Servlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//前面表单有file文件就需要用这个来获取数据
		ServletInputStream in=request.getInputStream();
		
		//IOUtils有一个toString方法，输入一个流，就可以把里面所有数据变成string输出来
		System.out.println(IOUtils.toString(in));
	}
}
