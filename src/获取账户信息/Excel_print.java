package 获取账户信息;

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

import 连接工具类.JDBCUtil;

public class Excel_print extends HttpServlet {
	
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
			String sql = "select * from scores order by zhuanye DESC,banji ";
			rs = st.executeQuery(sql);
				//导出excel
			   response.setContentType("application/vnd.ms-excel");//响应正文的MIME类型，表示Excel
				
		        sheet.setColumnWidth(1000, 10000);                          //设置列宽

		        XSSFRow titleRow = sheet.createRow(0);            //创建Excel中的标题行

		        XSSFCell titleCell1 =titleRow .createCell(0);          //在行中创建第1个单元格

		        titleCell1.setCellValue("学号"); 
		        
		        XSSFCell titleCell2 =titleRow .createCell(1);          //在行中创建第1个单元格

		        titleCell2.setCellValue("姓名"); 
		        
		        XSSFCell titleCell3 =titleRow .createCell(2);          //在行中创建第1个单元格

		        titleCell3.setCellValue("专业"); 
		        
		        XSSFCell titleCell4 =titleRow .createCell(3);          //在行中创建第1个单元格

		        titleCell4.setCellValue("班级");               //设置第1个单元格的值

		        XSSFCell titleCell5= titleRow.createCell(4);           //在行中创建第2个单元格

		        titleCell5.setCellValue("语文");                      //设置第2个单元格的值

		        XSSFCell titleCell6 =titleRow .createCell(5);          //在行中创建第3个单元格

		        titleCell6.setCellValue("数学");                      //设置第3个单元格的值

		        XSSFCell titleCell7= titleRow.createCell(6);           //在行中创建第4个单元格

		        titleCell7.setCellValue("英语");                      //设置第4个单元格的值
		        
		        XSSFCell titleCell8 =titleRow .createCell(7);          //在行中创建第1个单元格

		        titleCell8.setCellValue("体育"); 
		        
		        XSSFCell titleCell9 =titleRow .createCell(8);          //在行中创建第1个单元格

		        titleCell9.setCellValue("总分"); 
			
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
			        	
			        	XSSFCell idCell = valueRow.createCell(0);              //在第2行中创建单元格
			    		
				        idCell.setCellValue(id);
			        	
			        	XSSFCell nameCell = valueRow.createCell(1);              //在第2行中创建单元格
			    		
				        nameCell.setCellValue(name);
				        
				        XSSFCell zhuanyeCell = valueRow.createCell(2);              //在第2行中创建单元格
						
				        zhuanyeCell.setCellValue(zhuanye);
				        
				        XSSFCell banjiCell = valueRow.createCell(3);              //在第2行中创建单元格
						
				        banjiCell.setCellValue(banji);
	
				        XSSFCell chineseCell = valueRow.createCell(4);              //在第2行中创建单元格
		
				        chineseCell.setCellValue(chinese);
		
				        XSSFCell mathCell = valueRow.createCell(5);
		
				        mathCell.setCellValue(math);
		
				        XSSFCell englishCell = valueRow.createCell(6);
		
				        englishCell.setCellValue(english);
		
				        XSSFCell peCell = valueRow.createCell(7);
		
				        peCell.setCellValue(pe);
				        
				        XSSFCell sumCell = valueRow.createCell(8);              //在第2行中创建单元格
						
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
