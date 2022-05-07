# Team Four Musketeers

### Commit Log ###
Inital commit is the base code we will be using from Greenfoot at https://www.greenfoot.org/scenarios/25919. The code is initally written in Czech.

Eric: Respect  
John: Communication  
Richard: Feedback  
Ryan: Courage  

## 4/20/2022 ##
John fixed castling. All of the numbers were off. Which cause the need to refactor.

John suggested that all of hte other pieces should be refactored for readability. (Richard) will take this issue.

Eric, record moves. Thinking about using observer pattern. 
Eric discovered that you can only have 1 world at a time. For different levels other people had different "worlds". 

Ryan will do the chess AI near the end. 

What uniqueness can we add to the chess game?
Eric - maybe an additional game mode? AI can duplicate as an additional game mode.

Richard - we can change timer requirements. such as speed chess.

Let's target MVP first.

Features options: potential moves highlighting. 


Structure meetings?
1. Opening -- PR review
2. Issues we're facing?
3. Features and where we're going.

## 4/27/2022 ##
Review Eric's move history. All appears to be good. Only potential request was to include the color of the piece that moved. 
John demo the timer. Current move timer is set to 30 sec, a bug was observed where the person can have their turn skipped if the timer expires.
Team voted to allow the bug as it adds uniqueness to the game. 

Issue, Richard: how do you make a popup where you can select options.

This week:
John: enable timer disabling, add timer difficulty levels, and fix bugs
Eric: To fix record moves and start on showing valid moves
Richard: Pawn promotion.
Ryan: Castling through compromised squares and capturing king. And check alert. 

Obstacles:
Richard: popup dialog box.
Eric: Figure out source of bug with select

## 5/5/2022 ##
Eric: Movie history, scroll bar is not possible since the layout making percised points not possible. Must use buttons. 
ask team: is this ok? It's not as nice but it's the simpliest. 

Trying to work a pattern into it. The observer for the buttons and the labels. This would mean each label would have it's own state.
Move history should be done wihtin a day or two.

John: (Sick)
my progress: implement timer toggling and timer difficulty changing
obstacles: figuring out how to do greenfoot image, future obstacle is how to implement design patterns for timer

Richard:
got buttons for pieces in.
will continue working on it
need collab to set button locations and supper piece

Ryan:
Created a "super piece". 
Will refactor code for design patterns. 

John:
We should work on admin stuff.

Feedback for options:
Random piece alloactions. Or out of the 3 options we can present one of the options. 