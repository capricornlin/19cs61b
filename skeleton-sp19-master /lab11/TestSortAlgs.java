import edu.princeton.cs.algs4.Queue;
import org.junit.Test;
import static org.junit.Assert.*;


public class TestSortAlgs {

    @Test
    public void testQuickSort() {

        Queue<String> a = new Queue<>();
        a.enqueue("bohn");
        a.enqueue("clojnf");
        a.enqueue("aloka");

        Queue<String> actual = QuickSort.quickSort(a);
        String a1 = actual.dequeue();
        String a2 = actual.dequeue();
        String a3 = actual.dequeue();
        assertTrue(a1 == "aloka");
        assertTrue(a2 == "bohn");
        assertTrue(a3 == "clojnf");



    }

    @Test
    public void testMergeSort() {

        Queue<String> a = new Queue<>();
        a.enqueue("linwa");
        a.enqueue("morning");
        a.enqueue("hawaii");

        Queue<String> actual = MergeSort.mergeSort(a);
        String a1 = actual.dequeue();
        String a2 = actual.dequeue();
        String a3 = actual.dequeue();
        assertTrue(a1 == "hawaii");
        assertTrue(a2 == "linwa");
        assertTrue(a3 == "morning");


    }

    /**
     * Returns whether a Queue is sorted or not.
     *
     * @param items  A Queue of items
     * @return       true/false - whether "items" is sorted
     */
    private <Item extends Comparable> boolean isSorted(Queue<Item> items) {
        if (items.size() <= 1) {
            return true;
        }
        Item curr = items.dequeue();
        Item prev = curr;
        while (!items.isEmpty()) {
            prev = curr;
            curr = items.dequeue();
            if (curr.compareTo(prev) < 0) {
                return false;
            }
        }
        return true;
    }
}
