/**
 * Write a description of class IChessMoveSubject here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public interface IChessMoveSubject  
{
    public void notifyObservers(int turn, String movement);
    public void addObserver(IChessMoveObserver obs);
    public void deleteObserver(IChessMoveObserver obs);
    public void processMove(int x,int y,String pieceType);
}
