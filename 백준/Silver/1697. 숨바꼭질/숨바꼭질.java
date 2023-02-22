import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

/*풀이 알고리즘
 * *2, +1, -1를 이동하여 목표 장소면 탈출하는 반복문 필요 -> BFS
 * 이때 *2가 +1, -1보다 이동량이 많으므로 정답일 확률이 높음 -> DEQUE를 활용해 우선순위 적용
 * 먼저 도착한 위치의 시간을 저장하여 동생의 위치에 도착했을 때 bfs를 탈출하고 동생의 위치의 시간을 출력
 * */

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.valueOf(st.nextToken()); // 수빈이의 위치
		int K = Integer.valueOf(st.nextToken()); // 동생의 위치

		boolean visited[] = new boolean[100001];
		int time[] = new int[100001];
		Deque<Integer> deque = new LinkedList<>();
		deque.add(N);

		while (!deque.isEmpty()) {
			int curr = deque.pollFirst();
			visited[curr] = true;
			if (curr == K) {
				break;
			}
			if (curr * 2 < visited.length && !visited[curr * 2]) {
				time[curr * 2] = time[curr] + 1;
				deque.addFirst(curr * 2); // 우선순위가 높으므로 앞에 저장
				visited[curr * 2] = true;
			}

			if (curr + 1 < visited.length && !visited[curr + 1]) {
				time[curr + 1] = time[curr] + 1;
				deque.addLast(curr + 1); // 우선순위가 낮으므로 뒤에 저장
				visited[curr + 1] = true;
			}
			if (curr - 1 >= 0 && !visited[curr - 1]) {
				time[curr - 1] = time[curr] + 1;
				deque.addLast(curr - 1); // 우선순위가 낮으므로 뒤에 저장
				visited[curr - 1] = true;
			}
		}

		bw.write(time[K] + "\n");
		bw.flush();
		bw.close();
		br.close();
	}
}