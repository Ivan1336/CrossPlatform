package tcpWork;

public class PayMoneyOperation extends CardOperation {
    public String serialNumber;
    public double money;

    public PayMoneyOperation(String serialNumber, double money) {
        this.serialNumber = serialNumber;
        this.money = money;
    }

    public PayMoneyOperation() {
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
