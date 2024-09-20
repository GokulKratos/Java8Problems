
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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

		// Binary Subarrays With Sum
		int[] binArr = { 1, 0, 0, 1, 1, 0 };
		int goal = 2;
		int count = sw.binarySubarrayCount(binArr, goal) - sw.binarySubarrayCount(binArr, goal - 1);
		System.out.println("No of Binary Subarrays With Sum: " + count);

		sw.binarySubarrayCountBruteForce(binArr, 2);

		//Count number of Nice subarrays
		int[] nums10 = { 1, 1, 2, 1, 1 };
		int k10 = 3;
		System.out.println(sw.numberOfSubarrays(nums10, k10));
		
		//Subarrays with K Different Integers
		int[] nums11 = { 1, 1, 2, 1, 1 };
		int k11 = 3;
		System.out.println(sw.subarraysWithKDistinctFunc(nums11, k11));
		
		//Minimum Window Substring
		String word12= "cabwefgewcwaefgcf";
		String t = "cae";
		System.out.println(sw.minWindowSubstring(word12,t));
	}

	private String minWindowSubstring(String s, String t) {
		if(s.length() < t.length()) {
			return "";
		}
		
		int l=0;
		int r=0;
		int count=0;
		int min=Integer.MAX_VALUE;
		int start = -1;
		
		Map<Character, Long> map = t.chars().mapToObj(a->(char)a)
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		
		while(r<s.length()) {
			if(map.containsKey(s.charAt(r))) {
				if(map.getOrDefault(s.charAt(r), -1l) > 0) {
					count++;
				} 
				map.merge(s.charAt(r), 1l, (a,b)->a-b);
			}
			
			while(count == t.length()) {
				int temp = r-l+1;
				if(temp < min) {
					min = temp;
					start=l;
				}
				if(map.containsKey(s.charAt(l))) {
					map.merge(s.charAt(l),1l,(a,b)->a+b);
				}
				
				if(map.getOrDefault(s.charAt(l), -1l)>0) {
					count--;
				}
				l++;
			}
			r++;
		}
		return start==-1 ? "" :  s.substring(start, start+min);
	}

	public int subarraysWithKDistinct(int[] nums, int k) {
        return subarraysWithKDistinctFunc(nums,k) - subarraysWithKDistinctFunc(nums,k-1);
    }

    public int subarraysWithKDistinctFunc(int[] arr, int k) {
        int l=0;
        int r=0;
        int count=0;
        Map<Integer,Integer> map = new HashMap<>();

        while(r<arr.length){
            map.put(arr[r], map.getOrDefault(arr[r],0) + 1);

            while(map.size() > k){
                map.put(arr[l], map.get(arr[l]) - 1);
                if(map.get(arr[l]) <= 0){
                    map.remove(arr[l]);
                }
                l++;
            }

            count=count+(r-l+1);
            r++;
        }
        return count;
    }
    
	public int numberOfSubarrays(int[] nums, int k) {
		return numberOfSubarraysFunc(nums, k) - numberOfSubarraysFunc(nums, k - 1);
	}

	public int numberOfSubarraysFunc(int[] nums, int k) {
		if (k < 0) {
			return 0;
		}
		int l = 0;
		int r = 0;
		int n = nums.length;
		int count = 0;
		int nice = 0;

		while (r < n) {
			count += nums[r] % 2;
			while (count > k) {
				count -= nums[l] % 2;
				l++;
			}
			nice = nice + (r - l + 1);
			r++;
		}
		return nice;
	}

	private void binarySubarrayCountBruteForce(int[] nums, int goal) {
		int sum = 0;
		int count = 0;
		for (int i = 0; i < nums.length; i++) {
			sum = 0;
			for (int j = i; j < nums.length; j++) {
				sum = sum + nums[j];
				if (sum == goal) {
					count++;
				}
			}
		}
		System.out.println("count brute force: " + count);
	}

	private int binarySubarrayCount(int[] nums, int goal) {
		if (goal <= 0) {
			return 0;
		}
		int l = 0;
		int r = 0;
		int count = 0;
		int sum = 0;

		while (r < nums.length) {
			sum += nums[r];

			while (sum > goal) {
				sum -= nums[l];
				l++;
			}

			count += (r - l + 1);
			r++;
		}
		System.out.println("count" + count);
		return count;
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
