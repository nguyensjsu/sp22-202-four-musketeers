/**
 * Write a description of class LabelStateOffScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LabelStateOffScreen implements ILabelState
{
    private Label label;
    private static final int labelX = 9;
    private Chessboard world;

    /**
     * Constructor for objects of class LabelStateOffScreen
     */
    public LabelStateOffScreen(Label l)
    {
        label = l;
        world = (Chessboard) label.getLabelWorld();
    }
    

    public void downButtonPress() {
        if(label.getYPos() == 8) { //right below last value on display
            label.setState(Label.LabelStates.ON);
            label.setYPos(label.getYPos() - 1);
            
            world.addObject(label,labelX,label.getYPos());
        } else { // not next value to appear below or above list 
            label.setYPos(label.getYPos() - 1);
        }
        
    }
    
    
    
    public void upButtonPress() {
        if(label.getYPos() == -1) { // right above first value on display
            label.setState(Label.LabelStates.ON);
            label.setYPos(label.getYPos() + 1);
            
            world.addObject(label,labelX,label.getYPos());
        } else { 
            label.setYPos(label.getYPos() + 1);
        }
    }
}
