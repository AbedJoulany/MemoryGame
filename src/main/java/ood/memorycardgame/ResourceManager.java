package ood.memorycardgame;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class ResourceManager {
    private static ResourceManager instance;
    private final Map<String, Image> imageCache = new HashMap<>();

    private ResourceManager() {
        // Private constructor to prevent instantiation
    }

    public static ResourceManager getInstance() {
        if (instance == null) {
            instance = new ResourceManager();
        }
        return instance;
    }

    public Image getImage(String imagePath) {
        Image image = imageCache.get(imagePath);
        if (image == null) {
            try {
                image = new Image(getClass().getResourceAsStream(imagePath));
                imageCache.put(imagePath, image);
            } catch (Exception e) {
                // Handle the exception (e.g., log an error message)
                System.err.println("Failed to load image: " + imagePath);
                // You may also provide a default image here
            }
        }
        return image;
    }

    public void clearCache() {
        imageCache.clear();
    }
}

