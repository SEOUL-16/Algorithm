package com.algo.season2.w89;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 1,000,000그루의 나무를 일일이 세어보는 과정은 더 줄일 수 없다.
// 그렇다면 검사 케이스를 줄여야 한다. -> 이진탐색으로 '적절한 높이'를 찾아나간다.
// 데이터가 너무 커서 어떤 변수는 long으로 선언해야 한다.
public class Main_S2_2805_이지현 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] strs = br.readLine().split(" ");

		int N = Integer.parseInt(strs[0]);
		int M = Integer.parseInt(strs[1]);

		// start 값 세팅
		int start = 0;

		// 나무 높이 입력 받기
		int[] trees = new int[N];
		int max = 0;
		strs = br.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			trees[i] = Integer.parseInt(strs[i]);
			max = max < trees[i] ? trees[i] : max;
		}

		// end 값 세팅
		int end = max;

		while (start + 1 < end) {
			int mid = (start + end) / 2;
			
			long cutcount = 0;
			for (int i = 0; i < N; i++) {
				cutcount += trees[i] > mid ? trees[i] - mid : 0;
			}

			if (cutcount >= M)
				start = mid;
			else
				end = mid;
		}

		System.out.println(start);
	}
}