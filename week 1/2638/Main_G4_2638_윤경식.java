import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int M;
	static boolean[][] cheese;
	static boolean[][] visited;
	static int[][] count;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

//		치즈 존재 여부
		cheese = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				if (Integer.parseInt(st.nextToken()) == 1) {
					cheese[i][j] = true;
				}
			}
		}

//		There is Cheese
		boolean tiCheese = true;
		int timer = 0;
//		아직 치즈가 존재한다면
		while (tiCheese) {
			tiCheese = false;
			visited = new boolean[N][M];
			count = new int[N][M];
//			바깥쪽 dfs 탐색을 통해 녹아 없어질 치즈 선정
			dfs(0, 0);
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
//					녹아 없어진 치즈가 존재한다면 loop 반복
					if (count[i][j] > 1) {
						cheese[i][j] = false;
						tiCheese = true;
					}
				}
			}
			if (!tiCheese)
				break;
			timer++;
		}
		bw.write(String.valueOf(timer));

		bw.flush();
		bw.close();

	}

	public static void dfs(int row, int column) {
		visited[row][column] = true;
//		4방향 탐색
		for (int i = 0; i < 4; i++) {
			int nr = row + dr[i];
			int nc = column + dc[i];
//			바깥과 근접한 치즈 영역에 count++ (count가 2이상이면 녹아 없어질 예정)
			if (nr >= 0 && nr < N && nc >= 0 && nc < M && cheese[nr][nc]) {
				count[nr][nc] += 1;
			}
			if (nr >= 0 && nr < N && nc >= 0 && nc < M && !cheese[nr][nc] && !visited[nr][nc]) {
				dfs(nr, nc);
			}
		}
	}

}
