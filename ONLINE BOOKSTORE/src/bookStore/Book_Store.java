package bookStore;
/* 
    IDBMS Project
    ONLINE BOOK STORE
    GROUP 3:
        1. Sakshi Mittal            19ucs265
        2. Abhishek Singh Naruka    19ucs261
        3. Jaskaran Singh           19ucs262
        4. Tanisha Goyal            19ucs259
*/

import java.util.*; 

public class Book_Store     // Main class
{
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        User user = new User();
        Seller sel= new Seller();
        Customer cust = new Customer();
        Catalogue cat=new Catalogue();
        int ch;
        
         do
         {
        	 System.out.println();
        	 System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
             System.out.println("Please choose one of the following options: \n");
             System.out.println("1.Browse our store.");
             System.out.println("2.Sign up");
             System.out.println("3.Sign in");
             System.out.println("4.Close the software");

             final int choice = sc.nextInt();
             sc.nextLine();
             switch (choice)
             {
             	case 1:
             		System.out.println("1.Show by Alphabetical order.");
                	System.out.println("2.Show by Price.");
                	System.out.println("3.Search by Author.");
                	System.out.println("4.Search by Genre.");
                	System.out.println("5.Search by Discount.");
                	System.out.println("6.Show top selled books.");
                	System.out.println("7.Show currently released books.");
                	int c=sc.nextInt();
                	sc.nextLine();
                	switch(c)
                	{
                		case 1:
                			cat.sortBookByAlphabeticalOrder();
                		break;
                		case 2:
                			cat.sortBookByPrice();
                		break;
                		case 3:
                			System.out.println("Author: ");
                			String author = sc.nextLine();
                			cat.searchBookByAuthor(author);
                		break;
                		case 4:
                			System.out.println("Genre: ");
                			String genre = sc.nextLine();
                			cat.searchBookByGenre(genre);
                		break;
                		case 5:
                			cat.sortBookByDiscount();
                		break;
                		case 6:
                			cat.showtopSelledBooks();
                		break;
                		case 7:
                			cat.sortcurrentlyReleased();
                		break;
                	}
                break;
                 case 2:
                	 user.addUser();
                 break;
                 case 3:
                	 int []valid= {0,0};
                	 user.verifyUser(valid);
                	 
                	 if(valid[0]==10)   // valid login for seller  and valid[0] defines the valid login
                	 {
                		 	System.out.println("Seller successfully logged in!");
                		 	int z=1;
                		 	do
                	 		{
                	 			System.out.println("1. Add a book.");
                	 			System.out.println("2. Modify an existing book.");
                   	 			System.out.println("3. Check all the books that you sell.");
                   	 			System.out.println("4. Modify your details.");
                   	 			System.out.println("5. Show your details.");
                   	 			System.out.println("6. Delete your account.");
                   	 			System.out.println("7. Sign out");
                   	 			ch = sc.nextInt();
                   	 			sc.nextLine();
                   	 			switch(ch)
                   	 			{
                   	 				case 1:
                   	 					sel.addBook(valid[1]);   // valid[1] will have the id of the user
                   	 				break;
                   	 				case 2:
                   	 					sel.modifyBook(valid[1]);
                   	 				break;
                   	 				case 3:
                   	 					sel.booksThatUSell(valid[1]);
                   	 				break;
                   	 				case 4:
                   	 					user.modifyDetails(valid);
                   	 				break;
                   	 				case 5:
                   	 					user.showDetails(valid);
                   	 				break;
                   	 				case 6:
                   	 					user.deleteUser(valid);
                   	 					z=0;
                   	 				break;
                   	 				case 7:
                   	 					z=0;
                   	 				break;
                   	 				default:
                   	 					System.err.println("Please choose a valid operation.");
                   	 				break;          	 					
                   	 			}
                	 		} while(z==1);        			
               	 	 }
                	 
                	 else if(valid[0]==11)  //valid login for customer
                	 {
                		 	System.out.println("Customer successfully logged in!");
                		 	int a=1;
                	 		do
                	 		{
                	 			System.out.println("1. Check previous orders.");
                	 			System.out.println("2. Place a new order.");
                    	 		System.out.println("3. Cancel previous order.");
                   	 			System.out.println("4. Add a book to cart.");
                   	 			System.out.println("5. Show items in cart.");
                   	 			System.out.println("6. Delete a item from cart.");
                   	 			System.out.println("7. Modify your details.");
                   	 			System.out.println("8. Show your details.");
                   	 			System.out.println("9. Delete account.");
                   	 			System.out.println("10. Show/Search for books.");
                   	 			System.out.println("11. Sign out");
                    			ch = sc.nextInt();
                    			sc.nextLine();
                   	 			switch(ch)
                   	 			{
                   	 				case 1:
                   	 					cust.showOrders(valid[1]);
                   	 				break;
                   	 				case 2:
                   	 					cust.placeOrder(valid[1]);
                   	 				break;
                   	 				case 3:
                   	 					cust.cancelOrder(valid[1]);
                   	 				break;
                   	 				case 4:
                   	 					cust.addToCart(valid[1]);
                   	 				break;
                   	 				case 5:
                   	 					cust.showCart(valid[1]);
                   	 				break;
                   	 				case 6:
                   	 					cust.removeFromCart(valid[1]);
                   	 				break;
                   	 				case 7:
                   	 					user.modifyDetails(valid);
                   	 				break;
                   	 				case 8:
                   	 					user.showDetails(valid);
                   	 				break;
                   	 				case 9:
                   	 					user.deleteUser(valid);
                   	 					a=0;
                   	 				break;
                   	 				case 10:
                   	 					cust.searchBooks();
                   	 				break;
                   	 				case 11:
                   	 					a=0;
                   	 				break;
                   	 				default:
                   	 					System.err.println("Please choose a valid operation");
                   	 				break;
                   	 			}
                	 		} while(a==1);
                	 }  
                	 else
                	 {
                		 System.err.println("Sorry! Wrong login details.");
                	 }             
                 break;
                 case 4:
                	 System.out.println("Book Store is closed now. Thank you for visiting us!");
                	 System.out.println();
                	 System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
                	 sc.close();
                     System.exit(1);
                 break;
                 default:
                	 System.err.println("Please choose a valid option");
             }
         } while(true);       
    }
}