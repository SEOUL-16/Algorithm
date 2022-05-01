package com.algo.season2.nineth;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Main_S1_2447_이지현 {
	static char[][] picture;
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		// char[N][N]을 만든다
		picture = new char[N][N];
				
		// 그리기 함수를 호출한다
		drow(0, 0, N);
		
		// picture 내용을 출력한다
		// !! print함수 쓰면 시간 초과 남 !!
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				bw.append(picture[i][j]);
			}
			bw.append("\n");
		}
		bw.flush();
	}
	
	public static void drow(int y, int x, int N) {
		if(N == 1) {
			picture[y][x] = '*';
			return;
		}
		
		int unit = N/3;
		drow(y+0*unit, x+0*unit, unit);		drow(y+0*unit, x+1*unit, unit);		drow(y+0*unit, x+2*unit, unit); 
		
		drow(y+1*unit, x+0*unit, unit);		nodrow(y+1*unit, x+1*unit, unit);	drow(y+1*unit, x+2*unit, unit); 
		
		drow(y+2*unit, x+0*unit, unit);		drow(y+2*unit, x+1*unit, unit);		drow(y+2*unit, x+2*unit, unit); 
	}

	private static void nodrow(int y, int x, int unit) {
		for (int i = y; i < y+unit; i++) {
			for (int j = x; j < x+unit; j++) {
				picture[i][j] = ' ';
			}
		}
	}
}
