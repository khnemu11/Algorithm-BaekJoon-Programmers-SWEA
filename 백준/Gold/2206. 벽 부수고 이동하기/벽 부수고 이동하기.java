import java.io.*;
import java.util.*;

/*
	풀이 알고리즘
	
	벽을 부쉈는지, 안부쉈는지 정보가 담긴 방문처리 필요
	
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int height = Integer.valueOf(st.nextToken());
		int width = Integer.valueOf(st.nextToken());
		boolean visited[][][] = new boolean[2][height][width];
		int map[][] = new int[height][width];
		Coordinate end = new Coordinate(height - 1, width - 1);
		for (int i = 0; i < height; i++) {
			String inputRowString = br.readLine();
			for (int j = 0; j < width; j++) {
				map[i][j] = inputRowString.charAt(j) - '0';
			}
		}

		Queue<Coordinate> queue = new LinkedList<>();
		queue.add(new Coordinate(0, 0));
		int upDown[] = { -1, 1, 0, 0 };
		int leftRight[] = { 0, 0, -1, 1 };

		int time = 1;
		boolean isFind = false;
		while (!queue.isEmpty()) {
//			System.out.println("Time : " + time);
			int loop = queue.size();

			while (loop-- > 0) {
				Coordinate currCoordinate = queue.poll();
				visited[currCoordinate.isBreakWall][currCoordinate.row][currCoordinate.col] = true;
//				System.out.println(currCoordinate);
				if (currCoordinate.row == end.row && currCoordinate.col == end.col) {
					isFind = true;
					break;
				}

				for (int k = 0; k < 4; k++) {
					Coordinate nextCoordinate = new Coordinate(currCoordinate.row + upDown[k],
							currCoordinate.col + leftRight[k], currCoordinate.isBreakWall);

					if (nextCoordinate.row < 0 || nextCoordinate.col < 0 || nextCoordinate.row >= map.length
							|| nextCoordinate.col >= map[0].length) {
						continue;
					}

					if (visited[nextCoordinate.isBreakWall][nextCoordinate.row][nextCoordinate.col]) {
						continue;
					}

					if (map[nextCoordinate.row][nextCoordinate.col] == 0) {
						visited[nextCoordinate.isBreakWall][nextCoordinate.row][nextCoordinate.col] = true;
						queue.add(nextCoordinate);
					}

					if (map[nextCoordinate.row][nextCoordinate.col] == 1 && nextCoordinate.isBreakWall == 0) {
						nextCoordinate.isBreakWall = 1;
						visited[nextCoordinate.isBreakWall][nextCoordinate.row][nextCoordinate.col] = true;
						queue.add(nextCoordinate);
					}
				}
			}

			if (isFind) {
				break;
			}
			time++;
		}
		if (isFind) {
			System.out.println(time);
		} else {
			System.out.println(-1);
		}

	}
}

class Coordinate {
	int row;
	int col;
	int isBreakWall;

	public Coordinate(int row, int col) {
		super();
		this.row = row;
		this.col = col;
		isBreakWall = 0;
	}

	public Coordinate(int row, int col, int isBreakWall) {
		super();
		this.row = row;
		this.col = col;
		this.isBreakWall = isBreakWall;
	}

	@Override
	public String toString() {
		return "Coordinate [row=" + row + ", col=" + col + ", isBreakWall=" + isBreakWall + "]";
	}

}