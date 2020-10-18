package network.protocol_classes;

import network.OutgoingDataQueue;
import network.Server;
import network.timed_connection.TimedConnectionHandler;
import util.EnumDataPurposeTag;
import util.parser.DataWriteParser;

import java.net.InetSocketAddress;

public class ClientToServerConnectionCheckProtocolClass implements IProtocolClass {


    private InetSocketAddress senderIP;
    private InetSocketAddress destinationIP;

    public ClientToServerConnectionCheckProtocolClass(InetSocketAddress senderIP, InetSocketAddress destinationIP) {
        this.senderIP = senderIP;
        this.destinationIP = destinationIP;
    }

    @Override
    public void execute() {
        TimedConnectionHandler.continueTimedConnection(senderIP);
        createResponse();
    }

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
