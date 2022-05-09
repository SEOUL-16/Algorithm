import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	static int N;
	static char[][] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());

		arr = new char[N + 1][N + 1];
		print(N, N, N);
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				bw.write(String.valueOf(arr[i][j]));
			}
			bw.write("\n");
		}
		bw.flush();
		bw.close();

	}

	public static void print(int n, int row, int column) {
		if (n == 3) {
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (i == 1 && j == 1)
						arr[row - i][column - j] = ' ';
					else
						arr[row - i][column - j] = '*';
				}
			}
			return;
		}

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (i == 1 && j == 1) {
					int nr = row - n / 3;
					int nc = column - n / 3;
					for (int k = 0; k < n / 3; k++) {
						for (int l = 0; l < n / 3; l++) {
							arr[nr - k][nc - l] = ' ';
						}
					}
				} else
					print(n / 3, row - n / 3 * i, column - n / 3 * j);
			}
		}
	}

}