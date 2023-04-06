import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 풀이 알고리즘
 * 
 * 빈칸의 상하좌우를 값을 빈칸에 넣어 파악
 * 최소 이동 횟수를 구해야 하므로 bfs로
 * bfs 방문처리를 위해 3x3을 1x9 String으로 변형
 * string을 set으로 설정하여 방문 처리 
 * 
 * */

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringBuilder sb = new StringBuilder();
		HashSet<String> visited = new HashSet<>();

		int idx = 0;

		for (int i = 0; i < 3; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			for (int j = 0; j < 3; j++) {
				sb.append(st.nextToken());
				// 0인 인덱스 위치 확인
				if (sb.toString().charAt(sb.length() - 1) == '0') {
					idx = i * 3 + j;
				}
			}
		}

		String goal = "123456780";	//목표 맵의 모양

		PriorityQueue<Board> pq = new PriorityQueue<>();
		pq.add(new Board(sb.toString(), idx));
		int minTime = -1;

		while (!pq.isEmpty()) {
			Board curr = pq.poll();
			Queue<Board> nextQ = new LinkedList<>();
			visited.add(curr.val);

			if (goal.equals(curr.val)) {
				minTime = curr.time;
				break;
			}
			// 왼쪽
			if (curr.idx % 3 != 0 && curr.idx - 1 >= 0) {
				int nextIdx = curr.idx - 1;
				String nextStr = changeStr(curr, nextIdx);
				nextQ.add(new Board(nextStr, nextIdx, curr.time + 1));
			}

			// 오른쪽
			if (curr.idx % 3 != 2 && curr.idx + 1 < 9) {
				int nextIdx = curr.idx + 1;
				String nextStr = changeStr(curr, nextIdx);
				nextQ.add(new Board(nextStr, nextIdx, curr.time + 1));
			}
			// 위
			if ((curr.idx - 3) >= 0) {
				int nextIdx = curr.idx - 3;
				String nextStr = changeStr(curr, nextIdx);
				nextQ.add(new Board(nextStr, nextIdx, curr.time + 1));
			}
			// 아래
			if ((curr.idx + 3) < 9) {
				int nextIdx = curr.idx + 3;
				String nextStr = changeStr(curr, nextIdx);
				nextQ.add(new Board(nextStr, nextIdx, curr.time + 1));
			}

			while (!nextQ.isEmpty()) {
				Board next = nextQ.poll();

				if (visited.contains(next.val)) {
					continue;
				}

				visited.add(next.val);
				pq.add(next);
			}
		}

		bw.write(minTime + "\n");

		bw.flush();

	}
	//현재 0인 곳과 다음 인덱스를 교환하는 메소드
	public static String changeStr(Board board, int nextIdx) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < board.val.length(); i++) {
			if (i == board.idx) {
				sb.append(board.val.charAt(nextIdx));
			} else if (i == nextIdx) {
				sb.append(board.val.charAt(board.idx));
			} else {
				sb.append(board.val.charAt(i));
			}
		}

		return sb.toString();
	}
}

class Board implements Comparable<Board> {
	String val;
	int time;
	int idx;

	public Board(String val, int idx) {
		this.val = val;
		this.idx = idx;
	}

	public Board(String val, int idx, int time) {
		this.val = val;
		this.idx = idx;
		this.time = time;
	}

	@Override
	public int compareTo(Board o) {
		return this.time - o.time;
	}
}
