package net.doepner.squares.non_oo;

import net.doepner.squares.model.Edge;

/**
 * An example of a non object-oriented data holder class
 */
class SquareData {

    final Edge[] edges;

    int rotation = 0;
    boolean used;

    public SquareData(Edge... edges) {
        this.edges = edges;
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < edges.length; i++) {
            sb.append(edges[(i + rotation) % edges.length]);
            sb.append('|');
        }
        return sb.toString();
    }
}
