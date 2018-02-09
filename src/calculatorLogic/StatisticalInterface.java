package calculatorLogic;
import java.util.ArrayList;

import operands.Operand;
import operands.RealNumber;
import operands.StatVector;

public interface StatisticalInterface {
	
	public <T extends Number> RealNumber<T> min		(StatVector<T> a);
	public <T extends Number> RealNumber<T> max		(StatVector<T> a);
	public <T extends Number> RealNumber<T> mean	(StatVector<T> a);
	public <T extends Number> RealNumber<T> median	(StatVector<T> a);
	public <T extends Number> RealNumber<T> mode	(StatVector<T> a);
	public <T extends Number> RealNumber<T> variance(StatVector<T> a);
	public <T extends Number> RealNumber<T> stddev	(StatVector<T> a);
}

