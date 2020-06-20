package hello;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class ArithmeticCalculatorLoggingProxy {
    private ArithmeticCalculator target;

    public ArithmeticCalculatorLoggingProxy(ArithmeticCalculator target) {
        this.target = target;
    }

    public ArithmeticCalculator getLoggingProxy() {
        //代理对象由哪一个类加载器加载
        ClassLoader loader = target.getClass().getClassLoader();
        //代理对象的类型，其中有哪些方法
        Class[] interfaces = new Class[]{ArithmeticCalculator.class};
        //当调用代理对象其中的方法时，应该怎么做(执行该代码)
        InvocationHandler h = new InvocationHandler() {
            /**
             * @param proxy 正在返回的代理对象，一般情况下不使用该对象
             * @param method 正在被调用的方法
             * @param args 传入的参数
             * @return
             * @throws Throwable
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                String methodName = method.getName();
                //日志
                System.out.println("The method " + methodName + " begins with " + Arrays.asList(args));
                //执行方法
//                Object result = method.invoke(target, args);

                Object result = null;
                //执行方法
                try {
                    result = method.invoke(target, args);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //日志
                System.out.println("The method " + methodName + " ends with " + result);
                return result;
            }
        };
        ArithmeticCalculator proxy = (ArithmeticCalculator) Proxy.newProxyInstance(loader, interfaces, h);
        return proxy;
    }
}
