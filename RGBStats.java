

public class RGBStats {
    
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
     * @return   Returns the difference set.
     */
    public RGBSet diff() {
        // Now determine the differences between min and max.
        // Return the Difference Set.
        return this.max.diff(this.min);
    }

    public String toString() {
    	return "<" + max + ", " + min + ", " + size + ">";
    }


}
