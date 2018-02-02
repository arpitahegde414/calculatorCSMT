import static org.junit.Assert.*;

import org.junit.Test;
import monolithic.BusinessLogic;

public class TestCalci {
	double delta = 0.000000002;
	@Test
	public void test1() {
		BusinessLogic bl = new BusinessLogic();
		double actual = bl.solve("1 + 2");
		double expected = 3;
		assertEquals(actual, expected, delta);
	}
	@Test
	public void test2() {
		BusinessLogic bl = new BusinessLogic();
		double actual = bl.solve("1 - 2");
		double expected = -1;
		assertEquals(actual, expected, delta);
	}
	@Test
	public void test3() {
		BusinessLogic bl = new BusinessLogic();
		double actual = bl.solve("1 * 2");
		double expected = 2;
		assertEquals(actual, expected, delta);
	}
	@Test
	public void test4() {
		BusinessLogic bl = new BusinessLogic();
		double actual = bl.solve("1 / 2");
		double expected = 0.5;
		assertEquals(actual, expected, delta);
	}
	@Test
	public void test5() {
		BusinessLogic bl = new BusinessLogic();
		double actual = bl.solve("2 + 3 * 4");
		double expected = 14;
		assertEquals(actual, expected, delta);
	}
	@Test
	public void test6() {
		BusinessLogic bl = new BusinessLogic();
		double actual = bl.solve("2 + 3 - 4");
		double expected = 1;
		assertEquals(actual, expected, delta);
	}
	@Test
	public void test7() {
		BusinessLogic bl = new BusinessLogic();
		double actual = bl.solve("2 + 3 / 4");
		double expected = 2.75;
		assertEquals(actual, expected, delta);
	}
	@Test
	public void test8() {
		BusinessLogic bl = new BusinessLogic();
		double actual = bl.solve("2 + 3 * 4 / 5");
		double expected = 4.4;
		assertEquals(actual, expected, delta);
	}
	@Test
	public void test9() {
		BusinessLogic bl = new BusinessLogic();
		double actual = bl.solve("2 + 3 * 4 - 5");
		double expected = 9;
		assertEquals(actual, expected, delta);
	}
	@Test
	public void test10() {
		BusinessLogic bl = new BusinessLogic();
		double actual = bl.solve("( 2 + 3 ) * 5");
		double expected = 25;
		assertEquals(actual, expected, delta);
	}
	@Test
	public void test11() {
		BusinessLogic bl = new BusinessLogic();
		double actual = bl.solve("( 2 - 3 ) * 5");
		double expected = -5;
		assertEquals(actual, expected, delta);
	}
	@Test
	public void test12() {
		BusinessLogic bl = new BusinessLogic();
		double actual = bl.solve("( 2 * 3 ) / 5");
		double expected = 1.2;
		assertEquals(actual, expected, delta);
	}
	@Test
	public void test13() {
		BusinessLogic bl = new BusinessLogic();
		double actual = bl.solve("( 2 + 3 ) / ( 2 - 3 )");
		double expected = -5;
		assertEquals(actual, expected, delta);
	}
	@Test
	public void test14() {
		BusinessLogic bl = new BusinessLogic();
		double actual = bl.solve("( 2 * 3 ) + ( 2 / 5 )");
		double expected = 6.4;
		assertEquals(actual, expected, delta);
	}
	@Test
	public void test15() {
		BusinessLogic bl = new BusinessLogic();
		double actual = bl.solve("2.5 + 3.2 * 5.5");
		double expected = 20.1;
		assertEquals(actual, expected, delta);
	}
	@Test
	public void test16() {
		BusinessLogic bl = new BusinessLogic();
		double actual = bl.solve("( 2.8 * 3.6 ) - 4.4");
		double expected = 5.68;
		assertEquals(actual, expected, delta);
	}
	@Test
	public void test17() {
		BusinessLogic bl = new BusinessLogic();
		double actual = bl.solve("2.2 * 2 / 4.2");
		double expected = 1.047619;
		assertEquals(actual, expected, delta);
	}
	@Test
	public void test18() {
		BusinessLogic bl = new BusinessLogic();
		double actual = bl.solve("4 / 0 * 5");
		double expected = 3; 
		assertEquals(actual, expected, delta);
	}
	@Test
	public void test19() {
		BusinessLogic bl = new BusinessLogic();
		double actual = bl.solve("2+3*5");
		double expected = 17;
		assertEquals(actual, expected, delta);
	}
	@Test
	public void test20() {
		BusinessLogic bl = new BusinessLogic();
		double actual = bl.solve("2*3/5");
		double expected = 1.2;
		assertEquals(actual, expected, delta);
	}

}
