/** Class to represent a Gvertex (in a graph).
 */
public class GVertex<T> implements Comparable<GVertex<T>> {

    /* Note that the nextID variable had to be moved to the graph class. */

    /** GVertex unique ID number. */
    private int num;

    /** Data stored in the Gvertex. */
    private T data;

    /** Create a new Gvertex.
     *  @param d the data to store in the node
     *  @param id the unique id of the node
     */
    public GVertex(T d, int id) {
        this.data = d;
        this.num = id;
    }

    /** Get the id of this Gvertex.
     *  @return the id
     */
    public int id() {
        return this.num;
    }

    /** Get a string representation of the Gvertex.
     *  @return the string 
     */
    public String toString() {
        return this.num + "";
    }

    /** Check if two vertices are the same based on ID.
     *  @param that the Gvertex to compare to this
     *  @return true if the same, false otherwise
     */
    public boolean equals(GVertex<T> that) {
        return this.num == that.num;  // want these to be unique
    }

    /** Get the hashcode of a Gvertex based on its ID.
     *  @return the hashcode
     */
    public int hashCode() {
        return (new Integer(this.num)).hashCode();
    }

    /** Compare two vertices based on their IDs.
     *  @param that the Gvertex to compare to this
     *  @return negative if this < that, 0 if equal, positive if this > that
     */
    public int compareTo(GVertex<T> that) {
        return this.num - that.num;
    }
}
