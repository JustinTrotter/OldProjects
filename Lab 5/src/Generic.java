/*
 * 	An example of a generic class
 * 
 */


public class Generic<T> {

	private T element;
	
	// the empty Constructor
	public Generic(){
		element = null;
	}
	
	// non-empty Constructor
	public Generic(T elem){
		element = elem;
	}
	
	// set method
	public void setElement(T element){
		this.element = element;
	}
	
	// get method
	public T getElement(){
		return element;
	}
	
	// toString method
	public String toString(){
		String s = "The value is: " + element;
		return s;
	}
}
