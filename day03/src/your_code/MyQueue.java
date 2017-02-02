package your_code;

import ADTs.QueueADT;

import java.util.LinkedList;
/**
 * An implementation of the Queue interface.
 */
public class MyQueue implements QueueADT<Integer> {

    private LinkedList<Integer> ll;

    public MyQueue(){
        ll = new LinkedList<>();
    }

    @Override
    public void enqueue(Integer item) {
        ll.add(item);
    }

    @Override
    public Integer dequeue() {
        return ll.removeFirst();
    }

    @Override
    public boolean isEmpty() {
        return ll.isEmpty();
    }

    @Override
    public Integer front() {
        return ll.getFirst();
    }
}