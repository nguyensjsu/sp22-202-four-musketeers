import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * @author Pavol Biacko
 * @version date: 10.5.2020
 */

public class ChessPiece extends Actor 
{
    boolean selected = false;
    boolean ready = false;
    boolean occupied = false;

    protected boolean color; // false for black, true for white 

    public ChessPiece(Boolean color) {
        this.color = color;
    }
    
    public ChessPiece() {
    }

    protected void select()
    {
        int x = this.getX();
        int y = this.getY();

        if (Greenfoot.mouseClicked(this) && Greenfoot.getMouseInfo().getButton() == 1)
        {
            if (getWorld().getObjects(Select.class).size() == 0)
            {
                addSelection(x,y);
            }
            else if (getWorld().getObjectsAt(x,y,Select.class).size() == 1)
            {
                deleteSelection();
            }
            else
            {
                addSelection(x,y);
            }
        }
        else if (Greenfoot.mouseClicked(null) && selected)
        {
            deleteSelection();
        }
    }

    protected void changeStatus()
    {
        if (selected)
        {
            ready = true;
        }
    }

    private void addSelection(int x,int y)
    {
        Select select = new Select();
        getWorld().addObject(select,x,y);
        selected = true;
    }

    private void deleteSelection()
    {
        Select select = getWorld().getObjects(Select.class).get(0);
        getWorld().removeObject(select);
        selected = false;
    }

    protected void move(int x,int y) //movehistory should prob go here
    {
        this.setLocation(x,y);
        Chessboard chessBoard = (Chessboard)getWorld();
        chessBoard.turn = !chessBoard.turn;
        
        String pieceType = this.getClass().getSimpleName().substring(0,1);
        if(pieceType.equals("K")) 
            pieceType = this.getClass().getSimpleName().substring(0,2);
        //+1 to not start from 0,8 to have distance measured from bottom as opposed to top
        
        chessBoard.processMove(x + 1,8 - y + 1,pieceType);
        chessBoard.move++;
    }

    protected void capture()
    {
        ChessPiece touchPiece = (ChessPiece) getOneIntersectingObject(ChessPiece.class);
        if (touchPiece != null && touchPiece.color != this.color)
        {
            getWorld().removeObject(touchPiece);
        }
    } 
    
}
