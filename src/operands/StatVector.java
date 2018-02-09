package operands;

import java.util.ArrayList;

public class StatVector<T extends Number> implements Operand{
	private ArrayList<RealNumber<T>> value;
	
	public StatVector(ArrayList<RealNumber<T>> a) {
		value = (ArrayList<RealNumber<T>>) a.clone();
	}
	
	public Object value() {
		return value;
	}
	
	public String show() {
		String result = "";
		for (int i = 0; i < value.size(); i++) {
			result += String.valueOf(value.get(i).value()) + "\t";
		} result += "\n";
		return result;
	}
	
	public RealNumber<T> get(int i) {
		return value.get(i);
	}
	
	public int size() {
		return value.size();
	}
}
