package com.lind.common.core.util;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author lind
 * @date 2022/7/29 8:56
 * @since 1.0.0
 */
public class ThreadPoolTest {

	@Test
	public void pool() {
		ThreadPoolExecutor fastTriggerPool = new ThreadPoolExecutor(10, 15, 60L, TimeUnit.SECONDS,
				new LinkedBlockingQueue<Runnable>(100),
				r -> new Thread(r, "xxl-job, admin JobTriggerPoolHelper-fastTriggerPool-" + r.hashCode()));
		for (int i = 0; i < 50; i++) {
			fastTriggerPool.execute(new Runnable() {
				@SneakyThrows
				@Override
				public void run() {
					System.out.println("do..." + Thread.currentThread().getName());
				}
			});
		}
	}

	@Test
	public void linkedBlockingQueue() throws InterruptedException {

		final Queue<Integer> q1 = new LinkedBlockingQueue<Integer>();
		final Queue<Integer> perTheadTask = new LinkedBlockingQueue<Integer>();
		final int n = 1000000;
		final int m = 100;

		for (int i = 0; i < n; i++) {
			q1.add(i);
		}

		List<Thread> ts = new ArrayList<Thread>();
		for (int i = 0; i < m; i++) {
			ts.add(new Thread(() -> {
				int i1 = 0;
				while (perTheadTask.size() < n && i1++ < n / m) { // q2.size()
										// 非线程安全，所以设置每个线程添加平均个数，防止poll出null报错
					perTheadTask.add(q1.poll());
				}
			}));
		}

		for (Thread t : ts) {
			t.start();
		}

		System.out.println("启动了 " + m + " 个线程，每个线程处理 " + n / m + " 个操作");

		for (Thread t : ts) {
			while (t.isAlive()) {
				Thread.sleep(1);
			}
		}

		System.out.println("q1.size()：" + q1.size());
		System.out.println("q2.size()：" + perTheadTask.size());

		Set<Integer> set = new HashSet<Integer>();
		Integer i;
		while ((i = perTheadTask.poll()) != null) {
			set.add(i);
		}
		System.out.println("q2.size()：" + perTheadTask.size());
		System.out.println("set.size()：" + set.size());
	}

}
