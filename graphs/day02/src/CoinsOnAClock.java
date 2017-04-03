import sun.rmi.server.InactiveGroupException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoinsOnAClock {

    private static boolean noCoinsLeft(Map<Character,Integer> coinCount){
        for(int v: coinCount.values()){
            if(v!=0)
                return false;
        }
        return true;
    }

    private static void solve(char[] clock, int k, Map<Character,Integer> coinCount, Map<Character, Integer> coinVal, List<char[]> solns){
        if(noCoinsLeft(coinCount)){
            char[] copy = new char[clock.length];
            System.arraycopy(clock, 0, copy, 0, clock.length);
            solns.add(copy);
            return;
        }

        if (clock[k] != '.')
            return;


        for(Map.Entry<Character, Integer> e: coinCount.entrySet()){ //p=x, n=y, or d=z
            System.out.println(e);
            if(e.getValue()>0){ //coin count for this coin is greater than 0
                int thisCoinVal = coinVal.get(e.getKey());
                clock[k] = e.getKey(); //place this coin at spot k in clock
                int nextIndex = (k+thisCoinVal) % clock.length;

                coinCount.put(e.getKey(), e.getValue()-1); //remove this coin from coin count
                solve(clock, nextIndex, coinCount, coinVal, solns);

                //if we come out of the solve, it means we must backtrack
                //get rid of clock[k]
                clock[k] = '.';
                coinCount.put(e.getKey(), e.getValue()+1);
            }
        }
    }

    public static List<char[]> coinsOnAClock(int pennies, int nickels, int dimes, int hoursInDay) {
        Map<Character, Integer> coinsToCounts = new HashMap<>();
        coinsToCounts.put('p', pennies);
        coinsToCounts.put('n', nickels);
        coinsToCounts.put('d', dimes);
        Map<Character, Integer> coinsToValues = new HashMap<>();
        coinsToValues.put('p', 1);
        coinsToValues.put('n', 5);
        coinsToValues.put('d', 10);
        char[] clock = new char[hoursInDay];
        for(int i=0; i<clock.length;i++){
            clock[i] = '.';
        }
        List<char[]> result = new ArrayList<>();
        solve(clock, 0, coinsToCounts, coinsToValues, result);
        return result;
    }

}
