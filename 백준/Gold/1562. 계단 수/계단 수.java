import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
 * 풀이 과정
 * 
 * 뒷자리의 n이 들어 오는 개수 = (뒷자리-1번째에 n-1) +(뒷자리+1번째에 n1) 
 * 이때 모든 수를 방문했을 경우만 세면 되므로 0~9를 비트마스킹으로 표현
 * 방문 배열 = [숫자방문][길이(n)][끝자리수(m)] => 비트마스킹에 있는 숫자를 방문하고 끝자리가 m이고 길이가 n 수열의 개수
 * 2^10 * 100 * 10  < 1억
 * 
 * 
 * */

public class Main {
	static long stairNum[][][];
	static long MODULAR = 1_000_000_000;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.valueOf(br.readLine());

		stairNum = new long[1 << 10][N + 1][10];

		for (int i = 1; i < 10; i++) {
			stairNum[1 << i][1][i] = 1;
		}

		for (int visited = 0; visited < (1 << 10); visited++) {
			for (int length = 2; length <= N; length++) {
				for (int num = 0; num < 10; num++) {
					int next = visited | (1 << num);
					if (num - 1 >= 0) {
						stairNum[next][length][num] = (stairNum[visited][length - 1][num - 1]
								+ stairNum[next][length][num]) % MODULAR;

					}
					if (num + 1 < 10) {
						stairNum[next][length][num] = (stairNum[visited][length - 1][num + 1]
								+ stairNum[next][length][num]) % MODULAR;
					}
				}
			}
		}

		long sum = 0;
		for (int i = 0; i < 10; i++) {
			sum = (sum + stairNum[(1 << 10) - 1][N][i]) % MODULAR;
		}

		bw.write(sum + "\n");
		bw.flush();
	}
}
