package network.protocol_classes;

import graphic_logic.MainLogic;

import java.net.InetSocketAddress;

/**
 * After the client states that it is ready, it will call a method in MainLogic to signify that a player is ready. It sends the ip to distinguish which player is ready
 *
 * Thread: dataProcessingThread
 */
public class PlayerIsReadyProtocolClass implements IProtocolClass{


    private InetSocketAddress senderIP;

    public PlayerIsReadyProtocolClass(InetSocketAddress senderIP) {
        this.senderIP = senderIP;
    }


    @Override
    public void execute() {
        MainLogic.setPlayerReady(senderIP);
    }
}
