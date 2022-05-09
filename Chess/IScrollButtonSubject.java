/**
 * Write a description of class IScrollButtonSubject here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public interface IScrollButtonSubject  
{
    void notifyObservers();
    void addObserver(IScrollButtonObserver obs);
    void removeObserver(IScrollButtonObserver obs);
    void buttonClick();
}
