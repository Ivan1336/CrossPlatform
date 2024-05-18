package echoServer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;

public class ReceiverThread extends Thread {
    private DatagramSocket socket;
    private volatile boolean stopped = false;

    public ReceiverThread(DatagramSocket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        byte[] buffer = new byte[65507];
        while (true) {
            if (stopped) return;
            DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
            try {
                socket.receive(datagramPacket);
                System.out.print("Answer -> ");
                String msg = new String(datagramPacket.getData(), 0, datagramPacket.getLength(), StandardCharsets.UTF_8);
                if (msg.equalsIgnoreCase("Final")) setStopped();
                System.out.println(msg);
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }

    public void setStopped() {
        this.stopped = true;
    }
}
