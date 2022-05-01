package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_G4_1963_김태하 {
	// 1000 이상 10000 미만의 수 중 소수를 찾아 배열에 더한다.
	// 입력으로 받은 수가 소수인지 확인한다.
	// 소수라면,
	//		입력 받은 수를 큐에 넣는다.
	//		큐가 비어있지 않은 동안,
	//			현재 수 = queue.poll;
	//			현재 수가 목표 수와 같은지 확인한다.
	//			목표 수와 같다면,
	//				현재까지의 단계 수를 출력한다.
	//			목표 수와 같지 않다면,
	//				배열에서 현재 수와 한 자리 차이나는 소수들을 큐에 넣는다.
	//				현재 수를 방문처리한다.
	//			
	//	소수가 아니라면,
	//		Impossible을 출력한다.
	
	static ArrayList<String> primes = new ArrayList<>();
	static boolean[] visited;
	static String start, target;
	static int answer, T;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		getPrimes();
		
		for (int i = 0; i < T; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			start = st.nextToken();
			target = st.nextToken();
			answer = Integer.MAX_VALUE;
			
			if (start.equals(target)) {
				System.out.println(0);
				continue;
			}
			
			if (primes.contains(start) && primes.contains(target)) {
				visited = new boolean[primes.size()];
				bfs();
			} else {
				System.out.println("Impossible");
			}
			
			System.out.println(answer);
		}
	}

	private static void bfs() {
		Queue<Prime> queue = new LinkedList<>();
		
		queue.add(new Prime(start, 0));
		
		for (int i = 0; i < primes.size(); i++) {
			if (primes.get(i).equals(start)) {
				visited[i] = true;
				break;
			}
		}
		
		while (!queue.isEmpty()) {
			Prime current = queue.poll();
			
			for (int i = 0; i < primes.size(); i++) {
				String ad = primes.get(i);
				
				if (!visited[i] && getDist(current.num, ad) == 1) {
					if (ad.equals(target)) {
						answer = Math.min(answer, current.dist + 1);
						
						return;
					} else {
						queue.offer(new Prime(ad, current.dist + 1));
						visited[i] = true;
					}
				}
			}
		}
	}

	private static int getDist(String num, String ad) {
		char[] numc = num.toCharArray();
		char[] adc = ad.toCharArray();
		int cnt = 0;
		
		for (int i = 0; i < 4; i++) {
			if (numc[i] != adc[i]) {
				++cnt;
			}
		}
		
		return cnt;
	}

	private static void getPrimes() {
		for (int i = 1001; i < 10000; i++) {
			if (isPrime(i)) {
				primes.add(Integer.toString(i));
			}
		}
	}

	private static boolean isPrime(int num) {
		for (int i = 2; i <= Math.sqrt(num); i++) {
			if (num % i == 0) {
				return false;
			}
		}
		
		return true;
	}
	
	static class Prime {
		String num;
		int dist;
		
		public Prime(String num, int dist) {
			super();
			this.num = num;
			this.dist = dist;
		}
		public Prime() {
			super();
		}
		@Override
		public String toString() {
			return "Prime [num=" + num + ", dist=" + dist + "]";
		}
	}
}
