import View.App;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

public class ViewClient {
    @Parameter(names = {"--host", "-h"})
    protected String host;
    @Parameter(names = {"--port", "-p"})
    protected int port;
    @Parameter(names = {"--timeout", "-t"})
    protected int timeout;

    public static void main(String ... argv) {
        ViewClient main = new ViewClient();
        JCommander.newBuilder().addObject(main).build().parse(argv);
        main.run();
    }

    public void run(){
        new App(host, port, timeout);
    }

}
