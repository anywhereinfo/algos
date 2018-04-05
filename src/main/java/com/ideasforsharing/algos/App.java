package com.ideasforsharing.algos;

import java.util.Arrays;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        List<String> patterns = Arrays.asList("cat", "casper", "cater", "maninder", "elijah", "isabella", "isomorphic", "man", "eli", "a", "caterer");
        List<Trie.Node> rootNodes = Trie.constructTries(patterns);
        for (Trie.Node node: rootNodes) {
        	System.out.println("********Root Node**************");
        	node.print();
        }
    }
}
