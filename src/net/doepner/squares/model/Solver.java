package net.doepner.squares.model;


/**
 * A scrambled squares puzzle solver
 */
public class Solver {

    private final Board board;
    private final Square[] squares;

    public Solver(SquareChangeMessenger messenger, Square... squares) {
        this.squares = squares;
        board = new Board(messenger);
    }

    public boolean solve() {
        if (board.isFull()) {
            return true;
        }
        for (Square square : squares) {
            if (square.isUnused()) {
                for (int i = 0; i < Square.EDGES; i++) {
                    if (board.fit(square)) {
                        if (solve()) {
                            return true;
                        } else {
                            board.removeLastCard();
                        }
                    } else {
                        square.rotate();
                    }
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        if (board.isFull()) {
            return board.toString();
        } else {
            return "No solution!";
        }
    }
}
