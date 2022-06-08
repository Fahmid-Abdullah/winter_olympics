/**
 * This generic class represents a TreeBuilder. It creates binary trees using a queue-based approach.
 * @author Fahmid Abdullah
 */

public class TreeBuilder<T> {
	
	/**
	 * This class takes a generic array and creates a binary tree using the array as the data.
	 * @param data, an array of information for the binary tree.
	 * @return After implementing the given data into a binary tree, the binary tree is then returned.
	 */
	public LinkedBinaryTree<T> buildTree(T[] data) {
		LinkedQueue<T> dataQueue = new LinkedQueue<T>();
		
		for (int i = 0; i < data.length; i++) {
			dataQueue.enqueue(data[i]);
		}
		
		LinkedQueue<BinaryTreeNode<T>> parentQueue = new LinkedQueue<BinaryTreeNode<T>>();
		BinaryTreeNode<T> rootNode = new BinaryTreeNode<T>(dataQueue.dequeue());
		LinkedBinaryTree<T> returnTree = new LinkedBinaryTree<T>(rootNode);
		parentQueue.enqueue(rootNode);
		
		while (!(dataQueue.isEmpty())) {
			T a = dataQueue.dequeue();
			T b = dataQueue.dequeue();
			BinaryTreeNode<T> parent = parentQueue.dequeue();
			
			if (a != null) {
				BinaryTreeNode<T> aNode = new BinaryTreeNode<T>(a);
				parent.setLeft(aNode);
				parentQueue.enqueue(aNode);
			}
			if (b != null) {
				BinaryTreeNode<T> bNode = new BinaryTreeNode<T>(b);
				parent.setRight(bNode);
				parentQueue.enqueue(bNode);
			}
			
		}
		
		return returnTree;
	}
}

