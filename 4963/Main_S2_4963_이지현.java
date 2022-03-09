package com.algo.season2.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// https://www.acmicpc.net/problem/4963
// 섬의 개수
public class Main_S2_4963_이지현 {
	static int w;
	static int h;
	static boolean[][] isVisited;
	static int[][] map;
	static int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 }; // 상 상우 우 우하 하 좌하 좌 좌상
	static int[] dc = { 0, 1, 1, 1, 0, -1, -1, -1 }; // 상 상우 우 우하 하 좌하 좌 좌상

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			String[] strs = br.readLine().split(" ");
			w = Integer.parseInt(strs[0]);
			h = Integer.parseInt(strs[1]);

			if (w == 0 && h == 0) // 루프 종료
				break;

			isVisited = new boolean[h][w];
			map = new int[h][w];

			for (int i = 0; i < h; i++) {
				strs = br.readLine().split(" ");
				for (int j = 0; j < w; j++) {
					map[i][j] = Integer.parseInt(strs[j]);
				}
			}

			int count = 0;
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if(map[i][j] == 0)
						continue;
					
					if (bfs(new int[] { i, j })) // 아직 가보지 않은 섬이면
						count++;
				}
			}

			System.out.println(count);

		} // while 루프 종료
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
			for (int i = 0; i < 8; i++) { // 여덟 방향 탐색
				temp = new int[] {cur[0]+dr[i], cur[1]+dc[i]};
				
				if (cango(temp) && map[temp[0]][temp[1]] == 1 && !isVisited[temp[0]][temp[1]]) {
					queue.offer(temp);
					isVisited[temp[0]][temp[1]] = true;
				}
			}
		}

		return true;
	}

	private static boolean cango(int[] temp) {
		if(temp[0] >= 0 && temp[0] < h && temp[1] >= 0 && temp[1] < w)
			return true;
		return false;
	}
	
	
}
