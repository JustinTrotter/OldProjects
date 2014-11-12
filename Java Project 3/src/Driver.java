/* 
*    Name:  Justin Trotter
*    Current Date:  2/27/2014
*    Sources Consulted:
*    
*    Honor Code Statement: In keeping with the honor code policies of the University of Mississippi, the School of Engineering, and the Department of Computer and Information Science, I affirm that I have neither given nor received assistance on this programming assignment. This assignment represents my individual, original effort. 
*                   ... My Signature is on File. 
*/ 
import java.util.ArrayList;
public class Driver{
	public static ArrayList<Order> orders;
	public static void main(String [] arg){
		
		orders = new ArrayList<Order>();
		
		Order order1 = new Order(new Customer("John Doe", "4572 1248 3547 4468"),"October 15, 1981");
		order1.addPurchase(new Purchase("Bread", 1));
		order1.addPurchase(new Purchase("Egg", 2));
		order1.addPurchase(new Purchase("Ham",1));
		order1.addPurchase(new Purchase("Bacon", 3));
		order1.addPurchase(new Purchase("Milk", 2));
		orders.add(order1);
		
		Order order2 = new Order(new Customer("Richard Garret", "7543 4674 0034 7516"),"March 7, 2012");
		order2.addPurchase(new Purchase("Sword", 2));
		order2.addPurchase(new Purchase("Chainmail", 4));
		order2.addPurchase(new Purchase("Halberd",2));
		order2.addPurchase(new Purchase("Potion", 1));
		order2.addPurchase(new Purchase("Spellbook", 3));
		orders.add(order2);
		
		Order order3 = new Order(new Customer("George Washington", "0000 0000 0004 4572"),"July 4, 1776");
		order3.addPurchase(new Purchase("Apple", 10));
		order3.addPurchase(new Purchase("Hachet", 1));
		order3.addPurchase(new Purchase("Cannon",30));
		order3.addPurchase(new Purchase("Flag", 1));
		order3.addPurchase(new Purchase("Musket", 5));
		orders.add(order3);
		
		Order order4 = new Order(new Customer("Austin Powers", "8008 6969 6969 8008"),"Feburary 14, 1967");
		order4.addPurchase(new Purchase("Swag", 15));
		order4.addPurchase(new Purchase("Mojo", 1));
		order4.addPurchase(new Purchase("Condom",100));
		order4.addPurchase(new Purchase("Glasses", 1));
		order4.addPurchase(new Purchase("Peace Pendant", 3));
		orders.add(order4);
		
		System.out.println("*****************************");
		for(Order o : orders){
			System.out.println();
			System.out.println(o);
			System.out.println();
			System.out.println("*****************************");
		}
		
		order1.getCustomer().setName("Jane Doe");
		order2.getCustomer().setCreditCardNumber("4527 2668 0458 4540");
		order3.setDescriptionByIndex(2, "Artillery");
		order4.setAmountByDescription("Mojo", 4);
		
		System.out.println("Order Number 1's name has been changed to \"Jane Doe\".");
		System.out.println("Order Number 2's credit card number has been changed to \"4527 2668 0458 4540\".");
		System.out.println("Order Number 3's 3rd Purchase \"Cannon\" has been changed to \"Artillery\".");
		System.out.println("Order Number 4's amount of \"Mojo\" purchased has been changed to \"4\"");
		
		System.out.println();
		
		System.out.println("*****************************");
		System.out.println();
		for(Order o : orders){
			System.out.println();
			System.out.println(o);
			System.out.println();
			System.out.println("*****************************");
		}
	}
}