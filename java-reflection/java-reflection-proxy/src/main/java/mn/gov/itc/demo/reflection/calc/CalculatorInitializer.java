package mn.gov.itc.demo.reflection.calc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class CalculatorInitializer {

    /**
     * Уг method-н тусламжтайгаар Calculator interface-н proxy классын
     * object-г үүсгэнэ
     */
    public static Calculator getInstance() {
        /*
         * тухайн proxy object-н method-г дуудах үед уг handler
         * барьж авах хэрхэн ажиллахыг тодорхойлно.
         */
        InvocationHandler handler = new InvocationHandler() {

            /**
             * @param proxyObject proxy object өөрөө байна.
             * @param method дуудагдаж буй method байна.
             * @param params дуудагдаж буй method-руу дамжуулсан параметерүүд байна.
             */
            @Override
            public Object invoke(Object proxyObject, Method method, Object[] params) throws Throwable {
                if (method.getName().equals("sum")) {
                    return (int) params[0] + (int) params[1];
                }

                if (method.getName().equals("minus")) {
                    return (int) params[0] - (int) params[1];
                }

                throw new IllegalAccessException(method.getName() + " not defined this proxy class");
            }
        };
        return (Calculator) Proxy.newProxyInstance(CalculatorInitializer.class.getClassLoader(), new Class[]{Calculator.class}, handler);
    }
}
