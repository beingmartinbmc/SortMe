package sorting;

import setup.Implementation;
import setup.State;

import javax.swing.*;

public class HeapSort extends Implementation {
    public HeapSort(int[] values) {
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
        Sorting.count++;
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    fireStateChanged();
                }
            });
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }
    private class MyThread implements Runnable {
        private void sift(int[] anArrayOfInt, int l, int r) {
            if (r==l)
                return;
            int i = l, j = 2*l;
            int x = anArrayOfInt[i];
            if (j<r && anArrayOfInt[j]<anArrayOfInt[j+1])
                ++j;
            while (j<=r && x<=anArrayOfInt[j]) {
                swap(anArrayOfInt, i, j);
                Sorting.arrayAccesses++;
                i = j; j = 2*j;
                if (j<r && anArrayOfInt[j]<anArrayOfInt[j+1])
                    ++j;
            }
            Sorting.arrayAccesses++;
        }
        private void heapSort(int[] anArrayOfInt) {
            int r = anArrayOfInt.length-1;
            for (int l = anArrayOfInt.length/2 ; l>=0; --l)
                sift(anArrayOfInt, l, r);
            while (r > 0) {
                swap(anArrayOfInt, 0, r);
                sift(anArrayOfInt, 0, --r);
            }
        }
        @Override
        public void run() {
            int[] values = getValues();
            heapSort(values);
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    setState(State.Done);
                }
            });
        }
    }
}