package network.protocol_classes;


import network.IncomingDataQueue;
import util.EnumDataPurposeTag;
import util.parser.DataReadParser;

import java.net.InetSocketAddress;

/**
 * This class will be responsible for parsing the incoming data and applying the correct actions from the data
 *
 * Thread: dataProcessingThread
 */
public class DataDecider {

    private boolean shouldRun;


    public DataDecider() {
        shouldRun = false;
    }

    public void start(){
        shouldRun = true;

        while(shouldRun){
            if(IncomingDataQueue.hasData()){
                String rawData = IncomingDataQueue.getData();

                DataReadParser dataReadParser = new DataReadParser(rawData);

                executePurpose(dataReadParser);
            }
        }
    }


    private void executePurpose(DataReadParser dataReadParser){
        InetSocketAddress senderIP = dataReadParser.getSenderIP();
        InetSocketAddress destinationIP = dataReadParser.getDestinationIP();
        EnumDataPurposeTag purposeTag = dataReadParser.getPurposeTag();
        String data = dataReadParser.getData();

        IProtocolClass protocolClass;

        switch (purposeTag){
            case CLIENT_TO_SERVER_CONNECTION_CHECK:
                protocolClass = new ClientToServerConnectionCheckProtocolClass(senderIP,destinationIP);
                protocolClass.execute();
                break;

        }

    }
}
