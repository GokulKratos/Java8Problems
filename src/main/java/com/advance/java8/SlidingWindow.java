import java.util.Arrays;

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
