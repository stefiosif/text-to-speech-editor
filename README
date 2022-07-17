# Text-to-Speech (TTS) editor
A simple TTS app built with Java Swing and freetts library. The main purpose of this project is to experiment
with some common design patterns.

## Architecture pattern (Model-View-Controller)
#### Model
The business logic of the project, where the all the underlying calculations are made.
#### View
The graphical interface that the user interacts with.
#### Controller
The connecting tissue of the Model and the View.

## Design Patterns
The Parameterized Factory pattern is used for the different objects that can be created on all parts of our project.
For example different types of commands, different types of encoding strategies and lastly different types of tts
adapters.

In the Model package, the Strategy pattern is used to bring extensibility to our code for the different tuning
strategies, combined with the Template Method where similar parts of the encoding can be made so that there is no
duplicate code. The Adapter pattern also brings extensibility to our code for the different TTS adapters implementing
the same interface.

The Command pattern is used for all the different buttons that exist on the Controller package. Finally, the Singleton
pattern is used for the View package.