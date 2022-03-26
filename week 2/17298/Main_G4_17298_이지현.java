package com.algo.season2.second;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

// https://www.acmicpc.net/problem/17298
// 오큰수

/* 알고리즘
 * 1번 숫자부터 차례대로 검사
 * 		스택 맨 위에 있는 인덱스가 가리키는 숫자가 현재 검사하는 인덱스가 가리키는 숫자보다 작으면
 * 		(배열에서) 스택 맨 위에 있는 인덱스에 현재 검사하는 인덱스가 가리키는 숫자를 저장하고 스택을 pop한다
 * 		스택 맨 위에 있는 인덱스가 가리키는 숫자가 현재 검사하는 인덱스가 가리키는 숫자보다 작으면
 * 		(배열에서) 스택 맨 위에 있는 인덱스에 현재 검사하는 인덱스가 가리키는 숫자를 저장하고 스택을 pop한다
 * 		스택 맨 위에 있는 인덱스가 가리키는 숫자가 현재 검사하는 인덱스가 가리키는 숫자보다 작으면
 * 		(배열에서) 스택 맨 위에 있는 인덱스에 현재 검사하는 인덱스가 가리키는 숫자를 저장하고 스택을 pop한다
 * 		스택 맨 위에 있는 인덱스가 가리키는 숫자가 현재 검사하는 인덱스가 가리키는 숫자보다 크거나 같으면
 * 		현재 검사하는 인덱스를 스택에 넣는다
 * 다음 숫자를 검사하러 간다
 */

public class Main_G4_17298_이지현 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str =  br.readLine();
		int N = Integer.parseInt(str);
		
		int[] answer = new int[N];
		Deque<int[]> stack = new ArrayDeque<>(); // stack이 (index, value)를 저장하게 하여
		
		int temp;
		String[] strs = br.readLine().split(" ");
		for (int i = 0; i < N; i++) { // 입력 수열을 저장하지 않고도 검사 한 번에 answer 배열을 완성할 수 있도록 한다
			temp = Integer.parseInt(strs[i]);
			
			while(!stack.isEmpty()) {
				if(stack.peek()[1] >= temp) 
					break;

				answer[stack.pop()[0]] = temp;
			}
			
			stack.push(new int[] {i, temp});
		}
		
		StringBuilder sb = new StringBuilder(); // StringBuilder 안 쓰면 시간 초과
		for (int i = 0; i < N; i++) {
			if(answer[i] == 0)
				sb.append(-1);
			else
				sb.append(answer[i]);
			
			sb.append(" ");
		}
		
		System.out.println(sb);
	}
}