import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_2468_안전영역 {
    static int N;
    private static int[][] map;
    private static boolean[][] visitid;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        visitid = new boolean[N][N];
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (list.contains(map[i][j])) continue;
                list.add(map[i][j]);
            }
        }
        if(list.size()==1){
            System.out.println(1);
            return;
        }
        Collections.sort(list);
        int cnt;
        result=1;
        for (int k = 0; k < list.size(); k++) {
            cnt = 0;
            for (int i = 0; i < N; i++) {
            Arrays.fill(visitid[i],false);
            }
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(map[i][j]<=list.get(k)) continue;
                    if (!visitid[i][j]) {
                        cnt++;
                        dfs(list.get(k), i, j);
                    }
                }
            }
            result=Math.max(result,cnt);
        }
        System.out.println(result);
    }

    private static void dfs(int H, int i, int j) {
        visitid[i][j] = true;
        for (int k = 0; k < 4; k++) {
            int xx = i + dx[k];
            int yy = j + dy[k];
            if (xx < 0 || N - 1 < xx || yy < 0 || N - 1 < yy || map[xx][yy] <= H || visitid[xx][yy]) continue;
            dfs(H, xx, yy);
        }

    }
}
