package Socket;

import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private ServerSocket serverSocket;
    private volatile boolean keepRunning = true;
    private RequestService requester;

    public Server(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server is listening on port " + port);
        } catch (IOException e) {
            System.out.println("Could not listen on port " + port);
            System.exit(-1);
        }
    }

    public void run(int maxConnections) {
        ExecutorService executorService = Executors.newFixedThreadPool(maxConnections);
        while (keepRunning) {
            try {
                Socket clientSocket = serverSocket.accept();

                System.out.println("Accepted connection from " + clientSocket.getInetAddress().getHostName());

                Runnable connectionHandler = () -> {
                    try (InputStream in = clientSocket.getInputStream();
                         OutputStream out = clientSocket.getOutputStream()) {
                        out.write("STARTED".getBytes());
                        out.flush();

                        byte[] data = new byte[1024];
                        int dataBytes;

                        while (keepRunning) {
                            try {
                                dataBytes = in.read(data);
                                String request = new String(data, 0, dataBytes);
                                if (request.equals("kill")) {
                                    keepRunning = false;
                                    out.write("exit".getBytes());
                                    System.exit(1);
                                    break;
                                }
                                handleRequest(request, out);
                            } catch (Exception e) {
                                System.out.println("Failed to read data from client socket or client disconnect.");
                                break;
                            }
                        }
                    } catch (IOException e) {
                        System.out.println("Failed to open input/output streams for client socket.");
                    } finally {
                        try {
                            clientSocket.close();
                        } catch (IOException e) {
                            System.out.println("Failed to close client socket.");
                        }
                    }
                };
                executorService.submit(connectionHandler);
            } catch (Exception e) {
                System.out.println("Failed to accept client connection.");
            }
        }
        executorService.shutdown();
    }

    public void handleRequest(String request, OutputStream out) throws Exception {
        if (requester == null) {
            requester = new RequestService();
        }
        requester.setRequest(request);
        out.write(requester.execute().getBytes());
        out.flush();
        requester.resetRequestService();
    }

}
