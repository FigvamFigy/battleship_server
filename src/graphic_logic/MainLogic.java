package graphic_logic;

import graphics.MainWindow;
import javafx.stage.Stage;
import util.EnumScene;


/**
 * This class is responsible for being the middle man between the backend logic (server/client/data reading) to the front end logic (UI, user interaction, etc)
 * This class has a lot of static methods so that it can be referenced anywhere in the program
 *
 * Thread: JavaFX Application
 */
public class MainLogic {


    private static MainWindow mainWindow;

    public MainLogic(Stage stage) {
        mainWindow = new MainWindow(stage);

    }


    public static void showScene(EnumScene scene){
        mainWindow.showScene(scene);
    }
}
