TicTacToe-Java
==============
[![Build Status](https://travis-ci.org/Glavin001/TicTacToe-Java.png?branch=master)](https://travis-ci.org/Glavin001/TicTacToe-Java)

> Tic Tac Toe in Java. For CSCI 3465 Object Oriented Programming course at SMU.

See assignment at http://cs.smu.ca/~jdeveaux/csci/3465/2014/assign1.html

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

