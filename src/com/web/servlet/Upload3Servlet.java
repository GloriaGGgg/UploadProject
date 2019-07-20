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
		 * ����
		 */
		//��������
		DiskFileItemFactory factory=new DiskFileItemFactory();
		//������
		ServletFileUpload sfu=new ServletFileUpload(factory);
		//������������ļ���С�����ڽ���֮ǰд
		//sfu.setFileSizeMax(100*1024);//���Ƶ����ļ���СΪ100k��1024��1k
		//sfu.setSizeMax(1024*1024);//�����������ļ���С��1M
		
		//�õ�list
		try {
			//��仰���ڽ���
			List<FileItem> list=sfu.parseRequest(request);
			//�õ���һ���ļ�
			FileItem fi=list.get(1);
			
			//��WEB-INF���洴���ļ��У��ѵõ����ļ���������,�������Ŀ¼��ɢ�ĺ��Ĵ���
			
			/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			/*
			 * 1.�õ���·�������ļ������·��
			 */
			String root=this.getServletContext().getRealPath("/WEB-INF/files/ ");
			
			/*
			 * 2.��������Ŀ¼
			 *   1���õ��ļ���
			 *   2���õ��ļ�����hash code
			 *   3��ת����16����
			 *   4���õ�ǰ��λ�ַ�����Ŀ¼
			 */
			
			/*
			 * 1���õ��ļ���
			 */
			String fileName=fi.getName();//�õ�������"c:/xxx/xxx.jpg"��������ʾ
			
			
			/*
			 * �����ļ����ľ���·�����⣬��֮ǰ�Ĳ���Ҫ�Ķ�������ֻ����xxx.jpg��Ϣ
			 */
			int index=fileName.lastIndexOf("//");//�õ����һ��/��λ����Ϣ����������濪ʼ��ȡ
			if(index != -1){
				//�������/��˵�������substring������Ҫ��ȡ��һ����
				fileName=fileName.substring(index+1);//��ʼ��/����ȡ
			}
			/*
			 * ���ļ�����uuidǰ׺�������ļ�ͬ������
			 */
			String saveName=CommonUtils.uuid()+"_"+fileName;
			
			
			
			/*
			 *  2���õ�hash code
			 *  3��ת����16����
			 */
			int hCode=fileName.hashCode();//�õ�hash code
			String hex=Integer.toHexString(hCode);//ת����16����
			
			/*
			 *  4���õ�ǰ��λ�ַ���ȡĿ¼
			 */
			File dirFile=new File(root+hex.charAt(0)+"/"+hex.charAt(1));//������root����γ����Ĵ���ļ��ĵ�ַ
			
			/*
			 * 5������Ŀ¼��
			 */
			dirFile.mkdirs();
			
			/*
			 * 6������Ŀ¼�ļ�
			 */
			File deskFile=new File(dirFile,saveName);//�Ѵ������ļ������ڶ��õ�λ��
			
			
			
			////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
			/*
			 * 7�������ļ�
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
