package 하_스택;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_6198_옥상정원 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        Stack<Integer> stack=new Stack<>();
        long sum=0;
        for (int i = 0; i < N; i++) {
            int now=Integer.parseInt(br.readLine());

            while(!stack.isEmpty()&&stack.peek()<=now){
                stack.pop();
            }
            int size=stack.size();
            sum=sum+size;
//            while(!stack.isEmpty()&&stack.peek()==now){
//                stack.pop();
//            }
            stack.push(now);
        }
        System.out.println(sum);

    }
}
