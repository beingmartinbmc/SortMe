package setup;

import sorting.Sorting;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public abstract class Implementation implements Sorter {

    private List<ChangeListener> listeners;
    private int[] values;
    private State state = State.Waiting;
    private List<Integer> activeIndices;

    public Implementation(int[] values) {
        this.values = values;
        listeners = new ArrayList<>(25);
        activeIndices = new ArrayList<>(2);
    }

    @Override
    public State getState() {
        return state;
    }

    public void setState(State value) {
        if (value != state) {
            state = value;
            fireStateChanged();
        }
    }

    @Override
    public int[] getValues() {
        return values;
    }

    @Override
    public void addChangeListener(ChangeListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeChangeListener(ChangeListener listener) {
        listeners.remove(listener);
    }

    public void fireStateChanged() {
        if (!listeners.isEmpty()) {
            ChangeEvent evt = new ChangeEvent(this);
            listeners.forEach(listener -> listener.stateChanged(evt));
        }
    }

    protected void fireWhileSwapping() {
        Sorting.count += 1;
        try {
            Timer timer = new Timer();
            SwingUtilities.invokeAndWait(() -> timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    fireStateChanged();
                }
            }, 0));
        } catch (InterruptedException | InvocationTargetException exp) {
            exp.printStackTrace();
        }
    }

    @Override
    public boolean isActiveIndex(int index) {
        return activeIndices.contains(index);
    }

    private void setActiveIndices(int lower, int upper) {
        activeIndices.clear();
        activeIndices.add(lower);
        activeIndices.add(upper);
        fireStateChanged();
    }

    public void swap(int[] anArrayOfInt, int i, int j) {
        setActiveIndices(i, j);
        int x = anArrayOfInt[i];
        anArrayOfInt[i] = anArrayOfInt[j];
        anArrayOfInt[j] = x;
        fireStateChanged();
    }
}
