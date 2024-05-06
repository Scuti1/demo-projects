package mn.gov.itc.demo.reflection.calc;

public class CalculatorImpl implements Calculator {
    @Override
    public int sum(int a, int b) {
        return a + b;
    }

    @Override
    public int minus(int a, int b) {
        return a - b;
    }
}
