
public class Driver {
	
	public static void main (String [] args){
		
		// notice that the left declaration is of Shape and the right is Square
		Shape square = new Square(6.6, 6.6);
		
		
		// again the left is Shape but this time the right is Rectangle
		Shape rect = new Rectangle(5.0, 3.9);
		
		
		// neither Square or Rectangle have a getArea method but Shape does
		// so Square and Rectangle can use Shapes method
		System.out.println("The area of the square is: " + square.getArea());
		System.out.println("The area of the rectangle is: " + rect.getArea());
	}
}
