/**
 * Write a description of class IComponent here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public interface Component  
{
    void addChild(Component c);
    void removeChild(Component c);
    Component getChild(int i);
    void draw();
}
