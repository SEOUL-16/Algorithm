package com.algo.season2.fifth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_S1_1309_이지현 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int size = Integer.parseInt(br.readLine());

		int[] caseArr = new int[] { 1, 1, 1 }; // □□ ■□ □■

		int temp0, temp1, temp2;
		for (int i = 1; i < size; i++) {
			temp0 = caseArr[0] % 9901; // 직전 상태의 □□
			temp1 = caseArr[1] % 9901; // 직전 상태의 ■□
			temp2 = caseArr[2] % 9901; // 직전 상태의 □■

			caseArr[0] = temp0 + temp1 + temp2; // □□가 될 수 있는 경우는 직전 상태가 무엇이든
			caseArr[1] = temp0 + temp2; // ■□가 될 수 있는 경우는 직전 상태가 □□거나 □■여야
			caseArr[2] = temp0 + temp1; // □■ 될 수 있는 경우는 직전 상태가 □□거나 ■□여야
		}

		System.out.println((caseArr[0] + caseArr[1] + caseArr[2]) % 9901);
	}

}
