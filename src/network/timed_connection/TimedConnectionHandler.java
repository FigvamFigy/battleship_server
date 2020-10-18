package network.timed_connection;

import java.net.InetSocketAddress;
import java.util.ArrayList;

public class TimedConnectionHandler {

    private static ArrayList<TimedConnection> arrayListTimedConnection;
    private static ArrayList<InetSocketAddress> arrayListAddressesToClose;

    public TimedConnectionHandler() {
        arrayListTimedConnection = new ArrayList<>();
        arrayListAddressesToClose = new ArrayList<>();
    }


    public void addTimedConnection(InetSocketAddress address){
        TimedConnection timedConnection = new TimedConnection(address);
        arrayListTimedConnection.add(timedConnection);
        timedConnection.startTimedConnection();
    }


    public static void addConnectionToClose(InetSocketAddress address){
        arrayListAddressesToClose.add(address);
    }


    public void closeTimedConnection(InetSocketAddress address){
        arrayListAddressesToClose.remove(address);
        arrayListTimedConnection.remove(address);
    }

    public static void continueTimedConnection(InetSocketAddress address){
        for(TimedConnection connection: arrayListTimedConnection){
            if(connection.getAddress().equals(address)){
                connection.resetTimedConnection();
                System.out.println("Connection held for: " + connection.toString());
            }

        }

    }


    public boolean hasConnectionsToClose(){
        if(arrayListAddressesToClose.isEmpty()){
            return false;
        }
        return true;
    }

    /**
     * Returns the first element in the array, null if empty
     *
     * @return
     */
    public InetSocketAddress getAddressToClose(){
        if(!arrayListAddressesToClose.isEmpty()){
            return arrayListAddressesToClose.get(0);
        }

        return null;
    }


}
