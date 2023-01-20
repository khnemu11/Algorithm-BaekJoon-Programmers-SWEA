import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int parents[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int size = Integer.valueOf(br.readLine());
		Coordinate[] coordinates = new Coordinate[size];
		parents = new int[size];
		for (int i = 0; i < size; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			coordinates[i] = new Coordinate(i, Integer.valueOf(st.nextToken()), Integer.valueOf(st.nextToken()),
					Integer.valueOf(st.nextToken()));
		}

		for (int i = 0; i < parents.length; i++) {
			parents[i] = i;
		}

		Coordinate[] xCoordinates = Arrays.copyOf(coordinates, coordinates.length);
		Coordinate[] yCoordinates = Arrays.copyOf(coordinates, coordinates.length);
		Coordinate[] zCoordinates = Arrays.copyOf(coordinates, coordinates.length);

		Arrays.sort(xCoordinates, new Comparator<Coordinate>() {
			@Override
			public int compare(Coordinate o1, Coordinate o2) {
				return o1.x - o2.x;
			}

		});
		Arrays.sort(yCoordinates, new Comparator<Coordinate>() {
			@Override
			public int compare(Coordinate o1, Coordinate o2) {
				return o1.y - o2.y;
			}

		});
		Arrays.sort(zCoordinates, new Comparator<Coordinate>() {
			@Override
			public int compare(Coordinate o1, Coordinate o2) {
				return o1.z - o2.z;
			}
		});

		PriorityQueue<Vertex> pq = new PriorityQueue<>();

		for (int i = 0; i < xCoordinates.length - 1; i++) {
			pq.add(new Vertex(xCoordinates[i], xCoordinates[i + 1]));
		}
		for (int i = 0; i < yCoordinates.length - 1; i++) {
			pq.add(new Vertex(yCoordinates[i], yCoordinates[i + 1]));
		}
		for (int i = 0; i < zCoordinates.length - 1; i++) {
			pq.add(new Vertex(zCoordinates[i], zCoordinates[i + 1]));
		}

		int cnt = 0;
		long sum = 0;
		while (cnt < size - 1) {
			Vertex curr = pq.poll();

			if (getParent(curr.start.val) == getParent(curr.end.val)) {
				continue;
			}

			union(curr.start.val, curr.end.val);
			cnt++;
			sum += curr.min;
		}

		System.out.println(sum);
	}

	public static int getParent(int child) {
		if (child == parents[child]) {
			return child;
		} else {
			parents[child] = getParent(parents[child]);
			return parents[child];
		}
	}

	public static void union(int a, int b) {
		int pa = getParent(a);
		int pb = getParent(b);

		parents[pa] = pb;
	}
}

class Vertex implements Comparable<Vertex> {
	Coordinate start;
	Coordinate end;
	int min;

	public Vertex(Coordinate start, Coordinate end) {
		this.start = start;
		this.end = end;
		min = Math.min(Math.abs(start.x - end.x), Math.abs(start.y - end.y));
		min = Math.min(min, Math.abs(start.z - end.z));
	}

	@Override
	public int compareTo(Vertex o) {
		return this.min - o.min;
	}

	@Override
	public String toString() {
		return "Vertex [start=" + start + ", end=" + end + ", min=" + min + "]";
	}
}

class Coordinate {
	int val;
	int x;
	int y;
	int z;

	public Coordinate(int val, int x, int y, int z) {
		this.val = val;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public String toString() {
		return "Coordinate [val=" + val + ", x=" + x + ", y=" + y + ", z=" + z + "]";
	}
}