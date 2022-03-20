package numofisle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_S2_4963_김태하 {
	static int w, h, answer;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
	static int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		String input = "";
//		while ((input = br.readLine()) != null) {
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			
			if (w == 0 && h == 0) {
				return;
			}
			
			answer = 0;
			
			map = new int[h][w];
			visited = new boolean[h][w];
			
			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < w; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if (map[i][j] != 0 && !visited[i][j]) {
						dfs(i, j);
						answer++;
					}
				}
			}
			System.out.println(answer);
		}
	}

	private static void dfs(int r, int c) {
		visited[r][c] = true;
		
		for (int i = 0; i < 8; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if (nr >= 0 && nr < h && nc >= 0 && nc < w && !visited[nr][nc] && map[nr][nc] != 0) {
				dfs(nr, nc);
			}
		}
	}
}
