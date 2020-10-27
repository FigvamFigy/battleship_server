package network.protocol_classes;

import gameLogic.Grid;
import gameLogic.Player;
import graphic_logic.MainLogic;
import javafx.application.Platform;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.Base64;

/**
 * This class is deprecated
 *
 */
public class ClientToServerSendGridProtocolClass implements IProtocolClass{


    private String data;

    public ClientToServerSendGridProtocolClass(String data) {
        this.data = data;
    }


    @Override
    public void execute() {


    }


}
