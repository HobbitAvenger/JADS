import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Panel extends JPanel implements ActionListener {

    World world;

    static int windowWidth;
    static int windowHeight;

    Panel() {
        windowWidth = 800;
        windowHeight = 800;

        this.setPreferredSize(new Dimension(windowWidth, windowHeight));
        this.setBackground(new Color(255, 255, 255));

        world = new World(windowWidth, windowHeight);
        world.generateWorld();

        timer = new Timer(20, this);
        timer.start();
    }

    Timer timer;

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;

        world.render(g2D);
    }

    @Override
    public void actionPerformed(ActionEvent e) { repaint(); }
}