import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int board[][];
	static boolean isContacted[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.valueOf(st.nextToken());
		int M = Integer.valueOf(st.nextToken());

		board = new int[N][M];
		isContacted = new boolean[N][M];
		Queue<Coordinate> queue = new LinkedList<>();
		Queue<Coordinate> contactedQueue = new LinkedList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.valueOf(st.nextToken());

				if (board[i][j] == 1) {
					queue.add(new Coordinate(i, j));
				}
			}
		}
		contactedQueue.add(new Coordinate(0, 0));
		int count = 0;
		int upDown[] = { -1, 1, 0, 0 };
		int leftRight[] = { 0, 0, -1, 1 };

		while (!queue.isEmpty()) {
//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < M; j++) {
//					System.out.print(board[i][j]+" ");
//				}
//				System.out.println();
//			}
//			System.out.println();
			while (!contactedQueue.isEmpty()) {
				Coordinate curr = contactedQueue.poll();
				if (isContacted[curr.row][curr.col]) {
					continue;
				}
				checkContacted(curr);
			}

			int size = queue.size();
			ArrayList<Coordinate> meltList = new ArrayList<>();
			while (size-- > 0) {
				Coordinate curr = queue.poll();

				int adjacent = 0;

				for (int k = 0; k < upDown.length; k++) {
					Coordinate next = new Coordinate(upDown[k] + curr.row, leftRight[k] + curr.col);

					if (next.row < 0 || next.col < 0 || next.row >= N || next.col >= M) {
						continue;
					}
					if (board[next.row][next.col] == 1 || !isContacted[next.row][next.col]) {
						adjacent++;
					}
				}

				if (adjacent < 3) {
					meltList.add(curr);
				} else {
					queue.add(curr);
				}
			}

			for (Coordinate melt : meltList) {
				board[melt.row][melt.col] = 0;
				contactedQueue.add(melt);
			}

			count++;
		}

		bw.write(String.valueOf(count));
		bw.newLine();
		bw.flush();
		br.close();
	}

	static void checkContacted(Coordinate curr) {
		int upDown[] = { -1, 1, 0, 0 };
		int leftRight[] = { 0, 0, -1, 1 };

		isContacted[curr.row][curr.col] = true;

		for (int k = 0; k < upDown.length; k++) {
			Coordinate next = new Coordinate(upDown[k] + curr.row, leftRight[k] + curr.col);

			if (next.row < 0 || next.col < 0 || next.row >= isContacted.length || next.col >= isContacted[0].length
					|| isContacted[next.row][next.col]) {
				continue;
			}
			if (board[next.row][next.col] == 0) {
				checkContacted(next);
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
}