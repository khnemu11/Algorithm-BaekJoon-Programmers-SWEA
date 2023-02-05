import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
	풀이 알고리즘
	입력값이 200,000이므로 N^2알고리즘 불가
	강의 시간이 빨리 시작하는 기준으로 정렬
	현재 가장 빨리 끝나는 강의 시간보다 다음 강의 시간이 더 빨리시작하는 경우 강의를 한강의실에서 듣지 못함 -> 강의실 추가하고 현재 끝나는 시간을 포함하여 가장 빨리 끝나는 강의 시간을 초기화
	현재 가장 빨리 끝나는 강의 시간보다 다음 강의 시간이 더 늦게 시작하거나 같은경우 강의를 한강의실에서 듣을 수 있음 -> 가장 빨리 끝나는 강의 시간을 초기화
*/

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.valueOf(br.readLine());

		PriorityQueue<Lecture> pq = new PriorityQueue<>();

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			pq.add(new Lecture(Integer.valueOf(st.nextToken()), Integer.valueOf(st.nextToken())));
		}

		int cnt = 1;

		int end = 0;
		PriorityQueue<Integer> nextEndQ = new PriorityQueue<>();

		while (!pq.isEmpty()) {
			Lecture lecture = pq.poll();
			nextEndQ.add(lecture.end);

			if (lecture.start < end) {
				nextEndQ.add(end);
				end = nextEndQ.poll();
				cnt++;
				continue;
			}

			end = nextEndQ.poll();
		}

		bw.write(cnt + "\n");
		bw.flush();
	}

}

class Lecture implements Comparable<Lecture> {
	int start;
	int end;

	public Lecture(int start, int end) {
		this.start = start;
		this.end = end;
	}

	@Override
	public int compareTo(Lecture o) {
		return this.start - o.start;
	}

	@Override
	public String toString() {
		return "Lecture [start=" + start + ", end=" + end + "]";
	}
}
