package boj;

import java.util.LinkedList;
import java.util.Scanner;

public class Main_S2_1138_김태하 {
	// 키가 가장 큰 사람은 어디에 있든 항상 0이다.
	// 키가 가장 작은 사람은 자신의 위치를 그대로 말한다.
	// 가장 키가 큰 사람의 정보부터(뒤에서부터) 처리한다.
	// 		자신보다 키가 큰 사람의 수가 0이 될 때까지,
	//			뒤로 이동한다.
	//		해당 위치에 본인이 들어간다.
	static LinkedList<Integer> line = new LinkedList<>();
	static int[] infos;
	static int N;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		N = scanner.nextInt();
		
		infos = new int[N];
		
		for (int i = 0; i < N; i++) {
			infos[i] = scanner.nextInt();
		}
		
		for (int i = N - 1; i >= 0; i--) {
			int location = 0;
			
			while (infos[i] > 0) {
				if (line.get(location) > i + 1) {
					--infos[i];
				}
				++location;
			}
			
			line.add(location, i + 1);
		}
		
		for (int i = 0; i < N; i++) {
			System.out.print(line.get(i) + " ");
		}
		
		scanner.close();
	}
}
