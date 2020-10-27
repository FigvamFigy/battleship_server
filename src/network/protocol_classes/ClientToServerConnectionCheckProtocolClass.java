package network.protocol_classes;

import network.OutgoingDataQueue;
import network.Server;
import network.timed_connection.TimedConnectionHandler;
import util.EnumDataPurposeTag;
import util.parser.DataWriteParser;

import java.net.InetSocketAddress;

/**
 * Every 9 seconds (from Constants.java), the client will send a request to the server to see if the server is still alive. If the server is alive, this class will be created
 * and will create a response to the client.
 *
 *
 * Thread: DataProcessingThread
 */
public class ClientToServerConnectionCheckProtocolClass implements IProtocolClass {


    private InetSocketAddress senderIP;
    private InetSocketAddress destinationIP;

    public ClientToServerConnectionCheckProtocolClass(InetSocketAddress senderIP, InetSocketAddress destinationIP) {
        this.senderIP = senderIP;
        this.destinationIP = destinationIP;
    }

    /**
     * After this method is called, it will say to the TimedConnectionHandler.java to keep holding the client connection
     *
     */
    @Override
    public void execute() {
        TimedConnectionHandler.continueTimedConnection(senderIP);
        createResponse();
    }

    /**
     * In order for the client to not close its socket, the serer will respond to the client with this message
     *
     */
    private void createResponse() {
        DataWriteParser dataWriteParser = new DataWriteParser(
                destinationIP,
                senderIP,
                EnumDataPurposeTag.CLIENT_TO_SERVER_CONNECTION_CHECK,
                "CHECK OK"
            );

        OutgoingDataQueue.addToQueue(dataWriteParser);

    }
}
