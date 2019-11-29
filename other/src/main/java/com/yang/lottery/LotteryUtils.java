package com.yang.lottery;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class LotteryUtils {

	/**
	 * 获取中奖编码
	 * 
	 * @param rlist
	 * @return
	 */
	public static Reward getReward(List<Reward> rlist) {
		for (Iterator<Reward> oldListIterator = rlist.iterator(); oldListIterator.hasNext();) {
			Reward reward = oldListIterator.next();
			if (reward.getQuantity() != 0 && reward.getInventory() <= 0) {
				oldListIterator.remove();
			}
		}
		// 随机列表
		List<Reward> randomList = getRandomList(rlist);
		// 根据随机列表得到的概率区段
		List<Integer> percentSteps = getPercentSteps(randomList);
		// 概率区段的最大值
		int maxPercentStep = percentSteps.get(percentSteps.size() - 1);
		// 在概率区段范围内取一个随机数
		int randomStep = ThreadLocalRandom.current().nextInt(maxPercentStep) + 1;
		//int randomStep = new Random().nextInt(maxPercentStep) + 1;
		// 中间元素的下标
		int keyIndex = 0;
		float begin = 0;
		float end = 0;
		for (int i = 0; i < percentSteps.size(); i++) {
			if (i == 0) {
				begin = 0;
			} else {
				begin = percentSteps.get(i - 1);
			}
			end = percentSteps.get(i);
			// 判断随机数值是否在当前区段范围内
			if (randomStep > begin && randomStep <= end) {
				keyIndex = i;
				break;
			}
		}
		Reward reward = randomList.get(keyIndex);
		synchronized (LotteryUtils.class) {
			if (reward.getQuantity() == 0) {
				return reward;
			}
			if (reward.getInventory() > 0) {
				for (Reward reward2 : rlist) {
					if (reward2.getId() == reward.getId()) {
						reward2.setInventory(reward2.getInventory() - 1);
					}
				}
				return reward;
			}
		}
		return getReward(rlist);
	}

	/**
	 * 获取概率区段
	 * 
	 * @param rlist
	 * @return
	 */
	private static List<Integer> getPercentSteps(List<Reward> rlist) {
		List<Integer> percentSteps = new ArrayList<Integer>();
		int percent = 0;
		for (Reward r : rlist) {
			percent += r.getProbability() * 1000000;
			percentSteps.add(percent);
		}
		return percentSteps;
	}

	/**
	 * 获取随机列表
	 * 
	 * @param rlist
	 * @return
	 */
	private static List<Reward> getRandomList(List<Reward> rlist) {
		List<Reward> oldList = new ArrayList<Reward>(rlist);
		List<Reward> newList = new ArrayList<Reward>();
		// 随机排序的老序列中元素的下标
		int randomIndex = 0;
		// 随机排序下标的取值范围
		for (int i = 0, j = oldList.size(); i < j; i++) {
			// 指向下标范围
			// 取值范围元素的个数为多个时，从中随机选取一个元素的下标
//			randomIndex = ThreadLocalRandom.current().nextInt(oldList.size());
			randomIndex = ThreadLocalRandom.current().nextInt(oldList.size());
			// 取值范围元素的个数为一个时，直接返回该元素的下标
			// 在新的序列当中添加元素，同时删除元素取值范围中的randomIndex下标所对应的元素
			newList.add(oldList.remove(randomIndex));
		}
		return newList;
	}

}