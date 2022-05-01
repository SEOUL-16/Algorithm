package com.algo.season2.second;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

// https://www.acmicpc.net/problem/6198
// 옥상 정원 꾸미기

/*
 * = 
 * =           = 
 * =     -     = 
 * =     =     =        -> 관리인이 보는 방향
 * =  -  =  =  =   
 * =  =  =  =  =  = 
 * 10  3  7  4  12 2     -> 빌딩의 높이
 * [1][2][3][4][5][6]    -> 빌딩의 번호
 */

/* 알고리즘
 * 1번 옥상부터 차례대로 검사
 * 		스택 맨 위에 있는 건물의 높이가 현재 검사하는 건물 높이보다 낮거나 같으면 (건물 번호 차이-1)를 sum하고 스택을 pop한다
 * 		스택 맨 위에 있는 건물의 높이가 현재 검사하는 건물 높이보다 낮거나 같으면 (건물 번호 차이-1)를 sum하고 스택을 pop한다
 * 		스택 맨 위에 있는 건물의 높이가 현재 검사하는 건물 높이보다 낮거나 같으면 (건물 번호 차이-1)를 sum하고 스택을 pop한다
 * 		스택 맨 위에 있는 건물의 높이가 현재 검사하는 건물 높이보다 낮거나 같으면 (건물 번호 차이-1)를 sum하고 스택을 pop한다
 * 		스택 맨 위에 있는 건물의 높이가 현재 검사하는 건물 높이보다 높으면,
 * 		현재 검사하는 건물 번호를 스택에 넣는다
 * 다음 건물을 검사하러 간다
 */

public class Main_G5_6198_이지현 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str =  br.readLine();
		int N = Integer.parseInt(str);
		
		int[] buildings = new int[N+1]; // 맨 마지막에 엄청 높은 가상의 빌딩을 넣어줘야 제대로 된 결과가 나옴
		for (int i = 0; i < N; i++)
			buildings[i] = Integer.parseInt(br.readLine());
		buildings[N] = Integer.MAX_VALUE;
		
		long sum = 0;
		Deque<Integer> stack = new ArrayDeque<Integer>();
		
		for (int i = 0; i <= N; i++) {
			
			while(!stack.isEmpty()) {
				if(buildings[stack.peek()] > buildings[i]) 
					break;

				sum += (long)i - stack.pop() - 1;
			}
			
			stack.push(i);
		}
		
		System.out.println(sum);
	}
}