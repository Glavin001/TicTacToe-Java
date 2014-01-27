TicTacToe-Java
==============
[![Build Status](https://travis-ci.org/Glavin001/TicTacToe-Java.png?branch=master)](https://travis-ci.org/Glavin001/TicTacToe-Java)

> Tic Tac Toe in Java. For CSCI 3465 Object Oriented Programming course at SMU.

See assignment at http://cs.smu.ca/~jdeveaux/csci/3465/2014/assign1.html

## Documentation
See http://glavin001.github.io/TicTacToe-Java/doc/package-summary.html

## Known Issues
- Game will crash if `CommandLinePlayer` has `Scanner` currently waiting for input when game is restarted.
- `CommandLinePlayer` cannot restart game. This is because the `CommandLinePlayer`'s `Scanner` would then be waiting for input (`Press [Enter] to restart game`) and crash if another player restarted the game before input was received (See issue above).

