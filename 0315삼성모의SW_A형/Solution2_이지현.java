package pro;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

class point {
	int i;
	int j;

	public point(int i, int j) {
		super();
		this.i = i;
		this.j = j;
	}
}

// 사냥 문제 (100점)
class Solution2 {
	static int N, NumOfPoints=0, min_time = Integer.MAX_VALUE;
	static int[][] map;
	static boolean isvisited[];
	static LinkedList<point> points;
	static int[] route;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			// 맵 정보 받아오기
			NumOfPoints=0;
			min_time = Integer.MAX_VALUE;
			points = new LinkedList<>();
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				String[] strs = br.readLine().split(" ");
				for (int j = 0; j < N; j++) {
					int temp = Integer.parseInt(strs[j]);
					map[i][j] = temp;
					if(temp != 0) {
						points.add(new point(i, j));
						NumOfPoints++;
					}
				}
			}

			// 순열 만들기
			isvisited = new boolean[NumOfPoints];
			route = new int[NumOfPoints];
			permutation(0);
			
			// 출력
			System.out.printf("#%d %d\n", t, min_time);
		}
	}

	private static void permutation(int cnt) {
		if(cnt == NumOfPoints) {
			boolean ok = true;
			// 만든 순열에 대해
			// 유효한 루트인지 검사 + 거리 계산
			boolean[] monster = new boolean[5]; // 1~4
			int time = 0, cur_i = 0, cur_j = 0;
			for (int i = 0; i < NumOfPoints; i++) {
				int next_i = points.get(route[i]).i;
				int next_j = points.get(route[i]).j;
				time += (Math.abs(next_i-cur_i)+Math.abs(next_j-cur_j));
				if(map[next_i][next_j] > 0) { // 몬스터
					monster[map[next_i][next_j]] = true;
				}
				else { // 의뢰인
					if(monster[-map[next_i][next_j]] == false)
						ok = false;
				}
				cur_i = next_i;
				cur_j = next_j;
			}
			
			// 유효한 루트면서 시간이 적게 걸리면 갱신
			if(ok) {
				if(min_time > time) {
					min_time = time;
				}
			}
		}
		
		for (int i = 0; i < NumOfPoints; i++) {
			if(!isvisited[i]) {
				route[cnt] = i;
				isvisited[i] = true;
				permutation(cnt+1);
				isvisited[i] = false;
			}
		}
	}
}
/*
2
5
0 0 -3 0 0
0 0 0 3 0
0 0 0 0 2
0 0 1 0 0
-1 0 0 -2 0
3
0 0 0
0 1 -1
0 0 0

1
3
0 0 0
0 1 -1
0 0 0

 */
