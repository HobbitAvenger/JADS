import java.awt.*;
import java.util.Random;

public class World {

    static int worldWidth;
    static int worldHeight;

    static Random random;
    static final long seed = 2118;

    static int worldWidthInGrids;
    static int worldHeightInGrids;
    static int gridWidth;
    static int gridHeight;
    static int countryHouseCount;

    static CountryHouse[] countryHouseArray;

    static Town town;

    World(int windowWidth, int windowHeight) {
        worldWidth = windowWidth;
        worldHeight = windowHeight;

        worldWidthInGrids = 10;
        worldHeightInGrids = 10;

        countryHouseCount = worldWidthInGrids * worldHeightInGrids;
        gridWidth = worldWidth / worldWidthInGrids;
        gridHeight = worldHeight / worldHeightInGrids;

        random = new Random(seed);

        countryHouseArray = new CountryHouse[countryHouseCount];

        town = new Town();
    }

    void generateWorld() {
        // House Generation
        // Instantiation
        int k = 0;
        for (int i = 0; i < worldWidthInGrids; i++) {
            for (int j = 0; j < worldHeightInGrids; j++) {
                countryHouseArray[k] = new CountryHouse(
                        random.nextInt(i * gridWidth, (i + 1) * gridWidth),
                        random.nextInt(j * gridHeight, (j + 1) * gridHeight),
                        i, j, Util.wrappedIndex(Constants.countryHouseLandColors, k)
                );
                k++;
            }
        }

        // Create grids between houses
        //      Calculates midpoint between each house and the adjacent house
        //      in a specific direction (W, NW, N, NE, E, SE, S, SW)
        CountryHouse adjacentW;
        CountryHouse adjacentNW;
        CountryHouse adjacentN;
        CountryHouse adjacentNE;
        CountryHouse adjacentE;
        CountryHouse adjacentSE;
        CountryHouse adjacentS;
        CountryHouse adjacentSW;

        k = 0;
        for (int i = 0; i < worldWidthInGrids; i++) {
            for (int j = 0; j < worldHeightInGrids; j++) {
                adjacentW = getCountryHouseFromGridNmbr(i - 1, j);
                adjacentNW = getCountryHouseFromGridNmbr(i - 1, j - 1);
                adjacentN = getCountryHouseFromGridNmbr(i, j - 1);
                adjacentNE = getCountryHouseFromGridNmbr(i + 1, j - 1);
                adjacentE = getCountryHouseFromGridNmbr(i + 1, j);
                adjacentSE = getCountryHouseFromGridNmbr(i + 1, j + 1);
                adjacentS = getCountryHouseFromGridNmbr(i, j + 1);
                adjacentSW = getCountryHouseFromGridNmbr(i - 1, j + 1);

                // West
                if (adjacentW != null) {
                    countryHouseArray[k].borderX[0] = (int) Util.midpoint(countryHouseArray[k], adjacentW)[0];
                    countryHouseArray[k].borderY[0] = (int) Util.midpoint(countryHouseArray[k], adjacentW)[1];
                } else {
                    countryHouseArray[k].borderX[0] = 0;
                    countryHouseArray[k].borderY[0] = countryHouseArray[k].worldYPos;
                }
                // North
                if (adjacentN != null) {
                    countryHouseArray[k].borderX[2] = (int) Util.midpoint(countryHouseArray[k], adjacentN)[0];
                    countryHouseArray[k].borderY[2] = (int) Util.midpoint(countryHouseArray[k], adjacentN)[1];
                } else {
                    countryHouseArray[k].borderX[2] = countryHouseArray[k].worldXPos;
                    countryHouseArray[k].borderY[2] = 0;
                }
                // East
                if (adjacentE != null) {
                    countryHouseArray[k].borderX[4] = (int) Util.midpoint(countryHouseArray[k], adjacentE)[0];
                    countryHouseArray[k].borderY[4] = (int) Util.midpoint(countryHouseArray[k], adjacentE)[1];
                } else {
                    countryHouseArray[k].borderX[4] = worldWidth;
                    countryHouseArray[k].borderY[4] = countryHouseArray[k].worldYPos;
                }
                // South
                if (adjacentS != null) {
                    countryHouseArray[k].borderX[6] = (int) Util.midpoint(countryHouseArray[k], adjacentS)[0];
                    countryHouseArray[k].borderY[6] = (int) Util.midpoint(countryHouseArray[k], adjacentS)[1];
                } else {
                    countryHouseArray[k].borderX[6] = countryHouseArray[k].worldXPos;
                    countryHouseArray[k].borderY[6] = worldHeight;
                }

                // Oblique
                // North West
                if (adjacentN != null && adjacentNW != null && adjacentW != null) {
                    countryHouseArray[k].borderX[1] = (int) Util.midpoint4(
                            countryHouseArray[k], adjacentNW, adjacentW, adjacentN)[0];
                    countryHouseArray[k].borderY[1] = (int) Util.midpoint4(
                            countryHouseArray[k], adjacentNW, adjacentW, adjacentN)[1];
                } else {
                    countryHouseArray[k].borderX[1] = countryHouseArray[k].borderX[0];
                    countryHouseArray[k].borderY[1] = countryHouseArray[k].borderY[2];
                }
                // North East
                if (adjacentN != null && adjacentNE != null && adjacentE != null) {
                    countryHouseArray[k].borderX[3] = (int) Util.midpoint4(
                            countryHouseArray[k], adjacentNE, adjacentN, adjacentE)[0];
                    countryHouseArray[k].borderY[3] = (int) Util.midpoint4(
                            countryHouseArray[k], adjacentNE, adjacentN, adjacentE)[1];
                } else {
                    countryHouseArray[k].borderX[3] = countryHouseArray[k].borderX[4];
                    countryHouseArray[k].borderY[3] = countryHouseArray[k].borderY[2];
                }
                // South East
                if (adjacentS != null && adjacentSE != null && adjacentE != null) {
                    countryHouseArray[k].borderX[5] = (int) Util.midpoint4(
                            countryHouseArray[k], adjacentSE, adjacentE, adjacentS)[0];
                    countryHouseArray[k].borderY[5] = (int) Util.midpoint4(
                            countryHouseArray[k], adjacentSE, adjacentE, adjacentS)[1];
                } else {
                    countryHouseArray[k].borderX[5] = countryHouseArray[k].borderX[4];
                    countryHouseArray[k].borderY[5] = countryHouseArray[k].borderY[6];
                }
                // South West
                if (adjacentS != null && adjacentSW != null && adjacentW != null) {
                    countryHouseArray[k].borderX[7] = (int) Util.midpoint4(
                            countryHouseArray[k], adjacentSW, adjacentS, adjacentW)[0];
                    countryHouseArray[k].borderY[7] = (int) Util.midpoint4(
                            countryHouseArray[k], adjacentSW, adjacentS, adjacentW)[1];
                } else {
                    countryHouseArray[k].borderX[7] = countryHouseArray[k].borderX[0];
                    countryHouseArray[k].borderY[7] = countryHouseArray[k].borderY[6];
                }

                k++;
            }
        }

        // Town Generation
        town.generate();
    }

    void render(Graphics2D g2D) {
        for (CountryHouse house : countryHouseArray) {
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

    static CountryHouse getCountryHouseFromGridNmbr(int gridX, int gridY) {
        if (gridX < 0 | gridX > worldWidthInGrids - 1 | gridY < 0 | gridY > worldHeightInGrids - 1) {
            return null;
        } else {
            for (CountryHouse countryHouse : countryHouseArray) {
                if (gridX == countryHouse.gridX && gridY == countryHouse.gridY) {
                    return countryHouse;
                }
            }
        }
        return null;
    }
}