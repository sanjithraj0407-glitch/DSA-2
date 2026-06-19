// Bellman-Ford Algorithm
public class BellmanFord {

    static class Edge {
        int src, dest, weight;

        Edge(int s, int d, int w) {
            src = s;
            dest = d;
            weight = w;
        }
    }

    public static void main(String[] args) {

        String[] vertices = {"MJC", "JNTV", "KMR", "Ameer", "HUF", "HBT"};

        Edge[] edges = {
            new Edge(0, 1, 4),   // MJC -> JNTV
            new Edge(0, 3, 5),   // MJC -> Ameer
            new Edge(1, 2, 3),   // JNTV -> KMR
            new Edge(3, 4, 3),   // Ameer -> HUF
            new Edge(2, 4, 1),   // KMR -> HUF
            new Edge(3, 5, 5),   // Ameer -> HBT
            new Edge(4, 5, 2)    // HUF -> HBT
        };

        int V = vertices.length;
        int[] dist = new int[V];

        for (int i = 0; i < V; i++)
            dist[i] = Integer.MAX_VALUE;

        dist[0] = 0; // Source = MJC

        // Relax all edges V-1 times
        for (int i = 1; i < V; i++) {
            for (Edge e : edges) {
                if (dist[e.src] != Integer.MAX_VALUE &&
                    dist[e.src] + e.weight < dist[e.dest]) {
                    dist[e.dest] = dist[e.src] + e.weight;
                }
            }
        }

        // Negative cycle check
        boolean negativeCycle = false;
        for (Edge e : edges) {
            if (dist[e.src] != Integer.MAX_VALUE &&
                dist[e.src] + e.weight < dist[e.dest]) {
                negativeCycle = true;
                break;
            }
        }

        System.out.println("Source Vertex : MJC");
        System.out.println("Shortest Path Computation Completed Successfully\n");

        System.out.println("Vertex\tDistance from Source");
        for (int i = 0; i < V; i++) {
            System.out.println(vertices[i] + "\t" + dist[i]);
        }

        System.out.println("\nNegative Cycle Detected : " +
                (negativeCycle ? "YES" : "NO"));
        System.out.println("Status : PASS");
    }
}
