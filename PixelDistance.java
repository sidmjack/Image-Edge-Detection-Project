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
        int [] diffRa = {diff.getR(), diff.getG(), diff.getB()};
        double dist = 0;
        for (int i = 0; i < three; ++i) {
            dist += this.square(diffRa[i]);
        }
        return dist;
    }

    /**
     * Finds the squared difference between two integers.
     * @param  a First integer parameter.
     * @param  b Second integer parameter.
     * @return   Return the squared difference b/w 'a' and 'b'.
     */
    private double sqdf(int a, int b) {
        return (a - b) * (a - b);
    }

    private double square(int a) {
        return a * a;
    }
}