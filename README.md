# Team Four Musketeers

## Topic of Project
Chess

## Team Members
- Eric Arreola
- John Lu
- Richard Ngo
- Ryan Tran

## Summary of Contributions
- Eric Arreola
- John Lu
- Richard Ngo
- Ryan Tran
  - Translated some source code to English
  - Refactored main game loop and pieces
  - Fixed castling issues
  - Added check, checkmate, and stalemate
  - Added displaying possible moves
  - Added super piece (queen and knight moveset)
  - Added sound effects

## Summary of Project and Key Features

We implemented the base game of Chess with added modifications to it. Each player has a move timer that skips their turn. Players are given the option to toggle the timer or change the timer based on difficulty at the start of the game. We also fixed and implemented En Passant, Castling, and Promotion. To add further spice to the promotion feature, we added a super piece that has the moveset of a queen AND a knight (exciting, isn't it? now you can flex on your friends with style). To wrap this all up, we added a move history log that can be scrolled up or down one level at a time with buttons. 

The starter code we used was in Slovakian and had various bugs, so we took one week to refactor it. This starter code in particular looked appealing because of the board swap functionality at the end of each turn. This was a wonderful learning experience for us to learn how to refactor a project into a clean object oriented structure.

## High Level Architecture Diagram
![Architecture](architecture.png)

## Who contributed what feature?
- Eric Arreola
- John Lu
- Richard Ngo
- Ryan Tran: The main component that I own is the piece movement, which I used the factory method design pattern to implement. The factory method design pattern defines an interface for creating an object but lets subclasses decide which class to instantiate. I chose this design pattern because I wanted to delegate the responsibility of object instantiation to the subclasses. The interface IMoveSet acts as the Product participant that defines the interface of objects the factory method creates; it defines the getPossibleMoves method. The abstract MoveSet class implements IMoveSet and defines some shared methods between the concrete MoveSet classes. The concrete MoveSet classes (KingMoveSet, QueenMoveSet, etc.) act as the ConcreteProduct participants that override the getPossibleMoves method. The abstract class ChessPiece acts as the Creator participant that defines the abstract getMoveSet factory method. The concrete ChessPiece classes (King, Queen, etc.) act as the ConcreteCreator participants that override the factory method getMoveSet to return an instance of a ConcreteProduct (KingMoveSet, QueenMoveSet, etc.). The ChessPiece class then calls the getMoveSet method to instantiate the appropriate concrete MoveSet and then calls the getPossibleMoves method on that newly instantiated concrete MoveSet.

## Link to User Story Ad Video
[https://www.youtube.com/watch?v=zv_YKkZTb7g](https://www.youtube.com/watch?v=zv_YKkZTb7g)

## Link to Demo Video


## Link to Scrum Task Sheet
[https://docs.google.com/spreadsheets/d/1vNNHV441aIaOG6byEEKgsRQPKemb-DR_-ST4-R4IHVQ/edit?usp=sharing](https://docs.google.com/spreadsheets/d/1vNNHV441aIaOG6byEEKgsRQPKemb-DR_-ST4-R4IHVQ/edit?usp=sharing)


## UI Wireframes
