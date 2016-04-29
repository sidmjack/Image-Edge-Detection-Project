
/**
 * 
 * holds max and min vlaues.
 */
public class RGBStats {
    /**
     * max.
     */
    RGBSet max;
    /**
     * min.
     */
    RGBSet min;

    /**
     * size.
     */
    int size;
    /**
     * Construtor.
     * @param r red
     * @param g green
     * @param b blue
     * makes max / min all given values and 
     * size 1.
     */
    public RGBStats(int r, int g, int b) {

        this.max = new RGBSet(r, g, b);
        this.min = new RGBSet(r, g, b);
        this.size = 1;
    }
    /**
     * construtor.
     * @param first rgbstats set
     * @param second rgbsstats set
     * makes rgbstats given two others and 
     * sets correct max min values
     */
    public RGBStats(RGBStats first, RGBStats second) {
        this.min = first.min.getMin(second.min);
        this.max = first.max.getMax(second.max);
        this.size = first.size + second.size;
    }
    /**
     * returns size.
     * @return int size.
     */
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
    /**
     * toString fcn.
     * @return string of <max, min, size>
     */
    public String toString() {
        return "<" + this.max + ", " + this.min + ", " + this.size + ">";
    }


}
