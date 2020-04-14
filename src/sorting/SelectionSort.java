package sorting;

import setup.Implementation;
import setup.State;

import javax.swing.*;

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
        super.swap(anArrayOfInt, i, j);
        fireWhileSwapping();
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
            SwingUtilities.invokeLater(() -> setState(State.Done));
        }
    }
}