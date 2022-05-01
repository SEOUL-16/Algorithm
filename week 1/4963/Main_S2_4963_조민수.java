package firstweek;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_S2_4963_조민수 {
	static boolean[][] map;
	static boolean[][] visited;
	static int ans;
	static int [] dr = {-1,1,0,0,-1,1,-1,1};
	static int [] dc = {0,0,-1,1,-1,-1,1,1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			ans = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			int col = Integer.parseInt(st.nextToken());
			int row = Integer.parseInt(st.nextToken());
			if (row == 0 && col == 0)
				break;
			map = new boolean[row][col];
			visited = new boolean[row][col];
			for (int i = 0; i < row; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < col; j++) {
					map[i][j] = st.nextToken().equals("1")  ? true : false;
				}
			}
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					if (!visited[i][j] && map[i][j]) {
						bfs(i,j);
						ans++;
					}
				}
			}
			System.out.println(ans);
		}
	}

	private static void bfs(int row, int col) {
		Queue<int[]> q = new LinkedList<int[]>();
		q.add(new int[] {row, col});
		visited[row][col] = true;
		while (!q.isEmpty()) {
			int cr = q.peek()[0];
			int cc = q.peek()[1];
			q.poll();
			for (int i = 0; i < 8; i++) {
				int nr = cr + dr[i];
				int nc = cc + dc[i];
				if (-1 < nr && nr < map.length && -1 < nc && nc < map[nr].length && !visited[nr][nc] && map[nr][nc]) {
					q.add(new int[] {nr,nc});
					visited[nr][nc] = true;
				}
			}
		}
	}

}
