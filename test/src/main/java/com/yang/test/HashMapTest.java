package com.yang.test;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HashMapTest {
	public static void main(String[] args) {
		new HashMapTest().test2();
	}

	@Test
	public void test() {
		int size = 2 << 18;
		Map<String, String> map = new HashMap<String, String>(size, 0.75f);
		int point = (int) (size * 0.75);
		long nowTime1 = System.currentTimeMillis();
		for (Integer i = 0; map.entrySet().size() < point; i++) {
			// long nowTime = System.currentTimeMillis();
			map.put(i.toString(), i.toString());
			// System.out.println(System.currentTimeMillis() - nowTime);
		}
		System.out.println(System.currentTimeMillis() - nowTime1);
	}

	public static Map<Integer, Integer> map = new HashMap<Integer, Integer>(4);

	@Test
	public void test2() {
		ExecutorService threadPool = Executors.newFixedThreadPool(5);
		threadPool.execute(new Thread() {
			@Override
			public void run() {
				for (int i = 0; i < 1000; i++) {
					map.put(i, i);
				}
			}
		});
		threadPool.execute(new Thread() {
			@Override
			public void run() {
				for (int i = 1000; i < 2000; i++) {
					map.put(i, i);
				}
			}
		});
	}

	@Test
	public void test3() {
		Map<StudentEntry, StudentEntry> map = new HashMap<StudentEntry, StudentEntry>();
		StudentEntry student = new StudentEntry();
		StudentEntry setStudent = new StudentEntry();
		StudentEntry setStudent2 = new StudentEntry();
		setStudent2.setName("sunyangyang");
		map.put(setStudent, student);
		map.put(setStudent2, student);
		StudentEntry getStudent = map.get(setStudent);
		System.out.println(map.keySet().size());
		System.out.println(getStudent);
	}

	class StudentEntry {
		private String name;
		private int age;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		@Override
		public int hashCode() {
			System.out.println("hashCode run");
			return age;
		}

		@Override
		public boolean equals(Object obj) {
			System.out.println("equals run");
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			StudentEntry other = (StudentEntry) obj;
			if (age != other.age)
				return false;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "Student [name=" + name + ", age=" + age + "]";
		}
	}
}
