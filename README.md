TicTacToe-Java
==============
[![Build Status](https://travis-ci.org/Glavin001/TicTacToe-Java.png?branch=master)](https://travis-ci.org/Glavin001/TicTacToe-Java)

> Tic Tac Toe in Java. For CSCI 3465 Object Oriented Programming course at SMU.

### For Grading Assignment

See assignment at http://cs.smu.ca/~jdeveaux/csci/3465/2014/assign1.html

#### Two versions of Tic Tac Toe
- [&#x2713;] One version that runs from the command line
- [&#x2713;] One version that has a GUI

#### Implement
- [&#x2713;] Making a move
- [&#x2713;] Switching between players
- [&#x2713;] Detecting a win
- [&#x2713;] Detecting a draw
- [&#x2713;] Providing data for the interface to build the board
- [&#x2713;] All interface-related functionality must be implemented in separate classes
- [&#x2713;] The game will allow a person to play against a computer player. 
- [&#x2713;] The computer player will play by making random, legal moves. 

#### Bonus 
- [&#x2717;] Ideally, the computer will be able to learn from losing scenarios and not make a move leading it to what it knows is a losing scenario.

> The above suggested technique for building an AI is unsatisfactory and unsettlingly inefficient 
> -- requiring and storing a history of games.
> The true ideal is an algorithm that would deduce it's next move position through proven techniques, 
> with no need for prior winning or losing scenarios to be stored.
> A Java implementation can be found http://algojava.blogspot.ca/2008/12/how-to-create-tic-tac-toe-ai-java.html
> and such techniques are described in prose at http://www.wikihow.com/Win-at-Tic-Tac-Toe

-----

## Features

- Object-Oriented Design with an easily extendable `Player` class to add functionality to the game.
- Currently supported `Player`s: `GUIPlayer` (Human), `CommandLinePlayer` (Human), `RandomPlayer` (Computer), `AIPlayer` (Computer).

> **TODO**: Create `Player` that uses Sockets to communicate over the network. 
This would require, of course, a local `SocketServerPlayer` on the same machine as the `Engine` and a `SocketClientPlayer` on any machine that can connect to `SocketServerPlayer` over the network.


## Creating Your Own Player

Extend the `Player` class and implement at least the required `Player` methods, such as from the  [EngineObserver](http://glavin001.github.io/TicTacToe-Java/doc/EngineObserver.html).

## Documentation
See http://glavin001.github.io/TicTacToe-Java/doc/package-summary.html

## Known Issues
- Game will crash if `CommandLinePlayer` has `Scanner` currently waiting for input when game is restarted.
- `CommandLinePlayer` cannot restart game. This is because the `CommandLinePlayer`'s `Scanner` would then be waiting for input (`Press [Enter] to restart game`) and crash if another player restarted the game before input was received (See issue above).


## Installation / Building

This project was created using [Eclipse](http://www.eclipse.org/), 
and so I recommend using it for building this project.

Please see the [Releases](https://github.com/Glavin001/TicTacToe-Java/releases) for precompiled builds.

## Usage: Creating a Game

Edit the `App` class, inside the `main` method.

### Step 1) Initialize the `Player`s and `Engine`

```java
// === Initialize instances.
// Players
Player human1 = new GUIPlayer();
Player human2 = new CommandLinePlayer();
Player computer1 = new RandomPlayer();
Player computer2 = new AIPlayer();
// Engine
Engine engine = new Engine();
```

### Step 2) Add `Player`s to the Game's `Engine`.

```java
// Add Players to communicate to Engine and play the game.
// The order matters, and only two players can be playing in one engine at a time.
engine.addPlayer(human1);    // First Player, 'X'
engine.addPlayer(computer1); // Second Player, 'O'

// Or
//engine.addPlayer(human2);
//engine.addPlayer(computer2);
```

### Step 3: Start the Game!

```java
// Start the game!
engine.startGame();
```
