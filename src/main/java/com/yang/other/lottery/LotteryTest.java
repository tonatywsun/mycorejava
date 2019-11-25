package com.yang.other.lottery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

public class LotteryTest {
	private static ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(50);

	public static void main1(String[] args) {
		List<Integer> rewardList = Collections.synchronizedList(new ArrayList<Integer>());
		List<Integer> rewardList2 = Collections.synchronizedList(new ArrayList<Integer>());
		List<Integer> rewardList3 = Collections.synchronizedList(new ArrayList<Integer>());
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		Map<Integer, Integer> map2 = new HashMap<Integer, Integer>();
		Map<Integer, Integer> map3 = new HashMap<Integer, Integer>();
		for (int i = 0; i < 100; i++) {
			/*newFixedThreadPool.execute(new Runnable() {
				@Override
				public void run() {*/
					int nextInt = new Random().nextInt(10);
					int nextInt2 = ThreadLocalRandom.current().nextInt(10);
					int nextInt3 = (int)(Math.random()*10);
					System.out.println(nextInt+"-"+nextInt2+"-"+nextInt3);
					/*rewardList.add(nextInt);
					rewardList2.add(nextInt2);
					rewardList3.add(nextInt3);*/
			/*	}
			});*/
		}
		/*newFixedThreadPool.shutdown();
		while (true) {
			if (newFixedThreadPool.isTerminated()) {
				break;
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		for (Integer reward : rewardList) {
			Integer integer = map.get(reward);
			if (integer == null) {
				map.put(reward, 1);
			} else {
				map.put(reward, integer + 1);
			}
		}
		for (Integer reward2 : rewardList2) {
			Integer integer = map2.get(reward2);
			if (integer == null) {
				map2.put(reward2, 1);
			} else {
				map2.put(reward2, integer + 1);
			}
		}
		for (Integer reward3 : rewardList3) {
			Integer integer = map3.get(reward3);
			if (integer == null) {
				map3.put(reward3, 1);
			} else {
				map3.put(reward3, integer + 1);
			}
		}
		System.out.println(JSONObject.toJSON(map));
		System.out.println(JSONObject.toJSON(map2));
		System.out.println(JSONObject.toJSON(map3));*/
	}

	public static void main(String[] args) {
		long currentTimeMillis = System.currentTimeMillis();
		List<Reward> rlist = initRewards();
		List<Reward> rewardList = Collections.synchronizedList(new ArrayList<Reward>());
		for (int i = 0; i < 1000000; i++) {
			newFixedThreadPool.execute(new Runnable() {
				@Override
				public void run() {
					Reward reward = LotteryUtils.getReward(rlist);
					rewardList.add(reward);
				}
			});
		}
		newFixedThreadPool.shutdown();
		while (true) {
			if (newFixedThreadPool.isTerminated()) {
				break;
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (Reward reward : rewardList) {
			Integer integer = map.get(reward.getId());
			if (integer == null) {
				map.put(reward.getId(), 1);
			} else {
				map.put(reward.getId(), integer + 1);
			}
		}
		System.out.println(JSONObject.toJSON(map));
		/*
		 * for (Reward reward2 : rlist) { System.out.println(reward2); }
		 */
		System.out.println(System.currentTimeMillis()-currentTimeMillis);
	}

	public static List<Reward> initRewards() {
		List<Reward> rlist = new ArrayList<Reward>();
		rlist.add(new Reward(1, 500000, 500000, "1", 0.000001f));
		rlist.add(new Reward(2, 500000, 500000, "2", 0.00001f));
		rlist.add(new Reward(3, 500000, 500000, "3", 0.0001f));
		rlist.add(new Reward(4, 500000, 500000, "4", 0.001f));
		rlist.add(new Reward(5, 500000, 500000, "5", 0.01f));
		rlist.add(new Reward(6, 500000, 500000, "6", 0.1f));
		rlist.add(new Reward(7, 500000, 500000, "7", 1));
		rlist.add(new Reward(8, 500000, 500000, "8", 10));
		rlist.add(new Reward(9, 0, 0, "9", 50));
		return rlist;
	}

	@Test
	public void createProjectNo() {
		Random r1 = new Random(100);
		Random r2 = new Random(100);

		for (int i = 0; i < 100; i++) {
			System.out.println(r1.nextInt(10) + ", " + r2.nextInt(10));
		}
	}

}
