import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[] result;
	static boolean[] visited;
	static int[] output;
	static int[] answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		result = new int[N];
		for (int i = 0; i < N; i++) {
			result[i] = Integer.parseInt(st.nextToken());
		}

		output = new int[N];
		visited = new boolean[N];

		permutation(0);

		for (int i = 0; i < N; i++) {
			bw.write(String.valueOf(answer[i] + 1) + " ");
		}

		bw.flush();
		bw.close();
	}

	public static void permutation(int cnt) {
		if (cnt == N) {
			for (int i = 0; i < N; i++) {
				int count = 0;
				int j = 0;
				while (output[j] != i) {
					if (output[j] > i) {
						count++;
					}
					j++;
				}
				if (count != result[i]) {
					return;
				}
			}
			answer = output.clone();
			return;
		}
		for (int i = 0; i < N; i++) {
			if (visited[i])
				continue;
			visited[i] = true;
			output[cnt] = i;
			permutation(cnt + 1);
			visited[i] = false;
		}
	}
}