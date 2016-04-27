import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
//import java.awt.Graphics2D;
//import java.awt.Image;
//import java.awt.Toolkit;
import java.util.List;
//import java.util.ArrayList;
import java.util.LinkedList;

/** MakeGraph creates a WGraph given an image and K value.*/
public final class MakeGraph {

    /** To appease checkstyle. */
    private MakeGraph() {
        // Does Absolutely Nothing.
    }

    /**
     * Converts an image to a Pixel Graph.
     * @param  image Image Passed through main.
     * @param  pd    Function passed through main.
     * @return       returns a pixel graph form the image given.
     */
    static WGraph<Pixel> imageToGraph(BufferedImage image, Distance<Pixel> pd) {
        //Create a Pixel WGraph. 
        WGraphP4<Pixel> pixelGraph = new WGraphP4<Pixel>();

        //Determine "height"/"width" of the image in pixels.
        int height = image.getHeight();
        int width = image.getWidth();
        // Temp Pixel
        Pixel tempP;
        // Temp RGB
        int tempRGB;
        // Here, add the pixels into the pixelGraph.
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                tempRGB = image.getRGB(i, j); // Get RGB Value of Pixel.
                tempP = new Pixel(i, j, tempRGB); // Create new Pixel.
                pixelGraph.addVertex(tempP); //Add new Pixel.
            }
        }
        
        // Create a list containing all verticies in pixelGraph
        LinkedList<GVertex<Pixel>> verts 
            = new LinkedList<GVertex<Pixel>>(pixelGraph.allVertices());

        // Using the known shape of the image and the collected pixels, 
        // create add the edges of the image to the pixelGraph.
        int vertID = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                formEdges(pixelGraph, verts, vertID, height, width, pd);
                vertID++;
            }
        }
        return pixelGraph;
    }
    
    /**
     * Decides which vertex edge to form based off of pixel location in image.
     * @param pixelGraph : the Image Graph
     * @param verts      : list of vertices form pixelGraph
     * @param vertID     : the Vertex ID of the observed vertex.
     * @param h          : the Height of the image.
     * @param w          : the Width of the image.
     * @param pd         : the distance function for pixel.
     */
    static void formEdges(WGraphP4<Pixel> pixelGraph,
        LinkedList<GVertex<Pixel>> verts, int vertID,
        int h, int w, Distance<Pixel> pd) {
        
        // Q: Is this what we want "dist = pd.distance(vertID, endID);"?
        // Q: Should the helper's helper method not be names "addEdge"

        // Helper Method Operation Variables.
        int endID;   // Vertex ID of Vertex to serve as "end" of edge
        double dist; // Distance b/w 2 pixels found using distance function.  

        // Add edge if the pixel doesn't belong to the top edge of the image.
        if (vertID > w) {
            endID = vertID - w; // North Vertex.
            addEdge(pixelGraph, verts, vertID, endID, pd); // Add N Vertex.
        }
        
        // Add edge if the pixel doesn't belong to the bottom edge of the image.
        if (vertID <= (w * h)) {
            endID = vertID + w; // South Vertex.
            addEdge(pixelGraph, verts, vertID, endID, pd); // Add S Vertex.
        }
        
        // Add edge if the pixel doesn't belong to the left edge of the image.
        if ((vertID % w) != 0) {
            endID = vertID - 1; // West Vertex.
            addEdge(pixelGraph, verts, vertID, endID, pd); // Add W Vertex.
        }
        
        // Add edge if the pixel doesn't belong to the right edge of the image.
        if ((vertID % w) != 1) {
            endID = vertID + 1; // East Vertex.
            addEdge(pixelGraph, verts, vertID, endID, pd); // Add E Vertex.
        }
    }

    /**
     * Helper Method for formsEdges that adds a new edge to the pixelGraph.
     * @param pixelGraph : the graph that we're adding edges to.
     * @param verts      : the list of vertices form pixelGraph
     * @param vertID     : the Vertex ID of the "Source" Pixel.
     * @param endID      : the Vertex ID of the "End" Pixel
     * @param pd         : the distance function for pixel.
     */
    static void addEdge(WGraphP4<Pixel> pixelGraph, 
        LinkedList<GVertex<Pixel>> verts, int vertID, int endID,
        Distance<Pixel> pd) {
        
        // Helper Method Operation Variables.
        Pixel p1;    // Pixel one, used to calculate distance (weight).
        Pixel p2;    // Pixel two, used to calculate distance (weight). 
        GVertex<Pixel> pixVert1; // Vertex Pixel 1 needed to create edge.
        GVertex<Pixel> pixVert2; // Vertex Pixel 2 needed to create edge.
        double dist; // Distance <- Weight needed to add edge to pixelGraph.
        
        // Create teh Pixels.
        p1 = verts.get(vertID).data(); 
        p2 = verts.get(endID).data(); 
        dist = pd.distance(p1, p2);
        
        //Add the edge given the Pixels p1 & p2, and the weight (dist)
        pixelGraph.addEdge(pixVert1, pixVert2, dist);

    }


    /** Return a list of edges in a minimum spanning forest by
     *  implementing Kruskal's algorithm using fast union/finds.
     *  @param g the graph to segment
     *  @param kvalue the value to use for k in the merge test
     *  @return a list of the edges in the minimum spanning forest
     */

    //static List<WEdge<Pixel>> segmenter(WGraph<Pixel> g, double kvalue) {
        //Not yet implemented...
        //List<WEdge<Pixel>> edgeList = g.kruskals(k);
    //}

    /**
     * Where the Pixel Graph is Made, Kruskal's is tested, and edges are ouput.
     * @param args : the image file name and Kruskals K value.
     */
    public static void main(String[] args) {

        final int gray = 0x202020;

        try {
          // the line that reads the image file

            BufferedImage image = ImageIO.read(new File(args[0]));
            WGraph<Pixel> g = imageToGraph(image, new PixelDistance());
            List<WEdge<Pixel>> res = segmenter(g, Double.parseDouble(args[1]));

            System.out.print("result =  " + res.size() + "\n");
            System.out.print("NSegments =  "
                             + (g.numVerts() - res.size()) + "\n");

            // make a background image to put a segment into
            for (int i = 0; i < image.getHeight(); i++) {
                for (int j = 0; j < image.getWidth(); j++) {
                    image.setRGB(j, i, gray);
                }
            }

            // Couldn't we just use res as 'x'?

            // After you have a spanning tree connected component x, 
            // you can generate an output image like this:
            for (GVertex<Pixel> i: x)  {
                Pixel d = i.data();
                image.setRGB(d.col(), d.row(), d.value());
            }

            File f = new File("output.png");
            ImageIO.write(image, "png", f);

            // You'll need to do that for each connected component,
            // writing each one to a different file, clearing the
            // image buffer first

        } catch (IOException e) {
            System.out.print("Missing File!\n");

            // log the exception
            // re-throw if desired
        }
    }

}
