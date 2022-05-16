import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {

	static int n;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int t = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < t; tc++) {
			n = Integer.parseInt(br.readLine());
			String[] arr = new String[n];
			for(int i=0;i<n;i++) {
				arr[i] = br.readLine();
			}
			boolean yes = false;
			Arrays.sort(arr);
			for (int i = 0; i < n - 1; i++) {
				int length = arr[i].length();
				yes = false;
				for(int j=0;j<length;j++) {
					if (arr[i].charAt(j) != arr[i+1].charAt(j)) {
						yes = true;
						break;
					}
				}
				if (!yes)
					break;			
			}

			if (yes)
				bw.write("YES\n");
			else
				bw.write("NO\n");
		}

		bw.flush();
		bw.close();

	}

}