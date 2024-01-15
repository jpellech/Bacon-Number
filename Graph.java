import java.util.ArrayList;

public class Graph {

    private int order;
    private int size;
    private ArrayList<Vertex> vertices;

    /**
    * The `Graph` class represents an undirected graph that stores vertices and edges.
    */
    public Graph(){
        order = 0;
        size = 0;
        vertices = new ArrayList<Vertex>();
    }

    /**
     * Adds a vertex to the graph if it does not already exist.
     *
     * @param vertex The vertex to be added to the graph.
     */
    public void addVertex(Vertex vertex){
        for (Vertex v : vertices) {
            if (v.getName().equals(vertex.getName())) {
                System.out.println("Duplicate vertex found: " + vertex.getName());
                return;
            }
        }
    
        vertices.add(vertex);
        order++;
    }

    /**
     * Adds an edge between two vertices in the graph.
     *
     * @param source      The source vertex of the edge.
     * @param destination The destination vertex of the edge.
     * @param label       The label associated with the edge.
     */
    public void addEdge(Vertex source, Vertex destination, String label){
        ArrayList<Vertex> sourceAdjList = source.getAdjacents();
        ArrayList<Vertex> destinationAdjList = destination.getAdjacents();

      //  if (!sourceAdjList.contains(destination) && !destinationAdjList.contains(source) && !source.getName().equals(destination.getName())){
            sourceAdjList.add(destination);
            destinationAdjList.add(source);
            source.makeEdge(destination.getName(), label);
            destination.makeEdge(source.getName(), label);
            this.size++;
      //  }
    }

    /**
     * Checks if an edge exists between two vertices in the graph.
     *
     * @param v     The source vertex.
     * @param w     The destination vertex.
     * @param title The label associated with the edge.
     * @return True if the edge exists, false otherwise.
     */
    public boolean hasEdge(Vertex v, Vertex w, String title){
        if (v != null && v.getAdjacents() != null && !v.getAdjacents().contains(w)){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Retrieves the number of vertices in the graph.
     *
     * @return The order of the graph.
     */
    public int getOrder(){
        return this.order;
    }

    /**
     * Retrieves the number of edges in the graph.
     *
     * @return The size of the graph.
     */
    public int getSize(){
        return this.size;
    }

    /**
     * Finds and returns a vertex with a given name in the graph.
     *
     * @param name The name of the vertex to find.
     * @return The vertex with the specified name, or null if not found.
     */
    public Vertex findVertex(String name){
        for (Vertex v : vertices){
            if (v.getName().equals(name)){
                return v;
            }
        }
        return null;
    }

    /**
     * Checks if a vertex with a given name exists in the graph.
     *
     * @param name The name of the vertex to check.
     * @return True if the vertex exists, false otherwise.
     */
    public boolean contains(String name){
        if (findVertex(name) != null){
            return true;
        } else{
            return false;
        }
    }

    
}