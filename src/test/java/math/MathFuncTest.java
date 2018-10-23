package math;

import static org.junit.Assert.*;

import java.util.Arrays;
import org.junit.Test;
import org.junit.Before;

public class MathFuncTest {
	
	public static MathFunctions mathFuncs;
	
	@Before
	public void setup() {
		mathFuncs = new MathFunctions();
	}

	@Test
	public void testFibSeq() {
		System.out.println("Testing fibonacci sequence...");
		assertEquals(0, mathFuncs.getElement(0));
		assertEquals(13, mathFuncs.getElement(7));
		assertEquals(34, mathFuncs.getElement(9));
		
		assertEquals(0, mathFuncs.getElementMemoization(0, new int[0]));
		assertEquals(13, mathFuncs.getElementMemoization(7, new int[7]));
		assertEquals(34, mathFuncs.getElementMemoization(9, new int[9]));
		
		/* Test long type argument */
		//assertEquals(4660046610375530309L, (long) mathFuncs.getElement(91));
		//assertEquals(4660046610375530309L, (long) mathFuncs.getElementMemoization(91, new int[91]));
	}

	@Test
	public void testFindFactors() {
		System.out.println("Testing find factors...");
		assertEquals(Arrays.asList(1, 2, 4), mathFuncs.findFactors(4));
		assertEquals(Arrays.asList(1, 2, 3, 6, 17, 34, 51, 102), mathFuncs.findFactors(102));
		assertEquals(Arrays.asList(1, 5, 293, 1465), mathFuncs.findFactors(1465));
		assertEquals(Arrays.asList(), mathFuncs.findFactors(0));
		assertEquals(Arrays.asList(1), mathFuncs.findFactors(1));
		assertEquals(Arrays.asList(1, 31), mathFuncs.findFactors(31));
	}

	@Test
	public void testPalindrome() {
		System.out.println("Testing palindrome...");
		assertEquals(true, mathFuncs.isPalindrome(597868795));
		assertEquals(true, mathFuncs.isPalindrome(0));
		assertEquals(false, mathFuncs.isPalindrome(10010));
		assertEquals(true, mathFuncs.isPalindrome(23211232));
	}
	
	@Test
	public void testLS() {
		System.out.println("Testing longest sequence");
		assertEquals(5, mathFuncs.longestConsecutive(Arrays.asList(100, 1, 5, 3, 1518, 1256, 2154, 2, 88, 4)));
		assertEquals(6, mathFuncs.longestConsecutive(Arrays.asList(5, 4, 3, 2, 1, 0)));
		assertEquals(1, mathFuncs.longestConsecutive(Arrays.asList(1, 3, 5, 7, 9)));
		assertEquals(4, mathFuncs.longestConsecutive(Arrays.asList(0, 0, 1, 4, 3, 8, 1, 7, 0, 5, 9, 10)));
		assertEquals(4, mathFuncs.longestConsecutive(Arrays.asList(-1, -2, 2, -4, 4, 3, -3)));
	}

	@Test
	public void testLCS() {
		System.out.println("Testing LCS");
		assertEquals("notfcaon", mathFuncs.getLCS("notification", "notefucashon"));
		assertEquals("ABCABC", mathFuncs.getLCS("ABCABCBA", "CBABCABCC"));
		assertEquals("ABAB", mathFuncs.getLCS("ABAB", "ABABAB"));
		assertEquals("ACT", mathFuncs.getLCS("GACT", "AGCAT"));
		//assertEquals("ABCAAABBACABACABABABCB", mathFuncs.getLCS("ABCAAABBABBCCABCBACABABABCCBC", "ABCABABABCBACABCBACABABACBCB"));
	}
}
