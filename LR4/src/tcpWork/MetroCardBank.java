package tcpWork;
import java.util.HashMap;
public class MetroCardBank {
    private HashMap <String, MetroCard> cardMap;

    public MetroCardBank(){
        cardMap = new HashMap<>();
    }

    public boolean isCardRegistered(String serialNumber){
        return cardMap.containsKey(serialNumber);
    }

    public int getCardNumber(){
        return cardMap.size();
    }

    public void addCard(MetroCard card){
        if (isCardRegistered(card.getSerialNumber()))
            System.out.println("Карта вже зареєстрована!");
        else
            cardMap.put(card.getSerialNumber(), card);
    }

    public boolean removeCard(String serialNumber){
        if (isCardRegistered(serialNumber)) {
            cardMap.remove(serialNumber);
            return true;
        } else
            return false;
    }

    public boolean addMoney(String serialNumber, double money){
        if (isCardRegistered(serialNumber)) {
            cardMap.get(serialNumber).addMoney(money);
            return true;
        } else
            return false;
    }

    public boolean payMoney(String serialNumber, double money){
        boolean isPayed;
        if (isCardRegistered(serialNumber)){
            double currentMoney = cardMap.get(serialNumber).getBalance();
            if (currentMoney >= money) {
                cardMap.get(serialNumber).payMoney(money);
                isPayed = true;
            }else
                isPayed = false;

        } else
            isPayed = false;

        return isPayed;
    }

    public double getBalance(String serialNumber){
        if (isCardRegistered(serialNumber)){
            return cardMap.get(serialNumber).getBalance();
        } else
            return -1;
    }

    public String getCardInfo(String serialNumber){
        if (isCardRegistered(serialNumber)){
            return cardMap.get(serialNumber).toString();
        } else
            return null;
    }

    public String toString(){
        StringBuilder stringBuilder;
        if (cardMap.size() == 0)
            stringBuilder = new StringBuilder("Список карт пустий.");
        else {
            stringBuilder = new StringBuilder("Список зареєстрованих карт: ");

            for (String keys : cardMap.keySet())
                stringBuilder.append("\n\n").append(cardMap.get(keys));
        }

        return stringBuilder.toString();
    }
}
