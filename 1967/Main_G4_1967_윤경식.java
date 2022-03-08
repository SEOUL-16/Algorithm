import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static boolean[] visited;
	static ArrayList<ArrayList<int[]>> list;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			list.add(new ArrayList<int[]>());
		}
//		노드들의 연결 정보 입력
		for (int i = 0; i < N - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			list.get(from - 1).add(new int[] { to - 1, value });
			list.get(to - 1).add(new int[] { from - 1, value });
		}

//		루트 시작 가장 멀리 있는 노드 탐색
		int[] fromMax = bfs(0);
//		가장 멀리 있는 노드 또 다시 시작 가장 멀리 노드 탐색
		int[] toMax = bfs(fromMax[0]);

		bw.write(String.valueOf(toMax[1]));
		bw.flush();
		bw.close();

	}

//	bfs 탐색을 통해 가장 멀리 있는 노드의 인덱스와 값을 반환
	public static int[] bfs(int start) {
		LinkedList<int[]> queue = new LinkedList<>();
		visited = new boolean[N];
		int[] result = new int[2];
		queue.offer(new int[] { start, 0 });
		visited[start] = true;
		while (!queue.isEmpty()) {
			int[] temp = queue.poll();
			if (temp[1] > result[1]) {
				result[1] = temp[1];
				result[0] = temp[0];
			}
			ArrayList<int[]> tlist = list.get(temp[0]);
			for (int i = 0, end = tlist.size(); i < end; i++) {
				if (!visited[tlist.get(i)[0]]) {
					queue.offer(new int[] { tlist.get(i)[0], tlist.get(i)[1] + temp[1] });
					visited[tlist.get(i)[0]] = true;
				}
			}
		}
		return result;
	}

}