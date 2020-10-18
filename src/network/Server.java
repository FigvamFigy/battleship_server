package network;


import network.timed_connection.TimedConnectionHandler;
import thread.DataProcessingThread;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

/**
 * This class is responsible for holding and taking care of the connections with clients. This class does not handle the data except for reading it from the network and sending it to
 * IncomingData.java, and sending it from OutgoingData.java
 *
 * Thread: serverThread
 */
public class Server {

    private boolean isServerRunning;


    //Server Details
    private InetSocketAddress serverSocketAddress;


    //NIO server Stuff
    private Selector selector;// The selector stores all the connections
    private ServerSocketChannel serverSocket;

    //Outgoing/incoming data
    private OutgoingDataQueue outgoingDataQueue;
    private IncomingDataQueue incomingDataQueue;

    //timed connected handler
    private TimedConnectionHandler timedConnectionHandler;


    public Server(String serverAddress, int portNumber) {
        this.isServerRunning = false;
        serverSocketAddress = new InetSocketAddress(serverAddress,portNumber);
    }


    public void startServer() {
        System.out.println("--Starting Server--");

        serverSetup();
        serverLoop();
    }


    /**
     * This method creates the serverSocket and all of the other requirements for a server to exist
     */
    private void serverSetup() {
        try {
            System.out.println("Server Setup");
            //Server setup
            serverSocket = ServerSocketChannel.open();
            serverSocket.configureBlocking(false);

            serverSocket.bind(serverSocketAddress);;

            //Selector stuff
            selector = Selector.open();
            serverSocket.register(selector, SelectionKey.OP_ACCEPT);


            //Outgoing/incoming data initialization
            this.outgoingDataQueue = new OutgoingDataQueue();
            this.incomingDataQueue = new IncomingDataQueue();


            //timed connection handler
            this.timedConnectionHandler = new TimedConnectionHandler();

            //Start the dataprocessing thread
            DataProcessingThread dataProcessingThread = new DataProcessingThread();
            dataProcessingThread.start();

            isServerRunning = true;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }


    /**
     * After calling this method, we can only exit if isServerRunning is false.
     * This method hold three major aspects. With each iteration it will see if a connection should be accepted,have data to be read, or have data to be sent
     */
    private void serverLoop() {
        try {
            System.out.println("--Server Started: Entering Server Loop--");

            while (isServerRunning) {

                Thread.sleep(1000);

                if(timedConnectionHandler.hasConnectionsToClose()){
                    closeConnections();
                }


                selector.select();//Selects all keys that are ready for I/O operations

                Set<SelectionKey> selectionKeys = selector.selectedKeys();

                for (SelectionKey key : selectionKeys) {


                    if (key.isAcceptable()) {//This will only work with the server key
                        acceptConnection();
                    }

                    if (key.isReadable()) {
                        readData(key);
                    }

                    if (key.isWritable() && OutgoingDataQueue.hasData()) {
                        sendData(key);
                    }
                }

            }


        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }


    /**
     * Accepts a new connection
     *
     */
    private void acceptConnection(){
        try{
            SocketChannel receivedSocketChannel = serverSocket.accept();

            if(receivedSocketChannel != null){
                receivedSocketChannel.configureBlocking(false);
                receivedSocketChannel.register(selector,SelectionKey.OP_READ | SelectionKey.OP_WRITE);//Sets that this client socket will be able to send data or read data

                timedConnectionHandler.addTimedConnection((InetSocketAddress)receivedSocketChannel.getRemoteAddress());

                System.out.println("ADDRESS RECEIVED: " + receivedSocketChannel.getRemoteAddress());
            }

        }
        catch (Exception exception){

        }


    }


    private void readData(SelectionKey key){
        try{
            SocketChannel socketChannel = (SocketChannel) key.channel();

            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            socketChannel.read(byteBuffer);

            String result = new String(byteBuffer.array()).trim();

            if(!result.equals("")){//If it is not empty
                IncomingDataQueue.addToQueue(result);
                System.out.println("READ DATA: " + result);
            }

        }
        catch (IOException ioException){
            //System.out.println("Client socket has been closed by client. This error will go away after the server properly closes its client socket");
        }
        catch (Exception exception){
            exception.printStackTrace();
        }


    }

    private void sendData(SelectionKey key){
        try{
            //System.out.println("Data sent");

            SocketChannel socketChannel = (SocketChannel) key.channel();

            String destinationIP = OutgoingDataQueue.peakData().getDestinationIP().toString();


            if(socketChannel.getRemoteAddress().toString().equals(destinationIP)){//If they are thesame, that means this data is for this client

                System.out.println("GOING OUT: " + OutgoingDataQueue.peakData());

                ByteBuffer byteBuffer = ByteBuffer.wrap(OutgoingDataQueue.getData().getParsedData().getBytes());
                socketChannel.write(byteBuffer);
            }

        }
        catch (IOException ioException){

        }
        catch (Exception exception){
            exception.printStackTrace();
        }
    }





    private void closeConnections(){

        try {
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();



            for (SelectionKey key: selectionKeys) {

                if(!key.isAcceptable()){//Skip the server socket/key because we cannot cast it into SocketChannel
                    SocketChannel socketChannel = (SocketChannel)key.channel();

                    if(timedConnectionHandler.getAddressToClose() != null && ((InetSocketAddress)socketChannel.getRemoteAddress()).toString().equals(timedConnectionHandler.getAddressToClose().toString())){//if the key and the timedconnection match, close the connectionn
                        key.cancel();
                        System.out.println("Closed connection to : " + timedConnectionHandler.getAddressToClose());
                        timedConnectionHandler.closeTimedConnection(timedConnectionHandler.getAddressToClose());
                    }
                }
            }
        }
        catch (Exception exception){
            exception.printStackTrace();
        }



    }

}
