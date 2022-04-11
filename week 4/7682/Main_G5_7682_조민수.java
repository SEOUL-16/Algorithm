import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_G5_7682_조민수 {
	static char[][] map;
	static int dr[] = { 0, -1, -1, -1 };
	static int dc[] = { -1, -1, 0, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while (true) {
			String line = br.readLine();
			if (line.equals("end"))
				break;
			map = new char[3][3];
			int xCnt = 0, oCnt = 0;
			boolean isSpace = false;
			// map 설정 및 x와 o의 갯수 구하기 빈 공간 체크
			for (int r = 0; r < 3; r++) {
				for (int c = 0; c < 3; c++) {
					map[r][c] = line.charAt(r * 3 + c);
					if (map[r][c] == 'X')
						xCnt++;
					else if (map[r][c] == 'O')
						oCnt++;
					else
						isSpace = true;
				}
			}
			// X의 갯수와 O의 갯수 비교 x가 이기면 o의 갯수 + 1 o가 이기면 x의 갯수이므로
			if (!(xCnt == oCnt + 1 || xCnt == oCnt)) {
				sb.append("invalid").append("\n");
				continue;
			}
			// 게임이 끝났는데 빈 공간이 존재한다면 누군가는 반드시 이겨야 하므로
			boolean isXWin = isWin('X');
			boolean isOWin = isWin('O');
			if (isSpace) {
				// x가 이긴 경우
				if (xCnt == oCnt + 1) {
					if (isXWin && !isOWin)
						sb.append("valid").append("\n");
					else
						sb.append("invalid").append("\n");
				}
				// o가 이긴 경우
				else if (xCnt == oCnt) {
					if (isOWin && !isXWin)
						sb.append("valid").append("\n");
					else
						sb.append("invalid").append("\n");
				}
			} else {
				if ((isXWin && !isOWin) || (!isXWin && !isOWin))
					sb.append("valid").append("\n");
				else
					sb.append("invalid").append("\n");
			}
		}
		System.out.println(sb.toString());
	}

	private static boolean isWin(char c) {
		int cnt = 0;
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				if (map[row][col] == c) {
					for (int d = 0; d < 4; d++) {
						cnt = Math.max(getCnt(row, col, d), cnt);
					}
				}
			}
		}
		if (cnt == 3)
			return true;
		return false;
	}

	private static int getCnt(int row, int col, int d) {
		char cur = map[row][col];
		int res = 0;
		for (int size = 0; size < 3; size++) {
			int nr = row + dr[d] * size;
			int nc = col + dc[d] * size;
			if (-1 < nr && nr < 3 && -1 < nc && nc < 3 && map[nr][nc] == cur) {
				res++;
			}
		}
		return res;
	}

}
