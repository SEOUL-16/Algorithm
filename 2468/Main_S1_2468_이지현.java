package com.algo.season2.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// https://www.acmicpc.net/problem/2468
// 안전  영역
public class Main_S1_2468_이지현 {
	static int N, max_count = 0;
	static boolean[][] isVisited;
	static int[][] map, mapRained;
	static int[] dr = { -1, 0, 1, 0 }; // 상 우 하 좌
	static int[] dc = { 0, 1, 0, -1 }; // 상 우 하 좌

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] strs = br.readLine().split(" ");
		N = Integer.parseInt(strs[0]);

		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			strs = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(strs[j]);
			}
		}

		for (int r = 0; r <= 100; r++) { // 100개의 경우
			isVisited = new boolean[N][N];
			mapRained = new int[N][N];

			for (int i = 0; i < N; i++) { // 비가 r만큼 왔을 때 map
				for (int j = 0; j < N; j++) {
					if (map[i][j] <= r)
						mapRained[i][j] = 0; // 비에 잠김
					else
						mapRained[i][j] = 1; // 비에 안 잠김
				}
			}

			int count = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (mapRained[i][j] == 0)
						continue;

					if (bfs(new int[] { i, j })) // 아직 가보지 않은 섬이면
						count++;
				}
			}

			max_count = Integer.max(max_count, count);
			
			if(count == 0) // 다 잠겼으면 비가 더 왔을 때는 더 검사하지 말자
				break;
		}

		System.out.println(max_count);

	}

	private static boolean bfs(int[] cur) {
		if (isVisited[cur[0]][cur[1]]) // 이미 방문했으면
			return false;

		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] { cur[0], cur[1] });
		isVisited[cur[0]][cur[1]] = true;

		while (!queue.isEmpty()) {
			cur = queue.poll();

			int[] temp;
			for (int i = 0; i < 4; i++) { // 네 방향 탐색
				temp = new int[] { cur[0] + dr[i], cur[1] + dc[i] };

				if (cango(temp) && mapRained[temp[0]][temp[1]] == 1 && !isVisited[temp[0]][temp[1]]) {
					queue.offer(temp);
					isVisited[temp[0]][temp[1]] = true;
				}
			}
		}

		return true;
	}

	private static boolean cango(int[] temp) {
		if (temp[0] >= 0 && temp[0] < N && temp[1] >= 0 && temp[1] < N)
			return true;
		return false;
	}

}
