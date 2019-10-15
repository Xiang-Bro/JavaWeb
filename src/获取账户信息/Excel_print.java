package ��ȡ�˻���Ϣ;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.WeakHashMap;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import ���ӹ�����.JDBCUtil;

public class Excel_print extends HttpServlet {
	
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
			String sql = "select * from scores order by zhuanye DESC,banji ";
			rs = st.executeQuery(sql);
				//����excel
			   response.setContentType("application/vnd.ms-excel");//��Ӧ���ĵ�MIME���ͣ���ʾExcel
				
		        sheet.setColumnWidth(1000, 10000);                          //�����п�

		        XSSFRow titleRow = sheet.createRow(0);            //����Excel�еı�����

		        XSSFCell titleCell1 =titleRow .createCell(0);          //�����д�����1����Ԫ��

		        titleCell1.setCellValue("ѧ��"); 
		        
		        XSSFCell titleCell2 =titleRow .createCell(1);          //�����д�����1����Ԫ��

		        titleCell2.setCellValue("����"); 
		        
		        XSSFCell titleCell3 =titleRow .createCell(2);          //�����д�����1����Ԫ��

		        titleCell3.setCellValue("רҵ"); 
		        
		        XSSFCell titleCell4 =titleRow .createCell(3);          //�����д�����1����Ԫ��

		        titleCell4.setCellValue("�༶");               //���õ�1����Ԫ���ֵ

		        XSSFCell titleCell5= titleRow.createCell(4);           //�����д�����2����Ԫ��

		        titleCell5.setCellValue("����");                      //���õ�2����Ԫ���ֵ

		        XSSFCell titleCell6 =titleRow .createCell(5);          //�����д�����3����Ԫ��

		        titleCell6.setCellValue("��ѧ");                      //���õ�3����Ԫ���ֵ

		        XSSFCell titleCell7= titleRow.createCell(6);           //�����д�����4����Ԫ��

		        titleCell7.setCellValue("Ӣ��");                      //���õ�4����Ԫ���ֵ
		        
		        XSSFCell titleCell8 =titleRow .createCell(7);          //�����д�����1����Ԫ��

		        titleCell8.setCellValue("����"); 
		        
		        XSSFCell titleCell9 =titleRow .createCell(8);          //�����д�����1����Ԫ��

		        titleCell9.setCellValue("�ܷ�"); 
			
			while(rs.next()) {
				
				String id = rs.getString("id");
				String name = rs.getString("name");
				String zhuanye = rs.getString("zhuanye");
				String banji = rs.getString("banji");
				int chinese = rs.getInt("chinese");
				int math = rs.getInt("math");
				int english = rs.getInt("english");
				int pe = rs.getInt("pe");
		        int sum = chinese + math + english + pe;
		        
			        	XSSFRow valueRow = sheet.createRow(i++); 
			        	
			        	XSSFCell idCell = valueRow.createCell(0);              //�ڵ�2���д�����Ԫ��
			    		
				        idCell.setCellValue(id);
			        	
			        	XSSFCell nameCell = valueRow.createCell(1);              //�ڵ�2���д�����Ԫ��
			    		
				        nameCell.setCellValue(name);
				        
				        XSSFCell zhuanyeCell = valueRow.createCell(2);              //�ڵ�2���д�����Ԫ��
						
				        zhuanyeCell.setCellValue(zhuanye);
				        
				        XSSFCell banjiCell = valueRow.createCell(3);              //�ڵ�2���д�����Ԫ��
						
				        banjiCell.setCellValue(banji);
	
				        XSSFCell chineseCell = valueRow.createCell(4);              //�ڵ�2���д�����Ԫ��
		
				        chineseCell.setCellValue(chinese);
		
				        XSSFCell mathCell = valueRow.createCell(5);
		
				        mathCell.setCellValue(math);
		
				        XSSFCell englishCell = valueRow.createCell(6);
		
				        englishCell.setCellValue(english);
		
				        XSSFCell peCell = valueRow.createCell(7);
		
				        peCell.setCellValue(pe);
				        
				        XSSFCell sumCell = valueRow.createCell(8);              //�ڵ�2���д�����Ԫ��
						
				        sumCell.setCellValue(sum);
				        
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.release(coon, st, rs);
		}
		try {
			FileOutputStream fo = new FileOutputStream("scores.xlsx");
			wb.write(fo);
		} catch (Exception e2) {
			
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doGet(request, response);
	}

}
