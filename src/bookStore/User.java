package bookStore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.Scanner;

public class User
{
	String sql;
    Scanner sc = new Scanner(System.in);
	String name, address, emailID, password, zipcode, city, state, country, contactNo,accountNo;
    char gender; int ch;
    float balance;
    int uID;
    
    public void addUser() throws Exception
    {
        Class.forName("com.mysql.cj.jdbc.Driver");  
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/Book_Store","root","1234");  
        Statement stmt=con.createStatement();  
        PreparedStatement mystmt=null;
    	System.out.println("Kindly enter the following details to register yourself.");
   	 
	 		 System.out.println("Name: ");
	 		 name = sc.nextLine();
	 		 System.out.println("Gender(M/F): ");
	 		 gender = sc.next().charAt(0);
	 		 System.out.println("Address: ");
	 		 address = sc.nextLine();
	 		 address = sc.nextLine();
	 		 System.out.println("Email ID: ");
	 		 emailID = sc.nextLine();
	 		 System.out.println("Password: ");
	 		 password = sc.nextLine();
	 		 System.out.println("City: ");
	 		 city = sc.nextLine();
	 		 System.out.println("Zipcode: ");
	 		 zipcode = sc.nextLine();
	 		 System.out.println("State: ");
	 		 state = sc.nextLine();
	 		 System.out.println("Country: ");
	 		 country = sc.nextLine();
	 		 System.out.println("Contact Number: ");
	 		 contactNo = sc.nextLine();
	 		 System.out.println();
	 		 System.out.println("1.Do you want to register as a seller?");
	 		 System.out.println("2.Do you want to register as a customer?");
	 		 ch = sc.nextInt();
	 		 sc.nextLine();
	 		 int idshow=0;
	 		 switch(ch)
	 		 {
	 		 	case 1:
	     				idshow=0;
	     				sql = "insert into seller(name,gender,address,emailID,password,city,zipcode,state,country,contactNo) values (?,?,?,?,?,?,?,?,?,?)";
		 		 		mystmt = con.prepareStatement(sql);
		     			mystmt.setString(1,name);
		     			mystmt.setString(2,String.valueOf(gender));
		     			mystmt.setString(3,address);
		     			mystmt.setString(4,emailID);
		     			mystmt.setString(5,password);
		     			mystmt.setString(6,city);
		     			mystmt.setString(7,zipcode);
		     			mystmt.setString(8,state);
		     			mystmt.setString(9,country);
		     			mystmt.setString(10,contactNo);
		    			
		    			try{mystmt.execute();
		 		 		System.out.println("Seller successfully registered!");
		 		 		
		 		 		sql="select sID from seller where contactNo=?";
		 		 		mystmt = con.prepareStatement(sql);
		     			mystmt.setString(1,contactNo);
		     			ResultSet ra=mystmt.executeQuery();
		     			
		     			while(ra.next())
		     			{
		     				idshow=ra.getInt("sID");
		     			}
		     			ra.close();
		     			System.out.println(idshow+" is your ID. Keep it safe with you for future references.");}
	     			
	     			catch(SQLIntegrityConstraintViolationException e) {
	     				System.out.println("Sorry! We have a seller with either same email or same contact  details");}
		    		catch(SQLException e) {
		    			System.err.println("Sorry! We cannot register you. You have entered some invalid details"+e.getMessage());
		    		}
	     			
	 		 		
	 		 	break;
	 		 	case 2:
	 		 		System.out.println("Account number and balance: ");
	 		 		accountNo=sc.nextLine();
	 		 		balance=sc.nextFloat();
	     		
	     				idshow=0;
	     				sql = "insert into customer(name,gender,address,emailID,password,city,zipcode,state,country,contactNo,accountNo,balance_amt) values (?,?,?,?,?,?,?,?,?,?,?,?)";
		 		 		mystmt = con.prepareStatement(sql);
		     			mystmt.setString(1,name);
		     			mystmt.setString(2,String.valueOf(gender));
		     			mystmt.setString(3,address);
		     			mystmt.setString(4,emailID);
		     			mystmt.setString(5,password);
		     			mystmt.setString(6,city);
		     			mystmt.setString(7,zipcode);
		     			mystmt.setString(8,state);
		     			mystmt.setString(9,country);
		     			mystmt.setString(10,contactNo);
		     			mystmt.setString(11,accountNo);
		     			mystmt.setFloat(12,balance);
		    			try{mystmt.execute();
		 		 		System.out.println("Customer successfully registered!");
		 		 		
		 		 		sql="select cID from customer where contactNo=? and accountNo=?";
		 		 		mystmt = con.prepareStatement(sql);
		     			mystmt.setString(1,contactNo);
		     			mystmt.setString(2,accountNo);
		     			ResultSet ra2=mystmt.executeQuery();
		     			while(ra2.next())
		     			{
		     				idshow=ra2.getInt("cID");
		     			}
		     			ra2.close();
		     			System.out.println(idshow+" is your ID. Keep it safe with you for future references.");}
		    			catch(SQLIntegrityConstraintViolationException e) {
		     				System.out.println("Sorry! We have a seller with either same email or same contact or same account details");}
		    			catch(SQLException e) {
			    			System.out.println("Sorry! We cannot register you. You have entered some invalid details");
			    		}
	 		 	break;
	 		 	default:
	 		 		System.out.println("Sorry, you choose a wrong option.");
	 		 } 
	 		 mystmt.close();
			 stmt.close();
		    con.close(); 
    }
    public void verifyUser(int valid[]) throws Exception   // will return 10 for valid seller login, 11 for valid customer login, 0 for invalid login at 0th index
    {
        Class.forName("com.mysql.cj.jdbc.Driver");  
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/Book_Store","root","1234");  
        Statement stmt=con.createStatement();  
        PreparedStatement mystmt=null;
    	String password;int id;
    	System.out.println("1.Are you a seller?");
   	 	System.out.println("2.Are you a customer?");
   	 	valid[0]= 0; valid[1]=0;
   	 	ch = sc.nextInt();
   	    sc.nextLine();
   	 	ResultSet rs;
   	 	int count=0;
   	 	switch(ch)
   	 	{
   	 		case 1:
   	 			System.out.println("ID: ");
   	 			id= sc.nextInt();
   	 			sc.nextLine();
   	 			System.out.println("Password: ");   
   	 			password= sc.nextLine();

   	 			sql="select count(sID) as exist from seller where (sID=? and password=?)";
   	 			mystmt = con.prepareStatement(sql);
   	 			mystmt.setInt(1,id);
   	 			mystmt.setString(2, password);  	 			
   	 			rs= mystmt.executeQuery();
   	 			while(rs.next())
   	 			{
   	 				count=rs.getInt("exist");
   	 			}
   	 			rs.close();
   	 			if(count==1)
   	 			{
   	 				valid[0]=10; valid[1]=id;
   	 			}

   	 		break;
   	 		case 2:
   	 			System.out.println("ID: ");
   	 			id= sc.nextInt();
   	 			sc.nextLine();
   	 			System.out.println("Password: ");
   	 			password= sc.nextLine();
   	 			
   	 			sql="select count(cID) as exist from customer where cID=? and password=?";
   	 			mystmt = con.prepareStatement(sql);
	 			mystmt.setInt(1,id);
	 			mystmt.setString(2, password); 
	 			rs= mystmt.executeQuery();
	 			while(rs.next())
	 			{
	 				count=rs.getInt("exist");
	 			}
	 			rs.close();
	 			if(count==1)
	 			{
	 				valid[0]=11; valid[1]=id;
	 			}
   	 		break;
   	 		default:
   	 			valid[0]=-1;
   	 			System.err.println("Sorry, you choose a wrong option");
   	 	}
   	 	try 
   	 	{
   	 		if(mystmt!=null)
   	 		{
   	 			mystmt.close();
   	 		}
   	 	}
   	 	catch(Exception e)
   	 	{
   	 		System.out.println("Error in closing mystatement");
   	 	}
   	 	
   	 	stmt.close();
	 	con.close(); 
    }
    public void deleteUser(int valid[]) throws Exception
    {
        Class.forName("com.mysql.cj.jdbc.Driver");  
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/Book_Store","root","1234");  
        Statement stmt=con.createStatement();  
        PreparedStatement mystmt=null;
    	int id;
    	if(valid[0]==10)   //seller
    	{
    		id = valid[1];  // delete the seller with this id and also all his book details
    		sql= "delete from book where sID=?";
    		mystmt = con.prepareStatement(sql);
 			mystmt.setInt(1,id);
    		mystmt.execute();
    		sql="delete from seller where sID=?";
    		mystmt = con.prepareStatement(sql);
 			mystmt.setInt(1,id);
    		mystmt.execute();
    		
    		System.out.println("Seller successfully deleted!");
    	}
    	else if(valid[0]==11)  //customer
    	{
    		id = valid[1];     // delete the customer with this id and all his order details
    		sql= "delete from orders where cID=?";
    		mystmt = con.prepareStatement(sql);
 			mystmt.setInt(1,id);
    		mystmt.execute();
    		sql= "delete from cart where cID=?";
    		mystmt = con.prepareStatement(sql);
 			mystmt.setInt(1,id);
    		mystmt.execute();
    		sql="delete from customer where cID=?";
    		mystmt = con.prepareStatement(sql);
 			mystmt.setInt(1,id);
    		mystmt.execute();
    		
    		
    		System.out.println("Customer successfully deleted!");
    	}
    	mystmt.close();
		stmt.close();
	    con.close(); 
    }
    public void showDetails(int valid[]) throws Exception
    {
        Class.forName("com.mysql.cj.jdbc.Driver");  
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/Book_Store","root","1234");  
        Statement stmt=con.createStatement();  
        PreparedStatement mystmt=null;
    	int id;
    	if(valid[0]==10)   //seller
    	{
    		id = valid[1];   //show details of the seller of this id
    		sql="select name, emailID, city, zipcode, state, country, contactNo from seller where sID=?";
    		mystmt = con.prepareStatement(sql);
 			mystmt.setInt(1,id);
    		ResultSet rs= mystmt.executeQuery();
    		System.out.println("Details are: ");
    		while(rs.next()) 
    		{
    			String name=rs.getString("name");
    			String email=rs.getString("emailID");
    			String city=rs.getString("city");
    			String zipcode=rs.getString("zipcode");
    			String state=rs.getString("state");
    			String country=rs.getString("country");
    			String contactNo=rs.getString("contactNo");
    			System.out.println("Name: "+name);
    			System.out.println("EmailID: "+email);
    			System.out.println("City: "+city);
    			System.out.println("Zipcode: "+zipcode);
    			System.out.println("State: "+state);
    			System.out.println("Country: "+country);
    			System.out.println("ContactNo: "+contactNo);
    			System.out.println();
    		}
    		rs.close();
    	}
    	else if(valid[0]==11)  //customer
    	{
    		id = valid[1];   //show details of the customer of this id
    		sql="select name, emailID, city, zipcode, state, country, contactNo, accountNo, balance_amt from customer where cID=?";
    		mystmt = con.prepareStatement(sql);
 			mystmt.setInt(1,id);
    		ResultSet rs= mystmt.executeQuery();
    		System.out.println("Details are: ");
    		while(rs.next()) 
    		{
    			String name=rs.getString("name");
    			String email=rs.getString("emailID");
    			String city=rs.getString("city");
    			String zipcode=rs.getString("zipcode");
    			String state=rs.getString("state");
    			String country=rs.getString("country");
    			String contactNo=rs.getString("contactNo");
    			String accountNo=rs.getString("accountNo");
    			float balance=rs.getFloat("balance_amt");
    			System.out.println("Name: "+name);
    			System.out.println("EmailID: "+email);
    			System.out.println("City: "+city);
    			System.out.println("Zipcode: "+zipcode);
    			System.out.println("State: "+state);
    			System.out.println("Country: "+country);
    			System.out.println("ContactNo: "+contactNo);
    			System.out.println("AccountNo: "+accountNo);
    			System.out.println("Balance Amount: "+balance);
    			System.out.println();
    		}
    		rs.close();
    	}
    	mystmt.close();
		stmt.close();
	    con.close(); 
    }
    public void modifyDetails(int valid[]) throws Exception
    {
        Class.forName("com.mysql.cj.jdbc.Driver");  
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/Book_Store","root","1234");  
        Statement stmt=con.createStatement();  
        PreparedStatement mystmt=null;
    	int id;
    	int choice;
    	String contactNo,email;
    	if(valid[0]==10)   //seller
    	{
    		id = valid[1];			//modify details of the seller of this id
    		System.out.println("1. Modify your contact number");
    		System.out.println("2. Modify your emailaddress");
    		choice=sc.nextInt();
    		sc.nextLine();
    		if(choice==1)
    		{
    			System.out.println("New contact number: ");
    			contactNo=sc.nextLine();
    			sql="update seller set contactNo=? where sID=?";
    			mystmt = con.prepareStatement(sql);
     			mystmt.setString(1,contactNo);
     			mystmt.setInt(2,id);
    			mystmt.execute();
    			System.out.println("Successfully updated");
    		}
    		if(choice==2)
    		{
    			System.out.println("New email address: ");
    			email=sc.nextLine();
    			sql="update seller set emailID=? where sID=?";
    			mystmt = con.prepareStatement(sql);
     			mystmt.setString(1,email);
     			mystmt.setInt(2,id);
    			mystmt.execute();
    			System.out.println("Successfully updated");
    		}
    	}
    	else if(valid[0]==11)  //customer
    	{
    		id = valid[1];			//modify details of the customer of this id
    		System.out.println("1. Modify your contact number");
    		System.out.println("2. Modify your email address");
    		choice=sc.nextInt();
    		sc.nextLine();
    		if(choice==1)
    		{
    			System.out.println("New contact number: ");
    			contactNo=sc.nextLine();
    			sql="update customer set contactNo=? where cID=?";
    			mystmt = con.prepareStatement(sql);
     			mystmt.setString(1,contactNo);
     			mystmt.setInt(2,id);
    			mystmt.execute();
    			System.out.println("Successfully updated");
    		}
    		if(choice==2)
    		{
    			System.out.println("New email address: ");
    			email=sc.nextLine();
    			sql="update customer set emailID=? where cID=?";
    			mystmt = con.prepareStatement(sql);
     			mystmt.setString(1,email);
     			mystmt.setInt(2,id);
    			mystmt.execute();
    			System.out.println("Successfully updated");
    		}
    	}
    	mystmt.close();
		 stmt.close();
	    con.close();
    }
}