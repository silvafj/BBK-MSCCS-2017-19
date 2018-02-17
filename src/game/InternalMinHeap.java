package game;

import java.util.*;

/**
 * An instance is a priority queue of elements of type E implemented as a min-heap.
 */
class InternalMinHeap<E> {

    private int size; // number of elements in the priority queue (and heap)

    /**
     * The heap invariant is given below. Note that / denotes int division.
     * <p>
     * b[0..size-1] is viewed as a min-heap, i.e.
     * 1. Each array element in b[0..size-1] contains a value of the heap.
     * 2. The children of each b[i] are b[2i+1] and b[2i+2].
     * 3. The parent of each b[i] (except b[0]) is b[(i-1)/2].
     * 4. The priority of the parent of each b[i] is <= the priority of b[i].
     * 5. Priorities for the b[i] used for the comparison in point 4
     * are given in map. map contains one entry for each element of
     * the heap, and map and b have the same size.
     * For each element e in the heap, the map entry contains in the
     * Info object the priority of e and its index in b.
     */
    private List<E> b = new ArrayList<>();
    private Map<E, Info> map = new HashMap<>();

    /**
     * Constructor: an empty heap.
     */
    public InternalMinHeap() {
    }

    /**
     * Return the number of elements in the priority queue.
     * This operation takes constant time.
     */
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Add e with priority p to the priority queue.
     * Throw an illegalArgumentException if e is already in the queue.
     * The expected time is O(log N) and the worst-case time is O(N).
     */
    public void add(E e, double p) throws IllegalArgumentException {
        if (map.containsKey(e)) {
            throw new IllegalArgumentException("Cannot insert the same element twice");
        }

        b.add(e);
        map.put(e, new Info(size, p));
        size++;
        bubbleUp(size - 1);
    }

    /**
     * Return the element of the priority queue with lowest priority, without
     * changing the queue. This operation takes constant time.
     * Throw a PCueException with message "priority queue is empty" if the
     * priority queue is empty.
     */
    public E peek() {
        if (b.isEmpty()) {
            throw new NoSuchElementException();
        }
        return b.get(0);
    }

    /**
     * Remove and return the element of the priority queue with lowest priority.
     * The expected time is O(log n) and the worst-case time is O(N).
     * Throw a PCueException with message "priority queue is empty" if the
     * priority queue is empty.
     */
    public E poll() {
        E val = peek();
        map.remove(val);
        size--;
        if (size <= 0) {
            b.remove(0);
        } else {
            b.set(0, b.get(size));
            b.remove(size);
            bubbleDown(0);
        }
        return val;
    }

    /**
     * Change the priority of element e to p.
     * The expected time is O(log N) and the worst-case is time O(N).
     * Throw an illegalArgumentException if e is not in the priority queue.
     */
    public void changePriority(E e, double p) {
        Info info = map.get(e);
        if (info == null) {
            throw new IllegalArgumentException("No element found: " + e);
        }

        if (p < info.priority) {
            info.priority = p;
            bubbleUp(info.index);
        } else {
            info.priority = p;
            bubbleDown(info.index);
        }
    }


    /**
     * Bubble b[k] up in heap to its right place.
     * Precondition: Every b[i] satisfies the heap property except perhaps
     * k's priority < parent's priority
     */
    private void bubbleUp(int k) {
        E val = b.get(k);
        Info info = map.get(val);

        int i = k;
        while (i > 0) {
            int parentIdx = (i - 1) / 2;
            E parentVal = b.get(parentIdx);
            Info parentInfo = map.get(parentVal);

            if (parentInfo.priority <= info.priority) {
                break;
            }

            b.set(i, parentVal);
            parentInfo.index = i;

            i = parentIdx;
        }
        b.set(i, val);
        info.index = i;
    }

    /**
     * Bubble b[k] down in heap until it finds the right place.
     * Precondition: Every b[i] satisfies the heap property except perhaps
     * k's priority > a child's priority.
     */
    private void bubbleDown(int k) {
        E val = b.get(k);
        Info info = map.get(val);

        int i = k;
        while (2 * i + 1 < size) {
            int childIdx = getSmallerChild(i);
            E childVal = b.get(childIdx);
            Info childInfo = map.get(childVal);

            if (info.priority <= childInfo.priority) {
                break;
            }

            b.set(i, childVal);
            childInfo.index = i;

            i = childIdx;
        }
        b.set(i, val);
        info.index = i;
    }

    /**
     * Return the index of the smaller child of b[q]
     * Precondition: left child exists: 2q+1 < size of heap
     */
    private int getSmallerChild(int q) {
        int leftIdx = 2 * q + 1;
        int rightIdx = 2 * q + 2;
        if (size <= rightIdx) {
            return leftIdx;
        }

        Info leftInfo = map.get(b.get(leftIdx));
        Info rightInfo = map.get(b.get(rightIdx));
        return (leftInfo.priority <= rightInfo.priority ? leftIdx : rightIdx);
    }

    /**
     * An instance contains the index, value, and priority of an element of the heap.
     */
    private static class Info {
        private int index;  // index of this element in map
        private double priority; // priority of this element

        /**
         * Constructor: an instance in b[i] with priority p.
         */
        private Info(int i, double p) {
            index = i;
            priority = p;
        }

    }
}
