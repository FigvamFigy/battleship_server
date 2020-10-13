import graphic_logic.MainLogic;
import javafx.application.Application;
import javafx.stage.Stage;
import util.EnumScene;

public class Main extends Application {


    public static void main(String[] args){

        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        try
        {
            MainLogic mainLogic = new MainLogic(stage);
            MainLogic.showScene(EnumScene.sceneServerSetup);
        }
        catch (Exception exception){
            exception.printStackTrace();
        }

    }

    /**
     * This method will kill any threads when closing the application
     */
    @Override
    public void stop(){
        try{
            super.stop();
            System.exit(0);
        }
        catch (Exception exception){
            exception.printStackTrace();
        }
    }
}
