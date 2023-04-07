import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 풀이 알고리즘
 * 
 * 
 * */

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.valueOf(br.readLine());

		PriorityQueue<Integer> pq = new PriorityQueue<>();

		ArrayList<Problem> list = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int deadline = Integer.valueOf(st.nextToken());
			int cupRamen = Integer.valueOf(st.nextToken());
			list.add(new Problem(deadline, cupRamen));
		}

		Collections.sort(list);

		for (Problem problem : list) {
			pq.add(problem.cupRamen);
			if (pq.size() > problem.deadline) {
				pq.poll();
			}
		}

		int sum = 0;

		while (!pq.isEmpty()) {
			sum += pq.poll();
		}
		bw.write(sum + "\n");
		bw.flush();
	}
}

class Problem implements Comparable<Problem> {
	int deadline;
	int cupRamen;

	public Problem(int deadline, int cupRamen) {
		this.deadline = deadline;
		this.cupRamen = cupRamen;
	}

	@Override
	public int compareTo(Problem o) {
		if (this.deadline == o.deadline) {
			return o.cupRamen - this.cupRamen;
		}
		return this.deadline - o.deadline;
	}

	@Override
	public String toString() {
		return "Problem [deadline=" + deadline + ", cupRamen=" + cupRamen + "]";
	}

}