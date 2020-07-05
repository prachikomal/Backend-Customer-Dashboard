package test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;


public class FetchData {

    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection != null)
            return connection;
        else {
            try {
                Properties prop = new Properties();
                InputStream inputStream = FetchData.class.getClassLoader().getResourceAsStream("/db.properties");
                prop.load(inputStream);
                String driver = prop.getProperty("driver");
                String url = prop.getProperty("url");
                String user = prop.getProperty("user");
                String password = prop.getProperty("password");
                Class.forName(driver);
                connection = DriverManager.getConnection(url, user, password);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return connection;
        }

    }
    
    public static ArrayList<Countries> getAllCountries() {
    	connection = FetchData.getConnection();
        ArrayList<Countries> countryList = new ArrayList<Countries>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from customer_invoice limit 1000");
        
            while(rs.next()) {	
            	Countries p=new Countries();
            	
            	p.setAcct_doc_header_id(rs.getInt("Acct_doc_header_id"));
                p.setCompany_id(rs.getInt("Company_id"));
                p.setDocument_number(rs.getInt("Document_number"));
                //p.setDocument_number_norm(rs.getInt("Document_number_norm"));
                p.setBusiness_code(rs.getString("business_code"));
               // System.out.println(words[5]);
                //p.setCreate_year(rs.getString("Create_year"));
                //p.setDocument_line_number(rs.getInt("Document_Line_number"));
                p.setDoctype(rs.getString("Doctype"));
                p.setCustomer_number(rs.getInt("Customer_number"));
                //p.setCustomer_number_norm(rs.getInt("Customer_number_norm"));
                p.setFk_customer_map_id(rs.getInt("fk_Customer_map_id"));
                p.setCustomer_name(rs.getString("Customer_name"));
                //p.setDivision(rs.getString("Customer_number"));
                p.setDocument_create_date(rs.getDate("Document_create_date"));
                //p.setDocument_create_date_norm(rs.getDate("Document_create_date_norm"));
                //p.setPosting_date(rs.getDate("Posting_date"));
                //p.setPosting_date_norm(rs.getDate("Posting_date_norm"));
                //p.setPosting_id(rs.getString("Posting_id"));
                //p.setDue_date(rs.getDate("Due_date"));sss
                //p.setDue_date_norm(rs.getDate("Due_date_norm")) ;
                //p.setOrder_date(rs.getDate("Order_date"));
                //p.setOrder_date_norm(rs.getDate("Order_date_norm"));
                p.setInvoice_id(rs.getInt("Invoice_id"));
                //p.setInvoice_id_norm(rs.getInt("Invoice_id_norm"));
                p.setBaseline_create_date(rs.getDate("Baseline_create_date"));
                p.setInvoice_date_norm(rs.getDate("Invoice_date_norm"));
                p.setTotal_open_amount(rs.getFloat("Total_open_amount"));
                //p.setTotal_open_amount_norm(rs.getFloat("Total_open_amount_norm"));
                p.setCust_payment_terms(rs.getInt("Cust_payment_terms"));
                //p.setBusiness_area(rs.getString("Business_area"));
                p.setShip_date(rs.getDate("ship_date"));                  ///////////
                //p.setShip_to(rs.getString("Ship_to"));
                p.setClearing_date(rs.getDate("Clearing_date"));
                //p.setClearing_date_norm(rs.getDate("Clearing_date_norm"));
                //p.setReason_code(rs.getString("Reason_code"));s
                p.setIsOpen(rs.getInt("IsOpen"));
                //p.setDiscount_due_date_norm(rs.getDate("Discount_due_date_norm"));
                //p.setDebit_credit_indicator(rs.getString("Debit_credit_indicator"));
                //p.setPayment_method(rs.getString("Payment_method"));
                p.setDocument_creation_date(rs.getDate("Document_creation_date"));
                p.setInvoice_amount_doc_currency(rs.getFloat("Invoice_amount_doc_currency"));
                p.setDocument_id(rs.getInt("Document_id"));
                p.setActual_open_amount(rs.getFloat("Actual_open_amount"));
                p.setPaid_amount(rs.getFloat("Paid_amount"));
                p.setDayspast_due(rs.getInt("Dayspast_due"));
                p.setInvoice_age(rs.getInt("Invoice_age"));
               // p.setDisputed_amount(rs.getFloat("Disputed_amount"));
              
                
            	 
                
            	countryList.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return countryList;
    }
}