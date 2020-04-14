package sorting;

import setup.Implementation;
import setup.State;

import javax.swing.*;


public class MergeSort extends Implementation {

    public MergeSort(int[] values) {
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
        private void mergeSort(int[] anArrayOfInt, int l, int r) {
            int[][] B = new int[2][r + 1];
            sortMergingly(anArrayOfInt, B, l, r);
        }

        private void sortMergingly(int[] anArrayOfInt, int[][] B, int l, int r) {
            if (l >= r)
                return;
            int last = (l + r) / 2;
            sortMergingly(anArrayOfInt, B, l, last);
            sortMergingly(anArrayOfInt, B, last + 1, r);
            merge(anArrayOfInt, B, l, last, r);
        }

        private void merge(int[] anArrayOfInt, int[][] B, int l, int q, int r) {
            for (int i = l; i <= q; i++) {
                B[0][i] = i;
                B[1][i] = i;
            }
            for (int i = r; i > q; i--) {
                B[0][i] = r + q + 1 - i;
                B[1][i] = r + q + 1 - i;
            }
            int i = l;
            int j = r;
            for (int k = l; k < r; k++) {
                Sorting.arrayAccesses++;
                int s = B[0][i];
                int t = B[0][j];
                if (anArrayOfInt[s] <= anArrayOfInt[t]) {
                    i++;
                } else {
                    s = t;
                    j--;
                }
                swap(anArrayOfInt, s, k);
                Sorting.arrayAccesses++;
                t = B[1][k];
                B[0][t] = s;
                B[1][s] = t;
            }
        }

        @Override
        public void run() {
            int[] values = getValues();
            try {
                mergeSort(values, 0, values.length - 1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            SwingUtilities.invokeLater(() -> setState(State.Done));
        }
    }
}