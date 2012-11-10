package net.doepner.squares;

import net.doepner.squares.model.Solver;
import net.doepner.squares.model.Square;
import net.doepner.squares.model.SquareChangeListener;
import net.doepner.squares.model.SquareChangeMessenger;

import static net.doepner.squares.model.Edge.head;
import static net.doepner.squares.model.Edge.tail;
import static net.doepner.squares.model.Item.A;
import static net.doepner.squares.model.Item.B;
import static net.doepner.squares.model.Item.C;
import static net.doepner.squares.model.Item.D;

/**
 * The main class that sets up a sample puzzle, invokes the solver
 * and writes some output to illustrate how the solver moves and rotates
 * the cards on the board to solve the puzzle
 */
public class Main {

    public static void main(String[] args) {
        final SquareChangeMessenger squareChangeMessenger = new SquareChangeMessenger();

        final Solver solver = new Solver(
                squareChangeMessenger,
                new Square(tail(D), head(B), tail(D), head(C)),
                new Square(tail(A), head(D), tail(A), tail(C)),
                new Square(head(B), head(C), tail(D), head(A)),
                new Square(head(D), head(C), tail(A), tail(B)),
                new Square(tail(B), head(B), head(C), head(A)),
                new Square(tail(B), head(D), head(A), head(C)),
                new Square(head(D), tail(C), tail(B), tail(A)),
                new Square(head(D), tail(A), head(B), tail(C)),
                new Square(tail(B), head(A), tail(D), head(C))
        );

        squareChangeMessenger.addListener(new SquareChangeListener() {
            public void changeOccurred(int x, int y,
                                       Square before, Square after) {
                final StringBuffer sb = new StringBuffer();
                sb.append(x);
                sb.append(", ");
                sb.append(y);
                sb.append(" : ");
                sb.append(before);
                sb.append(" -> ");
                sb.append(after);

                System.out.println(sb);
            }
        });

        solver.solve();

        System.out.println(solver);
    }
}
