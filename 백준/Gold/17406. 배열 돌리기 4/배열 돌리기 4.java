import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
/*
	풀이 알고리즘
*/

public class Main {
	static boolean command_visited[];
	static int init_board[][];
	static int K;
	static ArrayList<Command> commands = new ArrayList<>();
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		init(); // 초기화

		solve(0, init_board);
		bw.write(min + "\n");

		bw.flush();
		bw.close();
	}

	public static void init() throws IOException { // 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int height = Integer.valueOf(st.nextToken());
		int width = Integer.valueOf(st.nextToken());
		K = Integer.valueOf(st.nextToken());
		init_board = new int[height + 1][width + 1];
		command_visited = new boolean[K];

		for (int row = 1; row < init_board.length; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 1; col < init_board[0].length; col++) {
				init_board[row][col] = Integer.valueOf(st.nextToken());
			}
		}

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			commands.add(new Command(Integer.valueOf(st.nextToken()), Integer.valueOf(st.nextToken()),
					Integer.valueOf(st.nextToken())));
		}

		br.close();
	}

	public static void printArr(int map[][]) {
		for (int row = 0; row < map.length; row++) {
			for (int col = 0; col < map[0].length; col++) {
				System.out.print(map[row][col] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void solve(int cnt, int map[][]) {
		if (cnt == K) {
//			printArr(map);
			for (int row = 1; row < map.length; row++) {
				int row_sum = 0;
				for (int col = 1; col < map[0].length; col++) {
					row_sum += map[row][col];
				}
				min = Math.min(min, row_sum);
			}

		} else {
			for (int i = 0; i < commands.size(); i++) {
				if (command_visited[i]) {
					continue;
				}

				command_visited[i] = true;
				int spinned_map[][] = spin(commands.get(i), map);
//				printArr(spinned_map);
				solve(cnt + 1, spinned_map);
				command_visited[i] = false;
			}
		}
	}

	public static int[][] spin(Command command, int map[][]) {
		int temp[][] = new int[init_board.length][init_board[0].length];
		boolean visited[][] = new boolean[init_board.length][init_board[0].length];
		int upDown[] = { 0, 1, 0, -1, 0 };
		int leftRight[] = { 1, 0, -1, 0, 0 };

		Coordinate start = new Coordinate(command.r - command.s, command.c - command.s);
		Coordinate end = new Coordinate(command.r + command.s, command.c + command.s);

		while (!visited[start.row][start.col]) {
			int direction = 0;
			Coordinate curr = new Coordinate(start.row, start.col);

			if (start.row == end.row && start.col == end.col) {
				temp[start.row][start.col] = map[start.row][start.col];
				visited[start.row][start.col] = true;
			} else {
				while (!visited[curr.row][curr.col]) {
					Coordinate next = new Coordinate(curr.row + upDown[direction], curr.col + leftRight[direction]);

					if (next.row < start.row || next.row > end.row || next.col < start.col || next.col > end.col) {
						direction++;
						next = new Coordinate(curr.row + upDown[direction], curr.col + leftRight[direction]);
					}
					temp[next.row][next.col] = map[curr.row][curr.col];
					visited[curr.row][curr.col] = true;
					curr = next;
				}
			}

//			System.out.println("temp");
//			printArr(temp);
			start.row++;
			start.col++;
			end.row--;
			end.col--;
		}

		start = new Coordinate(command.r - command.s, command.c - command.s);
		end = new Coordinate(command.r + command.s, command.c + command.s);

		return copy(temp, map, start, end);
	}

	public static int[][] copy(int temp[][], int map[][], Coordinate start, Coordinate end) {
		int spinned_map[][] = new int[init_board.length][init_board[0].length];
		for (int row = 0; row < spinned_map.length; row++) {
			for (int col = 0; col < spinned_map[0].length; col++) {
				if (row >= start.row && row <= end.row && col >= start.col && col <= end.col) {
					spinned_map[row][col] = temp[row][col];
				} else {
					spinned_map[row][col] = map[row][col];
				}
			}
		}
//		System.out.println("temp");
//		printArr(temp);
//		System.out.println("spinned");
//		printArr(spinned_map);
		return spinned_map;
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

}

class Command {
	int r;
	int c;
	int s;

	public Command(int r, int c, int s) {
		this.r = r;
		this.c = c;
		this.s = s;
	}
}
