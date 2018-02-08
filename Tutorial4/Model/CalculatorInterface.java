package Model;

public interface CalculatorInterface {
	public <T extends Number>double add(T a,T b);
    public <T extends Number>double sub(T a,T b);
    public <T extends Number>double mul(T a,T b);
    public <T extends Number>double div(T a,T b);
}
