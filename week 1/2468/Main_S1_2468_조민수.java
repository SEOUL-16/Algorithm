package firstweek;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_S1_2468_조민수 {
	static int[][] map;
	static int N;
	static int ans;
	static boolean[][] visited;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		int temp = 0;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (min > map[i][j]) {
					min = map[i][j];
				}
				if (max < map[i][j]) {
					max = map[i][j];
				}
			}
		}
		for (int x = min; x <= max; x++) {
			temp = 0;
			setMap(x);
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j] && map[i][j] != -1) {
						bfs(i,j);
						temp++;
					}
				}
			}
			ans = Math.max(temp, ans);
			visited = new boolean[N][N];
		}
		if (ans == 0)
			ans = 1;
		System.out.println(ans);
	}

	private static void setMap(int x) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] <= x)
					map[i][j] = -1;
			}
		}
	}

	private static void bfs(int row, int col) {
		Queue<int[]> q = new LinkedList<int[]>();
		visited[row][col] = true;
		q.add(new int[] { row, col });
		while (!q.isEmpty()) {
			int cr = q.peek()[0];
			int cc = q.peek()[1];
			q.poll();
			for (int i = 0; i < 4; i++) {
				int nr = cr + dr[i];
				int nc = cc + dc[i];
				if (-1 < nr && nr < N && -1 < nc && nc < N && !visited[nr][nc] && map[nr][nc] != -1) {
					q.add(new int[] {nr,nc});
					visited[nr][nc] = true;
				}
			}

		}
	}

}
