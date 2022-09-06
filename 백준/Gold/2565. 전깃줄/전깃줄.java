import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[] amount;
	static int[] dp;

	public static void main(String[] args) throws Exception {
		//철거해야 되는 전봇대의 최소 수 => 전체 전봇대의 수 - 세울 수 있는 전봇대의 최대의 수 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.valueOf(br.readLine());
		// 전봇대 배열
		UtilityPole[] utilityPoles = new UtilityPole[N];
		// 각 i번째 전봇대와 i 이전의 전봇대를 사용했을 때 세울 수 있는 전봇대 수의 최대값이 담겨있는 배열
		int dp[] = new int[N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			utilityPoles[i] = new UtilityPole(Integer.valueOf(st.nextToken()), Integer.valueOf(st.nextToken()));
		}
		// a 기준으로 오름차순으로 정렬하여 dp로 문제를 해결 할 수 있도록(n+1번째를 구하기 위해 n번째를 참고하는 방식) 함
		Arrays.sort(utilityPoles);
		int max = 0;

		// 전봇대의 개수만큼 루프
		for (int i = 0; i < N; i++) {
			dp[i] = 1; // 초기값은 자기 자신만 사용하는 경우이므로 1로 설정

			for (int j = 0; j < i; j++) { //i번째 이전의 전봇대를 탐색
				if (utilityPoles[j].b < utilityPoles[i].b) { //i이전의 전봇대에 연결되어있는 b가 i번째 전봇대의 b보다 작은경우만 i번째 전봇대를 사용할 때 겹치지 않고 같이 세울 수 있음
					dp[i] = Math.max(dp[j] + 1, dp[i]); //i번째+ i번째 이전의 세울수 있는 전봇대의 수와 i번째 이전의 전봇대 중 하나인 j번째에서 세울 수 있는 전봇대 수의 최개값 + 1(i번째 전봇대)를 비교하여 큰값을 i번째 전봇대에 값을 넣음 
				}
			}
			max = Math.max(dp[i], max); // 연결할 수 있는 최대의 전봇대의 수를 계산
		}

		bw.write(String.valueOf(N - max)); //철거해야 되는 전봇대의 수를 출력
		bw.newLine();
		bw.flush();
		bw.close();
		br.close();
	}
}

//a->b로 이어져 있는 전봇대 클래스
class UtilityPole implements Comparable<UtilityPole> {
	int a;
	int b;

	public UtilityPole(int a, int b) {
		this.a = a;
		this.b = b;
	}

	@Override
	public int compareTo(UtilityPole o) {
		return this.a - o.a;
	}

	@Override
	public String toString() {
		return "UtilityPole [a=" + a + ", b=" + b + "]";
	}
}
