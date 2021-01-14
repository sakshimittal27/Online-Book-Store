package bookStore;

public class Customer extends User				//Customer is a User
{
	String sql;

    Cart cart = new Cart();
    Book book = new Book();
    Catalogue cat= new Catalogue();
    public void searchBooks() throws Exception
    {
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
    			System.out.println("Enter the name of the author: ");
    			String author=sc.nextLine();
    			cat.searchBookByAuthor(author);
    		break;
    		case 4:
    			System.out.println("Enter the name of the genre: ");
    			String genre=sc.nextLine();
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
    	}
    }
    public void showCart(int cID) throws Exception
    {
    	cart.showCart(cID);
    }
    public void addToCart(int cID) throws Exception
	{
		cart.addToCart(cID);
	}
	public void removeFromCart(int cID) throws Exception
	{
		cart.removeFromCart(cID);
	}
	public void placeOrder(int cID) throws Exception
	{
		cart.placeOrder(cID);
	}
	public void cancelOrder(int cID) throws Exception
	{
		cart.cancelOrder(cID);
	}
	public void showOrders(int cID) throws Exception
	{
		cart.showOrders(cID);
	}
}