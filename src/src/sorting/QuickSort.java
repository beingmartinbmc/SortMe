package sorting;

import setup.Implementation;
import setup.State;

import javax.swing.*;

public class QuickSort extends Implementation {
    public QuickSort(int[] values) {
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
            exp.getMessage();
        }
    }
    private class MyThread implements Runnable {
        private void qSort(int[] anArrayOfInt, int l, int r){
            if (l >= r)
                return;
            swap(anArrayOfInt, l, (l+r)/2);
            int last = l;
            for (int i=l+1; i<=r; ++i) {
                if (anArrayOfInt[i] < anArrayOfInt[l]) {
                    swap(anArrayOfInt, ++last, i);
                }
                Sorting.arrayAccesses++;
            }
            swap(anArrayOfInt, l, last);
            qSort(anArrayOfInt, l, last-1);
            qSort(anArrayOfInt, last+1, r);
        }
        @Override
        public void run() {
            int[] values = getValues();
            qSort(values,0,values.length-1);
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    setState(State.Done);
                }
            });
        }
    }
}