public class Pixel {

    // Pixel Location
    private int rowNum;
    private int colNum;

    // Integer Value Containing Image Pixel Informatiom
    private int pxNum;

    // Pixel Components
    private int b1; // RGB: Empty, GRAY:
    private int b2; // RGB: Red, GRAY:
    private int b3; // RGB: Green, GRAY
    private int b4; // RGB: Blue, GRAY:

    //Row Number, Column Number, Pixel Number
    public Pixel(int rN, int cN, int pN) {
        this.rowNum = rN;
        this.colNum = cN;
        this.pxNum = pN;
    }

    public int row() {
        return this.rowNum;
    }

    public int col() {
        return this.colNum;
    }

    public int value() {
        return this.pxNum;
    }

    public int getByte(int byteNum) {    
        byteNum = 4-byteNum;
        // Determine the BitMask based off of the input (desired) byteNum.
        if (0 <= byteNum && byteNum <= 3) {
            return (this.pxNum >>> (8 * byteNum)) & 0xFF;
        } else {
            return 0;
        }
    }
}