package 하_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_4963_섬의개수 {
    static int R;
    static int C;
    static int[][] map;
    static int[] dx={-1,1,0,0,-1,1,-1,1};
    static int[] dy={0,0,1,-1,1,1,-1,-1};
    static int cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            C = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            if (R == 0 && C == 0) return;
            map = new int[R][C];
            //1은 땅이고 0은 바다이다.
            cnt = 0;
            int result = 0;
            for (int i = 0; i < R; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < C; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j] == 1) cnt++;
                }
            }
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (cnt != 0 && map[i][j] == 1) {
                        result++;
                        dfs(i, j);
                    }
                }
            }
             System.out.println(result);


        }
    }
    private static void dfs(int x, int y) {
        map[x][y] = -1;
        cnt--;
        if(cnt==0) return;
        for (int i = 0; i < 8; i++) {
            int xx=x+dx[i];
            int yy=y+dy[i];
            if(0>xx||R-1<xx||0>yy||C-1<yy) continue;
            if(map[xx][yy]>0) {

                dfs(xx, yy);
            }
        }

    }
}
