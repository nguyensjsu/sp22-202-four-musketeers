import greenfoot.*;

public class Bishop extends ChessPiece {

    public Bishop(Boolean color) {
        super(color);
        if(this.color == false) setImage("black_bishop.png");
        else setImage("white_bishop.png");
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
                
                int absDiffX = Math.abs(x - this.getX());
                int absDiffY = Math.abs(y - this.getY());
                
                boolean empty = getWorld().getObjectsAt(x,y,ChessPiece.class).isEmpty();
                //ChessPiece empty = getWorld().getObjectsAt(x,y,ChessPiece.class).get(0);
                
                if (empty || getWorld().getObjectsAt(x,y,ChessPiece.class).get(0).color != this.color) //if tile is empty or other color
                {
                    if (absDiffX == absDiffY)
                    {
                        int differenceX = x - this.getX();
                        int differenceY = y - this.getY();
                        int difference = Math.abs(differenceX);
                        
                        if (differenceX < 0 && differenceY < 0)
                        {
                            for (int i = 1;i < difference;i++)
                            {
                                if (!getWorld().getObjectsAt(this.getX() - i,this.getY() - i,ChessPiece.class).isEmpty())
                                {
                                    occupied = true;
                                    break;
                                }
                            }
                        }
                        else if (differenceX < 0 && differenceY > 0)
                        {
                            for (int i = 1;i < difference;i++)
                            {
                                if (!getWorld().getObjectsAt(this.getX() - i,this.getY() + i,ChessPiece.class).isEmpty())
                                {
                                    occupied = true;
                                    break;
                                }
                            }
                        }
                        else if (differenceX > 0 && differenceY < 0)
                        {
                            for (int i = 1;i < difference;i++)
                            {
                                if (!getWorld().getObjectsAt(this.getX() + i,this.getY() - i,ChessPiece.class).isEmpty())
                                {
                                    occupied = true;
                                    break;
                                }
                            }
                        }
                        else if (differenceX > 0 && differenceY > 0)
                        {
                            for (int i = 1;i < difference;i++)
                            {
                                if (!getWorld().getObjectsAt(this.getX() + i,this.getY() + i,ChessPiece.class).isEmpty())
                                {
                                    occupied = true;
                                    break;
                                }
                            }
                        }
                        if (!occupied)
                        {
                            move(x,y);
                        }
                    }
                }
                
                ready = false;
                occupied = false;
            }
        }
    }
}
