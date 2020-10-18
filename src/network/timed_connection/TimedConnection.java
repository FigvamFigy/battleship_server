package network.timed_connection;


import util.Constants;

import java.net.InetSocketAddress;
import java.util.Timer;
import java.util.TimerTask;

/**
 * This class will be used to simply give an address and whenever the server does not recieve a reply from a client, it will drop the connection
 *
 * Thread: serverThread
 */
public class TimedConnection {

    private InetSocketAddress address;
    private boolean shouldContinueToSend;

    private Timer timer;


    public TimedConnection(InetSocketAddress address) {
        this.address = address;
    }




    public void startTimedConnection(){


        TimerTask timerTask = new TimerTask() {//This should only run once, and that is after the connection timer has ended
            @Override
            public void run() {

                TimedConnectionHandler.addConnectionToClose(address);
            }
        };

        timer = new Timer(false);
        timer.schedule(timerTask, Constants.TIME_UNTIL_CONNECTION_CLOSE);

    }

    public void resetTimedConnection(){
        timer.cancel();
        timer = null;
        startTimedConnection();
    }

    public InetSocketAddress getAddress() {
        return address;
    }
}
