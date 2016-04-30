/**
 * Name: Sidney Jackson & Lawrence Wolf-Sonkin
 * Blackboard Login: sjacks85 & lwolfso1 & eheredi1
 * Course: Data Structures 600.226.02
 **/
import java.util.HashMap;
import java.util.Set;
import java.util.Collection;
/**
 * Calculations before Unions. 
 *
 */
public class UnionConditionsCalcs {
    /**
     * hashmap of vetieicy id and rgb stats.
     */
            
    private HashMap<Integer, RGBStats> eqClasses;

    /**
     * construtor. 
     * @param pixList collection of veerticeis of pixels. 
     */
    public UnionConditionsCalcs(Collection<GVertex<Pixel>> pixList) {
        this.eqClasses = new HashMap<Integer, RGBStats>();
        for (GVertex<Pixel> gv : pixList) {
            Pixel pix = gv.data();
            this.eqClasses.put(gv.id(),
                new RGBStats(pix.getR(), pix.getG(), pix.getB()));
        }
        
    }

    /**
     * Unions 2 sets, remembering the relavent information
     * to calculating statistics for diff, and unioning conditions.
     * @param idA The ID of the root of the first equivalence class union
     * @param idB The ID of the root of the second equivalence class union
     */
    public void union(int idA, int idB) {
        if (idA != idB) {    // Merge with weighted union
            RGBStats aStats = this.eqClasses.get(idA);
            RGBStats bStats = this.eqClasses.get(idB);

            if (aStats == null || bStats == null) {
                throw new IllegalArgumentException();
            }

            RGBStats newStats = new RGBStats(aStats, bStats);

            RGBStats kickedOutVal;

            if (bStats.size > aStats.size) {
                this.eqClasses.put(idB, newStats);
                kickedOutVal = this.eqClasses.remove(idA);
                if (kickedOutVal == null) {
                    throw new IllegalArgumentException(
                            "This RGBStats object wasn't in the HashMap.");
                }
            } else {
                this.eqClasses.put(idA, newStats);
                kickedOutVal = this.eqClasses.remove(idB);
                if (kickedOutVal == null) {
                    throw new IllegalArgumentException(
                            "This RGBStats object wasn't in the HashMap.");
                }
            }
        }
    }

    /**
     * Confirms whether the "Additional Kruskal's" Condition is met.
     * @param  idA Hashmap ID for the desired set A.
     * @param  idB Hashmap ID for the desired set B.
     * @param  k  : K Value.
     * @return     Returns "True" if for operation success.
     */
    public boolean minDiffCond(int idA, int idB, double k) {
        // Grab the Desired sets, Check for Null (NONHEAD) Access Attempt.
        RGBStats aStats = this.eqClasses.get(idA);
        RGBStats bStats = this.eqClasses.get(idB);

        if (aStats == null || bStats == null) {
            System.err.println("ERR: Invalid Access Attempt- Entry is Null!");
        }

        // Initialized/Grab Size of each Set.
        int sizeA = aStats.getSize();
        int sizeB = bStats.getSize();


        RGBStats aUnionB = new RGBStats(aStats, bStats);

        RGBSet rhs = aStats.diff().getMin(bStats.diff());
        rhs.plus((int) (k / (sizeA + sizeB)));

        RGBSet lhs = aUnionB.diff();


        // Return whether the condition is satisfied.
        return lhs.compareTo(rhs);
    }
    /**
     * gets all heads.
     * @return set of integers. each being a head. 
     */
    public Set<Integer> componentsHeadIDs() {
        return this.eqClasses.keySet();
    }
    /**
     * group sizes. 
     * @param id of vertex
     * @return size
     */
    public int sizeOfGroup(int id) {
        return this.eqClasses.get(id).getSize();
    }
    /**
     * size of hashmap ov vertexies. 
     * @return number of verticies.
     */
    public int size() {
        return this.eqClasses.size();
    }
    /**
     * toString fcn.
     * @return string
     */
    public String toString() {
        return this.eqClasses.toString();
    }
}