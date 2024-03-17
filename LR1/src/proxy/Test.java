package proxy;
import java.lang.reflect.Proxy;

public class Test {//Завдання 5
    public static void main(String[] args) {
        NumFunction fun = new NumFunction();
        Evaluatable e = (Evaluatable) Proxy.newProxyInstance(fun.getClass().getClassLoader(),
                fun.getClass().getInterfaces(), new FunHandler(fun));
        e.evalf(2.0);
    }
}