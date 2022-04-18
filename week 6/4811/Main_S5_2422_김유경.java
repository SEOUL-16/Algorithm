import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static List<Integer>[] list;
	static int[] selected;
	static int result;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		selected = new int[3];
		list = new LinkedList[N + 1];

		for (int i = 1; i < N + 1; i++) {
			list[i] = new LinkedList<Integer>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			list[Integer.parseInt(st.nextToken())].add(Integer.parseInt(st.nextToken()));
		}

		result=0;
		Combination(1, 0);
		System.out.println(result);

	}

	private static void Combination(int start, int cnt) {
		if (cnt == 3) {
			for (int i = 0; i < selected.length; i++) {					//각 자리의 숫자에 대해서 
				for (Integer num : list[selected[i]]) {					//그 숫자에 해당하는 리스트에 가서
					if (i == 0) {
						if (selected[1] == num || selected[2] == num)
							return;
					} else if (i == 1) {
						if (selected[0] == num || selected[2] == num)
							return;
					} else {
						if (selected[0] == num || selected[1] == num)
							return;
					}
				}
			}
			//모든 자리를 검사했는데 섞어먹으면 안되는 조합이 아니라 여기까지 왔으므로
			result++;
			return;
		}

		for (int i = start; i < N + 1; i++) {
			selected[cnt] = i;
			Combination(i + 1, cnt + 1);

		}

	}

}
