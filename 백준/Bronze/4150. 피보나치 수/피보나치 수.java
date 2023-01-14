import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;

public class Main {
	static BigInteger fibonacciArr[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int target = Integer.valueOf(br.readLine());
		fibonacciArr = new BigInteger[target + 1];
		fibonacci(target);

		bw.write(fibonacciArr[target].toString());
		bw.newLine();
		bw.flush();
	}

	public static BigInteger fibonacci(int val) {
		if (fibonacciArr[val] != null) {
			return fibonacciArr[val];
		} else if (val == 1 || val == 2) {
			fibonacciArr[val] = new BigInteger("1");
			return fibonacciArr[val];
		} else {
			fibonacciArr[val] = fibonacci(val - 1).add(fibonacci(val - 2));
			return fibonacciArr[val];
		}
	}
}
