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

//		1~N까지 출력하면
		int N = Integer.parseInt(st.nextToken());
//		K번째 문자
		int K = Integer.parseInt(st.nextToken());

		// 10의 몇 제곱 수 탐색해야되는지
		int idx = 0;
		while (K > Math.pow(10, idx) * 9 * (idx + 1)) {
			K -= Math.pow(10, idx) * 9 * (idx + 1);
			idx++;
		}

		int num = 0;
		while (K > (idx + 1)) {
			K -= (idx + 1);
			num++;
		}

		int answer = (int) (Math.pow(10, idx) + (num));

//		bw.write(String.valueOf(answer) + "\n");
		if (N >= answer) {
			bw.write(String.valueOf(answer).charAt(K - 1));
		} else {
			bw.write(String.valueOf(-1));
		}

		bw.flush();
		bw.close();

	}

}