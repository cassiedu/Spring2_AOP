package hello;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
//        ArithmeticCalculator target = new ArithmeticCalculatorImp();
//        int result1 = target.add(1, 2);
//        System.out.println(result1);
//        System.out.println("---------------");
//
//        ArithmeticCalculator proxy = new ArithmeticCalculatorLoggingProxy(target).getLoggingProxy();
//        int result2 = proxy.add(3, 4);
//        System.out.println(result2);
//        System.out.println("---------------");

        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        hello.ArithmeticCalculator arithmeticCalculator = ctx.getBean(hello.ArithmeticCalculator.class);
        System.out.println(arithmeticCalculator.getClass().getName());
        int result = arithmeticCalculator.add(5, 1);
        System.out.println(result);
    }
}
