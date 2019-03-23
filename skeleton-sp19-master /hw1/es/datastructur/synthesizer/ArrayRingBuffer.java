package es.datastructur.synthesizer;
import java.util.Iterator;

//TODO: Make sure to that this class and all of its methods are public
//TODO: Make sure to add the override tag for all overridden methods
//TODO: Make sure to make this class implement BoundedQueue<T>

public class ArrayRingBuffer<T> implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    // return an object Iterator<T> , a interface.
    @Override
    public Iterator<T> iterator(){
        return new ArrayBufferIterator();
    }


    // a Iterator Objects(interface) must have two methods, 1.hasNext() 2.Next().
    // so we create a private class to implements Iterator interface.
    private class ArrayBufferIterator implements Iterator<T>{
        private int pos;

        public ArrayBufferIterator(){
            pos = 0;
        }
        public boolean hasNext(){
            return pos < capacity();
        }
        public T next(){

            T returnItem = rb[pos];
            pos += 1;
            return returnItem;
        }
    }

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    @Override
    public int capacity(){
        return rb.length;
    }

    @Override
    public int fillCount(){
        return fillCount;
    }


    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        first = 0;
        last = 0;
        fillCount = 0;
        rb = (T[])new Object[capacity];
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */

    @Override
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update
        //       last.
        if(fillCount == capacity()){
            throw new RuntimeException("Ring bugger overflow");
        }else {

            if(last+1 == capacity()){
                rb[last] = x;
                last = 0;
            }else{
                rb[last] = x;
                last += 1;
            }
            fillCount += 1;
            return;
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */

    @Override
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and
        //       update first.
        if(fillCount() == 0){
            throw new RuntimeException("Ring buffer underflow");
        }else {
            T d;
            if(first+1 == capacity()){
                d = rb[first];
                first = 0;
            }else{
                d = rb[first];
                first += 1;
            }

            fillCount -= 1;
            return d;
        }

    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */

    @Override
    public T peek() {
        // TODO: Return the first item. None of your instance variables should
        //       change.
        T o;
        if(fillCount() == 0){
            throw new RuntimeException("Ring buffer underflow");
        }else{
            o = rb[first];
            return o;
        }
        //return o;
    }

    public boolean contains(T x){
        for(int i =0; i <capacity(); i+=1){
            if(rb[i].equals(x)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o){
        if(o == null){
            return false;
        }else if(this == o){
            return true;
        }else if(this.getClass() != o.getClass()){
            return false;
        }
        ArrayRingBuffer<T> other = (ArrayRingBuffer<T>) o;
        if(this.capacity() != other.capacity()){
            return false;
        }
        for(T item:this){
            if(!other.contains(item)){
                return false;
            }
        }
        return true;
    }


    // TODO: When you get to part 4, implement the needed code to support
    //       iteration and equals.
}
    // TODO: Remove all comments that say TODO when you're done.
