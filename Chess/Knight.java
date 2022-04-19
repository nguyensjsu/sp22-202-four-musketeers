import greenfoot.*;

public class Knight extends ChessPiece {

    public Knight(Boolean color){
        super(color);
        if(this.color == false) setImage("black_knight.png");
        else setImage("white_knight.png");
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
                
                boolean tile1 = (x == this.getX() - 2 && y == this.getY() - 1);
                boolean tile2 = (x == this.getX() - 2 && y == this.getY() + 1);
                boolean tile3 = (x == this.getX() - 1 && y == this.getY() - 2);
                boolean tile4 = (x == this.getX() - 1 && y == this.getY() + 2);
                boolean tile5 = (x == this.getX() + 1 && y == this.getY() - 2);
                boolean tile6 = (x == this.getX() + 1 && y == this.getY() + 2);
                boolean tile7 = (x == this.getX() + 2 && y == this.getY() - 1);
                boolean tile8 = (x == this.getX() + 2 && y == this.getY() + 1);
                
                //boolean empty = getWorld().getObjectsAt(x,y,Black.class).isEmpty();
                boolean empty = getWorld().getObjectsAt(x,y,ChessPiece.class).isEmpty();
                //ChessPiece empty = getWorld().getObjectsAt(x,y,ChessPiece.class).get(0);
                
                if (empty || getWorld().getObjectsAt(x,y,ChessPiece.class).get(0).color != this.color) //if tile is empty or other color
                {
                    if (tile1 || tile2 || tile3 || tile4 || tile5 || tile6 || tile7 || tile8)
                    {
                        move(x,y);
                    }
                }
                
                ready = false;
            }
        }
    }   
}
