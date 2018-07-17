package frames;


import setup.Sorter;
import sorting.Sorting;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class MyPanel extends JPanel {

    private Sorter sorter;
    private ChangeHandler changeHandler;
    private int maxValue;

    MyPanel() {
        setBackground(Color.BLACK);
    }

    @Override
    public Dimension getPreferredSize() {
        return Toolkit.getDefaultToolkit().getScreenSize();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(new Color(255,215,0));
        g2d.setFont(new Font("Arial", 0, 18));
        g2d.drawString("Comparisons = " + Integer.toString(Sorting.count), 0, 25);
        int values[] = getSorter().getValues();
        int width = getWidth() - 1;
        int height = getHeight() - 1;
        int colWidth = Math.round((float) width / (float) values.length);
        int x = 0;
        Color fill = null;
        Color highlight = null;
        switch (getSorter().getState()) {
            case Sorting:
                fill = new Color(178,34,34);
                highlight = Color.WHITE;
                break;
            case Done:
                fill = new Color(0,128,0);
                break;
        }
        for (int index = 0; index < values.length; index++) {
            g2d.setColor(fill);
            int value = values[index];
            int colHeight = (int) ((float) height * ((float) value / (float) maxValue));
            g2d.fillRect(x, height - colHeight, colWidth - 1, colHeight);
            if (getSorter().isActiveIndex(index) && highlight != null) {
                g2d.setColor(highlight);
                g2d.fillRect(x, height - colHeight, colWidth - 1, colHeight);
            }
            x += colWidth;
        }
        g2d.dispose();
    }

    private Sorter getSorter() {
        return sorter;
    }

    void setSorter(Sorter value) {
        if (sorter != value) {
            if (sorter != null) {
                sorter.removeChangeListener(getChangeHandler());
            }
            sorter = value;
            if (sorter != null) {
                sorter.addChangeListener(getChangeHandler());
                maxValue = 0;
                for (int intValue : sorter.getValues()) {
                    maxValue = Math.max(maxValue, intValue);
                }
            }
            repaint();
        }
    }

    private ChangeHandler getChangeHandler() {
        if (changeHandler == null) {
            changeHandler = new ChangeHandler();
        }
        return changeHandler;
    }

    public class ChangeHandler implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent e) {
            repaint();
        }
    }
}
