package bookStore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

class Cart						// Customer has a Cart
{
	String sql;
	Scanner sc = new Scanner (System.in);
	public void showCart(int cID) throws Exception
	{
        Class.forName("com.mysql.cj.jdbc.Driver");  
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/Book_Store","root","1234");  
        Statement stmt=con.createStatement();  
        PreparedStatement mystmt=null;
        
        String sql = "Select book.bID,name,author,price from book inner join (select bID from cart where cID = ?) as ct where ct.bID = Book.bID";
    	mystmt = con.prepareStatement(sql);
    	mystmt.setInt(1,cID);
    	ResultSet myRs = mystmt.executeQuery();
    	System.out.println("Books with following book IDs are there in cart");
    	int a=0;
    	while(myRs.next())
    	{
    		int Book_id = myRs.getInt("bID");
    		String name =myRs.getString("name");
			String author=myRs.getString("author");
			float price=myRs.getFloat("price");
			System.out.println(Book_id+"	"+name+" -----------> "+author+" -----------> "+price);
			a++;
    	}
    	if(a==0)
    		System.out.println("Your cart is empty!");
		myRs.close(); 
		mystmt.close();
		stmt.close();
	    con.close(); 
	}
	
		public void addToCart(int cID) throws Exception
		{
	        Class.forName("com.mysql.cj.jdbc.Driver");  
	        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/Book_Store","root","1234");  
	        Statement stmt=con.createStatement();  
	        PreparedStatement mystmt=null;
	        
	        System.out.println("Enter the ID of the book that you want to add to the cart");
			int bookID=sc.nextInt();
			sc.nextLine();
			sql="select count(bID) from book where bID=?";
			mystmt = con.prepareStatement(sql);
	    	mystmt.setInt(1,bookID);
	    	ResultSet myRs = mystmt.executeQuery();
	    	int count=0;
	    	while(myRs.next())
	    	{
	    		count=myRs.getInt("count(bID)");
	    	}
	    	if(count==0)
	    		System.out.println("We don't have any such book.");
	    	else
	    	{
	            sql = "Insert into Cart (cID,bID) values (?,?)";
	        	mystmt = con.prepareStatement(sql);
	        	mystmt.setInt(1,cID);
	        	mystmt.setInt(2,bookID);
	        	mystmt.execute();
	    		System.out.println("Successfully added to the cart!");
	    	}
	    	myRs.close();
			mystmt.close();
			stmt.close();
		    con.close();
		}
		
		public void removeFromCart(int cID) throws Exception
		{
			String sql;
	        Class.forName("com.mysql.cj.jdbc.Driver");  
	        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/Book_Store","root","1234");  
	        Statement stmt=con.createStatement();  
	        PreparedStatement mystmt=null;

			System.out.println("Enter the ID of the book that you want to remove from the cart");
			int bookID=sc.nextInt();
			sc.nextLine();
			
			//checking if it exists
			sql ="select count(cartID) from cart where cID=? and bID=?";
			mystmt = con.prepareStatement(sql);
			mystmt.setInt(1,cID);
			mystmt.setInt(2,bookID);
			ResultSet myRs = mystmt.executeQuery();
			int count=0;
			while(myRs.next())
			{
				count=myRs.getInt("count(cartID)");
			}
			if(count==0)
			{
				System.out.println("No such book exist in the cart.");
			}
			else
			{
				//delete this book from customer's cart
				sql = "Delete from Cart where cID = ? and bID = ?";
				mystmt = con.prepareStatement(sql);
				mystmt.setInt(1,cID);
				mystmt.setInt(2,bookID);
				mystmt.execute();

				System.out.println("Successfully removed!");
			}	
			myRs.close();
			mystmt.close();
			stmt.close();
		    con.close(); 
		}
	
	public void placeOrder(int cID) throws Exception
	{
        Class.forName("com.mysql.cj.jdbc.Driver");  
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/Book_Store","root","1234");  
        Statement stmt=con.createStatement();  
        PreparedStatement mystmt=null;
        
        showCart(cID);
		System.out.println("Enter the id of the book that you want to order.");
		int book_id=sc.nextInt();
		sc.nextLine();

		sql = "select * from cart where Cart.cID =?  and Cart.bID = ?";
		mystmt = con.prepareStatement(sql);
		mystmt.setInt(1,cID);
		mystmt.setInt(2,book_id);
		ResultSet myRs = mystmt.executeQuery();
		if(!myRs.next())
			System.out.println("No such book exists in cart");
		
		else
		{	                                                                            // customer has this book in cart
			sql = "select quantity from Book where Book.bID =?";
			mystmt = con.prepareStatement(sql);
			mystmt.setInt(1,book_id);
			ResultSet myRs2 =mystmt.executeQuery();
			int book_quan=0;
			while(myRs2.next())
			{
				book_quan = myRs2.getInt("quantity");
			}
			myRs2.close();
			if(book_quan<=0)
				System.out.println("Book currently not available");
			else
			{										// book available
				sql = "select price from Book where bID =?";
				mystmt = con.prepareStatement(sql);
				mystmt.setInt(1,book_id);
				ResultSet myRs3 =mystmt.executeQuery();
				float book_price=0;
				while(myRs3.next())
				{
					book_price = myRs3.getFloat("price");
				}	
				myRs3.close();
				sql = "select balance_amt from Customer where Customer.cID =?";
				mystmt = con.prepareStatement(sql);
				mystmt.setInt(1,cID);
				ResultSet myRs4 =mystmt.executeQuery();
				float balance=0;
				while(myRs4.next())
				{
					balance = myRs4.getFloat("balance_amt");
				}
				myRs4.close();
				if(balance<book_price)
				{
					System.out.println("Sorry! Insufficient amount in bank balance.");
				}
				else
				{										// customer has sufficient money
					sql = "update Book set quantity = ? where bID =?";		// updated quantity
					mystmt = con.prepareStatement(sql);
					mystmt.setInt(1,book_quan-1);
					mystmt.setInt(2,book_id);
					mystmt.execute();

					sql = "update Customer set balance_amt =? where cID=?";		// update card amount
					mystmt = con.prepareStatement(sql);
					mystmt.setFloat(1,balance-book_price);
					mystmt.setInt(2,cID);
					mystmt.execute();

					sql = "delete from cart where cart.cID =? and cart.bID = ?";
					mystmt = con.prepareStatement(sql);
					mystmt.setInt(1,cID);
					mystmt.setInt(2,book_id);
					mystmt.execute();
					
					sql="insert into Orders (cID,bID) values (?,?)";		// updating customer's orders
					mystmt = con.prepareStatement(sql);
					mystmt.setInt(1,cID);
					mystmt.setInt(2,book_id);
					mystmt.execute();
					
					System.out.println("Thank you! Order successfully placed!");
				}
			 }
		}
		myRs.close();
		mystmt.close();
		stmt.close();
	    con.close();
	}
	public void cancelOrder(int cID) throws Exception
	{
        Class.forName("com.mysql.cj.jdbc.Driver");  
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/Book_Store","root","1234");  
        Statement stmt=con.createStatement();  
        PreparedStatement mystmt=null;

		System.out.println("Enter the id of your order that you want to cancel");
		int orderID=sc.nextInt();
		sc.nextLine();
		
		sql="select count(oID) from orders where cID=? and oID=?";
		mystmt = con.prepareStatement(sql);
		mystmt.setInt(1,cID);
		mystmt.setInt(2,orderID);
		ResultSet myRs = mystmt.executeQuery();
		int count=0;
		while(myRs.next())
		{
			count=myRs.getInt("count(oID)");
		}
		if(count==0)
		{
			System.out.println("No such order has been placed by you.");
		}
		else
		{
			sql = "select book.bID from Book inner join (select bID from Orders where oID =?)as od";
			mystmt = con.prepareStatement(sql);
			mystmt.setInt(1,orderID);
			ResultSet myRs2 = mystmt.executeQuery();
			int book_id=0;
			while(myRs2.next())
				book_id = myRs2.getInt("bID");      // we have book id
			myRs2.close();
			
			sql = "delete from Orders where oID =?";
			mystmt = con.prepareStatement(sql);
			mystmt.setInt(1,orderID);
			mystmt.execute();

			sql = "select quantity from Book where Book.bID =?";
			mystmt = con.prepareStatement(sql);
			mystmt.setInt(1,book_id);
			ResultSet myRs3 = mystmt.executeQuery();
			int book_quan=0;
			while(myRs3.next())
			{
				book_quan = myRs3.getInt("quantity");       // we have book quantity
			}
			myRs3.close();
			sql = "update Book set quantity =? where bID =?";		// updated quantity
			mystmt = con.prepareStatement(sql);
			mystmt.setInt(1,book_quan+1);
			mystmt.setInt(2,book_id);
			mystmt.execute();


			sql = "select balance_amt from Customer where Customer.cID =?";   // get balance
			mystmt = con.prepareStatement(sql);
			mystmt.setInt(1,cID);
			ResultSet myRs5 = mystmt.executeQuery();
			float balance=0;
			while(myRs5.next())
			{
				balance = myRs5.getFloat("balance_amt");
			}
			myRs5.close();

			sql = "select price from Book where bID =?";       // get price
			mystmt = con.prepareStatement(sql);
			mystmt.setInt(1,book_id);
			ResultSet myRs6 = mystmt.executeQuery();
			float book_price=0;
			while(myRs6.next())
			{
				book_price = myRs6.getFloat("price");
			}
			myRs6.close();
			sql = "update Customer set balance_amt =? where cID=?";		// update card amount
			mystmt = con.prepareStatement(sql);
			mystmt.setFloat(1,balance+book_price);
			mystmt.setInt(2,cID);
			mystmt.execute();
			System.out.println("Order successfully cancelled!");
		}
		
		stmt.close();
		mystmt.close();
		con.close(); 
		

	}
	public void showOrders(int cID) throws Exception
	{
        Class.forName("com.mysql.cj.jdbc.Driver");  
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/Book_Store","root","1234");  
        Statement stmt=con.createStatement();  
        PreparedStatement mystmt=null;
        
		sql = "Select * from Orders where cID =?";
		mystmt = con.prepareStatement(sql);
		mystmt.setInt(1,cID);
		ResultSet myRs = mystmt.executeQuery();
		System.out.println("Order ID     Book ID");
		int co=0;
		while(myRs.next())
		{
			int oID = myRs.getInt("oID");
			int bID = myRs.getInt("bID");
			System.out.println(oID+"     "+bID);
			co++;
		}
		if(co==0)
			System.out.println("You have not placed any order so far.");
		myRs.close();
		stmt.close();
		con.close(); 
	}
}