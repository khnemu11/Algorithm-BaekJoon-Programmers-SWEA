import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
	풀이 알고리즘
	R % ((높이 + 세로) * 2 - 4) = 회전 이동 거리
	다음 높이 = 현재 높이 -2
	다음 세로 = 현재 세로 -2
	높이,세로 중 하나가 음수면 더이상 회전 시킬 영역이 없음
*/

public class Main {
	static int arr[][];
	static int rotatedArr[][];
	static boolean visited[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int height = Integer.valueOf(st.nextToken());
		int width = Integer.valueOf(st.nextToken());
		int rotate = Integer.valueOf(st.nextToken());
		arr = new int[height][width];
		visited = new boolean[height][width];

		for (int i = 0; i < height; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < width; j++) {
				arr[i][j] = Integer.valueOf(st.nextToken());
			}
		}

		Coordinate start = new Coordinate(0, 0);
		int upDown[] = { 1, 0, -1, 0, 0 }; // 반시계방향, 마지막 요소는 한바퀴 돌았다는 의미의 의미없는 값
		int leftRight[] = { 0, 1, 0, -1, 0 };

		rotatedArr = new int[height][width];

		while (!visited[start.row][start.col]) {
			int distance = rotate % ((height + width) * 2 - 4);
			Coordinate curr = new Coordinate(start.row, start.col);
			int nextDirection = 0;
			Queue<Coordinate> visitedQ = new LinkedList<>();

			while (nextDirection < 4 && !visited[curr.row][curr.col]) {
				visitedQ.add(curr);
				move(distance, curr, nextDirection);
				Coordinate next = new Coordinate(curr.row + upDown[nextDirection], curr.col + leftRight[nextDirection]);

				if (next.row < 0 || next.col < 0 || next.row >= arr.length || next.col >= arr[0].length
						|| visited[next.row][next.col]) {
					nextDirection++;
					next = new Coordinate(curr.row + upDown[nextDirection], curr.col + leftRight[nextDirection]);
				}
				curr = next;
			}

			while (!visitedQ.isEmpty()) {
				Coordinate visitedCoord = visitedQ.poll();
				visited[visitedCoord.row][visitedCoord.col] = true;
			}

			start.row++;
			start.col++;
			height -= 2;
			width -= 2;
		}

		for (int i = 0; i < rotatedArr.length; i++) {
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < rotatedArr[0].length; j++) {
				sb.append(rotatedArr[i][j] + " ");
			}
			bw.write(sb.deleteCharAt(sb.length() - 1).toString() + "\n");
		}
		bw.flush();
	}

	public static void move(int distance, Coordinate curr, int direction) {
		int upDown[] = { 1, 0, -1, 0 }; // 반시계방향
		int leftRight[] = { 0, 1, 0, -1 };
		Coordinate next = new Coordinate(curr.row, curr.col);
		for (int move = 0; move < distance; move++) {
			if (!isValid(new Coordinate(next.row + upDown[direction], next.col + leftRight[direction]))) {
				direction = direction + 1 >= upDown.length ? 0 : direction + 1;
			}
			next.row += upDown[direction];
			next.col += leftRight[direction];
		}
		rotatedArr[next.row][next.col] = arr[curr.row][curr.col];
	}

	public static boolean isValid(Coordinate coord) {
		if (coord.row < 0 || coord.col < 0 || coord.row >= arr.length || coord.col >= arr[0].length
				|| visited[coord.row][coord.col]) {
			return false;
		}
		return true;
	}

	public static void printArr(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
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
	public boolean equals(Object obj) {
		if (!(obj instanceof Coordinate)) {
			return false;
		}

		Coordinate o = (Coordinate) obj;
		if (o.row == this.row && o.col == this.col) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return "Coordinate [row=" + row + ", col=" + col + "]";
	}

}
