import greenfoot.*;

import java.util.*;

public class MoveHistory extends Actor implements IChessMoveObserver,IScrollButtonObserver {
    private Chessboard myWorld; // reference of world MoveHistory is in
    private final ArrayList<Label> history;
    private static final int historyFontSize = 36;
    private static final int maxHistorySize = 8; //max number of entries displayed 
    private static final int width = 300;
    private static final int height = 900;
    private int turnDisplayed = 1; //only iterates on white turns
    
    private int deviationsFromStart;

    private int labelY = 0;
    private final int labelX = 9;

    /**
     * Constructor for objects of class MoveHistory
     */
    public MoveHistory() {
        history = new ArrayList<>();
        deviationsFromStart = 0;
    }

    public void addedToWorld(World w) { //triggers when object added to a world
        setImage("MoveHistoryBack.png");
        myWorld = (Chessboard) w;
    }

    @Override // updates when a chesspiece is moved
    public void update(int turn, String movement) {
        resetToRecentHistory();
        if (turn % 2 != 0) { //if turn is white
            
            if (history.size() >= maxHistorySize) 
                shiftHistoryDown();
                
            Label newMove = new Label(turnDisplayed, historyFontSize);
            newMove.updateValues(movement, true);
            history.add(newMove);
            myWorld.addObject(newMove, labelX, labelY);
            labelY = labelY != (maxHistorySize - 1) ? labelY + 1 : labelY;
            turnDisplayed++;
        } else { //if turn is black
            //if label already exists then white already went and store black move in same label
            history.get(history.size() - 1).updateValues(movement, false);
        }
    }

    private void shiftHistoryUp() { //shifts all entries up by one space
        if (history.size() >= maxHistorySize) {
            for (Label l : history) {
                    l.shiftUpwards();
            }
        }
    }
    
    private void shiftHistoryDown() {//shifts all entries down by one space
        if (history.size() >= maxHistorySize) {
            for (Label l : history) {
                    l.shiftDownwards();
            }
        }
    }
    
    public void buttonEvent(String direction) { //observer event from up or down button press
            if(direction.equals("up")) {
                if(!myWorld.getObjectsAt(labelX,0,Label.class).isEmpty() && myWorld.getObjectsAt(labelX,0,Label.class).get(0) != history.get(0)) {
                    shiftHistoryUp();
                    deviationsFromStart++;
                }
            } else {
                if(!myWorld.getObjectsAt(labelX,7,Label.class).isEmpty() && myWorld.getObjectsAt(labelX,7,Label.class).get(0) != history.get(history.size()-1)) {
                    shiftHistoryDown();
                    deviationsFromStart--;
                }
            }
    }
    
    //resets view in case someone is scrolled up when their turn ends
    public void resetToRecentHistory() {
        while(deviationsFromStart != 0) {
            if(deviationsFromStart > 0) {
                shiftHistoryDown();
                deviationsFromStart--;
            } else {
                shiftHistoryUp();
                deviationsFromStart++;
            }
        }
    }
}
