#  (Ingegneria del Software) project: Lorenzo il Magnifico

This is the repository for the java implementation of Lorenzo il Magnifico.

# How to run:
In order to start game,  you first need to start Server. Then you can start an unlimited number of clients.

Server: 
     
     Magnifico-Yun-Sapienza-Vare/src/main/java/it/polimi/ingsw/GC_26/server/main/Server.java

Client:
     Magnifico-Yun-Sapienza-Vare/src/main/java/it/polimi/ingsw/GC_26/client/main/ClientMain.java


You can interact with client via a cli interface.
Server and Clients communicates via socket communication.



# Server initialisation:
When Server is first started, it automatically reads any file from:
      Magnifico-Yun-Sapienza-Vare/src/Cards/
      etc...
      
      Then it automatically waits for client connection

# Game 
When two clients are connected , a countdown starts: after the time determined by (inserire file...) or when the maximum number of clients 
is reached (4), , the game starts.
The game follows the advanced rules of Lorenzo il Magnifico

