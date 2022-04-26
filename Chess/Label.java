import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
// Leaf class
public class Label extends Actor 
{
    private int fontSize;
    private Color lineColor = Color.BLACK;
    private Color fillColor = Color.BLACK;

    private int turn;
    private String move1;
    private String move2;
    
    private static final Color transparent = new Color(0,0,0,0);

    public Label(String header) {
        this.fontSize = fontSize;
        this.turn = -1;
        this.move1 = header;
        this.move2 = "";
        updateImage();
    }

    
    /**
     * Create a new label, initialise it with the needed text and the font size 
     */
    public Label(int turn,int fontSize)
    {
        this.fontSize = fontSize;
        // Default values 
        this.turn = turn; 
        this.move1 = "";
        this.move2 = "";
        updateImage();
    }

    
    /**
     * Sets the line color of the text
     * 
     * @param lineColor the line color of the text
     */
    public void setLineColor(Color lineColor)
    {
        this.lineColor = lineColor;
        updateImage();
    }
    
    /**
     * Sets the fill color of the text
     * 
     * @param fillColor the fill color of the text
     */
    public void setFillColor(Color fillColor)
    {
        this.fillColor = fillColor;
        updateImage();
    }
    

    /**
     * Update the image on screen to show the current value.
     */
    private void updateImage()
    {
        if(turn == -1) { //if header label
            setImage(move1);
        } else { // if history label
            String value = String.valueOf(turn) + "." + "" + move1 + " " + move2;
            setImage(new GreenfootImage(value, fontSize, fillColor, transparent, lineColor));
        }
        
    }

    // this is how black movements are inserted into existing labels (can maybe take out move1 stuff)
    public void updateValues(String move,Boolean colorPiece) {
        if(colorPiece) 
            this.move1 = move;
        else 
            this.move2 = move;
        updateImage();
        
    }

    //shift self up 100 px
    public void shiftUpwards() {
        setLocation(getX(),getY()-1);
        updateImage();
    }
    
}