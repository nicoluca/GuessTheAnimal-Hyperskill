package animals.domain.tree;

import java.util.ArrayList;
import java.util.List;

public class TreeStats {
    private int numberOfNodes = 0;
    private int numberOfLeaves = 0;
    private int maxDepth = 0;
    private int minLeafDepth = Integer.MAX_VALUE;
    private double averageLeafDepth = 0;

    public TreeStats(BinaryTree tree) {
        List<Integer> leafDepths = new ArrayList<>();
        traverseTree(tree.getRoot(), 0, leafDepths);
        this.averageLeafDepth = leafDepths.stream()
                .mapToInt(Integer::intValue)
                .average().orElse(0);
    }

    private void traverseTree(Node node, int depth, List<Integer> leafDepths) {
        if (node.isLeaf()) {
            this.numberOfLeaves++;
            updateMinLeafDepth(depth);
            leafDepths.add(depth);
        } else if (node != null) {
            this.numberOfNodes++;
            traverseTree(node.getYes(), depth + 1, leafDepths);
            traverseTree(node.getNo(), depth + 1, leafDepths);
        } else
            return;

        updateMaxDepth(depth);
    }

    private void updateMaxDepth(int depth) {
        if (depth > this.maxDepth)
            this.maxDepth = depth;
    }

    private void updateMinLeafDepth(int depth) {
        if (depth < this.minLeafDepth)
            this.minLeafDepth = depth;
    }

    public int getNumberOfNodes() {
        return numberOfNodes;
    }

    public int getNumberOfLeaves() {
        return numberOfLeaves;
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    public int getMinLeafDepth() {
        return minLeafDepth;
    }

    public double getAverageLeafDepth() {
        return averageLeafDepth;
    }
}
