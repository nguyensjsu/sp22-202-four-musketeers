import greenfoot.*;

public class King extends ChessPiece {
    int moveCount = 0;

    public King(Boolean color) {
        super(color);
        if(this.color == false) setImage("black_king.png");
        else setImage("white_king.png");
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
                
                //boolean empty = getWorld().getObjectsAt(x,y,Black.class).isEmpty();
                boolean empty = getWorld().getObjectsAt(x,y,ChessPiece.class).isEmpty();
                //ChessPiece empty = getWorld().getObjectsAt(x,y,ChessPiece.class).get(0);
                
                if (empty || getWorld().getObjectsAt(x,y,ChessPiece.class).get(0).color != this.color) //if tile is empty or other color
                {
                    if (absDiffX <= 1 && absDiffY <= 1)
                    {
                        move(x,y);
                        moveCount++;
                    }
                    else if (moveCount == 0)   //castling
                    {
                        Chessboard chessboard = (Chessboard)getWorld();

                        int intColor = this.color ? 1 : 0;
                        switch(intColor) {
                            case 0: blackKing(chessboard, x, y);break;
                            case 1: whiteKing(chessboard,x,y);break;
                        }
                    }
                }
                
                ready = false;
            }
        }
    }

    void blackKing(Chessboard chessboard,int x,int y) {
        if (x == 1 && y == 7)
        {
            if (chessboard.getObjectsAt(1,7,ChessPiece.class).isEmpty() && chessboard.getObjectsAt(2,7,ChessPiece.class).isEmpty())
            {
                //Rook blackRook = chessboard.getObjects(Rook.class).get(2); // 2 is 3rd rook which is black
                Rook blackRook = null;
                for(Rook rook:chessboard.getObjects(Rook.class)) {
                    if(!rook.color) {
                        blackRook = rook;
                        break;
                    }
                }
                if (blackRook.moveCount == 0)
                {
                    move(x,y);
                    blackRook.setLocation(2,7);
                }
            }
        }
        else if (x == 5 && y == 7)
        {
            if (chessboard.getObjectsAt(4,7,ChessPiece.class).isEmpty() && chessboard.getObjectsAt(5,7,ChessPiece.class).isEmpty() && chessboard.getObjectsAt(6,7,ChessPiece.class).isEmpty())
            {
                //Rook blackRook = chessboard.getObjects(Rook.class).get(3); // 3 is 4rd rook which is black
                Rook blackRook = null;
                int second = 0;
                for(Rook rook:chessboard.getObjects(Rook.class)) {
                    if(!rook.color) {
                        second++;
                        if(second == 2) 
                            blackRook = rook;
                    }
                }
                if (blackRook.moveCount == 0)
                {
                    move(x,y);
                    moveCount++;
                    blackRook.setLocation(4,7);
                    blackRook.moveCount++;
                }
            }
        }
    }

    void whiteKing(Chessboard chessboard,int x,int y) {
        if (x == 2 && y == 7)
        {
            if (chessboard.getObjectsAt(1,7,ChessPiece.class).isEmpty() && chessboard.getObjectsAt(2,7,ChessPiece.class).isEmpty() && chessboard.getObjectsAt(3,7,ChessPiece.class).isEmpty())
            {
                Rook whiteRook = null;
                for(Rook rook:chessboard.getObjects(Rook.class)) {
                    if(rook.color) {
                        whiteRook = rook;
                        break;
                    }
                }

                //Rook whiteRook = chessboard.getObjects(Rook.class).get(0); // 0 is 1st rook which is white
                if (whiteRook.moveCount == 0)
                {
                    move(x,y);
                    whiteRook.setLocation(3,7);
                }
            }
        }
        else if (x == 6 && y == 7)
        {
            if (chessboard.getObjectsAt(5,7,ChessPiece.class).isEmpty() && chessboard.getObjectsAt(6,7,ChessPiece.class).isEmpty())
            {
                //Rook whiteRook = chessboard.getObjects(Rook.class).get(1); // 1 is 2nd rook which is white
                Rook whiteRook = null;
                int second = 0;
                for(Rook rook:chessboard.getObjects(Rook.class)) {
                    if(rook.color) {
                        second++;
                        if(second == 2) 
                            whiteRook = rook;
                    }
                }
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
