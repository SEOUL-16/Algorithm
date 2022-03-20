package diameteroftree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_G4_1967_김태하 {
	// 각각의 노드(start)로부터 트리를 dfs로 순회한다.
	// 지나온 간선의 가중치를 더하고 현재까지의 weight와 기존의 maxWeight 중 큰 것을 maxWeight에 대입한다.
	// 만들어진 weightmap에서 최대값을 선택한다.
	
	static int maxWeight, N;
	static boolean[] visited;
	static ArrayList<ArrayList<int[]>> adList = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		visited = new boolean[N];
		
		for (int i = 0; i < N; i++) {
			adList.add(new ArrayList<int[]>());
		}
		
		
		for (int i = 0; i < N - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int weight = Integer.parseInt(st.nextToken());
			
			adList.get(from).add(new int[] {to, weight});
			adList.get(to).add(new int[] {from, weight});
		}
		
		for (int i = 0; i < N; i++) {
			Arrays.fill(visited, false);
//			System.out.println("Started from " + i);
			dfs(i, 0);
		}
		
		System.out.println(maxWeight);
	}

	private static void dfs(int current, int sumWeight) {
		visited[current] = true;
		maxWeight = Math.max(sumWeight, maxWeight);
//		System.out.println("Visited : " + current + " sumWeight : " + sumWeight + " maxWeight : " + maxWeight);
		
		for (int[] adInfo : adList.get(current)) {
			if (!visited[adInfo[0]]) {
				dfs(adInfo[0], sumWeight + adInfo[1]);
			}
		}
	}
}
