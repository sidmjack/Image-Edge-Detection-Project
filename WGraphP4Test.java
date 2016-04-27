import static org.junit.Assert.*;

import java.util.List;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class WGraphP4Test {
    public WGraphP4<String> testGraph = new WGraphP4<String>();
    public List<WEdge<String>> expectedEdges = new ArrayList<WEdge<String>>();
    @Before 
    public void setup() {
        
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
    }
    @Test
    public void kruskalsTest() {
        List<WEdge<String>> k = testGraph.kruskals();
        for (WEdge<String> e: expectedEdges) {
            assertTrue(k.contains(e));
            k.remove(e);
        }
        assertTrue(k.size() == 0);
        
    }

}
