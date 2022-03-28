package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_G4_1707_김태하 {
	// 그래프의 모양이 주어졌을 때 컴퓨터가 이분그래프인지 판별할 수 있게 만들기
	
	// dfs로 그룹 정보 정하기
	// bfs로 인접한 노드의 그룹 정보가 모두 현재 노드의 정보와 다른지 확인
	
	
	static int T, V, E;
	static boolean[] visited;
	static int[] groupInfo;
	static ArrayList<ArrayList<Integer>> adList;
	static String answer;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for (int tc = 0; tc < T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			visited = new boolean[V];
			groupInfo = new int[V];
			adList = new ArrayList<ArrayList<Integer>>();
			answer = "YES";
			
			for (int i = 0; i < V; i++) {
				adList.add(new ArrayList<Integer>());
			}
			
			for (int e = 0; e < E; e++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken()) - 1;
				int end = Integer.parseInt(st.nextToken()) - 1;
				
				adList.get(start).add(end);
				adList.get(end).add(start);
			}
			
			for (int v = 0; v < V; v++) {
				dfs(v, 1);
			}
			
//			System.out.println(Arrays.toString(groupInfo));
			
			
			for (int v = 0; v < V; v++) {
				if (answer == "YES") {
					bfs(v);
				}
			}
			
			System.out.println(answer);
		}
	}

	private static void bfs(int start) {
		Queue<int[]> queue = new LinkedList<int[]>();
		
		queue.offer(new int[] {start, groupInfo[start]});
		
		while (!queue.isEmpty()) {
			int[] current = queue.poll();
			
			visited[current[0]] = true;							// 일반 BFS처럼 for문에서 한 노드에 연결된 다른 노드들을 모두 방문처리할 경우 해당 노드와 연결된 다른 노드들을 평가할 기회가 아예 없어지므로
																// 방문 처리를 노드 중심이 아닌 간선 중심으로 생각해야 한다...
			
			for (int ad : adList.get(current[0])) {
				if (current[1] == groupInfo[ad]) {
					answer = "NO";
					return;
				} else {
					if (!visited[ad]) {
						queue.offer(new int[] {ad, groupInfo[ad]});
					}
				}
			}
		}
	}

	private static void dfs(int current, int group) {
		if (!visited[current]) {
			visited[current] = true;
			groupInfo[current] = group;
		}
		
		for (int ad : adList.get(current)) {
			if (!visited[ad]) {
				dfs(ad, group * -1);
			}
		}
	}
}
