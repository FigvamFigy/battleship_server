package util.parser;


/**
 * This parser receives all the aspects of data that is needed to create a new data block to be sent. The parsed data here will be the String that is sent through the network
 *
 * Thread: serverThread
 * Data block standard: <SENDER_IP>|<DESTINATION_IP>|<PURPOSE_TAG>|<THE ACTUALLY DATA BEING SENT> Example: /10.0.0.15:36484|10.0.0.11:5555|CLIENT_TO_SERVER_MESSAGE|Hello World
 */
public class DataWriteParser {
}
