package binaryTreeQuestions;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinaryTreeApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*BinaryTree root = new BinaryTree(1);
		root.setLeft(new BinaryTree(2));
		root.setRight(new BinaryTree(3));
		root.getLeft().setLeft(new BinaryTree(4));
		root.getLeft().setRight(new BinaryTree(5));
		new BinaryTreeApp().morrisInOrder(root);*/
		System.out.println(new BinaryTreeApp().maxSumPath(new BinaryTree(-3)));
	}

	// inOrderTraversal recursive
	public void inOrderTraversal(BinaryTree root) {
		if (root == null)
			return;
		inOrderTraversal(root.getLeft());
		System.out.println(root.getData());
		inOrderTraversal(root.getRight());
	}

	// inOrderTraversal Itrative
	public void inOrderTraversalIterative(BinaryTree root) {
		if (root == null)
			return;
		Stack<BinaryTree> st = new Stack<>();
		while (true) {
			while (root != null) {
				st.push(root);
				root = root.getLeft();
			}
			if (st.isEmpty())
				break;
			root = st.pop();
			System.out.println(root.getData());
			root = root.getRight();
		}
	}

	// preOrder recursive
	public void preOrderTraversal(BinaryTree root) {
		if (root == null)
			return;
		System.out.println(root.getData());
		preOrderTraversal(root.getLeft());
		preOrderTraversal(root.getRight());

	}

	// preOrderIterative
	public void preOrderTravelsalIterative(BinaryTree root) {
		if (root == null)
			return;
		Stack<BinaryTree> st = new Stack<>();
		while (true) {
			while (root != null) {
				System.out.println(root.getData());
				st.push(root);
				root = root.getLeft();
			}
			if (st.isEmpty())
				break;
			root = st.pop();
			root = root.getRight();
		}
	}

	// postOrder traversal
	public void postOrder(BinaryTree root) {
		if (root == null)
			return;
		postOrder(root.getLeft());
		postOrder(root.getRight());
		System.out.println(root.getData());
	}

	// postOrder Iterative
	public void postOrderIterative(BinaryTree root) {
		if (root == null)
			return;
		Stack<BinaryTree> st = new Stack<>();
		while (true) {
			if (root != null) {
				st.push(root);
				root = root.getLeft();
			} else {
				if (st.isEmpty())
					break;
				if (st.peek().getRight() == null) {
					root = st.pop();
					System.out.println(root.getData());
					if (st.peek().getRight() == root) {
						root = st.pop();
						System.out.println(root.getData());
					}
					if (!st.isEmpty()) {
						root = root.getRight();
					} else {
						root = null;
					}
				}
			}
		}
	}

	// level order travesal
	public void levelOrder(BinaryTree root) {
		if (root == null)
			return;
		Queue<BinaryTree> q = new LinkedList<>();
		q.add(root);
		while (!q.isEmpty()) {
			BinaryTree temp = q.remove();
			System.out.println(temp.getData());
			if (temp.getLeft() != null) {
				q.add(root.getLeft());
			}
			if (temp.getRight() != null) {
				q.add(root.getRight());
			}
		}

	}

	// Count elements in the tree
	public int countElements(BinaryTree root) {
		if (root == null)
			return 0;
		else
			return countElements(root.getLeft()) + 1
					+ countElements(root.getRight());
	}

	// Max height of the tree
	public int maxHeight(BinaryTree root) {
		if (root == null)
			return 0;
		int left = maxHeight(root.getLeft());
		int right = maxHeight(root.getRight());
		if (left > right)
			return left + 1;
		else
			return right + 1;
	}

	// max element in the tree
	public int maxElement(BinaryTree root) {
		if (root == null)
			return 0;
		return Math.max(root.getData(), Math.max(maxElement(root.getLeft()),
				maxElement(root.getRight())));
	}

	// count Leaf
	public int countLeaf(BinaryTree root) {
		if (root == null)
			return 0;
		if (root.getLeft() == null && root.getRight() == null) {
			return 1;
		}
		return countLeaf(root.getLeft()) + countLeaf(root.getRight());
	}

	// isbalanced
	public boolean isBalanced(BinaryTree root) {
		if (root == null)
			return false;
		else
			return (isBalancedUtil(root) == -1) ? false : true;
	}

	private int isBalancedUtil(BinaryTree root) {
		if (root == null)
			return 0;
		int left = isBalancedUtil(root.getLeft());
		int right = isBalancedUtil(root.getRight());
		if (left == -1 || right == -1)
			return -1;
		if (Math.abs(left - right) > 1)
			return -1;
		return Math.max(left, right) + 1;
	}

	// count half nodes
	public int countHalfNodes(BinaryTree root) {
		if (root == null)
			return 0;
		if ((root.getLeft() != null && root.getRight() == null)
				|| (root.getLeft() == null && root.getRight() != null)) {
			return 1;
		}
		return countHalfNodes(root.getLeft()) + countHalfNodes(root.getRight());
	}

	// tree to linklist
	static BinaryTree head = null, prev = null;

	public void Tree2LinkList(BinaryTree root) {
		if (root == null)
			return;
		Tree2LinkList(root.getLeft());
		if (prev == null) {
			head = root;
		} else {
			prev.setRight(root);
			root.setLeft(prev);
		}
		prev = root;
		Tree2LinkList(root.getRight());

	}

	// max Diameter of tree
	public int maxDiameter(BinaryTree root, int[] height) {
		if (root == null) {
			height[0] = 0;
			return 0;
		}
		int[] lh = new int[1];
		int[] rh = new int[1];
		int left = maxDiameter(root.getLeft(), lh);
		int right = maxDiameter(root.getRight(), rh);
		height[0] = Math.max(lh[0], rh[0]) + 1;
		return Math.max(left, Math.max(right, lh[0] + rh[0] + 1));
	}

	// construct the tree from pre order to In order
	static int preIndex = 0;

	public BinaryTree constructTree(int[] pre, int[] inOrder, int low, int high) {
		if (low > high) {
			return null;
		}
		BinaryTree root = new BinaryTree(pre[preIndex]);
		if (low == high) {
			return root;
		}
		int mid = search(inOrder, pre[preIndex]);
		preIndex++;
		root.setLeft(constructTree(pre, inOrder, low, mid - 1));
		root.setRight(constructTree(pre, inOrder, mid + 1, high));
		return root;
	}

	public int search(int[] a, int element) {
		for (int i = 0; i < a.length; i++) {
			if (a[i] == element)
				return i;
		}
		return -1;
	}

	// reverse Order
	public void reverseOrder(BinaryTree root) {
		if (root == null)
			return;
		Queue<BinaryTree> q = new LinkedList<BinaryTree>();
		q.add(root);
		Stack<BinaryTree> st = new Stack<>();
		while (!q.isEmpty()) {
			BinaryTree temp = q.remove();
			if (temp.getRight() != null) {
				q.add(temp.getRight());
			}
			if (temp.getLeft() != null) {
				q.add(temp.getLeft());
			}
			st.push(temp);
		}
		while (!st.isEmpty()) {
			System.out.println(st.pop().getData());
		}

	}

	// print all paths from root to leaf
	public void allPath(BinaryTree root) {
		if (root == null)
			return;
		else
			allPaths(root, new int[100], 0);
	}

	public void allPaths(BinaryTree root, int[] path, int index) {
		if (root == null)
			return;
		path[index] = root.getData();
		if (root.getLeft() == null && root.getRight() == null) {
			print(path);
		} else {
			allPaths(root.getLeft(), path, index + 1);
			allPaths(root.getRight(), path, index + 1);
		}
	}

	public void print(int[] path) {
		for (int i = 0; i < path.length; i++) {
			System.out.println(path[i]);
		}
	}

	// is there path from root to leaf which sum to X
	public boolean ispath(BinaryTree root, int target) {
		if (root == null) {
			return (target == 0);
		} else
			return ispath(root.getLeft(), target - root.getData())
					|| ispath(root.getRight(), target - root.getData());
	}

	// Is Binary trees are structually identical
	public boolean isIdentical(BinaryTree root1, BinaryTree root2) {
		if (root1 == null && root2 == null)
			return true;
		if (root1 == null || root2 == null)
			return false;
		else
			return (root1.getData() == root2.getData())
					&& isIdentical(root1.getLeft(), root2.getLeft())
					&& isIdentical(root1.getRight(), root2.getRight());
	}

	// is mirror of each other
	public boolean isMirror(BinaryTree root1, BinaryTree root2) {
		if (root1 != null && root2 == null)
			return true;
		if (root1 != null || root2 == null)
			return false;
		else
			return (root1.getData() == root2.getData())
					&& isMirror(root1.getLeft(), root2.getRight())
					&& isMirror(root1.getRight(), root2.getLeft());
	}

	// binaryTree foldable
	public boolean isFoldable(BinaryTree root) {
		if (root == null)
			return true;
		return isFoldableUtil(root.getLeft(), root.getRight());
	}

	public boolean isFoldableUtil(BinaryTree root1, BinaryTree root2) {
		if (root1 == null && root2 == null)
			return true;
		if (root1 == null || root2 == null)
			return false;
		return isFoldableUtil(root1.getLeft(), root2.getRight())
				&& isFoldableUtil(root1.getRight(), root2.getLeft());
	}

	// LCA of binaryTree
	public BinaryTree LCA(BinaryTree a, BinaryTree b, BinaryTree root) {
		if (root == null)
			return root;
		if (root == a || root == b) {
			return root;
		}

		BinaryTree left = LCA(a, b, root.getLeft());
		BinaryTree right = LCA(a, b, root.getRight());
		if (left != null && right != null) {
			return root;
		}
		return left != null ? left : right;
	}

	// Zig zag traversal
	public void zigZagTraversal(BinaryTree root) {
		if (root == null)
			return;
		Stack<BinaryTree> st1 = new Stack<>();
		Stack<BinaryTree> st2 = new Stack<>();
		st1.push(root);
		while (!st1.isEmpty() || !st2.isEmpty()) {

			while (!st1.isEmpty()) {
				BinaryTree temp = st1.pop();
				System.out.println(temp.getData());
				if (temp.getLeft() != null) {
					st2.push(temp.getLeft());
				}
				if (temp.getRight() != null) {
					st2.push(temp.getRight());
				}
			}

			while (!st2.isEmpty()) {
				BinaryTree temp = st2.pop();
				System.out.println(temp.getData());
				if (temp.getRight() != null) {
					st1.push(temp.getRight());
				}
				if (temp.getLeft() != null) {
					st1.push(temp.getLeft());
				}
			}

		}
	}

	// vertical sum
	public void verticalSum(BinaryTree root, HashMap<Integer, Integer> hm,
			int index) {
		if (root == null)
			return;
		if (hm.containsKey(index)) {
			hm.put(index, hm.get(index) + 1);
		} else {
			hm.put(index, 1);
		}
		verticalSum(root.getLeft(), hm, index - 1);
		verticalSum(root.getRight(), hm, index + 1);
	}

	// next sibiling
	public void setNextSibiling(BinaryTree root) {
		if (root == null)
			return;
		Queue<BinaryTree> q = new LinkedList<>();
		q.add(root);
		while (!q.isEmpty()) {
			BinaryTree temp = q.remove();
			if (temp == null && !q.isEmpty()) {
				q.add(null);
				continue;
			}
			if (temp.getLeft() != null) {
				q.add(temp.getLeft());
			}
			if (temp.getRight() != null) {
				q.add(temp.getRight());
			}
			if (q.peek() != null) {
				// temp.setNextSibiling(q.peek());
			}
		}

	}

	// print the boundary of the tree
	public void printBoundary(BinaryTree root) {
		if (root == null)
			return;
		System.out.println(root.getData());
		printLeftBoundary(root.getLeft());
		printLeaves(root.getLeft());
		printLeaves(root.getRight());
		printRightBoundary(root.getRight());
	}

	private void printRightBoundary(BinaryTree root) {
		if(root!=null){
			if(root.getRight()!=null){
				printRightBoundary(root.getRight());
				System.out.println(root.getData());
			}else if(root.getLeft()!=null){
				printBoundary(root.getLeft());
				System.out.println(root.getData());
			}
		}
		
	}

	private void printLeaves(BinaryTree root) {
		if(root!=null){
			printLeaves(root.getLeft());
			if(root.getLeft()==null && root.getRight()==null){
				System.out.println(root.getData());
			}
			printLeaves(root.getRight());
		}
		
	}

	private void printLeftBoundary(BinaryTree left) {
		if (left != null) {
			if (left.getLeft() != null) {
				System.out.println(left.getData());
				printBoundary(left.getLeft());
			} else if(left.getRight()!=null) {
				System.out.println(left.getData());
				printBoundary(left.getRight());
			}
		}

	}

	// morris inOrder traversal
	/*
	 * 1. While current is not null find the pre decessor for current. Once we
	 * found out then make right child of predecssor as current. 2. Then go to
	 * left to see if there is ny more tree to fix. Then repeat 3. if current
	 * left is null then print this then go to right 4. again find predecssor to
	 * remove the link which we created. 5. then print current and keep going
	 * right.
	 * http://stackoverflow.com/questions/5502916/explain-morris-inorder-tree
	 * -traversal-without-using-stacks-or-recursion
	 */
	public void morrisInOrder(BinaryTree root) {
		if (root == null)
			return;
		BinaryTree current = root, prev = null;
		while (current != null) {
			if (current.getLeft() == null) {
				System.out.println(current.getData());
				current = current.getRight();
			} else {
				prev = current.getLeft();
				while (prev.getRight() != null && prev.getRight() != current) {
					prev = prev.getRight();
				}
				if (prev.getRight() == null) {
					prev.setRight(current);
					current = current.getLeft();
				} else {
					prev.setRight(null);
					System.out.println(current.getData());
					current = current.getRight();
				}
			}
		}
	}

	// is subtree

	// trim a tree
	public BinaryTree Trim(BinaryTree root, BinaryTree a, BinaryTree b) {
		if (root == null)
			return root;
		if (a == null && b == null)
			return root;
		if (a == null) {
			a = new BinaryTree(Integer.MIN_VALUE);
		}
		if (b == null) {
			b = new BinaryTree(Integer.MAX_VALUE);
		}
		if (a.getData() > b.getData()) {
			BinaryTree temp = a;
			a = b;
			b = temp;
		}
		return TrimUtil(root, a, b);
	}

	public BinaryTree TrimUtil(BinaryTree root, BinaryTree a, BinaryTree b) {
		if (root == null)
			return root;
		root.setLeft(TrimUtil(root.getLeft(), a, b));
		root.setRight(TrimUtil(root.getRight(), a, b));
		if (root.getData() < a.getData()) {
			return root.getRight();
		}
		if (root.getData() > b.getData()) {
			return root.getLeft();
		}

		return root;
	}
	
	// max path sum between two nodes of the tree
	static int maxSubtreeSum=Integer.MIN_VALUE;
	public int maxSumPath(BinaryTree root){
		if(root==null)return -1;
		int maxSum = maxSumPathUtil(root);
		if(maxSum>maxSubtreeSum){
			return maxSum;
		}else{
			return maxSubtreeSum;
		}
	}

	private int maxSumPathUtil(BinaryTree root) {
		if(root==null)return 0;
		
		int left =maxSumPathUtil(root.getLeft());
		int right = maxSumPathUtil(root.getRight());
		int currentData = root.getData();
		if(currentData+left>currentData){
			currentData=currentData+left;
		}
		if(currentData+right>currentData){
			currentData= currentData+ right;
		}
		maxSubtreeSum = Math.max(currentData,maxSubtreeSum);
		return Math.max(left+root.getData(), Math.max(root.getData(), right+ root.getData()));
	}

}
