import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
	풀이 알고리즘
	
	점과 점사이의 최단거리 -> 다익스트라
	간선의 개수 : 3가지 방법(1초후, 1초 전, 순간이동) * 전체 위치의 수 = 3* 100_001
	
	n^2의 알고리즘으로는 안되므로 최대 n^logn의 알고리즘 필요
	
	해당 문제는 최단거리로 가는 경로가 많을 수도 있기 때문에 방문처리를 하면 안된다
	-> bfs에서 각 depth별로 방문처리를 하기 힘드므로 방문처리를 사용하려면 dfs에서 백트래킹을 이용해야 하는데 최단거리를 구하고(nlogn) 다시
		dfs를 하기엔 최대 깊이가 100,000(시작점 0부터 목적지 100,00까지 1칸 앞으로 100,00번)이라 시간초과 우려
		
	다익스트라 원리를 이용해 출발지 현재 위치를 경유해서 다음 목적지(1초후, 1초전, 순간이동)가는 것이 출발지에서 바로 다음목적지로 가는것이 빠르면
	해당 시간으로 최신화 하고 해당 다음 위치를 다음 경유지로 사용
	
	만약 현재 위치가 목적지라면 해당 depth에 있는 목적지의 총 개수를 저장하고 시간과 개수를 출력
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int start = Integer.valueOf(st.nextToken());
		int end = Integer.valueOf(st.nextToken());

		int times[] = new int[100001];	
		Arrays.fill(times, 400000);	//출발지부터 목적지까지의 모든 최단시간은 현재 없으므로 최대치로 설정
		times[start] = 0; //시작점부터 시작점까지의 시간은 0이므로 0 설정

		Queue<Integer> q = new LinkedList<>();
		q.add(start);

		int time = 0;	//최단시간
		int cnt = 0;	//경로 수

		while (!q.isEmpty()) {
			int loop = q.size();
			while (loop-- > 0) {
				int curr = q.poll();
				if (curr == end) {	//현재 위치가 목적지라면 경로수 추가
					cnt++;
					continue;
				}

				if (curr - 1 >= 0 && times[curr - 1] >= times[curr] + 1) {	//1초전
					times[curr - 1] = times[curr] + 1;
					q.add(curr - 1);
				}
				if (curr + 1 < times.length && times[curr + 1] >= times[curr] + 1) {	//1초후
					times[curr + 1] = times[curr] + 1;
					q.add(curr + 1);
				}
				if (curr * 2 < times.length && times[curr * 2] >= times[curr] + 1) {	//순간이동 (*2)
					times[curr * 2] = times[curr] + 1;
					q.add(curr * 2);
				}
			}

			if (cnt > 0) {	//도착했다면 반목문탈출
				break;
			} else {	//아직 도착하지 못했다면 걸린시간 증가
				time++;
			}
		}
		bw.write(time + "\n");
		bw.write(cnt + "\n");
		bw.flush();
	}
}
