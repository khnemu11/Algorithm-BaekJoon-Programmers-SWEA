import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 풀이 알고리즘
 * 
 * 목표) 얻을 수 있는 점수의 최대값
 * 
 * 주어진 타자들의 이닝 별 예상 타격을 조합하여 점수의 최대값 출력
 * 이때 1번째로 들어오는 선수는 무조건 4번타자가 된다.
 * 
 * 현재 주루 상황을 큐로 구현하여 사람이 들어오면 1, 안들어오면 0으로 표현
 * 
 * 
 * 걸린시간 : 15분
 */

public class Main {
	static Queue<Integer> q = new LinkedList<>();
	static int results[][]; // 선수의 타격 결과
	static int selected[]; // 선수의 출루 순서
	static boolean visited[]; // 선수를 이미 골랐는지 방문 여부
	static int maxScore = 0; // 최대 점수값

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.valueOf(br.readLine());
		results = new int[N + 1][10];
		selected = new int[10];
		visited = new boolean[10];

		for (int i = 1; i < results.length; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j < results[0].length; j++) {
				results[i][j] = Integer.valueOf(st.nextToken());
			}
		}
		selected[4] = 1;
		visited[4] = true;
		selectPlayer(2); // 2번 타자부터 순서 선택

		bw.write(maxScore + "\n");
		bw.flush(); // 결과 출력
		bw.close();
	}

	public static void selectPlayer(int idx) { // 타자 순서를 고르는 메소드 (현재 순번)
		if (idx == selected.length) { // 다 골랐으면 게임 실행
			playGame(selected);
		} else { // 선택한 선수의 인덱스를 해당 순번에 저장
			for (int i = 1; i < selected.length; i++) {
				if (visited[i]) {
					continue;
				}
				visited[i] = true;
				selected[i] = idx;
				selectPlayer(idx + 1);
				visited[i] = false;
			}
		}
	}

	public static void playGame(int players[]) { // 야구 시작
		int totalScore = 0;
		int seq = 1; // 현재 타격 번수

		for (int round = 1; round < results.length; round++) {
			int roundScore = 0;
			int out = 0;
			boolean base[] = new boolean[4];

			while (out < 3) {
				int result = results[round][players[seq]]; // 선택된 선수의 타격 결과

				if (result == 0) {
					out++;
				} else if (result == 1) {
					if (base[3] == true) {
						roundScore++;
						base[3] = false;
					}
					if (base[2] == true) {
						base[3] = true;
						base[2] = false;
					}
					if (base[1] == true) {
						base[2] = true;
						base[1] = false;
					}

					base[1] = true;
				} else if (result == 2) {
					if (base[3] == true) {
						roundScore++;
						base[3] = false;
					}
					if (base[2] == true) {
						roundScore++;
						base[2] = false;
					}
					if (base[1] == true) {
						base[3] = true;
						base[1] = false;
					}
					base[2] = true;

				} else if (result == 3) {
					for (int i = 1; i <= 3; i++) {
						if (base[i]) {
							roundScore++;
							base[i] = false;
						}
					}
					base[3] = true;
				} else if (result == 4) {
					for (int i = 1; i <= 3; i++) {
						if (base[i]) {
							roundScore++;
							base[i] = false;
						}
					}
					roundScore++;
				}

				seq = seq + 1 >= players.length ? 1 : seq + 1;
			}

			totalScore += roundScore;
		}

		maxScore = Math.max(totalScore, maxScore);
	}
}