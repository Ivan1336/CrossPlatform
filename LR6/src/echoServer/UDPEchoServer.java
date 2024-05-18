package echoServer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPEchoServer extends UDPServer {
    public final static int DEFAULT_PORT = 7;

    public UDPEchoServer() {
        super(DEFAULT_PORT);
    }

    @Override
    public void respond(DatagramSocket socket, DatagramPacket request) {
        DatagramPacket reply = new DatagramPacket(request.getData(), request.getLength(), request.getAddress(), request.getPort());
        try {
            socket.send(reply);
        } catch (IOException e) {
            System.err.println(e.getMessage() + "\n" + e);
        }
    }

    public static void main(String[] args) {
        UDPServer UDPServer = new UDPEchoServer();
        Thread thread = new Thread(UDPServer);
        thread.start();
        System.out.println("Start echo-server.");
        try {
            Thread.sleep(60000);//stop (60 секунд)
        } catch (InterruptedException e) {
            System.err.println(e.getMessage() + "\n" + e);
        }
        UDPServer.shutDown();
        System.out.println("Finish echo-server.");
    }
}