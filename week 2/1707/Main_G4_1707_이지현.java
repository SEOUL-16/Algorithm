package com.algo.season2.second;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// [미해결: 시간 초과]

//BFS로 전체 그래프를 다 탐색,
//인접해있으면 하나는 1, 하나는 -1로 채워넣음
//하다가 오류가 발생하면 이분그래프가 아님

// https://www.acmicpc.net/problem/1707
// 이분 그래프
public class Main_G4_1707_이지현 {
	static int N; // 정점의 개수
	static int M; // 간선의 개수
	static int[] isVisited;
	static LinkedList<Integer>[] list;
	static boolean isBipartite;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] strs =  br.readLine().split(" ");
		int T = Integer.parseInt(strs[0]);
		
		for (int t = 1; t <= T; t++) {
			strs =  br.readLine().split(" ");
			N = Integer.parseInt(strs[0]);
			M = Integer.parseInt(strs[1]);
			
			isVisited = new int[N + 1]; // 인덱스 1~N
			list = new LinkedList[N + 1]; // 인덱스 1~N
			for (int i = 1; i <= N; i++) {
				list[i] = new LinkedList<Integer>();
			}
			
			for (int i = 0; i < M; i++) {
				strs = br.readLine().split(" ");
				list[Integer.parseInt(strs[0])].add(Integer.parseInt(strs[1]));
				list[Integer.parseInt(strs[1])].add(Integer.parseInt(strs[0]));
			}


			isBipartite = true;
			for (int i = 1; i <= N; i++) {
				bfs(i);
			}

			if(isBipartite)
				System.out.println("YES");
			else
				System.out.println("NO");
		}
	}

	private static void bfs(int cur) {
		int flag;
		if(isVisited[cur] != 0)
			flag = isVisited[cur];
		else
			flag = 1;
		
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(cur);
		isVisited[cur] = flag;

		while (!queue.isEmpty()) {
			cur = queue.poll();

			for (int i = 0; i < list[cur].size(); i++) {
				if (isVisited[list[cur].get(i)] == 0) {
					queue.offer(list[cur].get(i));
					isVisited[list[cur].get(i)] = -flag;
				}
				else {
					if(isVisited[cur] == isVisited[list[cur].get(i)]) {
						isBipartite = false;
						return;
					}
				}
			}
			
			flag = -flag;
		}
	}
}
