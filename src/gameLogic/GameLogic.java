package gameLogic;


/**
 * This is where the game specific game logic is handled. Things like, "is the players click valid" as well as general manipulation of the grids from the players
 *
 * Thread: JavaFX Application
 */
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

    /**
     * After the server determines that both players are ready to play, this method will be called and the game logic will start here.
     *
     */
    public void startGame(){
        playerTurn = playerOne;
    }

}
