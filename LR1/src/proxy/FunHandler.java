package proxy;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class FunHandler implements InvocationHandler {
    private Object object;

    public FunHandler(Object hidden) {
        this.object = hidden;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args){
        System.out.println("Ім'я методу: " + method.toGenericString());
        System.out.print("Параметри методу: ");
        for (Object a : args){
            System.out.print(a + " ");
        }
        double res = 0;
        double start = System.nanoTime();
        try {
            res = (double) method.invoke(object, args);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        double finish = System.nanoTime();
        System.out.println("\nЧас обчислення методу: " + (finish-start) + " ns");
        System.out.println("Результат обчислення методу: " + res);
        return res;
    }
}