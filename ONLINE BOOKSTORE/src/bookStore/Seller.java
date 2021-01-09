package bookStore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Seller extends User				// Seller is a User
{
	Book b = new Book();
	public void addBook(int sID) throws Exception
	{
		b.addBook(sID);
	}
	public void modifyBook(int sID) throws Exception
	{
		b.modifyBook(sID);
	}
	public void booksThatUSell(int sID) throws Exception
	{
        Class.forName("com.mysql.cj.jdbc.Driver");  
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/Book_Store","root","1234");  
        Statement stmt=con.createStatement();  

		// print the names, price, id, discount of the books that the seller with this id sells
		sql = "select bID, name, price, discount from book where sID="+sID;
		ResultSet rs=stmt.executeQuery(sql);
		System.out.println("BookID, name, price, discount are: ");
		while(rs.next())
		{
			int id=rs.getInt("bID");
			String name=rs.getString("name");
			float price =rs.getFloat("price");
			float dis =rs.getFloat("discount");
			System.out.println(id+"   "+name+" -----------> "+price+" -----------> "+dis);
			
		}
		rs.close(); 
		stmt.close();
	    con.close(); 
	}
}
