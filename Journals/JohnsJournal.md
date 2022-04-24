# John's Weekly Journal
---
## Four Musketeers: Weekly Scrum Report 4/13
Team Member Name: John Lu

### 04/13/2022
Progress since the last weekly scrum: 
- I was thinking through possible modifications to the game of chess I can add. My various ideas were adding new pieces, modifying gameplay, or trying different chess variations. 

What we did:
- We confirmed the Greenfoot starter code we would start out with. Since the initial codebase was written in Slovakian, we had to translate the files. After splitting up the work for translating files, we also put big goals as well as starter/refactoring tasks onto the sprint task spreadsheet. 

Obstacles:
- Our initial obstacles were figuring out how to start with the project as well as what starter code to use.

XP Core Values: Communication 
- I scheduled the initial meeting and set up our modes of communication. During the meeting and offline, I made sure to be crystal clear on my thoughts and to get everyone's input. I also stayed on top of raising the alarm on certain issues and gathered input from everyone. 

---
## Four Musketeers: Weekly Scrum Report 4/20
Team Member Name: John Lu

### 04/20/2022
Progress since the last weekly scrum: 
- I translated the files I was assigned and refactored certain files for readability/maintainability. The big culprits were Knight, Bishop, Queen, and Rook, where there were multiple redundant for loops and hard-coded conditionals. Likewise, I modified the Greenfoot world to be larger to fit the timer and move recording features in the future. This also meant I had to redo some of the coordinates to fix the movement of several chess pieces.

What we did:
- We code reviewed my coordinate refactoring of the chessboard and discussed past sprint tasks. For future sprint tasks, we divided up the tasks in the "To Do" section of our Kanban board. Our team also discussed possible modifications to the base game of chess and decided on the broad categories of difficulty or gamemode changes. 

Obstacles:
- I was stuck on which direction to go for implementing move timer. Eric clarified to me that the Timer should be an Actor, not extending World; if it extended World, its rendering would remove the chessboard rendering. The team also helped point me in a direction on where to look to start implementing timing in Java. Likewise, I was waiting for the code review of my chessboard coordinate refactoring pull request, since it made space on the grid for the future tasks of timer and move records. 

XP Core Values: Communication 
- Throughout the past week, I was very transparent on what changes I made as well as what issues or questions I had. During the meeting, I made sure everyone was on the same page and got a chance to talk.
