import greenfoot.*;
import java.util.List;
import javafx.util.Pair;

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
                
                List<Pair<Integer, Integer>> vals = List.of(
                    new Pair<>(-2, -1), new Pair<>(-2, 1),
                    new Pair<>(-1, -2), new Pair<>(-1, 2),
                    new Pair<>(1, -2), new Pair<>(1, 2),
                    new Pair<>(2, -1), new Pair<>(2, 1)
                );
                
                boolean empty = getWorld().getObjectsAt(x,y,ChessPiece.class).isEmpty();
                
                if (empty || getWorld().getObjectsAt(x,y,ChessPiece.class).get(0).color != this.color) //if tile is empty or other color
                {
                    for(Pair<Integer, Integer> p: vals) {
                        if((x == this.getX() + p.getKey() && y == this.getY() + p.getValue())) {
                            move(x, y);
                            break;
                        }
                    }
                }
                
                ready = false;
            }
        }
    }   
}
