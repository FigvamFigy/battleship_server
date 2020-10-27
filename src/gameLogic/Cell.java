package gameLogic;

import util.EnumCellState;
import util.EnumCellType;

import java.io.Serializable;

/**
 * This cell is responsible for the logical side of the game logic of holding information about the cell. A cell can be in different types of states, and depending
 * on these states it will act different in the GameLogic.java.
 *
 * Thread: JavaFX Application
 */
public class Cell implements Serializable {

    private EnumCellState state;

    //The type of cell is specified on its creation. FRIENDLY and ENEMY is used in the client. However, the server Cell.java must stay the same as the client Cell.java
    //in order for serialization to work. When the clients send their grids at the start, both grids will be FRIENDLY. So somewhere else there must be a designation between the two clients
    private EnumCellType type;

    private int cellID;
    private int rowNumber;
    private int colNumber;

    public Cell(EnumCellType type, int cellID, int rowNumber, int colNumber) {
        this.cellID = cellID;
        this.rowNumber = rowNumber;
        this.colNumber = colNumber;
        this.type = type;
        this.state = EnumCellState.EMPTY;
    }

    public void setState(EnumCellState state) {
        this.state = state;
    }

    public EnumCellState getState() {
        return state;
    }

    public EnumCellType getType() {
        return type;
    }

    public int getCellID() {
        return cellID;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public int getColNumber() {
        return colNumber;
    }

    public String toString(){
        String str = "";
        str += "\nCell Coord: " + rowNumber + " " + colNumber;
        str += "\nCell ID: " + cellID;
        str += "\nCell State: " + state;
        str += "\nCell Type: " + type;

        return str;
    }
}
