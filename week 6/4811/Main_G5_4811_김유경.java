import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
    static int N;
    static long[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    while (true){
        N=Integer.parseInt(br.readLine());
        if(N==0)
            return;

        dp=new long[N+1][N+1];

        for (int H = 0; H < N + 1; H++) {
            for (int W = 1; W < N+1; W++) {
                if(H==0) { dp[W][H]=1; continue; }         //H를 0개 고르는 경우의 수는 1가지
                if(W<H) continue;               //H는 W의 개수보다 많을 수 없음.
                dp[W][H]=dp[W][H-1]+dp[W-1][H];
            }
        }

        System.out.println(dp[N][N]);
    }
    }
}
