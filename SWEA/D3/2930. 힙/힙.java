import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 풀이 알고리즘
	최대 힙 = 역순 정렬한 우선순위 큐
	연산 1 : 1 x 
	x를 힙에 넣는다
	연산 2: 2
	최대값을 출력하고 삭제
*/
public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.valueOf(br.readLine());

		for (int testcase = 1; testcase <= T; testcase++) {
			int N = Integer.valueOf(br.readLine());
			PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int command = Integer.valueOf(st.nextToken());

				if (command == 1) {
					int val = Integer.valueOf(st.nextToken());
					pq.add(val);
				} else if (command == 2) {
					if (pq.isEmpty()) {
						sb.append("-1 ");
					} else {
						sb.append(pq.poll() + " ");
					}
				}
			}

			System.out.println("#" + testcase + " " + sb.deleteCharAt(sb.length() - 1).toString());
		}
	}

}