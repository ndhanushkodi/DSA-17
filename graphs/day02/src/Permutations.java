import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class Permutations {
    private static void backtrack(LinkedList<Integer> curr, Set<Integer> unused, List<List<Integer>> subsets) {
        if (unused.isEmpty())
            subsets.add(new LinkedList<>(curr));
        for (int u : new LinkedList<>(unused)) {
            curr.addLast(u);
            unused.remove(u);
            backtrack(curr, unused, subsets);
            unused.add(u);
            curr.removeLast();
        }
    }

    public static List<List<Integer>> permutations(List<Integer> A) {
        List<List<Integer>> permutations = new LinkedList<>();
        Set<Integer> unused = new HashSet<>(A);
        backtrack(new LinkedList<>(), unused, permutations);
        return permutations;
    }

//    public static List<List<Integer>> permutations(List<Integer> A) {
//        List<List<Integer>> perms = new LinkedList<List<Integer>>();
//        recPermute(new LinkedList<>(), A, perms);
//        return perms;
//    }
//
//    private static void recPermute(List<Integer> soFar, Set<Integer> rest, List<List<Integer>> perm){
//        if(rest.isEmpty()){
//            perm.add(soFar);
//        }
//        else{
//            for(int i=0;i<rest.size(); i++){
//                List remaining = new LinkedList<Integer>();
//                remaining.addAll(rest.subList(0,i));
//                remaining.addAll(rest.subList(i+1,rest.size()));
//                soFar.add(rest.get(i));
//                recPermute(soFar, remaining, perm);
//            }
//        }
//
//    }

}
