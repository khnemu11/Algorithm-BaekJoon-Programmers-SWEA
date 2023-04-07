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
 * 문제를 데드라인 내로 풀 수 있는가 -> 풀 수 있는 문제 중 가장 이득이 되는 것은 무엇인가?
 * 
 * 문제를 데드라인 내로 풀 수 있는가 == 현재까지 내가 푼 문제의 개수(소요한 시간)가 해당 문제의 데드라인 보다 작다
 * 
 * 가장 데드라인이 짧은 문제를 풀 수 있으면 긴 문제도 풀 수 있다.
 * => 데드라인이 짧은 문제가 작은 순서로 정렬 후 이득이 큰 것을 선택
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

		Collections.sort(list);	//데드라인이 짧은 순서로 정렬

		for (Problem problem : list) {
			pq.add(problem.cupRamen); //얻을 수 있는 컵라면 추가
			
			if (pq.size() > problem.deadline) { //만약 이 문제를 풀었을때 데드라인을 넘어선다면 가장 컵라면 수가 작은 문제를 제거
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
