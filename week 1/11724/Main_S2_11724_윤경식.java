import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int M;
	static boolean[][] map;
	static boolean[] visited;
	static int count = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

//		정점들의 연결 정보
		map = new boolean[N][N];
//		정점들의 방문 여부
		visited = new boolean[N];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			map[from - 1][to - 1] = true;
			map[to - 1][from - 1] = true;
		}

//		전체 정점 dfs탐색
		for (int i = 0; i < N; i++) {
//			탐색 시작 시 하나의 덩어리 count
			if (!visited[i]) {
				dfs(i);
				count++;
			}
		}

		bw.write(String.valueOf(count));
		bw.flush();
		bw.close();

	}

	public static void dfs(int index) {
		visited[index] = true;
		for (int i = 0; i < N; i++) {
			if (map[index][i] && !visited[i]) {
				dfs(i);
			}
		}
	}

}
