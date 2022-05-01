import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int R;
	static int C;
	static int K;
	static boolean[][] sticker;
	static boolean[][] map;
	static int row;
	static int column;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new boolean[40][40];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			row = Integer.parseInt(st.nextToken());
			column = Integer.parseInt(st.nextToken());
			sticker = new boolean[row][column];
			for (int j = 0; j < row; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < column; k++) {
					if (Integer.parseInt(st.nextToken()) == 1)
						sticker[j][k] = true;
				}
			}

			put(0);
		}

		int count = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j])
					count++;
			}
		}

		bw.write(String.valueOf(count));
		bw.flush();
		bw.close();
	}

	public static void put(int cnt) {
		if (cnt == 4) {
			return;
		}

		if (cnt > 0) {
			// 90도 회전
			boolean[][] temp = new boolean[column][row];
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < column; j++) {
					temp[j][row - i - 1] = sticker[i][j];
				}
			}
			sticker = temp;
			int t = row;
			row = column;
			column = t;

		}

		boolean b = true;

		loop1: for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				b = true;
				loop2: for (int k = 0; k < row; k++) {
					for (int l = 0; l < column; l++) {
						if (i + k >= R || j + l >= C || (sticker[k][l] && map[i + k][j + l])) {
							b = false;
							break loop2;
						}

					}
				}
				if (b) {
					// 스티커 붙이기
					for (int k = 0; k < row; k++) {
						for (int l = 0; l < column; l++) {
							if (sticker[k][l]) {
								map[i + k][j + l] = true;
							}
						}
					}
					break loop1;
				}
			}
		}

		if (b) {
			return;
		} else {
			put(cnt + 1);
		}
	}
}