//package calculatorLogic;
//
//import java.math.BigDecimal;
//import java.util.Stack;
//
//import operands.Operand;
//import operands.RealNumber;
package calculatorLogic;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.Arrays;
import calculatorLogic.MatrixOperations;
import operands.Matrix;
import operands.Operand;
import operands.RealNumber;
import operands.StatVector;
import exceptions.InvalidExpressionException;

public class BusinessLogic {
	private ArrayList<String> basicOperators;
	private ArrayList<String> geometricOperators;
	private ArrayList<String> matrixOperators;
	private ArrayList<String> statisticalOperators;
	private HashMap<String, Operand> variables;
	
	public BusinessLogic() {
		//	Listing all the basic operations
		String[] basicOperatorsArray 		= {"+", "-", "*", "/"};
		basicOperators 						= new ArrayList<String>(Arrays.asList(basicOperatorsArray));
		//	Listing all the geometric operations
		String[] geometricOperatorsArray 	= {"sin", "cos", "tan", "sec", "cot", "cosec", "sinh", "cosh", "tanh", "sech", "coth", "cosech"};
		geometricOperators 					= new ArrayList<String>(Arrays.asList(geometricOperatorsArray));
		//	Listing all the matrix operations
		String[] matrixOperatorsArray		= {"eye", "ones", "zeros", "mat_set", "mat_transpose", "mat_determinant", "mat_adjoint", "mat_inverse", "mat_mul", "mat_div", "mat_add", "mat_sub"};
		matrixOperators 					= new ArrayList<String>(Arrays.asList(matrixOperatorsArray));
		//	Listing all the statistical operations
		String[] statisticalOperatorsArray	= {"{, min", "max", "mean", "median", "mode", "variance", "skewness"};
		statisticalOperators 				= new ArrayList<String>(Arrays.asList(statisticalOperatorsArray));
		
		//	Initializing the HashMap for all variables
		variables = new HashMap<String, Operand>();
	}
	
	public Operand solve(String input) throws Exception{
		// 	Variables
		String input_copy = input;
		for (Map.Entry<String, Operand> entry : variables.entrySet()) {
			if (entry.getKey().equals(input_copy.replace("\\s", ""))) {
				return entry.getValue();
			}
		}
		
		//	Matrix Operations
		MatrixOperations matrixCalc = new MatrixOperations();
		for (int i = 0; i < matrixOperators.size(); i++) {
			if (input.contains(matrixOperators.get(i))) {
				String operator = matrixOperators.get(i);
				switch (operator) {
					case "eye": 
						if (!input.contains("=")) {
							throw new InvalidExpressionException("Error: Please enter an expression of the form\n\tmyMatrix = eye(3)");
						} else {
							String variableName = input.substring(0, input.indexOf("=")).replaceAll("\\s", "");
//							System.out.println(input.substring(input.indexOf("(") + 1, input.indexOf(")")).replaceAll("\\s", ""));
							int dimension = Integer.parseInt(input.substring(input.indexOf("(") + 1, input.indexOf(")")).replaceAll("\\s", ""));
							Matrix<Double> matrix = matrixCalc.identity(dimension);
							variables.put(variableName, matrix);
							return matrix;
						}
					case "ones":
						if (!input.contains("=")) {
							throw new InvalidExpressionException("Error: Please enter an expression of the form\n\tmyMatrix = ones(3, 8)");
						} else {
							String variableName = input.substring(0, input.indexOf("=")).replaceAll("\\s", "");
							int dimension1 = Integer.parseInt(input.substring(input.indexOf("(") + 1, input.indexOf(",")).replaceAll("\\s", ""));
							int dimension2 = Integer.parseInt(input.substring(input.indexOf(",") + 1, input.indexOf(")")).replaceAll("\\s", ""));
							Matrix<Double> matrix = matrixCalc.ones(dimension1, dimension2);
							variables.put(variableName, matrix);
							return matrix;
						}
					case "zeros":
						if (!input.contains("=")) {
							throw new InvalidExpressionException("Error: Please enter an expression of the form\n\tmyMatrix = zeros(3, 8)");
						} else {
							String variableName = input.substring(0, input.indexOf("=")).replaceAll("\\s", "");
							int dimension1 = Integer.parseInt(input.substring(input.indexOf("(") + 1, input.indexOf(",")).replaceAll("\\s", ""));
							int dimension2 = Integer.parseInt(input.substring(input.indexOf(",") + 1, input.indexOf(")")).replaceAll("\\s", ""));
							Matrix<Double> matrix = matrixCalc.zeros(dimension1, dimension2);
							variables.put(variableName, matrix);
							return matrix;
						}
					case "mat_transpose":
						if (!input.contains("=")) {
							throw new InvalidExpressionException("Error: Please enter an expression of the form\n\tmyMatrix = mat_transpose(a)");
						} else {
							String targetVariableName = input.substring(0, input.indexOf("=")).replaceAll("\\s", "");
							String variableName = input.substring(input.indexOf("(") + 1, input.indexOf(")")).replaceAll("\\s", "");
							Matrix<Double> matrix = matrixCalc.transpose((Matrix<Double>) variables.get(variableName));
							variables.put(targetVariableName, matrix);
							return matrix;
						}
					case "mat_determinant":
						if (!input.contains("=")) {
							throw new InvalidExpressionException("Error: Please enter an expression of the form\n\tmyMatrix = mat_determinant(a)");
						} else {
							String targetVariableName = input.substring(0, input.indexOf("=")).replaceAll("\\s", "");
							String variableName = input.substring(input.indexOf("(") + 1, input.indexOf(")")).replaceAll("\\s", "");
							try {
								RealNumber result = new RealNumber(matrixCalc.determinant((Matrix<Double>) variables.get(variableName)));
								variables.put(targetVariableName, result);
								return result;
							} catch (Exception e) {
								System.out.println(e.getMessage());
							}
						}	break;
					case "mat_adjoint":
						if (!input.contains("=")) {
							throw new InvalidExpressionException("Error: Please enter an expression of the form\n\tmyMatrix = mat_adjoint(a)");
						} else {
							String targetVariableName = input.substring(0, input.indexOf("=")).replaceAll("\\s", "");
							String variableName = input.substring(input.indexOf("(") + 1, input.indexOf(")")).replaceAll("\\s", "");
							try {
								Matrix<Double> matrix = matrixCalc.adjoint((Matrix<Double>) variables.get(variableName));
								variables.put(targetVariableName, matrix);
								return matrix;
							} catch (Exception e) {
								System.out.println(e.getMessage());
							}
						}	break;
					case "mat_inverse":
						if (!input.contains("=")) {
							throw new InvalidExpressionException("Error: Please enter an expression of the form\n\tmyMatrix = mat_inverse(a)");
						} else {
							String targetVariableName = input.substring(0, input.indexOf("=")).replaceAll("\\s", "");
							String variableName = input.substring(input.indexOf("(") + 1, input.indexOf(")")).replaceAll("\\s", "");
							try {
								Matrix<Double> matrix = matrixCalc.inverse((Matrix<Double>) variables.get(variableName));
								variables.put(targetVariableName, matrix);
								return matrix;
							} catch (Exception e) {
								System.out.println(e.getMessage());
							}
						}	break;
					case "mat_div":
						if (!input.contains("=")) {
							throw new InvalidExpressionException("Error: Please enter an expression of the form\n\tmyMatrix = mat_div(a, b)");
						} else {
							String targetVariableName = input.substring(0, input.indexOf("=")).replaceAll("\\s", "");
							String variable1Name = input.substring(input.indexOf("(") + 1, input.indexOf(",")).replaceAll("\\s", "");
							String variable2Name = input.substring(input.indexOf(",") + 1, input.indexOf(")")).replaceAll("\\s", "");
							try {
								Matrix<Double> matrix = (Matrix<Double>) matrixCalc.div((Matrix<Double>) variables.get(variable1Name), (Matrix<Double>) variables.get(variable2Name));
								variables.put(targetVariableName, matrix);
								return matrix;
							} catch (Exception e) {
								System.out.println(e.getMessage());
							}
						}	break;
					case "mat_mul":
						if (!input.contains("=")) {
							throw new InvalidExpressionException("Error: Please enter an expression of the form\n\tmyMatrix = mat_mul(a, b)");
						} else {
							String targetVariableName = input.substring(0, input.indexOf("=")).replaceAll("\\s", "");
							String variable1Name = input.substring(input.indexOf("(") + 1, input.indexOf(",")).replaceAll("\\s", "");
							String variable2Name = input.substring(input.indexOf(",") + 1, input.indexOf(")")).replaceAll("\\s", "");
							try {
								Matrix<Double> matrix = (Matrix<Double>) matrixCalc.mul((Matrix<Double>) variables.get(variable1Name), (Matrix<Double>) variables.get(variable2Name));
								variables.put(targetVariableName, matrix);
								return matrix;
							} catch (Exception e) {
								System.out.println(e.getMessage());
							}
						}	break;
					case "mat_add":
						if (!input.contains("=")) {
							throw new InvalidExpressionException("Error: Please enter an expression of the form\n\tmyMatrix = mat_add(a, b)");
						} else {
							String targetVariableName = input.substring(0, input.indexOf("=")).replaceAll("\\s", "");
							String variable1Name = input.substring(input.indexOf("(") + 1, input.indexOf(",")).replaceAll("\\s", "");
							String variable2Name = input.substring(input.indexOf(",") + 1, input.indexOf(")")).replaceAll("\\s", "");
							try {
								Matrix<Double> matrix = (Matrix<Double>) matrixCalc.add((Matrix<Double>) variables.get(variable1Name), (Matrix<Double>) variables.get(variable2Name));
								variables.put(targetVariableName, matrix);
								return matrix;
							} catch (Exception e) {
								System.out.println(e.getMessage());
							}
						}	break;
					case "mat_sub":
						if (!input.contains("=")) {
							throw new InvalidExpressionException("Error: Please enter an expression of the form\n\tmyMatrix = mat_add(a, b)");
						} else {
							String targetVariableName = input.substring(0, input.indexOf("=")).replaceAll("\\s", "");
							String variable1Name = input.substring(input.indexOf("(") + 1, input.indexOf(",")).replaceAll("\\s", "");
							String variable2Name = input.substring(input.indexOf(",") + 1, input.indexOf(")")).replaceAll("\\s", "");
							try {
								Matrix<Double> matrix = (Matrix<Double>) matrixCalc.sub((Matrix<Double>) variables.get(variable1Name), (Matrix<Double>) variables.get(variable2Name));
								variables.put(targetVariableName, matrix);
								return matrix;
							} catch (Exception e) {
								System.out.println(e.getMessage());
							}
						}	break;
				}
			}
		}
		
		//	Statistical Operations
		StatisticalOperations statCalc = new StatisticalOperations();
		for (int i = 0; i < statisticalOperators.size(); i++) {
			if (input.contains(statisticalOperators.get(i))) {
				switch (statisticalOperators.get(i)) {
					case "{":
						if (!input.contains("=")) {
							throw new InvalidExpressionException("An array must be assigned to a variable like:\n\ta = {2, 3, 6}\n");
						} else {
							String targetVariableName = input.substring(0, input.indexOf("=")).replaceAll("\\s", "");
							StringTokenizer tokenizer = new StringTokenizer(input.substring(input.indexOf("{") + 1), ",}");
							ArrayList<RealNumber<Double>> a = new ArrayList<RealNumber<Double>>();
							while (tokenizer.hasMoreTokens()) {
								a.add(new RealNumber(Double.parseDouble(tokenizer.nextToken())));
							} variables.put(targetVariableName, new StatVector<Double>(a));
						}
					//	TODO: add other statistical ops
				}
			}
		}
		
		//	TODO: Add Geometric ops
		
		//	Basic Operations
		for (int i = 0; i < basicOperators.size(); i++) {
			if (input.contains(basicOperators.get(i))) {
				if (!input.contains("=")) {
					return new RealNumber(Double.parseDouble(evaluateBasicExpression(input)));
				} else {
					String targetVariableName = input.substring(0, input.indexOf("=")).replaceAll("\\s", "");
					RealNumber result = new RealNumber(Double.parseDouble(evaluateBasicExpression(input)));
					variables.put(targetVariableName, result);
					return result;
				}
			}
		}
		return new RealNumber(0);
	}
	
	public String evaluateBasicExpression(String input) {
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
//	    double rawResult = 
//	    return values.pop().toString();
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
		BasicOperations basicCalc = new BasicOperations();
	    switch (op) {
	      	case '+':
	      		return (Double) basicCalc.add(new RealNumber(a), new RealNumber(b)).value();  // calling the business class 
	      	case '-':
	      		return (Double) basicCalc.sub(new RealNumber(a), new RealNumber(b)).value();
	      	case '*':
	      		return (Double) basicCalc.mul(new RealNumber(a), new RealNumber(b)).value();
	      	case '/':
	      		if (b == 0)
	      			throw new UnsupportedOperationException("Cannot divide by zero");
	      		return (Double) basicCalc.div(new RealNumber(a), new RealNumber(b)).value();
	    }
	    return 0.00;
	}
}

