package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/PopulateTable")
public class PopulateTable extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public static ResultSet dataBaseConnection() throws Exception{
		Connection conn = null;
		 String url = "jdbc:mysql://localhost:3306/project";
		 String user = "root";
		 String password = "root";
		
    	Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection(url, user, password);
		//conn.setAutoCommit(false);

		if (conn != null) {
			System.out.println("Connected to the mysql server successfully.");
		} else {
			System.out.println("Failed to make connection!");
		}
		
		String sql = "SELECT pk_id,company_id,acct_doc_header_id,document_number,business_code,doctype,"
				+ "customer_number,fk_customer_map_id,customer_name,"
				+ "document_create_date,baseline_create_date,invoice_date_norm,"
				+ "invoice_id,total_open_amount,cust_payment_terms,clearing_date,"
				+ "isOpen,ship_date,paid_amount,dayspast_due,document_id,document_creation_date,"
				+ "actual_open_amount,invoice_age,invoice_amount_doc_currency"
				+ " FROM customer_invoice";
		PreparedStatement pm = null;
		pm = conn.prepareStatement(sql);
		ResultSet rs = pm.executeQuery();
		return rs;
    	
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			ResultSet rs = dataBaseConnection();
			ArrayList<Countries> custList = new ArrayList<Countries>();
			Gson gson = new Gson();
			
			int count = 0;
			while(rs.next()) {
				++count;
				Countries cust = new Countries();
				cust.setPk_id(count);
				cust.setAcct_doc_header_id(rs.getInt("acct_doc_header_id"));
				cust.setCompany_id(rs.getInt("company_id"));
				cust.setDocument_number(rs.getInt("document_number"));
				//cust.setDocument_number_norm(rs.getInt("document_number_norm"));
				cust.setBusiness_code(rs.getString("business_code"));
				//cust.setCreate_year(rs.getString("create_year")!=null ? rs.getString("create_year"): new String(""));
				//cust.setDocument_line_number(rs.getInt("document_line_number"));
				cust.setDoctype(rs.getString("doctype"));
				cust.setCustomer_number(rs.getInt("customer_number"));
				//cust.setCustomer_number_norm(rs.getInt("customer_number_norm"));
				cust.setFk_customer_map_id(rs.getInt("fk_customer_map_id"));
				cust.setCustomer_name(rs.getString("customer_name"));
				//cust.setDivision(rs.getString("division")!= null ? rs.getString("division") : new String(""));
				cust.setDocument_create_date(rs.getDate("document_create_date")!=null ? rs.getDate("document_create_date"):null);//new Date(0));
				//cust.setDocument_create_date_norm(rs.getDate("document_create_date_norm")!=null ? rs.getDate("document_create_date_norm"):new Date(0));
				//cust.setPosting_date(rs.getDate("posting_date")!=null ? rs.getDate("posting_date"):new Date(0));
				//cust.setPosting_date_norm(rs.getDate("posting_date_norm")!=null ? rs.getDate("posting_date"):new Date(0));
				//cust.setPosting_id(rs.getString("posting_id")!= null ? rs.getString("posting_id") : new String(""));
				//cust.setDue_date(rs.getDate("due_date")!=null ? rs.getDate("due_date"):new Date(0));
				//cust.setDue_date_norm(rs.getDate("due_date_norm")!=null ? rs.getDate("due_date_norm"):new Date(0));
				//cust.setOrder_date(rs.getDate("order_date")!=null ? rs.getDate("order_date"):new Date(0));
				//cust.setOrder_date_norm(rs.getDate("order_date_norm")!=null ? rs.getDate("order_date_norm"):new Date(0));
				cust.setInvoice_id(rs.getInt("invoice_id"));
				//cust.setInvoice_id_norm(rs.getInt("invoice_id_norm"));
				cust.setBaseline_create_date(rs.getDate("baseline_create_date")!=null ? rs.getDate("baseline_create_date"):null);//:new Date(0));
				cust.setInvoice_date_norm(rs.getDate("invoice_date_norm")!=null ? rs.getDate("invoice_date_norm"):null);//:new Date(0));
				cust.setTotal_open_amount(rs.getFloat("total_open_amount"));
				//cust.setTotal_open_amount_norm(rs.getDouble("total_open_amount_norm"));
				cust.setCust_payment_terms(rs.getInt("cust_payment_terms"));
				//cust.setBusiness_area(rs.getString("business_area")!= null ? rs.getString("business_area") : new String(""));
				cust.setShip_date(rs.getDate("ship_date")!=null ? rs.getDate("ship_date"):null);//:new Date(0));
				//cust.setShip_to(rs.getString("ship_to")!= null ? rs.getString("ship_to"): new String(""));
				cust.setClearing_date(rs.getDate("clearing_date")!=null ? rs.getDate("clearing_date"):null);//new Date(0));
				//cust.setClearing_date_norm(rs.getDate("clearing_date_norm")!=null ? rs.getDate("clearing_date_norm"):new Date(0));
				//cust.setReason_code(rs.getString("reason_code")!= null ? rs.getString("reason_code"): new String(""));
				cust.setIsOpen(rs.getInt("isOpen"));
				//cust.setDiscount_due_date_norm(rs.getDate("discount_due_date_norm")!=null ? rs.getDate("discount_due_date_norm"):new Date(0));
				//cust.setDebit_credit_indicator(rs.getString("debit_credit_indicator")!= null ? rs.getString("debit_credit_indicator"): new String(""));
				//cust.setPayment_method(rs.getString("payment_method")!= null ? rs.getString("payment_method"): new String(""));
				cust.setDocument_creation_date(rs.getDate("document_creation_date")!=null ? rs.getDate("document_creation_date"):null);//:new Date(0));
				cust.setInvoice_amount_doc_currency(rs.getFloat("invoice_amount_doc_currency"));
				cust.setDocument_id(rs.getInt("document_id"));
				cust.setActual_open_amount(rs.getFloat("actual_open_amount"));
				cust.setPaid_amount(rs.getFloat("paid_amount"));
				cust.setDayspast_due(rs.getInt("dayspast_due"));
				cust.setInvoice_age(rs.getInt("invoice_age"));
				//cust.setDisputed_amount(rs.getDouble("disputed_amount"));
							
				custList.add(cust);
				System.out.println(count);
			}
			
			String jsonResponse = gson.toJson(custList);
			PrintWriter out = response.getWriter();
			//response.setContentType("application/json");
			//response.setCharacterEncoding("UTF-8");
			out.print(jsonResponse);
			out.flush();
			//out.println("<html><body>"+jsonResponse+"</body></html>");
			
			
			
		
		
		
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	

	
	
	
	/*
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
*/
}