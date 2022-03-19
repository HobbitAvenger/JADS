import java.awt.*;

public class Util {

    static void appendAtFirstNull(Object[] array, Object object) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) {
                array[i] = object;
                break;
            }
        }
    }

    static float distance2D(int posX1, int posY1, int posX2, int posY2) {
        return (float) Math.sqrt(Math.pow((posX1 + posX2), 2) + Math.pow((posY1 + posY2), 2));
    }

    static float[] midpoint(int posX1, int posY1, int posX2, int posY2) {
        return new float[]{(posX1 + posX2) / 2, (posY1 + posY2) / 2};
    }

    static float[] midpoint(float posX1, float posY1, float posX2, float posY2) {
        return new float[]{(posX1 + posX2) / 2, (posY1 + posY2) / 2};
    }

    static float[] midpoint(float[] posX, float[] posY) {
        return midpoint(posX[0], posX[1], posY[0], posY[1]);
    }

    static float[] midpoint(CountryHouse house1, CountryHouse house2) {
        return midpoint(house1.worldXPos, house1.worldYPos, house2.worldXPos, house2.worldYPos);
    }

    static float[] midpoint4(int posX1, int posY1, int posX2, int posY2, int posX3, int posY3, int posX4, int posY4) {
        return midpoint(
                midpoint(posX1, posY1, posX2, posY2),
                midpoint(posX3, posY3, posX4, posY4)
        );
    }

    static float[] midpoint4(CountryHouse house1, CountryHouse house2, CountryHouse house3, CountryHouse house4) {
        return midpoint(
                midpoint(house1.worldXPos, house1.worldYPos, house2.worldXPos, house2.worldYPos),
                midpoint(house3.worldXPos, house3.worldYPos, house4.worldXPos, house4.worldYPos)
        );
    }

    static Color wrappedIndex(Color[] array, int index) {
        if (index < array.length) {
            return array[index];
        } else {
            return array[index % array.length];
        }
    }
}