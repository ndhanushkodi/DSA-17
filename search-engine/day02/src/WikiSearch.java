import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

import redis.clients.jedis.Jedis;

public class WikiSearch {

    // map from URLs that contain the term(s) to relevance score
    private Map<String, Integer> map;

    public WikiSearch(Map<String, Integer> map) {
        this.map = map;
    }

    public Integer getRelevance(String url) {
        Integer relevance = map.get(url);
        return relevance == null ? 0 : relevance;
    }

    // Prints the contents in order of term frequency.
    private  void print() {
        List<Entry<String, Integer>> entries = sort();
        for (Entry<String, Integer> entry: entries) {
            System.out.println(entry);
        }
    }

    // Computes the union of two search results.
    public WikiSearch or(WikiSearch that) {
        Map<String, Integer> union = new HashMap<String, Integer>(map); //copy contents of map
        for (String term : that.map.keySet()) {
            int relevance = totalRelevance(this.getRelevance(term), that.getRelevance(term));
            union.put(term, relevance);
        }
        return new WikiSearch(union);
    }

    // Computes the intersection of two search results.
    public WikiSearch and(WikiSearch that) {
        Map<String, Integer> intersection = new HashMap<String, Integer>();//dont copy
        for (String term : map.keySet()) {
            if (that.map.containsKey(term)) {
                int relevance = totalRelevance(this.map.get(term), that.map.get(term));
                intersection.put(term, relevance);
            }
        }
        return new WikiSearch(intersection);
    }

    // Computes the difference of two search results.
    public WikiSearch minus(WikiSearch that) {
        Map<String, Integer> difference = new HashMap<String, Integer>(map);
        for (String term : that.map.keySet()) {
            difference.remove(term);
        }
        return new WikiSearch(difference);
    }

    // Computes the relevance of a search with multiple terms.
    protected int totalRelevance(Integer rel1, Integer rel2) {
        return rel1+rel2;
    }

    // Sort the results by relevance.
    public List<Entry<String, Integer>> sort() {
        // make a list of entries
        List<Entry<String, Integer>> entries =
                new LinkedList<Entry<String, Integer>>(map.entrySet());

        // make a Comparator object for sorting
        Comparator<Entry<String, Integer>> comparator = new Comparator<Entry<String, Integer>>() {
            @Override
            public int compare(Entry<String, Integer> e1, Entry<String, Integer> e2) {
                return e1.getValue().compareTo(e2.getValue());
            }
        };

        // sort and return the entries
        Collections.sort(entries, comparator);
        return entries;
    }


    // Performs a search and makes a WikiSearch object.
    public static WikiSearch search(String term, Index index) {
        // TODO: Use the index to get a map from URL to count

        // Fix this
        Map<String, Integer> map = index.getCounts(term);

        // Store the map locally in the WikiSearch
        return new WikiSearch(map);
    }

    // TODO: Choose an extension and add your methods here

    public static void main(String[] args) throws IOException {

        // make a Index
        Jedis jedis = JedisMaker.make();
        Index index = new Index(jedis); // You might need to change this, depending on how your constructor works.

        // search for the first term
        String term1 = "java";
        System.out.println("Query: " + term1);
        WikiSearch search1 = search(term1, index);
        search1.map.keySet();
        search1.print();

        // search for the second term
        String term2 = "programming";
        System.out.println("Query: " + term2);
        WikiSearch search2 = search(term2, index);
        search2.print();

        // compute the intersection of the searches
        System.out.println("Query: " + term1 + " AND " + term2);
        WikiSearch intersection = search1.and(search2);
        intersection.print();
    }
}