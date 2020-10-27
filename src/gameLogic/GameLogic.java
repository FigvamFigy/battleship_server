package gameLogic;

public class GameLogic {


    private Player playerOne;
    private Player playerTwo;

    private Player playerTurn;

    public GameLogic(){

    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }

    public void setPlayerOne(Player playerOne) {
        this.playerOne = playerOne;
    }

    public void setPlayerTwo(Player playerTwo) {
        this.playerTwo = playerTwo;
    }

    public void startGame(){
        playerTurn = playerOne;
    }

}
