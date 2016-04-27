/**
 * Pixel Class (Will serve as the Vertex Data for Purposes of P4).
 */
public class Pixel {

    // Pixel Location:
    /** Pixel Location: Row.  */
    private int rowNum;
    /** Pixel Location: Column.  */
    private int colNum;
    // Integer Value Containing Image Pixel Informatiom
    /** Pixel Number/ Integer.  */
    private int pxNum;
    //Pixel Components:
    /** Pixel Component (Byte) 1. */
    private int b1; // RGB: Empty
    /** Pixel Component (Byte) 2. */
    private int b2; // RGB: Red
    /** Pixel Component (Byte) 3. */
    private int b3; // RGB: Green
    /** Pixel Component (Byte) 4. */
    private int b4; // RGB: Blue

    /**
     * Constructs Pixel given Row, Column, and Pixel Number.
     * Pixel just holds the position and RGB values of an image point.
     * @param  rN Row Number
     * @param  cN Column Number
     * @param  pN Pixel Number.
     */
    public Pixel(int rN, int cN, int pN) {
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

    /**
     * Returns the Pixel Byte asked for (indicated by passed integer).
     * @param  byteNum Desired byte in Pixel Value.
     * @return         returns integer value of desired byte.
     */
    private int getByte(int byteNum) {    
        
        final int three = 3;
        final int four = 4;
        final int eight = 8;
        final int mask = 0xFF;
        
        byteNum = four - byteNum;
        // Determine the BitMask based off of the input (desired) byteNum.
        if (0 <= byteNum && byteNum <= three) {
            return (this.pxNum >>> (eight * byteNum)) & mask;
        } else {
            return 0;
        }
    }

    /**
     * Gets the Red component of the Pixel
     * @return Red component
     */
    public int getRed() {
        return this.getByte(0);
    }

    /**
     * Gets the Green component of the Pixel
     * @return Green component
     */
    public int getGreen() {
        return this.getByte(1);
    }


    /**
     * Gets the Blue component of the Pixel
     * @return Blue component
     */
    public int getBlue() {
        return this.getByte(2);
    }

}