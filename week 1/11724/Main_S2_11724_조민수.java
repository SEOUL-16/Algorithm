package firstweek;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_S2_11724_조민수 {
	static boolean map[][];
	static boolean visited[];
	static int ans;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		map = new boolean[N + 1][N + 1];
		visited = new boolean[N + 1];
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(st.nextToken());
			int col = Integer.parseInt(st.nextToken());
			map[row][col] = true;
			map[col][row] = true;
		}
		for (int i = 1; i <= N; i++) {
			if (!visited[i]) {
				bfs(i);
				ans++;
			}
		}
		System.out.println(ans);
	}
	private static void bfs(int start) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(start);
		visited[start] = true;
		while (!q.isEmpty()) {
			int next = q.poll();
			for (int i = 1; i < map.length; i++) {
				if (map[next][i] && !visited[i]) {
					q.add(i);
					visited[i] = true;
				} 
			}
		}
	}

}
