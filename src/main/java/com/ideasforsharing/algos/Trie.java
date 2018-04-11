package com.ideasforsharing.algos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Trie {

	private static Node ROOT_NODE = new Node((char)65535, null);
	
	
	public static List<String> matchingStrings(String input, Node node) {
		List<String> result = new ArrayList<String>();
		
		char[] charPattern = input.toCharArray();
		int patternIndex = 0;
		for (char character: charPattern)
		{
			Node tempNode = searchParentNode(node, character);
			if (tempNode != null) {
				node = tempNode;
				++patternIndex;
				continue;
			}
			else 
				break;
		}
		
		if (node == ROOT_NODE) // nothing found
			return result;
		else if (patternIndex == input.length())// either we found full match or partial match. Only in case of full match, we should find remainder of eligible strings
		{
			constructStringsFromNode (node, new StringBuffer(), result, new String(Arrays.copyOfRange(charPattern, 0, charPattern.length-1)));
		}
		return result;
	}
	
	
	private static void constructStringsFromNode(Node node, StringBuffer buffer, List<String> strings, String input) {
		buffer.append(node.character);
		if (node.isLeaf() )
			strings.add(input + buffer.toString());
		else {
			if (node.isWord)
				strings.add(input + buffer.toString());
			for (Node tempNode: node.nodes)
			{

				constructStringsFromNode(tempNode,  new StringBuffer(buffer), strings, input);
			}
		}

		
	}
	
	public static Node constructTries(List<String> patterns) {
		if( (patterns == null) || (patterns.size() == 0))
			throw new IllegalArgumentException("supply atleast one pattern");	

		
	
		for (String pattern : patterns) {
			char[] charPattern = pattern.toCharArray();
			if (charPattern.length == 0) //supplied empty string
				continue;
			Node node = ROOT_NODE;
			int patternIndex = 0;
			for (char character: charPattern)
			{
				Node tempNode = searchParentNode(node, character);
				if (tempNode != null) {
					node = tempNode;
					++patternIndex;
					continue;
				}
				else 
					break;
			}
			
			// if node = ROOT_NODE, we need to construct a trie and add it to the rootNode.nodes
			if (node == ROOT_NODE)
				constructTrie(ROOT_NODE, charPattern, 0);
			else if ( (node != ROOT_NODE) && (patternIndex < charPattern.length))// we met a partial match
				constructTrie(node, charPattern, patternIndex);
			else // we met a full match
				node.makeWordNode();

		}
		return ROOT_NODE;
	}
	
	private static void constructTrie(Node parentNode, char[] pattern, int patternIndex ) {
		if (patternIndex >= pattern.length)
		{
			parentNode.makeWordNode();
			return;
		}
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
	private static Node searchParentNode(Node startingNode, char character)
	{			
		for (Node node : startingNode.nodes) {
			if (node.character == character)
			{
				return node;
			}
		}		
		return null;
	}

	
	public static class Node {
		char character;
		List<Node> nodes;
		boolean isWord = false;
		
		Node (char c, Node nextNode)
		{
			this.character = c;
			if (nodes == null)
				nodes = new ArrayList<Node>();
			
			if (nextNode != null)
				nodes.add(nextNode);
		}
		
		public void makeWordNode() {
			isWord = true;
		}
		
		public boolean isWord() {
			return isWord;
		}
		
		public void print(int spacing) {
			if (spacing == 0)
				System.out.printf("[%c - %d - %b]\n", character, nodes.size(), this.isWord);
			else
				for (int i=0;i<=spacing;i++)
					if (i == spacing)
						System.out.printf("[%c - %d - %b]\n", character, nodes.size(), this.isWord);
					else
						System.out.printf("%s","-");
			for (Node node: nodes) 
				node.print(spacing+2);
		}
		
		public boolean isLeaf() {
			return nodes.isEmpty();
		}

	}
}
