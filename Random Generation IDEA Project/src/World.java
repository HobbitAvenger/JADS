import java.awt.*;
import java.util.Random;

public class World {

    static int worldWidth;
    static int worldHeight;

    Random random;
    static long seed;

    static int townRadius;

    static int worldWidthInGrids;
    static int worldHeightInGrids;
    static int gridWidth;
    static int gridHeight;
    static int houseCount;

    House[] houseArray;

    World(int windowWidth, int windowHeight) {
        worldWidth = windowWidth;
        worldHeight = windowHeight;

        worldWidthInGrids = 10;
        worldHeightInGrids = 10;

        houseCount = worldWidthInGrids * worldHeightInGrids;
        gridWidth = worldWidth / worldWidthInGrids;
        gridHeight = worldHeight / worldHeightInGrids;

        houseArray = new House[houseCount];
        townRadius = 75;

        seed = 2118;
        random = new Random(seed);
    }

    void generateWorld() {
        // Creates houses
        int k = 0;
        for (int i = 0; i < worldWidthInGrids; i++) {
            for (int j = 0; j < worldHeightInGrids; j++) {
                houseArray[k] = new House(
                        random.nextInt(i * gridWidth, (i + 1) * gridWidth),
                        random.nextInt(j * gridHeight, (j + 1) * gridHeight),
                        i, j, Util.wrappedIndex(House.landColorArray, k)
                );
                k++;
            }
        }

        // Creates grids between houses
        //      Calculates midpoint between each house and the adjacent house
        //      in a specific direction (W, NW, N, NE, E, SE, S, SW)

        House adjacentW;
        House adjacentNW;
        House adjacentN;
        House adjacentNE;
        House adjacentE;
        House adjacentSE;
        House adjacentS;
        House adjacentSW;

        k = 0;
        for (int i = 0; i < worldWidthInGrids; i++) {
            for (int j = 0; j < worldHeightInGrids; j++) {
                adjacentW = getHouseFromGridNmbr(i - 1, j);
                adjacentNW = getHouseFromGridNmbr(i - 1, j - 1);
                adjacentN = getHouseFromGridNmbr(i, j - 1);
                adjacentNE = getHouseFromGridNmbr(i + 1, j - 1);
                adjacentE = getHouseFromGridNmbr(i + 1, j);
                adjacentSE = getHouseFromGridNmbr(i + 1, j + 1);
                adjacentS = getHouseFromGridNmbr(i, j + 1);
                adjacentSW = getHouseFromGridNmbr(i - 1, j + 1);

                // West
                if (adjacentW != null) {
                    houseArray[k].borderX[0] = (int) Util.midpoint(houseArray[k], adjacentW)[0];
                    houseArray[k].borderY[0] = (int) Util.midpoint(houseArray[k], adjacentW)[1];
                } else {
                    houseArray[k].borderX[0] = 0;
                    houseArray[k].borderY[0] = houseArray[k].worldYPos;
                }
                // North
                if (adjacentN != null) {
                    houseArray[k].borderX[2] = (int) Util.midpoint(houseArray[k], adjacentN)[0];
                    houseArray[k].borderY[2] = (int) Util.midpoint(houseArray[k], adjacentN)[1];
                } else {
                    houseArray[k].borderX[2] = houseArray[k].worldXPos;
                    houseArray[k].borderY[2] = 0;
                }
                // East
                if (adjacentE != null) {
                    houseArray[k].borderX[4] = (int) Util.midpoint(houseArray[k], adjacentE)[0];
                    houseArray[k].borderY[4] = (int) Util.midpoint(houseArray[k], adjacentE)[1];
                } else {
                    houseArray[k].borderX[4] = worldWidth;
                    houseArray[k].borderY[4] = houseArray[k].worldYPos;
                }
                // South
                if (adjacentS != null) {
                    houseArray[k].borderX[6] = (int) Util.midpoint(houseArray[k], adjacentS)[0];
                    houseArray[k].borderY[6] = (int) Util.midpoint(houseArray[k], adjacentS)[1];
                } else {
                    houseArray[k].borderX[6] = houseArray[k].worldXPos;
                    houseArray[k].borderY[6] = worldHeight;
                }

                // Oblique
                //      North West
                if (adjacentN != null && adjacentNW != null && adjacentW != null) {
                    houseArray[k].borderX[1] = (int) Util.midpoint4(houseArray[k], adjacentNW, adjacentW, adjacentN)[0];
                    houseArray[k].borderY[1] = (int) Util.midpoint4(houseArray[k], adjacentNW, adjacentW, adjacentN)[1];
                } else {
                    houseArray[k].borderX[1] = houseArray[k].borderX[0];
                    houseArray[k].borderY[1] = houseArray[k].borderY[2];
                }
                //      North East
                if (adjacentN != null && adjacentNE != null && adjacentE != null) {
                    houseArray[k].borderX[3] = (int) Util.midpoint4(houseArray[k], adjacentNE, adjacentN, adjacentE)[0];
                    houseArray[k].borderY[3] = (int) Util.midpoint4(houseArray[k], adjacentNE, adjacentN, adjacentE)[1];
                } else {
                    houseArray[k].borderX[3] = houseArray[k].borderX[4];
                    houseArray[k].borderY[3] = houseArray[k].borderY[2];
                }
                //      South East
                if (adjacentS != null && adjacentSE != null && adjacentE != null) {
                    houseArray[k].borderX[5] = (int) Util.midpoint4(houseArray[k], adjacentSE, adjacentE, adjacentS)[0];
                    houseArray[k].borderY[5] = (int) Util.midpoint4(houseArray[k], adjacentSE, adjacentE, adjacentS)[1];
                } else {
                    houseArray[k].borderX[5] = houseArray[k].borderX[4];
                    houseArray[k].borderY[5] = houseArray[k].borderY[6];
                }
                //      South West
                if (adjacentS != null && adjacentSW != null && adjacentW != null) {
                    houseArray[k].borderX[7] = (int) Util.midpoint4(houseArray[k], adjacentSW, adjacentS, adjacentW)[0];
                    houseArray[k].borderY[7] = (int) Util.midpoint4(houseArray[k], adjacentSW, adjacentS, adjacentW)[1];
                } else {
                    houseArray[k].borderX[7] = houseArray[k].borderX[0];
                    houseArray[k].borderY[7] = houseArray[k].borderY[6];
                }

                k++;
            }
        }
    }

    void render(Graphics2D g2D) {
        for (House house : houseArray) {
            if (house != null) {
                // Fills centers
                g2D.setColor(house.landColor);
                g2D.fillPolygon(house.borderX, house.borderY, house.borderX.length);
                // Draws borders
                g2D.setColor(Color.BLACK);
                g2D.drawPolygon(house.borderX, house.borderY, house.borderY.length);

                // Draws X at house location
                // Color already set to black from previous step in this case
                g2D.drawLine(
                        house.worldXPos - 3, house.worldYPos - 3,
                        house.worldXPos + 3, house.worldYPos + 3
                );
                g2D.drawLine(
                        house.worldXPos - 3, house.worldYPos + 3,
                        house.worldXPos + 3, house.worldYPos - 3
                );
            }
        }
    }

    House getHouseFromGridNmbr(int gridX, int gridY) {
        if (gridX < 0 | gridX > worldWidthInGrids - 1 | gridY < 0 | gridY > worldHeightInGrids - 1) {
            return null;
        } else {
            for (House house : houseArray) {
                if (gridX == house.gridX && gridY == house.gridY) {
                    return house;
                }
            }
        }
        return null;
    }
}