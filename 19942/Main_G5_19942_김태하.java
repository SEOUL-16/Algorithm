package boj;

import java.io.*;
import java.util.*;

public class Main_G5_19942_김태하 {
	/*
	 * 모든 경우의 수를 확인한다.
	 * 		선택된 재료들의 영양소 합이 기준을 충족할 경우
	 * 			현재 비용이 최소 비용보다 작으면 최소 비용을 갱신한다.
	 */
	
	static int N, minPrice, mp, mf, mc, mv;
	static Ingredient[] ingredients;
	static boolean[] selected;
	static PriorityQueue<Ingredient> pq = new PriorityQueue<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		minPrice = Integer.MAX_VALUE;
		
		ingredients = new Ingredient[N];
		selected = new boolean[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		mp = Integer.parseInt(st.nextToken());
		mf = Integer.parseInt(st.nextToken());
		mc = Integer.parseInt(st.nextToken());
		mv = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int protein = Integer.parseInt(st.nextToken());
			int fat = Integer.parseInt(st.nextToken());
			int carb = Integer.parseInt(st.nextToken());
			int vit = Integer.parseInt(st.nextToken());
			int price = Integer.parseInt(st.nextToken());
			
			ingredients[i] = new Ingredient(i + 1, protein, fat, carb, vit, price);
		}
		
		
		dfs(0, 0);
		
		if (minPrice == Integer.MAX_VALUE) {
			System.out.println(-1);
			return;
		}
		
		System.out.println(minPrice);
		
		while (!pq.isEmpty()) {
			System.out.print(pq.poll().num + " ");
		}
	}
	
	private static void dfs(int cnt, int curPrice) {
		if (curPrice > minPrice) {
			return;
		}
		
		if (cnt == N || passed()) {
			if (curPrice < minPrice && passed()) {
				minPrice = curPrice;
				pq.clear();
				
				for (int i = 0; i < N; i++) {
					if (selected[i]) {
						pq.offer(ingredients[i]);
					}
				}
			}
			return;
		}
		
		// 재료를 선택했을 때
		selected[cnt] = true;
		dfs(cnt + 1, curPrice + ingredients[cnt].price);
		
		// 재료를 선택하지 않았을 때
		selected[cnt] = false;
		dfs(cnt + 1, curPrice);
	}

	private static boolean passed() {
		int nprotein = 0;
		int nfat = 0;
		int ncarb = 0;
		int nvit = 0;
		
		for (int i = 0; i < N; i++) {
			if (selected[i]) {
				nprotein += ingredients[i].protein;
				nfat += ingredients[i].fat;
				ncarb += ingredients[i].carb;
				nvit += ingredients[i].vit;
			}
		}
		
		if (nprotein >= mp && nfat >= mf && ncarb >= mc && nvit >= mv) {
			return true;
		}
		
		return false;
	}

	static class Ingredient implements Comparable<Ingredient>{

		int num, protein, fat, carb, vit, price;

		public Ingredient(int num, int protein, int fat, int carb, int vit, int price) {
			super();
			this.num = num;
			this.protein = protein;
			this.fat = fat;
			this.carb = carb;
			this.vit = vit;
			this.price = price;
		}
		
		@Override
		public int compareTo(Ingredient o) {
			return this.num - o.num;
		}
	}
}
