package sorting;

import setup.Implementation;
import setup.State;

import javax.swing.*;

public class BubbleSort extends Implementation {

    public BubbleSort(int[] values) {
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
        private void bubbleSort(int[] arr) {
            int n = arr.length;
            int i, j;
            boolean swapped;
            for (i = 0; i < n - 1; i++) {
                swapped = false;
                for (j = 0; j < n - i - 1; j++) {
                    Sorting.arrayAccesses += 1;
                    if (arr[j] > arr[j + 1]) {
                        swap(arr, j, j + 1);
                        swapped = true;
                    }
                }
                if (!swapped)
                    break;
            }
        }

        @Override
        public void run() {
            int[] values = getValues();
            bubbleSort(values);
            SwingUtilities.invokeLater(() -> setState(State.Done));
        }
    }
}