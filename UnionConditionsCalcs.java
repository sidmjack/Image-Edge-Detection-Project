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
	      /**
	       * updates rgb values of with another rgbstatus object.
	       * @param ohmToTheNomthPower other rgbstatus object.
	       */
	      public RGBStats update(RGBStats ohmToTheNomthPower) {
	          
	          int Rx = this.get(maxR, ohmToTheNomthPower.maxR, true);
	          int Gx = this.get(maxG, ohmToTheNomthPower.maxG, true);
	          int Bx = this.get(maxB, ohmToTheNomthPower.maxB, true);
	          
	          int R = this.get(minR, ohmToTheNomthPower.minR, false);
	          int G = this.get(minG, ohmToTheNomthPower.minG, false);
	          int B = this.get(minB, ohmToTheNomthPower.minB, false);
	          RGBStats r = new RGBStats(Rx, Gx, Bx);
	          r.size = this.size + ohmToTheNomthPower.size;
	          r.minR = R;
	          r.minG = G;
	          r.minB = B;
	          return r;
	          
	          
	      }

	      /**
	       * gets max or min of two ints.
	       * @param one first int
	       * @param two second int
	       * @param max determins max or min. if true: max else min
	       * @return max or min between one and two
	       */
	      private int get(int one, int two, boolean max) {
	          if (max) {//find max
	              if (one > two) {
	                  return one;
	              }
	              return two;
	          } else {//find min
	              if (one > two) {
	                  return two;
	              }
	              return one;
	              
	          }
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

	// Has to be inforced that it is the root that is being passed to it.
	/**
	 * Confirms whether the "Additional Kruskal's" Condition is met.
	 * @param  idA Hashmap ID for the desired set A.
	 * @param  idB Hashmap ID for the desired set B.
	 * @param  k  : K Value.
	 * @return     Returns "True" if for operation success.
	 */
	public boolean minDiffCond(int idA, int idB, double k) {
		// Grab the Desired sets.
		RGBStats setA = this.eqClasses.get(idA);
		RGBStats setB = this.eqClasses.get(idB);
		
		// Initialized/Grab Size of each Set.
		int sizeA = setA.size;
		int sizeB = setB.size;
		
		// Check to see if the given IDs are actually heads.
		// How should I check whether the root is being passed to it?
		
		// Return whether the condition is satisfied.
		return (diff(setA, setB) <= min(setA, setB) + k/(sizeA + sizeB));
	}

	public int diff(RGBStats a, RGBStats b) {
		// Create new RBGStats Object called 'c'.
		RGBStats c = new RBGStats();
		// Determine the max and min for all RGB Stats.
		c.maxR = Math.max(a.maxR, b.maxR);
		c.maxG = Math.max(a.maxG, b.maxG);
		c.maxB = Math.max(a.maxB, b.maxB);
		c.minR = Math.min(a.maxR, b.maxR);
		c.minG = Math.min(a.maxG, b.maxG);
		c.minB = Math.min(a.maxB, b.maxB);
		// Now determine the differences between min and max.
		
		// Return the difference.
		return c;
	}

	public RGBStats min(RGBStats a, RGBStats b) {

	}

}