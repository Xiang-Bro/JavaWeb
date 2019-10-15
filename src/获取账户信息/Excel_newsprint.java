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
 * Servlet implementation class Excel_newsprint
 */
public class Excel_newsprint extends HttpServlet {

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
			String sql = "select * from stu order by id";
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

		        titleCell3.setCellValue("רҵ");                      //���õ�3����Ԫ���ֵ

		        XSSFCell titleCell4= titleRow.createCell(3);           //�����д�����4����Ԫ��

		        titleCell4.setCellValue("�༶");                      //���õ�4����Ԫ���ֵ
		        
		        XSSFCell titleCell5= titleRow.createCell(4);           //�����д�����4����Ԫ��

		        titleCell5.setCellValue("�Ա�");
		        
		        XSSFCell titleCell6= titleRow.createCell(5);           //�����д�����4����Ԫ��

		        titleCell6.setCellValue("����");
		        
		        XSSFCell titleCell7= titleRow.createCell(6);           //�����д�����4����Ԫ��

		        titleCell7.setCellValue("��������");
		        
		        XSSFCell titleCell8= titleRow.createCell(7);           //�����д�����4����Ԫ��

		        titleCell8.setCellValue("���֤����");
		        
		        XSSFCell titleCell9= titleRow.createCell(8);           //�����д�����4����Ԫ��

		        titleCell9.setCellValue("�绰����");
		        
		        XSSFCell titleCell10= titleRow.createCell(9);           //�����д�����4����Ԫ��

		        titleCell10.setCellValue("qq");
		        
		        
			while(rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				String zhuanye = rs.getString("zhuanye");
				int banji = rs.getInt("banji");
				String age = rs.getString("age");
				String sex = rs.getString("sex");
				String tel = rs.getString("tel");
				String birthday = rs.getString("birthday");
				String qq = rs.getString("qq");
				String id_card = rs.getString("id_card");
		        		
			        	XSSFRow valueRow = sheet.createRow(i++);         //������2��
			        	
			        	XSSFCell idCell = valueRow.createCell(0);              //�ڵ�2���д�����Ԫ��
			    		
				        idCell.setCellValue(id);
	
				        XSSFCell nameCell = valueRow.createCell(1);              //�ڵ�2���д�����Ԫ��
		
				        nameCell.setCellValue(name);
		
				        XSSFCell zhuanyeCell = valueRow.createCell(2);
						
				        zhuanyeCell.setCellValue(zhuanye);
				        
				        XSSFCell banjiCell = valueRow.createCell(3);
						
				        banjiCell.setCellValue(banji);
				        
				        XSSFCell sexCell = valueRow.createCell(4);
		
				        sexCell.setCellValue(sex);
		
				        XSSFCell ageCell = valueRow.createCell(5);
		
				        ageCell.setCellValue(age);
				        
				        XSSFCell birCell = valueRow.createCell(6);
						
				        birCell.setCellValue(birthday);
				        
				        XSSFCell idcardCell = valueRow.createCell(7);
						
				        idcardCell.setCellValue(id_card);
				        
				        XSSFCell telCell = valueRow.createCell(8);
		
				        telCell.setCellValue(tel);
				        
				        XSSFCell qqCell = valueRow.createCell(9);
						
				        qqCell.setCellValue(qq);
				        
				        
				        System.out.println("���ǵ�" + i + "��"  + name + "--" + sex + "--" + age + "--" + tel);
				   
				       
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
