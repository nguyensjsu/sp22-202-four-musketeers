# Eric's Weekly Journal
---
## Four Musketeers: Weekly Scrum Report 4/14
Team Member Name: Eric Arreola

What I did since last weekly scrum:
-  Our group started heading towards an idea of chess within GreenFoot so I learned some of the principles of chess since that is something I am not too familiar with. I also looked for some examples on GreenFoot that we could potentially use as our base code since the one we had originally found was not very good. I also then looked into potential expansions or addons we could make to the classic game of chess that would make our version of the game unique.

What I plan to do this week:
-  Our starter code needs to be translated from Slovak so I am translating my designated section of the source code into English. I am also still looking into additions we could make to the base game of chess that would be feasible with the time we have for the project.

What blockers I have:
-  It is difficult to estimate the time it would take for additions to the code since it is in another language so I need to wait until it is fully translated to try and figure out potential timelines along with appropriate ideas for additions in terms of time and effort.

XP Core Value **Respect** : 
- Our group applied the core value of Respect as we took each person's opinion during the process of deciding what to do in terms of project ideas. Everyone gave input and once we decided the general direction we also weighed each others available time to decide what how large we wanted the project to potentially be. No single person's opinion or input was regarded higher than the others. We also evenly divided the intial translating so everyone would feel like a valued team member.   

---
## Four Musketeers: Weekly Scrum Report 4/20
Team Member Name: Eric Arreola

What I did since last weekly scrum:
-  I refactored the structure of the current class setup for the original chess source code. It used to be that chesspieces were seperated into different classes according to both their color and piece type, but now after the changes I did it is only by chess piece type. We decided as a group that this would be a good thing to do since it would help with the addition of features in the future. I also fixed a couple of bugs as I was restructuring the source code. 

What I plan to do this week:
-  I plan to put some hours into figuring how to add the move recording functionality into out game so that when a player commits to a move then the move is recorded and the history of past moves is displayed on the side. I also am going to try to check if each piece is behaving exactly how its supposed to in terms of the rules of classical chess since apparently some of the pieces are missing functionalities at the moment.

What blockers I have:
-  I am unsure how exactly to insert an object that represents move history into the greenfoot screen. I think this can mostly be fixed by going through greenfoot documentation since it is something I have not used in the past. I also need to decide what exact patterns I would like to use for the feature as at the moment I only really have observer in combination with singleton in mind.

XP Core Value **Respect** : 
- Our group was able to uphold the value of respect through our process of reviewing merge requests. Whenever someone is trying to commit changes to the main branch, we have to wait until everyone gives their opinion or input on the changes and then only after that we can fully merge any changes that have been done. This is done so that everyone feels like their input is valued throughout the project. We are also trying to make sure that every person is assigned something during our meetings so that contribution feel equally distributed.

---
## Four Musketeers: Weekly Scrum Report 4/27
Team Member Name: Eric Arreola

What I did since last weekly scrum:
-  In the past week since the last scrum meeting I worked on the implementation of the move history feature which would track what moves players recently made. I used the Observer pattern for this feature which just has the MoveHistory object observe the Chessboard and it is updated on whenever a chesspiece moves from its original position. The location it moved to along with the type of piece that moved is converted to a string and sent to the observer which in this case just displays it on the side of the screen. The move history moves up infinitely as new moves are made. Each history entry within the move history object displays the current turn and the movements of both the black and white players during that turn. I also did some further research into ches s itself since I had never really played it before and did not understand the rules well. I played it a few times and now feel comfortable being able to tell what rules a chess game should follow. 
What I plan to do this week:
-  During the meeting I was demonstrating the move history functionalities and during that Ryan noticed a bug with how the pieces were being displayed which wasn't there before and I also noticed that the move history being displayed was not displaying the exact coordinates that pieces were moving to so I have to figure out both roots of those bugs. Once I figure out those and patch them I need to merge my branch with the main branch so that others can continue their work. A suggestion for move history was also to maybe try adding a scroll bar so I will look into that and see what the implementation would look like in terms of time and effort. If it is feasible and worth the time then I can try adding it.
What blockers I have:
-  I am not really sure how to handle implementing a scroll bar so I have to do some research. I'm also not sure how Greenfoot handles storing data for past moves. I need to figure out the source of the bugs that were caught in the meeting since I have a vague idea of where they could be but nothing concrete yet. 

XP Core Value **Respect** : 
- As we were presenting our feature implementations to each other we tried to get each other's opinion on anything that could be changed to make the project better. In my case, I tried to get each of the other members of the group if there was any visual or code structure change thet thought I could make even if it was just an optional suggestion since everyone's feedback should be valid. Also to make sure we are respecting each others time and effort put into the project we addressed the responsibilities each person has for the next week. We also don't approve merge requests until we get everyone's approval since we want to maintain that everyone's opinion on aspects of the project are equally important. 
