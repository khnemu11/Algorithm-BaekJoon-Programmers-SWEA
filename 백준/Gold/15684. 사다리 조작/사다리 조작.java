import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*풀이 알고리즘
 * 모든 경로 탐색 최악의 경우 : (N-1)*H C 3 + (N-1)*H C 2 + (N-1)*H C 1 = 3,244,140 + 36,315 + 270
 * 사다리의 모든 경우의 수를 0개,1개,2개,3개 넣는 경우를 순서대로 구하고 
 * 전부 i->i로 갈 수 없으면 -1를 출력, 갈 수 있으면 해당 사다리 수의 개수를 출력하고 종료
 * 
 * */

public class Main {
	static boolean ladder[][][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.valueOf(st.nextToken());
		int M = Integer.valueOf(st.nextToken());
		int H = Integer.valueOf(st.nextToken());

		ladder = new boolean[H + 1][N + 1][N + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int height = Integer.valueOf(st.nextToken());
			int from = Integer.valueOf(st.nextToken());
			int to = from + 1;

			ladder[height][from][to] = true;	//사다리 세우기
			ladder[height][to][from] = true;
		}
		for (int cnt = 0; cnt <= 4; cnt++) {
			if (cnt == 4) {
				bw.write("-1\n");
			} else {
				if (pick(0, 1, 1, cnt)) {
					bw.write(cnt + "\n");
					break;
				}
			}
		}

		bw.flush();
	}

	public static boolean pick(int count, int height, int from, int max_count) {
		if (count == max_count) {
			for (int f = 1; f < ladder[0].length; f++) { // 출발지
				int to = f; // 도착지
				for (int h = 1; h < ladder.length; h++) {
					if (ladder[h][to][to - 1]) {
						to = to - 1;
					} else if (to + 1 < ladder[0].length && ladder[h][to][to + 1]) {
						to = to + 1;
					}
				}
				if (to != f) {	//출발지랑 도착지가 다르면 해당 경우는 안됨
					return false;
				}
			}

			return true;
		}
		for (int h = height; h < ladder.length; h++) {
			for (int f = height == h ? from : 1; f < ladder[0].length - 1; f++) { // 현재 위치에서 다음 사다리로 이동
				if (ladder[h][f][f - 1] || ladder[h][f][f + 1]) { // 이미 사다리가 있거나 왼쪽에 사다리가 있는 경우
					continue;
				}
				ladder[h][f][f + 1] = true; // from -> to 사다리 세우기
				ladder[h][f + 1][f] = true; // to -> from 사다리 세우기
				if (pick(count + 1, h, f + 1, max_count)) {
					return true;
				} else {
					ladder[h][f][f + 1] = false;
					ladder[h][f + 1][f] = false;
				}
			}
		}
		return false;
	}
}