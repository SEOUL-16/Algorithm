import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		long[][] map = new long[30 + 1][30 + 1];
		for (int i = 0; i <= 30; i++) {
			map[0][i] = 1L;
		}
		for (int i = 1; i <= 30; i++) {
			for (int j = 0; j <= 29; j++) {
				if (j == 0)
					map[i][j] = map[i - 1][j + 1];
				else
					map[i][j] = map[i - 1][j + 1] + map[i][j - 1];
			}
		}
		while (true) {
			int N = Integer.parseInt(br.readLine());
			if (N == 0)
				break;

			bw.write(String.valueOf(map[N - 1][1]) + "\n");
		}
		bw.flush();
		bw.close();

	}

}