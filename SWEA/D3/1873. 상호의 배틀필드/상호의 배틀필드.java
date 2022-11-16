import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {
			int height = sc.nextInt();
			int width = sc.nextInt();
			Tank tank = null;
			char map[][] = new char[height][width];
			for (int i = 0; i < height; i++) {
				String row = sc.next();
				for (int j = 0; j < width; j++) {
					char curr = row.charAt(j);

					if (curr == 'v' || curr == '>' || curr == '<' || curr == '^') {
						tank = new Tank(i, j, curr);
						map[i][j] = '.';
					} else {
						map[i][j] = curr;
					}
				}
			}

			sc.next();
			String commandLine = sc.next();
			int upDown[] = { -1, 1, 0, 0 };
			int leftRight[] = { 0, 0, -1, 1 };

			HashMap<Character, Integer> moveMap = new HashMap<>();
			HashMap<Character, Integer> shootMap = new HashMap<>();
			HashMap<Character, Character> headMap = new HashMap<>();

			moveMap.put('U', 0);
			moveMap.put('D', 1);
			moveMap.put('L', 2);
			moveMap.put('R', 3);
			moveMap.put('S', -1);

			shootMap.put('^', 0);
			shootMap.put('v', 1);
			shootMap.put('<', 2);
			shootMap.put('>', 3);

			headMap.put('U', '^');
			headMap.put('D', 'v');
			headMap.put('L', '<');
			headMap.put('R', '>');

			for (int i = 0; i < commandLine.length(); i++) {
				char command = commandLine.charAt(i);

				if (command == 'S') {
					int currRow = tank.row;
					int currCol = tank.col;

					while (true) {
						currRow = currRow + upDown[shootMap.get(tank.head)];
						currCol = currCol + leftRight[shootMap.get(tank.head)];

						if (currRow < 0 || currCol < 0 || currRow >= height || currCol >= width
								|| map[currRow][currCol] == '#') {
							break;
						}

						if (map[currRow][currCol] == '*') {
							map[currRow][currCol] = '.';
							break;
						}
					}

				} else {
					int move = moveMap.get(command);
					int nextRow = tank.row + upDown[move];
					int nextCol = tank.col + leftRight[move];

					tank.head = headMap.get(command);

					if (nextRow < 0 || nextCol < 0 || nextRow >= height || nextCol >= width
							|| map[nextRow][nextCol] != '.') {
						continue;
					}

					tank.row = nextRow;
					tank.col = nextCol;
				}
			}

			map[tank.row][tank.col] = tank.head;

			StringBuilder result = new StringBuilder();
			result.append("#").append(test_case).append(" ");

			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					result.append(map[i][j]);
				}
				result.append("\n");
			}

			System.out.println(result.deleteCharAt(result.length() - 1).toString());
		}
	}
}

class Tank {
	int row;
	int col;
	char head;

	public Tank(int row, int col, char head) {
		this.row = row;
		this.col = col;
		this.head = head;
	}
}