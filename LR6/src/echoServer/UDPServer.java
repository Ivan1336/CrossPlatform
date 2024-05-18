package echoServer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

public abstract class UDPServer implements Runnable {
    private final int bufferSize;
    private final int port;
    private volatile boolean isShutDown = false;

    public UDPServer(int bufferSize, int port) {
        this.bufferSize = bufferSize;
        this.port = port;
    }

    public UDPServer(int port) {
        this(8192, port);
    }


    @Override
    public void run() {
        byte[] buffer = new byte[bufferSize];
        try (DatagramSocket datagramSocket = new DatagramSocket(port)) {
            datagramSocket.setSoTimeout(10000);
            while (true) {
                if (isShutDown) return;
                DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
                try {
                    datagramSocket.receive(datagramPacket);
                    this.respond(datagramSocket, datagramPacket);
                } catch (SocketTimeoutException e) {
                    if (isShutDown) return;
                } catch (IOException e) {
                    System.err.println(e.getMessage() + "\n" + e);
                }

            }
        } catch (SocketException e) {
            System.err.println("Couldn't connect to port: " + port + "\n" + e);
        }

    }

    public void shutDown() {
        this.isShutDown = true;
    }

    public abstract void respond(DatagramSocket socket, DatagramPacket request);
}
