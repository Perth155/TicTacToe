# TicTacToe
![tictactoe](https://i.imgur.com/yUZLLkE.png)
## Description:
A simple 2-player command line based (GUI currently added using Java's swing library) Object Oriented tic-tac-toe game written in Java. An unbeatable AI has been implemented using [minimax algorithm](https://en.wikipedia.org/wiki/Minimax).
Currently supports game between two human players, or versing the computer (unbeatable AI).

## Requirements:
* [Java (Version 1.8)](http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html)
* [Apache Ant](http://ant.apache.org/)

To install dependencies
```
$ sudo apt-get install openjdk-8-jdk openjdk-8-jre ant  # Debian and derivatives (e.g. Ubuntu)
$ sudo pacman -S jdk8-openjdk jre8-openjdk apache-ant # Arch Linux
```

## Compile and Run
To automate the building process (with ant) a build script, ```build.xml``` has been included. To compile all java files and to create a JAR file:
```
$ ant compile
$ ant make_gui_jar
```
To run the game [GUI]
```
$ java -jar GTic.jar
```
To run a CLI version of the game, from the ```bin``` directory, simply run:
```
$ java main.TicTacToe
```

## Known bugs:
* Game crashes with any non-integer console input by user.

## TODO:
- [X] Implement an unbeatable AI using minimax algorithm, and allow human player to verse the computer.
- [X] Implement a GUI using swing (optional).
- [ ] Implement Alpha-Beta pruning to reduce number of nodes to be evaluated in the search tree by the minimax algorithm.
