package thread;

import network.protocol_classes.DataDecider;
import util.Constants;


/**
 * This is the thread class that will start the dataProcessingThread thread
 *
 * Thread: serverThread
 */
public class DataProcessingThread implements Runnable{

    private Thread thread;


    public DataProcessingThread() {


    }


    @Override
    public void run() {
        DataDecider dataDecider = new DataDecider();
        dataDecider.start();


    }



    public void start(){
        if(thread == null){
            thread = new Thread(this, Constants.DATA_PROCESSING_THREAD);
            thread.start();
        }

    }
}
