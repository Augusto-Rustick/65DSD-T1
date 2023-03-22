import Socket.Server;

public class StartServer {
    public static void main(String[] args) {
        int port = 80;
        Server server = new Server(port);
        server.run(5);
    }
}
