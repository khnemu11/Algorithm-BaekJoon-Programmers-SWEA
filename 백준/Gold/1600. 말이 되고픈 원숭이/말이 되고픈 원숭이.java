package bj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/* 
 * 	풀이 알고리즘
 * 	
 * 최대 K번까지 체스 말처럼 움직임
 * K번이 지나면 4방향만 가능 
 * 말 움직임으로 움직였을 때 방문처리와 일반 4방향 이동 방향 이동 처리 필요
 * 말 움직임으로 움직였을 때 말움직임의 횟수 만큼 따로 방문처리 필요
 * dp로 가장 먼저 도착한 것을 우선으로 하여 방문처리
 * */

public class Main {
	static int map[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.valueOf(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		int width = Integer.valueOf(st.nextToken());
		int height = Integer.valueOf(st.nextToken());

		map = new int[height][width];
		int time[][][] = new int[N + 1][height][width];

		for (int i = 0; i < height; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < width; j++) {
				map[i][j] = Integer.valueOf(st.nextToken());
			}
		}
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j < height; j++) {
				Arrays.fill(time[i][j], Integer.MAX_VALUE);
			}
		}
		PriorityQueue<Monkey> pq = new PriorityQueue<>();
		pq.add(new Monkey(0, 0, 0));

		int cnt = -1;

		int rowMove[] = { -1, 1, 0, 0 };
		int colMove[] = { 0, 0, -1, 1 };

		int horseRowMove[] = { -2, -2, -1, -1, 1, 1, 2, 2 };
		int horseColMove[] = { -1, 1, -2, 2, -2, 2, -1, 1 };

		while (!pq.isEmpty()) {
			Monkey curr = pq.poll();
			if (curr.row == map.length - 1 && curr.col == map[0].length - 1) { // 목표 위치에 도착하면 반복문 탈출
				cnt = curr.cnt;
				break;
			}
			for (int k = 0; k < rowMove.length; k++) { // 4방향 이동
				Monkey next = new Monkey(curr.row + rowMove[k], curr.col + colMove[k], curr.horseMoveCnt, curr.cnt + 1);
				if (next.row < 0 || next.col < 0 || next.row >= map.length || next.col >= map[0].length) {
					continue;
				}
				if (map[next.row][next.col] == 1) {
					continue;
				}
				if (time[next.horseMoveCnt][next.row][next.col] > next.cnt) {
					time[next.horseMoveCnt][next.row][next.col] = next.cnt;
					pq.add(next);
				}
			}
			if (curr.horseMoveCnt < N) { // 말 움직임이 가능하면 카운트 하나를 늘리고 말 8방향 움직임
				for (int k = 0; k < horseRowMove.length; k++) {
					Monkey next = new Monkey(curr.row + horseRowMove[k], curr.col + horseColMove[k],
							curr.horseMoveCnt + 1, curr.cnt + 1);
					if (next.row < 0 || next.col < 0 || next.row >= map.length || next.col >= map[0].length) {
						continue;
					}
					if (map[next.row][next.col] == 1) {
						continue;
					}
					if (time[next.horseMoveCnt][next.row][next.col] > next.cnt) {
						time[next.horseMoveCnt][next.row][next.col] = next.cnt;
						pq.add(next);
					}
				}
			}
		}
		bw.write(cnt + "\n");
		bw.flush();
	}
}

class Monkey implements Comparable<Monkey> {
	int row;
	int col;
	int horseMoveCnt;
	int cnt;

	public Monkey(int row, int col, int horseMoveCnt) {
		this.row = row;
		this.col = col;
		this.horseMoveCnt = horseMoveCnt;
	}

	public Monkey(int row, int col, int horseMoveCnt, int cnt) {
		this.row = row;
		this.col = col;
		this.horseMoveCnt = horseMoveCnt;
		this.cnt = cnt;
	}

	@Override
	public String toString() {
		return "Monkey [row=" + row + ", col=" + col + ", horseMoveCnt=" + horseMoveCnt + ", cnt=" + cnt + "]";
	}

	@Override
	public int compareTo(Monkey o) {
		return this.cnt - o.cnt;
	}
}
