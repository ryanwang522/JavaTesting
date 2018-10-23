package math;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
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
		
		if (position > 46) {
			System.out.println("Integer Overflow");
			return -1;
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
		
		if (position > 46) {
			System.out.println("Integer Overflow");
			return -1;
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
	
	public <E> List<E> LCS(E[] s1, E[] s2) {
        // init
        int[][] num = new int[s1.length+1][s2.length+1];

        // populate our data structure, in this case a matrix.
        for (int i = 1; i <= s1.length; i++)
            for (int j = 1; j <= s2.length; j++)
                if (s1[i-1].equals(s2[j-1]))
                    num[i][j] = 1 + num[i-1][j-1];
                else
                    num[i][j] = Math.max(num[i-1][j], num[i][j-1]);

        // init
        // s1 is y, s2 is x on the matrix.
        int s1position = s1.length, s2position = s2.length;
        List<E> result = new LinkedList<E>();

        // walk the matrix, adding the elements from the s1/s2 collection to
        // result LinkedList that match our criteria.
        while (s1position != 0 && s2position != 0) {
            // if the elements are the same, then we add the element
            // from s1 to our linkedlist.
            if (s1[s1position-1].equals(s2[s2position-1])) {
                result.add(s2[s2position-1]);
                s1position--;
                s2position--;
            }
            // if the previous element in the row is larger than the element
            // in the same position of the previous row, then we decrement s2position
            // thus continuing on the same row, one element before.
            else if (num[s1position][s2position-1] >= num[s1position][s2position]) {
                s2position--;
            }
            // if the previous element in the row is smaller than the element
            // in the same position of the previous row, then we decrement s1position
            // thus stepping up one row in our matrix, and remaining in the same position of the row.
            else {
                s1position--;
            }
        }

        // Once we have finished walking the matrix, we reverse it.
        Collections.reverse(result);
        return result;
    }

	public static String listToStr(List<Character> list) {
		String result = "";
		int i = 0;
		while (i < list.size()) {
			result += (list.get(i).toString());
			i++;
		}
		return result;
	}
	
	public String getLCS(String word1, String word2) {
		Character[] a_char1 = new Character[word1.length()];
        Character[] a_char2 = new Character[word2.length()];
        for (int i = 0; i < word1.length(); i++)
            a_char1[i] = word1.charAt(i);
        for (int i = 0; i < word2.length(); i++)
            a_char2[i] = word2.charAt(i);
        
        Character[][] inputs = new Character[2][];
        inputs[0] = a_char1;
        inputs[1] = a_char2;
        
        return listToStr(this.LCS(inputs[0], inputs[1]));
        
	}
	
	public String multiply(String a, String b) {
		if ("0".equals(a) || "0".equals(b) || a.equals("") || b.equals(""))
		{
			return "0";
		}

		final ArrayList<Integer> productArray = new ArrayList<Integer>();
		int moveOver = 1;

		for (int j = b.length() - 1; j >= 0; j--) {
			int leftover = 0;
			int position = productArray.size() - moveOver++;
			final int bottomDigit = Character.getNumericValue(b.charAt(j));

			for (int i = a.length() - 1; i >= 0; i--) {
				final int topDigit = Character.getNumericValue(a.charAt(i));
				final int currentDigit = position >= 0 ? productArray.get(position) : 0;
				final int product = bottomDigit * topDigit + leftover + currentDigit;
				final int placeNumber = product % 10;
				leftover = product / 10;

				if (position >= 0) {
					productArray.set(position, placeNumber);
				}
				else {
					productArray.add(0, placeNumber);
				}

				position--;
			}

			if (leftover != 0) {
				productArray.add(0, leftover);
			}

		}

		return printList(productArray);
	}

	private String printList(ArrayList<Integer> list) {
		final StringBuilder builder = new StringBuilder();
		for (final int digit : list) {
			if (builder.length() > 0 || digit != 0) {
				builder.append(digit);
			}
		}

		return builder.toString();
	}
	
	public static void main(String[] args){
		MathFunctions funcs = new MathFunctions();

		// Some test cases
		System.out.println(funcs.multiply("654","20"));
		System.out.println(funcs.longestConsecutive(Arrays.asList(0, 0, 1, 4, 3, 8, 1, 6, 0, 5)));
	    System.out.println(funcs.getLCS("CDDABDACBC", "DCAAAACD"));
    }
}
