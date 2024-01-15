
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class BaconNumber {
    private Graph graph;
    private Set<String> names;
    private Set<String> titles;
    private Map<String, List<String>> nameToTitle;
    private Map<String, List<String>>titleToName;
    
    /**
     * Constructs a new `BaconNumber` instance with an empty graph and sets of names and titles.
     */
    public BaconNumber(){
        graph = new Graph();
        names = new HashSet<>();
        titles = new HashSet<>();
        nameToTitle = new HashMap<>();
        titleToName = new HashMap<>();
    }

    /**
     * Retrieves the mapping of actor names to the list of associated titles.
     *
     * @return A map of actor names to the list of titles.
     */
    public Map<String, List<String>> getNameToTitle(){
        return nameToTitle;
    }

    /**
     * Retrieves the set of movie titles.
     *
     * @return A set of movie titles.
     */
    public Set<String> getTitles(){
        return titles;
    }

    /**
     * Retrieves the set of actor names.
     *
     * @return A set of actor names.
     */
    public Set<String> getNames(){
        return names;
    }

    /**
     * Retrieves the mapping of movie titles to the list of associated actor names.
     *
     * @return A map of movie titles to the list of actor names.
     */
    public Map<String, List<String>> getTitleToName(){
        return titleToName;
    }

    /**
     * Adds an actor name to the set of names.
     *
     * @param str The actor name to be added.
     */
    public void addName(String str){
        names.add(str);
    }

    /**
     * Adds a movie title to the set of titles.
     *
     * @param str The movie title to be added.
     */
    public void addTitle(String str){
        titles.add(str);
    }

    /**
     * Retrieves the graph associated with the `BaconNumber` instance.
     *
     * @return The graph used for storing relationships between actors and movies.
     */
    public Graph getGraph(){
        return graph;
    }

    /**
     * Finds the shortest path from a source actor to a destination actor using BFS.
     *
     * @param center The source actor.
     * @param goal   The destination actor.
     * @return A list of actor names representing the shortest path from the source to the destination.
     */
    public List<String> findPath(Vertex center, Vertex goal) {
        Queue<Vertex> frontier = new LinkedList<>();
        Set<String> explored = new HashSet<>();
        Map<String, String> parentMap = new HashMap<>();  // To store the parent of each vertex
    
        frontier.offer(center);
        parentMap.put(center.getName(), null);  // Center has no parent
    
        while (!frontier.isEmpty()) {
            Vertex vertex = frontier.poll();
    
            if (vertex == null) {
                continue;
            }
    
            for (Vertex neighbor : vertex.getAdjacents()) {
                if (neighbor != null && !explored.contains(neighbor.getName()) && !frontier.contains(neighbor)) {
                    frontier.offer(neighbor);
                    parentMap.put(neighbor.getName(), vertex.getName());  // Set the parent of the neighbor
                }
            }
    
            explored.add(vertex.getName());
    
            if (vertex.getName().equals(goal.getName())) {
                // Reconstruct the path from the goal to the center
                return buildPath(parentMap, center.getName(), goal.getName());
            }
        }
    
        return new ArrayList<>();  // No path found
    }
    
    /**
     * Constructs a path from the source to the destination using the parentMap.
     *
     * @param parentMap A map representing the parent of each vertex in the path.
     * @param center    The source actor.
     * @param goal      The destination actor.
     * @return A list of actor names representing the path from the source to the destination.
     */
    private List<String> buildPath(Map<String, String> parentMap, String center, String goal) {
        List<String> path = new ArrayList<>();
        String current = goal;
    
        while (current != null && !current.equals(center)) {
            path.add(0, current);  // Add to the beginning of the list
            current = parentMap.get(current);
        }
    
        if (current != null && current.equals(center)) {
            path.add(0, current);  // Add the center to the path
        }
    
        return path;
    }
    

    /**
     * Builds the graph by adding vertices for actors and edges for co-appearances in movies.
     */
    public void baconData(){
        for (String actor : getNames()){
            Vertex vertex = new Vertex(actor);
            getGraph().addVertex(vertex);
        }

        for (String actor : names) {
            List<String> movies = nameToTitle.get(actor);
            if (movies != null) {
                for (String movie : movies) {
                    List<String> coActors = titleToName.get(movie);
                    if (coActors != null) {
                        for (String coActor : coActors) {
                            if (!coActor.equals(actor) && getGraph().findVertex(coActor) != null) {
                                graph.addEdge(getGraph().findVertex(actor), getGraph().findVertex(coActor), movie);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * The main method for the `BaconNumber` program, which reads IMDb data and calculates
     * the Bacon Number for specified actors.
     *
     * @param args Command line arguments: (1) source file (2) source actor (3) optional destination actor.
     */
    public static void main(String[] args) {
        if (args.length != 2 && args.length != 3) {
            System.out.println("Please enter (1) source file (2) source actor (3) optional destination actor");
            System.out.println("Note: If you dont input (3), Kevin Bacon will be the default destination actor.");
            System.exit(1);
        }
        else {
            String fileName = args[0];
            try (Scanner scanner = new Scanner(new File(fileName))) {

                BaconNumber baconator = new BaconNumber();

                while (scanner.hasNextLine()){
                    String line = scanner.nextLine();
                    String[] split = line.split("\t");
                    String name = split[0].trim();
                    String title = split[1].trim();

                    baconator.addName(name);
                    baconator.addTitle(title);

                    if (!baconator.getNameToTitle().containsKey(name)) {
                        baconator.getNameToTitle().put(name, new ArrayList<>());
                    }
                    baconator.getNameToTitle().get(name).add(title);
                
                    if (!baconator.getTitleToName().containsKey(title)) {
                        baconator.getTitleToName().put(title, new ArrayList<>());
                    }
                    baconator.getTitleToName().get(title).add(name);
                }

                baconator.baconData();

                /*
                Integer order = baconator.getGraph().getOrder();
                Integer size = baconator.getGraph().getSize() / 2;
                System.out.println("Order is: " + order);
                System.out.println("Size is: " + size);
                */

                List<String> path;
                path = new ArrayList<String>();
                if (args.length == 2){
                    Vertex goal = baconator.getGraph().findVertex(args[1]);
                    Vertex center = baconator.getGraph().findVertex("Kevin Bacon (I)");

                    if (goal != null && center != null) {
                        path = baconator.findPath(center, goal);
                    } 
                    else {
                        System.out.println("Invalid vertices provided.");
                        System.exit(1);
                    }
                }
                else{
                    Vertex goal = baconator.getGraph().findVertex(args[1]);
                    Vertex center = baconator.getGraph().findVertex(args[2]);
                    path = baconator.findPath(center, goal);
                }
                if (path.size() == 0){
                    System.out.println("No path found.");
                }
                else{
                    for (int i = 0; i < path.size() - 2; i++){
                        String name = path.get(i);
                        String nextName = path.get(i+1);
                        String edge = baconator.getGraph().findVertex(name).getEdge(nextName);
                        System.out.print(i + ". " + name + " -> " + edge + " -> " + nextName + " -> ");

                    }

                    String name = path.get(path.size()-2);
                    String nextName = path.get(path.size()-1);
                    String edge = baconator.getGraph().findVertex(name).getEdge(nextName);
                    System.out.print(path.size() + ". " + name + " -> " + edge + " -> " + nextName);

                    System.out.println("");
                    System.out.println("");
                    System.out.println(path.get(path.size()-1)+"'s Bacon Number is "+ path.size());
                }
            }
            catch (FileNotFoundException e) {
                System.out.println("ERROR: File not found.");
                System.exit(1);
            }
        }
    }
}
