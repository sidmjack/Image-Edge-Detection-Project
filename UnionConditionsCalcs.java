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


	public UnionConditionsCalcs(Collection<GVertex<Pixel>> pixList) {

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