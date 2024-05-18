package bulletinBoardService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.nio.charset.StandardCharsets;

public class MulticastSenderReceiver {
    private String name;
    private InetAddress address;
    private int port = 3456;
    private MulticastSocket multicastSocket;

    public MulticastSenderReceiver(String name) {
        this.name = name;
        try {
            address = InetAddress.getByName("224.0.0.1");
            multicastSocket = new MulticastSocket(port);
            new Receiver().start();
            new Sender().start();
            System.out.println("|Start of conversation|Enter text");
        } catch (IOException e) {
            System.err.println("Error: " + e);
        }

    }

    private class Receiver extends Thread {

        @Override
        public void run() {
            try {
                byte[] in = new byte[256];
                DatagramPacket datagramPacket = new DatagramPacket(in, in.length);
                multicastSocket.joinGroup(address);
                while (true) {
                    multicastSocket.receive(datagramPacket);
                    System.out.println(new String(datagramPacket.getData(), 0, datagramPacket.getLength(), StandardCharsets.UTF_8));
                }
            } catch (IOException e) {
                System.err.println("Error: " + e);
            }

        }
    }

    private class Sender extends Thread {

        @Override
        public void run() {
            try (BufferedReader fromUser = new BufferedReader(new InputStreamReader(System.in))) {
                while (true) {
                    String in = fromUser.readLine();
                    if (in.equalsIgnoreCase("Final")) shutDown();
                    String msg = name + ": " + in;
                    byte[] output = msg.getBytes(StandardCharsets.UTF_8);
                    DatagramPacket datagramPacket = new DatagramPacket(output, output.length, address, port);
                    multicastSocket.send(datagramPacket);
                    Thread.sleep(1000);
                }
            } catch (IOException | InterruptedException e) {
                System.err.println("Error: " + e);
            }
        }
    }



    private void shutDown() {
        System.exit(0);
    }

    public static void main(String[] args) {
        new MulticastSenderReceiver("Name1");
    }
}
