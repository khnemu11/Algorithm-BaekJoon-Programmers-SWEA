import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int parent[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int size = Integer.valueOf(br.readLine());

		Coordinate coordinates[] = new Coordinate[size];

		for (int i = 0; i < size; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			double x = Double.parseDouble(st.nextToken());
			double y = Double.parseDouble(st.nextToken());
			coordinates[i] = new Coordinate(x, y);
		}
		PriorityQueue<Distance> pq = new PriorityQueue<>();

		for (int start = 0; start < size - 1; start++) {
			for (int end = start + 1; end < size; end++) {
				pq.add(new Distance(start, end, Math.sqrt(Math.pow(coordinates[start].row - coordinates[end].row, 2)
						+ Math.pow(coordinates[start].col - coordinates[end].col, 2))));
			}
		}
		parent = new int[size];

		for (int i = 0; i < parent.length; i++) {
			parent[i] = i;
		}

		double sum = 0;

		while (!pq.isEmpty()) {
			Distance curr = pq.poll();

			if (find(curr.start) == find(curr.end)) {
				continue;
			}
			union(find(curr.start), find(curr.end));
			sum += curr.distance;
		}
		bw.write(String.format("%.2f", sum));
		bw.newLine();
		bw.flush();
	}

	public static int find(int curr) {
		if (parent[curr] == curr) {
			return curr;
		} else {

			return parent[curr] = find(parent[curr]);
		}
	}

	public static void union(int a, int b) {
		if (a != b) {
			parent[a] = b;
		}
	}
}

class Coordinate {
	double row;
	double col;

	public Coordinate(double row, double col) {
		this.row = row;
		this.col = col;
	}

	@Override
	public String toString() {
		return "Coordinate [row=" + row + ", col=" + col + "]";
	}
}

class Distance implements Comparable<Distance> {
	int start;
	int end;
	double distance;

	public Distance(int start, int end, double distance) {
		this.start = start;
		this.end = end;
		this.distance = distance;
	}

	@Override
	public String toString() {
		return "Distance [start=" + start + ", end=" + end + ", distance=" + distance + "]";
	}

	@Override
	public int compareTo(Distance o) {
		if (this.distance < o.distance) {
			return -1;
		} else {
			return 1;
		}
	}
}