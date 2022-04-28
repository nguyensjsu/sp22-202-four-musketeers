import greenfoot.*;

import java.util.*;

public class MoveHistory extends Actor implements IChessMoveObserver {
    private Chessboard myWorld; // reference of world MoveHistory is in
    private final Label header;
    private final ArrayList<Label> history;
    private static final int maxHistorySize = 8; //max number of entries displayed
    private static final int width = 300;
    private static final int height = 900;
    private int turnDisplayed = 1; //only iterates on white turns

    private int labelY = 1;
    private final int labelX = 9;

    GreenfootImage boardImage; // large rectangle background

    /**
     * Constructor for objects of class MoveHistory
     */
    public MoveHistory() {
        header = new Label("movehistory.png");
        history = new ArrayList<>();
    }

    public void addedToWorld(World w) { //triggers when object added to a world
        myWorld = (Chessboard) w;
        boardImage = new GreenfootImage(width, height);
        boardImage.setColor(Color.LIGHT_GRAY);
        boardImage.fillRect(0, 0, width, height);
        setImage(boardImage);

        myWorld.addObject(header, labelX, 0);
    }

    @Override // updates when a chesspiece is moved
    public void update(int turn, String movement) {
        if (turn % 2 != 0) { //if turn is white
            if (history.size() != maxHistorySize) { //if max number of fields is not reached yet
                Label newMove = new Label(turnDisplayed, 42);
                newMove.updateValues(movement, true);
                history.add(newMove);
                myWorld.addObject(newMove, labelX, labelY);
                labelY = labelY != maxHistorySize ? labelY + 1 : labelY;
                turnDisplayed++;

            } else { //max number of history labels so remove first entry, shift up, rerun update
                myWorld.removeObject(history.get(0));
                history.remove(0);
                shiftHistory();
                update(turn, movement);
            }
        } else { //if turn is black
            //if label already exists then white already went and store black move in same label
            history.get(history.size() - 1).updateValues(movement, false);
        }
    }

    private void shiftHistory() { //shifts all entries up by one space
        for (Label l : history) {
            l.shiftUpwards();
        }
    }
}
