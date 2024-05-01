package tcpWork;

public class AddMetroCardOperation extends CardOperation {
    private MetroCard metroCard;

    public AddMetroCardOperation() {
        this(new MetroCard());
    }

    public AddMetroCardOperation(MetroCard metroCard) {
        this.metroCard = metroCard;
    }

    public MetroCard getMetroCard() {
        return metroCard;
    }

    public void setMetroCard(MetroCard metroCard) {
        this.metroCard = metroCard;
    }
}
