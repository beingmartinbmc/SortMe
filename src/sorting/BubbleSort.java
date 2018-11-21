package sorting;

import setup.Implementation;
import setup.State;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

public class BubbleSort extends Implementation {
    public BubbleSort(int[] values) {
        super(values);
        Sorting.arrayAccesses=0;
        Sorting.count=0;
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
            SwingUtilities.invokeAndWait(new Thread() {
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
        private void bubbleSort(int[] anArrayOfInt){
            for (int i = 0; i < anArrayOfInt.length; ++i) {
                for (int j = 1; j < anArrayOfInt.length - i; ++j) {
                    Sorting.arrayAccesses++;
                    if (anArrayOfInt[j - 1] > anArrayOfInt[j]) {
                        swap(anArrayOfInt, j - 1, j);
                    }
                }
            }
        }
        @Override
        public void run() {
            int[] values = getValues();
            try{bubbleSort(values);}
            catch (Exception h){h.printStackTrace();}
            SwingUtilities.invokeLater(()-> fireStateChanged());
        }
    }
}