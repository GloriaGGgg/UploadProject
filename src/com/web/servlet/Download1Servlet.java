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
 * ���servlet���������������ļ������
 * @author Gloria
 *
 */
public class Download1Servlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * ����ͷһ����
		 * 1.content-Type
		 * 2.content-Disposition
		 * ���������ļ�������
		 */
		
		//�õ�����ͷ
		String fileName="";//���ļ����Ʒ�������
		String contentType=this.getServletContext().getMimeType(fileName);//ͨ���ļ����õ�����Mime������
		String contentDisposition="attachment;a.mp3";
		
		//�õ�һ����
		//��һ���ļ�������mp�����һ�����ļ�
		FileInputStream input=new FileInputStream(fileName);
		
		//��������ͷ
		response.setHeader("Content-Type", contentType);
		response.setHeader("Content-Disposition", contentDisposition);
		
		//��������ļ���д����һ��������
		//��ȡ���˿ͻ��˵������������ͻ������
		ServletOutputStream output=response.getOutputStream();
		
		IOUtils.copy(input, output);//��һ����д��ͻ��������ȥ
		
		input.close();//��������ر�
		
		
	}

}
