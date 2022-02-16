package logistics.socketServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

    public final int server_port = 5000;

    private ServerSocket serverSocket;
    private Socket client;

    public SocketServer(){
        try {
            serverSocket = new ServerSocket(server_port);
            client = serverSocket.accept();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
