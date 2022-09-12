import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static PriorityQueue<Coordinate> queue;
	static boolean visited[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		queue = new PriorityQueue<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int row = Integer.valueOf(st.nextToken());
		int column = Integer.valueOf(st.nextToken());

		int maze[][] = new int[row][column];
		visited = new boolean[row][column];
		for (int i = 0; i < row; i++) {
			String route = br.readLine();
			for (int j = 0; j < route.length(); j++) {
				maze[i][j] = route.charAt(j) - 48;
			}
		}

		Coordinate start = new Coordinate(0, 0, 1);
		visited[0][0] = true;
		start.setcost(row - 1, column - 1);
		queue.add(start);

		int result = find(maze, row - 1, column - 1);

		bw.write(String.valueOf(result));
		bw.newLine();

		bw.flush();
		bw.close();
		br.close();
	}

	public static int find(int[][] maze, int goalRow, int goalColumn) {
//		System.out.println(queue.toString());
		Coordinate curr = queue.poll();
		visited[curr.row][curr.column] = true;
//		System.out.println(queue.toString());
//		System.out.println("curr : " + curr.toString());
//		System.out.println("goalRow : " + goalRow + " goalColumn : " + goalColumn);
		if (curr.row == goalRow && curr.column == goalColumn) {
			return curr.count;
		} else {
			
			if ((curr.column + 1) <= goalColumn && !visited[curr.row][curr.column + 1]
					&& maze[curr.row][curr.column + 1] == 1) {
				Coordinate next = new Coordinate(curr.row, curr.column + 1, curr.count + 1);
				next.setcost(goalRow, goalColumn);
//				System.out.println("next right >> " + next.toString());
				queue.add(next);
			}
			if ((curr.column - 1) >= 0 && !visited[curr.row][curr.column - 1]
					&& maze[curr.row][curr.column - 1] == 1) {
				Coordinate next = new Coordinate(curr.row, curr.column - 1, curr.count + 1);
				next.setcost(goalRow, goalColumn);
//				System.out.println("next left >> " + next.toString());
				queue.add(next);
			}
			if ((curr.row + 1) <= goalRow && !visited[curr.row + 1][curr.column]
					&& maze[curr.row + 1][curr.column] == 1) {
				Coordinate next = new Coordinate(curr.row + 1, curr.column, curr.count + 1);
//				System.out.println("next down >> " + next.toString());
				next.setcost(goalRow, goalColumn);
				queue.add(next);
			}
			if ((curr.row - 1) >= 0 && !visited[curr.row - 1][curr.column] && maze[curr.row - 1][curr.column] == 1) {
				Coordinate next = new Coordinate(curr.row - 1, curr.column, curr.count + 1);

				next.setcost(goalRow, goalColumn);
//				System.out.println("next up >> " + next.toString());
				queue.add(next);
			}

			return find(maze, goalRow, goalColumn);
		}
	}
}

class Coordinate implements Comparable<Coordinate> {
	int row;
	int column;
	int count;
	int cost;

	public Coordinate(int row, int column, int count) {
		this.row = row;
		this.column = column;
		this.count = count;
	}

	@Override
	public String toString() {
		return "Coordinate [row=" + row + ", column=" + column + ", count=" + count + ", distance=" + cost + "]";
	}

	public void setcost(int goalX, int goalY) {
		this.cost = (goalX - row) + 1 + (goalY - column) + 1 + count;
	}

	@Override
	public int compareTo(Coordinate o) {
		return this.cost - o.cost;
	}
}
