import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String arg[]) throws IOException {
		// a*b%c = (a%c*b%c)%c
		// a^10 = a^5*a^5 와 같은 지수 법칙을 이용해서 모든 값을 구하지 말고 절반의 값을 구하고 제곱을 하면 연산을 절반으로 줄일 수
		// 있다.
		// 이때 지수가 홀수인 경우 위의 나머지 법칙을 이용해서 한번 더 곱해준다.
		// 피보나치수를 행렬의 곱으로 표현하면 [fn+2,fn+1] = [[1,1],[1,0]]]*[fn+1,fn] 이다.
		// 이때 n==0일때 초기값은 [1,0] 이므로 [fn+2,fn+1] = [[1,1],[1,0]]]^n * [1,0]으로 표현할 수 있다.

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		long MatrixA[][] = { { 1, 1 }, { 1, 0 } };
		long MatrixB[][] = { { 1 }, { 0 } };

		long exponent = Long.valueOf(br.readLine());

		long[][] result = multifly(pow(MatrixA, exponent), MatrixB);

		bw.write(String.valueOf(result[1][0]));
		bw.flush();
		br.close();
		bw.close();
	}

	public static long[][] pow(long[][] base, long exponent) {
		if(exponent==0) {
			for(int i=0;i<base.length;i++) {
				for(int j=0;j<base[0].length;j++) {
					base[i][j] = 1;
				}
			}
			return base;
		}
		if (exponent == 1) {
			return base;
		} else {
			long temp[][] = pow(base, exponent / 2);

			if (exponent % 2 == 1) {
				return multifly(multifly(temp, temp), base);
			} else {
				return multifly(temp, temp);
			}
		}
	}

	public static long[][] multifly(long[][] matrixA, long[][] matrixB) {
		long[][] result = new long[matrixA.length][matrixB[0].length];

		for (int i = 0; i < matrixA.length; i++) {
			for (int j = 0; j < matrixB[0].length; j++) {
				for (int k = 0; k < matrixA.length; k++) {
					result[i][j] = (result[i][j] + matrixA[i][k] * matrixB[k][j] % 1000000007) % 1000000007;
				}
			}
		}

		return result;
	}
}