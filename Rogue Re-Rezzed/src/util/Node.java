package util;

/**
 * Part of A
 *
 *
 * @author Torri
 */
public class Node {

    public Vector2i tile;
    public Node parent;
    public double fCost, gCost, hCost;

    public Node(Vector2i tile, Node p, double gCost, double hCost) {
        this.tile = tile;
        this.parent = p;
        this.gCost = gCost;
        this.hCost = hCost;
        this.fCost = this.gCost + this.hCost;
    }
}
