/**
 * Write a description of class LabelStateOnScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LabelStateOnScreen implements ILabelState
{
    private Label label;
    private static final int labelX = 9;
    private Chessboard world;

    /**
     * Constructor for objects of class LabelStateOffScreen
     */
    public LabelStateOnScreen(Label l)
    {
        label = l;
        world = (Chessboard) label.getLabelWorld();
    }
    
    public void downButtonPress() {
        if(label.getYPos() == 0) { //first value in list about to go out
            label.setState(Label.LabelStates.OFF);
            label.setYPos(label.getYPos() - 1);
            
            world.removeObject(label);
        } else { // somewhere else in list
            label.setYPos(label.getYPos() - 1);
            label.setLocation(label.getX(), label.getYPos());
            label.updateImage();
        }
    }
    
    public void upButtonPress() {
        if(label.getYPos() == 7) { // last value in list about to go out
            label.setState(Label.LabelStates.OFF);
            label.setYPos(label.getYPos() + 1);
            
            world.removeObject(label);
        } else {  // somewhere else in list
            label.setYPos(label.getYPos() + 1);
            label.setLocation(label.getX(), label.getYPos());
            label.updateImage();
        }
    }
}
