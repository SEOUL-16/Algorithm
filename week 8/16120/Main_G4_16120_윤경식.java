import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Main {

	static int N;
	static int M;
	static int[] namoo;
	static int Min;
	static int Max;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String str = br.readLine();
		boolean ppap = true;
		Stack<Character> stack = new Stack<>();
		for (int i = 0, end = str.length(); i < end; i++) {
			char temp = str.charAt(i);
			if (temp == 'P') {
				stack.push('P');
				continue;
			} else if (temp == 'A') {
				int size = stack.size();
				if (size - 2 >= 0 && size + 1 < end && stack.get(size - 1) == 'P' && stack.get(size - 2) == 'P'
						&& str.charAt(i + 1) == 'P') {
					stack.pop();
					stack.pop();
					stack.push('P');
					i++;
					continue;
				} else {
					ppap = false;
					break;
				}
			}
		}
		if (ppap && stack.size() == 1 && stack.peek() == 'P')
			bw.write("PPAP");
		else {
			bw.write("NP");
		}
		bw.flush();
		bw.close();

	}

}