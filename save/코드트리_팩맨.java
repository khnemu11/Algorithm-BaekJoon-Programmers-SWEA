import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {
	static int map[][] = new int[5][5];
	static int dead[][] = new int[5][5];
	static ArrayList<Coordinate> packmanRoute = new ArrayList<>();
	static ArrayList<ArrayList<Monster>> monsterList = new ArrayList<>();
	static Queue<Monster> eggQ = new LinkedList<>();
	static Coordinate packman;
	static int monsterDx[] = { 0, -1, -1, 0, 1, 1, 1, 0, -1 };
	static int monsterDy[] = { 0, 0, -1, -1, -1, 0, 1, 1, 1 };
	static int packmanDx[] = { -1, 0, 1, 0 };
	static int packmanDy[] = { 0, -1, 0, 1 };
	static Path maxPath;
	static int maxCnt = -1;
	static int MOVE_CNT = 3;
	static boolean visited[][] = new boolean[5][5];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int m = Integer.valueOf(st.nextToken());
		int t = Integer.valueOf(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int row = Integer.valueOf(st.nextToken());
		int col = Integer.valueOf(st.nextToken());

		packman = new Coordinate(row, col);

		for (int i = 0; i <= map.length * map.length; i++) {
			monsterList.add(new ArrayList<>());
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			row = Integer.valueOf(st.nextToken());
			col = Integer.valueOf(st.nextToken());
			int direction = Integer.valueOf(st.nextToken());
			map[row][col]++;
//			monsterQ.add(new Monster(row, col, direction));
			monsterList.get(row * map.length + col).add(new Monster(row, col, direction));
		}

		gameStart(t);

		int cnt = 0;

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				cnt += map[i][j];
			}
		}

		bw.write(cnt + "\n");
		bw.flush();
		bw.close();
		br.close();
	}

	public static void gameStart(int t) {
		for (int round = 0; round < t; round++) {
//			System.out.println("======= round " + (round + 1) + "====");
//			System.out.println("팩맨 : " + packman);
//			showMonster();
//			printArr(map);

			copyMonster();

			moveMonster();
//			showMonster();
//			printArr(map);
//			System.out.println("시체");
//			printArr(dead);
			movePackMan();
//			printArr(map);
//			showMonster();

			removeDead();
//			printArr(dead);
			makeMonster();
//			printArr(map);
//			showMonster();
		}
	}

	private static void makeMonster() {
//		System.out.println("====== 알 부화 ==== ");
		while (!eggQ.isEmpty()) {
			Monster monster = eggQ.poll();
//			System.out.println(monster);
			monsterList.get(monster.row * map.length + monster.col).add(monster);
			map[monster.row][monster.col]++;
		}
	}

	private static void removeDead() {
//		System.out.println("====== 시체 제거 ==== ");
		for (int i = 0; i < dead.length; i++) {
			for (int j = 0; j < dead.length; j++) {
				if (dead[i][j] > 0) {
					dead[i][j]--;
				}
			}
		}
	}

	public static void printArr(int map[][]) {
		for (int i = 1; i < map.length; i++) {
			for (int j = 1; j < map.length; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void printArr(boolean map[][]) {
		for (int i = 1; i < map.length; i++) {
			for (int j = 1; j < map.length; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}

	private static void movePackMan() {
//		System.out.println("====== 팩맨 움직임 ==== ");
		visited = new boolean[map.length][map.length];
		packmanRoute = new ArrayList<>();
		maxCnt = -1;
		packmanRoute.add(packman);
		ArrayList<Coordinate> route = new ArrayList<>();
		route.add(packman);

		findPath(route, 0);

//		System.out.println(packmanRoute);

		for (Coordinate coord : packmanRoute) {
			if (coord.row == packman.row && coord.col == packman.col) {
				continue;
			} else if (map[coord.row][coord.col] > 0) {
				dead[coord.row][coord.col] = 3;
				monsterList.get(coord.row * map.length + coord.col).clear();
				map[coord.row][coord.col] = 0;
			}
		}

		packman = packmanRoute.get(packmanRoute.size() - 1);
	}

	private static void findPath(ArrayList<Coordinate> route, int length) {
		if (length == MOVE_CNT) {
			int cnt = 0;
			HashSet<Coordinate> set = new HashSet<>();
//			System.out.println("후보 : " + route);
			for (Coordinate coord : route) {
				set.add(coord);
			}

			for (Coordinate coord : set) {
//				System.out.print(coord + " ");
				if (coord.row == packman.row && coord.col == packman.col) {
					continue;
				}
				cnt += map[coord.row][coord.col];
			}
//			System.out.println();
//			System.out.println(set);
//			System.out.println(cnt + " vs " + maxCnt);

			if (maxCnt < cnt) {
				packmanRoute.clear();
				for (Coordinate coord : route) {
					packmanRoute.add(new Coordinate(coord.row, coord.col));
				}
				maxCnt = cnt;
			}
		} else {
			for (int k = 0; k < packmanDx.length; k++) {
				Coordinate next = new Coordinate(route.get(route.size() - 1).row + packmanDx[k],
						route.get(route.size() - 1).col + packmanDy[k]);
//					System.out.println(path.curr + " -> " + next);
				if (OutofArray(map, next)) {
					continue;
				}

//				System.out.println("경로 : " + path.curr + "->" + nextPath.curr);
//				System.out.println(next);
//				printArr(visited);

//				System.out.println("경로 : " + nextPath.prev.curr + "->" + nextPath.curr);
				route.add(next);
				findPath(route, length + 1);
				route.remove(route.size() - 1);
			}
		}
	}

	private static void moveMonster() {
//		System.out.println("====== 몬스터 움직임 ==== ");
		Queue<Monster> monsterQ = new LinkedList<>();
		for (int k = 0; k < monsterList.size(); k++) {
			for (Monster monster : monsterList.get(k)) {
//				System.out.println("현재 이동전 몬스터 : " + monster);
				int nextDriection = monster.direction;
				for (int i = 0; i < 8; i++) {

					if (i != 0) {
						nextDriection = nextDriection + 1 >= monsterDx.length ? 1 : nextDriection + 1;
					}
//					System.out.println("방향 " + nextDriection);
					Coordinate next = new Coordinate(monster.row + monsterDx[nextDriection],
							monster.col + monsterDy[nextDriection]);
//					System.out.println("다음 후보 좌표 : " + next);
					if (OutofArray(map, next)) {
//						System.out.println(next);
//						System.out.println("맵 나감");
						continue;
					} else if (dead[next.row][next.col] > 0) {
//						System.out.println("시체 있음");
						continue;
					} else if (next.row == packman.row && next.col == packman.col) {
//						System.out.println("팩맨 있음");
						continue;
					}
					map[monster.row][monster.col]--;
					map[next.row][next.col]++;

//					System.out.println(monster + " -> " + next);

					monster = new Monster(next.row, next.col, nextDriection);
					break;
				}
				monsterQ.add(monster);
			}
		}

		monsterList = new ArrayList<>();

		for (int i = 0; i <= map.length * map.length; i++) {
			monsterList.add(new ArrayList<>());
		}

		while (!monsterQ.isEmpty()) {
			Monster curr = monsterQ.poll();
			monsterList.get(curr.row * map.length + curr.col).add(curr);
		}
	}

	public static void showMonster() {
		for (int i = 0; i < monsterList.size(); i++) {
			for (Monster m : monsterList.get(i)) {
				System.out.println(m);
			}
		}
	}

	public static boolean OutofArray(int map[][], Coordinate coord) {
		if (coord.row <= 0 || coord.col <= 0 || map.length <= coord.row || map[0].length <= coord.col) {
			return true;
		}

		return false;
	}

	public static void copyMonster() {
//		System.out.println("====== 알 까기 ==== ");
		eggQ = new LinkedList<>();

		for (int i = 0; i < monsterList.size(); i++) {
			for (Monster monster : monsterList.get(i)) {
				eggQ.add(new Monster(monster.row, monster.col, monster.direction));
			}
		}
	}
}

class Monster extends Coordinate {
	int direction;

	public Monster(int row, int col, int direction) {
		super(row, col);
		this.direction = direction;
	}

	@Override
	public String toString() {
		return "Monster [direction=" + direction + ", row=" + row + ", col=" + col + "]";
	}
}

class Coordinate {
	int row;
	int col;

	public Coordinate(int row, int col) {
		this.row = row;
		this.col = col;
	}

	@Override
	public String toString() {
		return "Coordinate [row=" + row + ", col=" + col + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(col, row);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinate other = (Coordinate) obj;
		return col == other.col && row == other.row;
	}
}
