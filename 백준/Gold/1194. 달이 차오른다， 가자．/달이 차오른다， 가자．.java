import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/* 
	풀이 과정
	
	열쇠를 먹은 상태를 저장할 필요가 있음
	=>비트마스킹으로 현재 열쇠를 가지고 있는 상태를 표현
	
	방문배열 = [열쇠 비트][행의 크기][열의 크기]
	방문 배열에 맞추어 bfs 탐색해 가장 먼저 1에 도착한 시간 출력
*/
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int H = Integer.valueOf(st.nextToken());
		int W = Integer.valueOf(st.nextToken());

		int visited[][][] = new int[(1 << 6)][H][W];
		boolean exist[] = new boolean[26];
		for (int i = 0; i < visited.length; i++) {
			for (int j = 0; j < visited[0].length; j++) {
				Arrays.fill(visited[i][j], Integer.MAX_VALUE);
			}
		}

		char map[][] = new char[H][W];

		People start = null;

		for (int i = 0; i < H; i++) {
			char row[] = br.readLine().toCharArray();
			for (int j = 0; j < W; j++) {
				map[i][j] = row[j];
				if (map[i][j] == '0') {
					start = new People(i, j, 0, 0);
				}
				if (map[i][j] >= 'A' && map[i][j] <= 'F') {
					exist[map[i][j] - 'A'] = true;
				}
			}
		}

		PriorityQueue<People> q = new PriorityQueue<>();
		q.add(start);
		int time = -1;
		int dx[] = { -1, 1, 0, 0 };
		int dy[] = { 0, 0, -1, 1 };

		while (!q.isEmpty()) {
			People curr = q.poll();
			visited[curr.keyCode][curr.row][curr.col] = curr.time;
			if (map[curr.row][curr.col] == '1') {
				time = curr.time;
				break;
			}

			for (int k = 0; k < dx.length; k++) {
				People next = new People(curr.row + dx[k], curr.col + dy[k], curr.keyCode, curr.time + 1);
				if (next.row < 0 || next.col < 0 || next.row >= map.length || next.col >= map[0].length) {
					// 맵을 벗어나는 경우
					continue;
				} else if (visited[next.keyCode][next.row][next.col] < next.time) {
					// 이미 방문한 경우
					continue;
				} else if (map[next.row][next.col] == '#') {
					// 벽인 경우
					continue;
				} else if (map[next.row][next.col] >= 'A' && map[next.row][next.col] <= 'F') {
					// 문인 경우
					if ((next.keyCode & (1 << (Character.toLowerCase(map[next.row][next.col]) - 'a'))) == 0) {
						// 문의 열쇠를 가지고 있지 않은 경우
						continue;
					} else {
						// 문의 열쇠를 가지고 있는 경우
						visited[next.keyCode][next.row][next.col] = curr.time;
						q.add(next);
					}
				} else if (map[next.row][next.col] >= 'a' && map[next.row][next.col] <= 'f') {
					// 열쇠인 경우
					if (exist[Character.toUpperCase(map[next.row][next.col]) - 'A']) {
						next.keyCode = next.keyCode | (1 << (map[next.row][next.col] - 'a')); // 열쇠를 or 연산으로 먹기
					}
					visited[next.keyCode][next.row][next.col] = curr.time;
					q.add(next);
				} else {
					// 나머지 경우 (빈칸, 목적지)
					visited[next.keyCode][next.row][next.col] = curr.time;
					q.add(next);
				}
			}
		}
		bw.write(time + "\n");
		bw.flush();
	}
}

class People implements Comparable<People> {
	int row;
	int col;
	int keyCode;
	int time;

	public People() {
	}

	public People(int row, int col, int keyCode, int time) {
		this.row = row;
		this.col = col;
		this.keyCode = keyCode;
		this.time = time;
	}

	@Override
	public int compareTo(People o) {
		return this.time - o.time;
	}
}
