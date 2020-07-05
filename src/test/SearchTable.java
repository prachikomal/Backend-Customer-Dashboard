package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Out;

import com.google.gson.Gson;

@WebServlet("/Search")
public class SearchTable extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Gson gson = new Gson();
   
    public SearchTable() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			response.setContentType("text/html");
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/project","root","root");
			//company_name
			String sql = "SELECT  customer_name,customer_number, SUM(ROUND(actual_open_amount)) FROM customer_invoice GROUP BY  customer_name;";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			ResultSet rs= pstmt.executeQuery();
			ArrayList<Countries> data = new ArrayList<Countries>();
			//rs.next();
			while(rs.next()) {
				Countries p=new Countries();
				p.setCustomer_name(rs.getString(1));
				p.setCustomer_number(rs.getInt(2));
				 p.setTotal_open_amount(rs.getFloat(3));
				data.add(p);
			}
			String json = gson.toJson(data);
			PrintWriter out=response.getWriter();
			
			out.print(json);
		    out.flush();
		}
		catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}