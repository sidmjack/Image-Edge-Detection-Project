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
    /**
     * needed for testing.
     */
    private static final int FIVE = 5;
    /**
     * test graph.
     */
    public WGraphP4<String> testGraph;
    /**
     * list of expected edges.
     */
    public List<WEdge<String>> expectedEdges;
    /**
     * do before each test.
     */
    @Before 
    public void setup() {
        this.testGraph = new WGraphP4<String>();
        this.expectedEdges = new ArrayList<WEdge<String>>();
        
        GVertex<String> a = new GVertex<String>("a", 0);
        GVertex<String> b = new GVertex<String>("b", 1);
        GVertex<String> c = new GVertex<String>("c", 2);
        GVertex<String> d = new GVertex<String>("d", 2 + 1);
        GVertex<String> e = new GVertex<String>("e", 2 + 2);
        ArrayList<GVertex<String>> verts = new ArrayList<GVertex<String>>();
        verts.add(a); verts.add(b); verts.add(c); verts.add(d); verts.add(e);
        for (GVertex<String> v: verts) {
            this.testGraph.addVertex(v);
        }
        assertEquals(2 + 2 + 1, this.testGraph.numVerts());
        WEdge<String> ae = new WEdge<String>(a, e, 1);
        WEdge<String> ab = new WEdge<String>(a, b, 2 + 1);
        WEdge<String> be = new WEdge<String>(b, e, 2 + 2);
        WEdge<String> bc = new WEdge<String>(b, c, FIVE);
        WEdge<String> ec = new WEdge<String>(e, c, FIVE + 1);
        WEdge<String> cd = new WEdge<String>(c, d, 2);
        WEdge<String> ed = new WEdge<String>(e, d, 2 + FIVE);
        assertEquals(FIVE, this.testGraph.numVerts());
        
        this.expectedEdges.add(ae); 
        this.expectedEdges.add(ab); 
        this.expectedEdges.add(bc);
        this.expectedEdges.add(cd);
        
        assertEquals(FIVE, this.testGraph.numVerts());
        this.testGraph.addEdge(ae); this.testGraph.addEdge(ab); 
        this.testGraph.addEdge(be); this.testGraph.addEdge(bc); 
        this.testGraph.addEdge(ec); this.testGraph.addEdge(cd);
        this.testGraph.addEdge(ed);
        assertEquals(FIVE, this.testGraph.numVerts());
    }
    /**
     * testing kruskals on specific graph example.
     * makes spanning tree
     */
    @Test
    public void kruskalsTestOneGraph() {
        List<WEdge<String>> k = this.testGraph.kruskals();
        for (WEdge<String> e: this.expectedEdges) {
            assertTrue(k.contains(e));
            k.remove(e);
        }
        assertTrue(k.size() == 0);
        
    }
    /**
     * test kruskals on a graph example.
     * results in a spanning forest of two spanning trees. 
     */
    @Test
    public void kruskalsTestThreeGraph() {
        GVertex<String> f = new GVertex<String>("f", FIVE);
        GVertex<String> g = new GVertex<String>("g", FIVE + 1);
        GVertex<String> h = new GVertex<String>("h", FIVE + 2);
        WEdge<String> fg = new WEdge<String>(f, g, FIVE + FIVE);
        WEdge<String> fh = new WEdge<String>(f, h, 2);
        WEdge<String> hg = new WEdge<String>(h, g, FIVE + FIVE + 2);
        this.expectedEdges.add(fh); this.expectedEdges.add(fg);
        this.testGraph.addEdge(fg); this.testGraph.addEdge(fh); 
        this.testGraph.addEdge(hg);
        List<WEdge<String>> k = this.testGraph.kruskals();
        for (WEdge<String> e: this.expectedEdges) {
            assertTrue(k.contains(e));
            k.remove(e);
        }
        assertTrue(k.size() == 0);
        
        
        
    }
}
