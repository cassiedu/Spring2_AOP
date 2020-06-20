package hello;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

//把此类声明为一个切面：
// 1.需要把该类放入IOC容器，即加@component注解
// 2.把该类声明为切面
@Order(1)
@Aspect
@Component
public class LoggingAspect {
    //定义一个方法，用于声明切入点表达式(重用路径)
    @Pointcut("execution(public int hello.ArithmeticCalculator.*(int , int))")
    public void PointCut() {

    }

    //在接口ArithmeticCalculator的每一个实现类之前执行以下代码
    @Before("PointCut()")
    public void beforeMethod(JoinPoint joinPoint) {
        //以下两行是实现细节，需要传参joinointP
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        System.out.println("The method " + methodName + " begins with " + args);
    }

    //后置通知，无论是否出现异常都会被执行
    //后置通知中，不能返回
    @After("PointCut()")
    public void afterMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("The method " + methodName + " ends ");
    }

    //返回通知：在方法正常结束后执行的通知;
    //可以访问到方法的返回值
    @AfterReturning(value = "execution(* hello.*.*(..))", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("The method " + methodName + " ends with " + result);
    }

    //异常通知
    @AfterThrowing(value = "execution(* hello.*.*(..))", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Exception ex) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("The method " + methodName + " occurs exception " + ex);
    }

    //环绕通知 最详细但是最不常用
    //类似于动态代理的全过程，ProceedingJoinPoint类型的参数决定是否执行该方法
//    @Around("execution(public int hello.ArithmeticCalculator.add(int , int))")
//    public Object aroundMethod(ProceedingJoinPoint pjd){
//         Object result = null;
//        String methodName = pjd.getSignature().getName();
//        List<Object> args = Arrays.asList(pjd.getArgs());
//         try {
//             System.out.println("The method " + methodName + " begins with " + args);
//             result = pjd.proceed();
//             System.out.println("The method " + methodName + " ends with " + result);
//         } catch (Throwable throwable) {
//             System.out.println("The method " + methodName + " occurs exception " + throwable);
//             throw new RuntimeException(throwable);
//         }
//        System.out.println("The method " + methodName + " ends ");
//        return result;
//    }

}

