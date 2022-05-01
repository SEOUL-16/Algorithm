package firstweek;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_G4_1967_조민수 {
	static ArrayList<int[]>[]arr;
	static int [] hubo;
	static int N;
	static int max;
	static int answer;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		arr = new ArrayList[N + 1];
		max = 0;
		for (int i = 1; i <= N; i++) {
			arr[i] = new ArrayList<>();
		}
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			arr[Integer.parseInt(st.nextToken())].add(new int[] {Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())});
		}
		bfs();
	}
	private static void bfs() {
		for (int i = 1; i <= N; i++) {
			max = 0;
			findSum(i,0,i);
		}
		System.out.println(answer);
	}
	
	private static void findSum(int start, int sum, int origin) {
		if (start == origin) {
			hubo = new int[arr[origin].size()];
		}
		if (arr[start].size() == 0) {
			max = Math.max(max, sum);
			return ;
		}
		for (int i = 0,end = arr[start].size(); i < end; i++) {
			findSum(arr[start].get(i)[0], sum + arr[start].get(i)[1],origin);
			if (start == origin) {
				hubo[i] = max;
				max = 0;
			}
		}
		if (start == origin) {
			int temp = 0;
			if (hubo.length == 1) {
				temp = hubo[0];
			}else if(hubo.length > 1){
				Arrays.sort(hubo);
				temp = hubo[hubo.length - 1] +hubo[hubo.length - 2]; 
			}
			answer = Math.max(answer, temp);
		}
	}

}
