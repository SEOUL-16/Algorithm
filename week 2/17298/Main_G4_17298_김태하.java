package boj;

import java.util.*;
import java.io.*;

public class Main_G4_17298_김태하 {
	
	//	입력을 받은 수 num이 기존에 받은 인덱스에 있는 수들보다 큰지 확인한다.
	// 		크다면 해당 인덱스에 있는 수들의 오큰수를 num으로 만들고, 인덱스를 스택에서 제거한다.
	//		num의 인덱스를 스택에 추가한다.
	//	오큰수 배열을 순회하면서 오큰수가 0이면 -1을 출력하고, 아니라면 해당 오큰수를 출력한다.
	
	static int N;
	static int[] nums, okens;
	static Stack<Integer> stack = new Stack<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		
		nums = new int[N];
		okens = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
			
			while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
				okens[stack.pop()] = nums[i];
			}
			
			stack.push(i);
		}
		
		for (int i = 0; i < N; i++) {
			if (okens[i] == 0) {
				bw.write("-1 ");
			} else {
				bw.write(okens[i] + " ");
			}
		}
		
		bw.flush();
		bw.close();
	}
	
}
