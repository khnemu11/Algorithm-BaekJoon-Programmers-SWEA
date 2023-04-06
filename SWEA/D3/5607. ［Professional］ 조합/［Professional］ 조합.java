import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * 풀이 알고리즘
 * 
 * 큰 이항정리 값을 구하는 알고리즘 -> 페르마소정리 + 분할정복을 이용한 거듭제곱
 * 
 * */

public class Solution {
	static long MODULAR = 1234567891;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.valueOf(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int N = Integer.valueOf(st.nextToken());
			int R = Integer.valueOf(st.nextToken());

			long factorial[] = new long[N + 1];
			factorial[1] = 1;
			//팩토리얼을 구하는 부분
			for (int i = 2; i <= N; i++) {
				factorial[i] = ((factorial[i - 1] % MODULAR) * (i % MODULAR)) % MODULAR;
			}
			//분모를 구하는 부분 N * (N-R)
			long denominator = ((factorial[R] % MODULAR) * (factorial[N - R] % MODULAR)) % MODULAR;
			//페르마 소정리를 이용해서 이항 정리를 구하는 부분
       
			long result = ((factorial[N] % MODULAR) * (pow(denominator, MODULAR - 2))) % MODULAR;

			System.out.println("#" + tc + " " + result);
		}
	}
	//거듭 제곱을 분할 정복으로 구하는 메소드
	public static long pow(long n, long exp) {
		if (exp == 0) {
			return 1;
		} else if (exp == 1) {
			return n;
		}
		long result = 1L;

		long half = pow(n, exp / 2);

		result = ((half % MODULAR) * (half % MODULAR)) % MODULAR;

		if (exp % 2 == 1) {
			result = ((result % MODULAR) * (n % MODULAR)) % MODULAR;
		}

		return result;
	}
}
