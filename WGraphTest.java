import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;

/** Set of Junit tests for our WGraph implementations.
 */
public class WGraphTest {
    WGraph g;
    GVertex v, u, x, y;
    WEdge e, f;

    @Before
    public void setupWGraph() {
        g = new GraphAdjMatrix(100);
        v = new GVertex('v', g.nextID());
        u = new GVertex('u', g.nextID());
        x = new GVertex('x', g.nextID());
        y = new GVertex('y', g.nextID());
        e = new WEdge(v, u);
        f = new WEdge(v, x);
    }

    @Test
    public void testEmpty() {
        assertEquals(0, g.numWEdges());
        assertEquals(0, g.numVerts());
    }

    @Test
    public void testAddGVertex() {
        assertEquals(true, g.addGVertex(v));
        assertEquals(true, g.addGVertex(u));
        assertEquals(false, g.addGVertex(v));
    }

    @Test
    public void testAddWEdge() {
        assertEquals(true, g.addWEdge(e));
        assertEquals(true, g.addWEdge(v, x));
        assertEquals(false, g.addWEdge(v, u));
        assertEquals(false, g.addWEdge(f));
    }

    @Test
    public void testAdjacency() {
        g.addGVertex(v);
        g.addGVertex(u);
        g.addGVertex(x);
        g.addGVertex(y);
        assertEquals(false, g.areAdjacent(u, v));
        g.addWEdge(e);
        g.addWEdge(f);
        assertEquals(true, g.areAdjacent(u, v));
        assertEquals(true, g.areAdjacent(v, u));
        assertEquals(true, g.areAdjacent(v, x));
        assertEquals(false, g.areAdjacent(x, u));
        assertEquals(false, g.areAdjacent(v, y));
    }

    @Test
    public void testIncidence() {
        g.addGVertex(v);
        g.addGVertex(u);
        g.addGVertex(x);
        g.addGVertex(y);
        g.addWEdge(e);
        assertEquals(false, g.areIncident(e, x));
        assertEquals(false, g.areIncident(e, y));
        assertEquals(true, g.areIncident(e, v));
        assertEquals(true, g.areIncident(e, u));
        g.addWEdge(f);
        assertEquals(true, g.areIncident(f, x));
        assertEquals(false, g.areIncident(f, u));
        assertEquals(4, g.numVerts());
        assertEquals(2, g.numWEdges());
    }

    @Test
    public void testDegree() {
        g.addGVertex(v);
        g.addGVertex(u);
        g.addGVertex(x);
        g.addGVertex(y);
        assertEquals(0, g.degree(v));
        g.addWEdge(e);
        assertEquals(1, g.degree(v));
        g.addWEdge(f);
        assertEquals(2, g.degree(v));
        assertEquals(1, g.degree(x));
        assertEquals(0, g.degree(y));
    }


    @Test
    public void testNeighbors() {
        g.addGVertex(v);
        g.addGVertex(u);
        g.addGVertex(x);
        g.addGVertex(y);
        assertEquals("[]", g.neighbors(v).toString());
        g.addWEdge(e);
        //        System.out.println(g.neighbors(v).toString());
        assertEquals("[1]", g.neighbors(v).toString());
        assertEquals("[0]", g.neighbors(u).toString());
        g.addWEdge(f);
        assertEquals("[1, 2]", g.neighbors(v).toString());
        assertEquals("[0]", g.neighbors(u).toString());
        assertEquals("[0]", g.neighbors(x).toString());
        assertEquals("[]", g.neighbors(y).toString());
    }

}
