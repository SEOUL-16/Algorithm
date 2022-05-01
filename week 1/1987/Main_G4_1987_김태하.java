package alphabet;

import java.util.Scanner;

public class Main_G4_1987_김태하 {
	static int R, C, max_cnt;
	static char[][] map;
	static int[] dr = {-1, 1, 0, 0};	// 상하좌우
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		R = scanner.nextInt();
		C = scanner.nextInt();
		scanner.nextLine();
		map = new char[R][C];
		max_cnt = 0;
		
		for (int i = 0; i < R; i++) {
			map[i] = scanner.nextLine().toCharArray();
		}
		
		move(0, 0, 0, 0);
		
		System.out.println(max_cnt);
		scanner.close();
	}

	private static void move(int r, int c, int cnt, int check) {
		if ((check & 1 << map[r][c] - 'A') != 0 ) {
			max_cnt = Math.max(max_cnt, cnt);
			return;
		}
		
		check |= 1 << map[r][c] - 'A';
		
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if (nr >= 0 && nr < R && nc >= 0 && nc < C) {
				move(nr, nc, cnt + 1, check);
			}
		}
	}
}
