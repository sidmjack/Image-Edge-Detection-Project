/**
 * Pixel Class (Will serve as the Vertex Data for Purposes of P4).
 */
public final class Pixel extends RGBSet {

    // Pixel Location:
    /** Pixel Location: Row.  */
    private final int rowNum;
    /** Pixel Location: Column.  */
    private final int colNum;
    // Integer Value Containing Image Pixel Informatiom
    /** Pixel Number/ Integer.  */
    private final int pxNum;

    /**
     * Constructs Pixel given Row, Column, and Pixel Number.
     * Pixel just holds the position and RGB values of an image point.
     * @param  rN Row Number
     * @param  cN Column Number
     * @param  pN Pixel Number.
     */
    public Pixel(int rN, int cN, int pN) {
        super(getByte(pN, 0), getByte(pN, 1), getByte(pN, 2));
        this.rowNum = rN;
        this.colNum = cN;
        this.pxNum = pN;
    }

    /**
     * Returns row position of pixel.
     * @return returns row.
     */
    public int row() {
        return this.rowNum;
    }

    /**
     * Returns column position of pixel.
     * @return returns column.
     */
    public int col() {
        return this.colNum;
    }

    /**
     * Simply returns the Pixel Value.
     * @return returns pixel value.
     */
    public int value() {
        return this.pxNum;
    }

    // /**
    //  * Returns the Pixel Byte asked for (indicated by passed integer).
    //  * @param  byteNum Desired byte in Pixel Value.
    //  * @return         returns integer value of desired byte.
    //  */
    // private int getByte(int byteNum) {    
        
    //     final int three = 3;
    //     final int eight = 8;
    //     final int mask = 0xFF;
        
    //     // Determine the BitMask based off of the input (desired) byteNum.
    //     if (0 <= byteNum && byteNum <= three) {
    //         return (this.pxNum >>> (eight * byteNum)) & mask;
    //     } else {
    //         return 0;
    //     }
    // }

    /**
     * Returns the Pixel Byte asked for (indicated by passed integer).
     * @param  pxNum number 
     * @param  byteNum Desired byte in Pixel Value.
     * @return         returns integer value of desired byte.
     */
    private static int getByte(int pxNum, int byteNum) {    
        
        final int three = 3;
        final int eight = 8;
        final int mask = 0xFF;
        
        // Determine the BitMask based off of the input (desired) byteNum.
        if (0 <= byteNum && byteNum <= three) {
            return (pxNum >>> (eight * byteNum)) & mask;
        } else {
            return 0;
        }
    }
    /**
     * toString bruh.
     * @return the row, col num and super to sting.
     */
    public String toString() {
        return "(" + this.rowNum 
                + ", " + this.colNum + ": " + super.toString() + ")";
    }

}