/**
 * This is the main class of this program. This class represents the best path for the  skier one step at a time based on the given requirements.
 * @author Fahmid Abdullah
 */

public class Ski {
	
	private BinaryTreeNode<SkiSegment> root; // Instance variable representing the root of the binary tree,
	
	/**
	 * This method creates the ski mountain the skier will take and implements the available paths including regular, jump, and slalom.
	 * @param data, a string array to create the ski mountain with the possible actions in each step.
	 */
	public Ski(String[] data) {
		SkiSegment segments[] = new SkiSegment[data.length];
		
		for (int i = 0; i < data.length; i++) {
			if(data[i] == null) {
				segments[i] = null;
			} else {
				if (data[i].contains("jump") ) {
					segments[i] = new JumpSegment(String.valueOf(i),data[i]); 
				} else if (data[i].contains("slalom")) {
					segments[i] = new SlalomSegment(String.valueOf(i),data[i]);
				} else {
					segments[i] = new SkiSegment(String.valueOf(i),data[i]);
				}
			}
		}
		
		TreeBuilder<SkiSegment> skBuilder = new TreeBuilder<SkiSegment>();
		LinkedBinaryTree<SkiSegment> skiTree = skBuilder.buildTree(segments);
		root = skiTree.getRoot();
	}
	
	/**
	 * Returns the root Binary Tree Node of the itself.
	 * @return the instance variable of Binary Tree Node root.
	 */
	public BinaryTreeNode<SkiSegment> getRoot() {
		return root;
	}
	
	/**
	 * Checks if the the current node is a leaf node.
	 * @param node, the current node
	 * @return Boolean True if the current node is a leaf node.
	 */
	public boolean leafNode(BinaryTreeNode<SkiSegment> node) {
		return node.getLeft() == null && node.getRight() == null;
	}
	
	/**
	 * Checks if there is only one subsequent path.
	 * @param node, the current node
	 * @return Boolean True if there is only one subsequent path.
	 */
	public Boolean isOneWay(BinaryTreeNode<SkiSegment> node) {
		if (node.getLeft() == null & node.getRight() != null) {
			return true;
		} else if (node.getLeft() != null & node.getRight() == null) {
			return true;
		} else {
			return false;
		}

	}
	
	/**
	 * Checks which path exists and returns that path.
	 * @param node, the current node
	 * @return the node containing the one subsequent path.
	 */
	public BinaryTreeNode<SkiSegment> oneWayNode(BinaryTreeNode<SkiSegment> node) {
		if (node.getLeft() == null & node.getRight() != null) {
			return node.getRight();
		} else {
			return node.getLeft();
		}

	}
	
	/**
	 * Checks if both subsequent path are jumps.
	 * @param node, the current node
	 * @return Boolean True if both subsequent paths are jumps.
	 */
	public Boolean isBothJump(BinaryTreeNode<SkiSegment> node) {
		return (node.getLeft().getData() instanceof JumpSegment) && (node.getRight().getData() instanceof JumpSegment);
	}
	
	/**
	 * Checks if only one subsequent is a jump.
	 * @param node, the current node
	 * @return Boolean True if only one subsequent path is a jump.
	 */
	public Boolean hasOneJump(BinaryTreeNode<SkiSegment> node) {
		if (node.getLeft().getData() instanceof JumpSegment == true && node.getRight().getData() instanceof JumpSegment == false) {
			return true;
		} else if (node.getLeft().getData() instanceof JumpSegment == false && node.getRight().getData() instanceof JumpSegment == true) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Finds which subsequent path is a jump and returns the node containing the jump.
	 * @param node, the current node
	 * @return the left node, or the right node depending on the best path with the given requirements.
	 */
	public BinaryTreeNode<SkiSegment> oneJump(BinaryTreeNode<SkiSegment> node) {
		if (node.getLeft().getData() instanceof JumpSegment) {
			return node.getLeft();
		} else {
			return node.getRight();
		}
	}
	
	/**
	 * Finds which subsequent path has a jump with a greater height and returns the node containing the higher jump.
	 * @param node, the current node
	 * @return the left node, or the right node depending on the best path with the given requirements.
	 */
	public BinaryTreeNode<SkiSegment> greaterHeight(BinaryTreeNode<SkiSegment> node) {
		int heightLeft = ((JumpSegment)node.getLeft().getData()).getHeight();
		int heightRight = ((JumpSegment)node.getRight().getData()).getHeight();
		
		if (heightLeft > heightRight) {
			return node.getLeft();
		} else {
			return node.getRight();
		}
	}
	
	/**
	 * Checks if both subsequent paths are regular nodes.
	 * @param node, the current node
	 * @return Boolean True if both subsequent paths are regular nodes.
	 */
	public boolean bothRegular(BinaryTreeNode<SkiSegment> node) {
		return (node.getLeft().getData() instanceof SkiSegment) && (node.getRight().getData() instanceof SkiSegment);
	}
	
	/**
	 * Checks if both subsequent paths are slalom nodes.
	 * @param node, the current node
	 * @return Boolean True if both subsequent paths are slalom nodes.
	 */
	public Boolean isBothSlalom(BinaryTreeNode<SkiSegment> node) {
		return (node.getLeft().getData() instanceof SlalomSegment) && (node.getRight().getData() instanceof SlalomSegment);
	}
	
	/**
	 * Finds which of the two slalom nodes is in the leeward direction and returns the node containing said node.
	 * @param node, the current node
	 * @return the left node, or the right node depending on the best path with the given requirements.
	 */
	public BinaryTreeNode<SkiSegment> leewardDirection(BinaryTreeNode<SkiSegment> node) {
		String directionLeft = ((SlalomSegment)node.getLeft().getData()).getDirection();
		

		if (directionLeft.charAt(0) == 'L') {
			return node.getLeft();
		} else {
			return node.getRight();
		}
	}
	
	/**
	 * Checks if there is only one slalom in the subsequent paths.
	 * @param node, the current node
	 * @return Boolean True if there exists only one slalom node in the subsequent paths.
	 */
	public Boolean hasOneSlalom(BinaryTreeNode<SkiSegment> node) {
		return ((node.getLeft().getData() instanceof SlalomSegment) || (node.getRight().getData() instanceof SlalomSegment));
	}
	
	/**
	 * Checks if the slalom node is in the leeward direction and returns the node with node with that slalom, or returns the opposite if the slalom node direction is windward.
	 * @param node, the current node
	 * @return the left node, or the right node depending on the best path with the given requirements.
	 */
	public BinaryTreeNode<SkiSegment> oneSlalom(BinaryTreeNode<SkiSegment> node) {
		if (node.getLeft().getData() instanceof SlalomSegment) {
			String directionLeft = ((SlalomSegment)node.getLeft().getData()).getDirection();
			if (directionLeft.charAt(0) == 'L') {
				return node.getLeft();
			} else {
				return node.getRight();
			}
			
		} else if (node.getRight().getData() instanceof SlalomSegment) {
			String directionRight = ((SlalomSegment)node.getRight().getData()).getDirection();
			if (directionRight.charAt(0) == 'L') {
				return node.getRight();
			} else {
				return node.getLeft();
			}
			
		}
		return node;
	}
	
	/**
	 * This is a recursive method that illustrates the best path for the skier with the given requirements.
	 * @param node, the current node
	 * @param sequence, an array containing data for the ski mountain being traversed.
	 */
	public void skiNextSegment (BinaryTreeNode<SkiSegment> node, ArrayUnorderedList<SkiSegment> sequence) {
		sequence.addToRear(node.getData());
		if (leafNode(node)) {
			
		} else {
			if (isOneWay(node)) {
				skiNextSegment(oneWayNode(node), sequence);
			} else if (isBothJump(node)) {
				skiNextSegment(greaterHeight(node), sequence);
			} else if (hasOneJump(node)) {
				skiNextSegment(oneJump(node), sequence);
			} else if (isBothSlalom(node)) {
				skiNextSegment(leewardDirection(node), sequence);
			} else if (isBothSlalom(node) == false && hasOneSlalom(node) == true) {
				skiNextSegment(oneSlalom(node), sequence);
			} else if (bothRegular(node)) {
				skiNextSegment(node.getRight(), sequence);
			}
		}
	}

}
