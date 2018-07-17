package sorting;

import setup.Implementation;
import setup.State;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

public class SelectionSort extends Implementation {
    public SelectionSort(int[] values) {
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
            exp.printStackTrace();
        }
    }
    private class MyThread implements Runnable {
        private void selectionSort(int[] anArrayOfInt) {
            for (int i = 0; i < anArrayOfInt.length - 1; ++i) {
                for (int j = i + 1; j < anArrayOfInt.length; ++j)
                    if (anArrayOfInt[j] < anArrayOfInt[i]) {
                        swap(anArrayOfInt, i, j);
                        Sorting.count++;
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