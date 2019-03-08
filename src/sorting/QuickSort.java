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
        super.swap(anArrayOfInt, i, j);
        fireWhileSwapping();
    }
    private class MyThread implements Runnable {
        private int getRandom(int upper, int lower){
            return (int)(Math.random() * (upper - lower + 1)) + lower;
        }
        private int partition(int[] a, int start, int end, int pIndex){
            int pivot = a[pIndex];
            swap(a, pIndex, end);
            int i = start - 1;
            for(int j = start; j <= end - 1; j++){
                Sorting.arrayAccesses ++;
                if(a[j] < pivot){
                    i += 1;
                    swap(a, i, j);
                }
            }
            swap(a, i + 1, end);
            return i + 1;
        }
        private void qSort(int[] a, int l, int r){
            if(l < r){
                int pIndex = getRandom(l, r);
                int pivot = partition(a, l, r, pIndex);
                qSort(a, l, pivot - 1);
                qSort(a, pivot + 1, r);
            }
        }
        @Override
        public void run() {
            int[] values = getValues();
            qSort(values,0,values.length-1);
            SwingUtilities.invokeLater(() -> setState(State.Done));
        }
    }
}