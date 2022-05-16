package com.algo.season2.tenth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Main_G4_5052_이지현 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			LinkedList<String> list = new LinkedList<>();
			int N = Integer.parseInt(br.readLine());
			
			for (int n = 0; n < N; n++) {
				list.add(br.readLine());
			}
			
			Collections.sort(list);
			
			boolean isValid = true;
			// 0 ~ N-2번째 문자열에 대해
			for (int i = 0; i < N-1; i++) {
				if(list.get(i+1).indexOf(list.get(i)) ==0)
					isValid = false;
			}
			
			if(isValid)
				System.out.println("YES");
			else
				System.out.println("NO");
			
		}
	}
}