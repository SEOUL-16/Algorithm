import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

	static int R;
	static int C;
	static int[] dest;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int answer = -1;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		dest = new int[2];
		LinkedList<int[]> queue = new LinkedList<>();
		char[][] map = new char[R][C];
		for (int i = 0; i < R; i++) {
			String temp = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = temp.charAt(j);
				if (map[i][j] == 'D') {
					dest[0] = i;
					dest[1] = j;
				}
			}
		}

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] == '*')
					queue.offer(new int[] { i, j, '*', 0 });
			}
		}

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] == 'S') {
					queue.offer(new int[] { i, j, 'S', 0 });
				}
			}
		}

		loop: while (!queue.isEmpty()) {
			int[] temp = queue.poll();
			for (int i = 0; i < 4; i++) {
				int nr = temp[0] + dr[i];
				int nc = temp[1] + dc[i];
				if (nr >= 0 && nr < R && nc >= 0 && nc < C && temp[2] == 'S' && map[nr][nc] == 'D') {
					answer = temp[3] + 1;
					break loop;
				} else if (nr >= 0 && nr < R && nc >= 0 && nc < C && temp[2] == 'S' && map[nr][nc] == '.') {
					queue.offer(new int[] { nr, nc, 'S', temp[3] + 1 });
					map[nr][nc] = 'S';
				} else if (nr >= 0 && nr < R && nc >= 0 && nc < C && temp[2] == '*' && map[nr][nc] != 'X'
						&& map[nr][nc] != 'D' && map[nr][nc] != '*') {
					queue.offer(new int[] { nr, nc, '*', 0 });
					map[nr][nc] = '*';
				}

			}
		}

		if (answer != -1)
			bw.write(String.valueOf(answer));
		else
			bw.write("KAKTUS");
		bw.flush();
		bw.close();
	}
}