package util.parser;


import util.EnumDataPurposeTag;

import java.net.InetSocketAddress;

/**
 * This class will take in raw data that was read from the server and split it up into chunks that is easier to work with
 *
 * Thread: serverThread
 *
 * Data block standard: <SENDER_IP>|<DESTINATION_IP>|<PURPOSE_TAG>|<THE ACTUALLY DATA BEING SENT> Example: /10.0.0.15:36484|10.0.0.11:5555|CLIENT_TO_SERVER_MESSAGE|Hello World
 */
public class DataReadParser {

    private int[] splitterIndexes;
    private String rawData;

    private InetSocketAddress senderIP;
    private InetSocketAddress destinationIP;
    private EnumDataPurposeTag purposeTag;
    private String data;

    public DataReadParser(String rawData) {
        this.rawData = rawData;
        splitterIndexes = new int[3];

        findSplitterIndexes();
        parseSenderIp();
        parseDestinationIP();
        parsePurposeTag();
        parseData();
    }


    private void findSplitterIndexes(){
        int letterOffset = 0;
        String changingRawData = rawData;

        for(int i = 0; i < 3; i++){

            //Find index of splitter
            int splitterIndex = changingRawData.indexOf('|');
            int trueIndex = letterOffset + splitterIndex;
            splitterIndexes[i] = trueIndex;;

            //Setup letterOffSet
            letterOffset += (splitterIndex + 1);

            //Change string data
            changingRawData = changingRawData.substring(splitterIndex + 1);

        }
    }

    private void parseSenderIp(){
        String ipWithPort = rawData.substring(0,splitterIndexes[0]);

        String ip = ipWithPort.substring(0,ipWithPort.indexOf(':'));
        int port = Integer.parseInt(ipWithPort.substring(ip.length() + 1));

        senderIP = new InetSocketAddress(ip,port);
    }


    private void parseDestinationIP(){
        String ipWithPort = rawData.substring(splitterIndexes[0] + 1,splitterIndexes[1]);

        String ip = ipWithPort.substring(0,ipWithPort.indexOf(':'));
        int port = Integer.parseInt(ipWithPort.substring(ip.length() + 1));

        destinationIP = new InetSocketAddress(ip,port);
    }

    private void parsePurposeTag(){
        String strPurposeTag = rawData.substring(splitterIndexes[1] + 1,splitterIndexes[2]);

        purposeTag = EnumDataPurposeTag.valueOf(strPurposeTag);

    }

    private void parseData(){
        String data = rawData.substring(splitterIndexes[2] + 1);

        this.data = data;

    }

    public InetSocketAddress getSenderIP() {
        return senderIP;
    }

    public InetSocketAddress getDestinationIP() {
        return destinationIP;
    }

    public EnumDataPurposeTag getPurposeTag() {
        return purposeTag;
    }

    public String getData() {
        return data;
    }

    @Override
    public String toString(){
        String str = "";

        str += "\nSender IP: " + senderIP;
        str += "\nDestination IP: " + destinationIP;
        str += "\nPurpose Tag: " + purposeTag;
        str += "\nData: " + data;

        return str;

    }


}
