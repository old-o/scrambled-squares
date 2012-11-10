package net.doepner.squares.model;

/**
 * Represents a card in a scrambled squares puzzle
 */
public class Square {

    public static final int EDGES = 4;

    private final Edge[] edges = new Edge[EDGES];

    private int rotation = 0;
    private boolean used;

    public Square(Edge north, Edge east, Edge south, Edge west) {
        edges[0] = north;
        edges[1] = east;
        edges[2] = south;
        edges[3] = west;
    }

    public void rotate() {
        rotation = (rotation + 1) % edges.length;
    }

    void reset() {
        rotation = 0;
    }

    Edge getEdge(int i) {
        return edges[(i + rotation) % edges.length];
    }

    public boolean isUnused() {
        return !used;
    }

    void setUsed(boolean used) {
        this.used = used;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < edges.length; i++) {
            sb.append(getEdge(i));
            sb.append('|');
        }
        return sb.toString();
    }
}
