import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
	풀이 알고리즘

 	골드바흐의 추측 : 4보다 큰 모든 짝수는 두 홀수 소수의 합으로 나타낼 수 있다.
 	
 	N = 1,000,000
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		boolean isNotPrime[] = new boolean[1000000 + 1];

		for (int i = 3; i <= 1000000; i = i + 2) {
			if (isNotPrime[i]) {
				continue;
			}

			for (int notPrimeNum = i * 2; notPrimeNum <= 1000000; notPrimeNum = notPrimeNum + i) {
				isNotPrime[notPrimeNum] = true;
			}
		}

		while (true) {
			int N = Integer.valueOf(br.readLine());
			if (N == 0) {
				break;
			}

			boolean valid = false;
			int l = 0;
			int r = 0;

			for (int i = 3; i <= N / 2; i += 2) {
				if (!isNotPrime[i] && !isNotPrime[N - i]) {
					l = i;
					r = N - i;
					valid = true;
					break;
				}
			}

			if (!valid) {
				bw.write("Goldbach's conjecture is wrong.\n");
			} else {
				bw.write(N + " = " + l + " + " + r + "\n");
			}
		}

		bw.flush();
	}
}