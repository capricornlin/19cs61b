import edu.princeton.cs.algs4.Queue;

public class QuickSort {
    /**
     * Returns a new queue that contains the given queues catenated together.
     *
     * The items in q2 will be catenated after all of the items in q1.
     *
     * @param q1  A Queue of items
     * @param q2  A Queue of items
     * @return    A Queue containing the items of
     *            q1 followed by the items of q2.
     */
    private static <Item extends Comparable> Queue<Item> catenate(Queue<Item> q1, Queue<Item> q2) {
        Queue<Item> catenated = new Queue<Item>();
        for (Item item : q1) {
            catenated.enqueue(item);
        }
        for (Item item: q2) {
            catenated.enqueue(item);
        }
        return catenated;
    }

    /**
     * Returns a random item from the given queue.
     *
     * @param items  A Queue of items
     * @return       A random item from items
     */
    private static <Item extends Comparable> Item getRandomItem(Queue<Item> items) {
        int pivotIndex = (int) (Math.random() * items.size());
        Item pivot = null;
        // Walk through the queue to find the item at the given index.
        for (Item item : items) {
            if (pivotIndex == 0) {
                pivot = item;
                break;
            }
            pivotIndex--;
        }
        return pivot;
    }

    /**
     * Partitions the given unsorted queue by pivoting on the given item.
     *
     * @param unsorted  A Queue of unsorted items
     * @param pivot     The item to pivot on
     * @param less      An empty Queue. When the function completes, this queue will contain
     *                  all of the items in unsorted that are less than the given pivot.
     * @param equal     An empty Queue. When the function completes, this queue will contain
     *                  all of the items in unsorted that are equal to the given pivot.
     * @param greater   An empty Queue. When the function completes, this queue will contain
     *                  all of the items in unsorted that are greater than the given pivot.
     */
    private static <Item extends Comparable> void partition(
            Queue<Item> unsorted, Item pivot,
            Queue<Item> less, Queue<Item> equal, Queue<Item> greater) {
        // Your code here!
        int z = unsorted.size();
        for(int i = 0;i < z ;i+=1){
            Item a = unsorted.dequeue();
            if(a.compareTo(pivot) > 0){
                greater.enqueue(a);
            }else if(a.compareTo(pivot) < 0){
                less.enqueue(a);
            }else{
                equal.enqueue(a);
            }
        }

    }

    /**
     * Returns a Queue that contains the given items sorted from least to greatest.
     *
     * @param items  A Queue of possibly unsorted items
     * @return       A Queue of sorted items
     */
    public static <Item extends Comparable> Queue<Item> quickSort(
            Queue<Item> items) {
        // Your code here!

        Queue<Item> s = new Queue<>();
        Queue<Item> g = new Queue<>();
        Queue<Item> e = new Queue<>();
        Item r = getRandomItem(items);
        partition(items,r,s,e,g);
        Queue<Item> x = catenate(catenate(s,e),g);
        while(s.size()>1 || g.size()>1){
            Queue<Item> a = quickSort(s);
            Queue<Item> b = quickSort(g);
            x = catenate(catenate(a,e),b);
            return catenate(catenate(a,e),b);
        }
        return x;
    }

    public static void main(String[] args){
        Queue<String> a = new Queue<>();
        a.enqueue("bear");
        a.enqueue("aloha");
        a.enqueue("clare");
        a.enqueue("gosh");
        a.enqueue("elon");

        QuickSort x = new QuickSort();
        x.quickSort(a);

    }

}

