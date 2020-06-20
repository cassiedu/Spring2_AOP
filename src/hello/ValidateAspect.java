package hello;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Order(2)
@Aspect
@Component
public class ValidateAspect {
    @Before("execution(public int hello.ArithmeticCalculator.*(int , int))")
    public void validate(JoinPoint joinPoint) {
        System.out.println("validate:" + Arrays.asList(joinPoint.getArgs()));
    }
}
