package tcpWork;

import java.io.Serializable;

public class MetroCard implements Serializable {
    private String serialNumber;
    private User user;
    private String university;
    private double balance;

    public MetroCard(String serialNumber, User user, String university) {
        this.serialNumber = serialNumber;
        this.user = user;
        this.university = university;
    }

    public MetroCard() {
        this("Untitled", new User(), "Untitled");
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public double getBalance() {
        return balance;
    }

    public void addMoney(double balance) {
        this.balance += balance;
    }

    public void payMoney(double amount) {
        if (balance >= amount) {
            balance -= amount;
        } else {
            System.out.println("Недостатньо коштів на картці!");
        }
    }

    public String toString() {
        return "№: " + serialNumber + "\n<Користувач>\n" +
                user + "\n" +
                "Навчальний заклад: " + university + "\n" +
                "Баланс: " + balance + " $";
    }
}
