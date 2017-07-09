# MVC brief description 

In the following lines there is a short description of our implementation of the MVC pattern, to help you navigate through the classes of our project.  


# Model
Using the Observer pattern, we have decided to implement the game with the following logic:
whenever a change occurs in the model, the model sends a specific object to the view; 
this object belongs to one of the classes contained in the “messages” package. 
The objects of these classes can be real messages or immutable simple describers of the underlying structure of the model.

# View:
The server-side view is pretty simple:
- it receives objects from the model and sends them to the connection (and then to the client) (Observer pattern is used);
- it receives objects(an "Action" object or a string) from the connection and sends the info to the right controller;
- it starts and ends timers.

# Controller
The objects of the controllers are notified by the view . Controllers check if the player is allowed to perform the action which has been received
and call the part of the model that has been designed to perform that specific action. 
Particularly, the controller calls one of the objects of the “handlers" package, instantiated at the beginning of the game and always usable by controllers.
