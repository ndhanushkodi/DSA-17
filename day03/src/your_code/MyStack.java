package your_code;

import ADTs.StackADT;
import java.util.LinkedList;

/**
 * An implementation of the Stack interface.
 */
public class MyStack implements StackADT<Integer> {

    private LinkedList<Integer> ll;
    private LinkedList<Integer> max;

    public MyStack(){
        ll = new LinkedList<>();
        max = new LinkedList<>();
    }

    @Override
    public void push(Integer e) {
        ll.addFirst(e);
        if(max.isEmpty() || (max.getFirst().compareTo(e) <=0)){ //we have something larger than max
            max.addFirst(e);
        }
    }

    @Override
    public Integer pop() {
        Integer popped = ll.removeFirst();
        if(!max.isEmpty() && (popped.equals(max.getFirst()))){
            max.removeFirst();
        }
        return popped;
    }

    @Override
    public boolean isEmpty() {
        return ll.isEmpty();
    }

    @Override
    public Integer peek() {
        return ll.getFirst();
    }

    public Integer maxElement() {
        return max.getFirst();
    }
}
