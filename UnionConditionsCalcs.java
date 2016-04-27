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

	/**
	 * Unions 2 sets, remembering the relavent information
	 * to calculating statistics for diff, and unioning conditions.
	 * @param idA The ID of the root of the first equivalence class union
	 * @param idB The ID of the root of the second equivalence class union
	 */
	public void union(int idA, int idB) {
		if (idA != idB) {    // Merge with weighted union
			RGBStats aStats = this.eqClasses.get(idA);
			RGBStats bStats = this.eqClasses.get(idB);

			if (bStats.size > aStats.size) {
				bStats.update(aStats);
				this.eqClasses.put(idB, bStats);
				this.eqClasses.remove(idA);
			} else {
				aStats.update(bStats);
				this.eqClasses.put(idA, aStats);
				this.eqClasses.remove(idB);
			}
		}
	}


	public boolean minDiffCond(int idA, int idB, double k) {

	}
}