import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

/*
	풀이 알고리즘
	입력값이 10만 ->배열 가능
	-1,+1,*2로 이동
	도착한 시간의 배열을 만들어 인덱스를 도착한 시간, 값을 이전 도착한 시간(인덱스로 활용)해서 역추적
	
*/

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.valueOf(st.nextToken());
		int K = Integer.valueOf(st.nextToken());

		Deque<Integer> dq = new LinkedList<>();
		dq.add(N);

		int locations[] = new int[100001];
		Arrays.fill(locations, -1);

		while (!dq.isEmpty()) {
			int curr = dq.pollFirst();

			if (curr == K) {
				break;
			}
			if (curr * 2 < locations.length && locations[curr * 2] == -1) {
				locations[curr * 2] = curr;
				dq.addFirst(curr * 2);
			}
			if (curr + 1 < locations.length && locations[curr + 1] == -1) {
				locations[curr + 1] = curr;
				dq.addLast(curr + 1);
			}
			if (curr - 1 >= 0 && locations[curr - 1] == -1) {
				locations[curr - 1] = curr;
				dq.addLast(curr - 1);
			}
		}

		int curr = K;
		Stack<Integer> stack = new Stack<>();

		while (curr != N) {
			stack.add(curr);
			curr = locations[curr];
		}
		bw.write("" + stack.size());
		bw.newLine();

		StringBuilder sb = new StringBuilder(N + " ");

		while (!stack.isEmpty()) {
			sb.append(stack.pop() + " ");
		}

		bw.write(sb.deleteCharAt(sb.length() - 1).toString());
		bw.newLine();
		bw.flush();
	}
}
