import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*

N은 최대 10^6 = 100만
가능한 시간 복잡도는 최대 O(nlogn)
x,y의 범위는 -10^9~10^9
봉우리 = 연속된 x좌표가 0를 지나는 (양수->음수, 음수 -> 양수) 2개의 좌표
스택으로 최근 봉우리를 저장
만약 현재 봉우리의 오른쪽 좌표가 최근 봉우리의 오른쪽 좌표보다 크고,현재 봉우리의 왼쪽 좌표가 최근 봉우리의 왼쪽 좌표보다 작다면
현재 봉우리는 최근 봉우리를 "포함"한다.
->포함되지 않는 봉우리 감소
->포함하는 봉우리 증가
*/
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Hill> pq = new PriorityQueue<>();
		PriorityQueue<Coordinate> startQ = new PriorityQueue<>();
		int N = Integer.valueOf(br.readLine());
		Queue<Coordinate> coordQ = new LinkedList<>();
		Queue<Long> hillQ = new LinkedList<>();

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			long col = Long.valueOf(st.nextToken());
			long row = Long.valueOf(st.nextToken());
			Coordinate curr = new Coordinate(row, col);
			startQ.add(curr);
			coordQ.add(curr);
		}

		Coordinate start = startQ.poll();

		while (true) {
			if (coordQ.peek().row == start.row && coordQ.peek().col == start.col) {
				break;
			}
			coordQ.add(coordQ.poll());
		}

		start = coordQ.poll();

		while (!coordQ.isEmpty()) {
			Coordinate end = coordQ.poll();
			if ((start.row > 0 && end.row < 0) || (start.row < 0 && end.row > 0)) {
				hillQ.add(end.col);
			}
		}

		while (!hillQ.isEmpty()) {
			long left = hillQ.poll();
			long right = hillQ.poll();

			pq.add(new Hill(left, right));
		}

		int uncontained = 0;
		int uncontain = 0;
		long outMax = Long.MIN_VALUE;
		long inMax = 0;

		while (!pq.isEmpty()) {
			Hill curr = pq.poll();

			if (curr.left > outMax) { // 가장 바깥에 있는 애보다 큰경우
				uncontained++;
				uncontain++;
				outMax = curr.right;
				inMax = curr.right;
			} else if (curr.left > inMax) {
				inMax = curr.right;
				uncontain++;
			} else {
				inMax = curr.right;
			}
		}

		System.out.println(uncontained + " " + uncontain);
	}
}

class Coordinate implements Comparable<Coordinate> {
	long row;
	long col;

	public Coordinate(long row, long col) {
		this.row = row;
		this.col = col;
	}

	@Override
	public int compareTo(Coordinate o) {
		if (this.row == o.row) {
			if (this.col < o.col) {
				return -1;
			} else {
				return 1;
			}
		}
		if (this.row < o.row) {
			return -1;
		} else {
			return 1;
		}
	}
}

class Hill implements Comparable<Hill> {
	long left;
	long right;

	public Hill(long x1, long x2) {
		if (x1 < x2) {
			this.left = x1;
			this.right = x2;
		} else {
			this.left = x2;
			this.right = x1;
		}
	}

	@Override
	public String toString() {
		return "Hill [left=" + left + ", right=" + right;
	}

	@Override
	public int compareTo(Hill o) {
		if (this.left - o.left < 0) {
			return -1;
		} else {
			return 1;
		}
	}
}