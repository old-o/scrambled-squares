package net.doepner.squares.model;

/**
 * Represents an edge of a card in a scrambled squares puzzle
 */
public class Edge implements IEdge {

    public static Edge head(Item item) {
        return new Edge(item, true);
    }

    public static Edge tail(Item item) {
        return new Edge(item, false);
    }

    private final Item item;
    private final boolean head;

    private Edge(Item item, boolean head) {
        this.item = item;
        this.head = head;
    }

    @Override
    public Item getItem() {
        return item;
    }

    @Override
    public boolean isHead() {
        return head;
    }

    public boolean mismatches(Edge other) {
        return (item != other.item) || (head == other.head);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(item);
        sb.append('_');
        sb.append(head ? 'H' : 'T');
        return sb.toString();
    }
}
