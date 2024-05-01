package tcpWork;

public class ShowBalanceOperation extends CardOperation {
    private String serialNumber;
    private double balance;

    public ShowBalanceOperation() {
    }

    public ShowBalanceOperation(String serialNumber) {
        this.serialNumber = serialNumber;
        this.balance = balance;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
