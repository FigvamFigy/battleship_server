# Battleship Server


This is the server portion of the battleship project we are doing. 

# High Level Understanding of how the Program Works:

The clients are dependent on the server for everything. From receiving updated
 game grid from knowing if they can start the game. Essentially, whatever the
 client does has to get permission from the server.
 
 
# Threads
In the server, there are three important threads that are used to make everything
run. The threads are: *JavaFX Application*, *serverThread*, and *dataProcessingThread*

In order for these three threads to properly communicate there are a few classes
with the sole purpose to handle communications between these threads. The most 
important class is the ``MainLogic`` class. This class handles all communication
between the *JavaFX Application* thread and the rest of the program.


**JavaFX Application**

Everything that sits on this thread deals with gui logic, or game logic. 

**serverThread**

This thread is responsible for all of the server side networking communications. In 
this code, it has everything from having timed connections with the clients to
having the server be running. This thread is created from the *JavaFx Application*
thread after the user presses the "start server" button.

**dataProcessingThread**

Whenever the server reads incoming information, this thread will start to process
it and act accordingly to the purpose of the messages. This thread is created
within the *serverThread*.

The server part of the battleship thing being for fun

# Systems
There are systems designed that each serve an important purpose for the server
to run.

**The Timed Connection System**

There is no way to know when a client has disconnected. The connection keys in 
`Server` do not get deleted automatically. Thus, we must create a system to do
this for us.

The system consists of two important classes: `TimedConnectionHandler` and
`TimedConnection`. Whenever a new connection is made with a client, the server
calls `addTimedConnection(InetSocketAddress)` in its `acceptConnection` method.
Inside `addTimedConnection(InetSocketAddress)`, it creates a new instance of
`TimedConenction`, adds it to the `arrayListTimedConnection` array list, 
and calls `TimedConnection#startTimedConnection`. 

Inside `TimedConnection` sits a `java.util.Timer`. Inside that `TimerTask` is a call
to `TimedConnectionHandler#addConnectionToClose`. This method should only be called
if the `Timer`'s timer of 9 seconds elapses without being told to stop its thread.
If this method is called, the `TimedConnectionHandler` will add the connection's
`InetSocketAddress` to the `arrayListAddressesToClose` array list. Inside the main
loop of the server, there is a method call to `TimedConnectionHandler#hasConnectionsToClose`.
If there are connections to close, the server will remove all the connections inside
of `arrayListAddressesToClose` from itself.

In order to prevent the `TimedConnectionHandler#addConnectionToClose` method
from being called in the `TimerTask`, the client would have to send a connection
message to the server within 9 seconds. If this message is received from the client,
`ClientToServerConnectionCheckProtocolClass` will call the static method
`TimedConnectionHandler#continueTimedConnection`. Inside this method, it will find
the `TimedConnection` from the array and call `resetTimedConnection` which will
stop and kill the previous `Timer`'s thread and then call `startTimedConnection` to
start the process once more.

**Reading/Sending Data**

There are two classes that are important here `OutgoingData` and `IncomingData`.
When the server reads data that is coming in through the network, it will add
the data (which will be a `String`) to `IncomingData` by calling `addToQueue`.
The `DataDecider` then starts processing the data from this class. After it retrieves
the data, it will delete the data from `IncomingData`. A similar concept is used
for `OutgoingData`. Whenever we want to send data to a client, we will prepare the data
and add it to `OutgoingData`. Inside the server loop there is a check if there is
data to be sent. If there is data, it will be sent through the network and the data
will be removed from `OutgoingData`

**Data Processing**

The class `DataDecider` runs in a infinite loop and check if there is data to be read
in `IncomingData`. If there is data to be read, it will use the `DataReadParser` to 
read the data and use the enum class `EnumDataPurposeTag` to call the correct
action that needs to be taken from the data. 

These "actions" are called *Protocol Classes*. Every `EnumDataPurposeTag` has its own
protocol class. All of them have implement the method `execute` which is called
from the switch case statement in `DataDecider`. Depeing on the protocol tag, different
actions are taken.

**Communication Between the Server the Graphics/Game Logic**

As stated before, the class `MainLogic` is used as a communication system from the
network/data processing threads to the gui. This simplifies communication between
threads to one singular class. It makes it easier to debug and keep things organized
