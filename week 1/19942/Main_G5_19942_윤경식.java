import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static BufferedWriter bw;
	static int N;
	static int[] benchmark;
	static int[][] ingredients;
	static boolean[] choice;
	static int Min = Integer.MAX_VALUE;
	static boolean[] pick;
	static boolean is = false;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		benchmark = new int[4];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			benchmark[i] = Integer.parseInt(st.nextToken());
		}
		ingredients = new int[N][5];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 5; j++) {
				ingredients[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		choice = new boolean[N];
		subset(0, 0);
		if (pick == null) {
			bw.write(String.valueOf(-1));
		} else {
			bw.write(String.valueOf(Min) + "\n");
            // 사전 순 출력을 위해 정답의 뒷 부분 가공!!
			for (int i = 0; i < N; i++) {
				if (pick[i]) {
					if (benchmark[0] > 0 || benchmark[1] > 0 || benchmark[2] > 0 || benchmark[3] > 0) {
						bw.write(String.valueOf(i+1)+" ");
						for(int j=0;j<4;j++) {
							benchmark[j] -= ingredients[i][j];
						}
					}
				}
			}
		}
		bw.flush();
		bw.close();

	}

    // 부분 집합으로 전체 경우의 수 탐색
	public static void subset(int cnt, int cost) throws Exception {
		if (cnt == N) {
			for (int i = 0; i < 4; i++) {
				int temp = 0;
				for (int j = 0; j < N; j++) {
					if (choice[j])
						temp += ingredients[j][i];
				}
				if (benchmark[i] > temp)
					return;
			}
			if (cost < Min) {
				Min = cost;
				pick = choice.clone();
			}
			return;
		}

		choice[cnt] = true;
		if (Min > cost + ingredients[cnt][4])
			subset(cnt + 1, cost + ingredients[cnt][4]);
		choice[cnt] = false;
		if (Min > cost)
			subset(cnt + 1, cost);
	}

}