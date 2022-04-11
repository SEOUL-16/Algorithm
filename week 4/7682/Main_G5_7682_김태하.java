package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_G5_7682_김태하 {
	// 게임판 정보를 입력받는다.
	// 정보가 end가 아닌 동안,
	//		첫번째 행에 대해서 세로로 끝나는 조건이 있는지 검색한다.
	// 		첫번째 열에 대해서 가로로 끝나는 조건이 있는지 검색한다.
	//		대각선에 대해서 끝나는 조건이 있는지 검색한다.
	//		끝나는 조건의 수가 1이면 valid를 출력한다.
	//		아니면 invalid를 출력한다.
	//		
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = null;
		char[] map = new char[9];
		
		while (!(line = br.readLine()).equals("end")) {
			map = line.toCharArray();
			
			int numO = 0;
			int numX = 0;
			
			for (int i = 0; i < 9; i++) {
				if (map[i] == 'X') ++numX;
				if (map[i] == 'O') ++numO;
			}
			
			int lastTurn = numX - numO;		// 1 : X, 0 : O
			if (lastTurn < 0 || lastTurn > 1) {
				System.out.println("invalid");
				continue;
			}
			
			int endX = 0;
			int endO = 0;
			
			for (int i = 0; i < 3; i++) {
				if (map[i] == map[i + 3] && map[i] == map[i + 6]) {
					if (map[i] == 'X') {
						++endX;
					} else if (map[i] == 'O'){
						++endO;
					}
				}
				
				if (map[(3 * i)] == map[(3 * i) + 1] && map[(3 * i)] == map[(3 * i) + 2]) {
					if (map[3 * i] == 'X') {
						++endX;
					} else if (map[3 * i] == 'O'){
						++endO;
					}
				}
				
			}
			
			if (map[0] == map[4] && map[0] == map[8]) {
				if (map[0] == 'X') {
					++endX;
				} else if (map[0] == 'O'){
					++endO;
				}
			}
			
			if (map[2] == map[4] && map[2] == map[6]) {
				if (map[2] == 'X') {
					++endX;
				} else if (map[2] == 'O'){
					++endO;
				}
			}
			
			// X가 이겼을 때, O가 이겼을 때, 비겼을 때
			if ((lastTurn == 1 && endX != 0 && endO == 0) || (lastTurn == 0 && endX == 0 && endO != 0) || (lastTurn == 1 && endX == 0 && endO == 0 && numX == 5 && numO == 4)) {
				System.out.println("valid");
			} else {
				System.out.println("invalid");
			}
		}
	}
}
