import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * @author Pavol Biacko
 * @version date: 10.5.2020
 */

public class WhiteKing extends White
{
    int moveCount = 0;
    
    public void act() 
    {
        ChessBoard chessBoard = (ChessBoard)getWorld();
        if (chessBoard.move % 2 == 1)
        {
            select();
            move();
            changeStatus();
            acquire();
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
                
                int absDifferenceX = Math.abs(x - this.getX());
                int absDifferenceY = Math.abs(y - this.getY());
                
                boolean emtpy = getWorld().getObjectsAt(x,y,White.class).isEmpty();
                
                if (emtpy)
                {
                    if (absDifferenceX <= 1 && absDifferenceY <= 1)
                    {
                        move(x,y);
                        moveCount++;
                    }
                    else if (moveCount == 0)   //castling
                    {
                        ChessBoard chessBoard = (ChessBoard)getWorld();
                        if (x == 2 && y == 7)
                        {
                            if (chessBoard.getObjectsAt(1,7,Piece.class).isEmpty() && chessBoard.getObjectsAt(2,7,Piece.class).isEmpty() && chessBoard.getObjectsAt(3,7,Piece.class).isEmpty())
                            {
                                WhiteRook whiteRook = chessBoard.getObjects(WhiteRook.class).get(0);
                                if (whiteRook.moveCount == 0)
                                {
                                    move(x,y);
                                    whiteRook.setLocation(3,7);
                                }
                            }
                        }
                        else if (x == 6 && y == 7)
                        {
                            if (chessBoard.getObjectsAt(5,7,Piece.class).isEmpty() && chessBoard.getObjectsAt(6,7,Piece.class).isEmpty())
                            {
                                WhiteRook whiteRook = chessBoard.getObjects(WhiteRook.class).get(1);
                                if (whiteRook.moveCount == 0)
                                {
                                    move(x,y);
                                    moveCount++;
                                    whiteRook.setLocation(5,7);
                                    whiteRook.moveCount++;
                                }
                            }
                        }
                    }
                }
                
                ready = false;
            }
        }
    }
}
