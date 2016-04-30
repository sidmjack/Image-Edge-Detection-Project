/**
 * Name: Sidney Jackson & Lawrence Wolf-Sonkin
 * Blackboard Login: sjacks85 & lwolfso1 & eheredi1
 * Course: Data Structures 600.226.02
 **/
import java.util.List;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

/** Giant WGraphP4 Test. */
public class WGraphP4TestAlternate {
    
    /** Magic Number 2. */
    public static final int TWO = 2;
    /** Magic Number 3. */
    public static final int THREE = 3;
    /** Magic Number 3.5. */
    public static final double TPF = 3.5;
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

    /** Graph. */
    public WGraphP4<String> testGraph;
    /** Lists. */
    public List<WEdge<String>> expectedEdges;
    /** Vertices. */
    GVertex<String> a, b, c, d, e, f;
    /** WEdges. */
    WEdge<String> ae, ab, be, bc, ec, cd, ed;

    /** The setup for the tests. */
    @Before 
    public void setup() {
        this.testGraph = new WGraphP4<String>();
        this.expectedEdges = new ArrayList<WEdge<String>>();
        
        this.a = new GVertex<String>("a", 0);
        this.b = new GVertex<String>("b", 1);
        this.c = new GVertex<String>("c", TWO);
        this.d = new GVertex<String>("d", THREE);
        this.e = new GVertex<String>("e", FOUR);
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
        this.ae = new WEdge<String>(this.a, this.e, 1);
        this.ab = new WEdge<String>(this.a, this.b, THREE);
        this.be = new WEdge<String>(this.b, this.e, FOUR);
        this.bc = new WEdge<String>(this.b, this.c, FIVE);
        this.ec = new WEdge<String>(this.e, this.c, SIX);
        this.cd = new WEdge<String>(this.c, this.d, TWO);
        this.ed = new WEdge<String>(this.e, this.d, SEVEN);
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
        this.testGraph.addEdge(this.a, this.a, TWO);
        assertEquals(EIGHT, this.testGraph.numEdges());
    }
    
    /**
     * Tests Add Vertex.
     */
    @Test
    public void testAddV() {
        this.testGraph = new WGraphP4<String>();
        assertEquals(true, this.testGraph.addVertex(this.a));
        assertEquals(true, this.testGraph.addVertex(this.b));
        assertEquals("replicated vertex added", false,
            this.testGraph.addVertex(this.a));
    }
    
    /**
     * Tests Add Edge.
     */
    public void testAddE() {
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
        assertTrue(this.testGraph.addEdge(this.ae));
        assertFalse(this.testGraph.addEdge(this.ae));
        assertTrue(this.testGraph.addEdge(this.b, this.e, FOUR));
        assertFalse(this.testGraph.addEdge(this.be));
        assertTrue(this.testGraph.addEdge(this.bc));
        assertTrue(this.testGraph.addEdge(this.ec));
        assertTrue(this.testGraph.addEdge(this.cd));
        assertTrue(this.testGraph.addEdge(this.ed));
        assertEquals(FIVE, this.testGraph.numVerts()); 
    }
    
    /**
     * Tests Deletion.
     */
    @Test
    public void testDeletion() {
        this.testGraph = new WGraphP4<String>();
        ArrayList<GVertex<String>> verts 
            = new ArrayList<GVertex<String>>();
        verts.add(this.a); 
        verts.add(this.b); 
        verts.add(this.c);
        verts.add(this.d); 
        verts.add(this.e);
        for (GVertex<String> v: verts) {
            this.testGraph.addVertex(v);
        }
        assertEquals(FIVE, this.testGraph.numVerts());
        this.ae = new WEdge<String>(this.a, this.e, 1);
        this.ab = new WEdge<String>(this.a, this.b, THREE);
        this.be = new WEdge<String>(this.b, this.e, FOUR);
        this.bc = new WEdge<String>(this.b, this.c, FIVE);
        this.ec = new WEdge<String>(this.e, this.c, SIX);
        this.cd = new WEdge<String>(this.c, this.d, TWO);
        this.ed = new WEdge<String>(this.e, this.d, SEVEN);
        this.expectedEdges.add(this.ae); 
        this.expectedEdges.add(this.ab); 
        this.expectedEdges.add(this.bc);
        this.expectedEdges.add(this.cd);
        this.testGraph.addEdge(this.ae);
        this.testGraph.addEdge(this.ab); 
        this.testGraph.addEdge(this.be);
        this.testGraph.addEdge(this.bc); 
        this.testGraph.addEdge(this.ec); 
        this.testGraph.addEdge(this.cd);
        this.testGraph.addEdge(this.ed);
        
        assertTrue(this.testGraph.deleteEdge(this.a, this.e));
        assertEquals("Edge num not updated", SIX, this.testGraph.numEdges());
        assertFalse(this.testGraph.deleteEdge(this.e, this.a));
        assertEquals("connection not deleted", 
            false, this.testGraph.areAdjacent(this.a, this.e));
        assertTrue(this.testGraph.deleteEdge(this.a, this.b));
        assertEquals("Edge num not updated", FIVE, this.testGraph.numEdges());
        assertFalse(this.testGraph.deleteEdge(this.b, this.a));
        assertEquals("connection not deleted", 
            false, this.testGraph.areAdjacent(this.a, this.b));
        assertTrue(this.testGraph.deleteEdge(this.b, this.e));
        assertEquals("Edge num not updated", FOUR, this.testGraph.numEdges());
        assertFalse(this.testGraph.deleteEdge(this.e, this.b));
        assertEquals("connection not deleted", 
            false, this.testGraph.areAdjacent(this.b, this.e));
        assertTrue(this.testGraph.deleteEdge(this.b, this.c));
        assertEquals("Edge num not updated", THREE, this.testGraph.numEdges());
        assertFalse(this.testGraph.deleteEdge(this.c, this.b));
        assertEquals("connection not deleted", 
            false, this.testGraph.areAdjacent(this.b, this.c));
        assertTrue(this.testGraph.deleteEdge(this.e, this.c));
        assertEquals("Edge num not updated", TWO, this.testGraph.numEdges());
        assertFalse(this.testGraph.deleteEdge(this.c, this.e));
        assertEquals("connection not deleted", 
            false, this.testGraph.areAdjacent(this.e, this.c));
        assertTrue(this.testGraph.deleteEdge(this.c, this.d));
        assertEquals("Edge num not updated", 1, this.testGraph.numEdges());
        assertFalse(this.testGraph.deleteEdge(this.d, this.c));
        assertEquals("connection not deleted", 
            false, this.testGraph.areAdjacent(this.c, this.d));
        assertTrue(this.testGraph.deleteEdge(this.e, this.d));
        assertEquals("Edge num not updated", 0, this.testGraph.numEdges());
        assertFalse(this.testGraph.deleteEdge(this.d, this.e));
        assertEquals("connection not deleted", 
            false, this.testGraph.areAdjacent(this.e, this.d));
    }
    
    /**
     * Tests Adjacency.
     */
    @Test
    public void testAdj() {
        assertEquals("adjacency not established", true, 
            this.testGraph.areAdjacent(this.a, this.b));
        assertEquals("adjacency not established", true, 
            this.testGraph.areAdjacent(this.b, this.a));
        assertEquals("adjacency not established", true, 
            this.testGraph.areAdjacent(this.b, this.c));
        assertEquals("adjacency not established", true, 
            this.testGraph.areAdjacent(this.c, this.b));
        this.f = new GVertex<String>("f", FIVE);
        this.testGraph.addVertex(this.f);
        assertEquals("wrong adjacecy established", false, 
            this.testGraph.areAdjacent(this.f, this.b));
        assertEquals("wrong adjacecy established", false, 
            this.testGraph.areAdjacent(this.f, this.a));
        this.testGraph.addEdge(this.f, this.a, EIGHT);
        assertTrue(this.testGraph.addEdge(this.f, this.f, TWO));
        this.testGraph.addEdge(this.f, this.c, NINE);
        assertEquals("adjacency not established", true, 
            this.testGraph.areAdjacent(this.f, this.a));
        assertEquals("adjacency not established", true, 
            this.testGraph.areAdjacent(this.f, this.f));
        //assertEquals("reverse adjacency not established", 
        //true, testGraph.areAdjacent(a, f)); //?   
    }
    
    /**
     * Tests Incidence.
     */
    @Test
    public void testIncidence() {
        this.testGraph = new WGraphP4<String>();
        this.testGraph.addVertex(this.a);
        this.testGraph.addVertex(this.b);
        this.testGraph.addVertex(this.c);
        this.testGraph.addVertex(this.d);
        this.testGraph.addEdge(this.cd);
        assertEquals("wrong incident", false, 
            this.testGraph.areIncident(this.cd, this.b));
        assertEquals("wrong incident", false, 
            this.testGraph.areIncident(this.cd, this.b));
        assertEquals("incident haven't established", true, 
            this.testGraph.areIncident(this.cd, this.c));
        assertEquals("incident haven't established", true, 
            this.testGraph.areIncident(this.cd, this.d));
        this.testGraph.addEdge(this.ab);
        assertEquals(true, this.testGraph.areIncident(this.ab, this.b));
        assertEquals(false, this.testGraph.areIncident(this.ab, this.c));
        assertEquals(FOUR, this.testGraph.numVerts());
        assertEquals(TWO, this.testGraph.numEdges());
    }
    
    /**
     * Tests Degree.
     */
    @Test
    public void testDegree() {
        this.testGraph = new WGraphP4<String>();
        this.testGraph.addVertex(this.a);
        this.testGraph.addVertex(this.b);
        this.testGraph.addVertex(this.c);
        this.testGraph.addVertex(this.d);
        assertEquals("degree num not consistant", 0, 
            this.testGraph.degree(this.a));
        assertEquals("degree num not consistant", 0, 
            this.testGraph.degree(this.b));
        assertEquals("degree num not consistant", 0, 
            this.testGraph.degree(this.c));
        this.testGraph.addEdge(this.ab);
        assertEquals("degree num not consistant", 1, 
            this.testGraph.degree(this.a));
        this.testGraph.addEdge(this.bc);
        assertEquals("degree num not consistant", TWO, 
            this.testGraph.degree(this.b));
        assertEquals("degree num not consistant", 1, 
            this.testGraph.degree(this.a));
        assertEquals("degree num not consistant", 0, 
            this.testGraph.degree(this.d));
    }
    
    /**
     * Tests Neighbours.
     */
    @Test
    public void testNeighbour() {
        assertEquals("[4, 1, 0]", this.testGraph.neighbors(this.a).toString());
        assertEquals("[0, 4, 2]", this.testGraph.neighbors(this.b).toString());
        assertEquals("[1, 4, 3]", this.testGraph.neighbors(this.c).toString());
        assertEquals("[2, 4]", this.testGraph.neighbors(this.d).toString());
        assertTrue(this.testGraph.addEdge(this.a, this.c, THREE));
        assertEquals("[4, 1, 0, 2]", 
            this.testGraph.neighbors(this.a).toString());
        assertTrue(this.testGraph.deleteEdge(this.a, this.a));
        assertEquals("[4, 1, 2]", this.testGraph.neighbors(this.a).toString());
        assertTrue(this.testGraph.deleteEdge(this.a, this.b));
        assertFalse(this.testGraph.deleteEdge(this.b, this.b));
        assertTrue(this.testGraph.deleteEdge(this.a, this.e));
        assertTrue(this.testGraph.deleteEdge(this.a, this.c));
        assertEquals("[]", this.testGraph.neighbors(this.a).toString());
    }
    
    /**
     * Tests Degress.
     */
    @Test
    public void testDegrees() {
        assertEquals("degree num not consistant", THREE, 
            this.testGraph.degree(this.a));
        assertEquals("degree num not consistant", THREE, 
            this.testGraph.degree(this.b));
        assertEquals("degree num not consistant", THREE, 
            this.testGraph.degree(this.c));
        assertEquals("degree num not consistant", FOUR, 
            this.testGraph.degree(this.e));
        this.testGraph.deleteEdge(this.c, this.d);
        this.testGraph.deleteEdge(this.e, this.d);
        assertEquals("degree num not consistant", 0, 
            this.testGraph.degree(this.d));
       
        WGraphP4<String> tg = new WGraphP4<String>();
        tg.addVertex(this.a);
        tg.addVertex(this.b);
        assertEquals("degree num not consistant", 0, tg.degree(this.a));
        tg.addEdge(this.a, this.a, 1);
        tg.addEdge(this.a, this.a, 1);
        tg.addEdge(this.a, this.b, 1);
        tg.addEdge(this.b, this.a, 1);
        assertEquals("degree num not consistant", TWO, tg.degree(this.a));
    }
    
    /**
     * Tests All Edges.
     */
    @Test // problem is there's no space, should there be space between values?
    public void testAllEdges() { 
        assertEquals(EIGHT, this.testGraph.numEdges());
        assertEquals("[(0,4,1.0), (0,1,3.0), (0,0,2.0), (1,4,4.0), "
               + "(1,2,5.0), (2,4,6.0), (2,3,2.0), (3,4,7.0)]", 
            this.testGraph.allEdges().toString());
        this.testGraph.deleteEdge(this.a, this.a);
        assertEquals("[(0,4,1.0), (0,1,3.0), (1,4,4.0), "
               + "(1,2,5.0), (2,4,6.0), (2,3,2.0), (3,4,7.0)]", 
            this.testGraph.allEdges().toString());
        
        WGraphP4<String> tg = new WGraphP4<String>();
        assertEquals(0, tg.numEdges());
        assertEquals("[]", tg.allEdges().toString());
        tg.addVertex(this.a);
        tg.addEdge(this.a, this.a, TWO);
        assertEquals(1, tg.numEdges());
        assertEquals("[(0,0,2.0)]", tg.allEdges().toString());  
        tg.addVertex(this.b);
        tg.addEdge(this.a, this.b, TPF);
        assertEquals(TWO, tg.numEdges());
        assertEquals("[(0,0,2.0), (0,1,3.5)]", tg.allEdges().toString());   
    }
    
    /**
     * Tests All Vertices.
     */
    @Test
    public void testAllVertices() {
        assertEquals("[0, 1, 2, 3, 4]", 
            this.testGraph.allVertices().toString());
        assertEquals(FIVE, this.testGraph.numVerts());
        WGraphP4<String> tg = new WGraphP4<String>();
        assertEquals("[]", tg.allVertices().toString());
        assertEquals(0, tg.numVerts());
    }
    
    /**
     * Tests Kruskals.
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
     * Tests Kurskals.
     */
    @Test
    public void kruskalsTestTHREEGraph() {
        this.f = new GVertex<String>("f", FIVE);
        GVertex<String> g = new GVertex<String>("g", SIX);
        GVertex<String> h = new GVertex<String>("h", SEVEN);
        WEdge<String> fg = new WEdge<String>(this.f, g, TEN);
        WEdge<String> fh = new WEdge<String>(this.f, h, TWO);
        WEdge<String> hg = new WEdge<String>(h, g, TWELVE);
        this.expectedEdges.add(fh); 
        this.expectedEdges.add(fg);
        this.testGraph.addEdge(fg); 
        this.testGraph.addEdge(fh); 
        this.testGraph.addEdge(hg);
        List<WEdge<String>> k = this.testGraph.kruskals();
        for (WEdge<String> z: this.expectedEdges) {
            assertTrue(k.contains(z));
            k.remove(z);
        }
        assertTrue(k.size() == 0);     
    }
}
