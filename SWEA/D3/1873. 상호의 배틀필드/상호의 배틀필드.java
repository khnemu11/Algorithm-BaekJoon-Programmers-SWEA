import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static char map[][];
	static int upDown[] = { -1, 1, 0, 0 };
	static int leftRight[] = { 0, 0, -1, 1 };
	static int UP = 0;
	static int DOWN = 1;
	static int LEFT = 2;
	static int RIGHT = 3;
	static HashMap<Character, Integer> moveStrategy = new HashMap<>();
	static HashMap<Integer, Character> moveIcon = new HashMap<>();
	static String total_command;

	public static void main(String[] args) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.valueOf(br.readLine());
		moveStrategy.put('U', UP);
		moveStrategy.put('D', DOWN);
		moveStrategy.put('L', LEFT);
		moveStrategy.put('R', RIGHT);

		moveIcon.put(UP, '^');
		moveIcon.put(DOWN, 'v');
		moveIcon.put(LEFT, '<');
		moveIcon.put(RIGHT, '>');

		for (int testcase = 1; testcase <= T; testcase++) {
			Tank start = init();
			tankAction(start);
			bw.write(printArr(testcase));
//			System.out.println(total_command);
		}

		bw.flush();
		bw.close();
	}

	public static void tankAction(Tank tank) {
		for (int i = 0; i < total_command.length(); i++) {
			char command = total_command.charAt(i);
            
			if (command == 'S') {
				shoot(tank);
			} else {
				move(tank, command);
			}
		}
	}

	public static void shoot(Tank tank) {
		Coordinate cannon = new Coordinate(tank.row + upDown[tank.direction], tank.col + leftRight[tank.direction]); // 탱크의
																														// 다음
																														// 위치부터
																														// 시작함

		while (isValid(cannon)) {
			if (map[cannon.row][cannon.col] == '#') {
				break;
			} else if (map[cannon.row][cannon.col] == '*') {
				map[cannon.row][cannon.col] = '.';
				break;
			} else {
				cannon = new Coordinate(cannon.row + upDown[tank.direction], cannon.col + leftRight[tank.direction]);
			}
		}
	}

	public static void move(Tank tank, char command) { // 포신 방향 수정, 평지라면 이동 (탱크 위치, 명령어)
		tank.direction = moveStrategy.get(command); // 포신 방향 수정
		Tank next = new Tank(tank.row + upDown[moveStrategy.get(command)],
				tank.col + leftRight[moveStrategy.get(command)], moveStrategy.get(command)); // 다음 이동 탱크

		if (canMove(next)) { // 탱크가 이동이 가능하다면
			map[tank.row][tank.col] = '.'; // 현재 위치를 평지로
			map[next.row][next.col] = moveIcon.get(next.direction); // 다음 위치로 옮기고 포신 방향대로 맵에 저장
			tank.row = next.row;
			tank.col = next.col;
		} else {
			map[tank.row][tank.col] = moveIcon.get(tank.direction); // 현재 위치의 포신 방향만 수정
		}
	}

	public static boolean canMove(Tank tank) {
		if (!isValid(tank)) {
			return false;
		}
		if (map[tank.row][tank.col] == '.') {
			return true;
		}
		return false;
	}

	public static boolean isValid(Coordinate coord) {
		if (coord.row < 0 || coord.col < 0 || coord.row >= map.length || coord.col >= map[0].length) {
			return false;
		}
		return true;
	}

	public static Tank init() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.valueOf(st.nextToken()); // 행
		int M = Integer.valueOf(st.nextToken()); // 열
		map = new char[N][M];
		Tank tank = null;

		for (int row = 0; row < N; row++) {
			char input[] = br.readLine().toCharArray();
			for (int col = 0; col < M; col++) {
				map[row][col] = input[col];

				if (map[row][col] == '<') {
					tank = new Tank(row, col, LEFT);
				} else if (map[row][col] == '>') {
					tank = new Tank(row, col, RIGHT);
				} else if (map[row][col] == '^') {
					tank = new Tank(row, col, UP);
				} else if (map[row][col] == 'v') {
					tank = new Tank(row, col, DOWN);
				}
			}
		}

		int length = Integer.valueOf(br.readLine());
		total_command = br.readLine();

		return tank;
	}

	public static String printArr(int testcase) {
		StringBuilder sb = new StringBuilder();
		sb.append("#").append(testcase).append(" ");

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				sb.append(map[i][j]);
			}
			sb.append("\n");
		}

		return sb.toString();
	}
}

class Coordinate {
	int row;
	int col;

	public Coordinate() {
	}

	public Coordinate(int row, int col) {
		this.row = row;
		this.col = col;
	}
}

class Tank extends Coordinate {
	int direction;

	public Tank(int row, int col, int direction) {
		super(row, col);
		this.direction = direction;
	}

	@Override
	public String toString() {
		return "Tank [direction=" + direction + ", row=" + row + ", col=" + col + "]";
	}
}