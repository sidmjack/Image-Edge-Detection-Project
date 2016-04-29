import static org.junit.Assert.*;

import java.util.List;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class WGraphP4Test {
	/** Graph. */
    public WGraphP4<String> testGraph;
    /** Lists. */
    public List<WEdge<String>> expectedEdges;
    /** Vertices */
    GVertex<String> a, b, c, d, e;
    /** WEdges */
    WEdge<String> ae, ab, be, bc, ec, cd, ed;
    
    /** the setup for the tests. */
    @Before 
    public void setup() {
        testGraph = new WGraphP4<String>();
        expectedEdges = new ArrayList<WEdge<String>>();
        
        a = new GVertex<String>("a", 0);
        b = new GVertex<String>("b", 1);
        c = new GVertex<String>("c", 2);
        d = new GVertex<String>("d", 3);
        e = new GVertex<String>("e", 4);
        ArrayList<GVertex<String>> verts = new ArrayList<GVertex<String>>();
        verts.add(a); verts.add(b); verts.add(c); verts.add(d); verts.add(e);
        for(GVertex<String> v: verts) {
            testGraph.addVertex(v);
        }
        assertEquals(5, testGraph.numVerts());
        ae = new WEdge<String>(a, e, 1);
        ab = new WEdge<String>(a, b, 3);
        be = new WEdge<String>(b, e, 4);
        bc = new WEdge<String>(b, c, 5);
        ec = new WEdge<String>(e, c, 6);
        cd = new WEdge<String>(c, d, 2);
        ed = new WEdge<String>(e, d, 7);
        assertEquals(5, testGraph.numVerts());
        
        expectedEdges.add(ae); expectedEdges.add(ab); expectedEdges.add(bc);
        expectedEdges.add(cd);
        
        assertEquals(5, testGraph.numVerts());
        testGraph.addEdge(ae); testGraph.addEdge(ab); testGraph.addEdge(be);
        testGraph.addEdge(bc); testGraph.addEdge(ec); testGraph.addEdge(cd);
        testGraph.addEdge(ed); testGraph.addEdge(a, a, 2);
        assertEquals(8, testGraph.numEdges());
    }
    
    @Test
    public void testAddV() {
    	testGraph = new WGraphP4<String>();
    	GVertex<String> a = new GVertex<String>("a", 0);
        GVertex<String> b = new GVertex<String>("b", 1);
        GVertex<String> c = new GVertex<String>("c", 2);
    	assertEquals(true, testGraph.addVertex(a));
        assertEquals(true, testGraph.addVertex(b));
        assertEquals("replicated vertex added", false, testGraph.addVertex(a));
    }
    
    public void testAddE() {
    	testGraph = new WGraphP4<String>();
        expectedEdges = new ArrayList<WEdge<String>>();
        
        GVertex<String> a = new GVertex<String>("a", 0);
        GVertex<String> b = new GVertex<String>("b", 1);
        GVertex<String> c = new GVertex<String>("c", 2);
        GVertex<String> d = new GVertex<String>("d", 3);
        GVertex<String> e = new GVertex<String>("e", 4);
        ArrayList<GVertex<String>> verts = new ArrayList<GVertex<String>>();
        verts.add(a); verts.add(b); verts.add(c); verts.add(d); verts.add(e);
        for(GVertex<String> v: verts) {
            testGraph.addVertex(v);
        }
        assertEquals(5, testGraph.numVerts());
        WEdge<String> ae = new WEdge<String>(a, e, 1);
        WEdge<String> ab = new WEdge<String>(a, b, 3);
        WEdge<String> be = new WEdge<String>(b, e, 4);
        WEdge<String> bc = new WEdge<String>(b, c, 5);
        WEdge<String> ec = new WEdge<String>(e, c, 6);
        WEdge<String> cd = new WEdge<String>(c, d, 2);
        WEdge<String> ed = new WEdge<String>(e, d, 7);
        
        assertEquals(5, testGraph.numVerts());
        
        expectedEdges.add(ae); expectedEdges.add(ab); expectedEdges.add(bc);
        expectedEdges.add(cd);
        
        assertEquals(5, testGraph.numVerts());
        assertTrue(testGraph.addEdge(ae));
        assertFalse(testGraph.addEdge(ae));
        assertTrue(testGraph.addEdge(b, e, 4));
        assertFalse(testGraph.addEdge(be));
        assertTrue(testGraph.addEdge(bc));
        assertTrue(testGraph.addEdge(ec));
        assertTrue(testGraph.addEdge(cd));
        assertTrue(testGraph.addEdge(ed));
        assertEquals(5, testGraph.numVerts()); 
    }
    
    @Test
    public void testDeletion() {
    	testGraph = new WGraphP4<String>();
        
        GVertex<String> a = new GVertex<String>("a", 0);
        GVertex<String> b = new GVertex<String>("b", 1);
        GVertex<String> c = new GVertex<String>("c", 2);
        GVertex<String> d = new GVertex<String>("d", 3);
        GVertex<String> e = new GVertex<String>("e", 4);
        ArrayList<GVertex<String>> verts = new ArrayList<GVertex<String>>();
        verts.add(a); verts.add(b); verts.add(c); verts.add(d); verts.add(e);
        for(GVertex<String> v: verts) {
            testGraph.addVertex(v);
        }
        assertEquals(5, testGraph.numVerts());
        WEdge<String> ae = new WEdge<String>(a, e, 1);
        WEdge<String> ab = new WEdge<String>(a, b, 3);
        WEdge<String> be = new WEdge<String>(b, e, 4);
        WEdge<String> bc = new WEdge<String>(b, c, 5);
        WEdge<String> ec = new WEdge<String>(e, c, 6);
        WEdge<String> cd = new WEdge<String>(c, d, 2);
        WEdge<String> ed = new WEdge<String>(e, d, 7);
        expectedEdges.add(ae); expectedEdges.add(ab); expectedEdges.add(bc);
        expectedEdges.add(cd);
        testGraph.addEdge(ae); testGraph.addEdge(ab); testGraph.addEdge(be);
        testGraph.addEdge(bc); testGraph.addEdge(ec); testGraph.addEdge(cd);
        testGraph.addEdge(ed);
        
        assertTrue(testGraph.deleteEdge(a, e));
        assertEquals("Edge num not updated", 6, testGraph.numEdges());
        assertFalse(testGraph.deleteEdge(e, a));
        assertEquals("connection not deleted", false, testGraph.areAdjacent(a, e));
        assertTrue(testGraph.deleteEdge(a, b));
        assertEquals("Edge num not updated", 5, testGraph.numEdges());
        assertFalse(testGraph.deleteEdge(b, a));
        assertEquals("connection not deleted", false, testGraph.areAdjacent(a, b));
        assertTrue(testGraph.deleteEdge(b, e));
        assertEquals("Edge num not updated", 4, testGraph.numEdges());
        assertFalse(testGraph.deleteEdge(e, b));
        assertEquals("connection not deleted", false, testGraph.areAdjacent(b, e));
        assertTrue(testGraph.deleteEdge(b, c));
        assertEquals("Edge num not updated", 3, testGraph.numEdges());
        assertFalse(testGraph.deleteEdge(c, b));
        assertEquals("connection not deleted", false, testGraph.areAdjacent(b, c));
        assertTrue(testGraph.deleteEdge(e, c));
        assertEquals("Edge num not updated", 2, testGraph.numEdges());
        assertFalse(testGraph.deleteEdge(c, e));
        assertEquals("connection not deleted", false, testGraph.areAdjacent(e, c));
        assertTrue(testGraph.deleteEdge(c, d));
        assertEquals("Edge num not updated", 1, testGraph.numEdges());
        assertFalse(testGraph.deleteEdge(d, c));
        assertEquals("connection not deleted", false, testGraph.areAdjacent(c, d));
        assertTrue(testGraph.deleteEdge(e, d));
        assertEquals("Edge num not updated", 0, testGraph.numEdges());
        assertFalse(testGraph.deleteEdge(d, e));
        assertEquals("connection not deleted", false, testGraph.areAdjacent(e, d));
    }
    
    @Test
    public void testAdj() {
    	assertEquals("adjacency not established",true, testGraph.areAdjacent(a, b));
    	assertEquals("adjacency not established",true, testGraph.areAdjacent(b, a));
    	assertEquals("adjacency not established",true, testGraph.areAdjacent(b, c));
    	assertEquals("adjacency not established",true, testGraph.areAdjacent(c, b));
    	GVertex<String> f = new GVertex<String>("f", 5);
    	testGraph.addVertex(f);
    	assertEquals("wrong adjacecy established",false, testGraph.areAdjacent(f, b));
    	assertEquals("wrong adjacecy established",false, testGraph.areAdjacent(f, a));
    	testGraph.addEdge(f, a, 8);
    	assertTrue(testGraph.addEdge(f, f, 2));
    	testGraph.addEdge(f, c, 9);
    	assertEquals("adjacency not established",true, testGraph.areAdjacent(f, a));
    	assertEquals("adjacency not established",true, testGraph.areAdjacent(f, f));
    	//assertEquals("reverse adjacency not established",true, testGraph.areAdjacent(a, f)); //?	
    }
    
    @Test
    public void testIncidence() {
        testGraph = new WGraphP4<String>();
        testGraph.addVertex(a);
        testGraph.addVertex(b);
        testGraph.addVertex(c);
        testGraph.addVertex(d);
        testGraph.addEdge(cd);
        assertEquals("wrong incident", false, testGraph.areIncident(cd, b));
        assertEquals("wrong incident", false, testGraph.areIncident(cd, b));
        assertEquals("incident haven't established", true, testGraph.areIncident(cd, c));
        assertEquals("incident haven't established", true, testGraph.areIncident(cd, d));
        testGraph.addEdge(ab);
        assertEquals(true, testGraph.areIncident(ab, b));
        assertEquals(false, testGraph.areIncident(ab, c));
        assertEquals(4, testGraph.numVerts());
        assertEquals(2, testGraph.numEdges());
    }
    
    @Test
    public void testDegree() {
    	testGraph = new WGraphP4<String>();
    	testGraph.addVertex(a);
        testGraph.addVertex(b);
        testGraph.addVertex(c);
        testGraph.addVertex(d);
        assertEquals("degree num not consistant", 0, testGraph.degree(a));
        assertEquals("degree num not consistant", 0, testGraph.degree(b));
        assertEquals("degree num not consistant", 0, testGraph.degree(c));
        testGraph.addEdge(ab);
        assertEquals("degree num not consistant", 1, testGraph.degree(a));
        testGraph.addEdge(bc);
        assertEquals("degree num not consistant", 2, testGraph.degree(b));
        assertEquals("degree num not consistant", 1, testGraph.degree(a));
        assertEquals("degree num not consistant", 0, testGraph.degree(d));
    }
    
    @Test
    public void testNeighbour() {
    	assertEquals("[4, 1, 0]", testGraph.neighbors(a).toString());
    	assertEquals("[0, 4, 2]", testGraph.neighbors(b).toString());
    	assertEquals("[1, 4, 3]", testGraph.neighbors(c).toString());
    	assertEquals("[2, 4]", testGraph.neighbors(d).toString());
    	assertTrue(testGraph.addEdge(a, c, 3));
    	assertEquals("[4, 1, 0, 2]", testGraph.neighbors(a).toString());
    	assertTrue(testGraph.deleteEdge(a, a));
    	assertEquals("[4, 1, 2]", testGraph.neighbors(a).toString());
    	assertTrue(testGraph.deleteEdge(a, b));
    	assertFalse(testGraph.deleteEdge(b, b));
    	assertTrue(testGraph.deleteEdge(a, e));
    	assertTrue(testGraph.deleteEdge(a, c));
    	assertEquals("[]", testGraph.neighbors(a).toString());
    }
    
    @Test
    public void testDegrees() {
    	assertEquals("degree num not consistant", 3, testGraph.degree(a));
    	assertEquals("degree num not consistant", 3, testGraph.degree(b));
    	assertEquals("degree num not consistant", 3, testGraph.degree(c));
        assertEquals("degree num not consistant", 4, testGraph.degree(e));
        testGraph.deleteEdge(c, d);
        testGraph.deleteEdge(e, d);
        assertEquals("degree num not consistant", 0, testGraph.degree(d));
       
        WGraphP4<String> tg = new WGraphP4<String> ();
        tg.addVertex(a);
        tg.addVertex(b);
        assertEquals("degree num not consistant", 0, tg.degree(a));
        tg.addEdge(a, a, 1);
        tg.addEdge(a, a, 1);
        tg.addEdge(a, b, 1);
        tg.addEdge(b, a, 1);
        assertEquals("degree num not consistant", 2, tg.degree(a));
    }
    
    @Test // problem is there's no space, should there be space between values?
    public void testAllEdges() { 
    	assertEquals(8, testGraph.numEdges());
    	assertEquals("[(0,4,1.0), (0,1,3.0), (0,0,2.0), (1,4,4.0), "
    			+ "(1,2,5.0), (2,4,6.0), (2,3,2.0), (3,4,7.0)]",testGraph.allEdges().toString());
    	testGraph.deleteEdge(a, a);
    	assertEquals("[(0,4,1.0), (0,1,3.0), (1,4,4.0), "
    			+ "(1,2,5.0), (2,4,6.0), (2,3,2.0), (3,4,7.0)]",testGraph.allEdges().toString());
    	
    	WGraphP4<String> tg = new WGraphP4<String> ();
    	assertEquals(0, tg.numEdges());
    	assertEquals("[]",tg.allEdges().toString());
    	tg.addVertex(a);
    	tg.addEdge(a, a, 2);
    	assertEquals(1, tg.numEdges());
    	assertEquals("[(0,0,2.0)]",tg.allEdges().toString());	
    	tg.addVertex(b);
    	tg.addEdge(a, b, 3.5);
    	assertEquals(2, tg.numEdges());
    	assertEquals("[(0,0,2.0), (0,1,3.5)]",tg.allEdges().toString());	
    }
    
    @Test
    public void testAllVertices() {
    	assertEquals("[0, 1, 2, 3, 4]", testGraph.allVertices().toString());
    	assertEquals(5, testGraph.numVerts());
    	WGraphP4<String> tg = new WGraphP4<String> ();
    	assertEquals("[]",tg.allVertices().toString());
    	assertEquals(0, tg.numVerts());
    }
    
    @Test
    public void kruskalsTestOneGraph() {
        List<WEdge<String>> k = testGraph.kruskals();
        for (WEdge<String> e: expectedEdges) {
            assertTrue(k.contains(e));
            k.remove(e);
        }
        assertTrue(k.size() == 0);    
    }
    
    @Test
    public void kruskalsTestThreeGraph() {
        GVertex<String> f = new GVertex<String>("f", 5);
        GVertex<String> g = new GVertex<String>("g", 6);
        GVertex<String> h = new GVertex<String>("h", 7);
        WEdge<String> fg = new WEdge<String>(f, g, 10);
        WEdge<String> fh = new WEdge<String>(f, h, 2);
        WEdge<String> hg = new WEdge<String>(h, g, 12);
        expectedEdges.add(fh); expectedEdges.add(fg);
        testGraph.addEdge(fg); testGraph.addEdge(fh); testGraph.addEdge(hg);
        List<WEdge<String>> k = testGraph.kruskals();
        for (WEdge<String> e: expectedEdges) {
            assertTrue(k.contains(e));
            k.remove(e);
        }
        assertTrue(k.size() == 0);     
    }
}
