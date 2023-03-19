package socket;

import socket.RequestSocket;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    InputStream in;
    OutputStream out;

    public void connect(String host, int port, int timeout) throws IOException {
        RequestSocket request = new RequestSocket();
        try (Socket conn = new Socket(host, port)) {
            conn.setSoTimeout(timeout);
            in = conn.getInputStream();
            out = conn.getOutputStream();
            Scanner input = new Scanner(System.in);
            byte[] data = new byte[1024];
            int dataBytes;
            while (true) {
                dataBytes = in.read(data);
                request.setResponse(new String(data, 0, dataBytes));
                System.out.println(request.getResponse());
                request.setRequest(input.next());
                out.write(request.getRequest().getBytes());
                out.flush();
            }
        } catch (UnknownHostException err) {
            System.out.println(err.getMessage());
        }
    }
}