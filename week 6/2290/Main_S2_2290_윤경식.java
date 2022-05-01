import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static char[][] map;
	static int s;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		s = Integer.parseInt(st.nextToken());
		String str = st.nextToken();

		map = new char[2 * s + 3][(s + 2) * str.length() + str.length() - 1];
		
		for (int i = 0; i < 2 * s + 3; i++) {
			for (int j = 0; j < (s + 2) * str.length() + str.length() - 1; j++) {
				map[i][j] = ' ';
			}
		}

		for (int i = 0; i < str.length(); i++) {
			print(str.charAt(i), i);
		}

		for (int i = 0; i < 2 * s + 3; i++) {
			for (int j = 0; j < (s + 2) * str.length() + str.length() - 1; j++) {
				bw.write(map[i][j]);
			}
			bw.write("\n");
		}

		bw.flush();
		bw.close();

	}

	public static void print(char num, int idx) {
		int start = idx * 1 + (s + 2) * idx;
		for (int i = 0; i < s; i++) {
			if (num == '4' || num == '5' || num == '6' || num == '8' || num == '9' || num == '0')
				map[1 + i][start] = '|';
			if (num == '1' || num == '2' || num == '3' || num == '4' || num == '7' || num == '8' || num == '9'
					|| num == '0')
				map[1 + i][start + s + 1] = '|';
			if (num == '2' || num == '6' || num == '8' || num == '0')
				map[s + 2 + i][start] = '|';
			if (num != '2')
				map[s + 2 + i][start + s + 1] = '|';
			if (num == '2' || num == '3' || num == '5' || num == '6' || num == '7' || num == '8' || num == '9'
					|| num == '0')
				map[0][start + 1 + i] = '-';
			if (num == '2' || num == '3' || num == '5' || num == '6' || num == '4' || num == '8' || num == '9')
				map[s + 1][start + 1 + i] = '-';
			if (num == '2' || num == '3' || num == '5' || num == '6' || num == '0' || num == '8' || num == '9')
				map[2 + s * 2][start + 1 + i] = '-';
		}
	}

}