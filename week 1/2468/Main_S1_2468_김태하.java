package safearea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_S1_2468_김태하 {
	// 지역별 높이의 최소값(minHeight)과 최대값(maxHeight)을 구한다.
	// water를 최소값 - 1부터 최대값까지 순회시키면서,
	// 		지도의 각 원소를 순회하면서,
	// 			해당 지역이 방문되지 않았고 water 이상이면,
	//				dfs를 통해 상하좌우로 인접한 지역을 방문처리한다.
	//				안전영역의 개수(newanswer)를 증가시킨다.
	// 		현재 안전영역의 개수(answer)가 newanswer보다 작다면,
	//			answer = newanswer
	static int minHeight, maxHeight, N, water, answer, newanswer;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		minHeight = Integer.MAX_VALUE;
		maxHeight = 0;
		
		map = new int[N][N];
		visited = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int temp = Integer.parseInt(st.nextToken());
				
				map[i][j] = temp;
				if (temp < minHeight) {
					minHeight = temp;
				}
				
				if (temp > maxHeight) {
					maxHeight = temp;
				}
			}
		}
		
		for (int water = minHeight - 1; water <= maxHeight; water++) {
//		for (int water = 0; water < 100; water++) {
			newanswer = 0;
			visited = new boolean[N][N];
			
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (!visited[r][c] && map[r][c] >= water) {
						dfs(r, c, water);
						newanswer++;
					}
				}
			}
			if (newanswer > answer) {
				answer = newanswer;
			}
		}
		
		System.out.println(answer);
	}

	private static void dfs(int r, int c, int water) {
		visited[r][c] = true;
		
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc] && map[nr][nc] >= water) {
				dfs(nr, nc, water);
			}
		}
	}
}
