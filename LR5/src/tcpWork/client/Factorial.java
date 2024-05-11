package tcpWork.client;

import tcpWork.interfaces.Executable;

import java.io.Serializable;
import java.math.BigInteger;

public class Factorial implements Serializable, Executable {

    private static final long serialVersionUID = -1L;
    private int number;

    public Factorial(int number) {
        this.number = (number);
    }

    public Object execute() {
        return factorial(number);
    }

    public static BigInteger factorial(int n) {
        BigInteger res = BigInteger.valueOf(1);
        for (int i = 2; i <= n; i++) {
            res = res.multiply(BigInteger.valueOf(i));
        }
        return res;
    }

}
