package calculatorLogic;

public interface CalculatorInterface {
	//finds sum
	public <T extends Number> double add(T a, T b);
	
    //finds difference
	public <T extends Number> double sub(T a,	T b);
    
	//finds product
	public <T extends Number> double mul(T a,	T b);
    
	//finds quotient
	public <T extends Number> double div(T a,	T b);
	
	//accepts and solves the infix expression
	public String solve(String input);
	
	//applies the operator to the operands passed as arguments
	public Double applyOp(char op,  Double b, Double a);
}