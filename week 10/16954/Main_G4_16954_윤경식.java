import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	static char[][] map;
	static int[] dr = { -1, 1, 0, 0, -1, -1, 1, 1 };
	static int[] dc = { 0, 0, -1, 1, -1, 1, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		map = new char[8][8];

		Queue<int[]> queue = new LinkedList<>();

		for (int i = 0; i < 8; i++) {
			String str = br.readLine();
			for (int j = 0; j < 8; j++) {
				map[i][j] = str.charAt(j);
			}
		}

		queue.add(new int[] { 7, 0 });
		map[7][0] = '*';

		boolean depart = false;

		loop: while (true) {
			while (!queue.isEmpty()) {
				int[] temp = queue.poll();

				for (int i = 0; i < 8; i++) {
					int nr = temp[0] + dr[i];
					int nc = temp[1] + dc[i];
					if (nr >= 0 && nr < 8 && nc >= 0 && nc < 8 && map[nr][nc] == '.') {
						map[nr][nc] = '*';
					}
				}
			}

			for (int i = 7; i >= 0; i--) {
				for (int j = 0; j < 8; j++) {
					if (map[i][j] == '#') {
						if (i == 7) {
							map[i][j] = '.';
						} else {
							map[i][j] = '.';
							map[i + 1][j] = '#';
						}
					}
				}
			}

//			별 최고
			int min1 = 8;
//			벽 최고
			int min2 = 8;

			for (int i = 7; i >= 0; i--) {
				for (int j = 0; j < 8; j++) {
					if (map[i][j] == '*') {
						queue.add(new int[] { i, j });
						if (min1 > i) {
							min1 = i;
						}
					}
					if (map[i][j] == '#' && min2 > i) {
						min2 = i;
					}
				}
			}
			
			if (queue.isEmpty()) {
				break loop;
			}

			if (min1 <= min2) {
				depart = true;
				break loop;
			}
		}

		if (depart) {
			bw.write(String.valueOf(1));
		} else {
			bw.write(String.valueOf(0));
		}

		bw.flush();
		bw.close();

	}

}