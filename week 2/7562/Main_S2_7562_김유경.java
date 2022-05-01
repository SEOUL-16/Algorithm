package 하_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/7562
public class Main_S2_7562_나이트의이동 {
    static int T;
    static int N;
    static int[][] map;
    static int[] night;
    static int[] target;
    static int[] dx={-2,-1,1,2,2,1,-1,-2};
    static int[] dy={1,2,2,1,-1,-2,-2,-1};
    static int result;
    static boolean find;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            map=new int[N][N];
            night=new int[2];
            target=new int[2];
            result=-1;
            find=false;
            //나이트의 위치
            st = new StringTokenizer(br.readLine());
            night[0]=Integer.parseInt(st.nextToken());
            night[1]=Integer.parseInt(st.nextToken());
            //목표 위치
            st = new StringTokenizer(br.readLine());
            target[0]=Integer.parseInt(st.nextToken());
            target[1]=Integer.parseInt(st.nextToken());

            BFS(night[0],night[1]);
            System.out.println(result);

        }
    }

    private static void BFS(int x, int y) {
        Queue<int[]> q=new LinkedList<>();
        q.add(new int[]{x,y});
        map[x][y]=1;
        while(!q.isEmpty()&&!find) {
            int size = q.size();
            result++;
            while (--size >= 0) {
                int[] now = q.poll();
                if(now[0]==target[0]&&now[1]==target[1]){ find=true; break;}
                for (int i = 0; i < 8; i++) {
                    int xx = now[0] + dx[i];
                    int yy = now[1] + dy[i];
                    if (0 > xx || xx > N - 1 || 0 > yy || yy > N - 1) continue;
                    if (map[xx][yy] > 0) continue;
                    q.add(new int[]{xx, yy});
                    map[xx][yy] = 1;
                }
            }

        }
    }
}
