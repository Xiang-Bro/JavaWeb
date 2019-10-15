package 获取账户信息;

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

import 连接工具类.JDBCUtil;

/**
 * Servlet implementation class Excel_rewardprint
 */
public class Excel_rewardprint extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection coon = null;
		Statement st = null;
		ResultSet rs = null;
		int i = 1;
		XSSFWorkbook wb = new XSSFWorkbook(); //创建Excel表格
		XSSFSheet  sheet = wb.createSheet("用户注册信息");//创建工作簿
		
		try {
			
			coon = JDBCUtil.getConn();
			st =  coon.createStatement();
			String sql = "select * from reward order by id";
			rs = st.executeQuery(sql);
				//导出excel
			   response.setContentType("application/vnd.ms-excel");//响应正文的MIME类型，表示Excel
				
		        sheet.setColumnWidth(1000, 10000);                          //设置列宽

		        XSSFRow titleRow = sheet.createRow(0);            //创建Excel中的标题行

		        XSSFCell titleCell1 =titleRow .createCell(0);          //在行中创建第1个单元格

		        titleCell1.setCellValue("学号");  //设置第1个单元格的值
		        
		        XSSFCell titleCell2= titleRow.createCell(1);           //在行中创建第2个单元格

		        titleCell2.setCellValue("姓名");                      //设置第2个单元格的值

		        XSSFCell titleCell3 =titleRow .createCell(2);          //在行中创建第3个单元格

		        titleCell3.setCellValue("奖励");                      //设置第3个单元格的值

		        XSSFCell titleCell4= titleRow.createCell(3);           //在行中创建第4个单元格

		        titleCell4.setCellValue("处罚");                      //设置第4个单元格的值
		        
		        XSSFCell titleCell5= titleRow.createCell(4);           //在行中创建第4个单元格

		        titleCell5.setCellValue("描述");
		        
		        XSSFCell titleCell6= titleRow.createCell(5);           //在行中创建第4个单元格

		        titleCell6.setCellValue("备注");
		        
			while(rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				String jiangli = rs.getString("jiangli");
				String chufa = rs.getString("chengfa");
				String describe = rs.getString("describe");
				String other = rs.getString("other");
		        		
			        	XSSFRow valueRow = sheet.createRow(i++);         //创建第2行
			        	
			        	XSSFCell idCell = valueRow.createCell(0);              //在第2行中创建单元格
			    		
				        idCell.setCellValue(id);
	
				        XSSFCell nameCell = valueRow.createCell(1);              //在第2行中创建单元格
		
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
