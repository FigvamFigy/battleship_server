package network.protocol_classes;

import graphic_logic.MainLogic;

import java.net.InetSocketAddress;

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
