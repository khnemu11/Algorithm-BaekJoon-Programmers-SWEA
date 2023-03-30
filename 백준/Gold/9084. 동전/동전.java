import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/* 
 * 	풀이 알고리즘
 * 
 *  중복 가능한 냅색 알고리즘 
 *  
 *  현재 가능한 동전의 조합 = (이전 가능한 동전의 조합) + (현재크기 -동전의 크기의 가능한 조합의 수)
 * */

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.valueOf(br.readLine());    //테스트케이스 수

		for (int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.valueOf(br.readLine());
			int coins[] = new int[N + 1]; //동전의 개수

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) { //동전 초기화
				coins[i] = Integer.valueOf(st.nextToken());
			}

			int target = Integer.valueOf(br.readLine()); //목표 금액

			int makeTargetCnt[][] = new int[N + 1][target + 1]; //목표금액을 가방의 크기로 하는 냅색 알고리즘을 위한 배열

			for (int i = 1; i <= N; i++) {
				for (int j = 0; j <= target; j++) {
					if (j < coins[i]) { //현재 동전을 사용할 수 없는경우 => 현재 금액을 나타낼 수 있는 경우의 수  = 현재 동전을 사용하지 않는 경우의 수 
						makeTargetCnt[i][j] = makeTargetCnt[i - 1][j];
					} else if (j == coins[i]) { //현재 동전을 처음 사용할 수 있는 경우 = 현재 동전을 사용하지 않는 경우의 수 + 현재 동전만을 사용한 경우(1)
						makeTargetCnt[i][j] = makeTargetCnt[i - 1][j] + 1;
					} else { //현재 동전을 사용할 수 있는 경우 = 현재 동전을 사용하지 않는 경우의 수 + 현재 동전을 여러번 사용한 경우
						makeTargetCnt[i][j] = makeTargetCnt[i - 1][j] + makeTargetCnt[i][j - coins[i]];
					}
				}
			}

			bw.write(makeTargetCnt[N][target] + "\n");
		}

		bw.flush();
	}
}
