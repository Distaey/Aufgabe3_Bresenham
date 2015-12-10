package gui;

import draw.Bresenham;
import draw.Draw;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tristan-PC on 06.12.2015.
 */
public class GUI extends JFrame {

    Map<Integer, Point> coords = new HashMap<>();

    Point previous;
    Point click = new Point(0, 0);

    public GUI() {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int) (screenSize.width*0.75), (int) (screenSize.height*0.75));

        setTitle("Bresenham Übung 3");

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel jPanel1 = new JPanel(new GridBagLayout());
        JPanel jPanel2 = new JPanel(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);

        Draw draw = new Draw(coords);
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.NORTHWEST;
        c.weightx = 1;
        c.weighty = 1;
        jPanel1.add(draw, c);

            JLabel xLabel = new JLabel("x");
            c.gridx = 1;
            c.gridy = 0;
            c.insets = new Insets(5, 5, 5, 5);
            jPanel2.add(xLabel, c);

            JLabel yLabel = new JLabel("y");
            c.gridx = 2;
            c.gridy = 0;
            jPanel2.add(yLabel, c);

            JTextArea xTextArea = new JTextArea();
            xTextArea.setPreferredSize(new Dimension(50, (int) (screenSize.height * 0.55)));
            xTextArea.setEditable(false);
            xTextArea.setBackground(Color.WHITE);
            c.gridx = 1;
            c.gridy = 1;
            xTextArea.append(click.getX() + "\n");
            jPanel2.add(xTextArea, c);

            JTextArea yTextArea = new JTextArea();
            yTextArea.setPreferredSize(new Dimension(50, (int) (screenSize.height * 0.55)));
            yTextArea.setEditable(false);
            yTextArea.setBackground(Color.WHITE);
            c.gridx = 2;
            c.gridy = 1;
            yTextArea.append(click.getY() + "\n");
            jPanel2.add(yTextArea, c);

            JButton bStart = new JButton("Start");
            c.gridx = 1;
            c.gridy = 2;
            jPanel2.add(bStart, c);

            JButton bReset = new JButton("Reset");
            c.gridx = 2;
            c.gridy = 2;
            jPanel2.add(bReset, c);

        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        mainPanel.add(jPanel1, BorderLayout.WEST);
        mainPanel.add(jPanel2, BorderLayout.EAST);

        draw.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                previous = click;
                click = e.getPoint();
                xTextArea.append(String.valueOf(click.getX()) + "\n");
                yTextArea.append(String.valueOf(click.getY()) + "\n");
                System.out.println("Punkt: " + e.getPoint());
                Bresenham bresenham = new Bresenham((int) previous.getX(), (int) previous.getY(), (int) click.getX(), (int) click.getY());
                coords = bresenham.getCoords();
                //draw.repaint(100, 100, 100, 100);
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });

        add(mainPanel);
        getContentPane().revalidate();

        setVisible(true);

    }

}