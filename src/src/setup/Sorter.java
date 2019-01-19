package setup;

import javax.swing.event.ChangeListener;

public interface Sorter {

    void addChangeListener(ChangeListener listener);
    void removeChangeListener(ChangeListener listener);
    int[] getValues();
    void sort();
    State getState();
    boolean isActiveIndex(int index);
}