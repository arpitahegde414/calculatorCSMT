package calculatorLogic;
import exceptions.InvalidShapeException;
import operands.Matrix;
import operands.Operand;

public interface MatrixInterface extends CalculatorInterface{
//	finds sum
//	public <T extends Number> Matrix<T> add(Matrix<T> a, Matrix<T> b) throws InvalidShapeException;
//	
////	finds difference
//	public <T extends Number> Matrix<T> sub(Matrix<T> a,	Matrix<T> b) throws InvalidShapeException;
//    
////	finds product
//	public <T extends Number> Matrix<T> mul(Matrix<T> a,	Matrix<T> b) throws InvalidShapeException;
    
//	finds quotient
//	public <T extends Number> Matrix<T> div(Matrix<T> a,	Matrix<T> b) throws InvalidShapeException;
	
//	Transpose of the matrix
	public <T extends Number> Matrix<T> transpose(Matrix<T> mat);

//	finds minor 
	public <T extends Number> Matrix<T> minor(Matrix<T> mat, int row, int col) throws InvalidShapeException;
	
//	Determinant of the matrix
	public <T extends Number> Double determinant(Matrix<T> mat) throws InvalidShapeException;
	
//	Adjoint of the matrix
	public <T extends Number> Matrix<T> adjoint(Matrix<T> mat) throws InvalidShapeException;
	
//	Inverse of the matrix
	public <T extends Number> Matrix<T> inverse(Matrix<T> mat) throws InvalidShapeException;
}
