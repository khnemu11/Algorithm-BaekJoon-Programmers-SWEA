import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		// 반복문은 처음 증가율 끝 3가지를 꼭 생각해야함
		// 처음 : x기준으로 시작하여 X를 M으로 나누었을 때 x가 남으므로 M+x로 시작
		// 증가율 : M을 기준으로 시작하기 때문에 M씩 증가
		// 끝 : 카잉 달력에 따르면 마지막 해는 동시에 1:1이 시작되기 전 해이므로 M과 N이 동시에 1이 되려면 M과 N이 최소공배수 번째로
		// 탐색했을 때이다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.valueOf(br.readLine());

		for (int test = 0; test < T; test++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int M = Integer.valueOf(st.nextToken());
			int N = Integer.valueOf(st.nextToken());
			long x = Integer.valueOf(st.nextToken());
			long y = Integer.valueOf(st.nextToken());

			long start = x;
			long last = M * N / LCS(M, N); // 마지막 번째
			while (start <= last) {
				if (start % N == y || (N == y && start % N == 0)) {
					bw.write(String.valueOf(start));
					bw.newLine();
					break;
				} else {
					start += M;
				}
			}
			if (start > last) { // 만약 마지막 해를 넘어서면 존재 하지 않는 해이다.
				bw.write("-1");
				bw.newLine();
			}
		}

		bw.flush();
		br.close();
		bw.close();
	}

	public static long LCS(long a, long b) {
		while (b > 0) {
			long temp = a % b;
			a = b;
			b = temp;
		}
		return a;
	}
}
