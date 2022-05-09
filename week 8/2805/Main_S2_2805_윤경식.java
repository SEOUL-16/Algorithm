import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int M;
	static int[] namoo;
	static int Min;
	static int Max;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		namoo = new int[N];

		Min = 0;
		Max = Integer.MIN_VALUE;

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int temp = Integer.parseInt(st.nextToken());

			namoo[i] = temp;

			if (Max < temp)
				Max = temp;
		}

		int middle;
		int answer = 0;
		while (Min <= Max) {
			middle = (Min + Max) / 2;
			long sum = 0;
			for (int i = 0; i < N; i++) {
				if (middle < namoo[i]) {
					sum += namoo[i] - middle;
				}
			}
//			자른 합이 기준값보다 클 때
			if (sum >= M) {
				if (answer < middle)
					answer = middle;
				Min = middle + 1;
			} else {
				Max = middle - 1;
			}

		}

		bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();

	}

}