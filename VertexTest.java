import static org.junit.Assert.*;
import org.junit.Test;

public class VertexTest {

    @Test
    public void testMakeEdge() {
        Vertex vertexA = new Vertex("A");
        Vertex vertexB = new Vertex("B");
        String edgeLabel = "EdgeLabel";

        // Case 1: Make a new edge
        vertexA.makeEdge(vertexB.getName(), edgeLabel);
        assertTrue(vertexA.getEdge(vertexB.getName()).equals(edgeLabel));

        // Case 2: Make an edge with the same label
        vertexA.makeEdge(vertexB.getName(), edgeLabel);
        assertTrue(vertexA.getEdge(vertexB.getName()).equals(edgeLabel));

        // Case 3: Make an edge with a different label
        vertexA.makeEdge(vertexB.getName(), "DifferentLabel");
        assertFalse(vertexA.getEdge(vertexB.getName()).equals(edgeLabel));
        assertTrue(vertexA.getEdge(vertexB.getName()).equals("DifferentLabel"));

        // Case 4: Make an edge with a null label
        vertexA.makeEdge(vertexB.getName(), null);
        assertNull(vertexA.getEdge(vertexB.getName()));

        // Case 5: Make an edge with a null key
        vertexA.makeEdge(null, "NullKeyLabel");
        assertNull(vertexA.getEdge(null));
    }

    @Test
    public void testAddNeighbor() {
        Vertex vertexA = new Vertex("A");
        Vertex vertexB = new Vertex("B");

        // Case 1: Add a new neighbor
        vertexA.addNeighbor(vertexB);
        assertTrue(vertexA.getAdjacents().contains(vertexB));

        // Case 2: Add the same neighbor again
        vertexA.addNeighbor(vertexB);
        assertEquals(1, vertexA.getOrder());

        // Case 3: Add a null neighbor
        vertexA.addNeighbor(null);
        assertEquals(1, vertexA.getOrder());
    }
}
