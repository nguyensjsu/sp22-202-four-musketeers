import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * @author Pavol Biacko
 * @version date: 10.5.2020
 */

public class Chessboard extends World
{
    public int DIM_X = 10, DIM_Y = 9;
    
    public Chessboard()
    {    
        super(10,9,100);
        startup();
    }
    
    int move = 1; 
    boolean turn = true; //white turn first (true==white,false==black)
    boolean swapTurn = turn; 
    
    public void act()
    {
        change();
    }
    
    private void startup()
    {
        int x;
        int y;
    
        //tile placement
        for (x = 1; x < DIM_X - 2; x += 2)
        {
            for (y = 2; y < 10; y += 2)
            {
                Gray gray = new Gray();
                addObject(gray,x,y);
            }
        }
        for (x = 0; x < DIM_X - 2; x += 2)
        {
            for (y = 1; y < 8; y += 2)
            {
                Gray gray = new Gray();
                addObject(gray,x,y);
            }
        }
        for (x = 0; x < DIM_X - 2; x += 2)
        {
            for (y = 2; y < 10; y += 2)
            {
                Beige beige = new Beige();
                addObject(beige,x,y);
            }
        }
        for (x = 1; x < DIM_X - 2; x += 2)
        {
            for (y = 1; y < 8; y += 2)
            {
                Beige beige = new Beige();
                addObject(beige,x,y);
            }
        }
        
        setPaintOrder(ChessPiece.class,Tile.class);
        
        
        start();
    }
    
    private void start()
    {
        // true == white,false == black
        
        King wk = new King(true);
        Queen wq = new Queen(true);
        Bishop wb1 = new Bishop(true);
        Bishop wb2 = new Bishop(true);
        Knight wk1 = new Knight(true);
        Knight wk2 = new Knight(true);
        Rook wr1 = new Rook(true);
        Rook wr2 = new Rook(true);
        King bk = new King(false);
        Queen bq = new Queen(false);
        Bishop bb1 = new Bishop(false);
        Bishop bb2 = new Bishop(false);
        Knight bk1 = new Knight(false);
        Knight bk2 = new Knight(false);
        Rook br1 = new Rook(false);
        Rook br2 = new Rook(false);
        
        //King
        addObject(wk,4,8);
        addObject(bk,4,1);
        
        //Queen
        addObject(wq,3,8);
        addObject(bq,3,1);
        
        //Bishop
        addObject(wb1,2,8);
        addObject(wb2,5,8);
        addObject(bb1,5,1);
        addObject(bb2,2,1);
        
        //Knight
        addObject(wk1,1,8);
        addObject(wk2,6,8);
        addObject(bk1,6,1);
        addObject(bk2,1,1);
        
        //Rook
        addObject(wr1,0,8);
        addObject(wr2,7,8);
        addObject(br1,7,1);
        addObject(br2,0,1);
        
        //Pawn
        for (int i = 0;i < DIM_Y - 1;i++)
        {
            Pawn wp = new Pawn(true);
            addObject(wp,i,DIM_Y - 2);
        }
        for (int i = DIM_Y - 2;i >= 0;i--)
        {
            Pawn bp = new Pawn(false);
            addObject(bp,i,2);
        }
    }
    
    private void change()
    {
        
        if (swapTurn != turn)
        {
            Greenfoot.delay(30);
            
            int edge = DIM_Y - 1;
            
            for(King king:getObjects(King.class)) {
                king.setLocation(edge-king.getX()-1,edge-king.getY()+1);
            }
            
            for(Queen q:getObjects(Queen.class)) {
                q.setLocation(edge-q.getX()-1,edge-q.getY()+1);
            }
            
            for(Rook r:getObjects(Rook.class)) {
                r.setLocation(edge-r.getX()-1,edge-r.getY()+1);
            }
            
            for(Pawn p:getObjects(Pawn.class)) {
                p.setLocation(edge-p.getX()-1,edge-p.getY()+1);
            }
            
            for(Bishop b:getObjects(Bishop.class)) {
                b.setLocation(edge-b.getX()-1,edge-b.getY()+1);
            }
            
            for(Knight k:getObjects(Knight.class)) {
                k.setLocation(edge-k.getX()-1,edge-k.getY()+1);
            }
            
            swapTurn = turn;
        }
    }
}
