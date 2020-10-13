package graphics;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;
import thread.ServerThread;


/**
 * A scene for the user to setup the server
 *
 * Thread: JavaFX Application
 */
import java.net.InetAddress;

public class SceneServerSetup extends Scene {


    private GridPane masterGridPanel;


    public SceneServerSetup(GridPane gridPane, double xSize, double ySize){
        super(gridPane,xSize,ySize);

        this.masterGridPanel = gridPane;


        createUI();
    }


    /**
     * Sets up the ui and any setOnAction if needed
     */
    private void createUI(){
        try{
            Text textIP = new Text("IP: ");
            Text textPort = new Text("Port: ");

            TextField textFieldIP = new TextField();
            TextField textFieldPort = new TextField();

            Button buttonStart = new Button("Start Server");

            //IP
            GridPane.setConstraints(textIP, 0,0);
            GridPane.setConstraints(textFieldIP, 1,0);
            masterGridPanel.getRowConstraints().add(new RowConstraints(25));

            //PORT
            GridPane.setConstraints(textPort,0,1);
            GridPane.setConstraints(textFieldPort,1,1);
            masterGridPanel.getRowConstraints().add(new RowConstraints(25));

            //Button
            GridPane.setConstraints(buttonStart,0,4);
            masterGridPanel.getRowConstraints().add(new RowConstraints(25));

            buttonStart.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {

                    try{

                        ServerThread serverThread = new ServerThread(textFieldIP.getText(),Integer.parseInt(textFieldPort.getText()));
                        serverThread.start();

                    }
                    catch (Exception exception){
                        exception.printStackTrace();
                    }

                }
            });


            masterGridPanel.getChildren().addAll(textIP,textPort,textFieldIP,textFieldPort,buttonStart);


            InetAddress localHostAddress = InetAddress.getLocalHost();
            String stringLocalHostAddress = localHostAddress.getHostAddress();

            textFieldIP.setText(stringLocalHostAddress);
            textFieldPort.setText("5555");

        }
        catch (Exception exception){
            exception.printStackTrace();
        }


    }

}
