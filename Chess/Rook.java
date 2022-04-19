import greenfoot.*;

public class Rook extends ChessPiece{
    int moveCount = 0;
    
    public Rook(Boolean color) {
        super(color);
        if(this.color == false) setImage("black_rook.png");
        else setImage("white_rook.png");
    }
    
    public void act() 
    {
        Chessboard chessBoard = (Chessboard)getWorld();
        if (this.color == chessBoard.turn)
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
                
                //boolean empty = getWorld().getObjectsAt(x,y,Black.class).isEmpty();
                boolean empty = getWorld().getObjectsAt(x,y,ChessPiece.class).isEmpty();
                if (empty || getWorld().getObjectsAt(x,y,ChessPiece.class).get(0).color != this.color)
                {
                    if (x == this.getX())
                    {
                        int difference = y - this.getY();
                        
                        if (difference < 0)
                        {
                            difference = Math.abs(difference);
                            for (int i = 1;i < difference;i++)
                            {
                                if (!getWorld().getObjectsAt(x,this.getY() - i,ChessPiece.class).isEmpty())
                                {
                                    occupied = true;
                                    break;
                                }
                            }
                        }
                        else
                        {
                            difference = Math.abs(difference);
                            for (int i = 1;i < difference;i++)
                            {
                                if (!getWorld().getObjectsAt(x,this.getY() + i,ChessPiece.class).isEmpty())
                                {
                                    occupied = true;
                                    break;
                                }
                            }
                        }
                        if (!occupied)
                        {
                            move(x,y);
                            moveCount++;
                        }
                    }
                    else if (y == this.getY())
                    {
                        int difference = x - this.getX();
                        
                        if (difference < 0)
                        {
                            difference = Math.abs(difference);
                            for (int i = 1;i < difference;i++)
                            {
                                if (!getWorld().getObjectsAt(this.getX() - i,y,ChessPiece.class).isEmpty())
                                {
                                    occupied = true;
                                    break;
                                }
                            }
                        }
                        else
                        {
                            difference = Math.abs(difference);
                            for (int i = 1;i < difference;i++)
                            {
                                if (!getWorld().getObjectsAt(this.getX() + i,y,ChessPiece.class).isEmpty())
                                {
                                    occupied = true;
                                    break;
                                }
                            }
                        }
                        if (!occupied)
                        {
                            move(x,y);
                            moveCount++;
                        }
                    }
                }
                
                ready = false;
                occupied = false;
            }
        }
    } 
}
