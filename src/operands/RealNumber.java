package operands;

public class RealNumber<T extends Number> implements Operand{
	private T value;
	
	public RealNumber(T v) {
		value = v;
	}
	
	public Double value() {
		return new Double(value.doubleValue());
	}
}
