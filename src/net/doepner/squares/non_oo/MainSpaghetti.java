package net.doepner.squares.non_oo;

import static net.doepner.squares.model.Edge.head;
import static net.doepner.squares.model.Edge.tail;
import static net.doepner.squares.model.Item.A;
import static net.doepner.squares.model.Item.B;
import static net.doepner.squares.model.Item.C;
import static net.doepner.squares.model.Item.D;

/**
 * The whole thing in one class and few methods, to show how NOT to
 * structure code in an object-oriented fashion
 */
public class MainSpaghetti {

    private static final String NEW_LINE = System.getProperty("line.separator");

    private static final int DIM = 3;
    private static final SquareData[][] SQUARE = new SquareData[DIM][];

    private static int pos = 0;

    static {
        for (int i = 0; i < DIM; i++) {
            SQUARE[i] = new SquareData[DIM];
        }
    }

    public static void main(String[] args) {

        final SquareData[] cards = {
                new SquareData(tail(D), head(B), tail(D), head(C)),
                new SquareData(tail(A), head(D), tail(A), tail(C)),
                new SquareData(head(B), head(C), tail(D), head(A)),
                new SquareData(head(D), head(C), tail(A), tail(B)),
                new SquareData(tail(B), head(B), head(C), head(A)),
                new SquareData(tail(B), head(D), head(A), head(C)),
                new SquareData(head(D), tail(C), tail(B), tail(A)),
                new SquareData(head(D), tail(A), head(B), tail(C)),
                new SquareData(tail(B), head(A), tail(D), head(C))
        };

        solve(cards);

        if (pos == DIM * DIM) {
            final StringBuilder sb = new StringBuilder();

            for (int i = 0; i < pos; i++) {
                final int x = i % DIM;
                final int y = i / DIM;

                final SquareData card = SQUARE[x][y];

                sb.append(card);
                sb.append(' ');

                if (x == 2) {
                    sb.append(NEW_LINE);
                }
            }
            System.out.println(sb.toString());
        } else {
            System.out.println("No solution!");
        }

    }

    private static boolean solve(SquareData[] cards) {
        if (pos == DIM * DIM) {
            return true;
        }
        for (SquareData card : cards) {
            if (!card.used) {
                for (int i = 0; i < 4; i++) {
                    if (fit(card)) {
                        if (solve(cards)) {
                            return true;
                        } else {

                            if (pos == 0) {
                                throw new IllegalStateException("Board is empty");
                            }

                            pos--;
                            int x = pos % DIM;
                            int y = pos / DIM;

                            final SquareData before = SQUARE[x][y];
                            if (before != null) {
                                before.used = false;
                                before.rotation = 0;
                            }

                            SQUARE[x][y] = null;

                            print(card, x, y, before);
                        }
                    } else {
                        card.rotation = (card.rotation + 1) % card.edges.length;
                    }
                }
            }
        }
        return false;
    }

    private static boolean fit(SquareData card) {
        if (pos == DIM * DIM) {
            throw new IllegalStateException("Board is full");
        }

        final int x = pos % DIM;
        final int y = pos / DIM;

        if (x > 0) {
            final SquareData left = SQUARE[x - 1][y];
            if (left.edges[(1 + left.rotation) % left.edges.length].mismatches(card.edges[(3 + card.rotation) % card.edges.length])) {
                return false;
            }
        }
        if (y > 0) {
            final SquareData above = SQUARE[x][y - 1];
            if (above.edges[(2 + above.rotation) % above.edges.length].mismatches(
                    card.edges[(card.rotation) % card.edges.length])) {
                return false;
            }
        }

        final SquareData before = SQUARE[x][y];
        if (before != null) {
            before.used = false;
            before.rotation = 0;
        }
        SQUARE[x][y] = card;
        if (card != null) {
            card.used = true;
        }

        print(card, x, y, before);
        pos++;

        return true;
    }

    private static void print(SquareData card, int x, int y, SquareData before) {
        final StringBuffer sb = new StringBuffer();
        sb.append(x);
        sb.append(", ");
        sb.append(y);
        sb.append(" : ");
        sb.append(before);
        sb.append(" -> ");
        sb.append(card);

        System.out.println(sb);
    }

}
