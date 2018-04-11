package com.ideasforsharing.algos;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
//        List<String> patterns = Arrays.asList("cat", "casper", "cater", "catered", "caters", "capital", "maninder", "elijah", "isabella", "isomorphic", "man", "eli", "a", "caterer");

    	Stream<String> stream = Files.lines(Paths.get("/Users/mbatth/Downloads/CSV_Database_of_Last_Names.csv"));
    	Collector <String, ?, List<String> > collector = Collectors.toList();
    	List<String> patterns = stream.collect(collector);

    	Trie.Node rootNode= Trie.constructTries(patterns);
        /**
        System.out.println("Top level Nodes: " + rootNode.nodes.size());
        for (Trie.Node node: rootNode.nodes) {
        	System.out.println("********Root Node**************");
        	node.print(0);
        }
        **/
        List<String> matches = Trie.matchingStrings("Rod", rootNode);
        System.out.println("# of matches: " + matches.size());
        for (String match : matches)
        	System.out.println(match);
    }
}
