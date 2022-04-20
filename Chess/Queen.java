import greenfoot.*;

public class Queen extends ChessPiece {

    public Queen(Boolean color){
        super(color);
        if(this.color == false) setImage("black_queen.png");
        else setImage("white_queen.png");
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
                
                int diffX = x - this.getX();
                int diffY = y - this.getY();
                
                int absDiffX = Math.abs(x - this.getX());
                int absDiffY = Math.abs(y - this.getY());
                
                boolean empty = getWorld().getObjectsAt(x,y,ChessPiece.class).isEmpty();
                
                
                if (empty || getWorld().getObjectsAt(x,y,ChessPiece.class).get(0).color != this.color)
                {
                    if (x == this.getX())
                    {
                        int mult = (diffY < 0) ? -1 : 1;
                        
                        for (int i = 1;i < absDiffY;i++)
                        {
                            if (!getWorld().getObjectsAt(x,this.getY() + (mult * i),ChessPiece.class).isEmpty())
                            {
                                occupied = true;
                                break;
                            }
                        }
                        if (!occupied) move(x,y);
                        
                    }
                    else if (y == this.getY())
                    {
                        int mult = (diffX < 0) ? -1 : 1;
                        
                        for (int i = 1; i < absDiffX;i++)
                        {
                            if (!getWorld().getObjectsAt(this.getX() + (mult * i),y,ChessPiece.class).isEmpty())
                            {
                                occupied = true;
                                break;
                            }
                        }
                        if (!occupied) move(x,y);
                    }
                    else if (absDiffX == absDiffY)
                    {
                        int multX = 1;
                        int multY = 1;
                        
                        if (diffX < 0) multX = -1;
                        if (diffY < 0) multY = -1;
                            
                        for (int i = 1; i < absDiffX;i++)
                        {
                            if (!getWorld().getObjectsAt(this.getX() + (multX * i),this.getY() + (multY * i),ChessPiece.class).isEmpty())
                            {
                                occupied = true;
                                break;
                            }
                        }
                        
                        if (!occupied) move(x,y);
                    }
                }
                
                ready = false;
                occupied = false;
            }
        }
    }
}
