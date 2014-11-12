/* 
*    Name:  Justin Trotter
*    Current Date:  2/27/2014
*    Sources Consulted:
*    
*    Honor Code Statement: In keeping with the honor code policies of the University of Mississippi, the School of Engineering, and the Department of Computer and Information Science, I affirm that I have neither given nor received assistance on this programming assignment. This assignment represents my individual, original effort. 
*                   ... My Signature is on File. 
*/ 
import java.util.ArrayList;
public class Order {
	private static int counter = 0;
	private int number;
	private String date;
	private Customer customer;
	private ArrayList<Purchase> purchases;
	private int total;
	
	public Order(){
		customer = null;
		date = "";
		purchases = new ArrayList<Purchase>();
		total = 0;
		counter++;
		number = counter;
		
	}
	
	public Order(Customer c, String d){
		customer = c;
		date = d;
		purchases = new ArrayList<Purchase>();
		total = 0;
		counter++;
		number = counter;
	}
	
	public void setDate(String d){
		date = d;
	}
	
	public void addPurchase(Purchase c){
		purchases.add(c);
		total += c.getAmount();
	}
	
	public void removePurchaseByIndex(int index){
		purchases.remove(index);
	}
	
	public void removePurchaseByName(String n){
		for(int i = 0; i < purchases.size(); i++){
			if(purchases.get(i).getDescription() == n){
				purchases.remove(i);
				return;
			}
		}
		System.out.println("Error: Purchase Not Found!");
	}
	
	public Customer getCustomer(){
		return customer;
	}
	
	public String getDate(){
		return date;
	}
	
	public Purchase getPurchaseByIndex(int index){
		return purchases.get(index);
	}
	
	public Purchase getPurchaseByDescription(String n){
		for(int i = 0; i < purchases.size(); i++){
			if(purchases.get(i).getDescription() == n){
				return purchases.get(i);
			}
		}
		System.out.println("Error: Purchase Not Found!");
		return null;
	}
	
	public int getTotal(){
		int result = 0;
		for(Purchase p : purchases){
			result += p.getAmount();
		}
		return result;
	}
	
	public void setDescriptionByDescription(String oldD, String newD){
		getPurchaseByDescription(oldD).setDescription(newD);
	}
	
	public void setDescriptionByIndex(int index, String d){
		getPurchaseByIndex(index).setDescription(d);
	}
	
	public void setAmountByDescription(String n, int a)
	{
		getPurchaseByDescription(n).setAmount(a);
		updateTotal();
	}
	
	public void setAmountByIndex(int index, int a){
		getPurchaseByIndex(index).setAmount(a);
		updateTotal();
	}
	
	public void updateTotal(){
		total = 0;
		for(Purchase p : purchases){
			total += p.getAmount();
		}
	}
	
	public String toString(){
		String result = "";
		result += "Order Number: " + number + "\n";
		result += customer;
		result += "Order Date: " + date + "\n";
		for(Purchase p : purchases){
			result += p;
		}
		result += "Total Ordered Number of Items Ordered: " + total + "\n";
		return result;
	}
}
