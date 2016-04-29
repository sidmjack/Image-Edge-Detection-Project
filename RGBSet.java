/**
 * holds rgb values.
 * 
 *
 */
public class RGBSet {
    /**
     * needed for toString.
     */
    private static final int MASK = 0xFF;
    /**
     * red.
     */
    int r;
    /**
     * green.
     */
    int g;
    /**
     * blues clues.
     */
    int b;


    /**
     * construtor.
     * @param newR red
     * @param newG green
     * @param newB blue
     */
    public RGBSet(int newR, int newG, int newB) {
        this.set(newR, newG, newB);
    }
    /**
     * gets red.
     * @return red
     */
    public int getR() {
        return this.r;
    }
    /**
     * gets green.
     * @return green
     */
    public int getG() {
        return this.g;
    }
    /**
     * gets blue.
     * @return blue
     */
    public int getB() {
        return this.b;
    }
    /**
     * sets red.
     * @param newR red
     */
    public void setR(int newR) {
        this.r = newR;
    }
    /**
     * sets gree.
     * @param newG green
     */
    public void setG(int newG) {
        this.g = newG;
    }
    /**
     * sets blues clues.
     * @param newB blue
     */
    public void setB(int newB) {
        this.b = newB;
    }
    /**
     * sets all colors.
     * @param newR red 
     * @param newG green
     * @param newB that blue one
     */
    public void set(int newR, int newG, int newB) {
        this.setR(newR);
        this.setG(newG);
        this.setB(newB);
    }
    /**
     * gets the min rgb of two rgbsets.
     * @param that rgbset comparing values with.
     * @return new set with min rgb values of this and that.
     */
    public RGBSet getMin(RGBSet that) {
        int minR = Math.min(this.getR(), that.getR());
        int minG = Math.min(this.getG(), that.getG());
        int minB = Math.min(this.getB(), that.getB());

        return new RGBSet(minR, minG, minB);
    }

    /**
     * gets the max rgb of two rgbsets.
     * @param that rgbset comparing values with.
     * @return new set with max rgb values of this and that.
     */
    public RGBSet getMax(RGBSet that) {
        int maxR = Math.max(this.getR(), that.getR());
        int maxG = Math.max(this.getG(), that.getG());
        int maxB = Math.max(this.getB(), that.getB());

        return new RGBSet(maxR, maxG, maxB);
    }
    /**
     * adds to rbg values by adder.
     * @param adder number adding 
     */
    public void plus(int adder) {
        this.setR(this.getR() + adder);
        this.setG(this.getG() + adder);
        this.setB(this.getB() + adder);
    }
    /**
     * compareTo fcn. 
     * @param that another rgvSet to comparet to.
     * @return true if this <= that for all rgb values
     * else false.
     */
    public boolean compareTo(RGBSet that) {
        int rCond = this.getR() - that.getR();
        int gCond = this.getG() - that.getG();
        int bCond = this.getB() - that.getB();
        return rCond <= 0 && gCond <= 0 && bCond <= 0;

    }

    /**
     * Finds the difference between the max and min RGB components.
     * @param that the other RGBSet to take a difference with
     * @return     Returns the difference set.
     */
    public RGBSet diff(RGBSet that) {
        // Determine the max and min for all RGB Stats.
        int thisR = this.getR();
        int thisG = this.getG();
        int thisB = this.getB();

        int thatR = that.getR();
        int thatG = that.getG();
        int thatB = that.getB();
        // Now deterthate the differences between that and this.
        // Return the Difference Set.
        return new RGBSet(thisR - thatR, thisG - thatG, thisB - thatB);
    }

    /**
     * toString.
     * @return string with rgv values.
     */
    public String toString() {
        return "0x" 
                + String.format("%02X", this.r & MASK) 
                + String.format("%02X", this.g & MASK) 
                + String.format("%02X", this.b & MASK);
    }

}
