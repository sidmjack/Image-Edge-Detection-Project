import java.util.HashMap;
import java.util.Collection;

public class UnionConditionsCalcs {
	
	private class RGBSet {
		int r;
		int g;
		int b;


		public RGBSet(int newR, int newG, int newB) {
			this.set(newR, newG, newB);
		}

		public int getR() {
			return r;
		}

		public int getG() {
			return g;
		}

		public int getB() {
			return b;
		}

		public void setR(int newR) {
			this.r = newR;
		}

		public void setG(int newG) {
			this.g = newG;
		}

		public void setB(int newB) {
			this.b = newB;
		}

		public void set(int newR, int newG, int newB) {
			this.setR(newR);
			this.setG(newG);
			this.setB(newB);
		}

		public RGBSet getMin(RGBSet that) {
			int minR = Math.min(this.getR(), that.getR());
			int minG = Math.min(this.getG(), that.getG());
			int minB = Math.min(this.getB(), that.getB());

			return new RGBSet(minR, minG, minB);
		}


		public RGBSet getMax(RGBSet that) {
			int maxR = Math.max(this.getR(), that.getR());
			int maxG = Math.max(this.getG(), that.getG());
			int maxB = Math.max(this.getB(), that.getB());

			return new RGBSet(maxR, maxG, maxB);
		}

		public void plus(int adder) {
			this.setR(this.getR() + adder);
			this.setG(this.getG() + adder);
			this.setB(this.getB() + adder);
		}


	}


	private class RGBStats {
		
		RGBSet max;
		RGBSet min;


		int size;

		public RGBStats(int r, int g, int b) {

			this.max = new RGBSet(r, g, b);
			this.min = new RGBSet(r, g, b);
			this.size = 1;
		}

		public RGBStats(RGBStats first, RGBStats second) {
			this.min = first.min.getMin(second.min);
			this.max = first.max.getMax(second.max);
			this.size = first.size + second.size;
		}

		public int getSize() {
			return this.size;
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

			if (aStats == null || bStats == null) {
				throw new IllegalArgumentException();
			}

			RGBStats newStats = new RGBStats(aStats, bStats);

			if (bStats.size > aStats.size) {
				this.eqClasses.put(idB, newStats);
				this.eqClasses.remove(idA);
			} else {
				this.eqClasses.put(idA, newStats);
				this.eqClasses.remove(idB);
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