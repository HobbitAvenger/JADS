import java.awt.*;

public class CountryHouse extends WorldObject {

    int gridX;
    int gridY;

    int landControlled;
    Color landColor;

    int[] borderX;
    int[] borderY;

    CountryHouse(int worldXPos, int worldYPos, int gridX, int gridY, Color landColor) {
        this.worldXPos = worldXPos;
        this.worldYPos = worldYPos;
        this.gridX = gridX;
        this.gridY = gridY;
        this.landColor = landColor;

        borderX = new int[8];
        borderY = new int[8];
    }
}