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