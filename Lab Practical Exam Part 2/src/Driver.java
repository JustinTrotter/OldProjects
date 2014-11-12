
public class Driver {
	
	public static void main (String[] args){
		
		EasyRental ez = new EasyRental();
		
		// this section passes the makes, the color, the size, and the number of vehicles of this kind in stock
		// to the addVehicle method which adds the data to an array or an arrayList (you decide)
		ez.addVehicle("Ford", "Red", "mid-size", 3);
		ez.addVehicle("Fiat", "Blue", "economy", 5);
		ez.addVehicle("Toyota", "Black", "compact", 6);
		ez.addVehicle("Saab", "Yellow", "mid-size", 2);
		ez.addVehicle("Nissan", "Green", "economy", 1);
		ez.addVehicle("Jeep", "Red", "compact", 7);
		ez.addVehicle("Honda", "Blue", "economy", 4);
		ez.addVehicle("Dodge", "Yellow", "mid-size", 1);
		ez.addVehicle("Chevy", "Green", "full-size", 2);
		ez.addVehicle("Scion", "Black", "compact", 5);
		
		// this will call the Display method that will print all the data in your array or arrayList
		ez.Display();
		System.out.println();
	
		// this will call the method getTotal, which will sum the total number of vehicles in stock
		// and will print "The total number of vehicles in stock is: " and the total
		ez.getTotal();
		System.out.println();
		
		// this will call the method Colors, which will see if the passed string is equal to the color string
		// in each Vehicle object and if it is it will print ""We have these Makes in " and the passed color and
		// ": " and the makes
		ez.Colors("Red");
		System.out.println();
		ez.Colors("Yellow");
		System.out.println();
		
		// this will call the Sizes method, which will see if the passed string is equal to the size string
		// in each Vehicle object and if it is will print ""We have the size: " and the 
		// vehicle size and " for this make: " and the make the of vehicle
		ez.Sizes("economy");
		System.out.println();
		ez.Sizes("full-size");
		System.out.println();
		

	}
}