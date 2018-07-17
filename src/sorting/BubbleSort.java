package sorting;

import setup.Implementation;
import setup.State;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BubbleSort extends Implementation {

    private int outer = 0;
    private int inner = 0;
    public BubbleSort(int[] values) {
        super(values);
    }

    @Override
    public void sort() {
        setState(State.Sorting);
        outer = 0;
        inner = 1;
        Timer timer = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] values = getValues();
                inner++;
                if (inner >= values.length - outer) {
                    outer++;
                    inner = 1;
                }

                if (outer < values.length) {
                    if (values[inner - 1] > values[inner]) {
                        swap(values, inner - 1, inner);
                        Sorting.count++;
                    } else {
                        setActiveIndices(inner - 1, inner);
                    }
                } else {
                    ((Timer)e.getSource()).stop();
                    setState(State.Done);
                }
            }
        });
        timer.setRepeats(true);
        timer.start();
    }
}
