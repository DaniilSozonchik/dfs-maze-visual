import java.util.*;

public class RandomDepthFirstPaths {
  	private boolean[] marked;    // marked[v] = is there an s-v path?
    private int[] edgeTo;        // edgeTo[v] = last edge on s-v path
    private final int s;         // source vertex

    public RandomDepthFirstPaths(Graph G, int s) {
        this.s = s;
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        validateVertex(s);
    }
	    
	public void randomDFS(Graph G) {
		randomDFS(G,s);
	}
	    
    // depth first search from v
    private void randomDFS(Graph G, int v) {
		// TODO
        Random rand = new Random();
        marked[v] = true;
        LinkedList<Integer> random = new LinkedList<>();

        for(int i = 0; i < G.adj(v).size(); i++) {
            random.add(i);
        }

        while(random.size() != 0){
            int randIndex = rand.nextInt(random.size());
            int tmp = random.get(randIndex);
            random.remove(randIndex);
            int w = G.adj(v).get(tmp);
            if (!marked[w]) {
                edgeTo[w] = v;
                randomDFS(G, w);
            }
        }
    }
    
    public void randomNonrecursiveDFS(Graph G) {
		// TODO
    }

    /**
     * Is there a path between the source vertex {@code s} and vertex {@code v}?
     * @param v the vertex
     * @return {@code true} if there is a path, {@code false} otherwise
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public boolean hasPathTo(int v) {
        validateVertex(v);
        return marked[v];
    }

    /**
     * Returns a path between the source vertex {@code s} and vertex {@code v}, or
     * {@code null} if no such path.
     * @param  v the vertex
     * @return the sequence of vertices on a path between the source vertex
     *         {@code s} and vertex {@code v}, as an Iterable
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     * 
     * This method is different compared to the original one.
     */
    public List<Integer> pathTo(int v) {
		// TODO: Kopieren Sie diese Methode aus DepthFirstPaths Klasse
        validateVertex(v);

        if (!hasPathTo(v)) {
            return null;
        }
        Stack<Integer> path = new Stack<>();
        for (int x = v; x != s; x = edgeTo[x]){
            path.push(x);
        }
        path.push(s);
        return path;
    }
    
    public int [] edge() {
    	return edgeTo;
    }  

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

}

