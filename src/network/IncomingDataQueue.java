package network;


import java.util.PriorityQueue;

/**
 * This class will have its static methods called in Server.java whenever new data arrives. This essentially holds data that is waiting to be proccessed by DataDecider.java
 *
 * Thread: serverThread
 */
public class IncomingDataQueue {

    //A queue is used in order to have the oldest data be processed first
    private volatile static PriorityQueue<String> incomingDataQueue;

    public IncomingDataQueue(){
        incomingDataQueue = new PriorityQueue<>();

    }

    /**
     * Adds new data to the queue
     *
     * @param newData The raw data that was read by the server. It is not yet parsed
     */
    public synchronized static void addToQueue(String newData){
        incomingDataQueue.add(newData);
    }

    /**
     * After calling this method, the head of the queue will be deleted
     *
     * @return returns the data in the first position of the queue
     */
    public synchronized static String getData(){
        return incomingDataQueue.poll();
    }

    /**
     * This methods returns the head of the queue without deleting it
     * @return returns the data in the first position of the queue
     */
    public static boolean hasData(){

        if(incomingDataQueue.peek() == null){
            return false;
        }
        return true;
    }
}
