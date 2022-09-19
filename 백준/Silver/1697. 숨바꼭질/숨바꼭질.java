import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static boolean visited[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		Queue<Move> moves = new LinkedList<>();
		visited = new boolean[100001];
		StringTokenizer st = new StringTokenizer(br.readLine());

		int start = Integer.valueOf(st.nextToken());
		int goal = Integer.valueOf(st.nextToken());

		moves.add(new Move(0, start));

		int result = find(moves, goal);

		bw.write(String.valueOf(result));
		bw.flush();
		bw.close();
		br.close();
	}

	public static int find(Queue<Move> moves, int goal) {
//		System.out.println(moves.size());
		Move element = moves.poll();
//		System.out.println("curr " + element.curr);
		visited[element.curr] = true;
//		System.out.println(element);
		if (element.curr == goal) {
			return element.count;
		}

		if (element.curr > 0 && !visited[element.curr - 1]) {
			moves.add(new Move(element.count + 1, element.curr - 1));
		}
		if (element.curr < goal) {
			if (element.curr + 1 <= 100000 && !visited[element.curr + 1]) {
				moves.add(new Move(element.count + 1, element.curr + 1));
			}
			if (element.curr * 2 <= 100000 && !visited[element.curr * 2]) {
				moves.add(new Move(element.count + 1, element.curr * 2));
			}

		}

		return find(moves, goal);
	}
}

class Move implements Comparable<Move> {
	int count;
	int curr;
	int differ;

	public Move(int count, int curr) {
		this.count = count;
		this.curr = curr;
	}

	@Override
	public String toString() {
		return "Move [count=" + count + ", curr=" + curr + ", differ=" + differ + "]";
	}

	@Override
	public int compareTo(Move o) {
		if (this.count != o.count) {
			return this.differ - o.differ;
		}
		return this.count - o.count;
	}
}
