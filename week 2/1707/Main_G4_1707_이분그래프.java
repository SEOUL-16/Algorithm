package 하_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_G4_1707_이분그래프 {
    static int T;
    static int V;
    static int E;
    static int[] check;
    static LinkedList<Integer>[] list;
    static String answer;
    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("src/하_BFS/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        StringBuilder sb=new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            list=new LinkedList[V+1];
            check=new int[V+1];
            for (int i = 1; i < V+1; i++) {
                list[i]=new LinkedList<Integer>();
            }
            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to= Integer.parseInt(st.nextToken());
                list[from].add(to);
                list[to].add(from);
            }
            //1번 정점부터 BFS를 돌면서 인접한 정점에 다른색 칠하기.
            //인접한 정점에 같은 색이 칠해져 있는지 찾는 BFS
            answer="YES";
            for (int i = 1; i <V+1 ; i++) {
                if(check[i]==0){
                    if(!BFS(i)) {
                        answer="NO";
                        break;
                    }
                }

            }
            System.out.println(answer);
        }

    }

    private static boolean BFS(int x) {
        Queue<Integer> q=new LinkedList<>();
        q.add(x);
        check[x]=1;
        while (!q.isEmpty()){
            int now=q.poll();
            for(int temp:list[now]){
                if(check[now]==check[temp]) return false;

                if(check[temp]==0){
                    check[temp]=check[now]*-1;
                    q.add(temp);
                }
            }
        }
        return true;
    }
}