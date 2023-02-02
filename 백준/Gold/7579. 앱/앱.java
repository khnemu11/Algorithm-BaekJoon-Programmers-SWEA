import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 
 * 풀이 알고리즘
    1) 냅색을 위한 행 : 어플, 열 : 메모리 or 비용
    2) 메모리를 열로 사용하기에는 값이 너무 크므로 비용을 열로 사용
    3) 해당 비용 별 최대의 메모리 값을 dp를 이용해 저장
    4) 가장 작은 비용부터 비용 별 메모리값을 순회하여 만약 목표 메모리보다 크거나 같다면 가장 적은 비용이므로 출력 후 루프 탈출
*/

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.valueOf(st.nextToken());
		int target = Integer.valueOf(st.nextToken());
		App apps[] = new App[N + 1];

		StringTokenizer sizeSt = new StringTokenizer(br.readLine());
		StringTokenizer costSt = new StringTokenizer(br.readLine());
		int costSum = 0;

		for (int i = 1; i <= N; i++) {
			int size = Integer.valueOf(sizeSt.nextToken());
			int cost = Integer.valueOf(costSt.nextToken());

			apps[i] = new App(size, cost);
			costSum += cost;
		}
		int dp[] = new int[costSum + 1];
		Arrays.fill(dp, -1);
		dp[0] = 0;

		for (int i = 1; i < apps.length; i++) {
			for (int j = dp.length - 1; j >= apps[i].cost; j--) {
				if (dp[j - apps[i].cost] != -1) {
					dp[j] = Math.max(dp[j], dp[j - apps[i].cost] + apps[i].size);
				}
			}
		}

		for (int i = 0; i < dp.length; i++) {
			if (dp[i] >= target) {
				bw.write("" + i);
				break;
			}
		}

		bw.flush();
	}
}

class App {
	int size;
	int cost;

	public App(int size, int cost) {
		this.size = size;
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "App [size=" + size + ", cost=" + cost + "]";
	}
}
