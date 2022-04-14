import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * @author Pavol Biacko
 * @version date: 10.5.2020
 */

public class Sachovnica extends World
{
    public Sachovnica()
    {    
        super(8,8,100);
        priprava();
    }
    
    int tahy = 1;
    int tahy_kont = 1;
    
    public void act()
    {
        zmena();
    }
    
    private void priprava()
    {
        int x;
        int y;
    
        //rozmiestnenie policok
        for (x = 1;x < 8;x += 2)
        {
            for (y = 0;y < 8;y += 2)
            {
                Siva siva = new Siva();
                addObject(siva,x,y);
            }
        }
        for (x = 0;x < 8;x += 2)
        {
            for (y = 1;y < 8;y += 2)
            {
                Siva siva = new Siva();
                addObject(siva,x,y);
            }
        }
        for (x = 0;x < 8;x += 2)
        {
            for (y = 0;y < 8;y += 2)
            {
                Bezova bezova = new Bezova();
                addObject(bezova,x,y);
            }
        }
        for (x = 1;x < 8;x += 2)
        {
            for (y = 1;y < 8;y += 2)
            {
                Bezova bezova = new Bezova();
                addObject(bezova,x,y);
            }
        }
        
        setPaintOrder(Figurky.class,Policka.class);
        
        //pociatocna pozicia
        start();
    }
    
    private void start()
    {
        //figury
        BielyKral bk = new BielyKral();
        BielaDama bd = new BielaDama();
        BielyStrelec bs1 = new BielyStrelec();
        BielyStrelec bs2 = new BielyStrelec();
        BielyJazdec bj1 = new BielyJazdec();
        BielyJazdec bj2 = new BielyJazdec();
        BielaVeza bv1 = new BielaVeza();
        BielaVeza bv2 = new BielaVeza();
        CiernyKral ck = new CiernyKral();
        CiernaDama cd = new CiernaDama();
        CiernyStrelec cs1 = new CiernyStrelec();
        CiernyStrelec cs2 = new CiernyStrelec();
        CiernyJazdec cj1 = new CiernyJazdec();
        CiernyJazdec cj2 = new CiernyJazdec();
        CiernaVeza cv1 = new CiernaVeza();
        CiernaVeza cv2 = new CiernaVeza();
        
        //krali
        addObject(bk,4,7);
        addObject(ck,4,0);
        
        //damy
        addObject(bd,3,7);
        addObject(cd,3,0);
        
        //strelci
        addObject(bs1,2,7);
        addObject(bs2,5,7);
        addObject(cs1,5,0);
        addObject(cs2,2,0);
        
        //jazdci
        addObject(bj1,1,7);
        addObject(bj2,6,7);
        addObject(cj1,6,0);
        addObject(cj2,1,0);
        
        //veze
        addObject(bv1,0,7);
        addObject(bv2,7,7);
        addObject(cv1,7,0);
        addObject(cv2,0,0);
        
        //pesiaci
        for (int i = 0;i < 8;i++)
        {
            BielyPesiak bp = new BielyPesiak();
            addObject(bp,i,6);
        }
        for (int i = 7;i >= 0;i--)
        {
            CiernyPesiak cp = new CiernyPesiak();
            addObject(cp,i,1);
        }
    }
    
    private void zmena()
    {
        if (tahy != tahy_kont)
        {
            Greenfoot.delay(30);
            
            int bKrali = getObjects(BielyKral.class).size();
            for (int i = 0;i < bKrali;i++)
            {
                BielyKral obj = getObjects(BielyKral.class).get(i);
                obj.setLocation(7 - obj.getX(),7 - obj.getY());
            }
            
            int bDamy = getObjects(BielaDama.class).size();
            for (int i = 0;i < bDamy;i++)
            {
                BielaDama obj = getObjects(BielaDama.class).get(i);
                obj.setLocation(7 - obj.getX(),7 - obj.getY());
            }
            
            int bStrelci = getObjects(BielyStrelec.class).size();
            for (int i = 0;i < bStrelci;i++)
            {
                BielyStrelec obj = getObjects(BielyStrelec.class).get(i);
                obj.setLocation(7 - obj.getX(),7 - obj.getY());
            }
            
            int bJazdci = getObjects(BielyJazdec.class).size();
            for (int i = 0;i < bJazdci;i++)
            {
                BielyJazdec obj = getObjects(BielyJazdec.class).get(i);
                obj.setLocation(7 - obj.getX(),7 - obj.getY());
            }
            
            int bVeze = getObjects(BielaVeza.class).size();
            for (int i = 0;i < bVeze;i++)
            {
                BielaVeza obj = getObjects(BielaVeza.class).get(i);
                obj.setLocation(7 - obj.getX(),7 - obj.getY());
            }

            int bPesiaci = getObjects(BielyPesiak.class).size();
            for (int i = 0;i < bPesiaci;i++)
            {
                BielyPesiak obj = getObjects(BielyPesiak.class).get(i);
                obj.setLocation(7 - obj.getX(),7 - obj.getY());
            }
            
            int cKrali = getObjects(CiernyKral.class).size();
            for (int i = 0;i < cKrali;i++)
            {
                CiernyKral obj = getObjects(CiernyKral.class).get(i);
                obj.setLocation(7 - obj.getX(),7 - obj.getY());
            }
            
            int cDamy = getObjects(CiernaDama.class).size();
            for (int i = 0;i < cDamy;i++)
            {
                CiernaDama obj = getObjects(CiernaDama.class).get(i);
                obj.setLocation(7 - obj.getX(),7 - obj.getY());
            }
            
            int cStrelci = getObjects(CiernyStrelec.class).size();
            for (int i = 0;i < cStrelci;i++)
            {
                CiernyStrelec obj = getObjects(CiernyStrelec.class).get(i);
                obj.setLocation(7 - obj.getX(),7 - obj.getY());
            }
            
            int cJazdci = getObjects(CiernyJazdec.class).size();
            for (int i = 0;i < cJazdci;i++)
            {
                CiernyJazdec obj = getObjects(CiernyJazdec.class).get(i);
                obj.setLocation(7 - obj.getX(),7 - obj.getY());
            }
            
            int cVeze = getObjects(CiernaVeza.class).size();
            for (int i = 0;i < cVeze;i++)
            {
                CiernaVeza obj = getObjects(CiernaVeza.class).get(i);
                obj.setLocation(7 - obj.getX(),7 - obj.getY());
            }
            
            int cPesiaci = getObjects(CiernyPesiak.class).size();
            for (int i = 0;i < cPesiaci;i++)
            {
                CiernyPesiak obj = getObjects(CiernyPesiak.class).get(i);
                obj.setLocation(7 - obj.getX(),7 - obj.getY());
            }
            
            tahy_kont = tahy;
        }
    }
}
