package 백준_7주차_알고리즘스터디;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_G4_9935_조민수 {
	static char[] strings;
	static char[] bomb;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		strings = br.readLine().toCharArray();
		bomb = br.readLine().toCharArray();
		char lastWord = bomb[bomb.length - 1];
		Stack<Character> st = new Stack<>();
		for (int i = 0, end = strings.length; i < end; i++) {
			if (strings[i] != lastWord) st.push(strings[i]);
			else {
				int stackSize = st.size();
				int bombSize = bomb.length;
				if (stackSize < bombSize - 1) {
					st.push(strings[i]);
					continue;
				}
				boolean flag = true;
				for (int index = stackSize - bombSize + 1, bidx = 0; index < stackSize; index++) {
					if (st.get(index) != bomb[bidx++]) {
						flag = false;
						break;
					}
				}
				if (flag) {
					for (int index = 0; index < bombSize - 1; index++) st.pop();
				}else {
					st.push(strings[i]);
				}
			}
			
		}
		if (st.isEmpty()) System.out.println("FRULA");
		else {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < st.size(); i++) {
				sb.append(st.get(i));
			}
			System.out.println(sb);
		}
	} 	 	

}
