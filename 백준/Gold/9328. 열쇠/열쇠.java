import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
    풀이 알고리즘
   	가장 자리가 도착이자 시작 위치이므로 가장자리 위치를 모두 저장
   	가장자리 부터 4방향 bfs를 실행하여
   	1) $로 바로 갈 수 있으면 개수 증가
   	2) 대문자 알파벳을 만나면 다음 시작 위치 좌표 큐에 저장
   	3) 소문자 알파벳을 만나면 소문자 방문 배열에 저장 
   	가장자리 탐색이 모두 끝나면 다음 시작 위치 큐부터 4방향 bfs 실행
   	큐의 대문자 알파벳의 해당되는 소문자 알파벳을 방문 했으면 탐색이 가능하므로 대문자 알파벳부터 가장자리와 동일하게 4방향 bfs 실행
   	만약 큐를 한번 다 썼는데 소문자 키를 한번도 사용하지 않았다면 더이상 이동 불가능하다고 판정 후 결과 출력
   	
*/

public class Main {
	static int treasures;
	static int upDown[] = { -1, 1, 0, 0 };
	static int leftRight[] = { 0, 0, -1, 1 };
	static boolean visited[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.valueOf(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int height = Integer.valueOf(st.nextToken());
			int width = Integer.valueOf(st.nextToken());
			char map[][] = new char[height][width];
			visited = new boolean[height][width];
			Queue<Location> q = new LinkedList<>();
			treasures = 0;
			Queue<Location> doorsQ = new LinkedList<>();
			boolean keys[] = new boolean[26];

			// 맵 데이터 저장
			for (int i = 0; i < height; i++) {
				char elements[] = br.readLine().toCharArray();
				for (int j = 0; j < width; j++) {
					map[i][j] = elements[j];
					if ((i == 0 || j == 0 || i == height - 1 || j == width - 1) && map[i][j] != '*') { // 가장자리 시작 위치 저장
						if (map[i][j] >= 'A' && map[i][j] <= 'Z') {
							doorsQ.add(new Location(i, j, map[i][j]));
						} else if (map[i][j] >= 'a' && map[i][j] <= 'z') {
							keys[map[i][j] - 'a'] = true;
							q.add(new Location(i, j, map[i][j]));
						} else {
							q.add(new Location(i, j, map[i][j]));
						}
					}
				}
			}
			// 초기 소문자 키 저장

			char initKeys[] = br.readLine().toCharArray();
			for (char key : initKeys) {
				if (key == '0') {
					break;
				}
				keys[key - 'a'] = true;
			}

			while (!q.isEmpty()) {
				Location curr = q.poll();

				if (visited[curr.row][curr.col]) {
					continue;
				}
				Queue<Location> tempQ = move(map, curr, keys);

				while (!tempQ.isEmpty()) {
					doorsQ.add(tempQ.poll());
				}
			}

			while (!doorsQ.isEmpty()) { // 못여는 대문자 문이 있을때 까지
				boolean findNewRoute = false;

				int loop = doorsQ.size();

				while (loop-- > 0) {
					Location curr = doorsQ.poll();

					if (!keys[curr.val - 'A']) {
						doorsQ.add(curr); // 아직 열쇠가 없으므로 다시 넣기
						continue;
					}
					map[curr.row][curr.col] = '.';

					findNewRoute = true;

					Queue<Location> nextQ = move(map, curr, keys);

					while (!nextQ.isEmpty()) {
						doorsQ.add(nextQ.poll());
					}

				}

				if (!findNewRoute) {
					break;
				}
			}

			bw.write(treasures + "\n");
		}
		bw.flush();
	}

	public static Queue<Location> move(char map[][], Location start, boolean keys[]) {
		Queue<Location> doorsQ = new LinkedList<>();
		Queue<Location> startQ = new LinkedList<>();

		startQ.add(start);

		while (!startQ.isEmpty()) {
			Location curr = startQ.poll();
			visited[curr.row][curr.col] = true;

			if (map[curr.row][curr.col] == '$') {
				treasures++;
			}

			for (int k = 0; k < upDown.length; k++) {
				Location next = new Location(curr.row + upDown[k], curr.col + leftRight[k], ' ');
				if (next.row < 0 || next.col < 0 || next.row >= map.length || next.col >= map[0].length
						|| visited[next.row][next.col] || map[next.row][next.col] == '*') {
					continue;
				}

				next.val = map[next.row][next.col];

				if (map[next.row][next.col] >= 'A' && map[next.row][next.col] <= 'Z') {
					doorsQ.add(new Location(next.row, next.col, map[next.row][next.col]));
				} else {
					visited[next.row][next.col] = true;

					if (map[next.row][next.col] >= 'a' && map[next.row][next.col] <= 'z') {
						keys[map[next.row][next.col] - 'a'] = true;
					}

					startQ.add(next);
				}
			}
		}

//		printArr(visited);
		return doorsQ;
	}

	public static void printArr(boolean map[][]) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}

class Location {
	int row;
	int col;
	char val;

	public Location(int row, int col, char val) {
		this.row = row;
		this.col = col;
		this.val = val;
	}

	@Override
	public String toString() {
		return "Location [row=" + row + ", col=" + col + ", val=" + val + "]";
	}
}