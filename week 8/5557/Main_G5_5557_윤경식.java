import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	public static class Node {
		long count;
		int num;

		public Node(long count, int num) {
			this.count = count;
			this.num = num;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());

		long[] dp = new long[21];
		ArrayList<Node> list = null;
		N--;
		dp[Integer.parseInt(st.nextToken())]++;
		for (int i = 2; i <= N; i++) {
			int tempNum = Integer.parseInt(st.nextToken());
			list = new ArrayList<>();
			for (int j = 0; j < 21; j++) {
				if (dp[j] > 0) {
					if (j - tempNum >= 0) {
						list.add(new Node(dp[j], j - tempNum));
					}
					if (j + tempNum <= 20) {
						list.add(new Node(dp[j], j + tempNum));
					}
				}
			}
			dp = new long[21];
			for (int j = 0, end = list.size(); j < end; j++) {
				dp[list.get(j).num] += list.get(j).count;
			}
		}
		bw.write(String.valueOf(dp[Integer.parseInt(st.nextToken())]));
		bw.flush();
		bw.close();

	}

}