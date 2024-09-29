package com.dsa.greedyalgorithm;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

		// 5.Jump Game 2
		// https://leetcode.com/problems/jump-game-ii/description/
		// NOT ABLE TO UNDERSTAND AT THIS POINT OF TIME

		// 6.Job Sequencing Problem
		// https://www.geeksforgeeks.org/problems/job-sequencing-problem-1587115620/1
		List<Job> jobs = Stream.of(new Job(1, 4, 20), new Job(2, 5, 60), new Job(3, 6, 70), new Job(4, 6, 65),
				new Job(5, 4, 25), new Job(6, 2, 80), new Job(7, 2, 10), new Job(8, 2, 22)).toList();
		ga.maxProfit(jobs);

		// 7.N Meeting in One Room
		int[] st = { 0, 3, 1, 5, 5, 8 };
		int[] end = { 5, 4, 2, 9, 7, 9 };
		ga.noOfMeetings(st, end);
		
		//8.Non-overlapping Intervals
		//https://leetcode.com/problems/non-overlapping-intervals/description/
		int[][] intervals = {{1,2},{2,3},{3,4},{1,3}};
		ga.eraseOverlapIntervals(intervals);
	}

	private void eraseOverlapIntervals(int[][] intervals) {
        List<Meeting> jobs = new LinkedList<>();
        for(int i=0;i<intervals.length;i++){
                Meeting job = new Meeting(intervals[i][0], intervals[i][1],0);
                jobs.add(job);
        } 

        List<Meeting> sortedJobs = jobs.stream().sorted(Comparator.comparing(a->a.getEnd())).toList();
        int count = 1;
        int empty=sortedJobs.get(0).getEnd();

        for(int i=0;i<sortedJobs.size();i++){
            if(sortedJobs.get(i).getStart() >= empty){
                count++;
                empty=sortedJobs.get(i).getEnd();
            }
        }
        System.out.println("Non-overlapping Intervals: "+(sortedJobs.size() - count));
    }
	
	private void noOfMeetings(int[] st, int[] end) {
		List<Meeting> meetings = IntStream.range(0, st.length).mapToObj(i -> {
			Meeting m = new Meeting(st[i], end[i], i + 1);
			return m;
		}).toList();
		
		List<Meeting> sortedMeetings = meetings.stream().sorted(Comparator.comparing(Meeting::getEnd)).toList();
		
		int count=1;
		int empty=sortedMeetings.get(0).getEnd();
		List<Meeting> meetingHappend = new LinkedList<>();
		meetingHappend.add(sortedMeetings.get(0));
		
		for(int i=1;i<sortedMeetings.size();i++) {
			if(sortedMeetings.get(i).getStart() > empty) {
				count++;
				empty=sortedMeetings.get(i).getEnd();
				meetingHappend.add(sortedMeetings.get(i));
			}
		}
		
		System.out.println("No. of meetings: "+count);
		System.out.println("Meetings happend: "+meetingHappend);
	}

	private void maxProfit(List<Job> jobs) {
		List<Job> sortedJobs = jobs.stream().sorted(Comparator.comparing(Job::getProfit).reversed()).toList();
		int maxDeadLine = jobs.stream().sorted(Comparator.comparing(Job::getDeadLine).reversed()).toList()
				.get(0).deadLine;
		int[] hash = new int[maxDeadLine + 1];
		Arrays.fill(hash, -1);
		int maxProfit = 0;

		for (int i = 0; i < sortedJobs.size(); i++) {
			Job job = sortedJobs.get(i);
			int temp = job.deadLine;
			if (hash[temp] != -1) {

				while (temp >= 0 && hash[temp] != -1) {
					temp--;
				}
				if (temp >= 0) {
					hash[temp] = 1;
					maxProfit += job.profit;
				}
			} else {
				hash[temp] = 1;
				maxProfit += job.profit;
			}
		}
		System.out.println(maxProfit);
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

class Job {
	int id;
	int deadLine;
	int profit;

	public Job(int id, int deadLine, int profit) {
		this.id = id;
		this.deadLine = deadLine;
		this.profit = profit;
	}

	public int getProfit() {
		return this.profit;
	}

	public int getDeadLine() {
		return this.deadLine;
	}
}

class Meeting {
	int start;
	int end;
	int pos;

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public Meeting(int start, int end, int pos) {
		super();
		this.start = start;
		this.end = end;
		this.pos = pos;
	}

	@Override
	public String toString() {
		return "Meeting [start=" + start + ", end=" + end + ", pos=" + pos + "]";
	}

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

}
