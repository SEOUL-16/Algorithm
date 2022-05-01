import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

	static BufferedWriter bw;
	static int I;
	static int[] start;
	static int[] end;
	static int count;
	static int[] d_row = { -1, -2, -2, -1, 1, 2, 2, 1 };
	static int[] d_column = { -2, -1, 1, 2, 2, 1, -1, -2 };
	static boolean[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			I = Integer.parseInt(br.readLine());
			map = new boolean[I][I];
			st = new StringTokenizer(br.readLine());
			start = new int[] { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0 };
			st = new StringTokenizer(br.readLine());
			end = new int[] { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) };
			count = 0;
			LinkedList<int[]> list = new LinkedList<>();
			list.offer(start);
			map[start[0]][start[1]] = true;
			while (!list.isEmpty()) {
				int[] temp = list.poll();
				if (temp[0] == end[0] && temp[1] == end[1]) {
					bw.write(String.valueOf(temp[2]) + "\n");
					break;
				}
				int nr, nc;
				for (int i = 0; i < 8; i++) {
					nr = temp[0] + d_row[i];
					nc = temp[1] + d_column[i];
					if (nr >= 0 && nr < I && nc >= 0 && nc < I && !map[nr][nc]) {
						list.offer(new int[] { nr, nc, temp[2] + 1 });
						map[nr][nc] = true;
					}
				}
			}
		}
		bw.flush();
		bw.close();
	}
}