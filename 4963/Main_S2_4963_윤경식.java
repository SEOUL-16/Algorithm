import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int w;
	static int h;
	static boolean[][] map;
	static int count;
	static int[] dr = { -1, 1, 0, 0, -1, -1, 1, 1 };
	static int[] dc = { 0, 0, -1, 1, -1, 1, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			if (w == 0 && h == 0)
				break;
			map = new boolean[h][w];
//			땅이면 true, 바다면 false로 입력받고 방문한 땅 false 처리
			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < w; j++) {
					if (Integer.parseInt(st.nextToken()) == 1) {
						map[i][j] = true;
					} else {
						map[i][j] = false;
					}
				}
			}
			count = 0;
//			땅이면 dfs 탐색
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if (map[i][j]) {
						dfs(i, j);
						count++;
					}
				}
			}
			bw.write(String.valueOf(count) + "\n");
		}
		bw.flush();
		bw.close();

	}

	public static void dfs(int row, int column) {
		map[row][column] = false;
//		8방향 탐색
		for (int i = 0; i < 8; i++) {
			int nr = row + dr[i];
			int nc = column + dc[i];
			if (nr >= 0 && nr < h && nc >= 0 && nc < w && map[nr][nc]) {
				dfs(nr, nc);
			}
		}
	}

}
