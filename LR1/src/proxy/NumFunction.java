package proxy;

public class NumFunction implements Evaluatable {
    @Override
    public double evalf(double x) {
        return Math.exp(-x*x)*Math.sin(x);
    }
}
