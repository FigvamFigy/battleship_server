package network.protocol_classes;

/**
 * This interface will be implemented by protocol classes and will be used in DataDecider.java in order to simplify data management
 *
 * Thread: dataProcessingThread
 */
public interface IProtocolClass {


    public void execute();
}
