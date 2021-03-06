import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int R;
	static int C;
	static char[][] map;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int count;
	static int Max;
	static int[][] why;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}
		why = new int[R][C];
		Max = Integer.MIN_VALUE;
		int flag = 0;
		bt(0, 0, flag | 1 << (int) map[0][0] - (int) 'A', 0);
		bw.write(String.valueOf(Max + 1));
		bw.flush();
		bw.close();

	}

	public static void bt(int r, int c, int flag, int count) {
		if (why[r][c] == flag) return;
		why[r][c] = flag;
		
		if (Max < count)
			Max = count;
		for (int i = 0; i < 4; i++) {
			int nr, nc;
			nr = r + dr[i];
			nc = c + dc[i];
			if (nr >= 0 && nr < R && nc >= 0 && nc < C && 0 == (flag & 1 << (int) map[nr][nc] - (int) 'A')) {
				bt(nr, nc, flag | 1 << (int) map[nr][nc] - (int) 'A', count + 1);
			}
		}
	}

}
