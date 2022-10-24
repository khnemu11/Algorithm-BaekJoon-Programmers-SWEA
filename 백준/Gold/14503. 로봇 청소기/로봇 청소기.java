import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static Map<Integer, Vacuum> directionMap = new HashMap<>();
	static int board[][];
	static boolean visited[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int height = Integer.valueOf(st.nextToken());
		int width = Integer.valueOf(st.nextToken());
		board = new int[height][width];
		visited = new boolean[height][width];

		st = new StringTokenizer(br.readLine());

		Vacuum curr = new Vacuum(Integer.valueOf(st.nextToken()), Integer.valueOf(st.nextToken()));
		int direction = Integer.valueOf(st.nextToken());
		curr.direction = direction;

		directionMap.put(0, new Vacuum(-1, 0));
		directionMap.put(1, new Vacuum(0, 1));
		directionMap.put(2, new Vacuum(1, 0));
		directionMap.put(3, new Vacuum(0, -1));

		for (int i = 0; i < height; i++) {
			board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();
		}
//		System.out.println("청소기 가동");
		int count = execute(curr);
//		System.out.println("청소기 종료");

		bw.write(String.valueOf(count));
		bw.newLine();
		bw.flush();

		br.close();
		bw.close();
	}

	public static int execute(Vacuum curr) {
		int clean = 0;
		while (curr != null) {
			if (!visited[curr.row][curr.col]) {
//				System.out.println("청소");
				visited[curr.row][curr.col] = true;
				clean++;
			}
			
//			for(int i=0;i<board.length;i++) {
//				for(int j=0;j<board[0].length;j++) {
//					System.out.print(visited[i][j]);
//					System.out.print(" ");
//				}
//				System.out.println();
//			}
			
//			System.out.println("현재 위치 : " + curr.toString());
			Vacuum next = findNext(curr);

			if (next != null) {
				curr = next;
			} else {
				curr = backMove(curr);
			}
		}

		return clean;
	}

	public static Vacuum findNext(Vacuum curr) {
//		System.out.println("다음 목표 탐색");

		int count = 4;
		int direction = curr.direction == 0 ? 3 : curr.direction - 1;

		while (count-- > 0) {

			Vacuum next = new Vacuum(curr.row + directionMap.get(direction).row,
					curr.col + directionMap.get(direction).col, direction);
//			System.out.println(next.toString() + " 확인");

			if (next.row >= 0 && next.col >= 0 && next.row < board.length && next.col < board[0].length
					&& board[next.row][next.col] == 0 && !visited[next.row][next.col]) {
//				System.out.println("다음 목표 탐색 끝");
//				System.out.println(next.toString() + " 으로 이동");

				return next;
			}

			direction = direction == 0 ? 3 : direction - 1;
		}
//		System.out.println("청소할 구역 없음");
		return null;

	}

	public static Vacuum backMove(Vacuum curr) {
//		System.out.println("후진 시작");
		int backDirection = curr.direction + 2 > 3 ? curr.direction - 2 : curr.direction + 2;
		Vacuum back = new Vacuum(curr.row + directionMap.get(backDirection).row,
				curr.col + directionMap.get(backDirection).col, curr.direction);
//		System.out.println("후진 좌표 : " + back.toString());
		
		if (back.row < 0 || back.col < 0 || back.row >= board.length || back.col >= board[0].length
				|| board[back.row][back.col] == 1) {
//			System.out.println("후진 불가");
			return null;
		}

//		System.out.println("후진 가능");
		return back;
	}

}

class Vacuum {
	int row;
	int col;
	int direction;

	Vacuum() {
	}

	Vacuum(int row, int col) {
		this.row = row;
		this.col = col;
	}

	Vacuum(int row, int col, int direction) {
		this.row = row;
		this.col = col;
		this.direction = direction;
	}

	@Override
	public String toString() {
		return " ( " + row + " , " + col + " )+" + " 방향 :  " + direction;
	}
}