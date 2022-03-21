import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int exp;
	static int count;
	static int currentSize;
	static int currentRow = -1;
	static int currentColumn = -1;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int[][] map;

	static class Edge implements Comparable<Edge> {
		int row;
		int column;
		int length;

		public Edge(int row, int column, int length) {
			this.row = row;
			this.column = column;
			this.length = length;
		}

		public int compareTo(Edge o) {
			if (this.length > o.length)
				return 1;
			else if (this.length < o.length)
				return -1;
			else {
				if (this.row > o.row)
					return 1;
				else if (this.row < o.row)
					return -1;
				else {
					if (this.column > o.column)
						return 1;
					else
						return -1;
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int temp = Integer.parseInt(st.nextToken());
				map[i][j] = temp;
				if (temp == 9) {
					currentRow = i;
					currentColumn = j;
					map[i][j] = 0;
				}
			}
		}
//		입력 끝
		count = 0;
		exp = 0;
		currentSize = 2;

		while (true) {
			LinkedList<int[]> list = new LinkedList<>();
			list.offer(new int[] { currentRow, currentColumn, 0 });
			boolean stop = false;
			PriorityQueue<Edge> queue = new PriorityQueue<>();
			boolean[][] visited = new boolean[N][N];
			visited[currentRow][currentColumn] = true;
			while (!list.isEmpty()) {
				int[] temp = list.poll();
				if (map[temp[0]][temp[1]] != 0 && map[temp[0]][temp[1]] < currentSize) {
					stop = true;
					queue.add(new Edge(temp[0], temp[1], temp[2]));
				}
				if (!stop) {
					for (int i = 0; i < 4; i++) {
						int nr = temp[0] + dr[i];
						int nc = temp[1] + dc[i];
						if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc] && map[nr][nc] <= currentSize) {
							visited[nr][nc] = true;
							list.offer(new int[] { nr, nc, temp[2] + 1 });
						}
					}
				}
			}
			if (queue.size() == 0)
				break;
			else {
				Edge temp = queue.poll();
				currentRow = temp.row;
				currentColumn = temp.column;
				exp++;
				if (exp == currentSize) {
					currentSize++;
					exp = 0;
				}
				map[currentRow][currentColumn] = 0;
				count += temp.length;
			}

		}

		bw.write(String.valueOf(count));

		bw.flush();
		bw.close();
	}

}