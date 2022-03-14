import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_11724_연결요소의개수_BFS {
static int M;
static int N;
static List<Integer>[] list;
static boolean[] visitid;
static int result=0;
public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    list=new LinkedList[N+1];
    visitid=new boolean[N+1];
    for (int i = 1; i <= N; i++) {
         list[i]=new LinkedList<>();
    }
    for (int i = 0; i < M; i++) {
        st = new StringTokenizer(br.readLine());
        int from=Integer.parseInt(st.nextToken());
        int to=Integer.parseInt(st.nextToken());
        list[from].add(to);
        list[to].add(from);
    }
    bfs();
    System.out.println(result);
}
    private static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        for (int k = 1; k <N+1; k++) {
            if(visitid[k]) continue;
            else {
                q.add(k);
                visitid[k] = true;
                result++;
                while (!q.isEmpty()) {
                    int temp = q.poll();
                    for (int i = 0; i < list[temp].size(); i++) {
                        if (!visitid[list[temp].get(i)]) {
                            q.add(list[temp].get(i));
                            visitid[list[temp].get(i)]=true;
                        }
                    }
                }
            }
        }
    }
}
