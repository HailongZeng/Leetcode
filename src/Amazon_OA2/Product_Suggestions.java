package Amazon_OA2;

import java.util.*;

/**
 * Description:
 * Implement a function to return product suggestions using products from a product repository after each character is typed by the customer in the search bar.
 * If there are more than THREE acceptable products, return the product name that is first in the alphabetical order.
 * Only return product suggestions after the customer has entered two characters.
 * Product suggestions must start with the characters already typed.
 * Both the repository and the customer query should be compared in a CASE-INSENSITIVE way.
 *
 * Input:
 * The input to the method/function consist of three arguments:
 *
 * numProducts, an integer representing the number of various products in Amazon's product repository;
 * repository, a list of unique strings representing the various products in Amazon's product repository;
 * customerQuery, a string representing the full search query of the customer.
 * Output:
 * Return a list of a list of strings, where each list represents the product suggestions made by the system as the customer types each character of the customerQuery. Assume the customer types characters in order without deleting/removing any characters.
 *
 * Example:
 * Input:
 * numProducts = 5
 * repository = ["mobile", "mouse", "moneypot", "monitor", "mousepad"]
 * customerQuery = "mouse"
 *
 * Output:
 * [["mobile", "moneypot", "monitor"],
 * ["mouse", "mousepad"],
 * ["mouse", "mousepad"],
 * ["mouse", "mousepad"]]
 *
 * Explanation:
 * The chain of words that will generate in the search box will be mo, mou, mous and mouse, and each
 * line from output shows the suggestions of "mo", "mou", "mous" and "mouse", respectively in each line.
 * For the suggestions that are generated for "mo", the matches that will be generated are:
 * ["mobile", "mouse", "moneypot", "monitor", "mousepad"]. Alphabetically, they will be reordered to
 * ["mobile", "moneypot", "monitor", "mouse", "mousepad"]. Thus, the suggestions are ["mobile", "moneypot", "monitor"]
 */
public class Product_Suggestions {

    public static class Trie{
        Trie[] nextLetters;
        PriorityQueue<String> pq;
        public Trie(){
            nextLetters = new Trie[26];
            pq = new PriorityQueue<>((s1, s2)->
            {
                return s2.compareToIgnoreCase(s1);
            });
        }
    }

    public static List<List<String>> suggestionProduct(int numProducts, List<String> repository, String customerQuery){
        if (repository == null) return Collections.emptyList();

        //Build Trie
        Trie root = new Trie();
        for (String product: repository){
            if (product == null) continue;
            product = product.toLowerCase();
            Trie walk = root;
            for (int i = 0; i < product.length(); i++){
                int c = product.charAt(i) - 'a';
                if (walk.nextLetters[c] == null){
                    walk.nextLetters[c] = new Trie();
                }
                walk = walk.nextLetters[c];
                walk.pq.add(product);
                if (walk.pq.size() > 3){
                    walk.pq.poll();
                }
            }
        }

        List<List<String>> res = new ArrayList<>();
        Trie walk = root;
        customerQuery = customerQuery.toLowerCase();
        for (int i = 0; i < customerQuery.length(); i++){
            int c = customerQuery.charAt(i) - 'a';
            if (walk.nextLetters[c] == null){
                break;
            }
            walk = walk.nextLetters[c];
            if (i > 0 && walk.pq.size() != 0){
                PriorityQueue tmp = walk.pq;
                PriorityQueue ans = new PriorityQueue<>();
                while (!tmp.isEmpty()){
                    ans.add(tmp.poll());
                }
                res.add(new ArrayList<>(ans));
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int numProducts = 5;
        List<String> repository = new ArrayList<>();
        repository.add("mobile");
        repository.add("mouse");
        repository.add("moneypot");
        repository.add("monitor");
        repository.add("mousepad");
        String customerQuery = "mouse";

        List<List<String>> res = suggestionProduct(numProducts, repository, customerQuery);
        for (List<String> ls: res){
            System.out.println(Arrays.toString(ls.toArray()));
        }
    }
}
