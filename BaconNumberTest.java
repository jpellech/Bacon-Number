import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;

public class BaconNumberTest {

    private BaconNumber baconNumber;
    
    @Before
    public void setUp() {
        baconNumber = new BaconNumber();
    }

    @Test
    void testAddName() {
        setUp();
        baconNumber.addName("Actor1");
        assertTrue(baconNumber.getNames().contains("Actor1"));

    }

    @Test
    void testAddTitle() {
        setUp();
        baconNumber.addTitle("Movie1");
        assertTrue(baconNumber.getTitles().contains("Movie1"));
    }

    @Test
    void testGetGraph() {
        setUp();
        Vertex vertex1 = new Vertex("Actor1");
        Vertex vertex2 = new Vertex("Actor2");
        baconNumber.getGraph().addVertex(vertex1);
        baconNumber.getGraph().addVertex(vertex2);
        assertTrue(baconNumber.getGraph().contains("Actor1"));
        assertTrue(baconNumber.getGraph().contains("Actor2"));
        assertEquals(2, baconNumber.getGraph().getOrder());
    }

    @Test
    void testGetNameToTitle() {
        setUp();
        baconNumber.addName("Actor1");
        baconNumber.addTitle("Movie1");
        baconNumber.getNameToTitle().put("Actor1", List.of("Movie1"));
        assertEquals(List.of("Movie1"), baconNumber.getNameToTitle().get("Actor1"));
    }

    @Test
    void testGetNames() {
        setUp();
        baconNumber.addName("Actor1");
        baconNumber.addName("Actor2");
        assertTrue(baconNumber.getNames().contains("Actor1"));
        assertTrue(baconNumber.getNames().contains("Actor2"));
        assertEquals(2, baconNumber.getNames().size());
    }

    @Test
    void testGetTitleToName() {
        setUp();
        baconNumber.addName("Actor1");
        baconNumber.addTitle("Movie1");
        baconNumber.getTitleToName().put("Movie1", List.of("Actor1"));
        assertEquals(List.of("Actor1"), baconNumber.getTitleToName().get("Movie1"));
    }

    @Test
    void testGetTitles() {
        setUp();
        baconNumber.addTitle("Movie1");
        baconNumber.addTitle("Movie2");
        assertTrue(baconNumber.getTitles().contains("Movie1"));
        assertTrue(baconNumber.getTitles().contains("Movie2"));
        assertEquals(2, baconNumber.getTitles().size());

    }

    @Test
    void testMain() {
        setUp();

    }
}
