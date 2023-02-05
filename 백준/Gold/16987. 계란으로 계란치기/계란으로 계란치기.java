import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
	풀이 알고리즘
    왼쪽부터 순서대로 계란을 선택하므로 완전 탐색시 최대 8^8 시간 복잡도
    깨려는 계란이 내구성이 없는 경우 스킵하고 전부다 스킵한 경우에만 다음 계란 선택 dfs로 넘어감
    최종 탐색 시 내구성이 0인 개수를 세서 최대값 설정
*/

public class Main {
	static Egg eggs[];
	static boolean visited[];
	static int max;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.valueOf(br.readLine());

		eggs = new Egg[N];
		visited = new boolean[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			eggs[i] = new Egg(Integer.valueOf(st.nextToken()), Integer.valueOf(st.nextToken()));
		}

		breakEggs(0);
		bw.write(max + "\n");

		bw.flush();
	}

	public static void breakEggs(int pick) {
		if (pick >= eggs.length) {
			int cnt = 0;

			for (int i = 0; i < eggs.length; i++) {
				if (eggs[i].durability <= 0) {
					cnt++;
				}
			}

			max = Math.max(max, cnt);
		} else if (eggs[pick].durability <= 0) {
			breakEggs(pick + 1);
		} else {
			boolean canBreak = false;
			for (int target = 0; target < eggs.length; target++) {
				if (eggs[target].durability <= 0 || target == pick) {
					continue;
				}
				canBreak = true;
				eggs[pick].durability -= eggs[target].weight;
				eggs[target].durability -= eggs[pick].weight;

				breakEggs(pick + 1);

				eggs[pick].durability += eggs[target].weight;
				eggs[target].durability += eggs[pick].weight;
			}
			if (!canBreak) {
				breakEggs(pick + 1);
			}
		}
	}
}

class Egg {
	int weight;
	int durability;

	public Egg(int durability, int weight) {
		this.weight = weight;
		this.durability = durability;
	}
}
