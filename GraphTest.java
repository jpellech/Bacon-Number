import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

public class GraphTest {
    @Test
    void testAddEdge() {
        Graph graph = new Graph();
        Vertex v1 = new Vertex("1");
        Vertex v2 = new Vertex("2");

        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addEdge(v1, v2, null);
        assertEquals(v1.getOrder(), 1);
        assertEquals(v2.getOrder(), 1);
        Vertex v3 = new Vertex("3");
        graph.addVertex(v3);
        graph.addEdge(v3, v2, null);
        assertEquals(v2.getOrder(), 2);
        assertEquals(v3.getOrder(), 1);

    }

    @Test
    void testAddVertex() {
        Graph graph = new Graph();
        Vertex v1 = new Vertex("1");
        Vertex v2 = new Vertex("2");

        graph.addVertex(v1);
        assertEquals(graph.getOrder(), 1);
        graph.addVertex(v2);
        assertEquals(graph.getOrder(), 2);
        assertNotNull(graph.findVertex("1"));
        assertNotNull(graph.findVertex("2"));
    }

    @Test
    void testContains() {
        Graph graph = new Graph();
        Vertex v1 = new Vertex("1");
        Vertex v2 = new Vertex("2");

        graph.addVertex(v1);
        graph.addVertex(v2);

        assertTrue(graph.contains("1"));
        assertFalse(graph.contains("pickles"));
    }

    @Test
    void testFindVertex() {
        Graph graph = new Graph();
        Vertex v1 = new Vertex("1");
        Vertex v2 = new Vertex("2");

        graph.addVertex(v1);
        assertEquals(v1, graph.findVertex("1"));
    }

    @Test
    void testGetOrder() {
        Graph graph = new Graph();
        Vertex v1 = new Vertex("1");
        Vertex v2 = new Vertex("2");

        assertEquals(graph.getOrder(), 0);

        graph.addVertex(v1);
        graph.addVertex(v2);

         assertEquals(graph.getOrder(), 2);
    }

    @Test
    void testGetSize() {
        Graph graph = new Graph();
        Vertex v1 = new Vertex("1");
        Vertex v2 = new Vertex("2");

        graph.addVertex(v1);
        graph.addVertex(v2);

        assertEquals(graph.getSize(), 0);

        graph.addEdge(v1, v2, null);

        assertEquals(graph.getSize(), 1);
    }
    public void testHasEdge() {
        Graph graph = new Graph();
        Vertex vertexA = new Vertex("A");
        Vertex vertexB = new Vertex("B");
        Vertex vertexC = new Vertex("C");
        String label = "EdgeLabel";

        // Case 1: No edge exists
        graph.addVertex(vertexA);
        graph.addVertex(vertexB);
        assertFalse(graph.hasEdge(vertexA, vertexB, label));

        // Case 2: Edge exists
        graph.addEdge(vertexA, vertexB, label);
        assertTrue(graph.hasEdge(vertexA, vertexB, label));

        // Case 3: Edge with different label
        assertFalse(graph.hasEdge(vertexA, vertexB, "DifferentLabel"));

        // Case 4: Edge with different vertices
        assertFalse(graph.hasEdge(vertexA, vertexC, label));

        // Case 5: Edge with null vertex
        assertFalse(graph.hasEdge(vertexA, null, label));

        // Case 6: Edge with null adjacency list
        assertFalse(graph.hasEdge(vertexC, vertexA, label));
    }
}
