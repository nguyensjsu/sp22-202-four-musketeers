import greenfoot.*;
import java.util.*;


public class Pawn extends ChessPiece {
    int stepTwo;

    public Pawn(Boolean color) {
        super(color);
        if(this.color == false) setImage("black_pawn.png");
        else setImage("white_pawn.png");
    }

    public void act() 
    {
        Chessboard chessboard = (Chessboard)getWorld();

        if (this.color == chessboard.turn)
        {
            select();
            move();
            changeStatus();
            capture();
        }
        
    }

    private boolean isSpaceEmpty(int x, int y)
    {
        return getWorld().getObjectsAt(x,y,ChessPiece.class).isEmpty();
    }

    private void move()
    {

        //check if it's the pawn's turn
        if (ready)
        {
            //when a mouse is clicked anywhere
            if (Greenfoot.mouseClicked(null))
            {
                Chessboard chessboard = (Chessboard)getWorld();
                
                //get the location of the click
                int x = Greenfoot.getMouseInfo().getX();
                int y = Greenfoot.getMouseInfo().getY();

                int delta_x = x - this.getX();
                int delta_y = y - this.getY();

                //only advancing
                if(x == this.getX())
                {
                    //if first move allow 2 spaces. (need to check 2 spaces ahead to allow move)
                    if(this.getY() == chessboard.DIM_Y - 2 && delta_y == -2)
                    {
                        //check if the 2 spaces in front of it are blank
                        if( this.isSpaceEmpty(x, y) && this.isSpaceEmpty(x, y+1) )
                        {
                            stepTwo = chessboard.move;
                            move(x,y);
                        }
                    }
                    //else allow for 1 move forward
                    else if(delta_y == -1 && this.isSpaceEmpty(x, y))
                    {
                        move(x,y);
                    }
                }
                //capturing 
                else if(Math.abs(delta_x) == 1 && delta_y == -1 )
                {


                    //case 1 capturing a standard peice
                    //check if the space is not empty and if the piece there is not the same color
                    if(!isSpaceEmpty(x, y) && getWorld().getObjectsAt(x,y,ChessPiece.class).get(0).color != this.color)
                    {
                        move(x,y);
                    }
                    //case 2 en passant
                    else if(chessboard.getObjectsAt(x,y + 1,Pawn.class).size() != 0)
                    {
                        Pawn oppPawn = chessboard.getObjectsAt(x,y + 1,Pawn.class).get(0);
                        if(this.color != oppPawn.color){
                            if (oppPawn.stepTwo == chessboard.move - 1)
                            {
                                move(x,y);
                                chessboard.removeObject(oppPawn);
                            }
                        }
                    }
                }
                    
                //only capturing
                    //check if 


//removing old pawn code                 
/*
                //if tile is empty
                if (empty) 
                {
                    //if first row
                    if (this.getY() == chessboard.DIM_Y - 2)
                    {
                        //single square moves check
                        if (x == this.getX() && y == this.getY() - 1)
                        {
                            move(x,y);
                        }

                        //allows the pawn to move 2 places on it's first try
                        else if (x == this.getX() && y == this.getY() - 2)
                        {
                            stepTwo = chessboard.move;
                            move(x,y);
                        }
                    }
                    //standard forward move
                    else if (x == this.getX() && y == this.getY() - 1)
                    {
                        move(x,y);
                    }
                    
                    //en passant check x check for attacking y check for advancing.
                    else if ((x == this.getX() - 1 || x == this.getX() + 1) && y == this.getY() - 1)
                    {
                        if (chessboard.getObjectsAt(x,y + 1,Pawn.class).size() != 0)
                        {
                            Pawn oppPawn = chessboard.getObjectsAt(x,y + 1,Pawn.class).get(0);
                            if(this.color != oppPawn.color){
                                if (oppPawn.stepTwo == chessboard.move - 1)
                                {
                                    move(x,y);
                                    chessboard.removeObject(oppPawn);
                                }
                            }
                        }
                    }
                }
                //capture move
                else if (getWorld().getObjectsAt(x,y,ChessPiece.class).get(0).color != this.color) //diff color at pos
                {
                    if ((x == this.getX() - 1 || x == this.getX() + 1) && y == this.getY() - 1)
                    {
                        move(x,y);
                    }
                }
*/
                ready = false;
            }
        }
    }

}
