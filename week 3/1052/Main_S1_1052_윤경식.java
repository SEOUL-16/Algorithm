import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());

//		주어진 물통의 개수
		int N = Integer.parseInt(st.nextToken());
//		몇 개까지 줄여야 하는지
		int K = Integer.parseInt(st.nextToken());

		int idx = 0;
		int tempN = N;
		while (tempN != 0) {
			tempN /= 2;
			idx++;
		}

		int answer = 0;
		int count = 0;
		for (int i = 0; i <= idx; i++) {
			if ((N & (1 << i)) > 0) {
				count++;
			}
		}
		while (count > K) {
			N += 1;
			answer += 1;
			int temp = 0;
			for (int i = 0; i < idx; i++) {
				if ((N & 1 << i) > 0) {
					temp++;
				}
			}
			count = temp;
		}
		bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
	}
}