import java.util.LinkedList;

public class Problems {

    static void sortNumsBetween100s(int[] A) {
        for (int i = 0; i < A.length; i++)
            A[i] += 100;
        CountingSort.countingSort(A);
        for (int i = 0; i < A.length; i++)
            A[i] -= 100;
    }

    /**
     * @param n the character number, 0 is the rightmost character
     * @return
     */
    private static int getNthCharacter(String s, int n) {
        return s.charAt(s.length() - 1 - n) - 'a';
    }


    /**
     * Use counting sort to sort the String array according to a character
     *
     * @param n The digit number (where 0 is the least significant digit)
     */
    static void countingSortByCharacter(String[] A, int n) {
        //make 26 linked lists
        int k = 26;
        LinkedList<String>[] counts = new LinkedList[k];

        for (int i = 0; i<k; i++){
            counts[i] = new LinkedList<>();
        }

        for (String s: A){
            int nth = getNthCharacter(s,n);
            counts[nth].add(s);
        }
        int m = 0;
        for(LinkedList<String> list: counts){
            while(!list.isEmpty()){
                A[m++] = list.removeFirst();
            }
        }


    }

    /**
     * @param stringLength The length of each of the strings in S
     */
    static void sortStrings(String[] S, int stringLength) {
        // TODO
    }

}
