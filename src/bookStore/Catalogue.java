package bookStore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Catalogue
{
	Scanner sc = new Scanner(System.in);
	String sql;
	void sortBookByPrice() throws Exception
	{
        Class.forName("com.mysql.cj.jdbc.Driver");  
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/Book_Store","root","1234");  
        Statement stmt=con.createStatement();  
        System.out.println("BookID, name and price are: ");
		sql = "select bID, name, price from book order by price asc";
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next())
		{
			int bID=rs.getInt("bID");
			String name=rs.getString("name");
			float price =rs.getFloat("price");
			System.out.println(bID+"   "+name+" ----------->"+price);
		}
		 rs.close(); 
		 stmt.close();
	     con.close();
	}
	void sortBookByAlphabeticalOrder() throws Exception
	{
		int ch;
        Class.forName("com.mysql.cj.jdbc.Driver");  
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/Book_Store","root","1234");  
        Statement stmt=con.createStatement();  

		System.out.println("1. A-Z");
		System.out.println("2. Z-A");
		ch=sc.nextInt();
		if(ch==1)
		{
			sql = "select bID, name, price from book order by name asc";
		}
		if(ch==2)
		{
			sql = "select bID, name, price from book order by name desc";
		}
		else if(ch!=2 && ch!=1)
		{
			System.err.println("Invalid option");
			return;
		}
		System.out.println("BookID, name and price are: ");
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next())
		{
			int bID=rs.getInt("bID");
			String name=rs.getString("name");
			float price =rs.getFloat("price");
			System.out.println(bID+"   "+name+" -----------> "+price);
		}
		 rs.close(); 
		 stmt.close();
	    con.close();
	}
	void searchBookByAuthor(String author) throws Exception
	{
        Class.forName("com.mysql.cj.jdbc.Driver");  
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/Book_Store","root","1234");  
        Statement stmt=con.createStatement();  
        PreparedStatement mystmt=null;
        
		sql = "select bID, name, price, discount from book where author=?";
		mystmt = con.prepareStatement(sql);
		mystmt.setString(1,author);
		ResultSet rs = mystmt.executeQuery();
		System.out.println("BookID, name and price are: ");
		int a=0;
		while(rs.next())
		{
			int bID=rs.getInt("bID");
			String name=rs.getString("name");
			float price =rs.getFloat("price");
			System.out.println(bID+"   "+name+" -----------> "+price);
			a++;
		}
		if(a==0)
			System.out.println("Sorry! We do not sell any book of this particular author.");
		rs.close(); 
		mystmt.close();
		stmt.close();
	    con.close(); 
	}
	void searchBookByGenre(String genre) throws Exception
	{
        Class.forName("com.mysql.cj.jdbc.Driver");  
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/Book_Store","root","1234");  
        Statement stmt=con.createStatement();  
        PreparedStatement mystmt=null;
        
		sql = "select bID,name, price from book where genre=?";
		mystmt = con.prepareStatement(sql);
		mystmt.setString(1,genre);
		ResultSet rs = mystmt.executeQuery();
		System.out.println("BookID, name and price are: ");
		int a=0;
		while(rs.next())
		{
			int bID=rs.getInt("bID");
			String name=rs.getString("name");
			float price =rs.getFloat("price");
			System.out.println(bID+"   "+name+" -----------> "+price);
			a++;
		}
		if(a==0)
			System.out.println("Sorry! We do not sell any book of this particular genre.");
		rs.close(); 
		mystmt.close();
		stmt.close();
	    con.close(); 
	}
	void sortBookByDiscount() throws Exception
	{ 
        Class.forName("com.mysql.cj.jdbc.Driver");  
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/Book_Store","root","1234");  
        Statement stmt=con.createStatement();  

		sql = "select bID, name, price, discount from book order by discount desc";
		ResultSet rs = stmt.executeQuery(sql);
		System.out.println("BookID, name, price, discount are: ");
		while(rs.next())
		{
			int bID=rs.getInt("bID");
			String name=rs.getString("name");
			float price =rs.getFloat("price");
			float discount= rs.getFloat("discount");
			System.out.println(bID+"   "+name+" -----------> "+price+" -----------> "+discount+"%");
		}
		rs.close(); 
		stmt.close();
	    con.close(); 
	}
	void showtopSelledBooks() throws Exception
	{
        Class.forName("com.mysql.cj.jdbc.Driver");  
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/Book_Store","root","1234");  
        Statement stmt=con.createStatement();  

		sql = "select book.bID, name, price from book inner join (select bID,oID from orders) as od on od.bID=book.bID group by book.bID order by count(od.oID) desc";
		ResultSet rs = stmt.executeQuery(sql);
		System.out.println("BookID, name and price are: ");
		while(rs.next())
		{
			int bID=rs.getInt("bID");
			String name=rs.getString("name");
			float price =rs.getFloat("price");
			System.out.println(bID+"	"+name+"	"+price);
		}
		rs.close(); 
		stmt.close();
	    con.close(); 
	}
	void sortcurrentlyReleased() throws Exception
	{
        Class.forName("com.mysql.cj.jdbc.Driver");  
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/Book_Store","root","1234");  
        Statement stmt=con.createStatement();  

		sql = "select bID, name, price from book order by releaseDate desc";
		ResultSet rs = stmt.executeQuery(sql);
		System.out.println("BookID, name and price are: ");
		while(rs.next())
		{
			int bID=rs.getInt("bID");
			String name=rs.getString("name");
			float price =rs.getFloat("price");
			System.out.println(bID+"   "+name+" -----------> "+price);
		}
		rs.close(); 
		stmt.close();
	    con.close(); 
	}
}