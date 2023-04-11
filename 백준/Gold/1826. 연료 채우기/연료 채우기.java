import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 
 * 현재 내가 갈 수 있는 주유소 중 가장 많이 기름을 주는 주유소 선택
 * 
 * */

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.valueOf(br.readLine());
		
		//주유소를 거리가 가까운 순서로 정렬한 큐
		PriorityQueue<GasStation> stationByDistance = new PriorityQueue<>(N, (o1, o2) -> o1.distance - o2.distance);
		//주유소를 가스 양을 많이 주는 순서로 정렬한 큐
		PriorityQueue<GasStation> stationByAmount = new PriorityQueue<>(N, (o1, o2) -> o2.amount - o1.amount);
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int distance = Integer.valueOf(st.nextToken());
			int amount = Integer.valueOf(st.nextToken());

			stationByDistance.add(new GasStation(amount, distance));
		}

		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int villege = Integer.valueOf(st.nextToken()); //마을까지의 거리
		int currentGas = Integer.valueOf(st.nextToken()); //현재 가스 양
		int location = 0;	//현재 위치
		int cnt = 0;	//주유소를 들린 횟수
		
		while (!stationByDistance.isEmpty()) {	//다음 거리별 주유소 후보가 없을 때 까지
			if (location + currentGas >= villege) {	//현재 위치의 가스양으로 마을로 갈 수 있을 때
				break;
			}
			
			//현재 가스양으로 갈 수 있는 주유소를 후보지로 등록
			while (!stationByDistance.isEmpty() && location + currentGas >= stationByDistance.peek().distance) {
				stationByAmount.add(stationByDistance.poll());
			}
			
			///만약 후보지가 있으면 가장 가스양이 높은 주유소로 이동
			if (!stationByAmount.isEmpty()) {
				GasStation next = stationByAmount.poll();
				currentGas = currentGas - (next.distance - location) + next.amount;
				location = next.distance;
				cnt++;
			} 
			//없다면 더이상 갈 수 있는 주유소가 없으므로 반복문 탈출
			else {
				break;
			}
		}
		
		//현재 위치에서 마을로 갈 수 없으면 -1 출력
		if (location + currentGas < villege) {
			bw.write("-1\n");
		} else {
			bw.write(cnt + "\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}
}

class GasStation {
	int amount;
	int distance;

	public GasStation(int amount, int distance) {
		this.amount = amount;
		this.distance = distance;
	}
}