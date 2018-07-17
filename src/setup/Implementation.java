package setup;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.ArrayList;
import java.util.List;

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
        if (listeners.size() > 0) {
            ChangeEvent evt = new ChangeEvent(this);
            for (ChangeListener listener : listeners) {
                listener.stateChanged(evt);
            }
        }
    }

    @Override
    public boolean isActiveIndex(int index) {
        return activeIndices.contains(index);
    }

    public void setActiveIndices(int lower, int upper) {
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
