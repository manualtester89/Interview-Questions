package LinkListQuestions;

import java.util.HashMap;

import binaryTreeQuestions.BinaryTree;

public class LinkListApp {
	// find the length
	public int length(LinkList head) {
		int count = 0;
		while (head != null) {
			count++;
			head = head.getNext();
		}
		return count;
	}

	// insert the element the list
	public LinkList inserAt(LinkList head, LinkList insertAt, int pos) {
		if (head == null)
			return insertAt;
		int length = length(head);
		if (length < pos - 1 || pos < 1) {
			throw new IllegalArgumentException("error");
		}
		if (pos == 1) {
			insertAt.setNext(head);
			head = insertAt;
		} else {
			int current = 1;
			LinkList cur = head;
			while (current < pos - 1) {
				current++;
				cur = cur.getNext();
			}
			insertAt.setNext(cur.getNext());
			cur.setNext(insertAt);
		}
		return head;
	}

	// Merge List
	public LinkList MergeList(LinkList A, LinkList B) {
		if (A == null)
			return B;
		if (B == null)
			return A;
		LinkList result = null;
		if (A.getData() > B.getData()) {
			result = B;
			result.setNext(MergeList(A, B.getNext()));
		} else {
			result = A;
			result.setNext(MergeList(A.getNext(), B));
		}
		return result;
	}

	// Insert into sorted Linklist
	public LinkList insertSorted(LinkList head, LinkList insertThis) {
		if (head == null)
			return insertThis;
		LinkList temp = head;
		if (insertThis.getData() < temp.getData()) {
			insertThis.setNext(temp);
			return insertThis;
		}
		LinkList prev = null;
		while (temp != null && temp.getData() < insertThis.getData()) {
			prev = temp;
			temp = temp.getNext();
		}
		prev.setNext(insertThis);
		insertThis.setNext(temp);
		return temp;
	}

	// middle element
	public LinkList middleLinkList(LinkList head) {
		if (head == null)
			return null;
		LinkList first = head, second = head;
		while (second != null && second.getNext() != null) {
			second = second.getNext().getNext();
			first = first.getNext();
		}
		return first;
	}

	// loop in the linklist
	public boolean isLoop(LinkList head) {
		if (head == null)
			return false;
		LinkList first = head, second = head;
		while (second != null && first != null) {
			first = first.getNext();
			if (first == null)
				return false;
			if (first == second)
				return true;
			first = first.getNext();
			if (first == null)
				return false;
			if (first == second)
				return true;
			second = second.getNext();
		}
		return false;

	}

	// reverse linklist
	public LinkList reverseLinkList(LinkList head) {
		if (head == null)
			return null;
		LinkList temp = null, current = head;
		while (current != null) {
			LinkList next = current.getNext();
			current.setNext(temp);
			temp = current;
			current = next;
		}
		return temp;
	}

	// Intersection of linklist
	public LinkList intersection(LinkList a, LinkList b) {
		int l1 = length(a);
		int l2 = length(b);
		int diff = l1 - l2;
		if (diff < 0) {
			LinkList c = a;
			a = b;
			b = c;
		}
		int counter = 0;
		while (counter < diff) {
			counter++;
			a = a.getNext();
		}
		while (a.getNext() != null && b.getNext() != null) {
			if (a == b)
				return a;
			a = a.getNext();
			b = b.getNext();
		}
		return a == null ? b : a;
	}

	// add two linklist
	public LinkList addTwoLinkList(LinkList a, LinkList b) {
		if (a == null && b == null)
			return null;
		int carry = 0;
		LinkList result = null, finalResult = null;
		while (a != null && b != null) {
			int ai = a == null ? 0 : a.getData();
			int bi = b == null ? 0 : b.getData();
			int ans = (ai + bi + carry) % 10;
			carry = (ai + bi + carry) / 10;
			if (result == null) {
				result = new LinkList(ans);
				finalResult = result;
			} else {
				result.setNext(new LinkList(ans));
			}
		}
		return finalResult;
	}

	// sort two linklist
	public LinkList sortLinkList(LinkList head) {
		if (head == null || head.getNext() == null)
			return head;
		LinkList left = head, right = middleLinkList(head).getNext();
		LinkList h1 = sortLinkList(left);
		LinkList h2 = sortLinkList(right);
		LinkList merged = MergeList(h1, h2);
		return merged;
	}

	// singly linklist to tree
	static LinkList head;

	public BinaryTree singlyLinkList(LinkList root) {
		if (root == null)
			return null;
		head = root;
		int length = length(root);
		return singlyLinkListUtil(0, length - 1);

	}

	private BinaryTree singlyLinkListUtil(int low, int high) {
		if (low < high)
			return null;
		int mid = low + (high - low) / 2;
		BinaryTree left = singlyLinkListUtil(low, mid - 1);
		BinaryTree current = new BinaryTree(head.getData());
		head = head.getNext();
		current.setLeft(left);
		BinaryTree right = singlyLinkListUtil(mid + 1, high);
		current.setRight(right);
		return current;
	}

	// copy linklist with random pointer
	public LinkList randomPointer(LinkList head) {
		if (head == null || head.getNext() == null)
			return head;
		LinkList newhead = null, copynewHead = null;
		LinkList headCopy = head;
		HashMap<LinkList, LinkList> hm = new HashMap<>();
		while (head != null) {
			if (newhead == null) {
				newhead = new LinkList(head.getData());
				copynewHead = head;
			} else {
				newhead.setNext(new LinkList(head.getData()));
			}
			hm.put(head, newhead);
			head = head.getNext();
		}
		newhead = copynewHead;
		while (headCopy != null) {
			LinkList temp = hm.get(headCopy);
			// copynewHead.setRandomPointer(hm.get(temp.getRandomPointer()));
		}
		return newhead;
	}

	// given a node delete that
	public LinkList deleteNode(LinkList node) {
		if (node == null || node.getNext() == null)
			return node;
		LinkList temp = node.getNext();
		node.setData(temp.getData());
		node.setNext(temp.getNext());
		return temp;
	}

	// pairwise reversal

	// k reversal

	// spiral Linklist

	// divide circular linklist in two circular list

	// check if linklist is palindrome

	// rotate linklist

	// merge k sorted linklist

	// remove duplicates from linklist

	// LRU CACHE

	// doubly linklist to tree

}
