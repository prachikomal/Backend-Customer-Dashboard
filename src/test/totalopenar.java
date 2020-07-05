package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class totalopenar
 */
@WebServlet("/totalopenar")
public class totalopenar extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Gson gson = new Gson();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public totalopenar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			//response.setContentType("text/html");
			Connection conn = null;
			
			 String url = "jdbc:mysql://localhost:3306/project";
			 String user = "root";
			 String password = "root";
			
			 Class.forName("com.mysql.cj.jdbc.Driver");
			 conn = DriverManager.getConnection(url, user, password);
			 PreparedStatement pstmt=conn.prepareStatement("SELECT ROUND(SUM(actual_open_amount)) FROM customer_invoice WHERE isOpen=1;");
			 
			 ResultSet rs= pstmt.executeQuery();
			 rs.next();
			 String json = gson.toJson(rs.getInt(1));
			 PrintWriter out = response.getWriter();
			 //System.out.println(json);
		     //response.setContentType("application/json");
		     //response.setCharacterEncoding("UTF-8");
		     out.print(json);
		     out.flush();
		 }catch(Exception e) {
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
