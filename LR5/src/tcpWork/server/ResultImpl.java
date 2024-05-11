package tcpWork.server;

import java.io.Serializable;
import tcpWork.interfaces.Result;

public class ResultImpl implements Serializable, Result{

    private static final long serialVersionUID = 1L;
    private double scoreTime;
    private Object output;

    public ResultImpl(Object output, double scoreTime){
        this.setTime(scoreTime);
        this.setOutput(output);
    }

    public double scoreTime() {
        return scoreTime;
    }

    public void setTime(double scoreTime) {
        this.scoreTime = scoreTime;
    }

    public Object output()
    {
        return output;
    }

    public void setOutput(Object output) {
        this.output = output;
    }

    public String toString()
    {
        return "Result - " + output().toString() + "; time - " + scoreTime + "ns";
    }
}
