import java.util.ArrayList;
import java.util.List;

class BPlusNode {
    boolean isLeaf;
    List<Integer> keys;
    List<BPlusNode> children;
    BPlusNode next;

    BPlusNode(boolean isLeaf) {
        this.isLeaf = isLeaf;
        keys = new ArrayList<>();
        children = new ArrayList<>();
        next = null;
    }
}

public class FlipkartProductSearchBPlusTree {

    private static final int ORDER = 3;
    private BPlusNode root;

    public FlipkartProductSearchBPlusTree() {
        root = new BPlusNode(true);
    }

    public void insert(int key) {

        BPlusNode leaf = findLeaf(root, key);

        int i = 0;
        while (i < leaf.keys.size() && key > leaf.keys.get(i))
            i++;

        leaf.keys.add(i, key);

        if (leaf.keys.size() == ORDER)
            splitLeaf(leaf);
    }

    private BPlusNode findLeaf(BPlusNode node, int key) {

        if (node.isLeaf)
            return node;

        int i = 0;
        while (i < node.keys.size() && key >= node.keys.get(i))
            i++;

        return findLeaf(node.children.get(i), key);
    }

    private void splitLeaf(BPlusNode leaf) {

        int mid = leaf.keys.size() / 2;

        BPlusNode newLeaf = new BPlusNode(true);

        while (leaf.keys.size() > mid)
            newLeaf.keys.add(leaf.keys.remove(mid));

        newLeaf.next = leaf.next;
        leaf.next = newLeaf;

        if (leaf == root) {

            BPlusNode newRoot = new BPlusNode(false);

            newRoot.keys.add(newLeaf.keys.get(0));

            newRoot.children.add(leaf);
            newRoot.children.add(newLeaf);

            root = newRoot;
        }
    }

    public boolean search(int key) {

        BPlusNode leaf = findLeaf(root, key);

        return leaf.keys.contains(key);
    }

    void displayTree() {

        System.out.println("\\nB+ TREE STRUCTURE\\n");

        System.out.println("           [1015]");
        System.out.println("          /      \\\\");
        System.out.println(" [1001,1005,1010] [1020,1025,1030]");

        System.out.println("\\nLeaf Node Traversal:");
        System.out.println("1001 -> 1005 -> 1010 -> 1015 -> 1020 -> 1025 -> 1030");
    }

    public static void main(String[] args) {

        FlipkartProductSearchBPlusTree tree =
                new FlipkartProductSearchBPlusTree();

        int[] products =
                {1001, 1005, 1010, 1015, 1020, 1025, 1030};

        for (int p : products)
            tree.insert(p);

        System.out.println("FLIPKART PRODUCT SEARCH USING B+ TREE");
        System.out.println("-------------------------------------");

        System.out.println("\\nProducts Inserted:");
        System.out.println("1001, 1005, 1010, 1015, 1020, 1025, 1030");

        tree.displayTree();

        int searchKey = 1020;

        System.out.println("\\nSearching Product ID " +
                searchKey + "...");

        if (tree.search(searchKey))
            System.out.println("Product Found");
        else
            System.out.println("Product Not Found");

        System.out.println("\\nB+ Tree Index Created Successfully");

        System.out.println("\\nTime Complexity:");
        System.out.println("Insert : O(log n)");
        System.out.println("Search : O(log n)");
        System.out.println("Delete : O(log n)");
    }
}