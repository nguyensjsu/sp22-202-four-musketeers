import greenfoot.*;
import java.util.*;

public class promotionObserver
{
    private static promotionObserver observer = null;
    //World cb = getWorld();

    private Dictionary buttonList = new Hashtable<>();

    rookPromotionButton rpb;

    private promotionObserver()
    {
        // //initalize and add the buttons
        buttonList.put("rook", new rookPromotionButton());
        buttonList.put("knight", new knightPromotionButton());
        buttonList.put("bishop", new bishopPromotionButton());
        buttonList.put("queen", new queenPromotionButton());
    }

    public static promotionObserver getInstance()
    {
        if (observer == null)
        {
            observer = new promotionObserver();
        }
        return observer;
    }

    public Dictionary getButtonList()
    {
        return buttonList;
    }

}
