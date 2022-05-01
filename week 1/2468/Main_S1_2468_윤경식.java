import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int water_height;
	static int Max;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		Max = Integer.MIN_VALUE;
//		수면 높이를 증가시키면서 Max값 갱신
		water_height = 0;
		while (true) {
//			영역 방문 체크
			visited = new boolean[N][N];
			int count = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
//					수면보다 높고 방문하지 않았다면 영역 dfs 탐색
					if (map[i][j] > water_height && !visited[i][j]) {
						dfs(i, j);
						count++;
					}
				}
			}
//			최대 영역 개수보다 많았다면 갱신
			if (count > Max) {
				Max = count;
			}
//			안전 영역이 하나도 없다면 모든 수면 높이에 대한 체크를 완료
			if (count == 0)
				break;
			water_height++;
		}

		bw.write(String.valueOf(Max));
		bw.flush();
		bw.close();

	}

	public static void dfs(int row, int column) {
		visited[row][column] = true;
		for (int i = 0; i < 4; i++) {
			int nr = row + dr[i];
			int nc = column + dc[i];
			if (nr >= 0 && nr < N && nc >= 0 && nc < N && map[nr][nc] > water_height && !visited[nr][nc]) {
				dfs(nr, nc);
			}
		}
	}
}
