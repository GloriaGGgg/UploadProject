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
		 * �ϴ�����
		 * 1.�õ�����
		 * 2.ͨ�������õ�������
		 * 3.����request���õ�FileItem����
		 * 4.����FileItem���ϣ�����api�����ļ�����
		 */
		DiskFileItemFactory factory=new DiskFileItemFactory();
		ServletFileUpload sfu=new ServletFileUpload(factory);
		try {
			//�õ�����
			List<FileItem> fileItemList=sfu.parseRequest(request);
			//��������
			FileItem fi1=fileItemList.get(0);//�õ���һ����Ԫ��,����ͨ���username
			FileItem fi2=fileItemList.get(1);//�õ��ڶ�����Ԫ��,���ļ����photo
			
			//�õ�������������
			System.out.println("formal form "+fi1.getFieldName()+"="+fi1.getString("utf-8"));
			System.out.println("file form:");
			System.out.println("Content-type:"+fi2.getContentType());
			System.out.println("size:"+fi2.getSize());
			System.out.println("file name:"+fi2.getName());
			
			//�����ļ�
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
