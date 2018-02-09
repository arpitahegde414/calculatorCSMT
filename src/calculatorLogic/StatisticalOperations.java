package calculatorLogic;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.*;
import operands.Operand;
import operands.RealNumber;
import operands.StatVector;

public class StatisticalOperations<T extends Number> implements StatisticalInterface{
	
	// this class has an array of numbers whose mean,mode,standard deviation, variance 
	//private T[] a;
	
//	method to add two numbers
	BasicOperations basicCalc = new BasicOperations();
	public <T extends Number> Operand add(Operand a, Operand b) {
		return basicCalc.add(a, b);
    }

//	method to subtract two numbers
	public <T extends Number> Operand sub(Operand a, Operand b) {
		return basicCalc.sub(a, b);
    }

//  method to multiply two numbers
	public <T extends Number> Operand mul(Operand a, Operand b) {
		return basicCalc.mul(a, b);
    }

//  method to divide two numbers
	public <T extends Number> Operand div(Operand a, Operand b) {
		return basicCalc.div(a, b);
    }


    public String solve(String input) {
	char[] tokens = input.toCharArray();
	
	// Stack for numbers
    Stack<Double> values = new Stack<Double>();


    // Stack for Operators
    Stack<Character> ops = new Stack<Character>();


    for (int i = 0; i < tokens.length; i++) {
    	
    	// Current token is a number, push it to stack for numbers
    	if (tokens[i] >= '0' && tokens[i] <= '9') {
    		StringBuffer sbuf = new StringBuffer();
    		
    		// There may be more than one digits in number
    		while (i < tokens.length && ((tokens[i] >= '0' && tokens[i] <= '9') || tokens[i] == '.'))
    			sbuf.append(tokens[i++]);
    		
    		values.push(Double.parseDouble(sbuf.toString()));
    	} else if (tokens[i] == '(') {
    		ops.push(tokens[i]);
    	} else if (tokens[i] == ')') {
            while (ops.peek() != '(')
            	values.push(applyOp(ops.pop(), values.pop(), values.pop()));
            
            ops.pop();
        } else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/') {
            // While top of 'ops' has same or greater precedence to current
            // token, which is an operator. Apply operator on top of 'ops'
            // to top two elements in values stack
            while (!ops.empty() && hasPrecedence(tokens[i], ops.peek()))
            	values.push(applyOp(ops.pop(), values.pop(), values.pop()));
             
            ops.push(tokens[i]);
        }
    }


    // Entire expression has been parsed at this point, apply remaining operators to remaining values
    while (!ops.empty())
    	values.push(applyOp(ops.pop(), values.pop(), values.pop()));

    // Top of 'values' contains result, return it
    return new BigDecimal(values.pop()).setScale(12, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString();
//    double rawResult = 
//    return values.pop().toString();
    
}
  
    public static boolean hasPrecedence(char op1, char op2) {
	if (op2 == '(' || op2 == ')')
		return false;
    if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
        return false;
    else
        return true;
  	}
  
  	public Double applyOp(char op,  Double b, Double a) {
    switch (op) {
      	case '+':
      		return (Double) add(new RealNumber(a), new RealNumber(b)).value();  // calling the business class 
      	case '-':
      		return (Double) sub(new RealNumber(a), new RealNumber(b)).value();
      	case '*':
      		return (Double) mul(new RealNumber(a), new RealNumber(b)).value();
      	case '/':
      		if (b == 0)
      			throw new UnsupportedOperationException("Cannot divide by zero");
      		return (Double) div(new RealNumber(a), new RealNumber(b)).value();
    }
    return 0.00;
    }

  	public <T extends Number> RealNumber<T> min(StatVector<T> a){
		double minValue = a.get(0).value();
	    for (int i = 1; i < a.size(); i++) {
	        if (a.get(i).value() < minValue) {
	            minValue = a.get(i).value();
	        }
	    }
	    return new RealNumber(minValue);
		
	}
	public <T extends Number> RealNumber<T> max(StatVector<T> a){
		double maxValue = a.get(0).value();
	    for (int i = 1; i < a.size(); i++) {
	        if (a.get(i).value() > maxValue) {
	            maxValue = a.get(i).value();
	        }
	    }
	    return new RealNumber(maxValue);
	}
	public <T extends Number> RealNumber<T> mean(StatVector<T> a){
	  
	    double mean;
	    double tot=0;
		for(int i=0; i<a.size();i++)
	    {
	    tot =tot+a.get(i).value();
	    }
	    mean = tot/a.size();
	    return new RealNumber(mean);
	}
	public <T extends Number> RealNumber<T> median(StatVector<T> a){
		if(a.size()%2==0)
		return new RealNumber(( a.get(a.size()/2-1).value() + a.get(a.size()/2).value() )/2);
		else 
			return new RealNumber(a.get(a.size()/2).value());
	}
	
	public <T extends Number> RealNumber<T> mode(StatVector<T> a){
        double mode = 0, maxCount = 0;
        for (int i = 0; i < a.size(); ++i) 
        {  int count = 0;
            for (int j = 0; j < a.size(); ++j) 
            {
                if (a.get(j).value() == a.get(i).value())
                    ++count;
            }
            if (count > maxCount) 
            {
                maxCount = count;
                mode = a.get(i).value();
            }
        }
        return new RealNumber(mode);
	
	}
	public <T extends Number> RealNumber<T> variance(StatVector<T> a){
		double mean = mean(a).value();
		double temp=0,diff,square;
	    for(int i=0;i<a.size();i++)
	    {
	    	diff=a.get(i).value()-mean;
	    	square=diff*diff;
	    	temp+=square;
	    }
	    return new RealNumber(temp/a.size());
	    
	}
	//returns standard deviation
	public <T extends Number> RealNumber<T> stddev(StatVector<T> a){ 
	  return new RealNumber(Math.sqrt(variance(a).value()));
	}
	
	public <T extends Number> RealNumber<T> skewness(StatVector<T> a){
		double temp=0,cube,diff;
		double mean= mean(a).value();
		for(int i=0;i<a.size();i++)
		{
			diff=a.get(i).value()-mean;
			cube=Math.pow(diff,3);              //skewness=
			temp+=cube;
		}
		return new RealNumber(temp/(a.size()*Math.pow(stddev(a).value(),3)));
	}
	
}



