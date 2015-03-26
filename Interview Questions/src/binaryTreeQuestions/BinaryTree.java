package binaryTreeQuestions;

public class BinaryTree {
	private int data;
	private BinaryTree left;
	private BinaryTree right;
	public BinaryTree(int data){
		this.data=data;		
	}
	public BinaryTree(){
		this.data=0;
	}
	public int getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}
	public BinaryTree getLeft() {
		return left;
	}
	public void setLeft(BinaryTree left) {
		this.left = left;
	}
	public BinaryTree getRight() {
		return right;
	}
	public void setRight(BinaryTree right) {
		this.right = right;
	}
	
}
