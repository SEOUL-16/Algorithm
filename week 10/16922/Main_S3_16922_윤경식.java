import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	static int N;
	static int[] roma = { 1, 5, 10, 50 };
	static boolean[] output = new boolean[1000 + 1];
	static int answer = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());

		permutation(0, 0, 0);

		bw.write(String.valueOf(answer));

		bw.flush();
		bw.close();

	}

	public static void permutation(int count, int idx, int sum) {
		if (count == N) {
			if (!output[sum]) {
				output[sum] = true;
				answer++;
			}
			return;
		}

		for (int i = idx; i < 4; i++) {
			int num = sum + roma[i];
			permutation(count + 1, i, num);
		}
	}

}