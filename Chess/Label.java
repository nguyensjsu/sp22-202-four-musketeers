import greenfoot.*;

// Leaf class
public class Label extends Actor {
    private ILabelState onScreen;
    private ILabelState offScreen;
    
    private ILabelState currentState;
    
    
    private int fontSize;
    private Color lineColor = Color.BLACK;
    private Color fillColor = Color.BLACK;

    private final int turn;
    private String move1;
    private String move2;

    private static final Color transparent = new Color(0, 0, 0, 0);
    
    private int yPosition;
    
    public enum LabelStates {
        OFF,ON
    }

    /**
     * Create a new label, initialise it with the needed text and the font size
     */
    public Label(int turn, int fontSize) {
        this.fontSize = fontSize;
        this.turn = turn;
        this.move1 = "";
        this.move2 = "";
        updateImage();
        
    }
    
    public void addedToWorld(World w) { //triggers when object added to a world
        yPosition = getY();
        onScreen = new LabelStateOnScreen(this);
        offScreen = new LabelStateOffScreen(this);
        
        currentState = onScreen;
    }

    /**
     * Sets the line color of the text
     *
     * @param lineColor the line color of the text
     */
    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
        updateImage();
    }

    /**
     * Sets the fill color of the text
     *
     * @param fillColor the fill color of the text
     */
    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
        updateImage();
    }

    /**
     * Update the image on screen to show the current value.
     */
    public void updateImage() {
        String value = turn + "." + "" + move1 + " " + move2;
        setImage(new GreenfootImage(value, fontSize, fillColor, transparent, lineColor));
    }

    // this is how black movements are inserted into existing labels (can maybe take out move1 stuff)
    public void updateValues(String move, boolean colorPiece) {
        if (colorPiece) {
            this.move1 = move;
        } else {
            this.move2 = move;
        }
        updateImage();
    }
    
    public void setState(LabelStates state) {
        switch(state) {
            case OFF: currentState = offScreen; break;
            case ON: currentState = onScreen; break;
            default: break;
        }
    }
    
    public ILabelState getState() {
        return currentState;
    }
    
    public int getYPos() {
        return yPosition;
    }
    
    public void setYPos(int y) {
        yPosition = y;
    }
        
    //shift self up 100 px
    public void shiftUpwards() {
        currentState.upButtonPress();
    }
    
    public void shiftDownwards() {
        currentState.downButtonPress();
    }
    
    public World getLabelWorld() {
        return getWorld();
    }
    
}