package frames;


import setup.Sorter;
import setup.State;
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
        Graphics2D g2d = (Graphics2D) g;
        render(g2d);
        int[] values = getSorter().getValues();
        int width = getWidth() - 1;
        int height = getHeight() - 1;
        int colWidth = Math.round((float) width / (float) values.length);
        int x = 0;
        Color fill = null;
        Color highlight = null;
        State state = getSorter().getState();
        if (state == State.Sorting) {
            fill = new Color(0, 191, 255);
            highlight = new Color(128, 0, 128);
        } else if (state == State.Done) {
            fill = new Color(34, 139, 37);
        }
        colorBars(g2d, values, height, colWidth, x, fill, highlight);
        g2d.dispose();
    }

    private void colorBars(Graphics2D g2d, int[] values, int height, int colWidth, int x, Color fill, Color highlight) {
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
    }

    private void render(Graphics2D g2d) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Trebuchet MS", 0, 18));
        g2d.drawString("Comparisons = " + Sorting.count, 0, 25);
        g2d.drawString("Array accesses = " + Sorting.arrayAccesses, 250, 25);
    }

    private Sorter getSorter() {
        return sorter;
    }

    public void setSorter(Sorter value) {
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
