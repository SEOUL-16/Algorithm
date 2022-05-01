package firstweek;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_G4_1987_조민수 {
	static char[][] map;
	static boolean[][] visited;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int row = Integer.parseInt(st.nextToken());
		int col = Integer.parseInt(st.nextToken());
		map = new char[row][col];
		visited = new boolean[row][col];
		for (int i = 0; i < row; i++) {
			map[i] = br.readLine().toCharArray();
		}
		dfs(0, 0, 0,1);
		System.out.println(answer);
	}

	private static void dfs(int row, int col, int flag, int ans) {
		visited[row][col] = true;
		flag |= 1 << map[row][col] - 'A';
		answer = Math.max(ans, answer);
		for (int i = 0; i < 4; i++) {
			int nr = row + dr[i];
			int nc = col + dc[i];
			if (-1 < nr && nr < map.length && -1 < nc && nc < map[nr].length && !visited[nr][nc]
					&& (flag & (1 << map[nr][nc] - 'A')) == 0) {
				dfs(nr,nc,flag, ans + 1);
			}
		}
		visited[row][col] = false;
	}
}
