package Socket;

import java.io.*;
import java.net.*;

public class Server {
    private ServerSocket serverSocket;
    private InputStream in;
    private OutputStream out;
    private boolean isAlive = true;

    public Server(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server is listening on port " + port);
        } catch (IOException e) {
            System.out.println("Could not listen on port " + port);
            System.exit(-1);
        }
    }

    public void run() {
        while (isAlive) {
            try {
                Socket clientSocket = serverSocket.accept();
                in = clientSocket.getInputStream();
                out = clientSocket.getOutputStream();
                out.write("STARTED".getBytes());
                out.flush();

                System.out.println("Accepted connection from " + clientSocket.getInetAddress().getHostName());

                byte[] data = new byte[1024];
                int dataBytes;

                while (isAlive) {
                    dataBytes = in.read(data);
                    handleRequest(new String(data, 0, dataBytes));
                }
            } catch (Exception e) {
                System.out.println("Failed to accept client connection or client disconnected.");
            }
        }
    }

    public void handleRequest(String request) throws Exception {
        if (request.equals("exit")) {
            isAlive = false;
            out.write("STOPPED".getBytes());
        }
        RequestSocket req = new RequestSocket(request);
        out.write(req.execute().getBytes());
        out.flush();
    }

    public static void main(String[] args) {
        int port = 80;
        Server server = new Server(port);
        server.run();
    }
}
