
/** Distance Interface */
public interface Distance<VT> {
	/**
	 * Method that interface is soley comprised of. 
	 * @param  one First object compared.
	 * @param  two Second object compared.
	 * @return     Returns the distance between the two compared objects.
	 */
	double distance(VT one, VT two);
}