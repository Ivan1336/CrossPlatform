package echoServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class SenderThread extends Thread {
    private InetAddress server;
    private int port;
    private DatagramSocket socket;
    private volatile boolean stopped = false;

    public SenderThread(DatagramSocket socket, InetAddress address, int port) {
        this.socket = socket;
        this.server = address;
        this.port = port;
        this.socket.connect(address, port);
    }

    @Override
    public void run() {
        try (BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                if (stopped) return;
                System.out.println("Enter 'Final' to exit");
                System.out.print("Enter text: ");
                String msg = userInput.readLine();
                if (msg.equalsIgnoreCase("Final")) setStopped();
                byte[] data = msg.getBytes(StandardCharsets.UTF_8);
                DatagramPacket output = new DatagramPacket(data, data.length, server, port);
                socket.send(output);
                Thread.sleep(1000);
                Thread.yield();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setStopped() {
        this.stopped = true;
    }
}
