package network;


import javafx.scene.layout.Priority;
import util.parser.DataWriteParser;

import java.util.PriorityQueue;

/**
 * This is the class where the fully parsed data blocks will be stored until the Server.java sends it to the clients
 *
 * Thread: serverThread
 */
public class OutgoingDataQueue {

    private static PriorityQueue<DataWriteParser> outgoingDataQueue;

    public OutgoingDataQueue() {
        outgoingDataQueue = new PriorityQueue<>();

    }

    public static void addToQueue(DataWriteParser newData) {
        outgoingDataQueue.add(newData);
    }

    public static DataWriteParser getData() {
        return outgoingDataQueue.poll();
    }

    public static DataWriteParser peakData() {
        return outgoingDataQueue.peek();
    }

    public static boolean hasData() {

        if (outgoingDataQueue.peek() == null) {
            return false;
        }
        return true;
    }
}
