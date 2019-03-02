package frames;

import sorting.*;

import javax.swing.*;
import java.awt.*;

@Author(name="Ankit Sharma",date="July 05 2018")
public class Visualiser {
    private JFrame frame;
    public static String length;

    private Visualiser() {
        JPanel panel;
        JButton q, w, e, r, t, y;
        frame = new JFrame();
        frame.setSize(510, 390);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        q = new JButton("Bubble");
        w = new JButton("Selection");
        e = new JButton("Insertion");
        r = new JButton("Quick");
        t = new JButton("Merge");
        y = new JButton("Heap");

        q.setBounds(100, 50, 100, 50);
        w.setBounds(100, 150, 100, 50);
        e.setBounds(300, 50, 100, 50);
        r.setBounds(300, 150, 100, 50);
        t.setBounds(190, 250, 100, 50);

        q.addActionListener(v -> {
            Sorting.count = 0;
            MyPanel p = new MyPanel();
            Sorting.values = Sorting.returnArray();
            BubbleSort sorter = new BubbleSort(Sorting.values);
            p.setSorter(sorter);
            frame = returnFrame(p);
            sorter.sort();
        });

        w.addActionListener(v -> {
            Sorting.count = 0;
            MyPanel p = new MyPanel();
            Sorting.values = Sorting.returnArray();
            SelectionSort sorter = new SelectionSort(Sorting.values);
            p.setSorter(sorter);
            frame = returnFrame(p);
            sorter.sort();
        });

        e.addActionListener(v -> {
            Sorting.count = 0;
            MyPanel p = new MyPanel();
            Sorting.values = Sorting.returnArray();
            InsertionSort sorter = new InsertionSort(Sorting.values);
            p.setSorter(sorter);
            frame = returnFrame(p);
            sorter.sort();
        });

        r.addActionListener(v -> {
            Sorting.count = 0;
            MyPanel p = new MyPanel();
            Sorting.values = Sorting.returnArray();
            QuickSort sorter = new QuickSort(Sorting.values);
            p.setSorter(sorter);
            frame = returnFrame(p);
            sorter.sort();
        });

        t.addActionListener(v -> {
            Sorting.count = 0;
            MyPanel p = new MyPanel();
            Sorting.values = Sorting.returnArray();
            MergeSort sorter = new MergeSort(Sorting.values);
            p.setSorter(sorter);
            frame = returnFrame(p);
            sorter.sort();
        });

        panel.add(q);
        panel.add(w);
        panel.add(e);
        panel.add(r);
        panel.add(t);
        panel.add(y);
        panel.setBackground(new Color(33, 97, 140));
        frame.add(panel);
        frame.setVisible(true);
        JOptionPane.showMessageDialog(frame, "This is a sorting visualisation created by Ankit Sharma.\n");
        length = JOptionPane.showInputDialog(frame, "Enter number of array elements[10 - 700].\nDefault elements are 270");
        while (Integer.parseInt(length) > 700) {
            JOptionPane.showMessageDialog(frame, "Please choose the number in the given limit!");
            length = JOptionPane.showInputDialog(frame, "Enter number of array elements[10 - 700].\nDefault elements are 270");
        }
    }

    private JFrame returnFrame(JPanel panel){
        JFrame frame;
        frame = new JFrame("Sorting");
        frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        return frame;
    }
    public static void main(String ...s){
        new Visualiser();
    }
}
