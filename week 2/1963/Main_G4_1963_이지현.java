package com.algo.season2.second;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

// https://www.acmicpc.net/problem/1963
// 소수 경로
public class Main_G4_1963_이지현 {
	static Set<Integer> isVisited;
	static int start, end;

	static class Sosu {
		int number; // 4자리 숫자
		int count; // 처음 숫자에서 변경한 횟수
		
		public Sosu(int number, int count) {
			super();
			this.number = number;
			this.count = count;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] strs = br.readLine().split(" ");
		int T = Integer.parseInt(strs[0]);

		for (int t = 0; t < T; t++) {
			strs = br.readLine().split(" ");
			start = Integer.parseInt(strs[0]); // 처음 비밀번호
			end = Integer.parseInt(strs[1]); // 원하는 비밀번호
			
			isVisited = new HashSet<>();
			System.out.println(bfs(new Sosu(start, 0)));
		}
		
	}

	// bfs 탐색
	private static int bfs(Sosu cur) {
		Queue<Sosu> queue = new LinkedList<>();
		queue.offer(cur);
		isVisited.add(cur.number);
		
		Sosu next;
		int temp;
		
		while(!queue.isEmpty()) {
			cur = queue.poll();

			if(cur.number == end) // 원하는 비밀번호가 되었으면 count 리턴, bfs 탐색이므로 최단거리 보장됨
				return cur.count;
			
			for (int i = 0; i < 4; i++) { // 4자리를 차례대로 바꿔봄
				// ex) 1246에서 십의 자리를 삭제하여 1206으로 만들고 그 수를 temp에 저장하는 코드
				temp = cur.number - ((cur.number % (int)Math.pow(10, i+1) / (int)Math.pow(10, i)) * (int)Math.pow(10, i));

				for (int j = 0; j < 10; j++) { // 삭제한 자리에 0부터 9까지의 수를 다 넣어봄
					if(i == 3 && j == 0) // 다만 천의 자리 숫자는 0이 될 수 없다
						continue;
					
					// ex) 1206에다가 1216, 1226, 1236... 이런 식으로 삽입하는 코드
					next = new Sosu((int) (temp+(j*Math.pow(10, i))), cur.count+1);

					// 처음 나온 숫자면서 그 수가 소수라면 queue에 넣는다
					if(!isVisited.contains(next.number) && isPrime(next.number)) {
						queue.offer(next);
						isVisited.add(next.number);
					}
				}
			}
		}
		
		return 0;
	}

	private static boolean isPrime(int number) {
		if(number < 2) return false;
        for(int i=2; i*i<=number; i++){
            if(number % i == 0) return false;            
        }
        return true;
	}
}