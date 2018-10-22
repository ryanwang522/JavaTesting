package math;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MathFunctions {

	/**
	 * Calculates and returns a string with `num` number of elements in the fibonacci sequence, space separated.
	 * 
	 * @param num The number of elements in the sequence to print
	 * @return A string with with `num` number of elements in the fibonacci sequence, space separated.
	 */
	public String printSequence(int num) {
		StringBuilder builder = new StringBuilder();

		for (int i = 1; i <= num; i++) {
			builder.append(getElement(i));
			builder.append(" ");
		}
		return builder.toString();
	}

	/**
	 * Non-Memoization
	 * Time complexity = O(2^n)
	 * Space complexity = O(n) for call stack
	 * 
	 * @param position
	 * @return
	 */
	public int getElement(int position) {
		if (position == 0) {
			return 0;
		}

		if (position == 1 || position == 2) {
			return 1;
		}

		return getElement(position - 1) + getElement(position - 2); // tail recursion
	}

	/**
	 * Memoization<br>
	 * Time complexity = O(n)
	 * Space complexity = O(n) for call stack + O(n) for memo array = O(n)
	 * <p>
	 * Decrease time complexity by increasing space complexity. Only compute each
	 * fib number once and then store the number.
	 * 
	 * @param position
	 * @param memo
	 * @return
	 */
	public int getElementMemoization(int position, int[] memo) {
		if (position == 0) {
			return 0;
		}

		if (position == 1 || position == 2) {
			return 1;
		}

		if (memo[position - 2] == 0) {
			// save time by only computing the fib of each number once
			memo[position - 2] = getElementMemoization(position - 1, memo) + getElementMemoization(position - 2, memo);
		}
		return memo[position - 2];
	}


	public ArrayList<Integer> findFactors(int num) {
		ArrayList<Integer> factors = new ArrayList<Integer>();

		// Skip two if the number is odd
		int incrementer = num % 2 == 0 ? 1 : 2;

		for (int i = 1; i <= Math.sqrt(num); i += incrementer) {

			// If there is no remainder, then the number is a factor.
			if (num % i == 0) {
				factors.add(i);

				// Skip duplicates
				if (i != num / i) {
					factors.add(num / i);
				}

			}
		}

		// Sort the list of factors
		Collections.sort(factors);

		return factors;
	}



	/**
	 * Reverts half of the number and compares it to the other half.
	 * Time complexity : O(log{10}n). We divided the input by 10 for every iteration, so the time complexity is
	 * O(log{10} n)<br>
	 * Space complexity : O(1) - no extra space used
	 * 
	 * @param x
	 * @return
	 */
	public boolean isPalindrome(int x) {
		// Special cases:
		// As discussed above, when x < 0, x is not a palindrome.
		// Also if the last digit of the number is 0, in order to be a palindrome,
		// the first digit of the number also needs to be 0.
		// Only 0 satisfy this property.
		if (x < 0 || (x % 10 == 0 && x != 0)) {
			return false;
		}

		int revertedNumber = 0;
		while (x > revertedNumber) {
			// x % 10 gets the last digit
			// multiply the number by 10 to move them up a place value, and then add the new last digit
			revertedNumber = revertedNumber * 10 + x % 10;

			// remove the last digit by dividing the number by 10 and moving all numbers down a place value.
			x /= 10;
		}

		// When the length is an odd number, we can get rid of the middle digit by revertedNumber/10
		// For example when the input is 12321, at the end of the while loop we get x = 12, revertedNumber = 123,
		// since the middle digit doesn't matter in palidrome(it will always equal to itself),
		// we can simply get rid of it.
		return x == revertedNumber || x == revertedNumber / 10;
	}

	public int longestConsecutive(final List<Integer> a) {
		int max = 0;

		if (a == null || a.isEmpty()) {
			return max;
		}

		max = 1;

		if (a.size() == 1) {
			return max;
		}

		Collections.sort(a);
		int last = a.get(0);
		int currentCount = 1;
		
		for (int i = 1; i < a.size(); i++) {
			final int current = a.get(i);

			if (current != last) {
				if (current == last + 1) {
					currentCount++;

					if (currentCount > max) {
						max = currentCount;
					}
				}
				else {
					currentCount = 1;
				}
			}
			last = current;
		}
		return max;
	}
	
	/**
	 * Given an array or list of objects, return a set of all possible subsets.
	 * 
	 * @author Tzipora Ziegler
	 */
	public <T> Set<Set<T>> getPowerset(T[] originalSet, int start) {
		Set<Set<T>> sets = new HashSet<Set<T>>();

		if (originalSet.length == 0 || start >= originalSet.length) {
			sets.add(new HashSet<T>());
			return sets;
		}

		T head = originalSet[start];

		for (Set<T> set : getPowerset(originalSet, ++start)) {
			Set<T> newSet = new HashSet<T>();
			newSet.add(head);
			newSet.addAll(set);
			sets.add(newSet);
			sets.add(set);
		}
		return sets;
	}

	/**
	 * This function uses Generics and Sets.
	 * <p>
	 * The complexity of the function is O(2^n), since it needs to generate 2^n possible combinations.
	 */
	public <T> Set<Set<T>> getPowerset(Set<T> originalSet) {
		Set<Set<T>> sets = new HashSet<Set<T>>();

		if (originalSet.isEmpty()) {
			sets.add(new HashSet<T>());
			return sets;
		}

		List<T> list = new ArrayList<T>(originalSet);
		T head = list.get(0);

		// list.remove(0);

		for (Set<T> set : getPowerset(new HashSet<T>(list.subList(1, list.size())))) {
			Set<T> newSet = new HashSet<T>();
			newSet.add(head);
			newSet.addAll(set);
			sets.add(newSet);
			sets.add(set);
		}
		return sets;
	}
	
	public static void main(String[] args){
		MathFunctions funcs = new MathFunctions();

		System.out.println(funcs.findFactors(0));
		System.out.println(funcs.longestConsecutive(Arrays.asList(0, 0, 1, 4, 3, 8, 1, 6, 0, 5)));
		
		
    }

}