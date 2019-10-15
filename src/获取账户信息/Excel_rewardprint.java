package ��ȡ�˻���Ϣ;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import ���ӹ�����.JDBCUtil;

/**
 * Servlet implementation class Excel_rewardprint
 */
public class Excel_rewardprint extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection coon = null;
		Statement st = null;
		ResultSet rs = null;
		int i = 1;
		XSSFWorkbook wb = new XSSFWorkbook(); //����Excel���
		XSSFSheet  sheet = wb.createSheet("�û�ע����Ϣ");//����������
		
		try {
			
			coon = JDBCUtil.getConn();
			st =  coon.createStatement();
			String sql = "select * from reward order by id";
			rs = st.executeQuery(sql);
				//����excel
			   response.setContentType("application/vnd.ms-excel");//��Ӧ���ĵ�MIME���ͣ���ʾExcel
				
		        sheet.setColumnWidth(1000, 10000);                          //�����п�

		        XSSFRow titleRow = sheet.createRow(0);            //����Excel�еı�����

		        XSSFCell titleCell1 =titleRow .createCell(0);          //�����д�����1����Ԫ��

		        titleCell1.setCellValue("ѧ��");  //���õ�1����Ԫ���ֵ
		        
		        XSSFCell titleCell2= titleRow.createCell(1);           //�����д�����2����Ԫ��

		        titleCell2.setCellValue("����");                      //���õ�2����Ԫ���ֵ

		        XSSFCell titleCell3 =titleRow .createCell(2);          //�����д�����3����Ԫ��

		        titleCell3.setCellValue("����");                      //���õ�3����Ԫ���ֵ

		        XSSFCell titleCell4= titleRow.createCell(3);           //�����д�����4����Ԫ��

		        titleCell4.setCellValue("����");                      //���õ�4����Ԫ���ֵ
		        
		        XSSFCell titleCell5= titleRow.createCell(4);           //�����д�����4����Ԫ��

		        titleCell5.setCellValue("����");
		        
		        XSSFCell titleCell6= titleRow.createCell(5);           //�����д�����4����Ԫ��

		        titleCell6.setCellValue("��ע");
		        
			while(rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				String jiangli = rs.getString("jiangli");
				String chufa = rs.getString("chengfa");
				String describe = rs.getString("describe");
				String other = rs.getString("other");
		        		
			        	XSSFRow valueRow = sheet.createRow(i++);         //������2��
			        	
			        	XSSFCell idCell = valueRow.createCell(0);              //�ڵ�2���д�����Ԫ��
			    		
				        idCell.setCellValue(id);
	
				        XSSFCell nameCell = valueRow.createCell(1);              //�ڵ�2���д�����Ԫ��
		
				        nameCell.setCellValue(name);
		
				        XSSFCell jiangliCell = valueRow.createCell(2);
						
				        jiangliCell.setCellValue(jiangli);
				        
				        XSSFCell chufaCell = valueRow.createCell(3);
						
				        chufaCell.setCellValue(chufa);
				        
				        XSSFCell deCell = valueRow.createCell(4);
		
				        deCell.setCellValue(describe);
		
				        XSSFCell otCell = valueRow.createCell(5);
		
				        otCell.setCellValue(other);
				   
				       
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.release(coon, st, rs);
		}
		try {
			FileOutputStream fo = new FileOutputStream("news.xlsx");
			wb.write(fo);
		} catch (Exception e2) {
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
