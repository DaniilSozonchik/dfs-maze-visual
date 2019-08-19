import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;

public class Maze{
    private final int N;
    private Graph M;
    public int startnode;
        
	public Maze(int N, int startnode) {
		
        if (N < 0) throw new IllegalArgumentException("Number of vertices in a row must be nonnegative");
        this.N = N;
        this.M= new Graph(N*N);
        this.startnode= startnode;
        buildMaze();
	}
	
    public Maze (In in) {
    	this.M = new Graph(in);
    	this.N= (int) Math.sqrt(M.V());
    	this.startnode=0;
    }

	
    /**
     * Adds the undirected edge v-w to the graph M.
     *
     * @param  v one vertex in the edge
     * @param  w the other vertex in the edge
     * @throws IllegalArgumentException unless both {@code 0 <= v < V} and {@code 0 <= w < V}
     */
    public void addEdge(int v, int w) {
		// TODO
        M.addEdge(v, w);
    }
    
    /**
     * Returns true if there is an edge between 'v' and 'w'
     * @param v
     * @param w
     * @return true or false
     */
    public boolean hasEdge( int v, int w){
		// TODO
        if(v == w){
            return true;
        }
        for(int edge : M.adj(v)){
            if(edge == w){
                return true;
            }
        }
        return false;
    }	
    
    /**
     * Builds a grid as a graph.
     * @return Graph G -- Basic grid on which the Maze is built
     */
    public Graph mazegrid() {
		// TODO
        Graph G = new Graph(N*N);
        //int sq = (int) Math.sqrt(N);

        //Adds A
        for(int i = 0; i < N*N; i = i + N){
            for(int j = 0; j < N - 1; j++){
                G.addEdge(i + j, i + j + 1);
            }
        }

        for(int i = 0; i < N; i++){
            for(int j = N; j < N*N; j = j + N){
                G.addEdge(i+j-N, i+j);
            }
        }

        return G;
    }
    
    private void buildMaze() {
		// TODO
        Graph maze = mazegrid();
        RandomDepthFirstPaths rdfp = new RandomDepthFirstPaths(maze, 0);
        //Tiefensuche
        rdfp.randomDFS(maze);
        //Jede Edge aus edgeTo, die du durch die Tiefensuche im Graphen gefunden hast, zu dem Graphen M vom Maze hinzufügen
        for(int i = 0; i < rdfp.edge().length; i++) {
            if (!hasEdge(i, rdfp.edge()[i])) {
                M.addEdge(i, rdfp.edge()[i]);
            }
        }
    }

    
    public List<Integer> findWay(int v, int w){
		// TODO

        DepthFirstPaths dfp = new DepthFirstPaths(M, w);
        return dfp.pathTo(v);

    }
    
    public Graph M() {
    	return M;
    }

    public static void main(String[] args) {
		// FOR TESTING

    }


}

