import javax.swing.SingleSelectionModel;

public class promotionObserver
{
    private static promotionObserver observer = null;

    private PromotionButton rookButton;
    private PromotionButton knightButton;
    private PromotionButton bishopButton;
    private PromotionButton queenButton;

    private promotionObserver()
    {
        //initalize and add the buttons
        rookButton = new rookPromotionButton();
        knightButton = new knightPromotionButton();
        bishopButton = new bishopPromotionButton();
        queenButton = new queenPromotionButton();

        //add them to the world
        addObject(rookButton, 4,0);
        addObject(knightButton, 5,0);
        addObject(bishopButton, 6,0);
        addObject(queenButton, 7,0);
    }

    public static promotionObserver getInstance()
    {
        if (observer == null)
        {
            observer = new promotionObserver();
        }
        return observer;
    }

}
