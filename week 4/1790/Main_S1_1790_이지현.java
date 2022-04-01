package com.algo.season2.fourth;

import java.util.Scanner;

// https://www.acmicpc.net/problem/1790
// 수 이어 쓰기 2
public class Main_S1_1790_이지현 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int k = sc.nextInt();


		if(howlong(N) < k) {
			System.out.println(-1);
			return;
		}
		
		System.out.println(calcul_char(k));		

		
//		for (int i = 1000000; i < 1000050; i++) {
//			System.out.print(calcul_char(i));
//		}
		
//		for (int i = 1; i < 200; i++)
//		System.out.println(calcul(i));
	}

	// N까지의 수를 이용해서 만든 수는 몇 번째 자리까지 있는지 계산
	private static int howlong(int N) {
		int size = 0;
		for (int i = 1;; i++) {
			if(N >= Math.pow(10, i)) {
				size += (Math.pow(10, i) - 1 - (Math.pow(10, i-1) - 1) ) * i;
			} else {
				size += (N-(Math.pow(10, i-1) - 1) )* i;
				break;
			}
		}
		return size;
	}
	
	// k번째 숫자가 어떤 수의 일부인지 계산해주는 메소드
	private static char calcul_char(int k) {
		int i, start = 0, end = 0;
		
		for (i = 1;; i++) {
			start = end + 1;
			end = (int) ((start) + Math.pow(10, i - 1) * 9 * i)-1;

			if (k >= start && k <= end)
				break;
		}

		String value= Integer.toString((int) Math.pow(10, i - 1) + (k-start) / i);
		return value.charAt((k-start)%i);
	}
	
	// k번째 숫자가 어떤 수의 일부인지 계산해주는 메소드
	private static int calcul(int k) {
		int i, start = 0, end = 0;
		
		for (i = 1;; i++) {
			start = end + 1;
			end = (int) ((start) + Math.pow(10, i - 1) * 9 * i)-1;

			if (k >= start && k <= end)
				break;
		}

		return ((int) Math.pow(10, i - 1) + (k-start) / i);
	}
}