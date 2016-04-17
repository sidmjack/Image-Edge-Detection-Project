import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

public class WGraphP4 implements Graph {

    /** Used to sequentially generate Gvertex<T> IDs for this graph! */
    private int nextID;

    /** the vertices */
    private ArrayList<GVertex<T>> verts;
    private HashMap<double>[] list;
    private int numEdges;

    public WGraphP4(int maxVerts) {
        this.nextID = 0;
        this.numEdges = 0;
        this.verts = new ArrayList<GVertex<T>>(maxVerts);
        this.list = new boolean[maxVerts][maxVerts];
    }

    @Override
    public int numVerts() {
        return this.verts.size();
    }

    @Override
    public int numEdges() {
        return this.numEdges;
    }

    @Override
    public int nextID() {
        return nextID++;
    }

    @Override
    public boolean addVertex(Object data) {
        if (this.verts.size() == this.list.length) // full
            return false;
        this.verts.add(new GVertex<T>(data, nextID++));
        return true;
    }

    @Override
    public boolean addVertex(GVertex<T> v) {
        if (this.verts.size() == this.list.length) // full
            return false;
        if (this.verts.contains(v))
            return false;  // there 
        this.verts.add(v);
        return true;
    }
    
    @Override
    public boolean addEdge(WEdge<T> e) {
        boolean added = false;
        added = addEdge(e.source(), e.end(), e.weight());
        if (added && !e.isDirected()) {
            added = addEdge(e.end(), e.source(), e.weight());
            this.numEdges--;  // don't count it twice
        }
        return added;
    }

    @Override
    public boolean addEdge(GVertex<T> v, GVertex<T> u, ) {
        boolean success = true;
        if (!this.verts.contains(v))
            success = this.addVertex(v);
        if (success && !this.verts.contains(u))
            success = this.addVertex(u);
        if (!success)
            return false;
        // put the edge in, if not already there
        if (! this.list[v.id()][u.id()]) {
            this.list[v.id()][u.id()] = true;
            this.numEdges++;
            return true;
        }
        return false;  // was already there
    }

    @Override
    public boolean deleteEdge(GVertex<T> v, GVertex<T> u) {
        if (this.verts.contains(v) && this.verts.contains(u)) {
            if (this.list[v.id()][u.id()]) {
                this.list[v.id()][u.id()] = false;
                this.numEdges--;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean areAdjacent(GVertex<T> v, GVertex<T> u) {
        return this.list[v.id()][u.id()];
    }

    @Override
    public ArrayList<GVertex<T>> neighbors(GVertex<T> v) {
        ArrayList<GVertex<T>> nbs = new ArrayList<GVertex<T>>(this.numVerts());
        int row = v.id();
        for (int col=0; col < list.length; col++) {
            if (this.list[row][col]) {
                // add Gvertex<T> associated with col to nbs
                nbs.add(this.verts.get(col));
            }
        }
        return nbs;
    }

    @Override
    public int degree(GVertex<T> v) {
        return this.neighbors(v).size();
    }

    @Override
    public boolean areIncident(WEdge<T> e, GVertex<T> v) {
        return e.source().equals(v) || e.end().equals(v);
    }

    @Override
    public List<WEdge<T>> allEdges() {
        int nv = this.numVerts();
        ArrayList<WEdge<T>> edges = new ArrayList<WEdge<T>>(nv);
        for (int r = 0; r < nv; r++) {
            for (int c = 0; c < nv; c++) {
                if (this.list[r][c]) {
                    // there is an edge, add to list
                    edges.add(new WEdge<T>(this.verts.get(r), this.verts.get(c)));
                }
                // will create duplicate edges for an undirected graph
            }
        }
        return edges;
    }

    @Override
    public List<GVertex<T>> allVertices() {
        return this.verts;
    }

    public List<GVertex<T>> depthFirst(GVertex<T> v) {
        ArrayList<GVertex<T>> reaches = new ArrayList<GVertex<T>>(this.numVerts());
        // using LinkedList<GVertex<T>> as a Stack
        LinkedList<GVertex<T>> stack = new LinkedList<GVertex<T>>();
        boolean[] visited = new boolean[this.numVerts()];  // inits to false
        stack.addFirst(v);
        visited[v.id()] = true;
        while (! stack.isEmpty()) {
            v = stack.removeFirst();
            reaches.add(v);
            for (GVertex<T> u: this.neighbors(v)) {
                if (! visited[u.id()]) {
                    visited[u.id()] = true;
                    stack.addFirst(u);
                }
            }
        }
        return reaches;
    }

    
}
