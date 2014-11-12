
public class Square extends Shape {

	
	private double width;
	private double height;
	
	public Square(double w, double h){
		super(w * h);
		width = w;
		height = h;
		
//		if (width != height){
//			System.out.print("This isn't really a square you know...");
//		}
		
//		setArea(width * height);
	}
}
