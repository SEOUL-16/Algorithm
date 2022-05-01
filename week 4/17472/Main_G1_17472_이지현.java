package com.algo.season2.third;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.xml.crypto.NodeSetData;

// https://www.acmicpc.net/problem/17472
// 다리 만들기 2
public class Main_G1_17472_이지현 {

	static class Edge implements Comparable<Edge>{
		int from;
		int to;
		int cost;

		public Edge(int from, int to, int cost) {
			super();
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return this.cost - o.cost;
		}
	}

	static List<Edge> edgeList = new LinkedList<>();
	static int N, M, islandNum = 1;
	static int[] dr = { -1, 1, 0, 0 }; // 상하좌우
	static int[] dc = { 0, 0, -1, 1 }; // 상하좌우
	static int[][] map;
	static boolean[][] isVisted;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] strs = br.readLine().split(" ");
		N = Integer.parseInt(strs[0]);
		M = Integer.parseInt(strs[1]);
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			strs = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				int temp = Integer.parseInt(strs[j]);
				map[i][j] = temp == 1 ? -1 : 0; // -1이면 섬
			}
		}

		// 1. 모든 섬에 번호 부여
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == -1) {
					isVisted = new boolean[N][M];
					bfs(new int[] { i, j });
					islandNum++;
				}
			}
		}

		// 2. 놓을 수 있는 다리 찾기 (간선 정보 저장)
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] != 0) {
					// 네 방향으로
					for (int d = 0; d < 4; d++) {
						int ni = i + dr[d];
						int nj = j + dc[d];
						int distance = 0;
						// 섬을 만날 때까지 끝까지 전진
						while (cango(ni, nj)) {
							// 섬을 만났다면
							if (map[ni][nj] != 0) {
								if(distance >= 2) { // 다리 길이가 2 이상일 때만 간선으로 취급
									edgeList.add(new Edge(map[i][j], map[ni][nj], distance));
								}
								break; // 전진 종료
							}
							ni += dr[d];
							nj += dc[d];
							distance++;
						}
					}
				}
			}
		}

		// 3. 최소 연결 비용 찾기 (Kruskal)
		int[] nodes = new int[islandNum];
		makeset(nodes);
		
		int costSum = 0;
		Collections.sort(edgeList);
		for(Edge e : edgeList) {
			if(findset(nodes, nodes[e.from]) != findset(nodes, nodes[e.to])) {
				costSum+=e.cost;
				unionset(nodes, e.from, e.to);
			}
		}
		
		boolean isConnected = true;
		int itemp = findset(nodes, 1);
		for (int i = 1; i < islandNum; i++) {
			if(itemp != findset(nodes, i))
				isConnected = false;
		}
		
		if(isConnected && costSum != 0) // [수정] 섬이 모두 연결되어 있으며, costSum이 0인 경우를 제외하므로써 지도 전체가 땅이거나 바다인 경우를 빼기
			System.out.println(costSum);
		else
			System.out.println(-1);
	}

	
	/*
	 * Disjoint Set 메소드
	 */
	private static void makeset(int[] nodes) {
		for (int i = 1, end = nodes.length; i < end; i++)
			nodes[i] = i;
	}

	private static int findset(int[] nodes, int i) {
		if(nodes[i] != i)
			return nodes[i] = findset(nodes, nodes[i]); // path compression
		else
			return i;
	}

	private static void unionset(int[] nodes, int from, int to) {
		if(findset(nodes, from) != findset(nodes, to)) {
			nodes[findset(nodes, from)] = findset(nodes, to); // [수정] DisjointSet 코드 잘 외우기
		}
	}

	
	/*
	 * DFS 메소드
	 */
	private static void bfs(int[] cur) {
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(cur);
		map[cur[0]][cur[1]] = islandNum;
		isVisted[cur[0]][cur[1]] = true;

		while (!queue.isEmpty()) {
			cur = queue.poll();

			for (int d = 0; d < 4; d++) {
				int[] next = new int[] { cur[0] + dr[d], cur[1] + dc[d] };

				if (cango(next[0], next[1]) && map[next[0]][next[1]] == -1) {
					queue.offer(next);
					map[next[0]][next[1]] = islandNum; // 섬에 번호 부여
					isVisted[next[0]][next[1]] = true;
				}
			}
		}
	}

	private static boolean cango(int i, int j) {
		if (i >= 0 && i < N && j >= 0 && j < M)
			return true;
		return false;
	}

}