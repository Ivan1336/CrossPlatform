package tcpWork;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MetroServer extends Thread {
    private String appender = "@ |Повідомлення від сервера| ";
    private MetroCardBank cardBank;
    private ServerSocket serverSocket;
    private int serverPort = -1;

    public MetroServer(int serverPort){
        this.cardBank = new MetroCardBank();
        this.serverPort = serverPort;
    }

    @Override
    public void run(){
        try{
            this.serverSocket = new ServerSocket(serverPort);
            System.out.println(appender + "Сервер Метро запущено!");
            while (true){
                System.out.println(appender + "Очікування клієнта");
                Socket socket = serverSocket.accept();
                System.out.println(appender + "Клієнт підключений: " + socket.getInetAddress().getCanonicalHostName() + " на порту " + socket.getPort());
                ClientHandler clientHandler = new ClientHandler(cardBank, socket);
                clientHandler.startService();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
                System.out.println(appender + "Сервер Метро зупинено!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        MetroServer metroServer = new MetroServer(7891);
        metroServer.start();
    }
}
