
public class RGBSet {
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


    public String toString() {
    	return "0x" +  String.format("%02X", r & 0xFF) +  String.format("%02X", g & 0xFF) +  String.format("%02X", b & 0xFF);
    }

}
