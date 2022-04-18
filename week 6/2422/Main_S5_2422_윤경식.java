import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int M;
	static boolean[][] map;
	static int[] output = new int[3];
	static int count = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new boolean[N + 1][N + 1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int num1 = Integer.parseInt(st.nextToken());
			int num2 = Integer.parseInt(st.nextToken());
			map[num1][num2] = true;
			map[num2][num1] = true;
		}

		combination(0, 1);
		bw.write(String.valueOf(count));
		bw.flush();
		bw.close();

	}

	public static void combination(int cnt, int start) {
		if (cnt == 2) {
			if (map[output[0]][output[1]])
				return;
		}

		if (cnt == 3) {
			if (map[output[1]][output[2]] || map[output[0]][output[2]])
				return;
			count++;
			return;
		}

		for (int i = start; i <= N; i++) {
			output[cnt] = i;
			combination(cnt + 1, i + 1);
		}
	}
}