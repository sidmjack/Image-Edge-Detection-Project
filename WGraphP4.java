import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class WGraphP4<VT> implements WGraph<VT> {

    /** Used to sequentially generate Gvertex<T> IDs for this graph! */
    private int nextID;

    

    /** the vertices */
    private HashMap<Integer, GVertex<VT>> verts;
    private HashMap<Integer, HashMap<Integer, Double>> edges;
    private int numEdges;

    public WGraphP4() {
        this.nextID = 0;
        this.numEdges = 0;
        this.verts = new HashMap<Integer, GVertex<VT>>();
        this.edges = new HashMap<Integer, HashMap<Integer, Double>>();
    }

    /** Get the number of edges. 
     *  @return the number
     */
    @Override
    public int numEdges() {
        return this.numEdges;
    }

    /** Get the number of vertices. 
     *  @return the number
     */
    @Override
    public int numVerts() {
        return this.verts.size();
    }

    /** Get the next ID to use in making a vertex. 
     *  @return the id
     */
    @Override
    public int nextID() {
        return this.nextID++;
    }

    /** Create and add a vertex to the graph.
     *  @param d the data to store in the vertex
     *  @return true if successful, false otherwise
     */
    @Override
    public boolean addVertex(VT d) {
        GVertex<VT> vtx = new GVertex<VT>(d, this.nextID());
        return addVertex(vtx);
    }

    /** Add a vertex if it doesn't exist yet. 
     *  @param v the vertex to add
     *  @return false if already there, true if added
     */
    @Override
    public boolean addVertex(GVertex<VT> v) {
        if (this.vertexInGraph(v)) {
            return false;
            // realistically this condition shouldn't happen
            // unless it's being called by addEdge()
            
        } else {
            this.verts.put(v.id(), v);
            this.edges.put(v.id(), new HashMap<Integer, Double>());
            return true;
        }
    }
        

    /** Add a weighted edge, may also add the incident vertices. 
     *  @param e the edge to add
     *  @return false if already there, true if added
     */
    @Override
    public boolean addEdge(WEdge<VT> e) {                
        return addEdge(e.source(), e.end(), e.weight());  
    }

    /** Add a weighted edge, may also add vertices. 
     *  @param v the starting vertex
     *  @param u the ending vertex
     *  @param weight the weight of the edge
     *  @return false if already there, true if added
     */
    @Override
    public boolean addEdge(GVertex<VT> v, GVertex<VT> u, double weight) {
        this.addVertex(v);
        this.addVertex(u);
        HashMap<Integer, Double> tempH = this.edges.get(v.id());
        HashMap<Integer, Double> tempHe = this.edges.get(u.id());
        if(tempH.containsKey(u.id())) {
            return false;
            
        } else {
            tempH.put(u.id(), weight);
            tempHe.put(v.id(), weight);
            this.numEdges++;
            return true;
        }
    }

    /** Remove an edge if there.  
     *  @param v the starting vertex
     *  @param u the ending vertex
     *  @return true if delete, false if wasn't there
     */
    @Override
    public boolean deleteEdge(GVertex<VT> v, GVertex<VT> u) {
        boolean verticesExist = this.vertexInGraph(v) && this.vertexInGraph(u);
        if (verticesExist) {
            //don't check if the edge is already there before removing
            //since it makes no difference. If it isn't there, null will be
            //returned and the hashmap won't be changed
            //if it is there, it'll be removed, so there's no reason to check.
            boolean edgeExists = true;
            edgeExists &= this.removeEdgeDirectional(v.id(), u.id()) != null;
            edgeExists &= this.removeEdgeDirectional(u.id(), v.id()) != null;
            if (edgeExists) {
                this.numEdges--;
                return true;
            }
        }
        return false;
    }

    /** Return true if there is an edge between v and u. 
     *  @param v the starting vertex
     *  @param u the ending vertex
     *  @return true if there is an edge between them, false otherwise
     */
    @Override
    public boolean areAdjacent(GVertex<VT> v, GVertex<VT> u) {
        // symmetry is assumed
        return this.edges.get(v.id()).containsKey(u.id());
    }

    /** Return a list of all the neighbors of vertex v.  
     *  @param v the vertex source
     *  @return the neighboring vertices
     */
    @Override
    public List<GVertex<VT>> neighbors(GVertex<VT> v) {
        if (this.vertexInGraph(v)) {
            Set<Integer> idSet = this.edges.get(v.id()).keySet();
            List<GVertex<VT>> neighborList = new ArrayList<GVertex<VT>>(idSet.size());
            for (Integer id : idSet) {
                neighborList.add(this.getAssociatedVertex(id));
            }
            return neighborList;
        } else {
            return null;
        }
    }

    /** Return the number of edges incident to v.  
     *  @param v the vertex source
     *  @return the number of incident edges
     */
    @Override
    public int degree(GVertex<VT> v) {
        if (this.vertexInGraph(v)) {
            return this.edges.get(v.id()).keySet().size();
        }
        
        return -1;
    }

    /** See if an edge and vertex are incident to each other.
     *  @param e the edge
     *  @param v the vertex to check
     *  @return true if v is an endpoint of edge e
     */
    @Override
    public boolean areIncident(WEdge<VT> e, GVertex<VT> v) {
        return e.source().equals(v) || e.end().equals(v);
    }

    /** Return a list of all the edges.  
     *  @return the list
     */
    @Override
    public List<WEdge<VT>> allEdges() {
        int nv = this.numVerts();
        ArrayList<WEdge<VT>> edgesList = new ArrayList<WEdge<VT>>(nv);

        for (Map.Entry<Integer, HashMap<Integer, Double>> index : this.edges.entrySet()) {

            GVertex<VT> start = this.getAssociatedVertex(index.getKey());

            for(Map.Entry<Integer, Double> entry : index.getValue().entrySet()) {
                Double weight = entry.getValue();
                GVertex<VT> end = this.getAssociatedVertex(entry.getKey());
                WEdge<VT> tempEdge = new WEdge<VT>(start, end, weight);
                edgesList.add(tempEdge); 
            }
        }
        return edgesList;
    }

    /** Return a list of all the vertices.  
     *  @return the list
     */
    @Override
    public List<GVertex<VT>> allVertices() {
        return new LinkedList<GVertex<VT>>(this.verts.values());
    }

    /** Return a list of all the vertices that can be reached from v,
     *  in the order in which they would be visited in a depth-first
     *  search starting at v.  
     *  @param v the starting vertex
     *  @return the list of reachable vertices
     */
    @Override
    public List<GVertex<VT>> depthFirst(GVertex<VT> v) {
        ArrayList<GVertex<VT>> reaches = new ArrayList<GVertex<VT>>(this.numVerts());
        // using LinkedList<Vertex> as a Stack
        LinkedList<GVertex<VT>> stack = new LinkedList<GVertex<VT>>();
        boolean[] visited = new boolean[this.numVerts()];  // inits to false
        stack.addFirst(v);
        visited[v.id()] = true;
        while (! stack.isEmpty()) {
            v = stack.removeFirst();
            reaches.add(v);
            for (GVertex<VT> u: this.neighbors(v)) {
                if (! visited[u.id()]) {
                    visited[u.id()] = true;
                    stack.addFirst(u);
                }
            }
        }
        return reaches;
    }

    /** Return a list of all the edges incident on vertex v.  
     *  @param v the starting vertex
     *  @return the incident edges
     */
    @Override
    public List<WEdge<VT>> incidentEdges(GVertex<VT> v) {
        List<WEdge<VT>> allEdges = this.allEdges();
        List<WEdge<VT>> iEdges = new ArrayList <WEdge<VT>>();
        for (WEdge<VT> edge: allEdges) {
            if (this.areIncident(edge, v)) {
                iEdges.add(edge);
            }
        }
        return iEdges;
    }

    /** Return a list of edges in a minimum spanning forest by
     *  implementing Kruskal's algorithm using fast union/finds.
     *  @return a list of the edges in the minimum spanning forest
     */
    @Override
    public List<WEdge<VT>> kruskals() {
       /* int counter = 0;
        for (GVertex<VT> vert : this.verts) {
            int temp;
            temp = vert.id();
            temp = counter;
            counter++;
        }*/
        PQHeap<WEdge<VT>> pQueue = new PQHeap<WEdge<VT>>();
        pQueue.init(this.allEdges());
        Partition p = new Partition(this.numEdges);
        List<WEdge<VT>> edgeSet = new ArrayList<WEdge<VT>>();
        int addEdges = 0;
        int vert = this.numVerts();
        while (addEdges < vert && pQueue.size() != 0) {
            WEdge<VT> small = pQueue.remove();
            //small is the current smallest edge 
            int uset = p.find(small.source().id());
            // uset is the id of one of small's two verticies
            int vset = p.find(small.end().id());
            // vset is the id of small's second vertex
            if (uset != vset) {
                //meaning I can add an edge 
                addEdges++;
                edgeSet.add(small);
                p.union(uset, vset);
            }
            
        }
       return edgeSet;
        
        
    }

    private boolean vertexInGraph(GVertex<VT> vtx) {
        return this.verts.containsValue(vtx);
    }

    /**
     * Removes a directional edge
     * @param  firstID  ID of source vertex
     * @param  secondID ID of destination vertex
     * @return          weight of removed vertex
     */
    private Double removeEdgeDirectional(int firstID, int secondID) {
        return this.edges.get(firstID).remove(secondID);
    }
    
    private GVertex<VT> getAssociatedVertex(int id) {
        return this.verts.get(id);
    }
    /**
     * test main.
     * @param args
     */
    public static void main(String args[]) {
        WGraphP4<String> graph = new WGraphP4<String>();

        GVertex<String> hi = new GVertex<String>("hi", 0);
        GVertex<String> hellow = new GVertex<String>("hellow", 1);
        GVertex<String> bye = new GVertex<String>("bye", 2);
        GVertex<String> cya = new GVertex<String>("cya", 3);

        graph.addEdge(hi, hellow, 2);
        graph.addEdge(bye, cya, 3);
        graph.addEdge(hi, cya, 4);
        graph.addEdge(hellow, cya, 10);

        
        for(WEdge<String> e: graph.kruskals()) {
            System.out.println(e.weight());
        }

        
    }
}
