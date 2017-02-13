import java.util.List;
import java.util.Map;
import java.util.LinkedList;
import java.util.ArrayList;

public class Problems {

    public static Map<Integer, Integer> getCountMap(int[] arr) {
        Map<Integer, Integer> counts = new MyLinearMap<>();
        for(int i: arr){
            if(counts.containsKey(i)){
                counts.put(i, counts.get(i) + 1);
            }
            else{
                counts.put(i, 1);
            }
        }
        return counts;
    }

    public static List<Integer> removeKDigits(int[] num, int k) {
        LinkedList<Integer> arr = new LinkedList<>();

        for (int i : num) {

            while (!arr.isEmpty() && i < arr.getLast() && k>0) {
                arr.removeLast();
                k--;
            }
            if (arr.size() < num.length - k)
                arr.addLast(i);

        }

        return arr;
    }

    public static int sumLists(Node<Integer> l1, Node<Integer> l2) {
        l1 = reverseList(l1);
        l2 = reverseList(l2);
        int carry = 0;
        int listSum = 0;
        int place = 1;

        while (l1 != null || l2 != null || carry != 0) {
            //calculate sum so far
            int sum = ((l2 == null) ? 0 : l2.data) + ((l1 == null) ? 0 : l1.data) + carry;
            listSum = listSum + (sum % 10)*place;

            //setup for next place
            carry = sum / 10;
            l1 = (l1 == null) ? l1 : l1.next;
            l2 = (l2 == null) ? l2 : l2.next;
            place *= 10;
        }

        return listSum;
    }

    public static Node<Integer> reverseList(Node<Integer> head){
        Node<Integer> previous = null;
        Node<Integer> current;
        while (head != null) {
            current = head;
            head = head.next;
            current.next = previous;
            previous = current;
        }
        return previous;
    }
}
