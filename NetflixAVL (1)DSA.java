class AVLNode {
    String movie;
    AVLNode left, right;
    int height;

    AVLNode(String movie) {
        this.movie = movie;
        this.height = 1;
    }
}

public class NetflixAVL {

    AVLNode root;

    int height(AVLNode node) {
        return (node == null) ? 0 : node.height;
    }

    int getBalance(AVLNode node) {
        return (node == null) ? 0 : height(node.left) - height(node.right);
    }

    AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    AVLNode insert(AVLNode node, String movie) {

        if (node == null)
            return new AVLNode(movie);

        if (movie.compareToIgnoreCase(node.movie) < 0)
            node.left = insert(node.left, movie);
        else if (movie.compareToIgnoreCase(node.movie) > 0)
            node.right = insert(node.right, movie);
        else
            return node;

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

        if (balance > 1 && movie.compareToIgnoreCase(node.left.movie) < 0)
            return rightRotate(node);

        if (balance < -1 && movie.compareToIgnoreCase(node.right.movie) > 0)
            return leftRotate(node);

        if (balance > 1 && movie.compareToIgnoreCase(node.left.movie) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && movie.compareToIgnoreCase(node.right.movie) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    boolean search(AVLNode node, String movie) {

        if (node == null)
            return false;

        if (movie.equalsIgnoreCase(node.movie))
            return true;

        if (movie.compareToIgnoreCase(node.movie) < 0)
            return search(node.left, movie);

        return search(node.right, movie);
    }

    public static void main(String[] args) {

        NetflixAVL netflix = new NetflixAVL();

        netflix.root = netflix.insert(netflix.root, "Avatar");
        netflix.root = netflix.insert(netflix.root, "Inception");
        netflix.root = netflix.insert(netflix.root, "Titanic");
        netflix.root = netflix.insert(netflix.root, "Joker");
        netflix.root = netflix.insert(netflix.root, "Interstellar");

        System.out.println("NETFLIX MOVIE MANAGEMENT USING AVL TREE");
        System.out.println("---------------------------------------\\n");

        System.out.println("MOVIES INSERTED:");
        System.out.println("Avatar, Inception, Titanic, Joker, Interstellar\\n");

        System.out.println("SEARCH OPERATION");
        System.out.println("----------------");
        System.out.println("Searching for Titanic...\\n");

        if (netflix.search(netflix.root, "Titanic"))
            System.out.println("Movie Found");
        else
            System.out.println("Movie Not Found");
    }
}
