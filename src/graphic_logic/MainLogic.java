package graphic_logic;

import gameLogic.GameLogic;
import gameLogic.Grid;
import gameLogic.Player;
import graphics.MainWindow;
import javafx.stage.Stage;
import network.OutgoingDataQueue;
import network.Server;
import util.EnumDataPurposeTag;
import util.EnumScene;
import util.parser.DataWriteParser;

import java.net.InetSocketAddress;


/**
 * This class is responsible for being the middle man between the backend logic (server/client/data reading) to the front end logic (UI, user interaction, etc)
 * This class has a lot of static methods so that it can be referenced anywhere in the program
 *
 * Thread: JavaFX Application
 */
public class MainLogic {


    private static MainWindow mainWindow;
    private static GameLogic gameLogic;

    private static int playerNumber;

    private static boolean isPlayerOneReady;
    private static boolean isPlayerTwoReady;

    private static boolean isGameStarted;

    public MainLogic(Stage stage) {
        mainWindow = new MainWindow(stage);
        this.gameLogic = new GameLogic();
        playerNumber = 0;
        isPlayerOneReady = false;
        isPlayerTwoReady = false;
        isGameStarted = false;

    }


    public static void showScene(EnumScene scene){
        mainWindow.showScene(scene);
    }


    public static void createPlayer(InetSocketAddress address){
        if(playerNumber == 0){
            gameLogic.setPlayerOne(new Player(address));
            System.out.println("Player one created: " + address);
        }
        else {
            gameLogic.setPlayerTwo(new Player(address));
            System.out.println("Player two created: " + address);
        }
        playerNumber++;
    }

    public static void setPlayerReady(InetSocketAddress address){
        if(gameLogic.getPlayerOne().getAddress().toString().equals(address.toString())){//Its player 1
            isPlayerOneReady = true;
            System.out.println("PLAYER 1 READY");
        }
        else {//its player 2 that is first read
            isPlayerTwoReady = true;
            System.out.println("PLAYER 2 READY");
        }

        if(isPlayerTwoReady && isPlayerTwoReady){//Start the game after both clients are ready
            startGame();
        }
    }



    private static void startGame(){
        isGameStarted = true;
        System.out.println("GAME STARTED");
        gameLogic.startGame();

        sendGameStartMessage(gameLogic.getPlayerOne().getAddress());
        sendGameStartMessage(gameLogic.getPlayerTwo().getAddress());
    }


    private static void sendGameStartMessage(InetSocketAddress destinationIP){
        DataWriteParser dataWriteParser = new DataWriteParser(
                Server.serverSocketAddress,
                destinationIP,
                EnumDataPurposeTag.START_GAME,
                "START GAME");

        OutgoingDataQueue.addToQueue(dataWriteParser);
    }


}
