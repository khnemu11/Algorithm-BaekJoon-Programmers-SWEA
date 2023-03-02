import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 풀이 알고리즘
 * 
 * 	0) 문제 목표
 * 		1. 낚시 왕이 해당열 중 가장 행이 작은 상어를 제거
 * 		2. 상어가 이동
 * 			2.1 상어가 이동할 때 방향과 속력이 존재
 * 			2.2 속력 만큼 방향으로 이동
 * 			2.3 이때 벽이 부딪히면 반대 방향으로 돌아감
 * 			2.4 모든 상어의 이동이 끝나면 가장 큰 크기의 상어가 같은칸의 모든 상어를 먹음
 * 		3. 낚시 왕이 열을 오른쪽으로 이동
	1) 필요 알고리즘
		상어의 4방향 이동
		상어의 먹음 
*/
public class Main {
	static Queue<Shark> sharkQ = new LinkedList<>();
	static Shark map[][];
	static int R, C, total;
	static int cnt; // 남은 상어 개수
	static int upDown[] = { 0, -1, 1, 0, 0 };
	static int leftRight[] = { 0, 0, 0, 1, -1 };
	static HashMap<Integer, Integer> directionPairMap = new HashMap<>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.valueOf(st.nextToken());
		C = Integer.valueOf(st.nextToken());
		int M = Integer.valueOf(st.nextToken());
		cnt = M;
		directionPairMap.put(1, 2);
		directionPairMap.put(2, 1);
		directionPairMap.put(3, 4);
		directionPairMap.put(4, 3);

		map = new Shark[R + 1][C + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.valueOf(st.nextToken());
			int c = Integer.valueOf(st.nextToken());
			int s = Integer.valueOf(st.nextToken());
			int d = Integer.valueOf(st.nextToken());
			int z = Integer.valueOf(st.nextToken());
			Shark shark = new Shark(r, c, s, d, z);
			map[r][c] = shark;
			sharkQ.add(shark);
		}
//		printArr(map);''

		for (int col = 1; col < map[0].length; col++) {
			fishing(col);

			if (cnt == 0) {
				break;
			}

			move(sharkQ);
//			printArr(map);
		}
		bw.write(total + "\n");
		bw.flush(); // 결과 출력
		br.close();
		bw.close();
	}

	public static void fishing(int col) {
		for (int row = 0; row < map.length; row++) {
			if (map[row][col] != null) {
//				System.out.println("GET");
//				System.out.println(map[row][col]);
				total += map[row][col].size;
				map[row][col] = null;
				cnt--;
				break;
			}
		}
	}

	public static void move(Queue<Shark> q) {
		Shark[][] movedMap = new Shark[map.length][map[0].length];

		HashSet<Coordinate> set = new HashSet<>();

		while (!q.isEmpty()) {
			Shark shark = q.poll();
//			System.out.println(shark);
			if (map[shark.row][shark.col] == null) {
//				System.out.println("die");
				continue;
			}

			int moveCnt = 0;

			if (shark.direction == 1 || shark.direction == 2) {
				moveCnt = shark.speed % (R + R - 2);
			} else {
				moveCnt = shark.speed % (C + C - 2);
			}

			if (shark.speed != 0 && moveCnt == 0) {
				shark.direction = directionPairMap.get(shark.direction);
			} else {
				for (int i = 0; i < moveCnt; i++) {
					Shark next = new Shark(shark.row + upDown[shark.direction], shark.col + leftRight[shark.direction],
							shark.speed, shark.direction, shark.size);
					if (next.row < 1 || next.col < 1 || next.row >= map.length || next.col >= map[0].length) {
						shark.direction = directionPairMap.get(shark.direction);
						next = new Shark(shark.row + upDown[shark.direction], shark.col + leftRight[shark.direction],
								shark.speed, shark.direction, shark.size);
					}
					shark = next;
				}
			}

			if (movedMap[shark.row][shark.col] == null) {
				movedMap[shark.row][shark.col] = shark;
			} else if (movedMap[shark.row][shark.col].size < shark.size) {
				movedMap[shark.row][shark.col] = shark;
			}
//			printArr(movedMap);
			set.add(shark);
		}

		for (Coordinate coord : set) {
			q.add(movedMap[coord.row][coord.col]);
		}
//		System.out.println("copy");
		copyMap(map, movedMap);
	}

	public static void printArr(Shark map[][]) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void copyMap(Shark oriShark[][], Shark movedShark[][]) {
		for (int i = 0; i < oriShark.length; i++) {
			for (int j = 0; j < oriShark[0].length; j++) {
				if (movedShark[i][j] == null) {
					oriShark[i][j] = null;
				} else {
//					oriShark[i][j] = new Shark(movedShark[i][j].row, movedShark[i][j].col, movedShark[i][j].speed,
//							movedShark[i][j].direction, movedShark[i][j].size);
					oriShark[i][j] = movedShark[i][j];
				}
			}
		}
	}
}

class Coordinate {
	int row;
	int col;

	@Override
	public String toString() {
		return "Coordinate [row=" + row + ", col=" + col + "]";
	}

	public Coordinate(int row, int col) {
		this.row = row;
		this.col = col;
	}

	@Override
	public int hashCode() {
		return Objects.hash(col, row);
	}

	@Override
	public boolean equals(Object obj) {
		Coordinate o = (Coordinate) obj;
		return col == o.col && row == o.row;
	}
}

class Shark extends Coordinate {
	int speed;
	int direction;
	int size;

	public Shark(int row, int col, int speed, int direction, int size) {
		super(row, col);
		this.speed = speed;
		this.direction = direction;
		this.size = size;
	}

	@Override
	public String toString() {
		return "Shark [speed=" + speed + ", direction=" + direction + ", size=" + size + ", row=" + row + ", col=" + col
				+ "]";
	}

}