import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());

		int[] map = new int[N];
		for (int i = 0; i < N; i++) {
			map[i] = Integer.parseInt(br.readLine());
		}

//		0: 연속X / 1: 연속 1잔 / 2: 연속2잔
		int[][] dp = new int[N][3];
		dp[0][1] = map[0];
		for (int i = 1; i < N; i++) {
			int temp = Math.max(dp[i - 1][0], dp[i - 1][1]);
			dp[i][0] = Math.max(temp, dp[i - 1][2]);
			dp[i][1] = map[i] + dp[i - 1][0];
			dp[i][2] = map[i] + dp[i - 1][1];
		}
		int temp = Math.max(dp[N - 1][0], dp[N - 1][1]);
		bw.write(String.valueOf(Math.max(temp, dp[N - 1][2])));
		bw.flush();
		bw.close();
	}

}