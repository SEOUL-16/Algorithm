package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_S2_2290_김태하 {
	static int[] top = 		{1,  0,  1, 1, 0,  1,  1, 1, 1, 1};
	static int[] topmid = 	{0,  1,  1, 1, 0, -1, -1, 1, 0, 0};
	static int[] mid = 		{0,  0,  1, 1, 1,  1,  1, 0, 1, 1};
	static int[] midbot = 	{0,  1, -1, 1, 1,  1,  0, 1, 0, 1};
	static int[] bot = 		{1,  0,  1, 1, 0,  1,  1, 0, 1, 1};
	
	public static void main(String[] args) throws Exception {
		// 위부터 5개 부분으로 나눠서 출력한다.
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		int s = Integer.parseInt(st.nextToken());
		ArrayList<Integer> nums = new ArrayList<Integer>();
		for (char c : st.nextToken().toCharArray()) {
			nums.add(c - '0');
		}
		StringBuilder t = new StringBuilder();
		StringBuilder tm = new StringBuilder();
		StringBuilder m = new StringBuilder();
		StringBuilder mb = new StringBuilder();
		StringBuilder b = new StringBuilder();
		
		for (int c : nums) {
			if (top[c] == 1) {
				t.append(drawh(s));
			} else {
				t.append(noh(s));
			}
		}
		t.append("\n");
		
		
		for (int i = 0; i < s; i++) {
			for (int c : nums) {
				if (topmid[c] == -1) {
					tm.append(drawLeft(s));
				} else if (topmid[c] == 0) {
					tm.append(drawBoth(s));
				} else {
					tm.append(drawRight(s));
				}
			}
			tm.append("\n");
		}
		
		for (int c : nums) {
			if (mid[c] == 1) {
				m.append(drawh(s));
			} else {
				m.append(noh(s));
			}
		}
		m.append("\n");
		
		for (int i = 0; i < s; i++) {
			for (int c : nums) {
				if (midbot[c] == -1) {
					mb.append(drawLeft(s));
				} else if (midbot[c] == 0) {
					mb.append(drawBoth(s));
				} else {
					mb.append(drawRight(s));
				}
			}
			mb.append("\n");
		}
		
		for (int c : nums) {
			if (bot[c] == 1) {
				b.append(drawh(s));
			} else {
				b.append(noh(s));
			}
		}
		b.append("\n");
		
		StringBuilder result = new StringBuilder();
		result.append(t.toString());
		result.append(tm.toString());
		result.append(m.toString());
		result.append(mb.toString());
		result.append(b.toString());
		
		System.out.println(result);
	}
	
	static String drawh(int s) {
		StringBuilder sb = new StringBuilder();
		sb.append(" ");
		for (int i = 0; i < s; i++) {
			sb.append("-");
		}
		sb.append(" ");
		sb.append(" ");
		return sb.toString();
	}
	static String noh(int s) {
		StringBuilder sb = new StringBuilder();
		sb.append(" ");
		for (int i = 0; i < s; i++) {
			sb.append(" ");
		}
		sb.append(" ");
		sb.append(" ");
		return sb.toString();
	}
	
	static String drawLeft(int s) {
		StringBuilder sb = new StringBuilder();
		sb.append("|");
		for (int i = 0; i < s; i++) {
			sb.append(" ");
		}
		sb.append(" ");
		sb.append(" ");
		return sb.toString();
	}
	
	static String drawRight(int s) {
		StringBuilder sb = new StringBuilder();
		sb.append(" ");
		for (int i = 0; i < s; i++) {
			sb.append(" ");
		}
		sb.append("|");
		sb.append(" ");
		return sb.toString();
	}
	
	static String drawBoth(int s) {
		StringBuilder sb = new StringBuilder();
		sb.append("|");
		for (int i = 0; i < s; i++) {
			sb.append(" ");
		}
		sb.append("|");
		sb.append(" ");
		return sb.toString();
	}
	
}
