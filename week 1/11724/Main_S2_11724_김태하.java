package numofconnectedcomponents;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_S2_11724_김태하 {
	static int N, M, answer;
	static int[][] map;
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		answer = 0;
		
		map = new int[N][N];
		visited = new boolean[N];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			
			map[from][to] = 1;
			map[to][from] = 1;
		}
		//	모든 노드마다,
		//	 	시작 노드가 방문되었는지 확인한다.
		//		방문되지 않았으면,
		// 			dfs로 노드마다 방문하면서 방문 처리를 한다.
		//			연결 요소의 개수(answer)를 증가시킨다.
		
		for (int n = 0; n < N; n++) {
			if (!visited[n]) {
				dfs(n);
				answer++;
			}
		}
		
		System.out.println(answer);
	}

	private static void dfs(int current) {
		visited[current] = true;
		
		for (int ad = 0; ad < N; ad++) {
			if (!visited[ad] && map[current][ad] != 0) {
				dfs(ad);
			}
		}
	}
}
