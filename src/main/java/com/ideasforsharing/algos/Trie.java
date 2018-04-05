package com.ideasforsharing.algos;

import java.util.ArrayList;
import java.util.List;

public class Trie {

	public static List<Trie.Node> constructTries(List<String> patterns) {
		if( (patterns == null) || (patterns.size() == 0))
			throw new IllegalArgumentException("supply atleast one pattern");	

		List<Node> rootNodes = new ArrayList<Node>();
	
		for (String pattern : patterns) {
			char[] charPattern = pattern.toCharArray();
			if (charPattern.length == 0) //supplied empty string
				continue;
			NodeSearchResult result = searchLastMatchingParentNode(rootNodes, charPattern,0);
			if (result.matchingIndex < result.charPattern.length -1)
				constructTrie(result.parentNode, result.charPattern, ++result.matchingIndex);
			else // pattern already existed in nodes
				continue;
		}
		return rootNodes;
	}
	
	private static void constructTrie(Node parentNode, char[] pattern, int patternIndex ) {
		if (patternIndex >= pattern.length)
			return;
		Trie.Node node = new Trie.Node(pattern[patternIndex], null);
		parentNode.nodes.add(node);
		constructTrie(node, pattern, ++patternIndex);
	}
	
	/**
	 * This method searches for the parent Node, where a new Node would have to be attached. The possible results are
	 * 1. The charPattern is brand new and there is no existing Node that matches. Hence a new root node would be returned
	 * 2. The charPatern can be completly satisfied by existing Nodes, hence NULL_NODE would be returned
	 * 3. The last parent Node in rootNodes which matches charPattern would be returned
	 * @param charPattern
	 * @param rootNodes
	 * @return
	 */
	private static NodeSearchResult searchLastMatchingParentNode(List<Node> rootNodes, char[] charPattern, int startingIndex)
	{
		NodeSearchResult result;
				
		for (Node node : rootNodes) {
			if (node.character == charPattern[startingIndex])
				return searchLastMatchingParentNode(node.nodes, charPattern, ++startingIndex);
		}
			
		//none of the existing Nodes matched or rootNodes was empty
		Node newRootNode = new Node(charPattern[startingIndex], null);
		rootNodes.add(newRootNode);
		result = new NodeSearchResult(charPattern, startingIndex, newRootNode);		
		return result;

 
	}

	
	private static class NodeSearchResult {
		char[] charPattern;
		int matchingIndex;
		Node parentNode;
	
		NodeSearchResult(char[] charPattern, int matchingIndex, Node parentNode) {
			this.matchingIndex = matchingIndex;
			this.parentNode = parentNode;
			this.charPattern = charPattern;
		}
	}
	
	public static class Node {
		char character;
		List<Node> nodes;
		
		Node (char c, Node nextNode)
		{
			this.character = c;
			if (nodes == null)
				nodes = new ArrayList<Node>();
			
			if (nextNode != null)
				nodes.add(nextNode);
		}
		
		public void print() {
			System.out.printf("Character %c, Number of Nodes: %d\n", character, nodes.size());
			for (Node node: nodes) 
				node.print();
		}

	}
}
