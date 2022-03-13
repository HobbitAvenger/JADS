import java.util.ConcurrentModificationException;

public class Town extends WorldObject {

    int minRadius;
    int maxRadius;
    int radius;

    Town() {
        minRadius = 25;
        maxRadius = 100;

        radius = World.random.nextInt(minRadius, maxRadius);
    }

    void generate() {
        worldXPos = 0;
        worldYPos = 0;
    }
}
