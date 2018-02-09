package Model;

public class Model implements CalculatorInterface{
	public <T extends Number>double add(T a,T b) {
		return a.doubleValue() + b.doubleValue();
	}
	
	//	method to subtract two numbers
    public <T extends Number>double sub(T a,T b) {
    	return a.doubleValue() - b.doubleValue();
    }
    
    //    method to multiply two numbers
    public <T extends Number>double mul(T a,T b) {
    	return a.doubleValue() * b.doubleValue();
    }
    
    //    method to divide two numbers
    public <T extends Number>double div(T a,T b) {
    	return a.doubleValue() / b.doubleValue();
}

}
