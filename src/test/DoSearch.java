package test;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/DoSearch")
public class DoSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private void closeResource(ResultSet rs) {
		try {
			rs.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	private void closeResource(PreparedStatement ps) {
		try {
			ps.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	public void closeResource(Statement s) {
		try {
			s.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	private void closeResource(Connection cn) {
		try {
			cn.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String query = request.getParameter("search");
		Connection dbCon = null;
		Statement stmt = null;
		ResultSet rs = null;
		String url = "jdbc:mysql://localhost:3306/project";
		String userName = "root";
		String userPass = "root";
		try {
			int q = Integer.parseInt(query);
			
			String queryto="SELECT customer_name,customer_number,ROUND(SUM(actual_open_amount)) AS amount FROM customer_invoice WHERE customer_number=" + query + ";";
			Class.forName("com.mysql.cj.jdbc.Driver");
			dbCon = DriverManager.getConnection(url,userName,userPass);
			System.out.println("Ok connected");
			stmt = dbCon.createStatement();
			rs = stmt.executeQuery(queryto);
			ArrayList<HashMap<String,String>> data = new ArrayList<HashMap<String,String>>(); 
			while(rs.next()) {
				HashMap<String,String> hp = new HashMap<String,String>();
				hp.put("customer_name",rs.getString(1));
				hp.put("customer_number",rs.getString(2));
				hp.put("actual_open_amount",rs.getString(3));
				data.add(hp);
			}
			Gson gson = new Gson();
			String arr = gson.toJson(data);
			PrintWriter pw = response.getWriter();
			pw.write(arr);
			pw.flush();
		}
		catch(NumberFormatException e)
		{
			String queryto = "SELECT customer_name,customer_number,ROUND(SUM(actual_open_amount)) AS amount from customer_invoice where customer_name = \"" + query + "\";";
			try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			dbCon = DriverManager.getConnection(url,userName,userPass);
					System.out.println("Ok connected");
					stmt = dbCon.createStatement();
					rs = stmt.executeQuery(queryto);
					ArrayList<HashMap<String,String>> data = new ArrayList<HashMap<String,String>>(); 
					while(rs.next()) {
						HashMap<String,String> hp = new HashMap<String,String>();
						hp.put("customer_name",rs.getString(1));
						hp.put("customer_number",rs.getString(2));
						hp.put("actual_open_amount",rs.getString(3));
						data.add(hp);
					}
					Gson gson = new Gson();
					String arr = gson.toJson(data);
					PrintWriter pw = response.getWriter();
					pw.write(arr);
					pw.flush();
			}
			catch(Exception d){
				d.printStackTrace();
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			closeResource(rs);
			closeResource(stmt);
			closeResource(dbCon);
		}
	}

}
