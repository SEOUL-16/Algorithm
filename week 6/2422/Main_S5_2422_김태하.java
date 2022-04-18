package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main_S5_2422_김태하 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int answer = 0;
		ArrayList<HashSet<Integer>> notGood = new ArrayList<HashSet<Integer>>();
		for (int i = 0; i <= N; i++) {
			notGood.add(new HashSet<Integer>());
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			notGood.get(a).add(b);
			notGood.get(b).add(a);
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = i+1; j <= N; j++) {
				if (!isGood(i, j, notGood)) continue;
				for (int k = j+1; k <= N; k++) {
					if (!isGood(i, k, notGood) || !isGood(j, k, notGood)) continue;
//					System.out.println("i = " + i + " j = " + j + " k = " + k);
					answer++;
				}
			}
		}
		
		System.out.println(answer);
	}

	private static boolean isGood(int i, int j, ArrayList<HashSet<Integer>> notGood) {
		return !(notGood.get(i).contains(j) || notGood.get(j).contains(i));
	}
}
