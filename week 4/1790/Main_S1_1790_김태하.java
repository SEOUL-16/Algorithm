package boj;

import java.util.Scanner;

public class Main_S1_1790_김태하 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int N = scanner.nextInt();
		int K = scanner.nextInt();
		
		
		int posSum = 0;
		char answer = '0';
		
		for (int i = 1; i <= N; i++) {
			posSum += (int)Math.log10(i) + 1;
			
			if (posSum >= K) {
				answer = Integer.toString(i).charAt((int)Math.log10(i) - (posSum - K));
				break;
			}
		}
		
		if (posSum < K) {
			System.out.println(-1);
		} else {
			System.out.println(answer);
		}
		
		
		scanner.close();
	}
}
