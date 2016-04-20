import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Set;

public class WGraphP4<VT> implements WGraph<VT> {

    /** Used to sequentially generate Gvertex<T> IDs for this graph! */
    private int nextID;

    /** the vertices */
    private ArrayList<GVertex<VT>> verts;
    private ArrayList<HashMap<Integer, Double>> edges;
    private int numEdges;

    public WGraphP4() {
        this.nextID = 0;
        this.numEdges = 0;
        this.verts = new ArrayList<GVertex<VT>>();
        this.edges = new ArrayList<HashMap<Integer, Double>>();

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
     *          false also if v's ID is outside of 0 <= id <= maxVerts
     */
    @Override
    public boolean addVertex(GVertex<VT> v) {
        boolean alreadyThere = this.verts.contains(v);
        if (alreadyThere) {
            return false;
        } else {
            int idToAdd = v.id();
            this.edges.add(idToAdd, new HashMap<Integer, Double>());
            this.verts.add(idToAdd, v);
            return true;
        }
    }
        

    /** Add a weighted edge, may also add the incident vertices. 
     *  @param e the edge to add
     *  @return false if already there, true if added
     */
    @Override
    public boolean addEdge(WEdge<VT> e) {

    }

    /** Add a weighted edge, may also add vertices. 
     *  @param v the starting vertex
     *  @param u the ending vertex
     *  @param weight the weight of the edge
     *  @return false if already there, true if added
     */
    @Override
    public boolean addEdge(GVertex<VT> v, GVertex<VT> u, double weight) {

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
        Set<Integer> idSet = this.edges.get(v.id()).keySet();
        List<GVertex<VT>> neighborList = new ArrayList<GVertex<VT>>(idSet.size());
        for (Integer id : idSet) {
            neighborList.add(this.verts.get(id));
        }
        return neighborList;
    }

    /** Return the number of edges incident to v.  
     *  @param v the vertex source
     *  @return the number of incident edges
     */
    @Override
    public int degree(GVertex<VT> v) {

    }

    /** See if an edge and vertex are incident to each other.
     *  @param e the edge
     *  @param v the vertex to check
     *  @return true if v is an endpoint of edge e
     */
    @Override
    public boolean areIncident(WEdge<VT> e, GVertex<VT> v) {

    }

    /** Return a list of all the edges.  
     *  @return the list
     */
    @Override
    public List<WEdge<VT>> allEdges() {

    }

    /** Return a list of all the vertices.  
     *  @return the list
     */
    @Override
    public List<GVertex<VT>> allVertices() {

    }

    /** Return a list of all the vertices that can be reached from v,
     *  in the order in which they would be visited in a depth-first
     *  search starting at v.  
     *  @param v the starting vertex
     *  @return the list of reachable vertices
     */
    @Override
    public List<GVertex<VT>> depthFirst(GVertex<VT> v) {

    }

    /** Return a list of all the edges incident on vertex v.  
     *  @param v the starting vertex
     *  @return the incident edges
     */
    @Override
    public List<WEdge<VT>> incidentEdges(GVertex<VT> v) {

    }

    /** Return a list of edges in a minimum spanning forest by
     *  implementing Kruskal's algorithm using fast union/finds.
     *  @return a list of the edges in the minimum spanning forest
     */
    @Override
    public List<WEdge<VT>> kruskals() {

    }

    private boolean vertexInGraph(GVertex<VT> vtx) {
        return this.verts.contains(vtx);
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
    
}
