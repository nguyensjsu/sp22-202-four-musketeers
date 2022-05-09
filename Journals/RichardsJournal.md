# Weekly Scrum Journal for Richard #
### Core Value Selected ###
Feedback

## 4/13/2022 ##
Chose to use starter code 
1st sprint will be a clean up sprint will add features along the way.

Choosing https://www.greenfoot.org/scenarios/25919
Lock flipfloping 
Translate code to english
Record moves
Save game?
Fix check/mate/tie indicator.
Tie is define by 50 moves where no pieces are captured or no no pawns are moved.

## 4/20/2022 ##

### Task worked on ###
Tasked completed last week was the code localization refactoring for Chess pieces.
Update the burndown chart such that it displays the correct graph

### Task Planned for next week ###
1. (Spike) figure out the how the chess board code works in depth to target issue [#5 chess board flip lock](https://github.com/nguyensjsu/sp22-202-four-musketeers/issues/5)
2. Resolve the Pawn promotion bug that was discovered after testing the localization patch [#14 pawn promotion broken](https://github.com/nguyensjsu/sp22-202-four-musketeers/issues/14)
3. Continue to see if I can improve the burndown chart.

### Blocking items waiting on another team member ###
None

### Scrum notes ###
Since I'm doing feedback, I suggested to the team and they agreed to structure the meetings a bit more than freeflow.
I think our initial structure should be.
1. Any open discussion we want to talk about before meeting?
2. PRs to review
3. Items we're thinking about working on this week and items we have completed.
4. Any blockers?



##  4/27/2022
### Task worked on ###
Working on the Pawn promption problem but after further invesitagation it appears that some of the pawns core movements are broken and the code is too "messy" to properly function. Refactored the pawn class such that there's less redundency for hte move options. Also fixed the "jumping" issue where a pawn can jump over any piece in the first move. 

### Task Planned for next week ###
Continue the spike to figure out how to do a popup window for pawn promption. if fail go with pre-established buttons.

### Blocking items waiting on another team member ###
Help, I still can't fiugre out how to do a popup menu. (toast style)

### Scrum notes ###
Feedback side, there was a bug John discovered where his chess clock would allow a person's turn to be skilled. However since we all liked it we decided to re-classify as a feature.

## 5/5/2022 ##
### Task worked on ###
Figured out how to add the promotion buttons to the UI and started basic integration to the Chess board. Converted to a primative observer pattern and singleton to facilitate operations.
### Task Planned for next week ###
Refactor and further expand on the promotion buttons task needed.
[] need to change colors as needed
[] pawn class need to add a forwarding method to call 
### Blocking items waiting on another team member ###
[] not cirtical now but need to talk with John on how to integrate the timer.
[] also neogiate the button real estate.
### Scrum notes ###
Help organized the scrum meeting into the 3 orginal questions. We potentially have too much conversation. For a growing team this is good for a short build cycle not as much.
1. what did you do
2. what will you do
3. what are your blockers

## 5/5/2022 working notes
Pawn has to be a subject class that holds pointer to PromotionButton master class. This class will hold all of the promotional buttons.
(needs array list)

Buttons extended from the promotional class will need to have a create and delete option. Constructors should have an add to the promotion class to add the item to an instance variable.


# Template #
### Task worked on ###
### Task Planned for next week ###
### Blocking items waiting on another team member ###
### Scrum notes ###