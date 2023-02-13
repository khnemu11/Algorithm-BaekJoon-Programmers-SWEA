import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

/*
	풀이 알고리즘
	이전, 다음에 값 추가 및 삭제 -> 링크드 리스트
	역의 개수 150만 -> 인덱스가 고유 번호가 아니기 때문에 탐색알고리즘을 사용해야 하는데 이때 링크드 리스트의 탐색 알고리즘 시간인 N^2로는 불가
	
	탐색시간을 O(1)로 할 필요가 있음 -> map사용
	이전 역 map, 다음 역 map을 사용 
	----> 시간 초과
	
	map보다 생성이 빠른 배열을 이용
*/

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] input = br.readLine().split(" ");
		int N = Integer.valueOf(input[0]);
		int M = Integer.valueOf(input[1]);

		int nextStation[] = new int[1000001];
		int prevStation[] = new int[1000001];

		StringTokenizer st = new StringTokenizer(br.readLine());
		int start = Integer.valueOf(st.nextToken());
		int from = start;
		int to = from;

		while (st.hasMoreTokens()) { // 현재->다음, 현재->이전 배열 초기화
			to = Integer.valueOf(st.nextToken());
			nextStation[from] = to;
			prevStation[to] = from;
			from = to;
		}
		nextStation[to] = start;
		prevStation[start] = to;

		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());

			String command = st.nextToken();

			if (command.equals("BN")) { // 다음 출력 및 사이에 저장
				int curr = Integer.valueOf(st.nextToken());
				int next = nextStation[curr];
				int between = Integer.valueOf(st.nextToken()); // 현재-> 사이->다음

				nextStation[curr] = between;
				nextStation[between] = next;
				prevStation[between] = curr;
				prevStation[next] = between;

				bw.write(next + "\n");
			} else if (command.equals("BP")) {// 이전 출력 및 사이에 저장
				int curr = Integer.valueOf(st.nextToken());
				int prev = prevStation[curr];
				int between = Integer.valueOf(st.nextToken()); // 이전-> 사이->현재

				nextStation[prev] = between;
				nextStation[between] = curr;
				prevStation[between] = prev;
				prevStation[curr] = between;

				bw.write(prev + "\n");
			} else if (command.equals("CN")) { // 다음 출력 및 폐쇄
				int curr = Integer.valueOf(st.nextToken());
				int next = nextStation[curr];
				int nextnext = nextStation[next]; // 현재 -> 다음 -> 다음다음

				nextStation[curr] = nextnext;
				prevStation[nextnext] = curr;

				bw.write(next + "\n");
			} else if (command.equals("CP")) { // 이전 출력 및 페쇄
				int curr = Integer.valueOf(st.nextToken());
				int prev = prevStation[curr];
				int prevprev = prevStation[prev];

				nextStation[prevprev] = curr;
				prevStation[curr] = prevprev;

				bw.write(prev + "\n");
			}
		}
		bw.flush();
	}
}
