import java.util.HashMap;
import java.util.Set;
import java.util.Collection;

public class UnionConditionsCalcs {
    
    private class RGBSet implements Comparable<RGBSet> {
        int r;
        int g;
        int b;


        public RGBSet(int newR, int newG, int newB) {
            this.set(newR, newG, newB);
        }

        public int getR() {
            return r;
        }

        public int getG() {
            return g;
        }

        public int getB() {
            return b;
        }

        public void setR(int newR) {
            this.r = newR;
        }

        public void setG(int newG) {
            this.g = newG;
        }

        public void setB(int newB) {
            this.b = newB;
        }

        public void set(int newR, int newG, int newB) {
            this.setR(newR);
            this.setG(newG);
            this.setB(newB);
        }

        public RGBSet getMin(RGBSet that) {
            int minR = Math.min(this.getR(), that.getR());
            int minG = Math.min(this.getG(), that.getG());
            int minB = Math.min(this.getB(), that.getB());

            return new RGBSet(minR, minG, minB);
        }


        public RGBSet getMax(RGBSet that) {
            int maxR = Math.max(this.getR(), that.getR());
            int maxG = Math.max(this.getG(), that.getG());
            int maxB = Math.max(this.getB(), that.getB());

            return new RGBSet(maxR, maxG, maxB);
        }

        public void plus(int adder) {
            this.setR(this.getR() + adder);
            this.setG(this.getG() + adder);
            this.setB(this.getB() + adder);
        }

        public int compareTo(RGBSet that) {
            int rCond = this.getR() - that.getR();
            int gCond = this.getG() - that.getG();
            int bCond = this.getB() - that.getB();
            if (rCond == 0 && gCond == 0 && bCond == 0) {
                return 0;
             } else if (rCond <= 0 && gCond <= 0 && bCond <= 0) {
                return 1;
             } else {
                return -1;
             }
        }

        public String toString() {
        	return "0x" + Integer.toHexString(r) + Integer.toHexString(g) + Integer.toHexString(b);
        }


    }


    private class RGBStats {
        
        RGBSet max;
        RGBSet min;


        int size;

        public RGBStats(int r, int g, int b) {

            this.max = new RGBSet(r, g, b);
            this.min = new RGBSet(r, g, b);
            this.size = 1;
        }

        public RGBStats(RGBStats first, RGBStats second) {
            this.min = first.min.getMin(second.min);
            this.max = first.max.getMax(second.max);
            this.size = first.size + second.size;
        }

        public int getSize() {
            return this.size;
        }

        /**
         * Finds the difference between the max and min RGB components.
         * @param  a : a RGBStat
         * @param  b : b RGBStat
         * @return   Returns the difference set.
         */
        public RGBSet diff(RGBStats that) {
            // Determine the max and min for all RGB Stats.
            int maxR = Math.max(this.max.getR(), that.max.getR());
            int maxG = Math.max(this.max.getG(), that.max.getG());
            int maxB = Math.max(this.max.getB(), that.max.getB());
            int minR = Math.min(this.min.getR(), that.min.getR());
            int minG = Math.min(this.min.getG(), that.min.getG());
            int minB = Math.min(this.min.getB(), that.min.getB());
            // Now determine the differences between min and max.
            // Return the Difference Set.
            return new RGBSet(maxR - minR, maxG - minG, maxB - minB);
        }

        public RGBSet diff() {
            // Determine the max and min for all RGB Stats.
            int maxR = this.max.getR();
            int maxG = this.max.getG();
            int maxB = this.max.getB();
            int minR = this.min.getR();
            int minG = this.min.getG();
            int minB = this.min.getB();
            // Now determine the differences between min and max.
            // Return the Difference Set.
            return new RGBSet(maxR - minR, maxG - minG, maxB - minB);
        }

        public String toString() {
        	return "<" + max + ", " + min + ", " + size + ">";
        }


    }


    private HashMap<Integer, RGBStats> eqClasses;


    public UnionConditionsCalcs(Collection<GVertex<Pixel>> pixList) {
        this.eqClasses = new HashMap<Integer, RGBStats>();
        for (GVertex<Pixel> gv : pixList) {
            Pixel pix = gv.data();
            eqClasses.put(gv.id(),
                new RGBStats(pix.getRed(), pix.getGreen(), pix.getBlue()));
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

            if (bStats.size > aStats.size) {
                this.eqClasses.put(idB, newStats);
                this.eqClasses.remove(idA);
            } else {
                this.eqClasses.put(idA, newStats);
                this.eqClasses.remove(idB);
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
        RGBStats setA = this.eqClasses.get(idA);
        RGBStats setB = this.eqClasses.get(idB);

        if (setA == null || setB == null) {
             System.err.println("ERR: Invalid Access Attempt- Entry is Null!");
        }

        // Initialized/Grab Size of each Set.
        int sizeA = setA.getSize();
        int sizeB = setB.getSize();


        RGBStats aUnionB = new RGBStats(setA, setB);

        RGBSet rhs = setA.diff().getMin(setB.diff());
        rhs.plus((int)(k / (sizeA + sizeB)));


        // Return whether the condition is satisfied.
        return (aUnionB.diff()).compareTo(rhs) >= 0;
    }

    public Set<Integer> componentsHeadIDs() {
    	return this.eqClasses.keySet();
    }

    public String toString() {
    	return this.eqClasses.toString();
    }
}