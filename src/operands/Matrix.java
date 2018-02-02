package operands;
import java.util.ArrayList;
import java.util.Collections;
import operands.RealNumber;

public class Matrix<T extends Number> implements Operand{
	ArrayList< ArrayList<T> > value;
	
//	Matrix initialized by giving an existing ArrayList
	public Matrix(ArrayList< ArrayList<T> > mat) {
		value = new ArrayList< ArrayList<T> >();
		for (int i = 0; i < mat.size(); i++) {
			for (int j = 0; j < mat.get(i).size(); j++) {
				value.get(i).set(j, mat.get(i).get(j));
			}
		}
	}
	
//	Matrix initialized by giving the shape
	public Matrix(int rows, int cols) {
		value = new ArrayList< ArrayList<T> >();
		for (int i = 0; i < rows; i ++) {
			value.add(new ArrayList<T>());
			for (int j = 0; j < cols; j++) {
				value.get(i).add((T) new Integer(0));
			}
		}
	}
	
	public Object value() {
		return value;
	}
	
	public ArrayList<T> get(int i) {
		return value.get(i);
	}
	
	public ArrayList<Integer> shape() {
		ArrayList<Integer> shape = new ArrayList<Integer>();
		shape.add(value.size());
		shape.add(value.get(0).size());
		return shape;
	}
	
	public void set(int x, int y, T val) {
		value.get(x).set(y, val);
	}
}
