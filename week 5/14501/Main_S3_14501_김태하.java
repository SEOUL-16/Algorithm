package boj;

import java.util.Arrays;
import java.util.Scanner;

public class Main_S3_14501_김태하 {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int[] T = new int[N+1];
		int[] P = new int[N+1];
		int[] dp = new int[N+2];
		
		for (int i = 1; i <= N; i++) {
			T[i] = sc.nextInt();
			P[i] = sc.nextInt();
		}
		
		for (int i = N; i >= 0; i--) {		// 뒤에서부터 최적해 구하기
			if (T[i] + i - 1 > N)			// 일정에 맞지 않는 경우
				dp[i] = dp[i+1];			// 		i를 포기하고 i+1번째 상담을 할 때의 최적해
			else
				dp[i] = Math.max(dp[i+1], P[i]+dp[i+T[i]]);	// i를 포기하고 i+1번째 상담을 할 때의 최적해와 i번쨰 일 + 다음 일을 할 때의 최적해를 비교
		}
		
		System.out.println(dp[0]);
//		System.out.println(Arrays.toString(dp));
//		System.out.println(Arrays.toString(P));
	}
}
