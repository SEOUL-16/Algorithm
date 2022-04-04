import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_G5_17472_조민수 {
	static int R, C;
	static int map[][];
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static ArrayList<Edge> edges;
	static int parents[];

	static class Edge implements Comparable<Edge> {
		int from;
		int to;
		int val;

		public Edge(int from, int to, int val) {
			super();
			this.from = from;
			this.to = to;
			this.val = val;
		}

		@Override
		public int compareTo(Edge o) {
			return this.val - o.val;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		for (int row = 0; row < R; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < C; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		int num = setNum() - 1;
		edges = new ArrayList<>();
		setEdge(num);
		Collections.sort(edges);
		parents = new int[num];
		makeSet();
		int res = 0, cnt = 0;
		for (int i = 0, end = edges.size(); i < end; i++) {
			Edge e = edges.get(i);
			int from = e.from;
			int to = e.to;
			int val = e.val;
			if (union(from, to)) {
				res += e.val;
				cnt++;
			}
			if (cnt == num - 1)
				break;
		}
		if (cnt != num - 1)
			res = -1;
		System.out.println(res);
	}

	private static boolean union(int from, int to) {
		int rootA = find_parent(from);
		int rootB = find_parent(to);
		if (rootA == rootB)
			return false;
		parents[rootB] = rootA;
		return true;
	}

	private static int find_parent(int node) {
		if (node == parents[node])
			return parents[node];
		return parents[node] = find_parent(parents[node]);
	}

	private static void makeSet() {
		for (int i = 0, end = parents.length; i < end; i++) {
			parents[i] = i;
		}
	}

//	minR,maxR,minC,maxC
	private static void setEdge(int num) {
		for (int i = 0, end = num; i < end; i++) {
			for (int j = i + 1; j < end; j++) {
				int dist = getDist(i, j);
				if (dist >= 2 && dist != Integer.MAX_VALUE)
					edges.add(new Edge(i, j, dist));
			}
		}
	}

	private static int getDist(int island1, int island2) {
		int res = Integer.MAX_VALUE;
		boolean isChange = false;
		for (int row = 0; row < R; row++) {
			for (int col = 0; col < C; col++) {
				if (map[row][col] == island1 + 1) {
					int dist = 0;
					for (int d = 1; row + d < R; d++) {
						if (map[row + d][col] == island1 + 1) break;
						else if (map[row + d][col] != 0 && map[row + d][col] != island2 + 1) break; 
						else if (map[row + d][col] == 0) dist++; 
						else if (map[row + d][col] == island2 + 1) {
							if (!isChange) {
								res = Math.min(res,dist);
								isChange = true;
							}
							else if (isChange) {
								if (dist >= 2)
									res = Math.min(res,dist);
							}
							if (res == 1) res = Integer.MAX_VALUE; 
							break;
						}
					}
					dist = 0;
					for (int d = 1; row - d > -1; d++) {
						if (map[row - d][col] == island1 + 1) break;
						else if (map[row - d][col] != 0 && map[row - d][col] != island2 + 1) break; 
						else if (map[row - d][col] == 0) dist++; 
						else if (map[row - d][col] == island2 + 1) { 
							if (!isChange) {
								res = Math.min(res,dist);
								isChange = true;
							}
							else if (isChange) {
								if (dist >= 2)
									res = Math.min(res,dist);
							}
							if (res == 1) res = Integer.MAX_VALUE; 
							break;
						}
					}
					dist = 0;
					for (int d = 1; col + d < C; d++) {
						if (map[row][col + d] == island1 + 1) break;
						else if (map[row][col + d] != 0 && map[row][col + d] != island2 + 1) break; 
						else if (map[row][col + d] == 0) dist++; 
						else if (map[row][col + d] == island2 + 1){ 
							if (!isChange) {
								res = Math.min(res,dist);
								isChange = true;
							}
							else if (isChange) {
								if (dist >= 2)
									res = Math.min(res,dist);
							}
							if (res == 1) res = Integer.MAX_VALUE; 
							break;
						}
					}
					dist = 0;
					for (int d = 1; col -d > -1; d++) {
						if (map[row][col - d] == island1 + 1) break;
						else if (map[row][col - d] != 0 && map[row][col - d] != island2 + 1) break; 
						else if (map[row][col - d] == 0) dist++; 
						else if (map[row][col - d] == island2 + 1) { 
							if (!isChange) {
								res = Math.min(res,dist);
								isChange = true;
							}
							else if (isChange) {
								if (dist >= 2)
									res = Math.min(res,dist);
							}
							if (res == 1) res = Integer.MAX_VALUE; 
							break;
						}
					}
				}
			}
		}
//		System.out.println((island1 + 1)+ "에서" + (island2 + 1)+ "로 가는 최단거리는 " + res + "입니다");
		return res;
	}

	private static int setNum() {
		boolean isVisit[][] = new boolean[R][C];
		int num = 1;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (!isVisit[i][j] && map[i][j] == 1)
					bfs(i, j, isVisit, num++);
			}
		}
		return num;
	}

	private static void bfs(int i, int j, boolean[][] visit, int num) {
		Queue<int[]> q = new LinkedList<>();
		visit[i][j] = true;
		map[i][j] = num;
		q.add(new int[] { i, j });
		while (!q.isEmpty()) {
			int[] temp = q.poll();
			for (int d = 0; d < 4; d++) {
				int nr = temp[0] + dr[d];
				int nc = temp[1] + dc[d];
				if (-1 < nr && nr < R && -1 < nc && nc < C && !visit[nr][nc] && map[nr][nc] == 1) {
					visit[nr][nc] = true;
					map[nr][nc] = num;
					q.add(new int[] { nr, nc });
				}
			}
		}
	}

}

/*
 * 7 8 0 0 0 0 0 0 1 1 1 1 0 0 0 0 1 1 1 1 0 0 0 0 0 0 1 1 0 0 0 1 1 0 0 0 0 0 0
 * 1 1 0 0 0 0 0 0 0 0 0 1 1 1 1 1 1 1 1
 * 
 * 7 8 1 0 0 1 1 1 0 0 0 0 1 0 0 0 1 1 0 0 1 0 0 0 1 1 0 0 1 1 1 0 0 0 0 0 0 0 0
 * 0 0 0 0 1 1 1 0 0 0 0 1 1 1 1 1 1 0 0
 * 
 * 7 8 1 0 1 1 1 1 0 0 0 0 0 0 0 0 1 1 0 0 1 0 0 0 1 1 0 0 1 1 1 0 0 0 0 0 1 1 1
 * 0 0 0 0 0 0 0 0 0 0 0 1 1 1 1 1 1 0 0
 * 
 * 
 */
