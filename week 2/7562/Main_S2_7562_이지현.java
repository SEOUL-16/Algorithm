import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// https://www.acmicpc.net/problem/7562
// 나이트의 이동
public class Main {
	static int N, dest_x, dest_y;
	static boolean[][] isVisited;
	static int[] dy = { -2, -2, -1, 1, 2, 2, 1, -1 }; // 시계 방향
	static int[] dx = { -1, 1, 2, 2, 1, -1, -2, -2 }; // 시계 방향

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] strs = br.readLine().split(" ");
		int T = Integer.parseInt(strs[0]);

		for (int t = 1; t <= T; t++) {
			// 입력
			strs = br.readLine().split(" ");
			N = Integer.parseInt(strs[0]);
			isVisited = new boolean[N][N];
			
			int x, y;
			strs = br.readLine().split(" ");
			x = Integer.parseInt(strs[0]);
			y = Integer.parseInt(strs[1]);
			
			strs = br.readLine().split(" ");
			dest_x = Integer.parseInt(strs[0]);
			dest_y = Integer.parseInt(strs[1]);
			
			// 현재 위치부터 BFS로 탐색
			int min_count = bfs(new int[]{x, y, 0});
			
			// 도착하면 리턴
			System.out.println(min_count);
		}
	}

	private static int bfs(int[] cur) {
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] { cur[0], cur[1], cur[2] });
		isVisited[cur[0]][cur[1]] = true;

		while (!queue.isEmpty()) {
			cur = queue.poll();
			if(cur[0]==dest_x&&cur[1]==dest_y)
				return cur[2];

			int[] temp;
			for (int i = 0; i < 8; i++) { // 여덟 가지 경우 탐색
				temp = new int[] { cur[0] + dx[i], cur[1] + dy[i], cur[2]+1 };

				if (cango(temp) && !isVisited[temp[0]][temp[1]]) {
					queue.offer(temp);
					isVisited[temp[0]][temp[1]] = true;
				}
			}
		}
		return cur[2];
	}

	private static boolean cango(int[] temp) {
		if (temp[0] >= 0 && temp[0] < N && temp[1] >= 0 && temp[1] < N)
			return true;
		return false;
	}

}