package sorting;

import setup.Implementation;
import setup.State;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Timer;
import java.util.TimerTask;

public class SelectionSort extends Implementation {
    public SelectionSort(int[] values) {
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
        Sorting.count += 1;
        try {
            Timer timer = new Timer();
            SwingUtilities.invokeAndWait(()->{
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        fireStateChanged();
                    }
                }, 2);
            });
        } catch (InterruptedException | InvocationTargetException exp) {
            exp.printStackTrace();
        }
    }
    private class MyThread implements Runnable {
        private void selectionSort(int[] anArrayOfInt) {
            for (int i = 0; i < anArrayOfInt.length - 1; ++i) {
                for (int j = i + 1; j < anArrayOfInt.length; ++j) {
                    if (anArrayOfInt[j] < anArrayOfInt[i]) {
                        swap(anArrayOfInt, i, j);
                    }
                    Sorting.arrayAccesses++;
                }
            }
        }
        @Override
        public void run() {
            int[] values = getValues();
            selectionSort(values);
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    setState(State.Done);
                }
            });
        }
    }
}