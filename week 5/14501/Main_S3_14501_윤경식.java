import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[][] map;
	static int[][] dp;
	static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		map = new int[N][2];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			map[i][0] = Integer.parseInt(st.nextToken());
			map[i][1] = Integer.parseInt(st.nextToken());
		}

		dp = new int[N][2];
//		0: 상담O / 1: 상담X 한 경우
		dfs(0, 0);

		bw.write(String.valueOf(max));
		bw.flush();
		bw.close();
	}

	public static void dfs(int num, int count) {
		if (num == N) {
			if (max < count)
				max = count;
			return;
		}

		if (num + 1 <= N) {
			dfs(num + 1, count);
		} else {
			if (max < count)
				max = count;
			return;
		}

		if (num + map[num][0] <= N) {
			dfs(num + map[num][0], count + map[num][1]);
		} else {
			if (max < count)
				max = count;
			return;
		}

	}

}