package com.algo.season2.second;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// [미해결: 메모리 초과]

// https://www.acmicpc.net/problem/3055
// 탈출
public class Main_G4_3055_이지현 {
	static int R, C, time = 0;
	static char[][] map;
	static boolean isLive = false;
	static int[] dr = { -1, 1, 0, 0 }; // 상 하 좌 우
	static int[] dc = { 0, 0, -1, 1 };

	static class Point {
		int i;
		int j;

		public Point(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] strs = br.readLine().split(" ");
		R = Integer.parseInt(strs[0]);
		C = Integer.parseInt(strs[1]);
		Point Ss = null;

		Queue<Point> pipiQueue = new LinkedList<>();
		map = new char[R][C];

		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
				if (map[i][j] == 'D')
					pipiQueue.offer(new Point(i, j));
				if (map[i][j] == 'S')
					Ss = new Point(i, j);
			}
		}

		while (!pipiQueue.isEmpty()) {
			if(isLive)
				break;
			
			// 물 채우고
			Queue<Point> waterQueue = new LinkedList<>();
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if (map[i][j] == '*')
						waterQueue.offer(new Point(i, j));
				}
			}
			Point curP;
			while (!waterQueue.isEmpty()) {
				curP = waterQueue.poll();
				for (int i = 0; i < 4; i++) {
					if (cango(curP.i + dr[i], curP.j + dc[i]))
						map[curP.i + dr[i]][curP.j + dc[i]] = '*';
				}
			}

			// 고슴도치 이동
			for (int i = 0, end = pipiQueue.size(); i < end; i++) {
				curP = pipiQueue.poll();

				for (int m = 0; m < 4; m++) {
					if (curP.i + dr[m] == Ss.i && curP.j + dc[m] == Ss.j) {
						isLive = true;
					}

					if (cango(curP.i + dr[m], curP.j + dc[m]))
						pipiQueue.offer(new Point(curP.i + dr[m], curP.j + dc[m]));
				}
			}
			time++;
		}

		if (isLive)
			System.out.println(time);
		else
			System.out.println("KAKTUS");
	}

	private static boolean cango(int i, int j) {
		if (i >= 0 && i < R && j >= 0 && j < C && map[i][j] != 'X' && map[i][j] != '*')
			return true;
		return false;
	}

}
