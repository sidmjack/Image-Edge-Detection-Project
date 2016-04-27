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

	}


	private HashMap<Integer, RGBStats> eqClasses;


	public UnionConditionsCalcs(Collection<Pixel> pixList) {

	}

	public void union(int idA, int idB) {

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