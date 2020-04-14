package sorting;

import setup.Implementation;
import setup.State;

import javax.swing.*;


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
        super.swap(anArrayOfInt, i, j);
        fireWhileSwapping();
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
            SwingUtilities.invokeLater(() -> setState(State.Done));
        }
    }
}