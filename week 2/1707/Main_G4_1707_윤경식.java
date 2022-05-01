import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

	static int K;
	static int V;
	static int E;
	static ArrayList<ArrayList<Integer>> list;
	static int[] setNumber;
	static boolean check;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = null;
		K = Integer.parseInt(br.readLine());
		for (int k = 0; k < K; k++) {
			check = true;
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			setNumber = new int[V];
			list = new ArrayList<>();
			for (int i = 0; i < V; i++) {
				list.add(new ArrayList<>());
			}
//			연결 정보 입력
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int n1 = Integer.parseInt(st.nextToken()) - 1;
				int n2 = Integer.parseInt(st.nextToken()) - 1;
				list.get(n1).add(n2);
				list.get(n2).add(n1);
			}
			for (int i = 0; i < V; i++) {
				if (setNumber[i] == 0) {
					bfs(i);
				}
			}
            // true면 이분 그래프, false면 이분 그래프X
			if (check) {
				bw.write("YES\n");
			} else
				bw.write("NO\n");
		}
		bw.flush();
		bw.close();
	}
	// 1,2값을 번갈아 할당하며 bfs 탐색; 탐색 도중 같은 값이 인접 노드에서 발견되면 check변수에 false 할당
	public static void bfs(int index) {
		LinkedList<Integer> queue = new LinkedList<>();
		queue.offer(index);
		setNumber[index] = 1;
		while (!queue.isEmpty()) {
			int temp = queue.poll();
			ArrayList<Integer> tlist = list.get(temp);
			for (int i = 0, end = tlist.size(); i < end; i++) {
				if (setNumber[tlist.get(i)] == setNumber[temp]) {
					check = false;
					return;
				}
				if (setNumber[tlist.get(i)] == 0) {
					setNumber[tlist.get(i)] = setNumber[temp] == 1 ? 2 : 1;
					queue.offer(tlist.get(i));
				}
			}
		}
		return;
	}

}