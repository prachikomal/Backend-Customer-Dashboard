<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customer_invoice Data</title>
<link href='http://fonts.googleapis.com/css?family=Oxygen' rel='stylesheet' type='text/css'>
<style type="text/css">
table, td, th
{
border:1px solid green;
font-family: 'Oxygen', sans-serif;
}
th
{
background-color:green;
color:white;
}
body
{
	text-align: center;
}
.container
{
	margin-left: auto;
	margin-right: auto;
	width: 40em;
}
h4
{
	font-family: 'Oxygen', sans-serif;
	color:#1E90FF;
}
</style>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$("#tablediv").hide();
     $("#showTable").click(function(event){
           $.get('PopulateTable',function(responseJson) {
        	   if(responseJson!=null){
            	   $("#countrytable").find("tr:gt(0)").remove();
            	   var table1 = $("#countrytable");
	               $.each(responseJson, function(key,value) { 
	               		   var rowNew = $("<tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>");
	                       rowNew.children().eq(0).text(value['acct_doc_header_id']); 
	                       rowNew.children().eq(1).text(value['company_id']); 
	                       rowNew.children().eq(2).text(value['document_number']); 
	                       rowNew.children().eq(3).text(value['document_number_norm']); 
	                       rowNew.children().eq(4).text(value['business_code']); 
	                       rowNew.children().eq(5).text(value['create_year']); 
	                       rowNew.children().eq(6).text(value['document_line_number']); 
	                       rowNew.children().eq(7).text(value['doctype']); 
	                       rowNew.children().eq(8).text(value['customer_number']); 
	                       rowNew.children().eq(9).text(value['customer_number_norm']); 
	                       rowNew.children().eq(10).text(value['fk_customer_map_id']); 
	                       rowNew.children().eq(11).text(value['customer_name']); 
	                       rowNew.children().eq(12).text(value['division']); 
	                       rowNew.children().eq(13).text(value['document_create_date']); 
	                       rowNew.children().eq(14).text(value['document_create_date_norm']);
	                       rowNew.children().eq(15).text(value['posting_date']);
	                       rowNew.children().eq(16).text(value['posting_date_norm']); 
	                       rowNew.children().eq(17).text(value['posting_id']); 
	                       rowNew.children().eq(18).text(value['due_date']); 
	                       rowNew.children().eq(19).text(value['due_date_norm']); 
	                       rowNew.children().eq(20).text(value['order_date']); 
	                       rowNew.children().eq(21).text(value['order_date_norm']); 
	                       rowNew.children().eq(22).text(value['invoice_id']); 
	                       rowNew.children().eq(23).text(value['invoice_id_norm']); 
	                       rowNew.children().eq(24).text(value['baseline_create_date']); 
	                       rowNew.children().eq(25).text(value['invoice_date_norm']); 
	                       rowNew.children().eq(26).text(value['total_open_amount']); 
	                       rowNew.children().eq(27).text(value['total_open_amount_norm']); 
	                       rowNew.children().eq(28).text(value['cust_payment_terms']); 
	                       rowNew.children().eq(29).text(value['business_area']); 
	                       rowNew.children().eq(30).text(value['ship_date']); 
	                       rowNew.children().eq(31).text(value['ship_to']); 
	                       rowNew.children().eq(32).text(value['clearing_date']); 
	                       rowNew.children().eq(33).text(value['clearing_date_norm']); 
	                       rowNew.children().eq(34).text(value['reason_code']); 
	                       rowNew.children().eq(35).text(value['isOpen']); 
	                       rowNew.children().eq(36).text(value['discount_due_date_norm']); 
	                       rowNew.children().eq(37).text(value['debit_credit_indicator']); 
	                       rowNew.children().eq(38).text(value['payment_method']); 
	                       rowNew.children().eq(39).text(value['document_creation_date']); 
	                       rowNew.children().eq(40).text(value['invoice_amount_doc_currency']); 
	                       rowNew.children().eq(41).text(value['document_id']); 
	                       rowNew.children().eq(42).text(value['actual_open_amount']); 
	                       rowNew.children().eq(43).text(value['paid_amount']); 
	                       rowNew.children().eq(44).text(value['dayspast_due']); 
	                       rowNew.children().eq(45).text(value['invoice_age']); 
	                       rowNew.children().eq(46).text(value['disputed_amount']); 
	                     
	                        
	                       
	                       rowNew.appendTo(table1);
	               });
                }
            });
            $("#tablediv").show();          
	 });      
});
</script>
</head>
<body class="container">
<h1>Retrieved Data</h1>

<input type="button" value="Show Table" id="showTable"/>
<br/>
<br/>
<div id="tablediv">
<table cellspacing="0" id="countrytable"> 
    <tr> 
        <th scope="col">acct_doc_header_id</th> 
        <th scope="col">company_id</th> 
        <th scope="col">document_number</th> 
        <th scope="col">document_number_norm</th> 
        <th scope="col">business_code</th>
        <th scope="col">create_year</th>
        <th scope="col">document_line_number</th>
        <th scope="col">doctype</th>
        <th scope="col">customer_number</th>
        <th scope="col">customer_number_norm</th>
        <th scope="col">fk_customer_map_id</th>
        <th scope="col">customer_name</th>
        <th scope="col">division</th>
        <th scope="col">document_create_date</th>
        <th scope="col">document_create_date_norm</th>
        <th scope="col">posting_date</th>
        <th scope="col">posting_date_norm</th>
        <th scope="col">posting_id</th>
        <th scope="col">due_date</th>
        <th scope="col">due_date_norm</th>
        <th scope="col">order_date</th>
        <th scope="col">order_date_norm</th>
        <th scope="col">invoice_id</th>
        <th scope="col">invoice_id_norm</th>
        <th scope="col">baseline_create_date</th>
        <th scope="col">invoice_date_norm</th>
        <th scope="col">total_open_amount</th>
        <th scope="col">total_open_amount_norm</th>
        <th scope="col">cust_payment_terms</th>
        <th scope="col">business_area</th>
        <th scope="col">ship_date</th>
        <th scope="col">ship_to</th>
        <th scope="col">clearing_date</th>
        <th scope="col">clearing_date_norm</th>
        <th scope="col">reason_code</th>
        <th scope="col">isOpen</th>
        <th scope="col">discount_due_date_norm</th>
        <th scope="col">debit_credit_indicator</th>
        <th scope="col">payment_method</th>
        <th scope="col">document_creation_date</th>
        <th scope="col">invoice_amount_doc_currency</th>
        <th scope="col">document_id</th>
        <th scope="col">actual_open_amount</th>
        <th scope="col">paid_amount</th>
        <th scope="col">dayspast_due</th>
        <th scope="col">invoice_age</th>
        <th scope="col">disputed_amount</th>
       
    </tr> 
</table>
</div>
</body>
</html>