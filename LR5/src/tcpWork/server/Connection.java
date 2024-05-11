package tcpWork.server;



import tcpWork.interfaces.Executable;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import static tcpWork.server.ServerFrame.Text;

public class Connection extends Thread {
    ObjectInputStream in;
    ObjectOutputStream out;
    Socket socket;

    public Connection(Socket socket) throws IOException {
        this.socket = socket;
        in = new ObjectInputStream(socket.getInputStream());
        out = new ObjectOutputStream(socket.getOutputStream());
    }

    public void run() {
        while (true) {
            try {
                try {

                    String classFile = (String) in.readObject();
                    classFile.replaceFirst("client", "server");
                    byte[] b = (byte[]) in.readObject();
                    FileOutputStream fos = new FileOutputStream(classFile);
                    fos.write(b);
                    Executable executable = (Executable) in.readObject();

                    Text.append("Start counting" + "\n");
                    double startTime = System.nanoTime();
                    Object output = executable.execute();
                    double endTime = System.nanoTime();
                    double competitionTime = endTime - startTime;
                    ResultImpl result = new ResultImpl(output, competitionTime);

                    classFile.replaceFirst("server", "client");
                    out.writeObject(classFile);
                    FileInputStream fis = new FileInputStream(classFile);
                    byte[] bo = new byte[fis.available()];
                    fis.read(bo);
                    out.writeObject(bo);
                    out.writeObject(result);
                    Text.append("Sending result..." + "\n");


                } catch (SocketException e) {
                    break;
                }

            }catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }


}

