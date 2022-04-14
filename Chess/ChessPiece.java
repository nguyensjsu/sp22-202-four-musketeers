import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * @author Pavol Biacko
 * @version date: 10.5.2020
 */

public class ChessPiece extends Actor
{
    boolean selected = false;
    boolean ready = false;
    boolean occupied = false;

    protected void select()
    {
        int x = this.getX();
        int y = this.getY();

        if (Greenfoot.mouseClicked(this) && Greenfoot.getMouseInfo().getButton() == 1)
        {
            if (getWorld().getObjects(select.class).size() == 0)
            {
                addSelection(x,y);
            }
            else if (getWorld().getObjectsAt(x,y,select.class).size() == 1)
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
        Select select = getWorld().getObjects(select.class).get(0);
        getWorld().removeObject(select);
        selected = false;
    }

    protected void move(int x,int y)
    {
        this.setLocation(x,y);
        ChessBoard chessBoard = (ChessBoard)getWorld();
        chessBoard.move++;
    }
}