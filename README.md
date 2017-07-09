#  Ingegneria del Software project: Lorenzo il Magnifico

This is the repository which contains a Java-based online multiplayer game of the board game “Lorenzo il Magnifico”, made by Cranio Creations.
The game has been created following the MVC pattern.

# How to run:
In order to start the game, the first thing to do is running the Server class, contained in the following path:

	Magnifico-Yun-Sapienza-Vare/src/main/java/it/polimi/ingsw/GC_26/server/main/Server.java

Once the Server is started, you can run the amount of clients you want, from running the ClientMain class contained in the following path:

	Magnifico-Yun-Sapienza-Vare/src/main/java/it/polimi/ingsw/GC_26/client/main/ClientMain.java


You can interact with client via CLI (Command Line Interface).
Server and Clients communicate via Socket communication.


# Server initialisation:
When Server is started, it automatically reads any file from:

     Magnifico-Yun-Sapienza-Vare/doc/Cards/      
     Magnifico-Yun-Sapienza-Vare/doc/ResourcesForBoard/
     Magnifico-Yun-Sapienza-Vare/doc/StartingResources/
     Magnifico-Yun-Sapienza-Vare/doc/Timers/

Then, it starts to wait for clients who want to connect with the Server.

# Game 
When two clients are connected, a countdown is started. The game starts if one of the two following events occur:
1) the countdown ends and so the game starts with the current connected clients;
2) the maximum number of clients (4) connected to the current game is reached and so the countdown is interrupted and ignored and the game starts automatically with 4 players.

The game follows the advanced rules of Lorenzo il Magnifico.

Every player has a limited time to perform an action in his turn. If the player doesn’t perform any action within this limited time, he is suspended. He can ask to be reconnected to the game at any time, following the instructions given by the client’s interface.

When the game ends, it establishes who the winner is, and then clients can choose whether to close the program or start a new game.

In order to change any timer value, go to:

     Magnifico-Yun-Sapienza-Vare/doc/Timers/timer.json

# Cli

To interact with the game, just follow the instructions sent by the interface.

# Docs

Inside the “diagrams” folder there are some UML diagrams. 2 are dedicated to the Model: the file “orderedModelUML.png” (if you prefer, you can see also the equivalent file, which is “modelUML.png”, that has a nicer management of the links between classes, but similar classes have very long links and are very outdistanced from each other) contains a reordered UML diagram for the Model, with the Effects displayed as a package, because it contains too many classes, and representing them with the other classes in an only UML diagram would have compromised its readability and clarity. So we have decided to represent with an expanded view the Effects package with another dedicated UML diagram, which is contained in the “effectsUML.png” file.

Then there’s a UML diagram dedicated to the View, which is contained in the “viewUML.png”, and a UML diagram dedicated to the Controllers, which is contained in the ”controllerUML.png” file. 

There is also an initial UML diagram, named "InitialUMLDraft.png" which contains the initial planning around the model in the very first moments of development. 

Finally, please take a look to the “README.md” file contained in the "src” folder, that briefly describes the MVC pattern that we’ve followed.

