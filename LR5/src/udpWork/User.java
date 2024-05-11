package udpWork;


import java.io.Serializable;
import java.net.InetAddress;

public class User implements Serializable {
    private static final long serialVersionUID = 1;

    private int port;
    private InetAddress address;

    public User(InetAddress address, int port){
        this.port = port;
        this.address = address;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public InetAddress getAddress() {
        return address;
    }

    public void setAddress(InetAddress address) {
        this.address = address;
    }

    public String toString(){
        return "Address: " + address.getCanonicalHostName() + ", port: " + port;
    }

    public boolean equals(User user){
        return port == user.getPort() && address.getCanonicalHostName().equals(user.getAddress().getCanonicalHostName());
    }
}

