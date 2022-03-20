package firstweek;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_G5_19942_조민수 {
	static class Gradient {
		int dan, ji, tan, vi, cost;

		public Gradient(int dan, int ji, int tan, int vi, int cost) {
			super();
			this.dan = dan;
			this.ji = ji;
			this.tan = tan;
			this.vi = vi;
			this.cost = cost;
		}

	}

	// input데이터 담을 배열
	static Gradient[] arr;
	static int N;
	static int min;
	static boolean isChecked[];
	static int minNeed[];
	static boolean[] res;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new Gradient[N + 1];
		isChecked = new boolean[N + 1];
		minNeed = new int[4];
		min = Integer.MAX_VALUE;
		res = new boolean[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			minNeed[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i] = new Gradient(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()));
		}
		s(1);
		if (min == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(min);
			for (int i = 1; i <= N; i++) {
				if (res[i]) {
					if (arr[i].dan > 0 || arr[i].ji > 0 || arr[i].tan > 0 || arr[i].vi > 0)
					System.out.print(i +" ");
				}
			}
		}

	}

	private static void s(int pos) {
		if (pos == N + 1) {
			int toDan = 0, toTan = 0, toJi = 0, toVi = 0, toCost = 0;
			for (int i = 1; i <= N; i++) {
				if (!isChecked[i])
					continue;
				toDan += arr[i].dan;
				toTan += arr[i].tan;
				toJi += arr[i].ji;
				toVi += arr[i].vi;
				toCost += arr[i].cost;
			}
			if (toDan >= minNeed[0] && toJi >= minNeed[1] && toTan >= minNeed[2] && toVi >= minNeed[3]) {
				if (min > toCost) {
					Arrays.fill(res, false);
					min = toCost;
					for (int i = 1; i <= N; i++) {
						if (isChecked[i])
							res[i] = true;
					}
				}
			}
			return;
		}
		isChecked[pos] = true;
		s(pos + 1);
		isChecked[pos] = false;
		s(pos + 1);

	}

}



//8
//80 80 80 80
//1 1 1 1 100
//50 50 50 50 4
//10 10 10 10 2
//15 15 15 15 3
//10 10 10 10 2
//15 15 15 15 3
//1 1 1 1 100
//10 10 10 10 2


//4
//30 30 30 30
//10 10 10 10 1
//10 10 10 10 1
//10 10 10 10 1
//10 10 10 10 1


//8
//30 30 30 30
//10 10 10 10 2
//5 5 5 5 1
//10 10 10 10 3
//10 10 10 10 2
//10 10 10 10 3
//5 5 5 5 1
//10 10 10 10 2
//10 10 10 10 2
