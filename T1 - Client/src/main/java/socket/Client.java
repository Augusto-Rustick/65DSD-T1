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
    Socket conn;

    public String write(String req) throws IOException {
        out.flush();
        out.write(req.getBytes());
        dataBytes = in.read(data);
        return new String(data, 0, dataBytes);
    }

    private void connect(String host, int port, int timeout) throws IOException {
        try  {
            conn = new Socket(host, port);
            conn.setSoTimeout(timeout);
            in = conn.getInputStream();
            out = conn.getOutputStream();
            dataBytes = in.read(data);
        } catch (UnknownHostException err) {
            System.out.println(err.getMessage());
        }
    }
}