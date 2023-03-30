import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/* 
 * 	풀이 알고리즘
 * 
 *  정해진 크기 이내,이상의 최대 값 = 냅색 알고리즘
 * 	
 * 	메모리 크기로 가방의 크기로 한다
 *  -> 메모리의 크기는 최대 합은 10,000,000 * 100
 *  O(n) = 핸드폰의 개수 * 최대 메모리의 합 =100*10,000,000 * 100 > 1억 
 *  시간 초과
 *  
 *  비활성화 비용의 합을 가방의 크기로 한다.
 *  -> 비활성화 비용의 최대 합은 100*100
 *  O(n) = 핸드폰의 개수 * 최대 비활성화 비용의 합 =100*100 * 100 <1억 
 *  시간 내 가능
 *  
 *  비활성화 크기 별 얻을 수 있는 최대 메모리의 크기를 냅색 알고리즘으로 구한 뒤
 *  목표 메모리크기보다 같거나 크고 가장 작은 비활성화 크기를 출력 
 * 
 * */

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.valueOf(st.nextToken()); // 물건 개수
		int M = Integer.valueOf(st.nextToken()); // 비활성화 어플 크기

		App apps[] = new App[N + 1]; // 탐색할 어플배열

		st = new StringTokenizer(br.readLine());

		for (int i = 1; i <= N; i++) { // 어플의 메모리 값 설정
			apps[i] = new App();
			apps[i].memory = Integer.valueOf(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());

		int costSum = 0; // 비활성화 비용의 합

		for (int i = 1; i <= N; i++) {// 어플의 비활성화 비용 설정
			apps[i].cost = Integer.valueOf(st.nextToken());
			costSum += apps[i].cost;
		}

		int maxMemeory[][] = new int[N + 1][costSum + 1]; // 비활성화 비용의 합을 가방의 크기로 하여 비활성화 비용 별 얻을 수 있는 최대 메모리 크기를 나타내는 dp
															// 배열 생성

		for (int i = 1; i <= N; i++) { // 냅색 알고리즘
			for (int j = 0; j <= costSum; j++) {
				if (j < apps[i].cost) {
					maxMemeory[i][j] = maxMemeory[i - 1][j];
				} else {
					maxMemeory[i][j] = Math.max(maxMemeory[i - 1][j],
							maxMemeory[i - 1][j - apps[i].cost] + apps[i].memory);
				}
			}
		}

		for (int i = 0; i <= costSum; i++) { // 목표 메모리보다 같거나 큰 메모리 중 가장 작은 비활성화 비용을 가진 비용 탐색 및 출력
			if (maxMemeory[N][i] >= M) {
				bw.write(i + "\n");
				break;
			}
		}

		bw.flush();
	}
}

class App {
	int memory;
	int cost;

	public App() {
	}
}
