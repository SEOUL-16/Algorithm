package pro;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

// 낚시터 입장 문제 (80점)
class Solution1 {
	static int min_distance;
	static int[][] group;
	static boolean[] field;
	static int N;
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			min_distance = Integer.MAX_VALUE;
			N = Integer.parseInt(br.readLine());
			field = new boolean[N];
			group = new int[3][2];
			
			for (int i = 0; i < 3; i++) {
				String[] strs = br.readLine().split(" ");
				group[i][0] = Integer.parseInt(strs[0])-1;
				group[i][1] = Integer.parseInt(strs[1]);
			}
			
			for (int i = 0; i < 3; i++) {
				Arrays.fill(field, false);
				dfs(0, 0, 1<<0, field, 0);
				
				Arrays.fill(field, false);
				dfs(1, 0, 1<<1, field, 0);
				
				Arrays.fill(field, false);
				dfs(2, 0, 1<<2, field, 0);
			}
			
			System.out.printf("#%d %d\n", t, min_distance);
		}
	}

	private static void dfs(int i_group, int i_person, int flag_groupUsed, boolean[] field, int distance) {
		// 백 트래킹
		if(distance >= min_distance)
			return;
		
		// 이 그룹 다 배치됐다면
		if(i_person == group[i_group][1]) {
			// 배치할 그룹 이제 없으면 min 갱신
			if(flag_groupUsed == 7) {
				min_distance = Integer.min(min_distance, distance);
				return;
			}

			// 사용 안된 그룹
			for (int i = 0; i < 3; i++) {
				if((flag_groupUsed & 1<<i) == 0) {
					dfs(i, 0, flag_groupUsed | (1<<i), field, distance);
				}
			}
		}
		
		// 인간 인덱스 배치 후 인자 갱신해서 dfs 호출
		int put;
		int cur_distance = 1;
		int search = 0;
		boolean isin = false;
		while(true) {
			if(search >= N) // 원인불명의 무한루프 해결 불가, 임시방편
				break;
			
			put = group[i_group][0] - search;
			if(put >= 0 && put < N && field[put] == false) {
				field[put] = true;
				dfs(i_group, i_person+1, flag_groupUsed, field, distance + cur_distance);
				field[put] = false;
				isin = true;
			}
			if(search!=0) {
				put = group[i_group][0] + search;
				if(put >= 0 && put < N && field[put] == false) {
					field[put] = true;
					dfs(i_group, i_person+1, flag_groupUsed, field, distance + cur_distance);
					field[put] = false;
					isin = true;
				}
			}
			
			if(isin == true)
				break;
			
			cur_distance++;
			search++;
		}
	}
}

/*
1
10
4 5
6 2
10 2

*/