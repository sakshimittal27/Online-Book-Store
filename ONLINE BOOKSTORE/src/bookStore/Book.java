package bookStore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Book
{
	String sql;
	Scanner sc=new Scanner(System.in);
    String name, genre, author,releaseDate;
    int bID, quantity;
    float price, discount;
    public void addBook(int sID) throws Exception
    {
        Class.forName("com.mysql.cj.jdbc.Driver");  
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/book_store","root","1234");  
        Statement stmt=con.createStatement();  
        PreparedStatement mystmt=null;

    	System.out.println("Book name: ");
    	name= sc.nextLine();
    	System.out.println("Author: ");
    	author=sc.nextLine();
    	System.out.println("Quantity available: ");
    	quantity = sc.nextInt();
    	System.out.println("Price: ");
    	price= sc.nextFloat();
    	System.out.println("Discount: ");
    	discount= sc.nextFloat();
    	sc.nextLine();
    	System.out.println("Genre: ");
    	genre= sc.nextLine();
    	System.out.println("Release Date: ");
    	releaseDate= sc.nextLine();
    	// add the book with these details
    	sql="insert into book(name,author,quantity,price,discount,genre,releaseDate,sID) values (?,?,?,?,?,?,?,?)";
    	mystmt = con.prepareStatement(sql);
		mystmt.setString(1,name);
		mystmt.setString(2,author);
		mystmt.setInt(3,quantity);
		mystmt.setFloat(4,price);
		mystmt.setFloat(5,discount);
		mystmt.setString(6,genre);
		mystmt.setString(7,releaseDate);
		mystmt.setInt(8,sID);
		mystmt.execute();
    	System.out.println("Book successfully added!");
    	stmt.close();
    	mystmt.close();
    	con.close();
    }
    
    public void modifyBook(int sID) throws Exception
    {
        Class.forName("com.mysql.cj.jdbc.Driver");  
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/book_store","root","1234");  
        Statement stmt=con.createStatement();  
        PreparedStatement mystmt=null;
        PreparedStatement mystmt2=null;

    	System.out.println("Enter the ID of the book whose details has to be modified.");
    	int bookID=sc.nextInt();
    	sc.nextLine();
    	sql="select count(bID) from book where bID=? and sID=?";
    	mystmt = con.prepareStatement(sql);
		mystmt.setInt(1,bookID);
		mystmt.setInt(2,sID);
		ResultSet myrs=mystmt.executeQuery();
		int count=0;
		while(myrs.next())
		{
			count=myrs.getInt("count(bID)");
		}
		if (count==0)
		{
			System.out.println("You don't sell any such book.");
		}
		else
		{
			System.out.println("What do you want to modify?");
	    	System.out.println("1. Quantity available");
	    	System.out.println("2. Discount provided");
	    	int c = sc.nextInt();
	    	sc.nextLine();
	    	if(c==1)
	    	{
	    		System.out.println("Modified quantity: ");
	    		int quantity = sc.nextInt();
	    		sc.nextLine();
	    		sql="update book set quantity=? where bID=? and sID=?";
	    		mystmt2 = con.prepareStatement(sql);
	    		mystmt2.setInt(1,quantity);
	    		mystmt2.setInt(2,bookID);
	    		mystmt2.setInt(3,sID);
	    		mystmt2.execute();
	    		System.out.println("Book successfully modified!");
	    	}
	    	else if(c==2)
	    	{
	    		System.out.println("Modified discount: ");
	    		float discount = sc.nextInt();
	    		sc.nextLine();
	    		sql="update book set discount=? where bID=? and sID=?";
	    		mystmt2 = con.prepareStatement(sql);
	    		mystmt2.setFloat(1,discount);
	    		mystmt2.setInt(2,bookID);
	    		mystmt2.setInt(3,sID);
	    		mystmt2.execute();
	    		System.out.println("Book successfully modified!");
	    	}
	    	mystmt2.close();
		}
    	myrs.close();
    	stmt.close();
    	mystmt.close();
    	con.close();
    }
}