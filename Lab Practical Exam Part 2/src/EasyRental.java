import java.util.ArrayList;


public class EasyRental {
	ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
	
	//Constructor
	public EasyRental(){
		
	}
	
	public void addVehicle(String m, String c, String s, int n){
		vehicles.add(new Vehicle(m, c, s, n));
	}
	
	public void Display(){
		for(int i = 0; i < vehicles.size(); i++){
			System.out.println(vehicles.get(i).toString());
		}
	}
	
	public void getTotal(){
		int result = 0;
		for(int i = 0; i < vehicles.size(); i++){
			result += vehicles.get(i).getNumInStock();
		}
		System.out.println("The total number of vehicles in stock is: " + result);
	}
	
	public void Colors(String c){
		for(int i = 0; i < vehicles.size(); i++){
			if(vehicles.get(i).getColor() == c)
				System.out.println("We have these Makes in " + c + ": " + vehicles.get(i).getMake() + " ");
		}
	}
	
	public void Sizes(String s){
		for(int i = 0; i < vehicles.size(); i++){
			if(vehicles.get(i).getSize() == s)
				System.out.println("We have the size: " + s + " for this make: " + vehicles.get(i).getMake() + " ");
		}
	}
}
