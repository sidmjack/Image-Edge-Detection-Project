import java.util.HashMap;
import java.util.Collection;

public class UnionConditionsCalcs {
	
	private class RGBStats {
		
		int maxR;
		int maxG;
		int maxB;

		int minR;
		int minG;
		int minB;

		int size;
	      private RGBStats(int R, int G, int B) {
	            maxR = R; minR = R;
	            maxG = G; minG = G;
	            maxB = B; minB = B;
	            size = 1;
	        }

	}


	private HashMap<Integer, RGBStats> eqClasses;


	public UnionConditionsCalcs(Collection<GVertex<Pixel>> pixList) {
	    for (GVertex<Pixel> gv: pixList) {
	       eqClasses.put(gv.id(), 
	               new RGBStats(gv.data().getRed(), gv.data().getGreen(), gv.data().getBlue()));
	    }
	    
	}

	public void union(int idA, int idB) {
		int root1 = this.find(a);     // Find root of node a
		int root2 = this.find(b);     // Find root of node b
		if (root1 != root2) {    // Merge with weighted union
		    if (this.weight[root2] > this.weight[root1]) {
		        this.parent[root1] = root2;
		        this.weight[root2] += this.weight[root1];
		    } else {
		        this.parent[root2] = root1;
		        this.weight[root1] += this.weight[root2];
		    }
		}

	}


	public boolean minDiffCond(int idA, int idB, double k) {

	}
}