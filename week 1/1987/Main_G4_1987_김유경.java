import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
    static int R,C;
    static char[][] board;
    static int[] dx={-1,1,0,0};
    static int[] dy={0,0,-1,1};
    static boolean[] visited;
    static int max;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new char[R][C];
        visited=new boolean[26];
        for (int i = 0; i < R; i++) {
            board[i] = br.readLine().toCharArray();
        }

            dfs(0,0,1);

        System.out.println(max);

    }
    static void dfs(int x,int y,int cnt){
        visited[board[x][y]-'A']=true;
        max=Math.max(max,cnt);
        //상하좌우 4번 반복
        for (int p = 0; p < 4; p++) {
            int xx=x+dx[p];
            int yy=y+dy[p];
            if(xx>-1&&xx<R&&yy>-1&&yy<C){
                if(visited[board[xx][yy]-'A']) continue;
                visited[board[xx][yy]-'A']=true;
                dfs(xx,yy,cnt+1);
                visited[board[xx][yy]-'A']=false;
            }


        }
    }
}
