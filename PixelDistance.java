public class PixelDistance implements Distance<Pixel> {

    /** Get the distance between two vertices. 
     *  @return the distance.
     */
    public double distance(Pixel one, Pixel two) {
        // sqdf = "Squared Distance"
        double dist = 0;
        for (int i = 1; i <= 4; ++i) {
            dist += sqdf(one.getByte(i), two.getByte(i));
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

}