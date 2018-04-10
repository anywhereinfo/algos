package com.ideasforsharing.algos;

import java.util.Arrays;
import java.util.List;

import com.ideasforsharing.algos.Trie.Node;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        List<String> patterns = Arrays.asList("cat", "casper", "cater", "maninder", "elijah", "isabella", "isomorphic", "man", "eli", "a", "caterer");
        Node rootNode= Trie.constructTries(patterns);
        System.out.println("Top level Nodes: " + rootNode.nodes.size());
        for (Trie.Node node: rootNode.nodes) {
        	System.out.println("********Root Node**************");
        	node.print(0);
        }
    }
}
