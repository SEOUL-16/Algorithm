package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_G4_3055_김태하 {
	static int R, C;
	static char[][] map;
	static int[][] waterMap, moveMap;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int[] locD = new int[2];
	static int[] locS = new int[2];
	static Queue<int[]> waterStart = new LinkedList<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		waterMap = new int[R][C];
		moveMap = new int[R][C];
		
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				waterMap[i][j] = Integer.MAX_VALUE;
				
				if (map[i][j] == 'D') {
					locD[0] = i;
					locD[1] = j;
				} else if (map[i][j] == 'S') {
					locS[0] = i;
					locS[1] = j;
				} else if (map[i][j] == '*') {
					waterStart.add(new int[] {i, j, 0});
					waterMap[i][j] = 0;
				}
			}
		}
		
		map[locS[0]][locS[1]] = '.';
		
		water();
		
		waterMap[locD[0]][locD[1]] = Integer.MAX_VALUE;
		
		move();
		
		if (moveMap[locD[0]][locD[1]] != 0 && moveMap[locD[0]][locD[1]] != Integer.MAX_VALUE) {
			System.out.println(moveMap[locD[0]][locD[1]]);
		} else {
			System.out.println("KAKTUS");
		}
	}

	private static void water() {
		
		while (!waterStart.isEmpty()) {
			int[] current = waterStart.poll();
			
			for (int i = 0; i < 4; i++) {
				int nr = current[0] + dr[i];
				int nc = current[1] + dc[i];
				
				if (nr >= 0 && nr < R && nc >= 0 && nc < C && map[nr][nc] == '.' && (waterMap[nr][nc] == 0 || waterMap[nr][nc] == Integer.MAX_VALUE)) {
					waterMap[nr][nc] = current[2] + 1;
					waterStart.offer(new int[] {nr, nc, current[2] + 1});
				}
			}
		}
	}

	private static void move() {
		Queue<int[]> queue = new LinkedList<>();
		
		queue.offer(new int[] {locS[0], locS[1], 0});
		
		while (!queue.isEmpty()) {
			int[] current = queue.poll();
			
			for (int i = 0; i < 4; i++) {
				int nr = current[0] + dr[i];
				int nc = current[1] + dc[i];
				
				if (nr >= 0 && nr < R && nc >= 0 && nc < C && waterMap[nr][nc] > current[2] + 1 && (map[nr][nc] =='.' || map[nr][nc] == 'D') && (moveMap[nr][nc] == 0 || moveMap[nr][nc] > current[2] + 1)) {
					moveMap[nr][nc] = current[2] + 1;
					queue.offer(new int[] {nr, nc, current[2] + 1});
				}
			}
		}
	}

}
