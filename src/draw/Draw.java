package draw;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tristan-PC on 06.12.2015.
 */
public class Draw extends JPanel {

    int drawWidth;
    int drawHeight;
    Map<Integer, Point> coords = new HashMap<>();

    public Draw(Map coords) {

        this.coords = coords;

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setPreferredSize(new Dimension((int) (screenSize.width*0.6), (int) (screenSize.height*0.65)));
        drawWidth = (int) (screenSize.width*0.6);
        drawHeight = (int) (screenSize.height*0.65);

        setVisible(true);

    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setBackground(Color.WHITE);
        g2.setColor(Color.BLACK);
        g2.drawRect(0, 0, drawWidth-1, drawHeight-1);
        //g2.drawRect((int) coords.get(0).getX(), (int) coords.get(0).getY(), 1, 1);

    }

    /*
    public void paint(Graphics g) {

        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setBackground(Color.WHITE);
        g2.setColor(Color.BLACK);
        g2.drawRect(0, 0, drawWidth, drawHeight);

    }
    */

}