package com.algo.season2.second;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// 물 채우기
// 고슴도치 이동 시키기

// https://www.acmicpc.net/problem/3055
// 탈출
public class Main_G4_3055_이지현 {
	static int R, C, time = 0;
	static char[][] map;
	static boolean[][] isVisited;
	static boolean isLive = false;
	static int[] dr = {-1, 1, 0, 0}; // 상 하 좌 우
	static int[] dc = {0, 0, -1, 1}; // 상 하 좌 우
	
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
		
		Queue<Point> pipiQueue = new LinkedList<>();
		map = new char[R][C];
		isVisited = new boolean[R][C];
		
		// map에 맵 입력
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == 'S') { // 고슴도치의 시작 위치를 Queue에 넣음
					pipiQueue.offer(new Point(i, j));
					isVisited[i][j] = true;
				}
			}
		}
		
		Point curP, nextP;
		while(!pipiQueue.isEmpty()) {
			// 물 채우기
			Queue<Point> waterQueue = new LinkedList<>();
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if(map[i][j] == '*')
						waterQueue.offer(new Point(i, j));
				}
			}
			while(!waterQueue.isEmpty()) {
				curP = waterQueue.poll();
				for (int i = 0; i < 4; i++) {
					if(watercango(curP.i+dr[i], curP.j+dc[i]))
						map[curP.i+dr[i]][curP.j+dc[i]] = '*';
				}
			}
			
			// 고슴도치 이동 시키기
			// (Queue에 들어있는) 모든 평행세계의 고슴도치에게서 각각 진행할 수 있는 경우의 수를 따짐
			for (int i = 0, end = pipiQueue.size(); i < end; i++) {
				curP = pipiQueue.poll();

				if(map[curP.i][curP.j] == 'D') { // 도착한 거였다면 isLive = true
					isLive = true;
					break;
				}
				
				for (int m = 0; m < 4; m++) {
					nextP = new Point(curP.i+dr[m], curP.j+dc[m]);
					if(pipicango(nextP.i, nextP.j) && !isVisited[nextP.i][nextP.j]) {
						pipiQueue.offer(nextP);
						isVisited[nextP.i][nextP.j] = true;
					}
				}
			}
			
			if(isLive)
				break;	
			
			time++; // 1분 경과
		}
		
		if(isLive)
			System.out.println(time);
		else
			System.out.println("KAKTUS");
	}

	private static boolean watercango(int i, int j) {
		if(i >= 0 && i < R && j >= 0 && j < C && map[i][j] != 'X' && map[i][j] != '*' && map[i][j] != 'D')
			return true;
		return false;
	}
	
	private static boolean pipicango(int i, int j) {
		if(i >= 0 && i < R && j >= 0 && j < C && map[i][j] != 'X' && map[i][j] != '*')
			return true;
		return false;
	}

}
