
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static boolean visited[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int length = Integer.valueOf(br.readLine());
		int board[][] = new int[length][length];
		Queue<Fish> feed = new LinkedList<>();
		Fish curr = new Fish();
		int size = 2;
		for (int i = 0; i < length; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < length; j++) {
				board[i][j] = Integer.valueOf(st.nextToken());

				if (board[i][j] == 9) {
					curr = new Fish(i, j);
					board[i][j] = 0;
				} else if (board[i][j] > 0) {
					feed.add(new Fish(i, j));
				}
			}
		}

//		System.out.println(feed.toString());

		int exp = 0;
		int time = 0;
		while (true) {
//			System.out.println("curr : " + curr.toString());
			Fish min = null;
			int minDistance = Integer.MAX_VALUE;

			int feedSize = feed.size();

			for (int i = 0; i < feedSize; i++) {
				Fish target = feed.poll();

				if (board[target.row][target.col] >= size) {
					feed.add(target);
					continue;
				}
				int targetDistance = calDistance(board, curr, target, size);
				if (targetDistance == -1) {
					feed.add(target);
					continue;
				}
				if (min == null) {
					min = target;
					minDistance = targetDistance;
				} else if (min != null) {
					// 1. 자신보다 작은 애들인데 거리가 짧은애들 리스트에 저장
					// 2. 가장 위에있는애들 필터
					// 3. 가장 왼쪽에 있는애 필터
					if (targetDistance < minDistance) {
						feed.add(min);
						min = target;
						minDistance = targetDistance;
					} else if (targetDistance == minDistance && min.compareTo(target) > 0) {
						feed.add(min);
						min = target;
						minDistance = targetDistance;
					} else {
						feed.add(target);
					}
				}
			}
			// 먹을게 없다면 탈출
			// 자신의 크기보다 작은애가 없다면 탈출

			if (min == null) {
//				System.out.println("There is no fish for eating");
				break;
			}
//
//			System.out.println("next feed : " + min.toString());
//			System.out.println(feed.toString());
//			System.out.println("distance to " + min.toString() + " : " + minDistance);

			time = time + minDistance;
			curr = min;
			exp++;

			if (size == exp) {
				size++;
				exp = 0;
			}

//			System.out.println("time : " + time);
//			System.out.println();

			// 위치로 이동하고 먹이 삭제
			// 거리저장
		}

		bw.write(String.valueOf(time));
		bw.newLine();
		bw.flush();

		br.close();
		bw.close();
	}

	public static int calDistance(int board[][], Fish start, Fish goal,int size) {
		int distance = -1;
		int upDown[] = { -1, 1, 0, 0 };
		int leftRight[] = { 0, 0, -1, 1 };
		Queue<Fish> fishqueue = new LinkedList<>();
		Queue<Integer> distanceQueue = new LinkedList<>();
		boolean visited[][] = new boolean[board.length][board.length];
		fishqueue.add(start);
		distanceQueue.add(0);

		while (!fishqueue.isEmpty()) {
			Fish curr = fishqueue.poll();
			int currDistance = distanceQueue.poll();
			visited[curr.row][curr.col] = true;

			for (int k = 0; k < 4; k++) {
				int nextRow = curr.row + upDown[k];
				int nextCol = curr.col + leftRight[k];

				if (nextRow < 0 || nextCol < 0 || nextCol >= board.length || nextRow >= board.length
						|| visited[nextRow][nextCol] || board[nextRow][nextCol] > size) {
					continue;
				}

				if (nextRow == goal.row && nextCol == goal.col) {
					return currDistance + 1;
				}
				visited[nextRow][nextCol] = true;
				fishqueue.add(new Fish(nextRow, nextCol));
				distanceQueue.add(currDistance + 1);
			}

		}

		return distance;
	}

}

class Fish implements Comparable<Fish> {
	int row;
	int col;

	public Fish() {
	}

	public Fish(int row, int col) {
		this.row = row;
		this.col = col;
	}

	@Override
	public String toString() {
		return "Fish [row=" + row + ", col=" + col + "]";
	}

	public boolean isEqual(Fish o) {
		if (this.row == o.row && this.col == o.col) {
			return true;
		}
		return false;
	}

	@Override
	public int compareTo(Fish o) {
		if (this.row == o.row) {
			return this.col - o.col;
		}

		return this.row - o.row;
	}
}
