import java.io.*;
import java.net.*;

public class Server {
    private ServerSocket serverSocket;
    private InputStream in;
    private OutputStream out;

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
        Request request = new Request();
        while (request.isActive()) {
            try {
                Socket clientSocket = serverSocket.accept();
                in = clientSocket.getInputStream();
                out = clientSocket.getOutputStream();
                out.write(request.getResponse().getBytes());
                out.flush();

                System.out.println("Accepted connection from " + clientSocket.getInetAddress().getHostName());

                byte[] data = new byte[1024];
                int dataBytes;

                while (request.isActive()) {
                    dataBytes = in.read(data);
                    request.setRequest(new String(data, 0, dataBytes));
                    handleRequest(request);
                    System.out.println(request);
                }
            } catch (IOException e) {
                System.out.println("Failed to accept client connection.");
                e.printStackTrace();
            }
        }
    }

    public void handleRequest(Request request) throws IOException {
        if (request.getRequest().equals("exit")) {
            request.setActive(false);
            request.setResponse("STOPPED");
            out.write(request.getResponse().getBytes());
        }
        request.setResponse("OK");
        out.write(request.getResponse().getBytes());
        out.flush();
    }

    public static void main(String[] args) {
        int port = 80;
        Server server = new Server(port);
        server.run();
    }
}
