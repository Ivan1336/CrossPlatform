package echoServer;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPEchoClient {
    public final static int PORT = 7;

    public static void main(String[] args) {
        String hostName = "localhost";
        if (args.length > 0) {
            hostName = args[0];
        }

        try {
            InetAddress serverAddress = InetAddress.getByName(hostName);
            DatagramSocket datagramSocket = new DatagramSocket();
            Thread sender = new SenderThread(datagramSocket, serverAddress, PORT);
            sender.start();
            Thread receiver = new ReceiverThread(datagramSocket);
            receiver.start();
        } catch (UnknownHostException e) {
            System.err.println(e);
        } catch (SocketException e) {
            System.err.println(e);
        }
    }
}
