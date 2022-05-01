package com.algo.season2.second;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_G3_16236_이지현 {
	static class fish implements Comparable<fish> {
		int i;
		int j;
		int level;
		int ex;
		int step;

		public fish(int i, int j, int level, int ex, int step) {
			super();
			this.i = i;
			this.j = j;
			this.level = level;
			this.ex = ex;
			this.step = step;
		}

		@Override
		public int compareTo(fish o) {
			return level - o.level;
		}
	}

	static int N, time = 0;
	static int[][] map;
	static boolean[][] visited;
	static fish babyshark;
	static List<fish> prey = new LinkedList<>();
	static int[] dr = { -1, 0, 1, 0 }; // 상 우 하 좌
	static int[] dc = { 0, 1, 0, -1 }; // 상 우 하 좌

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];

		// 입력 받으면서 물고기들 위치도 따로 저장해놓기
		int temp;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				temp = Integer.parseInt(st.nextToken());
				if (temp == 9)
					babyshark = new fish(i, j, 2, 0, 0);
				else if (temp != 0)
					prey.add(new fish(i, j, temp, 0, 0));

				map[i][j] = temp;
			}
		}

		while (true) {
			int dis_temp, dis_min = Integer.MAX_VALUE;
			fish cur_prey = null;

			// 먹을 수 있는 물고기 중 가깝고, 위에 있는 물고기 찾기
			for (fish f : prey) {
				if (f.level >= babyshark.level)
					continue;

				// 거기까지 가는 거리를 BFS로 구하기
				dis_temp = bfs(f);
				//System.out.printf("(%d, %d)까지의 거리: %d\n", f.i, f.j, dis_temp);
				if (dis_min > dis_temp) {
					dis_min = dis_temp;
					cur_prey = f;
				}
			}

			if (cur_prey == null) 	// 먹을 수 있는 물고기가 이제 없음
				break;
			else { 					// 먹을 수 있는 물고기 찾음
				// 물고기 맵 수정
				map[cur_prey.i][cur_prey.j] = 9;
				// 상어 위치 수정
				map[babyshark.i][babyshark.j] = 0;
				// 물고기 삭제
				prey.remove(cur_prey);
				// 상어 정보 갱신
				babyshark.i = cur_prey.i;
				babyshark.j = cur_prey.j;
				babyshark.ex++;
				if (babyshark.ex == babyshark.level) {
					babyshark.level++;
					babyshark.ex = 0;
				}

				// 먹으러 가느라 걸린 시간 경과 반영
				time += dis_min;
			}
		}

		System.out.println(time);
	}

	private static int bfs(fish prey) {
		visited = new boolean[N][N];
		
		Queue<fish> queue = new LinkedList<>();
		queue.offer(babyshark);
		visited[babyshark.i][babyshark.j] = true;
		
		
		int ni, nj;
		fish cur;
		while(!queue.isEmpty()) {
			cur = queue.poll();
			//System.out.printf("(%d, %d)로 헤엄쳐\n", cur.i, cur.j);
			
			if(cur.i == prey.i && cur.j == prey.j) {
				return cur.step;
			}
			
			for (int k = 0; k < 4; k++) {
				ni = cur.i + dr[k];
				nj = cur.j + dc[k];
				
				if(cango(ni, nj)&&!visited[ni][nj]) {
					queue.offer(new fish(ni, nj, 0, 0, cur.step+1));
					visited[ni][nj] = true;
				}
			}
		}
		
		return Integer.MAX_VALUE; // 멀리 있는 작은 물고기까지 도착할 수 없을 경우에, 그 거리를 0으로 리턴하면 안되고 엄청 큰 값으로 리턴해야지!!!!
	}

	public static boolean cango(int i, int j) {
		if (i >= 0 && i < N && j >= 0 && j < N && map[i][j] <= babyshark.level)
			return true;
		else
			return false;

	}

}