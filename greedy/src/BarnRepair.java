import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BarnRepair {
    static class Gap {
        int start, end, length;

        Gap(int s, int e, int l) {
            this.start = s;
            this.end = e;
            this.length = l;
        }
    }
    public static int solve(int M, int S, int C, int[] occupied) {
        Arrays.sort(occupied);
        //cover every occupied stall, while number of separate blanks >M, join 2 blanks
        // PQ to keep smallest gap. Always cover smallest available gap
        PriorityQueue<Gap> pq = new PriorityQueue<>(new Comparator<Gap>() {
            @Override
            public int compare(Gap o1, Gap o2) {
                return o1.length - o2.length;
            }
        });

        int numBoards = 1; //cover the first cow

        for(int i=1; i<occupied.length;i++){
            if(occupied[i] > occupied[i-1] + 1){
                pq.offer(new Gap(occupied[i-1], occupied[i], occupied[i]-occupied[i-1]-1));
                numBoards++;
            }
        }

        Gap g;
        int blocked = occupied.length;// how many stalls are blocked
        while(numBoards >M){
            g = pq.remove(); //greedy choice is to remove the smallest gap
            blocked += g.length; //add length of gap to block
            numBoards--;
        }
        return blocked;
    }
}
