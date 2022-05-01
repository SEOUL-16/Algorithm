import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

//참고 : https://fastz123.github.io/algorithm/Algorithm-%EB%8B%A8%EC%96%B4%EB%A7%8C%EB%93%A4%EA%B8%B0(%EB%B0%B1%EC%A4%801148)/

public class Main {

	static int size;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = null;
		int[][] dictionary = new int[200000][26];
//		사전 입력
		while (true) {
			String temp = br.readLine();
			if (temp.charAt(0) == '-')
				break;
			for (int i = 0, end = temp.length(); i < end; i++) {
				dictionary[size][temp.charAt(i) - 'A']++;
			}
			size++;
		}
		while (true) {
			String temp = br.readLine();
			if (temp.charAt(0) == '#')
				break;
			int[] map = new int[26];
			for (int i = 0, end = temp.length(); i < end; i++) {
				map[temp.charAt(i) - 'A']++;
			}
			int[] answer = new int[26];
			loop: for (int i = 0; i < size; i++) {
				for (int j = 0; j < 26; j++) {
					if (map[j] < dictionary[i][j])
						continue loop;
				}
				for (int j = 0; j < 26; j++) {
					if (dictionary[i][j] > 0)
						answer[j]++;
				}
			}

			int max = Integer.MIN_VALUE;
			int min = Integer.MAX_VALUE;
			for (int i = 0; i < 26; i++) {
				if (map[i] > 0) {
					if (answer[i] > max)
						max = answer[i];
					if (answer[i] < min)
						min = answer[i];
				}
			}

			for (int i = 0; i < 26; i++) {
				if (map[i] > 0) {
					if (answer[i] == min) {
						bw.write(String.valueOf((char) (i + 'A')));
					}
				}
			}
			bw.write(" " + String.valueOf(min) + " ");
			for (int i = 0; i < 26; i++) {
				if (map[i] > 0) {
					if (answer[i] == max) {
						bw.write(String.valueOf((char) (i + 'A')));
					}
				}
			}
			bw.write(" " + String.valueOf(max) + "\n");
		}
		bw.flush();
		bw.close();
	}
}