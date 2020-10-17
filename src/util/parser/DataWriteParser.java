package util.parser;


import util.EnumDataPurposeTag;

import java.net.InetSocketAddress;

/**
 * This parser receives all the aspects of data that is needed to create a new data block to be sent. The parsed data here will be the String that is sent through the network
 *
 * Thread: serverThread
 * Data block standard: <SENDER_IP>|<DESTINATION_IP>|<PURPOSE_TAG>|<THE ACTUALLY DATA BEING SENT> Example: /10.0.0.15:36484|10.0.0.11:5555|CLIENT_TO_SERVER_MESSAGE|Hello World
 */
public class DataWriteParser {


    private InetSocketAddress senderIP;
    private InetSocketAddress destinationIP;
    private EnumDataPurposeTag purposeTag;
    private String data;

    private String parsedData;

    public DataWriteParser(InetSocketAddress senderIP, InetSocketAddress destinationIP, EnumDataPurposeTag purposeTag, String data) {
        this.senderIP = senderIP;
        this.destinationIP = destinationIP;
        this.purposeTag = purposeTag;
        this.data = data;

        combineData();
    }

    private void combineData(){
        parsedData = senderIP + "|" + destinationIP + "|" + purposeTag.toString() + "|" + data;
    }

    public String getParsedData() {
        return parsedData;
    }

    public String toString(){
        String str = "";
        str += parsedData;
        return str;
    }



}
