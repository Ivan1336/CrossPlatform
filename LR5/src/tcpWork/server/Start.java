package tcpWork.server;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Start extends Thread {
    ServerSocket socket;

    public Start(ServerSocket socket) throws IOException {
        this.socket = socket;
    }

    public void run() {
        while (true) {
            Socket server;
            try {
                server = socket.accept();
                new Connection(server).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
