package 하_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2683_치즈 {
    static int C;
    static int R;
    static int[][] map;
    static int[] dx={-1,1,0,0};
    static int[] dy={0,0,-1,1};
    static int cnt;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map=new int[R][C];
        cnt=0;
        result=0;
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
            map[i][j]=Integer.parseInt(st.nextToken());
            if(map[i][j]==1) cnt++;
            }
        }
        //외부공기 찾기
        BFS(0,0);
        //노출된 치즈 찾기
        while(cnt!=0) {
            find();
            //노출된 치즈 공기로 바꾸기
            change();
        }
        System.out.println(result);
    }

    private static void change() {
        result++;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(map[i][j]==9){
                    BFS(i,j);
                    cnt--;
                }
            }
        }
    }

    private static void find() {
        int count;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(map[i][j]==1){
                    count=0;
                    for (int k = 0; k < 4; k++) {
                        int xx=i+dx[k];
                        int yy=j+dy[k];
                        if(map[xx][yy]==3){
                            count++;
                        }
                    }
                    if(count>1){
                        map[i][j]=9;
                    }
                }
            }
        }
    }

    private static void BFS(int x, int y) {
        Queue<int []> queue=new LinkedList<>();
        queue.add(new int[]{x,y});
        map[x][y]=3;
        while (!queue.isEmpty()){
            int[] now= queue.poll();
            for (int i = 0; i < 4; i++) {
                int xx=now[0]+dx[i];
                int yy=now[1]+dy[i];
                if(xx<0||R-1<xx||yy<0||C-1<yy) continue;
                if(map[xx][yy]>0) continue;
                map[xx][yy]=3;
                queue.add(new int[]{xx,yy});
            }
        }
    }
}
