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
 * ���servlet������������ϴ����ϴ�����������
 * @author Gloria
 *
 */
public class Upload1Servlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//ǰ�����file�ļ�����Ҫ���������ȡ����
		ServletInputStream in=request.getInputStream();
		
		//IOUtils��һ��toString����������һ�������Ϳ��԰������������ݱ��string�����
		System.out.println(IOUtils.toString(in));
	}
}
