package ood.memorycardgame;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

// Card.java
public class Card extends ImageView {
    private final int id;
    private final String frontImagePath;
    private static final String backImagePath = "Images/card.jpg";


    Position positionToGrid;

    public void setPositionToGrid(Position positionToGrid) {
        this.positionToGrid = positionToGrid;
    }

    boolean isFlipped;

    public Card(int id, String frontImagePath) {
        this.id = id;
        this.frontImagePath = frontImagePath;
        boolean isFlipped = false;
        this.setImage(ResourceManager.getInstance().getImage(getImagePath()));
        positionToGrid = null;
    }

    public boolean isFlipped() {
        return isFlipped;
    }

    public void setFlipped(boolean flipped) {
        isFlipped = flipped;
    }

    void flip(){
        isFlipped = !isFlipped;
        this.setImage(ResourceManager.getInstance().getImage(getImagePath()));
    }

    public boolean isMatching(Card card) {
        return this.id == card.id;
    }

    public String getFrontImagePath() {
        return frontImagePath;
    }

    public String getImagePath() {
        if (isFlipped) {
            return frontImagePath;
        }
        else {
            return backImagePath;
        }
    }

    public Position getPositionToGrid() {
        return positionToGrid;
    }


// Getters and setters
}
