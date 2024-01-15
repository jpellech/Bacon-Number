import java.util.ArrayList;
import java.util.HashMap;

public class Vertex{

    String name;
    private ArrayList<Vertex> adjacents;
    private HashMap<String, String> edges;

    /**
     * Constructs a new vertex with the given name.
     *
     * @param nameString The name of the vertex.
     */
    public Vertex(String nameString){
        this.name = nameString;
        adjacents = new ArrayList<>();
        edges = new HashMap<String, String>();

    }

    /**
     * Creates an edge between this vertex and a neighboring vertex with the given label.
     *
     * @param key   The name of the neighboring vertex.
     * @param value The label associated with the edge.
     */
    public void makeEdge(String key, String value){
        if (!edges.containsKey(key)) {
            edges.put(key, value);
        }
    }
    
    /**
     * Retrieves the label of the edge between this vertex and a neighboring vertex.
     *
     * @param key The name of the neighboring vertex.
     * @return The label of the edge, or null if no edge exists.
     */
    public String getEdge(String key){
        return edges.get(key);
    }

    /**
     * Retrieves the list of neighboring vertices.
     *
     * @return The list of neighboring vertices.
     */
    public ArrayList<Vertex> getAdjacents(){
        return adjacents;
    }

    /**
     * Retrieves the name of the vertex.
     *
     * @return The name of the vertex.
     */
    public String getName(){
        return this.name;
    }

    /**
     * Retrieves the number of neighboring vertices.
     *
     * @return The order of the vertex.
     */
    public int getOrder(){
        return adjacents.size();
    }

    /**
     * Adds a neighboring vertex to the list of adjacents.
     *
     * @param v The neighboring vertex to be added.
     */
    public void addNeighbor(Vertex v){
        if (v != null){
            adjacents.add(v);
        }
    }
}