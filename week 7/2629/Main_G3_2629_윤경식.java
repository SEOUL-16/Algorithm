import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

//		추의 개수 입력
		int chu = Integer.parseInt(br.readLine());
//		구슬의 무게가 최대 40000이기 때문에 0~40000까지의 가능성을 표기할 수 있는 배열 선택
		boolean[] chuDP = new boolean[40000 + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		int temp;
//		각 추를 저울의 왼쪽, 오른쪽에 놓는 경우와 아무 곳에도 놓지 않는 경우를 고려해야 함
//		왼쪽에 놓는 경우를 -, 오른쪽에 놓는 경우를 +로 가정하여 chuDP배열에 가능한 무게를 표기
//		계산한 무게가 0보다 작을 경우는 절댓값 처리하여 범위 안에 있는지 확인 후 표기해야 함
//		ex) 무게가 1,4 추 2개를 왼쪽에 동시에 놓는 경우 = 무게가 1,4인 추 2개를 오른쪽에 동시에 놓는 경우
//		첫 번째 추는 저울에 올리고 내리고의 경우의 수만 고려
		chuDP[0] = true;
		chuDP[Integer.parseInt(st.nextToken())] = true;
//		두 번째 추부터 기존의 가능했던 무게들을 대상으로 가능한 무게 계산
		for (int i = 1; i < chu; i++) {
			temp = Integer.parseInt(st.nextToken());
//			계산 과정에서 섞일 수 있기 때문에(현재 추까지 고려해버린 무게에 또 추가로 계산하는 경우)
//			list에 가능한 무게를 표기해두고 마지막에 배열에 반영
			LinkedList<Integer> list = new LinkedList<>();
			for (int j = 0; j <= 40000; j++) {
//				고려 대상인 무게라면
				if (chuDP[j]) {
//					왼쪽에 올린 경우
					int num1 = Math.abs(j - temp);
//					오른쪽에 올린 경우
					int num2 = j + temp;
//					둘 다 범위 내에 있다면 list에 추가, 범위 밖이라면 pass
					if (num1 >= 0 && num1 <= 40000 && !chuDP[num1])
						list.add(num1);
					if (num2 >= 0 && num2 <= 40000 && !chuDP[num2])
						list.add(num2);
				}
			}
//			계산 가능한 무게들 배열에 표기
			for (int j = 0, end = list.size(); j < end; j++) {
				chuDP[list.get(j)] = true;
			}
		}

//		구슬의 개수 입력
		int ball = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
//		구슬의 개수만큼 반복문
		for (int i = 0; i < ball; i++) {
//			해당 구슬의 무게의 인덱스가 배열에 true 표기 되어있다면(계산 가능한 무게라면) Y, false라면 N 출력
			if (chuDP[Integer.parseInt(st.nextToken())])
				bw.write("Y ");
			else
				bw.write("N ");
		}
		bw.flush();
		bw.close();
	}

}