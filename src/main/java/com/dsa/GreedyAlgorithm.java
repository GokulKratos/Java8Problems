package com.advance.coding.questions;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GreedyAlgorithm {

	public static void main(String[] args) {
		GreedyAlgorithm ga = new GreedyAlgorithm();

		// 1.Assign Cookies
		// https://leetcode.com/problems/assign-cookies/description/
		int[] g = { 1, 2, 3 };
		int[] s = { 1, 1 };
		ga.findContentChildren(g, s);

		// 2.Lemonade Change
		// https://leetcode.com/problems/lemonade-change/description/
		int[] bills = { 5, 5, 5, 10, 20 };
		System.out.println("2.Lemonade Change: " + ga.lemonadeChange(bills));

		// 3.Shortest Job First (or SJF) CPU Scheduling
		int[] time = { 4, 3, 7, 1, 2 };
		ga.sjf(time);

		// 4.Jump Game
		// https://leetcode.com/problems/jump-game/
		int[] jump = { 2, 3, 1, 1, 0, 4 };
		System.out.println("Jump Game: " + ga.canJump(jump));

	}

	public boolean canJump(int[] nums) {
		int max = 0;
		for (int i = 0; i < nums.length; i++) {
			if (i > max) {
				return false;
			}
			max = Math.max(max, i + nums[i]);
		}
		return true;
	}

	private void sjf(int[] time) {
		int timer = 0;
		int waitingTime = 0;

		List<Integer> timeList = Arrays.stream(time).boxed().sorted().toList();

		for (int t : timeList) {
			waitingTime += timer;
			timer = timer + t;
		}
		System.out.println("SJF avg timer: " + waitingTime / timeList.size());
	}

	private boolean lemonadeChange(int[] bills) {
		int five = 0;
		int ten = 0;

		for (int bill : bills) {
			if (bill == 5) {
				five++;
			} else if (bill == 10) {
				if (five > 0) {
					ten++;
					five--;
				} else {
					return false;
				}
			} else {
				if (ten > 0 && five > 0) {
					ten--;
					five--;
				} else if (five > 2) {
					five -= 3;
				} else {
					return false;
				}
			}
		}
		return true;
	}

	private void findContentChildren(int[] g, int[] s) {
		List<Integer> greed = Arrays.stream(g).mapToObj(a -> a).sorted().collect(Collectors.toList());
		List<Integer> cookie = Arrays.stream(s).mapToObj(a -> a).sorted().collect(Collectors.toList());

		int l = 0;
		int r = 0;

		while (l < greed.size() && r < cookie.size()) {
			if (cookie.get(r) >= greed.get(l)) {
				l++;
			}
			r++;
		}

		System.out.println("1.Assign cookies: " + l);
	}
}
