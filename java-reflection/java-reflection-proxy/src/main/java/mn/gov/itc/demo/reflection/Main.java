package mn.gov.itc.demo.reflection;

import mn.gov.itc.demo.reflection.calc.Calculator;
import mn.gov.itc.demo.reflection.calc.CalculatorImpl;
import mn.gov.itc.demo.reflection.calc.CalculatorInitializer;

public class Main {
    public static void main(String[] args) throws Throwable {
        System.out.println("=========================================================");
        Calculator calculator = new CalculatorImpl();
        System.out.println(calculator.getClass().getName());
        System.out.println("calculator.sum(10, 5) = " + calculator.sum(10, 5));
        System.out.println("=========================================================");
        Calculator calculatorProxy = CalculatorInitializer.getInstance();
        System.out.println(calculatorProxy.getClass().getName());
        System.out.println("calculatorProxy.sum(10, 5) = " + calculatorProxy.sum(10, 5));
    }
}
