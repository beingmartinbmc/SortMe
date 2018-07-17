package sorting;

import setup.Implementation;
import setup.State;

import javax.swing.*;

public class MergeSort extends Implementation {
    public MergeSort(int[] values) {
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
            SwingUtilities.invokeAndWait(new Thread() {
                @Override
                public void run() {
                    fireStateChanged();
                }
            });
        } catch (Exception h) {
        }
    }
    private class MyThread implements Runnable {
        private void mergeSort(int[] anArrayOfInt, int l, int r) {
            int[][] B = new int[2][r+1];
            sortMergingly(anArrayOfInt, B, l, r);
        }

        private void sortMergingly(int[] anArrayOfInt, int[][] B, int l, int r) {
            if (l >= r)
                return;
            int last = (l+r)/2;
            sortMergingly(anArrayOfInt, B, l, last);
            sortMergingly(anArrayOfInt, B, last+1, r);
            merge(anArrayOfInt, B, l, last, r);
        }
        private void merge(int[] anArrayOfInt, int[][] B, int l, int q, int r) {
            for (int i=l;i<=q;i++) {
                B[0][i] = i;
                B[1][i] = i;
            }
            for (int i=r;i>q;i--) {
                B[0][i] = r+q+1-i;
                B[1][i] = r+q+1-i;
            }
            int i = l;
            int j = r;
            for (int k=l; k<r;k++) {
                int s = B[0][i];
                int t = B[0][j];
                if (anArrayOfInt[s]<=anArrayOfInt[t]) {
                    i++;
                } else {
                    s = t;
                    j--;
                }
                swap(anArrayOfInt, s, k);
                Sorting.count++;
                t = B[1][k];
                B[0][t] = s;
                B[1][s] = t;
            }
        }
        @Override
        public void run() {
            int[] values = getValues();
            try{mergeSort(values,0,values.length-1);}
            catch (Exception e){e.printStackTrace();}
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    setState(State.Done);
                }
            });
        }
    }
}