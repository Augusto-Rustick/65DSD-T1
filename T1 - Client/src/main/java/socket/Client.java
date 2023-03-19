package Socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    public Client(String host, int port, int timeout) throws IOException {
        connect(host, port, timeout);
    }

    InputStream in;
    OutputStream out;
    byte[] data = new byte[1024];
    int dataBytes;
    RequestSocket request = new RequestSocket();
    Socket conn;

    public String write(String req) throws IOException {
        request.setRequest(req);
        out.flush();
        out.write(request.getRequest().getBytes());
        dataBytes = in.read(data);
        request.setResponse(new String(data, 0, dataBytes));
        return request.getResponse();
    }

    private void connect(String host, int port, int timeout) throws IOException {
        try  {
            conn = new Socket(host, port);
            conn.setSoTimeout(timeout);
            in = conn.getInputStream();
            out = conn.getOutputStream();
            dataBytes = in.read(data);
            request.setResponse(new String(data, 0, dataBytes));
        } catch (UnknownHostException err) {
            System.out.println(err.getMessage());
        }
    }
}