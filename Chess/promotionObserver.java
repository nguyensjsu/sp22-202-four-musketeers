import greenfoot.*;
import java.util.*;

public class promotionObserver
{
    private static promotionObserver observer = null;
    //World cb = getWorld();

    private Map<String, PromotionButton> buttonList = new Hashtable<String, PromotionButton>();

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

    public Map getButtonList()
    {
        return buttonList;
    }

    public void openPromotion(Pawn toRemove, int xLocation, int yLocation)
    {
        for(PromotionButton pb : buttonList.values())
        {
            pb.activationPromotion(toRemove, xLocation, yLocation, this);
        }
    }

    public void closePromotion()
    {
        for(PromotionButton pb : buttonList.values())
        {
            pb.deActivatePromotion();
        }
    }
}

/*
Dev notes:
The observer needs to:
1. send the coordinates and color to the buttons
2. the buttons then need activate and allow for "clicks"
3. once click all button buttons must be deactivated
*/