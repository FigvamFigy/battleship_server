package thread;

import network.Server;
import util.Constants;

import java.net.InetAddress;

/**
 * This thread is called from the JavaFX thread to start the server thread
 *
 * Thread: JavaFX Thread
 */
public class ServerThread implements Runnable{

    private Thread thread;

    private String ip;
    private int port;

    public ServerThread(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    @Override
    public void run(){
        Server server = new Server(ip,port);
        server.startServer();


    }

    public void start(){
        if(thread == null){
            thread = new Thread(this, Constants.SERVER_THREAD_NAME);
            thread.start();
        }
    }
}
