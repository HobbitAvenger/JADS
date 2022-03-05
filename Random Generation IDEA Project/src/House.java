import java.awt.*;

public class House {

    int worldXPos;
    int worldYPos;
    int gridX;
    int gridY;

    int landControlled;
    Color landColor;

    int[] borderX;
    int[] borderY;

    boolean fullyGenerated;

    static final Color[] landColorArray = new Color[]{
            Color.RED,
            Color.GREEN,
            Color.BLUE,
            Color.ORANGE,
            Color.YELLOW,
            Color.PINK,
            Color.WHITE,
            Color.GRAY,
            Color.DARK_GRAY,
            Color.LIGHT_GRAY,

            new Color(197, 0, 197),
            new Color(0, 161, 197),
            new Color(255, 86, 86),
            new Color(154, 255, 110),
            new Color(197, 141, 0)
    };

    House(int worldXPos, int worldYPos, int gridX, int gridY, Color landColor) {
        this.worldXPos = worldXPos;
        this.worldYPos = worldYPos;
        this.gridX = gridX;
        this.gridY = gridY;
        this.landColor = landColor;

        borderX = new int[8];
        borderY = new int[8];

        fullyGenerated = false;
    }
}