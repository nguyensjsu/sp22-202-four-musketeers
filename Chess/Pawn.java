import greenfoot.*;

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

    private void move()
    {
        if (ready)
        {
            if (Greenfoot.mouseClicked(null))
            {
                int x = Greenfoot.getMouseInfo().getX();
                int y = Greenfoot.getMouseInfo().getY();

                //boolean emptyF = getWorld().getObjectsAt(x,y,ChessPiece.class).isEmpty();
                //boolean emptyC = getWorld().getObjectsAt(x,y,Black.class).isEmpty();

                boolean empty = getWorld().getObjectsAt(x,y,ChessPiece.class).isEmpty();
                //ChessPiece empty = getWorld().getObjectsAt(x,y,ChessPiece.class).get(0);
                
                if (empty) //if tile is empty or other color
                {
                    if (this.getY() == 6)
                    {
                        if (x == this.getX() && y == this.getY() - 1)
                        {
                            move(x,y);
                        }
                        else if (x == this.getX() && y == this.getY() - 2)
                        {
                            Chessboard chessboard = (Chessboard)getWorld();
                            stepTwo = chessboard.move;
                            move(x,y);
                        }
                    }
                    else if (x == this.getX() && y == this.getY() - 1)
                    {
                        move(x,y);
                    }
                    else if ((x == this.getX() - 1 || x == this.getX() + 1) && y == this.getY() - 1)
                    {
                        Chessboard chessboard = (Chessboard)getWorld();
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
                else if (getWorld().getObjectsAt(x,y,ChessPiece.class).get(0).color != this.color) //diff color at pos
                {
                    if ((x == this.getX() - 1 || x == this.getX() + 1) && y == this.getY() - 1)
                    {
                        move(x,y);
                    }
                }

                ready = false;
            }
        }
    }

}