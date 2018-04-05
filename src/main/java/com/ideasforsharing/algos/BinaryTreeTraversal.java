package com.ideasforsharing.algos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BinaryTreeTraversal {
	
	
	public static void main(String[] args) {
		List<Integer> input = new ArrayList<Integer>();
		input.add(new Integer(4));
		input.add(new Integer(5));
		input.add(new Integer(7));
		input.add(new Integer(3));
		input.add(new Integer(2));
		BinaryTreeTraversal bst = new BinaryTreeTraversal();
		bst.traverse(input);
			
		}
		
	
	public void traverse(List<Integer> input) {
		Integer previous = null;
		Direction direction = null;
		int index= 0 ;
		
		Iterator<Integer> iter = input.iterator();
		while (iter.hasNext()) {
			Integer current = iter.next();
			if (previous == null) {
				previous = current;
				index++;
				continue;
			} else if (current > previous){
				
				if (isDirectionRight(direction)) {
					previous = current;
					index++;
					continue;
				} else if (isDirectionNull(direction)) {
					direction = Direction.RIGHT;
					previous = current;
					index++;
				} else //change in direction detected and we are now moving to left
				{
					if ((checkIfIntegerisBiggerThanPreviousSoFar(index, current, input)) && 
							((isDirectionRight(direction)))){
						previous = current;
						index++;
						direction = Direction.LEFT;
						continue ; }
					else 
					{
						System.err.println("NO: " + current);
						System.exit(0);
					}
				}
			} else {
				
				if (isDirectionLeft(direction)) {
					index++;
					previous = current;
					continue;
				} else if (isDirectionNull(direction)) {
					index++;
					previous = current;
					direction = Direction.LEFT;
				} else {
					if (checkIfIntegerisSmallerThanPreviousSoFar(index, current, input)) {
						direction = Direction.LEFT;
						index++;
						previous = current;
						continue;
					}
					else
						{
							System.err.println("NO: " + current);
							System.exit(0);
						}
				}
			}
				
		}
		System.out.println(true);
	}
	
	private boolean isDirectionRight(Direction direction) {
		return (direction == Direction.RIGHT) ? true: false;
	}
	
	private boolean isDirectionLeft(Direction direction) {
		return (direction == Direction.LEFT) ? true : false;
	}
	
	private boolean isDirectionNull (Direction direction) {
		return (direction == null) ? true: false;
	}
	
	private boolean checkIfIntegerisBiggerThanPreviousSoFar(int index, Integer current, List<Integer> input) {
		int currIndex = 0;
		while (currIndex < index) {
			if (current > input.get(currIndex)) {
				currIndex++;
				continue;
			} else
				return false;
		}
		return true;
	}
	
	private boolean checkIfIntegerisSmallerThanPreviousSoFar(int index, Integer current, List<Integer> input) {
		int currIndex = 0;
		while (currIndex < index) {
			Integer temp = input.get(currIndex);
			if (current < temp) {
				currIndex++;
				System.out.println("current < temp " + current + " " + temp);
				continue;
			} else
				return false;
		}
		return true;
	}
	
	private enum Direction {
		LEFT, RIGHT;
	}
}


