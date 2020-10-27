package gameLogic;

import java.net.InetSocketAddress;


/**
 * This class is used to keep track who's grid belongs to who. The player is dependant on the ip of the client to differ from each other
 *
 * Thread: JavaFX Application
 */
public class Player {


    private Grid grid;
    private InetSocketAddress address;

    public Player(InetSocketAddress address) {
        this.address = address;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public Grid getGrid() {
        return grid;
    }

    public InetSocketAddress getAddress() {
        return address;
    }
}
