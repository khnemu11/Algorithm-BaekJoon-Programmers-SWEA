import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 *	풀이 과정
 *	
 *	순서가 정해져 있는 탐색 -> 위상 정렬
 *	
 * */

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.valueOf(br.readLine());

		List<Integer> graph[] = new ArrayList[N + 1];
		int parentNum[] = new int[N + 1];	//부모 개수
		int totalTimes[] = new int[N + 1];	//인덱스 까지 걸리는 총 작업 시간
		int times[] = new int[N + 1];	//인덱스 별 결리는 작업 시간
		
		//그래프 생성
		for (int i = 0; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		//위상정렬 큐 <현재 끝난 작업>
		Queue<Integer> q = new LinkedList<>();

		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int time = Integer.valueOf(st.nextToken());
			
			//작업시간 초기화
			times[i] = time;	
			totalTimes[i] = time;
			
			int size = Integer.valueOf(st.nextToken());
			
			//선행 작업이 없다면 바로 시작
			if (size == 0) {
				q.add(i);
				continue;
			}
			
			//선행 작업이 있다면 선행 작업 만큼 부모 개수 추가
			for (int j = 0; j < size; j++) {
				int parent = Integer.valueOf(st.nextToken());
				graph[parent].add(i);
				parentNum[i]++;
			}
		}

		while (!q.isEmpty()) {
			int curr = q.poll();

			for (int child : graph[curr]) {
				totalTimes[child] = Math.max(totalTimes[curr] + times[child], totalTimes[child]);
				parentNum[child]--;
				
				if (parentNum[child] == 0) {
					q.add(child);
				}
			}
		}
		
		//가장 많이 걸린 시간 = 가장 늦게 끝난 작업
		bw.write(Arrays.stream(totalTimes).max().getAsInt() + "\n");
		bw.flush();
	}
}