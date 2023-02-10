import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.valueOf(st.nextToken()); // 트럭 개수
		int W = Integer.valueOf(st.nextToken()); // 다리 길이(트럭이 최대 올라갈 수 있는 개수)
		int L = Integer.valueOf(st.nextToken()); // 최대 버티는 무게

		Queue<Integer> bridge = new LinkedList<>();
		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < W; i++) {
			bridge.add(0); // 다리 초기 설정
		}

		int sum = 0;
		int time = 0;

		while (st.hasMoreTokens()) {
			int truck = Integer.valueOf(st.nextToken());

			while (true) { // 다리에 트럭 넣기
				sum -= bridge.poll(); // 다리 맨 끝에있는 트럭 내보내기
				time++;
				if (sum + truck > L) {
					bridge.add(0); // 다리 하중보다 현재 다리에 있는 트럭 + 지금 넣으려는 트럭의 무게가 큰 경우 넣을 수 없으므로 빈공간 넣기
				} else {
					bridge.add(truck); // 다리에 트럭 넣기
					sum += truck;
					break;
				}
			}
		}

		time += bridge.size(); // 다리에 남은 트럭 시간 추가

		bw.write(time + "\n");
		bw.flush();
	}

}
