package util;

import dungeon.Level;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Torri
 */
public class Astar {

    private final Comparator<Node> nodeSort = new Comparator<Node>() {
        @Override
        public int compare(Node n0, Node n1) {
            if (n1.fCost < n0.fCost) {
                return +1;
            }
            if (n1.fCost > n0.fCost) {
                return -1;
            }
            return 0;
        }
    };
    private int x;
    private int y;
    private int xi;
    private int yi;
    private Vector2i a;
    private double gCost;
    private double hCost;
    private Node node;

    public List<Node> findPath(Vector2i start, Vector2i goal, Level l, int followrange) {
        List<Node> openList = new ArrayList<>();
        List<Node> closedList = new ArrayList<>();
        Node current = new Node(start, null, 0, getDist(start, goal));
        openList.add(current);
        do {
            Collections.sort(openList, nodeSort);
            if (openList.size() > followrange) {
                return null;
            }
            current = openList.get(0);
            if (current.tile.equals(goal)) {
                List<Node> path = new ArrayList<>();
                path.add(current);
                while (current.parent != null) {
                    current = current.parent;
                    path.add(current);
                }
                openList.clear();
                closedList.clear();
                return path;
            }
            openList.remove(current);
            closedList.add(current);
            for (int i = 0; i < 9; i++) {
                if (i == 0 || i == 2 || i == 4 || i == 6 || i == 8) {
                    continue;
                }
                x = current.tile.getX();
                y = current.tile.getY();
                xi = (i % 3) - 1;
                yi = (i / 3) - 1;
                if (x + xi < 0 || y + yi < 0) {
                    continue;
                }
                if (x + xi >= l.board.length || y + yi >= l.board.length || !l.board[x + xi][y + yi]) {
                    continue;
                }
                a = new Vector2i(x + xi, y + yi);
                gCost = current.gCost + getDist(current.tile, a);
                hCost = getDist(a, goal);
                node = new Node(a, current, gCost, hCost);
                if (vecInList(closedList, a) && gCost >= current.gCost) {
                    continue;
                }
                if (!vecInList(openList, a) || gCost < current.gCost) {
                    openList.add(node);
                }
            }
        } while (openList.size() > 0);
        closedList.clear();
        return null;
    }

    private boolean vecInList(List<Node> list, Vector2i v) {
        for (Node n : list) {
            if (n.tile.equals(v)) {
                return true;
            }
        }
        return false;
    }

    private double getDist(Vector2i v1, Vector2i v2) {
        double dx = v1.getX() - v2.getY();
        double dy = v1.getY() - v2.getY();
        return Math.sqrt((dx * dx) + (dy * dy));
    }
}
