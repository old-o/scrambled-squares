package net.doepner.squares.model;

/**
 * Listens to square change events, e.g. moves or rotations
 */
public interface SquareChangeListener {

    void changeOccurred(int x, int y, Square before, Square after);
}
