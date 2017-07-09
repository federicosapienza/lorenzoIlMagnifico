#  Ingegneria del Software project: Lorenzo il Magnifico

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

     Magnifico-Yun-Sapienza-Vare/doc/Cards/      
     Magnifico-Yun-Sapienza-Vare/doc/ResourcesForBoard/
     Magnifico-Yun-Sapienza-Vare/doc/StartingResources/
     Magnifico-Yun-Sapienza-Vare/doc/Timers/

Then,it starts waiting for clients.


# Game 
When two clients are connected, a countdown starts: after the time determined by a timer or when the maximum number of clients 
is reached (4),  the game starts.
The game follows the advanced rules of Lorenzo il Magnifico.
Any player has a limited time to complete his turn. After that, the turn is automatically ended, and if the player has not perfrmed any action, he is suspended. Anyway he can be reconnected to the game any time , following the istructions given by client interface.
When the game ends, winner is chosen, and then clients can choose whether to close the program or start a new game.

# Cli

To interact with the game, you only has to follow the istructions the interface will send.

# Docs

In folder doc you can find some UML's maps.  We have done two high levels UMl's maps , one for model , one for model-view-controller pattern. We have also added a Uml focused about the package of cards effect, because of the fact there were about 20 classes,and showing all of them in model's Uml would have compromised readability.
There is also a Uml 's map , showing the model as we thought it in the firsts moments of development. 

Finally, please take a look to the README.md in folder "src" , that briefly describes the mvc pattern.


