package network.protocol_classes;

/**
 * This interface will be implemented by protocol classes and will be used in DataDecider.java in order to simplify data management
 *
 * Thread: dataProcessingThread
 */
public interface IProtocolClass {

    //This method is always called in the DataDecider.java. Each protocol class has its own implementation of this method depending on the needs
    public void execute();
}
