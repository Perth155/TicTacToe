# TicTacToe
![tictactoe](https://i.imgur.com/QjAmQxg.png)

## Description:
A simple 2-player command line based (GUI currently added using Java's swing library) Object Oriented N*N tic-tac-toe game written in Java. An Agent has been implemented using [minimax algorithm with Alpha-Beta pruning](https://en.wikipedia.org/wiki/Minimax).
Currently supports game between two human players, or versing the computer (Random Agent, or Minimax Agent).

## Requirements:
* [Java (JDK 1.8)](http://openjdk.java.net/install/)
* [Apache Ant](http://ant.apache.org/)

### To install dependencies:
Debian/ Ubuntu/ Linux Mint :
```
$ sudo apt-get install openjdk-8-jdk apache-ant
```
Arch/ Manjaro Linux :
```
$ sudo pacman -S jdk8-openjdk apache-ant
```
Windows
```
> choco install java-8 ant  # Administrator aliviated prompt, requires chocolaty.
```

## Compile and Run
To automate the building process (with ant) a build script, ```build.xml``` has been included. To compile all java files and to create a runnable JAR file containing all the binaries and resources:
```
$ ant package
```
To run the game with a GUI:
```
$ java -jar target/GTic.jar
```
To run a CLI version of the game:
```
$ java -jar target/TicTacToe-1.1.0.jar
```

## Known bugs:
See [Issues](https://github.com/Perth155/TicTacToe)

## TODO:
- [X] Implement an unbeatable AI using minimax algorithm, and allow human player to verse the computer.
- [X] Implement a GUI using swing package (optional).
- [X] Implement Alpha-Beta pruning to reduce number of nodes to be evaluated in the search tree by the minimax algorithm.
