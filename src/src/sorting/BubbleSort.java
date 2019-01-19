package sorting;

import setup.Implementation;
import setup.State;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Timer;
import java.util.TimerTask;

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
        setActiveIndices(i, j);
        int x = anArrayOfInt[i];
        anArrayOfInt[i] = anArrayOfInt[j];
        anArrayOfInt[j] = x;
        Sorting.count += 1;
        try {
            Timer timer = new Timer();
            SwingUtilities.invokeAndWait(()->{
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        fireStateChanged();
                    }
                }, 2);
            });
        } catch (InterruptedException | InvocationTargetException exp) {
            exp.printStackTrace();
        }
    }
    private class MyThread implements Runnable {
        private void bubbleSort(int[] arr) {
            int n = arr.length;
            int i, j, temp;
            boolean swapped;
            for (i = 0; i < n - 1; i++) {
                swapped = false;
                for (j = 0; j < n - i - 1; j++) {
                    Sorting.arrayAccesses += 1;
                    if (arr[j] > arr[j + 1]) {
                        swap(arr, j, j+1);
                        swapped = true;
                    }
                }
                if (!swapped)
                    break;
            }
        }
        //        private void BubbleSort(int[] anArrayOfInt) {
//            for (int i = 0; i < anArrayOfInt.length - 1; ++i) {
//                for (int j = i + 1; j < anArrayOfInt.length; ++j) {
//                    if (anArrayOfInt[j] < anArrayOfInt[i]) {
//                        swap(anArrayOfInt, i, j);
//                    }
//                    Sorting.arrayAccesses++;
//                }
//            }
//        }
        @Override
        public void run() {
            int[] values = getValues();
            bubbleSort(values);
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    setState(State.Done);
                }
            });
        }
    }
}