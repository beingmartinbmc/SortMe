package sorting;

import setup.Implementation;
import setup.State;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

public class InsertionSort extends Implementation {
    public InsertionSort(int[] values) {
        super(values);
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
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    fireStateChanged();
                }
            });
        } catch (InterruptedException | InvocationTargetException exp) {
            System.out.println(exp);
        }
    }
    private class MyThread implements Runnable {
        @Override
        public void run() {
            int[] values = getValues();
            for (int i = 0; i < values.length; ++i) {
                for (int j = i - 1; j >= 0 && values[j] > values[j + 1]; --j,Sorting.count++) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException ex) {
                    }
                    swap(values, j, j + 1);

                }
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