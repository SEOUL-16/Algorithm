package 하_스택;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_G4_17298_오큰수 {
    static int T;
    static class Data{
        int num;
        int pos;

        public Data(int num, int pos) {
            this.num = num;
            this.pos = pos;
        }
    }
    static int[] answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        StringBuilder sb=new StringBuilder();
        answer=new int[T];
        Arrays.fill(answer,-1);
        Stack<Data> stack=new Stack<>();
        //N개의 수 입력
        st = new StringTokenizer(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            int next=Integer.parseInt(st.nextToken());

            while(!stack.isEmpty()&&stack.peek().num<next){
              answer[stack.peek().pos]=next;
                stack.pop();
            }
            stack.push(new Data(next,tc));
        }
        for(int a:answer){
            sb.append(a).append(" ");
        }
        System.out.println(sb.toString());

    }
}
