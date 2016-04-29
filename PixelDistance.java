/**
 * Class that exists simply to define the distance function for Pixel.
 */
public class PixelDistance implements Distance<Pixel> {

    /**
     * Calculates the distance using two Pixels.
     * @param  one : First Pixel.
     * @param  two : Second Pixel.
     * @return     returns distance. 
     */
    public double distance(Pixel one, Pixel two) {
        // sqdf = "Squared Distance"
        final int three = 3;
        RGBSet diff = one.diff(two);
        int[] diffRa = {diff.getR(), diff.getG(), diff.getB()};
        double dist = 0;
        for (int i = 0; i < three; ++i) {
            dist += this.square(diffRa[i]);
        }
        return dist;
    }
    /**
     * squares a number a.
     * @param a number a.
     * @return squared number.
     */
    private double square(int a) {
        return a * a;
    }
}