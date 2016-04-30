/**
 * Name: Sidney Jackson & Lawrence Wolf-Sonkin
 * Blackboard Login: sjacks85 & lwolfso1 & eheredi1
 * Course: Data Structures 600.226.02
 **/
/** Distance Interface.
  * @param <VT> Generic. */
public interface Distance<VT> {
    /**
     * Method that interface is soley comprised of. 
     * @param  one First object compared.
     * @param  two Second object compared.
     * @return     Returns the distance between the two compared objects.
     */
    double distance(VT one, VT two);
}