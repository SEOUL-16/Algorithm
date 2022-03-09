package com.algo.season2.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// https://www.acmicpc.net/problem/11724
// 11724 연결 요소의 개수
public class Main_S2_11724_이지현 {
	static int N; // 정점의 개수
	static int M; // 간선의 개수
	static boolean[] isVisited;
	static LinkedList<Integer>[] list;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] strs =  br.readLine().split(" ");
		N = Integer.parseInt(strs[0]);
		M = Integer.parseInt(strs[1]);

		isVisited = new boolean[N + 1]; // 인덱스 1~N
		list = new LinkedList[N + 1]; // 인덱스 1~N
		for (int i = 1; i <= N; i++) {
			list[i] = new LinkedList<Integer>();
		}


		for (int i = 0; i < M; i++) {
			strs = br.readLine().split(" ");
			list[Integer.parseInt(strs[0])].add(Integer.parseInt(strs[1]));
			list[Integer.parseInt(strs[1])].add(Integer.parseInt(strs[0]));
		}

		int count = 0;
		for (int i = 1; i <= N; i++) {
			if (bfs(i)) // bfs를 할만한 아직 안 가본 곳이 있었으면
				count++;
		}

		System.out.println(count);
	}

	private static boolean bfs(int cur) {
		if (isVisited[cur]) // 이미 방문했으면
			return false;

		Queue<Integer> queue = new LinkedList<>();
		queue.offer(cur);
		isVisited[cur] = true;

		while (!queue.isEmpty()) {
			cur = queue.poll();

			for (int i = 0; i < list[cur].size(); i++) {
				if (!isVisited[list[cur].get(i)]) {
					queue.offer(list[cur].get(i));
					isVisited[list[cur].get(i)] = true;
				}
			}
		}

		return true;
	}
}
