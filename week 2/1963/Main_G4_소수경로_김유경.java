import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_G4_소수경로 {
    static int start;
    static int target;
    static boolean[] prime;
    static boolean[] visited;
    static class pp{
        int number;
        int depth;
        public pp(int number, int depth) {
            this.number = number;
            this.depth = depth;
        }
    }
    public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
              StringTokenizer st = new StringTokenizer(br.readLine());
              int T = Integer.parseInt(st.nextToken());
              for (int tc = 1; tc <= T; tc++) {
                  st = new StringTokenizer(br.readLine());
                  start =Integer.parseInt(st.nextToken());
                  target=Integer.parseInt(st.nextToken());
                  if(start ==target) {
                      System.out.println(0);
                  }else {
                      prime = new boolean[10000];
                      isPrimeNum();
                      visited = new boolean[10000];
                      Queue<pp> queue = new LinkedList<>();
                      //처음 수를 넣어준다.
                      queue.add(new pp(start, 0));
                      visited[start] = true;
                      boolean isFind = false;
                      while (!queue.isEmpty()) {
                          //현재 원소를 팝하고
                          pp current = queue.poll();
                          if (current.number == target && current.depth > 0) {
                              System.out.println(current.depth);
                              isFind = true;
                              break;
                          }
                          //숫자를 바꿔가면서 소수이면 큐에 넣는다.
                          for (int i = 0; i < 4; i++) {
                              for (int j = 0; j <= 9; j++) {
                                  if (i == 0 && j == 0) continue;               //앞자리
                                  int num = change(current.number, i, j);
                                  if (!visited[num] & !prime[num]) {
                                      queue.add(new pp(num, current.depth + 1));
                                      visited[num] = true;
                                  }
                                  //숫자의 i번째자리를 j로 바꿔줌
                                  //방문하지 않았고, 소수가 아니면 큐에 넣고 방문처리한다.
                              }
                          }

                      }
                      if (!isFind) {
                          System.out.println("Impossible");
                      }
                  }
              }
    }
    //에라토스테네스의 체
    static void isPrimeNum(){
        for (int i = 2; i <10000; i++) {
            if(!prime[i]){
                for (int j = 2*i; j <10000; j+=i) {
                    if(!prime[j]) prime[j]=true;
                }
            }
        }
    }
    static int change(int num, int i, int j){
        //num의 i번째 자리를 j로 바꿔준다.
        StringBuilder sb=new StringBuilder(String.valueOf(num));
        sb.setCharAt(i,(char)(j+'0'));
        return Integer.parseInt(sb.toString());

    }


}
