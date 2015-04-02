package binaryTreeQuestions;

public class BinarySearchTreeAPP {
	
	public boolean isBST(BinaryTree root){
		if(root==null)return true;
		else return IsBSTUtil(root,Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	private boolean IsBSTUtil(BinaryTree root, int minValue, int maxValue) {
		if(root==null)return true;
		else return (root.getData()<=maxValue&&root.getData()>=minValue)&&IsBSTUtil(root.getLeft(), minValue, root.getData())&&IsBSTUtil(root.getRight(), root.getData(), maxValue);
	}


}
