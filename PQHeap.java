import java.util.Comparator;
import java.util.Collection;
import java.util.ArrayList;

public class PQHeap<T extends Comparable<? super T>> implements PriorityQueue<T> {
    

    private static class DefaultComparator<T extends Comparable<? super T>> implements Comparator<T> {
        public int compare(T t1, T t2) {
            return t1.compareTo(t2);
        }
    }

    private ArrayList<T> heapArray;

    // private int capacity;

    private int size;

    private Comparator<? super T> comparator;


    public PQHeap(Comparator<? super T> cmprtr) {
        this.comparator = cmprtr;
        this.heapArray = new ArrayList<T>();
        this.clear();
    }

    public PQHeap() {
        this(new DefaultComparator<T>());
    }

    /** Insert a value. Duplicate values <b>do</b> end up in the
     *  queue, so inserting X three times means it has to be removed
     *  three times before it's gone again.
     *  @param t Value to add.
     */
    @Override
    public void insert(T t) {
        // this.resizeIfNeeded();
        this.size++;
        this.heapArray.add(t);
        for (int i = this.size() / 2; i > 0; i /= 2) {
            this.siftDown(i); //sift up tree starting at inserted value
        }
        
    }
    
    /** Remove "best" value. This value is the "best" value in the
     *  queue as determined by the comparator for the queue.
     *  @return the value that was removed
     *  @throws QueueEmptyException If queue is empty.
     */
    @Override
    public T remove() throws QueueEmptyException {
        if (this.isEmpty()) {
            throw new QueueEmptyException();
        } else {
            T bestValue = this.heapArray.get(1);
            this.heapArray.set(1, this.heapArray.remove(this.size--));
            int toSift = 1;
            while(toSift != -1) { // sift down the displaced value
                toSift = this.siftDown(toSift);
            }
            return bestValue;
        }

    }
    
    /** Get the "best" value. This value is the "best" value in the
     *  queue as determined by the comparator for the queue.  [Note,
     *  "best" is at the root in a heap-based implementation.]
     *  @return best value in the queue.
     *  @throws QueueEmptyException If queue is empty.
     */
    @Override
    public T peek() throws QueueEmptyException {
        if (this.isEmpty()) {
            throw new QueueEmptyException();
        } else {
            return this.heapArray.get(1);
        }
    }
    
    /** No elements?
     *  @return True if queue is empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    /** Get the number of elements in the queue.
     *  @return the numbers
     */
    @Override
    public int size() {
        return this.size;
    }

    /** Dump the contents of the priority queue.
     */
    @Override
    public void clear() {
        this.size = 0;
        this.heapArray.clear();
        this.heapArray.add(null);
    }

    /** Initialize a priority queue from a container of values.
     *  @param values the collection of starting values
     */
    @Override
    public void init(Collection<T> values) {

        this.clear();

        this.size = values.size();


        this.heapArray.addAll(1, values);

        // heapify
        for (int j = this.size / 2; j > 0; j--) {
            this.siftDown(j);
        }


    }

    /**
     * Switches the index with the "best" of its 2 children
     * @param  idx heap index to compare to its children
     * @return     returns the index of the child switched
     *             with or -1 if not switched
     */
    private int siftDown(int idx) {
        int n = this.size();
        //if too small to sift or if looking at childless elements of heap
        if (n <= 1 || idx > n / 2) {
            return -1;
        }
        int leftChildIdx = 2 * idx;
        int rightChildIdx = leftChildIdx + 1;

        int childMinIdx = leftChildIdx;
        T childMin = this.heapArray.get(childMinIdx);
        if (rightChildIdx < n && this.comparator.compare(
            this.heapArray.get(rightChildIdx), childMin) < 0) {

            childMinIdx = rightChildIdx;
            childMin = this.heapArray.get(rightChildIdx);
        }

        if (this.comparator.compare(childMin, this.heapArray.get(idx)) < 0) {
            // swap better child with parent
            T temp = childMin;
            this.heapArray.add(childMinIdx, this.heapArray.get(idx));
            this.heapArray.add(idx, temp);
            return childMinIdx; //return id of switched child
        } else {
            return -1;
        }


    }

    public String toString() {
        return this.heapArray.toString();
    }

    // private void resizeIfNeeded() {
    //     if (this.size + 1 >= this.capacity) {
    //         this.capacity *= 2;
    //         T[] temp = this.heapArray;
    //         this.heapArray = new T[this.capacity];
    //         for (int i = 1; i <= this.size(); i++) {
    //             this.heapArray.add(i, temp[i]);
    //         }
    //     }
    // }


}