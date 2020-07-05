package test;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class UpdateTable
 */
@WebServlet("/ModifyTable")
public class ModifyTable extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private void closeResource(ResultSet rs) {
		try {
			rs.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	private void closeResource(PreparedStatement rs) {
		try {
			rs.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	public void closeResource(Statement rs) {
		try {
			rs.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	private void closeResource(Connection rs) {
		try {
			rs.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection dbCon = null;
		Statement stmt = null;
		String amount = request.getParameter("open_amount");
		String type = request.getParameter("doctype");
		String id = request.getParameter("id");
		String url = "jdbc:mysql://127.0.0.1:3306/project";
		String userName = "root";
		String userPass = "root";
		String query_part = request.getParameter("search_id");
		String query = "UPDATE customer_invoice SET actual_open_amount="+amount+", doctype=\""+type+"\" where document_number="+id;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			dbCon = DriverManager.getConnection(url,userName,userPass);
			System.out.println("Ok connected");
			stmt = dbCon.createStatement();
			int r = stmt.executeUpdate(query);
			if(r>0)
			{
				HashMap<String,String> data = new HashMap<String,String>();
				data.put("status","true");
				Gson gson = new Gson();
				String arr = gson.toJson(data);
				PrintWriter pw = response.getWriter();
				pw.write(arr);
				pw.flush();
			}
			else {
				HashMap<String,String> data = new HashMap<String,String>();
				data.put("status","false");
				Gson gson = new Gson();
				String arr = gson.toJson(data);
				PrintWriter pw = response.getWriter();
				pw.write(arr);
				pw.flush();
			}
		}
		catch(Exception d){
			d.printStackTrace();
		}
		finally {
			closeResource(stmt);
			closeResource(dbCon);
		}
	}
}
