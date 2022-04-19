import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * @author Pavol Biacko
 * @version date: 10.5.2020
 */

public class Chessboard extends World
{
    public Chessboard()
    {    
        super(8,8,100);
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
    
        //rozmiestnenie policok
        for (x = 1;x < 8;x += 2)
        {
            for (y = 0;y < 8;y += 2)
            {
                Gray gray = new Gray();
                addObject(gray,x,y);
            }
        }
        for (x = 0;x < 8;x += 2)
        {
            for (y = 1;y < 8;y += 2)
            {
                Gray gray = new Gray();
                addObject(gray,x,y);
            }
        }
        for (x = 0;x < 8;x += 2)
        {
            for (y = 0;y < 8;y += 2)
            {
                Beige beige = new Beige();
                addObject(beige,x,y);
            }
        }
        for (x = 1;x < 8;x += 2)
        {
            for (y = 1;y < 8;y += 2)
            {
                Beige beige = new Beige();
                addObject(beige,x,y);
            }
        }
        
        setPaintOrder(ChessPiece.class,Tile.class);
        
        //pociatocna pozicia
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
        
       

        
        
        //krali
        addObject(wk,4,7);
        addObject(bk,4,0);
        
        //damy
        addObject(wq,3,7);
        addObject(bq,3,0);
        
        //strelci
        addObject(wb1,2,7);
        addObject(wb2,5,7);
        addObject(bb1,5,0);
        addObject(bb2,2,0);
        
        //jazdci
        addObject(wk1,1,7);
        addObject(wk2,6,7);
        addObject(bk1,6,0);
        addObject(bk2,1,0);
        
        //veze
        addObject(wr1,0,7);
        addObject(wr2,7,7);
        addObject(br1,7,0);
        addObject(br2,0,0);
        
        //pesiaci
        for (int i = 0;i < 8;i++)
        {
            Pawn wp = new Pawn(true);
            addObject(wp,i,6);
        }
        for (int i = 7;i >= 0;i--)
        {
            Pawn bp = new Pawn(false);
            addObject(bp,i,1);
        }
    }
    
    private void change()
    {
        
        if (swapTurn != turn)
        {
            Greenfoot.delay(30);
            
            
            for(King king:getObjects(King.class)) {
                king.setLocation(7 - king.getX(),7 - king.getY());
            }
            
            for(Queen p:getObjects(Queen.class)) {
                p.setLocation(7-p.getX(),7-p.getY());
            }
            
            for(Rook p:getObjects(Rook.class)) {
                p.setLocation(7-p.getX(),7-p.getY());
            }
            
            for(Pawn p:getObjects(Pawn.class)) {
                p.setLocation(7-p.getX(),7-p.getY());
            }
            
            for(Bishop p:getObjects(Bishop.class)) {
                p.setLocation(7-p.getX(),7-p.getY());
            }
            
            for(Knight p:getObjects(Knight.class)) {
                p.setLocation(7-p.getX(),7-p.getY());
            }
            
            swapTurn = turn;
        }
    }
}
