package sorting;

import setup.Implementation;
import setup.State;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class InsertionSort extends Implementation {
    public InsertionSort(int[] values) {
        super(values);
        Sorting.count = 0;
        Sorting.arrayAccesses = 0;
    }

    @Override
    public void sort() {
        setState(State.Sorting);
        new Thread(new MyThread()).start();
    }

    @Override
    public void swap(int[] anArrayOfInt, int i, int j) {
        setActiveIndices(i, j);
        int x = anArrayOfInt[i];
        anArrayOfInt[i] = anArrayOfInt[j];
        anArrayOfInt[j] = x;
        Sorting.count = Sorting.count + 1;
        try {
            java.util.Timer timer = new Timer();
            SwingUtilities.invokeAndWait(()-> {
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        fireStateChanged();
                    }
                }, 0);
            });
        } catch (Exception exp) {
            System.out.println(exp);
        }
    }
    private class MyThread implements Runnable {
        @Override
        public void run() {
            int[] values = getValues();
            for (int i = 0; i < values.length; ++i) {
                for (int j = i - 1; j >= 0 && values[j] > values[j + 1]; --j) {
                    swap(values, j, j + 1);
                    Sorting.arrayAccesses++;
                }
                Sorting.arrayAccesses++;
            }
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    setState(State.Done);
                }
            });
        }
    }
}