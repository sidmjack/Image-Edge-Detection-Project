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

	public Pixel(int rN, int cN, int pN) {
		this.rowNum = rN;
		this.colNum = cN;
		this.pxNum = pN;
	}

	public int getRow() {
        return this.rowNum;
    }

    public int getCol() {
        return this.colNum;
    }

    public int getPixel() {
    	return this.pxNum;
    }

	public int getByte(int byteNum) {
        
        int bitMask; // Bit mask used to acquire desired Byte in bitMask.
        int b; // Byte value to return after pxNum is ANDED with bitMask.
        
        // Determine the BitMask based off of the input (desired) byteNum.
        if (byteNum == 1) {
        	bitMask = 0xF000;
        } else if (byteNum == 2) {
        	bitMask = 0x0F00;
        } else if (byteNum == 3) {
        	bitMask = 0x00F0;
        } else if (byteNum == 4) {
        	bitMask = 0x000F;
        } else {
        	bitMask = 0;
        }

        // AND pxNum with bitMask
        b = (this.pxNum & bitMask);
        return b;
    }

    public int getB1() {
        int bitMask = 0xF000;
        int b1 = (this.pxNum & bitMask);
        return b1;
    }

    public int getB2() {
        int bitMask = 0x0F00;
        int b2 = (this.pxNum & bitMask);
        return b2;
    }

    public int getB3() {
        int bitMask = 0x00F0;
        int b3 = (this.pxNum & bitMask);
        return b3;
    }

    public int getB4() {
        int bitMask = 0x000F;
        int b4 = (this.pxNum & bitMask);
        return b4;
    }
}