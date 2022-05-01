package 백준_7주차_알고리즘스터디;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_G5_2671_조민수 {
	static int offset;
	static char [] sounds;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sounds = br.readLine().toCharArray();
		offset = 1;
		int idx = 0, end = sounds.length;
		while (idx < end) {
			if (isSecond(idx)) {
				idx += 2;
			}else if (isFirst(idx)) {
				idx += offset;
				offset = 1;
			}else {
				break;
			}
		}
		if (idx == end) {
			System.out.println("SUBMARINE");
		}else {
			System.out.println("NOISE");
		}
	}
	private static boolean isFirst(int idx) {
		int lastIdx = sounds.length - 1;
		//최소길이 4를 맞춰야 한다.
		if (idx + 2 >= lastIdx) return false;
		//현재 위치 값이 1이고 다음 위치 값이 0이라면 조건의 반은 맞다. 
		if (sounds[idx] == '1' && sounds[idx + offset] == '0') offset++;
		//조건의 반도 안 맞았으니 false
		else return false;
		//idx기준으로 2칸 떨어진 친구도 0이어야 하므로 (100~~~~~~~~~~~~~~~~~~~1)
		if (sounds[idx + offset] == '0') offset++;
		//idx 기준으로 2칸 떨어진 친구가 1이면 조건에 위배되므로
		else return false;
		//100 까지 맞췄으므로 그 후에는 0이 0부터 n까지 있을 것이고 1이면 반복문을 멈춘다 
		while (sounds[idx + offset] == '0') {
			//offset을 증가시켜준다.
			offset++;
			//만약 offset을 증가시키는데 배열의 끝을 만날때까지 1을 못만나면 조건에 위배되므로
			if (idx + offset == sounds.length) return false;
		}
		//여기에 왔다는 것은 1을 만난 상황이므로 1이 끝날때까지 offset을 증가 시킨다.
		while (idx + offset <= lastIdx && sounds[idx + offset] == '1') {
			offset++;
		}
		//여기에 왔다는 것은 배열의 끝에 왔거나 다시 0을 만난 상황이다.
		if (idx + offset + 1 < sounds.length && sounds[idx + offset] == '0' && sounds[idx + offset + 1] == '0') {
			if (sounds[idx + offset - 2] == '1') offset--;
			
		}
		return true;
	}
	private static boolean isSecond(int idx) {
		//두번째 신호는 길이가 2인데 앞자리의 idx가 마지막 idx라면 false
		if (idx == sounds.length - 1) return false;
		//앞자리의 값이 0이고 뒷자리의 값이 1이면 신호이고 이때만이 신호이므로
		if (sounds[idx] == '0' && sounds[idx + 1] == '1') return true;
		//여기에 오는 경우는 전부 신호가 아니다.
		return false;
	}

}
