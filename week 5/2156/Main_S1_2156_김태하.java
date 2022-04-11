package boj;

import java.util.Arrays;
import java.util.Scanner;

public class Main_S1_2156_김태하 {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int[] w = new int[10001];
		int[] dp = new int[10001];
		
		for (int i = 1; i <= N; i++) {
			w[i] = sc.nextInt();
		}
		
		dp[1] = w[1];
		dp[2] = w[1] + w[2];
		
		for (int i = 3; i <= N; i++) {
			int temp = Math.max(w[i] + dp[i-2], w[i] + w[i-1] + dp[i-3]);
			temp = Math.max(temp, dp[i-1]);
			dp[i] = temp;
		}
		
		System.out.println(dp[N]);
//		System.out.println(Arrays.toString(dp));
		
	}
}
