package net.doepner.squares.model;

/**
 * Represents a 3x3 board of scrambled squares
 */
public class Board {

    private static final int DIM = 3;

    private final Square[][] squares = new Square[DIM][];

    private int pos = 0;

    private final SquareChangeMessenger messenger;

    public Board(SquareChangeMessenger messenger) {
        this.messenger = messenger;

        for (int i = 0; i < DIM; i++) {
            squares[i] = new Square[DIM];
        }
    }

    boolean isEmpty() {
        return pos == 0;
    }

    boolean isFull() {
        return pos == DIM * DIM;
    }

    public boolean fit(Square square) {
        if (isFull()) {
            throw new IllegalStateException("Board is full");
        }

        final int x = pos % DIM;
        final int y = pos / DIM;

        if (x > 0) {
            final Square left = squares[x - 1][y];
            if (left.getEdge(1).mismatches(square.getEdge(3))) {
                return false;
            }
        }
        if (y > 0) {
            final Square above = squares[x][y - 1];
            if (above.getEdge(2).mismatches(square.getEdge(0))) {
                return false;
            }
        }

        cardChange(x, y, square);
        pos++;

        return true;
    }

    public void removeLastCard() {
        if (isEmpty()) {
            throw new IllegalStateException("Board is empty");
        }

        pos--;
        cardChange(pos % DIM, pos / DIM, null);
    }

    private void cardChange(int x, int y, Square after) {
        final Square before = squares[x][y];
        if (before != null) {
            before.setUsed(false);
            before.reset();
        }
        squares[x][y] = after;
        if (after != null) {
            after.setUsed(true);
        }

        messenger.changeOccurred(x, y, before, after);
    }

    private static final String NEW_LINE =
            System.getProperty("line.separator");

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();

        for (int i = 0; i < pos; i++) {
            final int x = i % DIM;
            final int y = i / DIM;

            final Square square = squares[x][y];

            sb.append(square);
            sb.append(' ');

            if (x == 2) {
                sb.append(NEW_LINE);
            }
        }
        return sb.toString();
    }
}
