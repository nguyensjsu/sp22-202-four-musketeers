import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;


//this class is the parent class to all of the promotion buttons
public abstract class PromotionButton extends Actor
{

    //0 = gray, 1 = white, 2 = black
    ArrayList<String> imgLocation;
    private boolean isWhite;
    private boolean isActive = false;
    private int newPieceXLocation;
    private int newPieceYLocation;
    private promotionObserver obs;

    public PromotionButton()
    {
        imgLocation = new ArrayList<>();
    }

    public PromotionButton(ArrayList<String> image)
    {
        imgLocation = image;
        this.setIcon(imgLocation.get(0));
    }

    public void act()
    {
        if(isActive)
        {
            this.addPiece();
            this.obs.closePromotion();
        }
    }

    private void setIcon(String image)
    {
        GreenfootImage iconImage = new GreenfootImage(image);
        iconImage.scale(50,50);

        setImage(iconImage);
    }

    public void activationPromotion(boolean color, int xLocation, int yLocation, promotionObserver chain)
    {
        isWhite = color;
        newPieceXLocation = xLocation;
        newPieceYLocation = yLocation;
        isActive = true;
        obs = chain;

        if(this.isWhite)
        {
            setIcon(imgLocation.get(1));
        }
        else
        {
            setIcon(imgLocation.get(2));
        }
    }

    private void addPiece()
    {
        Chessboard chessboard = (Chessboard) getWorld();
        ChessPiece promotion = this.getPiece();
        promotion.chessboard = chessboard;
        chessboard.addObject(promotion, newPieceXLocation, newPieceYLocation);
        promotion.move(newPieceXLocation, newPieceYLocation, false);
    }

    //pieces replacement goes here
    public abstract ChessPiece getPiece();

    public void deActivatePromotion()
    {
        isActive = false;
        this.setIcon(imgLocation.get(0));
    }

    protected boolean getColor()
    {
        return this.isWhite;
    }
}

/*
Dev notes:
1. Need to initalize the button to the proper color
2. activate the buttons and initalize the variable and set the proper color
3. set the pieces
4. deactivate the button and change color
5. let the other objects know to deactivate
*/