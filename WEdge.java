/**
 * Implementation of an Wedge class (for graphs), could be directed or not.
 * @param <T> data type held by the GVertex
 */
public class WEdge<T> implements Comparable<WEdge<T>> {

    /** Starting Gvertex<T> of an Wedge. */
    private GVertex<T> source;
    /** Ending Gvertex<T> of an Wedge. */
    private GVertex<T> end;
    /** Edge Weight. */
    private double weight;

    /**
     *  Create an undirected Wedge.
     *  @param u the start
     *  @param v the end
     *  @param w the weight
     */
    public WEdge(GVertex<T> u, GVertex<T> v, double w) {
        this.source = u;
        this.end = v;
        this.weight = w;
    }

    /** Is a Gvertex<T> incident to this Wedge.
     *  @param v the Gvertex<T>
     *  @return true if source or end, false otherwise
     */
    public boolean isIncident(GVertex<T> v) {
        return this.source.equals(v) || this.end.equals(v);
    }

    /** Get the starting endpoint Gvertex<T>.
     *  @return the Gvertex<T>
     */
    public GVertex<T> source() {
        return this.source;
    }

    /** Get the ending endpoint Gvertex<T>.
     *  @return the Gvertex<T>
     */
    public GVertex<T> end() {
        return this.end;
    }

    /** Get the weight of Wedge.
     *  @return the weight
     */
    public double weight() {
        return this.weight;
    }

    /** Create a string representation of the Wedge.
     *  @return the string as (source,end)
     */
    public String toString() {
        return "(" + this.source + "," + this.end + "," + this.weight + ")";
    }

    /**
     * Compares the weights of two different WEdges.
     * @param  that WEdge.
     * @return Returns -1 if this less than that, 1 f this is greater than that
     *         and returns 0 if this is equal to that.
     */
    public int compareTo(WEdge<T> that) {
        if (this.weight > that.weight) {
            return 1;
        } else if (this.weight < that.weight) {
            return -1;
        } else {
            return 0;
        }
    }

    /** Check if two Wedges are the same.
     *  @param other the Wedge to compare to this
     *  @return true if directedness and endpoints match, false otherwise
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof WEdge<?>) {
            WEdge<T> that = null;
            try {
                that = (WEdge<T>) other;
            } catch (ClassCastException e) {
                return false;
            }
            return this.source().equals(that.source())
                && this.end().equals(that.end())
                || this.source().equals(that.end())
                && this.end().equals(that.source());
        }
        return false;

    }

    /** Make a hashCode based on the toString.
     *  @return the hashCode
     */
    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

}
