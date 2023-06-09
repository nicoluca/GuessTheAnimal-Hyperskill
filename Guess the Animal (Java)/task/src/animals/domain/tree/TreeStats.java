package animals.domain.tree;

import java.util.ArrayList;
import java.util.List;

public class TreeStats {
    private int numberOfNodes = 0;
    private int numberOfLeaves = 0;
    private int maxDepth = 0;
    private int minLeafDepth = Integer.MAX_VALUE;
    private double averageLeafDepth;

    public TreeStats(BinaryTree tree) {
        List<Integer> leafDepths = new ArrayList<>();
        traverseTreeDFS(tree.getRoot(), 0, leafDepths);
        double average = leafDepths.stream()
                .mapToInt(Integer::intValue)
                .average().orElse(0);
        this.averageLeafDepth = round(average, 1);
    }

    // https://stackoverflow.com/a/22186845/11292952
    private static double round (double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }

    private void traverseTreeDFS(Node node, int depth, List<Integer> leafDepths) {
        if (node.isLeaf()) {
            this.numberOfLeaves++;
            updateMinLeafDepth(depth);
            leafDepths.add(depth);
        } else {
            this.numberOfNodes++;
            traverseTreeDFS(node.getYes(), depth + 1, leafDepths);
            traverseTreeDFS(node.getNo(), depth + 1, leafDepths);
        }

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
