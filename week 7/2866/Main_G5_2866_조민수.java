package 백준_7주차_알고리즘스터디;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_G5_2866_조민수 {
	static int row, col;
	static char[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		map = new char[row][col];
		for (int i = 0; i < row; i++) {
			map[i] = br.readLine().toCharArray();
		}
		int max = Integer.MIN_VALUE;
		int cnt;
		for (int c = 0; c < col; c++) {
			for (int cc = c + 1; cc < col; cc++) {
				cnt = 0;
				if (map[row - 1][c] != map[row - 1][cc]) continue;
				for (int r = row - 1; r > -1; r--) {
					if (map[r][c] != map[r][cc]) break;
					cnt++;
				}
				max = Math.max(max, cnt);
			}
		}
		if (max == Integer.MIN_VALUE) System.out.println(row - 1);
		else System.out.println(row - max  -1);
	}
}
/*

5 8
acdefghb
hhhhhhhh
cdcfkttc
bbadbbbb
abacbada

*/