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
	      public void update(RGBStats ohmToTheNomthPower) {
	          maxR = this.get(maxR, ohmToTheNomthPower.maxR, 1);
	          maxG = this.get(maxG, ohmToTheNomthPower.maxG, 1);
	          maxB = this.get(maxB, ohmToTheNomthPower.maxB, 1);
	          minR = this.get(minR, ohmToTheNomthPower.minR, 0);
	          minG = this.get(minG, ohmToTheNomthPower.minG, 0);
	          minB = this.get(minB, ohmToTheNomthPower.minB, 0);
	          this.size += ohmToTheNomthPower.size;
	      }

	      /**
	       * gets max or min of two ints.
	       * @param one first int
	       * @param two second int
	       * @param max determins max or min. if max > 0 find max else min
	       * @return max or min between one and two
	       */
	      private int get(int one, int two, int max) {
	          if (max > 0) {//find max
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


	public boolean minDiffCond(int idA, int idB, double k) {

	}
}