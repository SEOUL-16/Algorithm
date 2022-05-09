import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static boolean[][] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		arr = new boolean[4][8];
		for (int i = 0; i < 4; i++) {
			String temp = br.readLine();
			for (int j = 0; j < 8; j++) {
				if (temp.charAt(j) == '1') {
					arr[i][j] = true;
				}
			}
		}

		N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			boolean[] visited = new boolean[4];
			Queue<int[]> queue = new LinkedList<>();
			StringTokenizer st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken()) - 1;
			int direct = Integer.parseInt(st.nextToken());
			queue.add(new int[] { num, direct });
			visited[num] = true;
			while (!queue.isEmpty()) {
				int[] temp = queue.poll();
				if (temp[0] - 1 >= 0 && !visited[temp[0] - 1] && (arr[temp[0] - 1][2] != arr[temp[0]][6])) {
					visited[temp[0] - 1] = true;
					queue.add(new int[] { temp[0] - 1, temp[1] == 1 ? -1 : 1 });
				}
				if (temp[0] + 1 < 4 && !visited[temp[0] + 1] && (arr[temp[0] + 1][6] != arr[temp[0]][2])) {
					visited[temp[0] + 1] = true;
					queue.add(new int[] { temp[0] + 1, temp[1] == 1 ? -1 : 1 });
				}
				rotate(temp[0], temp[1]);
			}

		}

		int sum = 0;
		for (int i = 0; i < 4; i++) {
			if (arr[i][0]) {
				sum += Math.pow(2, i);
			}
		}

		bw.write(String.valueOf(sum));
		bw.flush();
		bw.close();

	}

	public static void rotate(int num, int direct) {
		if (direct == -1) {
			boolean temp = arr[num][0];
			for (int i = 0; i < 7; i++) {
				arr[num][i] = arr[num][i + 1];
			}
			arr[num][7] = temp;
		} else {
			boolean temp = arr[num][7];
			for (int i = 6; i >= 0; i--) {
				arr[num][i + 1] = arr[num][i];
			}
			arr[num][0] = temp;
		}
	}

}