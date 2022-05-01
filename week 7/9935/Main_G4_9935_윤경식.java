import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String str = br.readLine();
		String bomb = br.readLine();
		int strLength = str.length();
		int bombLength = bomb.length();

		Stack<Character> stack = new Stack<>();
		loop: for (int i = 0; i < strLength; i++) {
			char temp = str.charAt(i);
			stack.push(temp);
			if (stack.size() >= bombLength) {
				int idx = stack.size() - bombLength;
				for (int j = 0; j < bombLength; j++) {
					if (stack.get(idx + j) != bomb.charAt(j))
						continue loop;
				}

				for (int j = 0; j < bombLength; j++) {
					stack.pop();
				}
			}
		}
		if (stack.size() == 0)
			bw.write("FRULA");
		else {
			for (int i = 0, end = stack.size(); i < end; i++) {
				bw.write(String.valueOf(stack.get(i)));
			}
		}

		bw.flush();
		bw.close();

	}

}