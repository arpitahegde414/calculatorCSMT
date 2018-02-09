
package calculatorLogic;

import operands.Operand;

public class GeometricalOperations<T extends Number> implements GeometricalInterface
{

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
	
	public <T extends Number> double sine(int deg) {
		// TODO Auto-generated method stub
		double radianVal = Math.toRadians(deg);
		return Math.sin(radianVal);
	}

	public <T extends Number> double cosine(int deg) {
		double radianVal = Math.toRadians(deg);
		return Math.cos(radianVal);
	}

	public <T extends Number> double tangent(int deg) {
		double radianVal = Math.toRadians(deg);
		return Math.tan(radianVal);
	}

	public <T extends Number> double sec(int deg) {
		double radianVal = Math.toRadians(deg);
		return 1.0 / Math.cos(radianVal);
	}

	public <T extends Number> double cosec(int deg) {
		double radianVal = Math.toRadians(deg);
		return 1.0 / Math.sin(radianVal);
	}

	public <T extends Number> double cotangent(int deg) {
		double radianVal = Math.toRadians(deg);
		return 1.0 / Math.tan(radianVal);
	}

}
