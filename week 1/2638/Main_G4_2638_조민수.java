package firstweek;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_G4_2638_조민수 {
	static boolean [][]isChange;
	static char [][]map;
	static boolean [][]visited;
	static int answer;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int row = Integer.parseInt(st.nextToken());
		int col = Integer.parseInt(st.nextToken());
		int cnt = 0;
		map = new char[row][col];
		visited = new boolean[row][col];
		isChange = new boolean[row][col];
		for (int i = 0; i < row; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < col; j++) {
				map[i][j] = st.nextToken().charAt(0);
				if (map[i][j] == '1') cnt++;
			}
		}
		go(cnt, 0);
		System.out.println(answer);
		
	}
	private static void go(int cnt, int day) {
		if (cnt == 0) {
			answer = day;
			return;
		}
		setAir();
		int newCnt = 0;
		for (int i = 0, end = map.length; i < end; i++) {
			for (int j = 0, endd = map[i].length; j < endd; j++) {
				if (map[i][j] == '0') continue;
				int air = 0;
				for (int d = 0; d < 4; d++) {
					int nr = i + dr[d];
					int nc = j + dc[d];
					if (-1 < nr && nr < end && -1 < nc && nc < endd && map[nr][nc] == '2') air++; 
				}
				if (air > 1) isChange[i][j] = true;
			}
		}
		for (int i = 0, end = map.length; i < end; i++) {
			for (int j = 0, endd = map[i].length; j < endd; j++) {
				map[i][j] = isChange[i][j] ? '0' : map[i][j];
				if (map[i][j] == '1') newCnt++;
			}
		}
		
		go(newCnt,day+1);
	}
	private static void setAir() {
		for (int i = 0 ,end = visited.length; i < end; i++) {
			Arrays.fill(visited[i],false);
		}
		Queue<int[]> q = new LinkedList<int[]>();
		q.add(new int[] {0,0});
		map[0][0] = '2';
		visited[0][0] = true;
		while (!q.isEmpty()) {
			int cr = q.peek()[0];
			int cc = q.peek()[1];
			q.poll();
			for (int d = 0; d < 4; d++) {
				int nr = cr +dr[d];
				int nc = cc +dc[d];
				if (-1 < nr && nr < map.length && -1 < nc && nc < map[cr].length && map[nr][nc] != '1' && !visited[nr][nc]) {
					q.add(new int [] {nr,nc});
					visited[nr][nc] = true;
					map[nr][nc] = '2';
					
				}
			}
		}
	}

}
/*
8 9
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
0 1 1 0 0 0 1 1 0
0 1 0 1 1 1 0 1 0
0 1 0 0 1 0 0 1 0
0 1 0 1 1 1 0 1 0
0 1 1 0 0 0 1 1 0
0 0 0 0 0 0 0 0 0

*/