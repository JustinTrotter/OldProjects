
public class Rectangle extends Shape {

	private double width;
	private double height;
	
	public Rectangle (double w, double h){
		super(w * h);
		width = w;
		height = h;
		
//		setArea(width * height);
	}
}
