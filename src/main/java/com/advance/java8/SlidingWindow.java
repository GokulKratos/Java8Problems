public class SlidingWindow {

	public static void main(String[] args) {
		SlidingWindow sw = new SlidingWindow();

		int[] arr = { 6, 2, 3, 4, 7, 2, 1, 7, 1 };
		int k = 4;
		sw.maxCards(arr, k);
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
