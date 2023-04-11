import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 현재 내가 갈 수 있는 주유소 중 가장 많이 기름을 주는 주유소 선택
 * 
 * 
 * */

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.valueOf(br.readLine());
		PriorityQueue<GasStation> stationByDistance = new PriorityQueue<>(N, (o1, o2) -> o1.distance - o2.distance);
		PriorityQueue<GasStation> stationByAmount = new PriorityQueue<>(N, (o1, o2) -> o2.amount - o1.amount);
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int distance = Integer.valueOf(st.nextToken());
			int amount = Integer.valueOf(st.nextToken());

			stationByDistance.add(new GasStation(amount, distance));
		}

		StringTokenizer st = new StringTokenizer(br.readLine());
		int villege = Integer.valueOf(st.nextToken());
		int currentGas = Integer.valueOf(st.nextToken());
		int location = 0;
		int cnt = 0;
		while (!stationByDistance.isEmpty()) {
			if (location + currentGas >= villege) {
				break;
			}

			while (!stationByDistance.isEmpty() && location + currentGas >= stationByDistance.peek().distance) {
				stationByAmount.add(stationByDistance.poll());
			}

			if (!stationByAmount.isEmpty()) {
				GasStation next = stationByAmount.poll();
				currentGas = currentGas - (next.distance - location) + next.amount;
				location = next.distance;
				cnt++;
			} else {
				break;
			}
		}

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

	@Override
	public String toString() {
		return "GasStation [amount=" + amount + ", distance=" + distance + "]";
	}

}