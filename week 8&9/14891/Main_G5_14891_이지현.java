package com.algo.season2.w89;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_G5_14891_이지현 {

	static Queue<Integer>[] gears;
	static Boolean[] isVisited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 4개의 톱니바퀴 생성
		int N = 4;
		gears = new Queue[N];

		for (int i = 0; i < N; i++) {
			gears[i] = new LinkedList<Integer>();

			// 각 톱니바퀴 상태 저장
			String str = br.readLine();
			for (int j = 0; j < 8; j++)
				gears[i].offer(str.charAt(j) - '0');
		}

		int L = Integer.parseInt(br.readLine());
		for (int i = 0; i < L; i++) {
			String[] strs = br.readLine().split(" ");

			// 회전 함수 호출
			isVisited = new Boolean[] {false, false,false,false};
			work(Integer.parseInt(strs[0]) - 1, Integer.parseInt(strs[1]));
		}

		int sum = 0;
		for (int i = 0; i < N; i++) {
			// S극이면 점수 준다
			if (gears[i].peek() == 1)
				sum += (1 << i);
		}

		System.out.println(sum);
	}

	// 회전 함수 - c: 톱니 번호, d: 회전방향
	private static void work(int c, int d) {
		// 방문 표시
		isVisited[c] = true;

		// 옆 톱니 회전 체크 (재귀 호출) : 1 유효한 톱니고, 2 안 가본 방향의 톱니고, 3 극이 다르면 얘도 돌리자
		if(c-1 >= 0 && !isVisited[c-1] && gears[c].toArray()[6] != gears[c-1].toArray()[2])
			work(c-1, -d);
		
		if(c+1 <  4 && !isVisited[c+1] && gears[c].toArray()[2] != gears[c+1].toArray()[6])
			work(c+1, -d);
		
		// 현재 톱니 회전 처리
		// 시계 방향
		if(d == 1) {
			for (int i = 0; i < 7; i++) {
				int temp = gears[c].poll();
				gears[c].offer(temp);
			}
		}
		// 반시계 방향
		else if(d == -1) {
			int temp = gears[c].poll();
			gears[c].offer(temp);
		}
	}

}
