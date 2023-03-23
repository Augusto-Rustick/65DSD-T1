import Socket.Server;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

public class StartServer {
    @Parameter(names = {"--port", "-p"})
    protected int port;
    @Parameter(names = {"--maxconnections", "-m"})
    protected int connections;
    public static void main(String ... argv) {
        StartServer main = new StartServer();
        JCommander.newBuilder().addObject(main).build().parse(argv);
        main.run();
    }

    public void run(){
        Server server = new Server(port);
        server.run(connections);
    }
}
