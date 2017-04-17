import java.util.Comparator;
import java.util.PriorityQueue;

public class MixingMilk {
    static class Farmer {
        int pricePerUnit;
        int index;
        int total;

        Farmer(int ppu, int t, int i) {
            this.pricePerUnit = ppu;
            this.total = t;
            this.index = i;
        }
    }
    public static int solve(int M, int N, int[] units, int[] price) {
        int bought = 0;
        int farmersUsed = 0;
        int cost = 0;

        // PQ to keep cheapest farmer at the top
        PriorityQueue<Farmer> pq = new PriorityQueue<>(new Comparator<Farmer>() {
            @Override
            public int compare(Farmer o1, Farmer o2) {
                return o1.pricePerUnit - o2.pricePerUnit;
            }
        });

        for (int i=0; i<N; i++) {
            pq.add(new Farmer(price[i], units[i], i));
        }
        Farmer f;
        int toBuy;
        while(bought<M && farmersUsed < N){
            f=pq.remove(); //greedy choice
            toBuy = Math.min(M-bought, f.total); //buy them all or m

            cost += toBuy * f.pricePerUnit;
            bought += toBuy;
            farmersUsed++;

        }
        return cost;
    }
}
