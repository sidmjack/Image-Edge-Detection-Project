/**
 * Name: Sidney Jackson & Lawrence Wolf-Sonkin
 * Blackboard Login: sjacks85 & lwolfso1 
 * Course: Data Structures 600.226.02 
 * Assignment: Written Assignment 4
 * Description: PQ JUnit Testing
 * Due Date: April 13, 2016
 */

// NOTE: For the most part, this JUNIT test is pretty modular 
// (as far as the tests not depending on one another). There's
// an exception to this with the use of the size() and remove()
// functions; however, whenever another MPQ function that hasn't
// yet been tested is used to help test some other method, an
// error message was written to inform where the error actually
// may have occured.

// NOTE: Alot of the code is repeated while performing tests that could
// easily have been written in a single test. The division of tests into
// simpler tests (along with error message) was intended to make it easier 
// to track down troublesome methods/functions.

/** JUNIT TESTING FOR "PQHeap.java":
 *  Comprehensive Description: Tests Max Priority Queue Heap.

 *  (1) Explicit Tests for isEmpty() Method to ensure queue is empty.
 *  (2) Explicit Tests for init(Collection<T> values) Method to initialize 
 *      empty queue with values provided by Collection<T> Values.
 *  (3) Explicit Tests for init(Collection<T> values) Method to overwrite
 *      nonempty queue with values provided by Collection<T> Values.
 *  (4) Explicit Tests for isClear() Method to esnrue all values are cleared.
 *  (5) Explicit Tests for size() Method against EMPTY queue.
 *  (6) Explicit Tests for size() Method against NON-EMPTY queue.
 *  (7) Explicit Tests for size() Method against dynamically 
 *      changing queue for init() (and insertions/deletions after 
 *      those methods are double checked for functionality).
 *  (8) Explicit Tests for size() Method when duplicate values are present.
 *  (9) Explicit Tests for insert(T val) Method to ensure insertions are 
 *      actually made (checks if size agrees with number of insertions).
 *  (10)Explicit Tests for insert(T val) Method to ensure that after 
 *      insertions are made, the max value is the expected value.
 *  (11)Explicit Tests for insert(T val) Method to ensure insertions allow
 *      for duplicate values.   
 *  (12)Explicit Tests for peek() Method to ensure the values "returned"
 *      is the expected maximum value.
 *  (13)Explicit Tests for peek() Method to ensure that the maximum
 *      value isn't actually removed (size remains the same).
 *  (14)Explicit Tests for peek() Method to ensure that an exception
 *      is thrown when an attempt to get max is made on an empty queue.     
 *  (15)Explicit Tests for remove() Method to ensure the expected max
 *      value is actually removed (Ensure Size()--). 
 *  (16)Explicit Tests for remove() Method to ensure the expected max
 *      value is found, returned, and removed after the removeMax operation. 
 *  (17)Explicit Tests for remove() Method to ensure that after the
 *      removal is complete, the new max is the expected new max.
 *  (18)Explicit Tests for remove() Method to ensure the expected max
 *      values when duplicates are present operates as expected.  
 *  (19)Explicit Tests for remove() Method to ensure that when run 
 *      against duplicate values, that only one duplicate value is removed
 *      as well as that the new max is equivalent to the value removed.
 *  (20)Explicit Tests for remove() Method to ensure that an exception
 *      is thrown if a remove operation is attempted on an empty queue.  
 */

/*Junit Imports: */
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/*Java Library Imports: */
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

/** TESTING BEGINS HERE */
public class MaxPriorityQueueTest {

    static PQHeap<Integer> intMPQ;

    static PQHeap<String> strMPQ;  
    
    static Integer[] intArray = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    static ArrayList<Integer> intVals;
    static Collection<Integer> intCol;
    static Integer[] intArray2 = {5, 15, 10, 25, 20};
    static ArrayList<Integer> intVals2;
    static Collection<Integer> intCol2;
    static String[] strArray1 = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    static ArrayList<String> strVals1;
    static Collection<String>  strCol1;
    static String[] strArray2 = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K"};
    static ArrayList<String> strVals2;
    static Collection<String> strCol2;


    private static class MaxComparator<T extends Comparable<? super T>> implements Comparator<T> {
        public int compare(T t1, T t2) {
            return t2.compareTo(t1);
        }
    }



    /** Helper Method that Ensures Collections are Equal.
     *  @param c1 -> First Collection
     *  @param c2 -> Second Collection
     *  @return True if Collections are equal.
     */
    public static <T> boolean sameCollection(Collection<T> c1, Collection<T> c2) {
        if (c1.size() != c2.size()) {
            return false;
        }
        for (T val : c1) { 
            if (!c2.remove(val)) {
                return false;
            }
        }
        if (c2.size() != 0) {
            return false;
        }
        return true;
    }

    @BeforeClass
    public static void init() {


        // Fill up "intVals" ArrayList with integers from "intArray".
        intCol = new ArrayList<Integer>();
        intVals = new ArrayList<Integer>();
        for (Integer val : intArray) {
            intVals.add(val);
            intCol.add(val);
        }
        // Fill up "intVals2" ArrayList with integers from "intArray2".
        intCol2 = new ArrayList<Integer>();
        intVals2 = new ArrayList<Integer>();
        for (Integer val : intArray2) {
            intVals2.add(val);
            intCol2.add(val);
        }
        // Fill up "strArray1" ArrayList with strings from "strArray1".
        strCol1 = new ArrayList<String>();
        strVals1 = new ArrayList<String>();
        for (String val1 : strArray1) {
            strVals1.add(val1);
            strCol1.add(val1);
        }
        // Fill up "strArray2" ArrayList with strings from "strArray2".
        strCol2 = new ArrayList<String>();
        strVals2 = new ArrayList<String>();
        for (String val2 : strArray2) {
            strVals2.add(val2);
            strCol2.add(val2);
        }
    }

    @Before // Simply sets up PQHeaps to be tested against.       
    public void setup() {
        intMPQ = new PQHeap<Integer>(new MaxComparator<Integer>());   
        strMPQ = new PQHeap<String>(new MaxComparator<String>());   
    }

    @Test // Double checks that the ArrayLists were properly initialized.
    public void testInit() {
        assertEquals(intVals.toString(), "[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10]");
        assertEquals(strVals1.toString(), "[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10]");
        assertEquals(strVals2.toString(), "[A, B, C, D, E, F, G, H, I, J, K]");
    }

    @Test //Tests functionality of the isEmpty() MPQ Method.
    public void testIsEmpty() {
        // Check if the MPQs are empty.
        assertEquals(0, intMPQ.size());
        assertTrue(intMPQ.isEmpty());
        assertEquals(0, strMPQ.size());
        assertTrue(strMPQ.isEmpty());
    }

    @Test // Tests whether the init() method is functional for empty queue.
    public void testInitOnEmptyQueue() {
        //Check if the MPQs are empty.
        assertTrue(intMPQ.isEmpty());
        assertTrue(strMPQ.isEmpty());

        int is = intCol.size();
        int ss = strCol1.size();

        //Initialize Empty MPQs given a Collection.
        intMPQ.init(intCol);
        strMPQ.init(strCol1);

        // Assert that init doesn't change the given collection's size
        assertEquals("The init() changes the collection it copies from", is, intCol.size());
        assertEquals("The init() changes the collection it copies from", ss, strCol1.size());
        //Ensure that the MPSs are not empty.
        assertFalse("The init() isn't filling the given MPQ.",intMPQ.isEmpty());
        assertFalse("The init() isn't filling the given MPQ.",strMPQ.isEmpty());
        //Check size of recently filled MPQs. 
        assertEquals("Size is Broken", 11, intMPQ.size());
        assertEquals("Size is Broken", 11, strMPQ.size());
    }

    @Test // Tests whether the init() method is functional for non-empty queue.
    public void testInitOnNonEmptyQueue() {
        //Check if the MPQs are empty.
        assertTrue(intMPQ.isEmpty());
        assertTrue(strMPQ.isEmpty());
        //Initialize Empty MPQs given a Collection.
        intMPQ.init(intCol);
        strMPQ.init(strCol1);
        //Ensure that the MPSs are not empty.
        assertFalse(intMPQ.isEmpty());
        assertFalse(strMPQ.isEmpty());
        //Check size of recently filled MPQs. 
        assertEquals("Size is Broken", 11, intMPQ.size());
        assertEquals("Size is Broken", 11, strMPQ.size());
        //Attempt Reinitialization on, now, Non-Empty MPQs.
        intMPQ.init(intCol2);
        strMPQ.init(strCol2);
        //Ensure that the MPSs are not empty.
        assertFalse(intMPQ.isEmpty());
        assertFalse(strMPQ.isEmpty());
        //Check new sizes of recently filled MPQs. 
        assertEquals("Size is Broken", 5, intMPQ.size());
        assertEquals("Size is Broken", 11, strMPQ.size());
    }

    @Test //Tests functionality of the clear() MPQ Method.
    public void testClear() {
        //Check if the MPQs are empty.
        assertTrue(intMPQ.isEmpty());
        assertTrue(strMPQ.isEmpty());
        // Clear... Just to be thorough.
        intMPQ.clear();
        strMPQ.clear();
        //Check if the MPQs are empty.
        assertTrue(intMPQ.isEmpty());
        assertTrue(strMPQ.isEmpty());
        //Initialize Empty MPQs given a Collection.
        intMPQ.init(intCol);
        strMPQ.init(strCol1);
        //Ensure that the MPSs are not empty.
        assertFalse(intMPQ.isEmpty());
        assertFalse(strMPQ.isEmpty());
        // Clear the MPQs.
        intMPQ.clear();
        strMPQ.clear();
        //Check that the new sizes of the cleared MPQs is 0. 
        assertEquals("Size is Broken", 0, intMPQ.size());
        assertEquals("Size is Broken", 0, strMPQ.size());
        //Check if the MPQs are empty.
        assertTrue(intMPQ.isEmpty());
        assertTrue(strMPQ.isEmpty());
    }

    @Test //Tests functionality of the size() MPQ Method on an Empty MPQ.
    public void testSizeOnEmptyMPQ() {
        // Check if the MPQs are empty.
        assertTrue(intMPQ.isEmpty());
        // Make sure size is 0.
        assertEquals("Size is Broken", 0, intMPQ.size());
    }

    @Test //Tests functionality of the size() MPQ Method on Non-Empty MPQ.
    public void testSizeOnNonEmptyMPQ() {
        // Clear intMPQ.
        intMPQ.clear();
        // Fill intMPQ.
        intMPQ.init(intCol);
        //Make sure intMPQ isn't empty.
        assertFalse(intMPQ.isEmpty());
        // Check expected size.
        assertEquals("Size is Broken", 11, intMPQ.size());
    }

    @Test //Tests the size() MPQ Method on a "dynamically" changing MPQ.
    public void testSizeOnDynamicMPQ() {
        // Clear tested MPQ then check Empty Size.
        assertEquals("Size is Broken", 0, intMPQ.size());
        // Check Filled to Size 11.
        intMPQ.init(intCol);
        assertEquals("Size is Broken", 11, intMPQ.size());
        // Check Reinitialized to Size 5.
        intMPQ.init(intCol2);
        assertEquals("Size is Broken", 5, intMPQ.size());
        // Clear tested MPQ and Check Empty Again.
        intMPQ.clear();
        assertEquals("Size is Broken", 0, intMPQ.size());
    }

    @Test //Tests size() MPQ Method when duplicates are in the MPQ.
    public void testSizeOnDuplicates() {
        // Create a new Collection of ONLY repeat keys.
        Collection<Integer> duplicates = new ArrayList<Integer>();
        for (int i = 0; i < 5; i++) {
            duplicates.add(5);
        }

        assertEquals("Size is Broken", 5, duplicates.size());   

        //Initialize intMPQ with duplicate integers.
        intMPQ.init(duplicates);
        //Check the size.
        assertEquals("Size is Broken", 5, intMPQ.size());   
    }

    @Test //Tests insert() MPQ Method for whether a value is inserted at all.
    public void testInsertValues() {
        // Check Size while intMPQ is inserted into.
        for (int i = 0; i < intArray.length; i++) {
            assertEquals(i, intMPQ.size());
            intMPQ.insert(intArray[i]);
        }
        // Check Size while strMPQ is inserted into.
        for (int j = 0; j < strArray1.length; j++) {
            assertEquals(j, strMPQ.size());
            strMPQ.insert(strArray1[j]);
        }
        // Assert that the MPQs arn't empty.
        assertFalse(intMPQ.isEmpty());
        assertFalse(strMPQ.isEmpty());
        // Assert that the size is as expected.
        assertEquals("Size is Broken", 11, intMPQ.size());
        assertEquals("Size is Broken", 11, strMPQ.size());
    }

    @Test //Tests insert() for whether the expected max is maintained.
    public void testInsertMax() {
        // Test Drive Array
        Integer[] setArray = {3, 2, 1, 5, 6, 4, 7, 8, 0, 9};
        // Set up Array containing Expected MAX
        // Contains values of expected new max after insertion of values occurs.
        Integer[] maxArray = {3, 3, 3, 5, 6, 6, 7, 8, 8, 9};
        // Test whether MAX is maintained.
        for (int i = 0; i < setArray.length; i++) {
            intMPQ.insert(setArray[i]);
            assertEquals("Max value NOT bubbling up.", maxArray[i], intMPQ.peek());
        }
        // Deconstruct Queue.
        for (int j = setArray.length - 1; j >= 0 ; j--) {
            assertEquals("Remove may be broken.", j, (long)intMPQ.remove());
            assertEquals(j, intMPQ.size());
        }
        //Check if actually empty.
        assertTrue(intMPQ.isEmpty());
    }

    @Test //Tests insert() MPQ Method when duplicates are in the MPQ.
    public void testInsertDuplicates() {
        // Add duplicate values.
        for (int i = 0; i < 5; i++) {
            intMPQ.insert(5);
        }
        // Assert that the number of values added is actually in the MPQ.
        assertEquals("Insert may not be allowing duplicate values", 5, intMPQ.size());
        // Add duplicate values.
        for (int i = 0; i < 5; i++) {
            assertEquals("Duplicates not properly inserted.", 5, (long)intMPQ.remove());
        }
        //Check if actually empty.
        assertTrue(intMPQ.isEmpty());
    }

    @Test //Tests peek() MPQ Method to check if expected MAX is returned.
    public void testGetMaxValue() {
        // Create Collections to test against peek().
        Collection<Integer> getMaxCol1 = new ArrayList<Integer>();
        Collection<Integer> getMaxCol2 = new ArrayList<Integer>();
        Collection<Integer> getMaxCol3 = new ArrayList<Integer>();
        Collection<Integer> getMaxCol4 = new ArrayList<Integer>();
        Collection<Integer> getMaxCol5 = new ArrayList<Integer>();
        //Create Various Arrays to fill up the Collections.
        Integer[] gMaxAry1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Integer[] gMaxAry2 = {3, 2, 1, 5, 6, 4, 7, 8};
        Integer[] gMaxAry3 = {82, 21, 55, 46, 78, 19};
        Integer[] gMaxAry4 = {5, 3, 1};
        Integer[] gMaxAry5 = {2};
        // Array Containing Correct Expected Max Values.
        Integer[] expectedMax = {10, 8, 82, 5, 2};
        //Fill Collections with Array Values
        for (int a = 0; a < gMaxAry1.length; a++) {
            getMaxCol1.add(gMaxAry1[a]);
        }
        for (int b = 0; b < gMaxAry2.length; b++) {
            getMaxCol2.add(gMaxAry2[b]);
        }
        for (int c = 0; c < gMaxAry3.length; c++) {
            getMaxCol3.add(gMaxAry3[c]);
        }
        for (int d = 0; d < gMaxAry4.length; d++) {
            getMaxCol4.add(gMaxAry4[d]);
        }
        for (int e = 0; e < gMaxAry5.length; e++) {
            getMaxCol5.add(gMaxAry5[e]);
        }
        // Test the above 5 Collections searching for max.
        //Trial Number 1
        assertTrue(intMPQ.isEmpty());
        intMPQ.init(getMaxCol1);
        assertEquals("GetMax() not properly handling single value", expectedMax[0], intMPQ.peek());
        //Trial Number 2
        intMPQ.clear();
        assertTrue(intMPQ.isEmpty());
        intMPQ.init(getMaxCol2);
        assertEquals("GetMax() not properly handling single value", expectedMax[1], intMPQ.peek());
        //Trial Number 3
        intMPQ.clear();
        assertTrue(intMPQ.isEmpty());
        intMPQ.init(getMaxCol3);
        assertEquals("GetMax() not properly handling single value", expectedMax[2], intMPQ.peek());
        //Trial Number 4
        intMPQ.clear();
        assertTrue(intMPQ.isEmpty());
        intMPQ.init(getMaxCol4);
        assertEquals("GetMax() not properly handling single value", expectedMax[3], intMPQ.peek());
        //Trial Number 5
        intMPQ.clear();
        assertTrue(intMPQ.isEmpty());
        intMPQ.init(getMaxCol5);
        assertEquals("GetMax() not properly handling single value", expectedMax[4], intMPQ.peek());
    }

    @Test //Tests peek() MPQ Method to make sure getMax isn't removing.
    public void testGetMaxValUpdateMax() {
        // Clear intMPQ.
        intMPQ.clear();
        // Init intMPQ with Collection.
        intMPQ.init(intCol2);
        //Make sure intMPQ isn't empty.
        assertFalse(intMPQ.isEmpty());
        // Perform getMax and ensure that size remains the same.
        int origSize = intMPQ.size();
        intMPQ.peek();
        assertEquals("peek() changed the size of the MPQ", origSize, intMPQ.size());
    }

    @Test //Tests peek() MPQ Method to make sure getMax works w/ duplicates.
    public void testGetMaxDuplicates() {
        // Create Collections to test against peek().
        Collection<Integer> getMaxCol1 = new ArrayList<Integer>();
        Collection<Integer> getMaxCol2 = new ArrayList<Integer>();
        Collection<Integer> getMaxCol3 = new ArrayList<Integer>();
        //Create Various Arrays to fill up the Collections.
        Integer[] gMaxAry1 = {0, 0, 0, 0, 1};
        Integer[] gMaxAry2 = {1, 1, 1, 1, 0};
        Integer[] gMaxAry3 = {0, 0, 0, 0, 0};
        // Array Containing Correct Expected Max Values.
        Integer[] expectedMax = {1, 1, 0};
        //Fill Collections with Array Values
        for (int a = 0; a < gMaxAry1.length; a++) {
            getMaxCol1.add(gMaxAry1[a]);
            getMaxCol2.add(gMaxAry2[a]);
            getMaxCol3.add(gMaxAry3[a]);
        }
        //Trial Number 1
        assertTrue(intMPQ.isEmpty());
        intMPQ.init(getMaxCol1);
        assertEquals("GetMax() NOT handling duplicates well.", expectedMax[0], intMPQ.peek());
        //Trial Number 2
        intMPQ.clear();
        assertTrue(intMPQ.isEmpty());
        intMPQ.init(getMaxCol2);
        assertEquals("GetMax() NOT handling duplicates well.", expectedMax[1], intMPQ.peek());
        //Trial Number 3
        intMPQ.clear();
        assertTrue(intMPQ.isEmpty());
        intMPQ.init(getMaxCol3);
        assertEquals("GetMax() NOT handling duplicates well.", expectedMax[2], intMPQ.peek());
        // Test on Strings:
                // Create Collections to test against peek().
        Collection<String> getStrMaxCol1 = new ArrayList<String>();
        Collection<String> getStrMaxCol2 = new ArrayList<String>();
        Collection<String> getStrMaxCol3 = new ArrayList<String>();
        //Create Various Arrays to fill up the Collections.
        String[] gStrMaxAry1 = {"0", "0", "0", "0", "1"};
        String[] gStrMaxAry2 = {"1", "1", "1", "1", "0"};
        String[] gStrMaxAry3 = {"0", "0", "0", "0", "0"};
        // Array Containing Correct Expected Max Values.
        String[] expectedStrMax = {"1", "1", "0"};
        //Fill Collections with Array Values
        for (int b = 0; b < gStrMaxAry1.length; b++) {
            getStrMaxCol1.add(gStrMaxAry1[b]);
            getStrMaxCol2.add(gStrMaxAry2[b]);
            getStrMaxCol3.add(gStrMaxAry3[b]);
        }
        //Trial Number 1
        assertTrue(strMPQ.isEmpty());
        strMPQ.init(getStrMaxCol1);
        assertEquals("GetMax() NOT handling duplicates well.", expectedStrMax[0], strMPQ.peek());
        //Trial Number 2
        strMPQ.clear();
        assertTrue(strMPQ.isEmpty());
        strMPQ.init(getStrMaxCol2);
        assertEquals("GetMax() NOT handling duplicates well.", expectedStrMax[1], strMPQ.peek());
        //Trial Number 3
        strMPQ.clear();
        assertTrue(strMPQ.isEmpty());
        strMPQ.init(getStrMaxCol3);
        assertEquals("GetMax() NOT handling duplicates well.", expectedStrMax[2], strMPQ.peek());
    }
    
    // Tests whether peek() throws an exception when called on an empty MPQ.
    @Test (expected=QueueEmptyException.class)
    public void testClearGetMax() {
        // Check if the MPQs are empty.
        assertTrue(intMPQ.isEmpty());
        // Call getMax on Empty MPQ.
        intMPQ.peek();
    }

    @Test // Tests whether remove() at least removes some value when called.
    public void testRemoveMaxValues() {
        // Check if the MPQs are empty.
        assertTrue(intMPQ.isEmpty());
        // Create Collection to test against remove().
        Collection<Integer> getMaxCol = new ArrayList<Integer>();
        //Create Various Arrays to fill up the Collections.
        Integer[] gMaxAry = {1, 2, 3};
        //Fill Collections with Array Values
        for (int i = 0; i < gMaxAry.length; i++) {
            getMaxCol.add(gMaxAry[i]);
        }
        intMPQ.init(getMaxCol);
        for (int j = gMaxAry.length - 1; j >= 0; j--) {
            assertEquals(gMaxAry[j], intMPQ.remove());
            assertEquals("Remove values aren't being removed", j, intMPQ.size());
        }
        //Check if the MPQ is empty and has size 0.
        assertTrue("Last value not being removed", intMPQ.isEmpty());
        assertEquals(0, intMPQ.size());
    }

    @Test // Tests remove() removes the expected value with each operation.
    public void testRemoveMaxCorrectValues() {
        // Check if the MPQs are empty.
        assertTrue(intMPQ.isEmpty());
        // Create Collection to test against remove().
        Collection<Integer> getMaxCol = new ArrayList<Integer>();
        //Create Various Arrays to fill up the Collections.
        Integer[] gMaxAry = {3, 2, 1, 5, 6, 4, 7, 8};
        //Fill Collections with Array Values
        for (int i = 0; i < gMaxAry.length; i++) {
            getMaxCol.add(gMaxAry[i]);
        }
        intMPQ.init(getMaxCol);
        for (int j = gMaxAry.length - 1; j >= 0; j--) {
            assertEquals("Remove Max is removing the wrong value.", j + 1, (long)intMPQ.remove());
            assertEquals(j, intMPQ.size());
        }
        //Check if the MPQ is empty and has size 0.
        assertTrue(intMPQ.isEmpty());
        assertEquals(0, intMPQ.size());
    }

    @Test // Tests whether remove() properly updates max after remove operation.
    public void testRemoveMaxUpdateMax() {
        // Check if the MPQs are empty.
        assertTrue(intMPQ.isEmpty());
        // Create Collection to test against remove().
        Collection<Integer> getMaxCol = new ArrayList<Integer>();
        //Create Array to fill up the Collections.
        Integer[] gMaxAry =     {3, 2, 3, 5, 6, 4, 8, 8};
        //Create Array with expected maxes.
        Integer[] expectedMax = {2, 3, 3, 4, 5, 6, 8, 8};
        //Fill Collections with Array Values
        for (int i = 0; i < gMaxAry.length; i++) {
            getMaxCol.add(gMaxAry[i]);
        }
        intMPQ.init(getMaxCol);
        for (int j = gMaxAry.length - 1; j >= 0; j--) {
            assertEquals("Remove Max is not properly updating Max.", expectedMax[j], intMPQ.peek());
            assertEquals(expectedMax[j], intMPQ.remove());
            assertEquals(j, intMPQ.size());
        }
        //Check if the MPQ is empty and has size 0.
        assertTrue(intMPQ.isEmpty());
        assertEquals(0, intMPQ.size());
    }

    @Test // Tests whether remove() properly updates when duplicates are present.
    public void testRemoveMaxDuplicates() {
        // Check if the MPQs are empty.
        assertTrue(intMPQ.isEmpty());
        // Create Collection to test against remove().
        Collection<String> getMaxCol = new ArrayList<String>();
        //Create Array to fill up the Collections.
        String[] gMaxAry = {"3", "3", "2", "2", "1", "1", "0", "0"};
        //Create Array of Expected Max Values.
        String[] expectedMaxAry = {"0", "0", "1", "1", "2", "2", "3", "3"};
        //Fill Collections with Array Values
        for (int i = 0; i < gMaxAry.length; i++) {
            getMaxCol.add(gMaxAry[i]);
        }
        strMPQ.init(getMaxCol);
        for (int j = gMaxAry.length - 1; j >= 0; j--) {
            assertEquals(expectedMaxAry[j], strMPQ.remove());
            assertEquals(j, strMPQ.size());
        }
        //Check if the MPQ is empty and has size 0.
        assertTrue(strMPQ.isEmpty());
        assertEquals(0, strMPQ.size());
    }

    @Test // Tests whether remove() properly updates duplicate max after remove operation.
    public void testRemoveMaxUpdateDuplicateMax() {
        // Check if the MPQs are empty.
        assertTrue(intMPQ.isEmpty());
        // Create Collection to test against remove().
        Collection<Integer> getMaxCol = new ArrayList<Integer>();
        //Create Array to fill up the Collections.
        Integer[] gMaxAry = {1, 1, 1, 0, 0, 0};
        //Create Array with expected maxes.
        Integer[] expectedMax = {0, 0, 0, 1, 1, 1};
        //Fill Collections with Array Values
        for (int i = 0; i < gMaxAry.length; i++) {
            getMaxCol.add(gMaxAry[i]);
        }
        intMPQ.init(getMaxCol);
        for (int j = gMaxAry.length - 1; j >= 0; j--) {
            System.out.println(j + " " + intMPQ + " " + intMPQ.size());
            
            assertEquals("RemoveMax not properly removing duplicate values.", expectedMax[j], intMPQ.peek());
            assertEquals((long)expectedMax[j], (long)intMPQ.remove());
            assertEquals(j, intMPQ.size());
        }
        //Check if the MPQ is empty and has size 0.
        assertTrue(intMPQ.isEmpty());
        assertEquals(0, intMPQ.size());
    }

    // Tests whether remove() throws an exception when called on an empty MPQ.
    @Test (expected=QueueEmptyException.class)
    public void testClearRemoveMax() {
        // Check if the MPQs are empty.
        assertTrue(intMPQ.isEmpty());
        // Call removeMax on Empty MPQ.
        intMPQ.remove();
    }
}


