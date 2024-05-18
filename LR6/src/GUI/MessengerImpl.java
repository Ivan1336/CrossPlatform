package GUI;

import javax.swing.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.nio.charset.StandardCharsets;

public class MessengerImpl implements Messenger {
    private UITasks uiTasks;
    private MulticastSocket multicastSocket;
    private InetAddress address;
    private int port;
    private String name;
    private boolean cancelled;

    public MessengerImpl(UITasks uiTasks, InetAddress address, int port, String name) {
        this.name = name;
        this.uiTasks = uiTasks;
        this.address = address;
        this.port = port;
        this.cancelled = false;
        try {
            multicastSocket = new MulticastSocket(port);
            multicastSocket.setTimeToLive(2);
            multicastSocket.joinGroup(address);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    @Override
    public void start() {
        new Receiver().start();
    }

    @Override
    public void stop() {
        cancel();
        try {
            multicastSocket.leaveGroup(address);
            JOptionPane.showMessageDialog(null, "Connection shutdown");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Detach error\n" + e.getMessage());
        } finally {
            multicastSocket.close();
        }
    }

    @Override
    public void send() {
        new Sender().start();
    }

    private class Receiver extends Thread {

        @Override
        public void run() {
            byte[] in = new byte[512];
            DatagramPacket datagramPacket = new DatagramPacket(in, in.length);
            try {
                while (!isCancelled()) {
                    multicastSocket.receive(datagramPacket);
                    uiTasks.setText(new String(datagramPacket.getData(), 0, datagramPacket.getLength(), StandardCharsets.UTF_8));
                }
            } catch (IOException e) {
                if (isCancelled()) {
                    JOptionPane.showMessageDialog(null, "Connection completed");
                } else {
                    JOptionPane.showMessageDialog(null, "Reception error\n" + e.getMessage());
                }
            }
        }

    }

    private class Sender extends Thread {
        @Override
        public void run() {
            String msg = name + ": " + uiTasks.getMessage();
            byte[] out = msg.getBytes(StandardCharsets.UTF_8);
            DatagramPacket datagramPacket = new DatagramPacket(out, out.length, address, port);
            try {
                multicastSocket.send(datagramPacket);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Sending error");
            }
        }

    }

    private synchronized boolean isCancelled() {
        return cancelled;
    }

    private synchronized void cancel() {
        cancelled = true;
    }
}
