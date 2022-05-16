package com.algo.season2.tenth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_G4_16954_이지현 {

	static class Point {
		int i;
		int j;
		int step;

		public Point(int i, int j, int step) {
			this.i = i;
			this.j = j;
			this.step = step;
		}
	}

	static boolean[][] isVisited;
	static char[][] map;
	static boolean can = false;
	static int[] dr = {0, -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dc = {0,  0, 1, 1, 1, 0, -1, -1, -1 };
	static int n;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = 8;
		map = new char[n][n];
		isVisited = new boolean[n][n];

		// 입력
		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			for (int j = 0; j < n; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		
		// BFS
		bfs(new Point(n - 1, 0, 0));

		System.out.println(can ? 1 : 0);
	}

	private static void bfs(Point point) {
		Queue<Point> queue = new LinkedList<>();
		queue.offer(point);
		//isVisited[point.i][point.j] = true;

		int time = 0;

		while (!queue.isEmpty()) {
			point = queue.poll();
			
			
//			System.out.println();
//			for (int i = 0; i < n; i++) {
//				for (int j = 0; j < n; j++) {
//					if(i == point.i && j==point.j)
//						System.out.print("@");
//					else
//						System.out.print(map[i][j]);
//				}
//				System.out.println();
//			}

			// 도착했는가?
			if(point.i == 0 && point.j == n-1) {
				can = true;
				break;
			}
				
			// 맵을 지금 밑으로 내려야 하나?
			if (time != point.step) {
				for (int i = n - 1; i > 0; i--) {
					for (int j = 0; j < n; j++) {
						map[i][j] = map[i - 1][j];
					}
				}

				for (int i = 0; i < n; i++) {
					map[0][i] = '.';
				}

				time++;
				
			
			}

			// 캐릭터의 위치가 벽과 겹치나?
			if (map[point.i][point.j] == '#')
				continue;

			// bfs
			for (int i = 0; i < 9; i++) {
				Point nextPoint = new Point(point.i + dr[i], point.j + dc[i], point.step + 1);
				if (cango(nextPoint.i, nextPoint.j) && !isVisited[nextPoint.i][nextPoint.j] && map[nextPoint.i][nextPoint.j] == '.') {
					queue.offer(nextPoint);
					isVisited[nextPoint.i][nextPoint.j] = true;
				}
			}
		}

	}

	private static boolean cango(int i, int j) {
		if (i >= 0 && i < n && j >= 0 && j < n)
			return true;
		return false;
	}

}