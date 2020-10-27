package gameLogic;

import java.net.InetSocketAddress;

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
