import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SlidingWindow {

	public static void main(String[] args) {
		SlidingWindow sw = new SlidingWindow();

		// Maximum Points You Can Obtain from Cards
		int[] arr = { 6, 2, 3, 4, 7, 2, 1, 7, 1 };
		int k = 4;
		sw.maxCards(arr, k);

		// Longest Substring Without Repeating Characters
		String str = "cadbzabcd";
		sw.longestSubstring(str);

		// Max Consecutive Ones III
		int[] nums = { 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0 };
		sw.maxOnes(nums, 2);

		// Fruits into baskets
		int[] trees = { 1, 0, 1, 4, 1, 4, 1, 2, 3 };
		sw.findFruits(trees);

		// Longest Substring With At Most K Distinct Characters
		String s1 = "aaabbccd";
		sw.longestSubstringWihKDistinctChar(s1);

		// Number of Substrings Containing All Three Characters
		String s2 = "abcabc";
		sw.noOfSubstringAllThreeChar(s2);

		// Longest Repeating Character Replacement
		String s3 = "AABABBA";
		sw.longestRepeatingChar(s3, 2);
	}

	private void longestRepeatingChar(String s, int k) {
		int l = 0;
		int r = 0;
		int max = 0;
		int[] hash = new int[26];
		int maxFrequency = 0;
		int noOfConversions = 0;
		int n = s.length();

		while (r < n) {
			hash[s.charAt(r) - 'A']++;
			maxFrequency = Math.max(maxFrequency, hash[s.charAt(r) - 'A']);
			noOfConversions = (r - l + 1) - maxFrequency;

			if (noOfConversions > k) {
				hash[s.charAt(l) - 'A']--;
				l++;
				maxFrequency = Math.max(maxFrequency, hash[s.charAt(l) - 'A']);
				noOfConversions = (r - l + 1) - maxFrequency;
			}

			if (noOfConversions <= k) {
				max = Math.max(max, r - l + 1);
			}
			r++;
		}
		System.out.println("Longest Repeating Character Replacement: " + max);
	}

	private void longestSubstringWihKDistinctChar(String s) {
		int l = 0;
		int r = 0;
		int max = 0;
		int k = 2;
		int n = s.length();

		Map<Character, Integer> map = new HashMap<>();

		while (r < n) {
			map.merge(s.charAt(r), 1, (a, b) -> a + b);

			if (map.size() > k) {
				map.merge(s.charAt(l), 1, (a, b) -> a - b);
				if (map.get(s.charAt(l)) <= 0) {
					map.remove(s.charAt(l));
				}
				l++;
			}
			if (map.size() <= k) {
				max = Math.max(max, r - l + 1);
			}
			r++;
		}
		System.out.println("max: " + max);
	}

	private void noOfSubstringAllThreeChar(String s) {
		int[] hash = { -1, -1, -1 };
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			hash[s.charAt(i) - 'a'] = i;
			count = count + (Math.min(hash[0], Math.min(hash[1], hash[2])) + 1);
		}
		System.out.println("Number of Substrings Containing All Three Characters: " + count);
	}

	private void findFruits(int[] trees) {
		int l = 0;
		int r = 0;
		int max = 0;
		int k = 2;
		int n = trees.length;

		Map<Integer, Integer> map = new HashMap<>();

		while (r < n) {
			map.merge(trees[r], 1, (a, b) -> a + b);

			if (map.size() > k) {
				map.merge(trees[l], 1, (a, b) -> a - b);
				if (map.get(trees[l]) <= 0) {
					map.remove(trees[l]);
				}
				l++;
			}
			if (map.size() <= k) {
				max = Math.max(max, r - l + 1);
			}
			r++;
		}
		System.out.println("max: " + max);
	}

	private void maxOnes(int[] nums, int k) {
		int l = 0;
		int r = 0;
		int max = 0;
		int zeroes = 0;
		int n = nums.length;

		while (r < n) {
			if (nums[r] == 0) {
				zeroes++;
			}

			if (zeroes > k) {
				if (nums[l] == 0) {
					zeroes--;
				}
				l++;
			}

			if (zeroes <= k) {
				max = Math.max(max, r - l + 1);
			}
			r++;
		}
		System.out.println("Max consecutive ones: " + max);
	}

	private void longestSubstring(String str) {
		int n = str.length();
		int l = 0;
		int r = 0;
		int max = 0;
		int[] hash = new int[256];
		Arrays.fill(hash, -1);

		while (r < n) {
			if (hash[str.charAt(r)] != -1 && hash[str.charAt(r)] >= l) {
				l = hash[str.charAt(r)] + 1;
			}
			hash[str.charAt(r)] = r;
			max = Math.max(max, r - l + 1);
			r++;
		}
		System.out.println("max: " + max);
	}

	private void maxCards(int[] arr, int k) {
		int lSum = 0;
		int rSum = 0;
		int maxSum = 0;
		int rightIndex = arr.length - 1;

		for (int i = 0; i < k; i++) {
			lSum += arr[i];
		}
		maxSum = lSum;

		for (int i = k - 1; i >= 0; i--) {
			lSum -= arr[i];
			rSum += arr[rightIndex];
			rightIndex--;
			maxSum = Math.max(maxSum, lSum + rSum);
		}

		System.out.println("maxSum: " + maxSum);
	}
}
