package net.doepner.squares.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Propagates each board change event, i.e. whenever a card changes position
 * or rotation
 */
public class SquareChangeMessenger implements SquareChangeListener {

    private final Set<SquareChangeListener> listeners =
            new HashSet<SquareChangeListener>();

    public void addListener(SquareChangeListener listener) {
        listeners.add(listener);
    }

    @Override
    public void changeOccurred(int x, int y, Square before, Square after) {
        for (SquareChangeListener listener : listeners) {
            listener.changeOccurred(x, y, before, after);
        }
    }
}
