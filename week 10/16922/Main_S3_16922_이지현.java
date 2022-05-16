package com.algo.season2.tenth;

import java.util.Scanner;

public class Main_S3_16922_이지현 {
	
	static boolean[] arr;
	static int N, count = 0;
	static int[] Carr = {50, 10, 5, 1};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		arr = new boolean[N * 50+1];
		
		func2(1, 0);
		
		for(boolean b : arr) {
			if(b)
				count++;
		}
		
		System.out.println(count);
		
	}
	
	// i가 최대한 작을 때 num+c에 대한 arr 방문체크를 하려면, 큰 수부터
	private static void func(int i, int num) {

		for(int c : Carr) {
			if(!arr[num+c]) {
				if(i == N) {
					arr[num+c] = true;
//					System.out.print(num+c+" ");
					count++;
				}
				else {
					func(i+1, num+c);
				}
			}
		}
	}

	private static void func2(int i, int num) {
		for(int c : Carr) {
			if(i == N) {
				arr[num+c] = true;
			}
			else {
				func2(i+1, num+c);
			}
		}
	}
}