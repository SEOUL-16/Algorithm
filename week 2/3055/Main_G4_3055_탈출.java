package 하_BFS;
//https://www.acmicpc.net/problem/3055
//BFS 탐색문제
//visited 배열을 썼을때와 쓰지 않았을때의 차이를 느껴볼 것.


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_G4_3055_탈출 {
    static int R;
    static int C;
    static char map[][];
    static int visited[][];
    static Queue<int[]> q;
    static int beaverX;
    static int beaverY;
    static int goX;
    static int goY;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static String result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        visited = new int[R][C];
        q = new LinkedList<>();
        //map셋팅
        for (int i = 0; i < R; i++) {
            String string = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = string.charAt(j);
                if (map[i][j] == 'D') {
                    beaverX = i;
                    beaverY = j;
                } else if (map[i][j] == 'S') {
                    goX = i;
                    goY = j;
                } else if (map[i][j] == '*') {
                    q.add(new int[]{0, i, j, 0});
                    visited[i][j] = 1;
                }
            }
        }
        int result = bfs();
        if (result < 0)
            System.out.println("KAKTUS");
        else
            System.out.println(result);
    }

    private static int bfs() {
        q.add(new int[]{99, goX, goY, 0});
        while (!q.isEmpty()) {
            int now[] = q.poll();
            if (now[1] == beaverX && now[2] == beaverY) {
                if (now[0] == 99) {
                    return now[3];
                }
            } else {
                //고슴도치
                if (now[0] == 99) {
                    for (int i = 0; i < 4; i++) {
                        int xx = now[1] + dx[i];
                        int yy = now[2] + dy[i];
                        //고슴도치는 .일때와 D일때만 갈 수 있음
                        if (xx < 0 || R - 1 < xx || yy < 0 || C - 1 < yy)
                            continue;
                        if (map[xx][yy] == '.' || map[xx][yy] == 'D') {
                            map[xx][yy] = 'S';
                            q.add(new int[]{99, xx, yy, now[3] + 1});
                        }
                    }

                } else {
                    for (int i = 0; i < 4; i++) {
                        int xx = now[1] + dx[i];
                        int yy = now[2] + dy[i];
                        //물은 X와 *만 아니면 갈 수 있음. -> S,. 됨
                        if (xx < 0 || R - 1 < xx || yy < 0 || C - 1 < yy) continue;
                        if (map[xx][yy] == '.' || map[xx][yy] == 'S') {
                            map[xx][yy] = '*';
                            q.add(new int[]{0, xx, yy, 0});
                        }
                    }
                }
            }

        }
        return -1;

    }
}
