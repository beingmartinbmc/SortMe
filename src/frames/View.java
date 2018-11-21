package frames;

import sorting.*;

import javax.swing.*;
import java.awt.*;

@Author(name="Ankit Sharma",date="July 05 2018")
public class View {
    private static JFrame frame;
    private static JPanel panel;
    private static JButton q,w,e,r,t,y;
    View(){
        frame=new JFrame();
        frame.setSize(510,500);
        frame.setDefaultCloseOperation(3);
        frame.setLocationRelativeTo(null);

        panel=new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        q=new JButton("Bubble");
        w=new JButton("Selection");
        e=new JButton("Insertion");
        r=new JButton("Quick");
        t=new JButton("Merge");
        y=new JButton("Heap");

        q.setBounds(100,100,100,50);
        w.setBounds(100,200,100,50);
        e.setBounds(300,100,100,50);
        r.setBounds(300,200,100,50);
        t.setBounds(100,300,100,50);
        y.setBounds(300,300,100,50);

        q.addActionListener(e->{
            Sorting.count=0;
            MyPanel p=new MyPanel();
            Sorting.values=Sorting.returnArray();
            BubbleSort sorter = new BubbleSort(Sorting.values);
            p.setSorter(sorter);
            frame = returnFrame(p);
            sorter.sort();
        });

        w.addActionListener(e->{
            Sorting.count=0;
            MyPanel p=new MyPanel();
            Sorting.values=Sorting.returnArray();
            SelectionSort sorter = new SelectionSort(Sorting.values);
            p.setSorter(sorter);
            frame = returnFrame(p);
            sorter.sort();
        });

        e.addActionListener(e->{
            Sorting.count=0;
            MyPanel p=new MyPanel();
            Sorting.values=Sorting.returnArray();
            InsertionSort sorter = new InsertionSort(Sorting.values);
            p.setSorter(sorter);
            frame = returnFrame(p);
            sorter.sort();
        });

        r.addActionListener(e->{
            Sorting.count=0;
            MyPanel p=new MyPanel();
            Sorting.values=Sorting.returnArray();
            QuickSort sorter = new QuickSort(Sorting.values);
            p.setSorter(sorter);
            frame = returnFrame(p);
            sorter.sort();
        });

        t.addActionListener(e->{
            Sorting.count=0;
            MyPanel p=new MyPanel();
            Sorting.values=Sorting.returnArray();
            MergeSort sorter = new MergeSort(Sorting.values);
            p.setSorter(sorter);
            frame = returnFrame(p);
            sorter.sort();
        });

        y.addActionListener(e->{
            Sorting.count=0;
            MyPanel p=new MyPanel();
            Sorting.values=Sorting.returnArray();
            HeapSort sorter = new HeapSort(Sorting.values);
            p.setSorter(sorter);
            frame = returnFrame(p);
            sorter.sort();
        });

        panel.add(q);panel.add(w);panel.add(e);panel.add(r);
        panel.add(t);panel.add(y);
        frame.add(panel);
        frame.setVisible(true);
        JOptionPane.showMessageDialog(frame,"This is a sorting visualisation created by Ankit Sharma.\n");
    }
    private JFrame returnFrame(JPanel panel){
        JFrame frame;
        frame = new JFrame("Sorting");
        frame.setDefaultCloseOperation(2);
        frame.setLayout(new BorderLayout());
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        return frame;
    }
    public static void main(String ...s){
        new View();
    }
}
