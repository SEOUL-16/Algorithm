package boj;

import java.util.Scanner;
import java.util.Stack;

public class Main_G5_6198_김태하 {
	static Stack<Integer> stack = new Stack<>();
	static int N;
	static long answer;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		N = scanner.nextInt();
		answer = 0;
		
		for (int i = 0; i < N; i++) {
			int current = scanner.nextInt();
			
			while (!stack.isEmpty() && stack.peek() <= current) {
				stack.pop();
			}
			
			answer += stack.size();
			stack.push(current);
		}
		
		System.out.println(answer);
		
		scanner.close();
	}
}
