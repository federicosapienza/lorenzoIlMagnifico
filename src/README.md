# MVC brief description 

Here is briefly described our implementation of Mvc pattern , to help you navigate through the classes of our project.  


# Model
Any time there is a change in the model, the model sends to the view a specific object to the view: 
this object belongs to one of the classes of "Message" folder. 
The objects of these classes can be real messages or immutable simple describers of the underlying structure of the model.
Observer pattern is used


# View:
The server-side view is pretty simple:
-receives objects from the model and sends them to the connection (and then to the client). (Observer pattern is used)
-receives objects(an "Action" object or a string) from the connection and sends the info to the right controller
-starts and ends timers.

# Controller
Controller ' s objects are notified by the view . Controllers check if player is allowed to perform the action that was received
and call the part of the model that was designed to perform that action. 
Specifically, the controller calls one of the objects of "Handlers" package,  instantiated at the beginning of the game and always usable by controllers 
