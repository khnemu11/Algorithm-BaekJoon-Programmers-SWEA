import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
    모든 컴퓨터가 감염이 되는 시간 -> 모든 컴퓨터에 도착한 최단 시간 중 가장 긴 시간
    -> 다익스트라
    감염이 되지 않음 = 연결되지 않은 노드 = 최단거리가 inf
*/
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.valueOf(br.readLine());    //테스트케이스 개수
    
        //테스트 케이스 개수 만큼 루프
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int n = Integer.valueOf(st.nextToken());    //전체 노드 개수
			int d = Integer.valueOf(st.nextToken());    //엣지 개수
			int c = Integer.valueOf(st.nextToken());    //감염 시작 노드

			List<Computer> graph[] = new List[n + 1];    //그래프
            
            //그래프 노드 생성
			for (int i = 0; i <= n; i++) {
				graph[i] = new ArrayList<>();
			}
            
            //그래프 초기화
			for (int i = 0; i < d; i++) {
				st = new StringTokenizer(br.readLine());

				int child = Integer.valueOf(st.nextToken());
				int parent = Integer.valueOf(st.nextToken());
				int time = Integer.valueOf(st.nextToken());

				graph[parent].add(new Computer(child, time));
			}
            //최단거리를 inf로 초기화
			int times[] = new int[n + 1];
			int INF = 1_000 * 10_000 + 1;
			Arrays.fill(times, INF);
			times[c] = 0;

			PriorityQueue<Computer> pq = new PriorityQueue<>();
			pq.add(new Computer(c, 0));    //시작 컴퓨터 추가
            
            //다익스트라 알고리즘 실행
			while (!pq.isEmpty()) {
				Computer curr = pq.poll();
				if (times[curr.val] < curr.time) {
					continue;
				}

				for (Computer end : graph[curr.val]) {
					if (times[end.val] > times[curr.val] + end.time) {
						times[end.val] = times[curr.val] + end.time;

						pq.add(new Computer(end.val, times[end.val]));
					}
				}
			}
			
			int max = 0; //가장 마지막으로 감염된 컴퓨터 시간
			int cnt = 0; //감염된 컴퓨터 개수
			
			for (int i = 1; i < times.length; i++) {
				if (times[i] < INF) {
					cnt++;
					max = Math.max(times[i], max);
				}
			}

			bw.write(cnt + " " + max+"\n");
			bw.flush();
		}

		bw.flush();
	}
}


class Computer implements Comparable<Computer> {
	int val;
	int time;

	public Computer(int val, int time) {
		this.val = val;
		this.time = time;
	}

	public int compareTo(Computer computer) {
		return this.time - computer.time;
	}
}