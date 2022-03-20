package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_G4_2638_김태하 {
	// 비어있는 칸에서 dfs를 통해 인접한 비어있는 칸을 탐색한다.
	// 빈칸마다 사방탐색 하면서 인접한 칸에 치즈가 있으면 그 칸에 스택을 쌓는다.
	// 탐색이 끝난 뒤 맵을 순회하면서 스택이 2개 이상 쌓인 치즈를 제거한다.
	// 치즈에 쌓인 스택을 초기화한다.
	
	static int N, M, time;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visited = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		while (!isFinished())
		{
			dfs(0, 0);
			remove();
			time++;
			resetVisited();
		}
		
		System.out.println(time);
	}


	private static void resetVisited() {
		for (int i = 0; i < N; i++) {
			Arrays.fill(visited[i], false);
		}
	}


	private static void remove() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] != 0) {
					if (map[i][j] > 2) {
						map[i][j] = 0;
					} else {
						map[i][j] = 1;
					}
				} 
			}
		}
	}


	private static void dfs(int r, int c) {
		visited[r][c] = true;
//		System.out.println("visited (" + r + ", " + c + ")");
		
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if (nr >= 0 && nr < N && nc >= 0 && nc < M) {
				if (map[nr][nc] == 0 && !visited[nr][nc]) {
					dfs(nr, nc);
				} else if (map[nr][nc] > 0){
					map[nr][nc]++;
				}
			}
		}
		
	}


	private static boolean isFinished() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] != 0) {
					return false;
				}
			}
		}
		return true;
	}
}
