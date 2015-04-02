package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import binaryTreeQuestions.BinaryTree;
import LinkListQuestions.LinkList;

public class LeetCodeApp {

	// probelem 1. 2 Sum problem target sum is given find the two element which
	// sums to that
	// number
	/*
	 * 1.Keep everything in hashmap then look for other pair 2. sort the array
	 * then keep two pointer one at start and one at end and move
	 * themaccordingly
	 */
	public void twoSum(int[] a, int target) {
		if (a == null)
			return;
		HashSet<Integer> hs = new HashSet<Integer>();
		for (int i = 0; i < a.length; i++) {
			hs.add(a[i]);
		}
		for (int i = 0; i < a.length; i++) {
			if (hs.contains(target - a[i])) {
				System.out.println(a[i] + " " + (target - a[i]));
			}
		}
	}

	// problem 15. 3sum problem target sum is given find the three elemeent
	// which sums to
	// that number
	/*
	 * 1. duplicates are allowed and number can repeat more times. 2. duplicates
	 * are not allowed.
	 */
	public void threeSum(int[] a, int target) {
		if (a == null)
			return;
		Arrays.sort(a);
		for (int i = 0; i < a.length; i++) {
			int j = i, k = a.length - 1;
			while (j <= k) {
				int sum = a[i] + a[j] + a[k];
				if (sum == target) {
					System.out.println(a[i] + " " + a[j] + " " + a[k]);
					return;
				}
				if (sum > target) {
					k--;
				} else {
					j++;
				}
			}
		}
	}

	public void threeSumWithoutDuplicates(int a[], int target) {
		if (a == null)
			return;
		Arrays.sort(a);
		for (int i = 0; i < a.length; i++) {
			int j = i + 1, k = a.length - 1;
			while (j < k && a[j] == a[i]) {
				j++;
			}
			if (j == k) {
				break;
			}
			while (j < k) {
				int sum = a[i] + a[j] + a[k];
				if (sum == target) {
					System.out.println(a[i] + " " + a[j] + " " + a[k]);
				}
				while (j < k && a[j] == a[j + 1])
					j++;
				while (j < k && a[k] == a[k - 1])
					k--;
				if (sum > target) {
					k--;
				}
				if (sum < target) {
					j++;
				}
			}
		}

	}

	// problem 16 3 sum closet find out 3 integers which are closet to the given
	// sum
	public int threeSumCloset(int[] a, int target) {
		if (a == null)
			return -1;
		int min = Integer.MAX_VALUE, finalSum = 0;
		Arrays.sort(a);
		for (int i = 0; i < a.length; i++) {
			int j = i + 1, k = a.length - 1;
			while (j < k) {
				int sum = a[i] + a[j] + a[k];
				if (sum == target) {
					min = 0;
					finalSum = sum;
					System.out.println(a[i] + "  " + a[j] + "  " + a[k]);
					break;
				}
				if (sum < target) {
					if (target - sum < min) {
						min = target - sum;
						finalSum = sum;
					}
					k--;
				}
				if (sum > target) {
					if (target - sum < min) {
						min = target - sum;
						finalSum = sum;
					}
					j++;
				}
			}
			while (i < a.length - 1 && a[i] == a[i - 1])
				i++;
		}
		return finalSum;
	}

	// problem 18 4Sum Problem
	public int fourSome(int a[], int target) {
		if (a == null)
			return 0;
		HashMap<Integer, ArrayList<Integer>> hm = new HashMap<>();
		for (int i = 0; i < a.length; i++) {
			for (int j = i + 1; j < a.length; j++) {
				int sum = a[i] + a[j];
				if (hm.containsKey(sum)) {
					hm.get(sum).add(a[i]);
					hm.get(sum).add(a[j]);
				} else {
					ArrayList<Integer> ar = new ArrayList<Integer>();
					ar.add(a[i]);
					ar.add(a[j]);
					hm.put(sum, ar);
				}
			}
		}
		@SuppressWarnings("rawtypes")
		Iterator i = hm.entrySet().iterator();
		while (i.hasNext()) {
			@SuppressWarnings("rawtypes")
			Map.Entry me = (Map.Entry) i.next();
			int key = (int) me.getKey();
			if (hm.containsKey(target - key)) {
				System.out.println(key + " " + (target - key));
				return 1;
			}
		}
		return 0;
	}

	// problem 67. Add Binary
	public String addBinary(String a, String b) {
		int carry = 0, i = 0, j = 0, sum = 0;
		StringBuilder sb = new StringBuilder();
		while (a != null && b != null) {
			int ai = (i < a.length()) ? a.charAt(i) - 'o' : 0;
			i++;
			int bj = (j < b.length()) ? b.charAt(j) - 'o' : 0;
			j++;
			sum = (ai + bj + carry) % 2;
			carry = carry / 2;
			sb.insert(0, sum);
		}
		if (carry > 0) {
			sb.insert(0, carry);
		}
		return sb.toString();
	}

	// problem 166. Fraction to recurring decimal
	public String FractionToDecimal(int num, int denum) {
		if (denum == 0)
			return "NAN";
		if (num == 0)
			return "0";
		String sign = (denum >> 31) == 1 ? "-" : "";
		if (num >> 31 == 1) {
			if (sign == "-") {
				sign = "";
			} else {
				sign = "-";
			}
		}
		// sign= (((num>>31)==1)&&(sign=="-"))?"":"-";
		long num1 = num, den1 = denum;
		num1 = Math.abs(num);
		den1 = Math.abs(denum);
		long major = num1 / den1;
		long rem = num1 % den1;
		if (rem == 0)
			return sign + major;

		String pre = sign + major + ".";
		StringBuilder sb = new StringBuilder();
		Map<Long, Integer> mp = new HashMap<>();
		while (rem != 0) {
			int res = (int) (rem * 10 / den1);
			if (mp.containsKey(rem)) {
				int index = mp.get(rem);
				sb.insert(index, "(");
				sb.append(")");
				return pre + sb;
			} else {
				sb.append(res);
				mp.put(rem, sb.length() - 1);
				rem = ((rem * 10) % den1);
			}
		}
		return sb.toString();
	}

	// problem 2. add two numbers
	public LinkList addTWONumbers(LinkList no1, LinkList no2) {
		if (no1 == null && no2 == null)
			return null;
		LinkList result = null, resultHead = null;
		int carry = 0;
		while (no1 != null && no2 != null) {
			int number1 = (no1 == null) ? 0 : no1.getData();
			int number2 = (no2 == null) ? 0 : no2.getData();
			int sum = (number1 + number2 + carry) % 10;
			if (result == null) {
				result = new LinkList(sum);
				resultHead = result;
			} else {
				result.setNext(new LinkList(sum));
			}
			carry = (number1 + number2 + carry) / 10;
		}

		if (carry > 0) {
			result.setNext(new LinkList(carry));
		}
		return resultHead;
	}

	// prob 49. Anagram
	public ArrayList<ArrayList<String>> anagrams(ArrayList<String> ar) {
		if (ar == null)
			return null;
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		HashMap<Integer, ArrayList<String>> hm = new HashMap<Integer, ArrayList<String>>();
		for (String string : ar) {
			char[] str = string.toCharArray();
			Arrays.sort(str);
			if (hm.containsKey(str.hashCode())) {
				ArrayList<String> temp = hm.get(str.hashCode());
				temp.add(string);
			} else {
				ArrayList<String> temp = new ArrayList<>();
				temp.add(string);
				hm.put(str.hashCode(), temp);
			}
		}
		Iterator it = hm.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			result.add((ArrayList<String>) pair.getValue());
		}
		return result;
	}

	// prob 121. Best time to buy and sell stock [only once and sell once]
	public int maxProfit(int[] prices) {
		if (prices == null)
			return -1;
		int min = Integer.MAX_VALUE;
		int max = 0;
		int diff = 0;
		for (int i = 0; i < prices.length; i++) {
			if(prices[i]<min){
				min=prices[i];
			}
			diff= prices[i]-min;
			if(max<diff)max=diff;
		}
		return max;
	}
	
	//prob 122. Best time to buy and sell stock[you can buy and sell n times]
	public int maxProfit2(int [] prices){
		if(prices==null)return -1;
		int sum=0;
		for (int i = 1; i < prices.length; i++) {
			int diff=  prices[i]-prices[i-1];
			if(diff<0)
				sum+=diff;
		}
		return sum;
	}
	
	//prob 110. Balanced Binary tree
	public boolean isBalanced(BinaryTree root){
		if(root==null)return true;
		int val = isBalancedUtil(root);
		if(val==-1)return false;
		return true;
	}
	
	private int isBalancedUtil(BinaryTree root) {
		if(root==null)return 0;
		int left = isBalancedUtil(root.getLeft());
		if(left==-1)return -1;
		int right = isBalancedUtil(root.getRight());
		if(right==-1)return -1;
		if(Math.abs(right-left)>1)return -1;
		return Math.max(left, right)+1;
	}

	public static void main(String[] args) {
		System.out.println(new LeetCodeApp().FractionToDecimal(1, 9973));
	}

}
