import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int dists[][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int upDown[] = { -1, 1, 0, 0 };
		int leftRight[] = { 0, 0, -1, 1 };
		int width = Integer.valueOf(st.nextToken());
		int height = Integer.valueOf(st.nextToken());

		int rooms[][] = new int[height][width];

		for (int i = 0; i < rooms.length; i++) {
			rooms[i] = Arrays.stream(br.readLine().split("")).mapToInt(x -> Integer.valueOf(x)).toArray();
		}

		dists = new int[height][width];

		for (int i = 0; i < dists.length; i++) {
			Arrays.fill(dists[i], 200000);
		}
		dists[0][0] = 0;
		Coordinate start = new Coordinate(0, 0, 0);
		PriorityQueue<Coordinate> pq = new PriorityQueue<>();
		pq.add(start);

		while (!pq.isEmpty()) {
			Coordinate curr = pq.poll();

			if (curr.cost > dists[curr.row][curr.col]) {
				continue;
			}

			for (int k = 0; k < upDown.length; k++) {
				Coordinate next = new Coordinate(curr.row + upDown[k], curr.col + leftRight[k]);

				if (next.row < 0 || next.col < 0 || next.row >= rooms.length || next.col >= rooms[0].length
						|| dists[next.row][next.col] != 200000) {
					continue;
				}

				if (dists[next.row][next.col] > dists[curr.row][curr.col] + rooms[next.row][next.col]) {
					dists[next.row][next.col] = dists[curr.row][curr.col] + rooms[next.row][next.col];
					next.cost = dists[next.row][next.col];
					pq.add(next);
				}
			}
		}

		System.out.println(dists[height - 1][width - 1]);
	}
}

class Coordinate implements Comparable<Coordinate> {
	int row;
	int col;
	int cost;

	public Coordinate(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public Coordinate(int row, int col, int cost) {
		this.row = row;
		this.col = col;
		this.cost = cost;
	}

	@Override
	public int compareTo(Coordinate o) {
		return this.cost - o.cost;
	}
}
