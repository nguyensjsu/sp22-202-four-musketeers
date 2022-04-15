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
    int turn = 1;
    
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
        //figury
        WhiteKing wk = new WhiteKing();
        WhiteQueen wq = new WhiteQueen();
        WhiteBishop wb1 = new WhiteBishop();
        WhiteBishop wb2 = new WhiteBishop();
        WhiteKnight wk1 = new WhiteKnight();
        WhiteKnight wk2 = new WhiteKnight();
        WhiteRook wr1 = new WhiteRook();
        WhiteRook wr2 = new WhiteRook();
        BlackKing bk = new BlackKing();
        BlackQueen bq = new BlackQueen();
        BlackBishop bb1 = new BlackBishop();
        BlackBishop bb2 = new BlackBishop();
        BlackKnight bk1 = new BlackKnight();
        BlackKnight bk2 = new BlackKnight();
        BlackRook br1 = new BlackRook();
        BlackRook br2 = new BlackRook();
        
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
            WhitePawn wp = new WhitePawn();
            addObject(wp,i,6);
        }
        for (int i = 7;i >= 0;i--)
        {
            BlackPawn bp = new BlackPawn();
            addObject(bp,i,1);
        }
    }
    
    private void change()
    {
        if (move != turn)
        {
            Greenfoot.delay(30);
            
            int wKingi = getObjects(WhiteKing.class).size();
            for (int i = 0;i < wKingi;i++)
            {
                WhiteKing obj = getObjects(WhiteKing.class).get(i);
                obj.setLocation(7 - obj.getX(),7 - obj.getY());
            }
            
            int wQueeni = getObjects(WhiteQueen.class).size();
            for (int i = 0;i < wQueeni;i++)
            {
                WhiteQueen obj = getObjects(WhiteQueen.class).get(i);
                obj.setLocation(7 - obj.getX(),7 - obj.getY());
            }
            
            int wBishopi = getObjects(WhiteBishop.class).size();
            for (int i = 0;i < wBishopi;i++)
            {
                WhiteBishop obj = getObjects(WhiteBishop.class).get(i);
                obj.setLocation(7 - obj.getX(),7 - obj.getY());
            }
            
            int wKnighti = getObjects(WhiteKnight.class).size();
            for (int i = 0;i < wKnighti;i++)
            {
                WhiteKnight obj = getObjects(WhiteKnight.class).get(i);
                obj.setLocation(7 - obj.getX(),7 - obj.getY());
            }
            
            int wRooki = getObjects(WhiteRook.class).size();
            for (int i = 0;i < wRooki;i++)
            {
                WhiteRook obj = getObjects(WhiteRook.class).get(i);
                obj.setLocation(7 - obj.getX(),7 - obj.getY());
            }

            int wPawni = getObjects(WhitePawn.class).size();
            for (int i = 0;i < wPawni;i++)
            {
                WhitePawn obj = getObjects(WhitePawn.class).get(i);
                obj.setLocation(7 - obj.getX(),7 - obj.getY());
            }
            
            int bKingi = getObjects(BlackKing.class).size();
            for (int i = 0;i < bKingi;i++)
            {
                BlackKing obj = getObjects(BlackKing.class).get(i);
                obj.setLocation(7 - obj.getX(),7 - obj.getY());
            }
            
            int bQueeni = getObjects(BlackQueen.class).size();
            for (int i = 0;i < bQueeni;i++)
            {
                BlackQueen obj = getObjects(BlackQueen.class).get(i);
                obj.setLocation(7 - obj.getX(),7 - obj.getY());
            }
            
            int bBishopi = getObjects(BlackBishop.class).size();
            for (int i = 0;i < bBishopi;i++)
            {
                BlackBishop obj = getObjects(BlackBishop.class).get(i);
                obj.setLocation(7 - obj.getX(),7 - obj.getY());
            }
            
            int bKnighti = getObjects(BlackKnight.class).size();
            for (int i = 0;i < bKnighti;i++)
            {
                BlackKnight obj = getObjects(BlackKnight.class).get(i);
                obj.setLocation(7 - obj.getX(),7 - obj.getY());
            }
            
            int bRooki = getObjects(BlackRook.class).size();
            for (int i = 0;i < bRooki;i++)
            {
                BlackRook obj = getObjects(BlackRook.class).get(i);
                obj.setLocation(7 - obj.getX(),7 - obj.getY());
            }
            
            int bPawni = getObjects(BlackPawn.class).size();
            for (int i = 0;i < bPawni;i++)
            {
                BlackPawn obj = getObjects(BlackPawn.class).get(i);
                obj.setLocation(7 - obj.getX(),7 - obj.getY());
            }
            
            turn = move;
        }
    }
}
