import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//https://lotuslee.tistory.com/131
//따로 저장하고 초기화하지 않으면 중간에서 의도치 않게 값을 덮어 써버리는 경우가 생김...

public class Main {

	static int N;
	static int S;
	static int M;
	static int[] map;
	static int[] input;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[M + 1];
		for (int i = 0; i <= M; i++) {
			map[i] = -1;
		}
		map[S] = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int temp = Integer.parseInt(st.nextToken());

			List<Integer> list = new ArrayList<>();

			for (int j = 0; j <= M; j++) {
				if (map[j] == i) { // i-1번째에서 가능한 볼륨들을 찾는다.
					int res1 = j + temp;
					int res2 = j - temp;

					if (res1 <= M)
						list.add(res1);
					if (0 <= res2)
						list.add(res2);
				}
			}

			for (int n : list) {
				map[n] = i + 1;
			}
		}

//		dfs(1, S);

		int max = -1;
		for (int i = 0; i <= M; i++) {
			if (map[i] == N) {
				max = Math.max(max, i);
			}
		}

		if (max == -1)
			bw.write(String.valueOf(-1));
		else
			bw.write(String.valueOf(max));

		bw.flush();
		bw.close();

	}

//	public static void dfs(int idx, int count) {
//		if (idx == N + 1) {
//			return;
//		}
//
//		if (count - input[idx] >= 0) {
//			if (map[count - input[idx]] < idx)
//				map[count - input[idx]] = idx;
//			dfs(idx + 1, count - input[idx]);
//		}
//		if (count + input[idx] <= M) {
//			if (map[count + input[idx]] < idx)
//				map[count + input[idx]] = idx;
//			dfs(idx + 1, count + input[idx]);
//		}
//	}
}