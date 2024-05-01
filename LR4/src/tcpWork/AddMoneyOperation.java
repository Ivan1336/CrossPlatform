package tcpWork;

public class AddMoneyOperation extends CardOperation {
    private String serialNumber;
    private double money;

    public AddMoneyOperation(String serialNumber, double money) {
        this.serialNumber = serialNumber;
        this.money = money;
    }

    public AddMoneyOperation() {
        this("Untitled", 0);
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
