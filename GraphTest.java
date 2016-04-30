/**
 * Name: Sidney Jackson & Lawrence Wolf-Sonkin
 * Blackboard Login: sjacks85 & lwolfso1 & eheredi1
 * Course: Data Structures 600.226.02
 **/
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;

/**
 * Set of Junit tests for our Graph implementations.
 */
public class GraphTest {
    /**  */
    Graph g;
    /**  */
    Vertex v, u, x, y;
    /** Set of Edges. */
    Edge e, f;

    /**
     * Sets up graph.
     */
    @Before
    public void setupGraph() {
        final int hundred = 100;
        this.g = new GraphAdjMatrix(hundred);
        this.v = new Vertex('v', this.g.nextID());
        this.u = new Vertex('u', this.g.nextID());
        this.x = new Vertex('x', this.g.nextID());
        this.y = new Vertex('y', this.g.nextID());
        this.e = new Edge(this.v, this.u);
        this.f = new Edge(this.v, this.x);
    }

    /**
     * Tests Empty.
     */
    @Test
    public void testEmpty() {
        assertEquals(0, this.g.numEdges());
        assertEquals(0, this.g.numVerts());
    }

    /**
     * Tests Add Vertex.
     */
    @Test
    public void testAddVertex() {
        assertEquals(true, this.g.addVertex(this.v));
        assertEquals(true, this.g.addVertex(this.u));
        assertEquals(false, this.g.addVertex(this.v));
    }

    /**
     * Tests add edge.
     */
    @Test
    public void testAddEdge() {
        assertEquals(true, this.g.addEdge(this.e));
        assertEquals(true, this.g.addEdge(this.v, this.x));
        assertEquals(false, this.g.addEdge(this.v, this.u));
        assertEquals(false, this.g.addEdge(this.f));
    }

    /**
     * Tests Adjacency.
     */
    @Test
    public void testAdjacency() {
        this.g.addVertex(this.v);
        this.g.addVertex(this.u);
        this.g.addVertex(this.x);
        this.g.addVertex(this.y);
        assertEquals(false, this.g.areAdjacent(this.u, this.v));
        this.g.addEdge(this.e);
        this.g.addEdge(this.f);
        assertEquals(true, this.g.areAdjacent(this.u, this.v));
        assertEquals(true, this.g.areAdjacent(this.v, this.u));
        assertEquals(true, this.g.areAdjacent(this.v, this.x));
        assertEquals(false, this.g.areAdjacent(this.x, this.u));
        assertEquals(false, this.g.areAdjacent(this.v, this.y));
    }

    /**
     * Tests Incidence.
     */
    @Test
    public void testIncidence() {
        this.g.addVertex(this.v);
        this.g.addVertex(this.u);
        this.g.addVertex(this.x);
        this.g.addVertex(this.y);
        this.g.addEdge(this.e);
        assertEquals(false, this.g.areIncident(this.e, this.x));
        assertEquals(false, this.g.areIncident(this.e, this.y));
        assertEquals(true, this.g.areIncident(this.e, this.v));
        assertEquals(true, this.g.areIncident(this.e, this.u));
        this.g.addEdge(this.f);
        final int four = 4;
        final int two = 2;
        assertEquals(true, this.g.areIncident(this.f, this.x));
        assertEquals(false, this.g.areIncident(this.f, this.u));
        assertEquals(four, this.g.numVerts());
        assertEquals(two, this.g.numEdges());
    }
    
    /**
     * Tests Degree.
     */
    @Test
    public void testDegree() {
        this.g.addVertex(this.v);
        this.g.addVertex(this.u);
        this.g.addVertex(this.x);
        this.g.addVertex(this.y);
        assertEquals(0, this.g.degree(this.v));
        this.g.addEdge(this.e);
        assertEquals(1, this.g.degree(this.v));
        this.g.addEdge(this.f);
        final int two = 2;
        assertEquals(two, this.g.degree(this.v));
        assertEquals(1, this.g.degree(this.x));
        assertEquals(0, this.g.degree(this.y));
    }

    /**
     * Tests Neighbors.
     */
    @Test
    public void testNeighbors() {
        this.g.addVertex(this.v);
        this.g.addVertex(this.u);
        this.g.addVertex(this.x);
        this.g.addVertex(this.y);
        assertEquals("[]", this.g.neighbors(this.v).toString());
        this.g.addEdge(this.e);
        //        System.out.println(g.neighbors(v).toString());
        assertEquals("[1]", this.g.neighbors(this.v).toString());
        assertEquals("[0]", this.g.neighbors(this.u).toString());
        this.g.addEdge(this.f);
        assertEquals("[1, 2]", this.g.neighbors(this.v).toString());
        assertEquals("[0]", this.g.neighbors(this.u).toString());
        assertEquals("[0]", this.g.neighbors(this.x).toString());
        assertEquals("[]", this.g.neighbors(this.y).toString());
    }

}
