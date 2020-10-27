package gameLogic;

import util.EnumCellType;

import java.io.Serializable;


/**
 * This class is created when a new board is created. A board can be friendly or an enemy. This grid holds the cells in its Cell[][]
 *
 * Thread: JavaFX Application
 */
public class Grid implements Serializable {

    private EnumCellType gridType;

    private int rowQuantity;
    private int colQuantity;

    private Cell[][] cellList;

    public Grid(EnumCellType gridType, int rowQuantity, int colQuantity) {
        this.gridType = gridType;
        this.rowQuantity = rowQuantity;
        this.colQuantity = colQuantity;
        cellList = new Cell[rowQuantity][colQuantity];

        fillCellList();
    }


    private void fillCellList(){
        int cellNumber = 0;

        for (int row = 0; row < rowQuantity; row++) {
            for (int col = 0; col < colQuantity; col++) {

                Cell cell = new Cell(gridType,cellNumber, row, col);
                cellList[row][col] = cell;

                cellNumber++;
            }
        }

    }

    public Cell getCell(int row, int col){
        return cellList[row][col];
    }

    public EnumCellType getGridType() {
        return gridType;
    }

    public int getRowQuantity() {
        return rowQuantity;
    }

    public int getColQuantity() {
        return colQuantity;
    }


    public String toString(){
        String str = "";

        for (int row = 0; row < rowQuantity; row++) {
            for (int col = 0; col < colQuantity; col++) {
                str += "  " + cellList[row][col].getCellID();
            }
            str += "\n";
        }
        return str;
    }
}
