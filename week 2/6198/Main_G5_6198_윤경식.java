import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Main {
	
	static int N;
	static int[] map;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		map = new int[N];
		
		for(int i=0;i<N;i++) {
			map[i] = Integer.parseInt(br.readLine());
		}
		
		long count = 0;
		Stack<Integer> stack = new Stack<>();
		stack.push(map[0]);
		for(int i=1;i<N;i++) {
			while(!stack.isEmpty() && stack.peek() <= map[i])
				stack.pop();
			count+= stack.size();
			stack.push(map[i]);
		}
		
		bw.write(String.valueOf(count));
		bw.flush();
		bw.close();
	}
}