import java.util.List;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
/**
 * JUnit test. 
 *
 */
public class WGraphP4Test {
    /** Magic Number 2. */
    public static final int TWO = 2;
    /** Magic Number 3. */
    public static final int THREE = 3;
    /** Magic Number 4. */
    public static final int FOUR = 4;
    /** Magic Number 5. */
    public static final int FIVE = 5;
    /** Magic Number 6. */
    public static final int SIX = 6;
    /** Magic Number 7. */
    public static final int SEVEN = 7;
    /** Magic Number 8. */
    public static final int EIGHT = 8;
    /** Magic Number 9. */
    public static final int NINE = 9;
    /** Magic Number 10. */
    public static final int TEN = 10;
    /**Magic Number 12. */
    public static final int TWELVE = 12;
    /**
     * test graph.
     */
    public WGraphP4<String> testGraph;
    /**
     * list of expected edges.
     */
    public List<WEdge<String>> expectedEdges;
    /**
     * v a.
     */
    public GVertex<String> a = new GVertex<String>("a", 0);
    /**
     * vb.
     */
    public GVertex<String> b = new GVertex<String>("b", 1);
    /**
     * vc.
     */
    public GVertex<String> c = new GVertex<String>("c", 2);
    /**
     * vd.
     */
    public GVertex<String> d = new GVertex<String>("d", THREE);
    /**
     * ve.
     */
    public GVertex<String> e = new GVertex<String>("e", FOUR);
    
    /**
     * vf.
     */
    public GVertex<String> f = new GVertex<String>("f", FIVE);
    /**
     * edge ae.
     */
    public WEdge<String> ae = new WEdge<String>(this.a, this.e, 1);
    /**
     * edge ab.
     */
    public WEdge<String> ab = new WEdge<String>(this.a, this.b, THREE);
    /**
     * edge be.
     */
    public  WEdge<String> be = new WEdge<String>(this.b, this.e, FOUR);
    /**
     * edge bc.
     */
    public  WEdge<String> bc = new WEdge<String>(this.b, this.c, FIVE);
    /**
     * edge ec.
     */
    public WEdge<String> ec = new WEdge<String>(this.e, this.c, SIX);
    /**
     * edge cd.
     */
    public WEdge<String> cd = new WEdge<String>(this.c, this.d, 2);
    /**
     * edge ed.
     */
    public WEdge<String> ed = new WEdge<String>(this.e, this.d, SEVEN);
    /**
     * do before each test.
     */
    @Before 
    public void setup() {
        this.testGraph = new WGraphP4<String>();
        this.expectedEdges = new ArrayList<WEdge<String>>();
        ArrayList<GVertex<String>> verts = new ArrayList<GVertex<String>>();
        verts.add(this.a); 
        verts.add(this.b); 
        verts.add(this.c); 
        verts.add(this.d); 
        verts.add(this.e);
        for (GVertex<String> v: verts) {
            this.testGraph.addVertex(v);
        }
        assertEquals(FIVE, this.testGraph.numVerts());
        assertEquals(FIVE, this.testGraph.numVerts());
        this.expectedEdges.add(this.ae); 
        this.expectedEdges.add(this.ab); 
        this.expectedEdges.add(this.bc);
        this.expectedEdges.add(this.cd);
        assertEquals(FIVE, this.testGraph.numVerts());
        this.testGraph.addEdge(this.ae); 
        this.testGraph.addEdge(this.ab); 
        this.testGraph.addEdge(this.be); 
        this.testGraph.addEdge(this.bc); 
        this.testGraph.addEdge(this.ec); 
        this.testGraph.addEdge(this.cd);
        this.testGraph.addEdge(this.ed);
        assertEquals(FIVE, this.testGraph.numVerts());
    }
    /**
     * Tests Incidence.
     */
    @Test
    public void doVertexesTouchInEdge() {
        assertEquals(true, this.testGraph.areIncident(this.ab, this.b));
        assertEquals(false, this.testGraph.areIncident(this.ab, this.c));
        assertEquals(false, this.testGraph.areIncident(this.cd, this.b));
        assertEquals(true, this.testGraph.areIncident(this.cd, this.c));
        assertEquals(true, this.testGraph.areIncident(this.cd, this.d));
        assertEquals(FIVE, this.testGraph.numVerts());
        assertEquals(SEVEN, this.testGraph.numEdges());
    }
    /**
     * Tests Degree.
     */
    @Test
    public void howManyNeighbors() {
        assertEquals(2, this.testGraph.degree(this.a));
        assertEquals(THREE, this.testGraph.degree(this.b));
        assertEquals(THREE, this.testGraph.degree(this.c));
        assertEquals(FOUR, this.testGraph.degree(this.e));
        assertEquals(2, this.testGraph.degree(this.d));
    }
    /**
     * testing kruskals on specific graph example.
     * makes spanning tree
     */
    @Test
    public void kruskalsTestOneGraph() {
        List<WEdge<String>> k = this.testGraph.kruskals();
        for (WEdge<String> z: this.expectedEdges) {
            assertTrue(k.contains(z));
            k.remove(z);
        }
        assertTrue(k.size() == 0);
    }
    /**
     * test kruskals on a graph example.
     * results in a spanning forest of two spanning trees. 
     */
    @Test
    public void kruskalsTestTwoGraph() {
        GVertex<String> g = new GVertex<String>("g", SIX);
        GVertex<String> h = new GVertex<String>("h", SEVEN);
        WEdge<String> fg = new WEdge<String>(this.f, g, TEN);
        WEdge<String> fh = new WEdge<String>(this.f, h, 2);
        WEdge<String> hg = new WEdge<String>(h, g, TWELVE);
        this.expectedEdges.add(fh); this.expectedEdges.add(fg);
        this.testGraph.addEdge(fg); this.testGraph.addEdge(fh); 
        this.testGraph.addEdge(hg);
        List<WEdge<String>> k = this.testGraph.kruskals();
        for (WEdge<String> z: this.expectedEdges) {
            assertTrue(k.contains(z));
            k.remove(z);
        }
        assertTrue(k.size() == 0);  
    }
    /**
     * Tests Adjacency.
     */
    @Test
    public void adjacencyTesting() {
        assertEquals(true, this.testGraph.areAdjacent(this.a, this.b));
        assertEquals(true, this.testGraph.areAdjacent(this.b, this.a));
        assertEquals(true, this.testGraph.areAdjacent(this.e, this.c));
        assertEquals(true, this.testGraph.areAdjacent(this.c, this.e));
        this.testGraph.addVertex(this.f);
        assertEquals(false, this.testGraph.areAdjacent(this.f, this.e));
        assertEquals(false, this.testGraph.areAdjacent(this.f, this.c));
        this.testGraph.addEdge(this.f, this.c, EIGHT);
        assertEquals(true, this.testGraph.areAdjacent(this.f, this.c));
        assertEquals(false, this.testGraph.areAdjacent(this.f, this.b));  
    }
}
