# battleship_server
The server part of the battleship thing being for fun


Organization of the code:
There are three main threads/areas of the code inside this project.

The gui
The server
The data processing 

The gui is run by JavaFX and it runs on the "JavaFX Application" thread. All gui related tasks are put in this thread. This thread will communicate with the 
"serverThread" when needded.

The server is run on a seperate thread and it is created inside the "JavaFX Application" thread. The thread's name is "serverThread". The "serverThread" mostly
does three important things. Inside an infinite while loop, it looks to see if a new connection is pending, if a client is sending data, or if data is waiting
to be sent to a client. 

The data processing thread, named "dataProcessingThread" is created inside the "serverThread". It is responsible to work the raw data that this sent from the
serverThread into something that be interpreated and used by the program. Depending on the type of data being sent, different types of actions will be performed.
This thread will communicate withthe serverThread when needed. 
